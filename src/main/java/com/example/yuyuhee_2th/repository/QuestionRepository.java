package com.example.yuyuhee_2th.repository;

import com.example.yuyuhee_2th.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // TODO:
    Question findBySubject(String subject);
}
