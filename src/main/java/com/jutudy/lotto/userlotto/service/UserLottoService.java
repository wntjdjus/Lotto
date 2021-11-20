package com.jutudy.lotto.userlotto.service;

import com.jutudy.lotto.userlotto.domain.UserLotto;
import com.jutudy.lotto.userlotto.domain.UserLottoRepository;
import com.jutudy.lotto.userlotto.web.dto.UserLottoListRequestDto;
import com.jutudy.lotto.userlotto.web.dto.UserLottoResponseDto;
import com.jutudy.lotto.userlotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lotto.userlotto.web.dto.UserLottoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

        userLotto.update(requestDto.getNum1(), requestDto.getNum2(), requestDto.getNum3(), requestDto.getNum4(), requestDto.getNum5(),
                requestDto.getNum6(), requestDto.getBuyYn(), requestDto.getHitYn(), requestDto.getScore());

        return id;
    }

    public UserLottoResponseDto findById(Long id) {
        UserLotto entity = userLottoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저로또정보가 없습니다. id=" + id));

        return new UserLottoResponseDto(entity);
    }

    public List<UserLottoResponseDto> findList(UserLottoListRequestDto requestDto) {
        List<UserLotto> list;
        return null;
    }
}