package com.example.controller;

import com.example.model.AnswerForm;
import com.example.service.QuestionService;
import com.example.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    private final SiteUserService userService;

    @GetMapping("/list")
    public String questionList(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "kw", defaultValue = "") String kw) {

        // TODO:

        return "/question_list";
    }

    @GetMapping("/detail/{id}")
    public String questionDetail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {

        // TODO:

        return "/question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {

        // TODO:

        return "/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/question_form";
        }

        // TODO:

        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(@PathVariable("id") Integer id,
                                 Principal principal) {

        // TODO:

        return "redirect:/question/list";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {

        // TODO:

        return "/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/question_form";
        }

        // TODO:

        return "redirect:/question/detail/%s";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    @ResponseBody
    public String questionVote(@PathVariable("id") Integer id,
                               Principal principal) {

        // TODO:

        return null;
    }

}
