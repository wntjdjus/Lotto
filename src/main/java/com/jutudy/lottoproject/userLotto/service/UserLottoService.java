package com.jutudy.lottoproject.userLotto.service;

import com.jutudy.lottoproject.userLotto.domain.UserLotto;
import com.jutudy.lottoproject.userLotto.domain.UserLottoRepository;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoResponseDto;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserLottoService {

    private final UserLottoRepository userLottoRepository;

    @Transactional
    public Long save(UserLottoSaveRequestDto requestDto) {
        return userLottoRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UserLottoUpdateRequestDto requestDto) {
        UserLotto userLotto = userLottoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저로또정보가 없습니다. id=" + id));

        userLotto.update(requestDto.getNum1(), requestDto.getNum2(), requestDto.getNum3(),
                requestDto.getNum4(), requestDto.getNum5(), requestDto.getNum6(), requestDto.getBuyYn());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        UserLotto userLotto = userLottoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저로또정보가 없습니다. id=" + id));

        userLottoRepository.delete(userLotto);
    }

    public UserLottoResponseDto findById(Long id) {
        UserLotto entity = userLottoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저로또정보가 없습니다. id=" + id));

        return new UserLottoResponseDto(entity);
    }

    public List<UserLottoResponseDto> findAll(String userId, int round) {
        List<UserLottoResponseDto> responseDtos = new ArrayList<>();
        List<UserLotto> list = userLottoRepository.findAllByRound(userId, round);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            UserLottoResponseDto responseDto = new UserLottoResponseDto(list.get(i));
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }
}
