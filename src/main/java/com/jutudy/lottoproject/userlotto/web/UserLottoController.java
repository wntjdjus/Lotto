package com.jutudy.lottoproject.userlotto.web;

import com.jutudy.lottoproject.config.auth.LoginUser;
import com.jutudy.lottoproject.config.auth.dto.SessionUser;
import com.jutudy.lottoproject.userlotto.service.UserLottoService;
import com.jutudy.lottoproject.userlotto.web.dto.UserLottoResponseDto;
import com.jutudy.lottoproject.userlotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lottoproject.userlotto.web.dto.UserLottoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserLottoController {

    private final UserLottoService userLottoService;

    @PostMapping("/userlotto")
    public Long save(@RequestBody UserLottoSaveRequestDto requestDto){

        return userLottoService.save(requestDto);
    }

    @PutMapping("/userlotto/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserLottoUpdateRequestDto requestDto){
        return userLottoService.update(id, requestDto);
    }

    @GetMapping("/userlotto/{id}")
    public UserLottoResponseDto findById(@PathVariable Long id){
        return userLottoService.findById(id);
    }
}
