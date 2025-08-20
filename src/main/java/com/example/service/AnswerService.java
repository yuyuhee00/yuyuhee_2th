package com.example.service;

import com.example.model.Answer;
import com.example.model.Question;
import com.example.model.SiteUser;
import com.example.repository.AnswerRepository;
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
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    public List<Answer> getListByQuestion(Question question) {
        return this.answerRepository.getAnswersByQuestion(question);
    }

    public Answer getAnswerById(Integer id) {
        return this.answerRepository.getReferenceById(id);
    }

    public void delete(Integer id) {
        this.answerRepository.deleteById(id);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public int vote(Answer answer, SiteUser siteUser) {
        if (! answer.getVoter().add(siteUser))
            return answer.getVoter().size();

        return this.answerRepository.save(answer).getVoter().size();
    }
}
