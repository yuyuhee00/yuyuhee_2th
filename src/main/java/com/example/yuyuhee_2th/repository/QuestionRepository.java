package com.example.yuyuhee_2th.repository;

import com.example.yuyuhee_2th.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // TODO:
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
