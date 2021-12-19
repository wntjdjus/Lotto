package com.jutudy.lottoproject;

import com.jutudy.lottoproject.config.auth.LoginUser;
import com.jutudy.lottoproject.config.auth.dto.SessionUser;
import com.jutudy.lottoproject.winLotto.service.WinLottoService;
import com.jutudy.lottoproject.winLotto.web.dto.WinLottoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final WinLottoService winLottoService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        WinLottoResponseDto responseDto = winLottoService.selectRecentWinLotto();

        model.addAttribute("nextRound", responseDto.getRound() + 1);

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(responseDto.getDate()));
            calendar.add(Calendar.DATE, 7);
            model.addAttribute("nextDay", dateFormat.format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userId", user.getEmail());
        }
        return "index.html";
    }

    @GetMapping("/myList")
    public String myList(Model model, @LoginUser SessionUser user) {

        WinLottoResponseDto responseDto = winLottoService.selectRecentWinLotto();

        model.addAttribute("recentRound", responseDto.getRound() + 1);

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userId", user.getEmail());
        }
        return "myList.html";
    }
}
