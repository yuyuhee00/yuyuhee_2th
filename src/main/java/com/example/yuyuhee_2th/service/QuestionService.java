package com.example.yuyuhee_2th.service;

import com.example.yuyuhee_2th.model.Answer;
import com.example.yuyuhee_2th.model.Question;
import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.repository.QuestionRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Page<Question> getPageList(int page, String kw) {

        // TODO:
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        // return this.questionRepository.findAll(pageable);
        Specification<Question> spec = search(kw);
        return this.questionRepository.findAll(spec, pageable);
    }

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            //
            // Predicate - Where clause in SQL
            // where 문을 작성할때 Parameter의 Type 및 값 유무에 따라 조건을 다르게 하고 싶을 사용
            //
            @Override
            public Predicate toPredicate(Root<Question> q,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                query.distinct(true); // 중 복 을 제 거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"), // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
            }
        };
    }

    // http://127.0.0.1:8081/question/list/2
    public Question getQuestionById(Integer id) {

        // TODO:

        return null;
    }

    // http://127.0.0.1:8081/question/create
    public void create(String subject, String content, SiteUser user){

        // TODO:

    }

    public void modify(Question question, String subject, String content) {

        // TODO:

    }

    public void delete(Integer id) {

        // TODO:

    }

    public void delete(Question question) {

        // TODO:
    }

    public int vote(Question question, SiteUser siteUser) {

        // TODO:

        return 0;
    }
}
