package heykakao.HeyForm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.exception.ResourceNotFoundException;
import heykakao.HeyForm.model.Question;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.dto.AnswerDto;
import heykakao.HeyForm.model.dto.QuestionDto;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.repository.*;
import heykakao.HeyForm.service.DtoService;
import heykakao.HeyForm.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/api/v1/")
@ResponseBody
@RequiredArgsConstructor
public class SurveyController {
    @Autowired
    DtoService dtoService;
    @Autowired
    SurveyService surveyService;


    //Surveyjson type 리턴값 : 설문 url
    //"{\"surveyDto\":{\"survey_state\":0,\"survey_url\":\"www.heykakao.com\"},\"questionDtos\":[{\"question_type\":2,\"question_order\":1,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs1 bla bla bla\"}]},{\"question_type\":1,\"question_order\":2,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs2 bla bla bla\"},{\"choice_order\":1,\"choice_contents\":\"ch1 bla bla bla\"},{\"choice_order\":2,\"choice_contents\":\"ch2 bla bla bla\"}]}]}"
    @PostMapping("/survey/{userAccount}")
    public String createSurvey(@RequestParam String surveyJson, @RequestParam String userAccount) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        Long survey_id = dtoService.saveSurvey(userAccount,surveyQuestionDto);
        // ... 이어서
//        List<String> url = surveyRepository.findByUser_Account(userAccount).stream().map(Survey::getUrl).collect(Collectors.toList());
//        return "/survey/post/"+url.get(url.size()-1);
        return "";
    }
    //설문 정보를 url을 통해 전달한다.
    @GetMapping("/survey/post/{surveyUrl}")
    public SurveyQuestionDto createPaper(@PathVariable String surveyUrl){
        return dtoService.getSurveyQuestionByUrl(surveyUrl);
    }

    @PostMapping("/survey/delete/{surveyId}")
    public void deleteSurvey(@RequestParam Long surveyId){
        surveyService.delSurvey(surveyId);
    }

    //surveyId를 통해 업데이트..
    @PostMapping("/survey/update/{userId}/{surveyId}")
    public void updateSurvey(@RequestParam String surveyJson, @RequestParam Long userId) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        dtoService.updateSurvey(surveyQuestionDto);
    }
    //surveyId를 통해 설문지 정보 불러오기
    @GetMapping("/survey/paper/{surveyId}")
    public String postSurvey(@PathVariable Long surveyId) throws JsonProcessingException{
        SurveyQuestionDto surveyQuestionDto = dtoService.getSurveyQuestionBySurveyId(surveyId);
        ObjectMapper objectMapper = new ObjectMapper();
        String surveyJson = objectMapper.writeValueAsString(surveyQuestionDto);
        return surveyJson;
    }

    // userId를 통해 해당 유저의  survey, question, answer 정보 모두 불러오기
    @GetMapping("/survey/{userAccount}")
    public String getInfoByUserId(@PathVariable String user_account) throws JsonProcessingException{
        return String.valueOf(dtoService.getSurveysByUserAccount(user_account));
    }
}