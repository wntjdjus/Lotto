package com.jutudy.lottoproject.lotto.service;

import com.jutudy.lottoproject.lotto.domain.Lotto;
import com.jutudy.lottoproject.lotto.web.dto.RandomLottoResponseDto;
import com.jutudy.lottoproject.winLotto.domain.WinLotto;
import com.jutudy.lottoproject.winLotto.domain.WinLottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class LottoService {

    private final WinLottoRepository winLottoRepository;

    public RandomLottoResponseDto getRandomLotto() {
        RandomLottoResponseDto responseDto = null;
        Lotto lotto = Lotto.builder().build();
        lotto.randomize();
        responseDto = new RandomLottoResponseDto(lotto);
        responseDto = findPastWinCnt(responseDto);

        return responseDto;
    }

    public RandomLottoResponseDto getRandomLotto(int exceptRoundNum){
        RandomLottoResponseDto responseDto = null;

        Lotto lotto = Lotto.builder().build();
        lotto.randomize();
        responseDto = new RandomLottoResponseDto(lotto);
        responseDto = findPastWinCnt(responseDto);

        return responseDto;
    }

    public RandomLottoResponseDto findPastWinCnt(RandomLottoResponseDto dto) {
        List<WinLotto> winLottos = winLottoRepository.findAll();
        Set<Long> winLottoSet = null;
        Set<Long> randomLottoSet = new HashSet<>();
        int size = winLottos.size();
        int hitCnt = 0;

        randomLottoSet.add(dto.getNum1());
        randomLottoSet.add(dto.getNum2());
        randomLottoSet.add(dto.getNum3());
        randomLottoSet.add(dto.getNum4());
        randomLottoSet.add(dto.getNum5());
        randomLottoSet.add(dto.getNum6());

        for (int i = 0; i < size; i++) {
            winLottoSet = new HashSet<>();
            hitCnt = 0;
            WinLotto winLotto = winLottos.get(i);
            winLottoSet.add(winLotto.getNum1());
            winLottoSet.add(winLotto.getNum2());
            winLottoSet.add(winLotto.getNum3());
            winLottoSet.add(winLotto.getNum4());
            winLottoSet.add(winLotto.getNum5());
            winLottoSet.add(winLotto.getNum6());

            if (winLottoSet.contains(dto.getNum1())) {
                hitCnt += 1;
            }
            if (winLottoSet.contains(dto.getNum2())) {
                hitCnt += 1;
            }
            if (winLottoSet.contains(dto.getNum3())) {
                hitCnt += 1;
            }
            if (winLottoSet.contains(dto.getNum4())) {
                hitCnt += 1;
            }
            if (winLottoSet.contains(dto.getNum5())) {
                hitCnt += 1;
            }
            if (winLottoSet.contains(dto.getNum6())) {
                hitCnt += 1;
            }

            switch (hitCnt) {
                case 6:
                    dto.addPastWin1Cnt();
                    break;
                case 5:
                    if (randomLottoSet.contains(winLotto.getBonusNum())) {
                        dto.addPastWin2Cnt();
                    } else {
                        dto.addPastWin3Cnt();
                    }
                    break;
                case 4:
                    dto.addPastWin4Cnt();
                    break;
                case 3:
                    dto.addPastWin5Cnt();
                    break;
                default:
                    break;
            }
        }

        return dto;
    }
}


