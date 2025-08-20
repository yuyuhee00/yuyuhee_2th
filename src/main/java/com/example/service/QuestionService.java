package com.example.service;

import org.springframework.stereotype.Service;
import com.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
}
