package com.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.SiteUserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class SiteUserController {

    private final SiteUserService userService;

    @GetMapping("/signup")
    public String userSignup(UserCreateForm userCreateForm) {

        return "/signup_form";
    }

    @PostMapping("/signup")
    public String userSignup(@Valid UserCreateForm userCreateForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                System.out.println(error.toString());
//            }
            return "/signup_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2()))
        {
            bindingResult.rejectValue(
                    "password2",
                    "passwordInCorrect",
                    "2 개 의 패 스 워 드 가 일 치 하 지 않 습 니 다.");
            return "/signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이 미 등 록 된 사 용 자 입 니 다.");
            return "/signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/signup_form";
        }
        return "redirect:/question/list";
    }

    @GetMapping("/login")
    public String userLogin() {

        return "/login_form";
    }
}
