package com.jutudy.lottoproject.winLotto.service;

import com.jutudy.lottoproject.util.ApiCallService;
import com.jutudy.lottoproject.util.DateUtil;
import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLottoRepository;
import com.jutudy.lottoproject.winLotto.web.dto.WinLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class WinLottoService {

    private final ApiCallService apiCallService;
    private final WinLottoRepository winLottoRepository;
    private final String lottoUrl = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";

    private WinLotto winLottoApiCall(long round) {
        try {

            JSONObject object = apiCallService.apiCall(lottoUrl + round, "GET", null, null);
            if (object.get("returnValue").equals("success")) {
                System.out.println(object.get("drwNo"));
                WinLotto winLotto = WinLotto.builder()
                        .round((Long) object.get("drwNo"))
                        .date(object.get("drwNoDate").toString().replaceAll("-", ""))
                        .num1((Long) object.get("drwtNo1"))
                        .num2((Long) object.get("drwtNo2"))
                        .num3((Long) object.get("drwtNo3"))
                        .num4((Long) object.get("drwtNo4"))
                        .num5((Long) object.get("drwtNo5"))
                        .num6((Long) object.get("drwtNo6"))
                        .bonusNum((Long) object.get("bnusNo"))
                        .firstReward((Long) object.get("firstWinamnt"))
                        .firstWinnerCnt((Long) object.get("firstPrzwnerCo"))
                        .firstTotalReward((Long) object.get("firstAccumamnt"))
                        .build();

                return winLotto;

            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public WinLottoResponseDto selectWinLottoByRound(Long round) {
        WinLotto winLotto = winLottoRepository.findByRound(round);
        if (winLotto == null) {
            insertWinLotto(round);
            winLotto = winLottoRepository.findByRound(round);
        }

        return new WinLottoResponseDto(winLotto);
    }

    public WinLottoResponseDto selectRecentWinLotto() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();
        String recentSat = dateFormat.format(DateUtil.findRecentSaturday(today));
        WinLotto winLotto = winLottoRepository.findTopByOrderByDateDesc();
        if (winLotto == null || !winLotto.getDate().equals(recentSat)) {
            long round = 0;
            if (winLotto != null) {
                round = winLotto.getRound();
            }
            insertWinLotto(round + 1);
            winLotto = winLottoRepository.findTopByOrderByRoundDesc();
        }

        return new WinLottoResponseDto(winLotto);
    }

    public void insertWinLotto(long round) {
        WinLotto winLotto = null;
        while (true) {
            winLotto = winLottoApiCall(round);
            if (winLotto == null) {
                break;
            }
            winLottoRepository.save(winLotto);
            round += 1;
        }
    }

}
