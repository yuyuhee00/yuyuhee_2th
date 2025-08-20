package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class Answer {
    private Integer id;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Question question;
    private SiteUser author;
    Set<SiteUser> voter;
}
