package heykakao.HeyForm.controller;
import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ResponseBody
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @GetMapping("/answer")
    public List<Answer> getAllAnswer(){
        return answerRepository.findAll();
    }

}
