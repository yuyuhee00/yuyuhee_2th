package com.example.yuyuhee_2th.service;

import com.example.yuyuhee_2th.model.Answer;
import com.example.yuyuhee_2th.model.Question;
import com.example.yuyuhee_2th.model.SiteUser;
import com.example.yuyuhee_2th.repository.AnswerRepository;
import com.example.yuyuhee_2th.utils.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content, SiteUser author) {

        // TODO:
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public List<Answer> getListByQuestion(Question question) {

        // TODO:
        return this.answerRepository.getAnswersByQuestion(question);
    }

    public Answer getAnswerById(Integer id) {

        // TODO:
        return this.answerRepository.getReferenceById(id);
    }

    public void delete(Integer id) {

        // TODO:
        this.answerRepository.deleteById(id);
    }

    public Answer getAnswer(Integer id) {

        // TODO:
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        }
        throw new DataNotFoundException("answer not found");

    }
    public void modify(Answer answer, String content) {

        // TODO:
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {

        // TODO:
        this.answerRepository.delete(answer);
    }

    public int vote(Answer answer, SiteUser siteUser) {

        // TODO:
        if (! answer.getVoter().add(siteUser))
            return answer.getVoter().size();

        return this.answerRepository.save(answer).getVoter().size();

    }
}
