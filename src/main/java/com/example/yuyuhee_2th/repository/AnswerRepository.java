package com.example.yuyuhee_2th.repository;

import com.example.yuyuhee_2th.model.Answer;
import com.example.yuyuhee_2th.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    // TODO:
    List<Answer> getAnswersByQuestion(Question question);
}
