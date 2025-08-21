package com.example.yuyuhee_2th.controller;

import com.example.yuyuhee_2th.model.Question;
import com.example.yuyuhee_2th.model.AnswerForm;
import com.example.yuyuhee_2th.model.QuestionForm;
import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.service.QuestionService;
import com.example.yuyuhee_2th.service.SiteUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    private final SiteUserService userService;

    // http://127.0.0.1:8082/question/list
    @GetMapping("/list")
    public String questionList(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "kw", defaultValue = "") String kw) {

        // TODO:
        Page<Question> paging = this.questionService.getPageList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        // return "jsp_home";
        return "jsp_question_list";
    }

    @GetMapping("/detail/{id}")
    public String questionDetail(Model model,
                                 @PathVariable("id") Integer id) {
        // TODO:
        Question question = this.questionService.getQuestionById(id);
        if (question == null) {
            return "jsp_question_list";
        }

        model.addAttribute("question", question);
        return "jsp_question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {

        // TODO:
        return "jsp_question_form";
    }

    //
    // Principle
    // - 1. 로그인 : 사용자가 로그인 하면, 스프링 시큐리티는 해당 사용자의 정보를 Principle 객체에 저장.
    // - 2. 보호된 자원접근 : 사용자가 인증된 상태에서 보호된 페이지에 접근할 때, 스프링 시큐리티는 Principle 을 이용해 현재 사용자를 확인.
    // - 3. 권한 확인 : Principle 또는 @AuthenticationPrinciple 을 사용하여, 어떤 권한을 가지고 있는지 확인 하고, 이에 따라 접근을 제어.
    //
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "jsp_question_form";
        }

        // TODO:
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(@PathVariable("id") Integer id,
                                 Principal principal) {
        // TODO:
        Question question = questionService.getQuestionById(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        this.questionService.delete(id);
        return "redirect:/question/list";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {
        // TODO:
        Question question = questionService.getQuestionById(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "jsp_question_form";
    }

    //
    // BindingResult
    // - BindingResult는 스프링부트가 컨트롤러에서 요청받은 객체를 바인딩 하는데 발생하는 오류를 포착해주는 역할.
    // - BindingResult는 주로 컨트롤러의 메서드 파라미터 내에 @ModelAttribute 뒤에 위치해야 하며 그 사이에 다른 파라미터가 오는 건 상관없다.
    // - BindingResult는 Model에 자동으로 포함이 된다.
    // - BindingResult를 사용하게 되면 400 오류가 발생하지 않고 에러를 담아 컨트롤러를 정상호출한다. 따라서, 오류페이지에 넘어가기 전에 처리를 할수 있다.
    //
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "jsp_question_form";
        }

        // TODO:
        Question question = questionService.getQuestionById(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    @ResponseBody
    public String questionVote(@PathVariable("id") Integer id,
                               Principal principal) {
        // TODO:
        Question question = questionService.getQuestionById(id);
        if (question.getAuthor() != null && question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "추천 권한이 없습니다.");
        }

        SiteUser siteUser = this.userService.getUser(principal.getName());
        return Integer.toString(this.questionService.vote(question, siteUser));
    }
}
