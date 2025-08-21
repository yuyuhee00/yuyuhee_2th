package com.example.yuyuhee_2th.controller;

import ch.qos.logback.core.spi.ErrorCodes;
import com.example.yuyuhee_2th.model.UserCreateForm;
import com.example.yuyuhee_2th.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
        return "jsp_signup_form";
    }

    @PostMapping("/signup")
    public String userSignup(@Valid UserCreateForm userCreateForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "jsp_signup_form";
        }

        // TODO:
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue(userCreateForm.getPassword2(),
                    "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "jsp_signup_form";
        }

        try {
            System.out.println("passwd 1 " + userCreateForm.getPassword1());
            System.out.println("passwd 2 " + userCreateForm.getPassword2());

            this.userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getEmail());
        } catch (DataIntegrityViolationException de) {
            de.printStackTrace();
            bindingResult.reject("singedUpFailed", "이미 등록된 사용자 입니다.");
            return "jsp_signup_form";
        } catch (Exception e) {
            bindingResult.reject("singedUpFailed", e.getMessage());
            return "jsp_signup_form";
        }
        return "redirect:/question/list";
    }

    @GetMapping("/login")
    public String userLogin() {

        // TODO:
        return "jsp_login_form";
    }
}
