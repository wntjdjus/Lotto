package com.jutudy.lottoproject.winLotto.service;

import com.jutudy.lottoproject.util.ApiCallService;
import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLottoRepository;
import com.jutudy.lottoproject.winLotto.web.dto.WinLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WinLottoService {

    private final ApiCallService apiCallService;
    private final WinLottoRepository winLottoRepository;
    private final String lottoUrl = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=";

    public WinLottoResponseDto selectWinLottoByRound(Long round) {
        WinLotto winLotto = winLottoRepository.findByRound(round);
        if (winLotto == null) {
            JSONObject object = apiCallService.apiCall(lottoUrl + round, "GET", null, null);
            if(object.get("returnValue").equals("success")){
                System.out.println(object.get("drwNo"));
                winLotto = WinLotto.builder()
                        .round((Long)object.get("drwNo"))
                        .date(object.get("drwNoDate").toString().replaceAll("-",""))
                        .num1((Long)object.get("drwtNo1"))
                        .num2((Long)object.get("drwtNo2"))
                        .num3((Long)object.get("drwtNo3"))
                        .num4((Long)object.get("drwtNo4"))
                        .num5((Long)object.get("drwtNo5"))
                        .num6((Long)object.get("drwtNo6"))
                        .bonusNum((Long)object.get("bnusNo"))
                        .firstReward((Long)object.get("firstWinamnt"))
                        .firstWinnerCnt((Long)object.get("firstPrzwnerCo"))
                        .firstTotalReward((Long)object.get("firstAccumamnt"))
                        .build();
            }else{
                throw new RuntimeException();
            }
        }

        return new WinLottoResponseDto(winLotto);
    }

}
