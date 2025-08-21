package com.example.yuyuhee_2th.controller;

import com.example.yuyuhee_2th.model.Answer;
import com.example.yuyuhee_2th.model.AnswerForm;
import com.example.yuyuhee_2th.model.Question;
import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.service.AnswerService;
import com.example.yuyuhee_2th.service.QuestionService;
import com.example.yuyuhee_2th.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final SiteUserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @Valid AnswerForm answerForm,
                               BindingResult bindingResult,
                               @PathVariable("id") Integer id,
                               Principal principal) {
        // TODO:
        Question question = this.questionService.getQuestionById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "jsp_question_detail";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);

        //
        // For Anker in Page
        // - %s#answer_%s : <a id="answer_${answer.id}"></a>
        //
        return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm,
                               @PathVariable("id") Integer id,
                               Principal principal) {
        // TODO:
        Answer answer = this.answerService.getAnswer(id);
        if (answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        answerForm.setContent(answer.getContent());
        return "jsp_answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm,
                               BindingResult bindingResult,
                               @PathVariable("id") Integer id,
                               Principal principal) {
        if (bindingResult.hasErrors()) {
            return "jsp_answer_form";
        }

        // TODO:
        Answer answer = this.answerService.getAnswer(id);
        if (answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable("id") Integer id,
                               Principal principal) {
        // TODO:
        Answer answer = this.answerService.getAnswer(id);
        if (answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(id);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    @ResponseBody
    public String answerVote(@PathVariable("id") Integer id,
                              Principal principal) {
        // TODO:
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        return Integer.toString(this.answerService.vote(answer, siteUser));
    }
}
