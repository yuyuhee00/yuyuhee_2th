package com.example.service;

import com.example.model.Answer;
import com.example.model.Question;
import com.example.model.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw);

        return this.questionRepository.findAll(spec, pageable);
        //return this.questionRepository.findAllByKeyword(kw, pageable);
    }
    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                query.distinct(true); // 중 복 을 제 거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제 목
                        cb.like(q.get("content"), "%" + kw + "%"), // 내 용
                        cb.like(u1.get("username"), "%" + kw + "%"), // 질 문 작 성자
                        cb.like(a.get("content"), "%" + kw + "%"), // 답 변 내 용
                        cb.like(u2.get("username"), "%" + kw + "%")); // 답 변 작 성자
            }
        };
    }

    // http://127.0.0.1:8081/question/list/2
    public Question getQuestionById(Integer id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    // http://127.0.0.1:8081/question/create
    public void create(String subject, String content, SiteUser user){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Integer id) {
        this.questionRepository.deleteById(id);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public int vote(Question question, SiteUser siteUser) {
        if (! question.getVoter().add(siteUser))
            return question.getVoter().size();

        return this.questionRepository.save(question).getVoter().size();
    }
}
