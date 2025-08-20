package com.example.service;

import org.springframework.stereotype.Service;
import com.example.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

}
