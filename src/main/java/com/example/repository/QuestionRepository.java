package com.example.repository;

import com.example.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Answer,Integer> {

    // TODO:

}
