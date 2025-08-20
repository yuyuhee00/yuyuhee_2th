package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Question {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private List<Answer> answerList;
    private SiteUser author;
    Set<SiteUser> voter;
}
