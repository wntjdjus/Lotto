package com.jutudy.lottoproject;

import com.jutudy.lottoproject.config.auth.LoginUser;
import com.jutudy.lottoproject.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userId", user.getEmail());
        }
        return "index1.html";
    }
}
