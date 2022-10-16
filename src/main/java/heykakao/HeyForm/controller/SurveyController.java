package heykakao.HeyForm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.User;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.repository.*;
import heykakao.HeyForm.service.DtoService;
import heykakao.HeyForm.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@RequestMapping("/api/v1/")
@ResponseBody
public class SurveyController {
    @Autowired
    UserRepository userRepository;
    private final DtoService dtoService;
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(DtoService dtoService, SurveyService surveyService){
        this.dtoService = dtoService;
        this.surveyService = surveyService;
    }


    //Surveyjson type 리턴값 : 설문 url
    //"{\"surveyDto\":{\"survey_state\":0,\"survey_url\":\"www.heykakao.com\"},\"questionDtos\":[{\"question_type\":2,\"question_order\":1,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs1 bla bla bla\"}]},{\"question_type\":1,\"question_order\":2,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs2 bla bla bla\"},{\"choice_order\":1,\"choice_contents\":\"ch1 bla bla bla\"},{\"choice_order\":2,\"choice_contents\":\"ch2 bla bla bla\"}]}]}"
    @PostMapping("/survey/{userAccount}")
    public String createSurvey(@RequestParam String surveyJson, @RequestParam String userAccount) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        Long survey_id = dtoService.saveSurvey(userAccount,surveyQuestionDto);
        return surveyService.getUrl(survey_id);
    }
    //설문 정보를 url을 통해 전달한다.
    @GetMapping("/survey/paper/{surveyUrl}")
    public SurveyQuestionDto createPaperByURL(@PathVariable String surveyUrl){
        return dtoService.getSurveyQuestionByUrl(surveyUrl);
    }

    @DeleteMapping("/survey/{surveyId}")
    public void deleteSurvey(@RequestParam Long surveyId){
        surveyService.delSurvey(surveyId);
    }

    //surveyId를 통해 업데이트..
    @PostMapping("/survey/update")
    public void updateSurvey(@RequestParam String surveyJson) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        dtoService.updateSurvey(surveyQuestionDto);
    }

    //surveyId를 통해 설문지 정보 불러오기
    @GetMapping("/survey/list/{surveyId}")
    public String getSurveyInfoBySurveyId(@PathVariable Long surveyId) throws JsonProcessingException{
        SurveyQuestionDto surveyQuestionDto = dtoService.getSurveyQuestionBySurveyId(surveyId);
        ObjectMapper objectMapper = new ObjectMapper();
        String surveyJson = objectMapper.writeValueAsString(surveyQuestionDto);
        return surveyJson;
    }

    // userId를 통해 해당 유저의  survey, question, answer 정보 모두 불러오기
    @GetMapping("/survey/total/{userAccount}")
    public String getInfoByUserAccount(@PathVariable String userAccount){
        return String.valueOf(dtoService.getSurveysByUserAccount(userAccount));
    }
    // 테스트용
    @GetMapping("/survey")
    public String getAllSurvey(){
        List<User> users = userRepository.findAll();
        List<List<SurveyQuestionDto>> allInfo = new ArrayList<>();
        for (User user : users) {
            List<SurveyQuestionDto> surveyQuestionDtos =  dtoService.getSurveysByUserAccount(user.getAccount());
            allInfo.add(surveyQuestionDtos);
        }

        return String.valueOf(allInfo);
    }
}