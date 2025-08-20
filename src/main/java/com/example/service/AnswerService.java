package com.example.service;

import com.example.model.Answer;
import com.example.model.Question;
import com.example.model.SiteUser;
import com.example.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, SiteUser author) {

        // TODO:

        return null;
    }

    public List<Answer> getListByQuestion(Question question) {

        // TODO:

        return null;
    }

    public Answer getAnswerById(Integer id) {

        // TODO:

        return null;
    }

    public void delete(Integer id) {

        // TODO:

    }

    public Answer getAnswer(Integer id) {

        // TODO:

        return null;
    }
    public void modify(Answer answer, String content) {

        // TODO:

    }

    public void delete(Answer answer) {

        // TODO:
    }

    public int vote(Answer answer, SiteUser siteUser) {

        // TODO:

        return null;
    }
}
