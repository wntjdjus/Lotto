package com.jutudy.lottoproject.userLotto.web;

import com.jutudy.lottoproject.config.auth.LoginUser;
import com.jutudy.lottoproject.config.auth.dto.SessionUser;
import com.jutudy.lottoproject.userLotto.service.UserLottoService;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoResponseDto;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoSaveRequestDto;
import com.jutudy.lottoproject.userLotto.web.dto.UserLottoUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/userlotto/{id}")
    public Long delete(@PathVariable Long id){
        userLottoService.delete(id);
        return id;
    }

    @GetMapping("/userlotto/{id}")
    public UserLottoResponseDto findById(@PathVariable Long id){
        return userLottoService.findById(id);
    }

    @GetMapping("/userlottos/{round}")
    public List<UserLottoResponseDto> findAll(@PathVariable long round, @LoginUser SessionUser user){
        return userLottoService.findAll(user.getEmail(), round);
    }
}
