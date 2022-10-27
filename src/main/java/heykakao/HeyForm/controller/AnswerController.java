package heykakao.HeyForm.controller;
import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.model.dto.AnswerDto;
import heykakao.HeyForm.repository.AnswerRepository;
import heykakao.HeyForm.service.DtoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@ResponseBody
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    DtoService dtoService;
    @GetMapping("/answer")
    public List<Answer> getAllAnswer(){
        return answerRepository.findAll();
    }

    @GetMapping("/answer/{surveyId}")
    @ApiOperation(value = "설문조사 답변 조회", notes = "설문조사 id로 모든 답변 조회")
    public List<AnswerDto> getAnswersBySurveyId(@PathVariable Long surveyId){
        return dtoService.getAnswersBySurveyId(surveyId);
    }
}
