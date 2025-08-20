package com.example.controller;

import com.example.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SiteUserController {

    private final SiteUserService userService;

    @GetMapping("/signup")
    public String userSignup(UserCreateForm userCreateForm) {

        // TODO:

        return "/signup_form";
    }

    @PostMapping("/signup")
    public String userSignup(@Valid UserCreateForm userCreateForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/signup_form";
        }

        // TODO:

        return "redirect:/question/list";
    }

    @GetMapping("/login")
    public String userLogin() {

        // TODO:

        return "/login_form";
    }
}
