package com.jutudy.lotto.web;

import com.jutudy.lotto.service.UserLottoService;
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
