package heykakao.HeyForm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.User;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.repository.*;
import heykakao.HeyForm.service.DtoService;
import heykakao.HeyForm.service.SurveyService;
import io.swagger.annotations.ApiOperation;
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
    private final UserRepository userRepository;
    private final DtoService dtoService;
    private final SurveyService surveyService;

    @Autowired
    public SurveyController(UserRepository userRepository, DtoService dtoService, SurveyService surveyService){
        this.userRepository = userRepository;
        this.dtoService = dtoService;
        this.surveyService = surveyService;
    }


    //Surveyjson type 리턴값 : 설문 url
    //"{\"surveyDto\":{\"survey_state\":0,\"survey_url\":\"www.heykakao.com\"},\"questionDtos\":[{\"question_type\":2,\"question_order\":1,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs1 bla bla bla\"}]},{\"question_type\":1,\"question_order\":2,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs2 bla bla bla\"},{\"choice_order\":1,\"choice_contents\":\"ch1 bla bla bla\"},{\"choice_order\":2,\"choice_contents\":\"ch2 bla bla bla\"}]}]}"
    @PostMapping("/survey/{userToken}")
    @ApiOperation(value = "설문조사 생성", notes = "사용자 token을 사용해서 설문조사를 생성한다. 생성된 설문조사의 페이지 Url이 리턴된다. (설문조사 제작)")
    public String createSurvey(@RequestParam String surveyJson, @RequestParam String userToken) throws JsonProcessingException, NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        Long survey_id = dtoService.saveSurvey(userToken,surveyQuestionDto);
        return surveyService.getUrl(survey_id);
    }
    //설문 정보를 url을 통해 전달한다.
    @GetMapping("/survey/paper/{surveyUrl}")
    @ApiOperation(value = "설문조사 페이지 생성", notes = "url을 이용해서 설문조사 정보를 불러온다. (설문조사 url)")
    public SurveyQuestionDto createPaperByURL(@PathVariable String surveyUrl){
        return dtoService.getSurveyQuestionByUrl(surveyUrl);
    }

    @DeleteMapping("/survey/{surveyId}")
    @ApiOperation(value = "설문조사 제거", notes = "설문조사 id를 이용해서 설문조사를 제거한다. (마이페이지 / 설문조사 제거)")
    public void deleteSurvey(@RequestParam Long surveyId){
        surveyService.delSurvey(surveyId);
    }

    //surveyId를 통해 업데이트..
    @PostMapping("/survey/update")
    @ApiOperation(value = "설문조사 업데이트", notes = "설문조사를 업데이트한다. (마이페이지 / 설문조사 업데이트)")
    public void updateSurvey(@RequestParam String surveyJson) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);
        dtoService.updateSurvey(surveyQuestionDto);
    }

    //surveyId를 통해 설문지 정보 불러오기
    @GetMapping("/survey/list/{surveyId}")
    @ApiOperation(value = "설문조사 정보 가져오기" , notes = "설문조사 id를 이용해서 설문조사 정보를 불러온다. (마이페이지 / 설문조사 정보보기)")
    public String getSurveyInfoBySurveyId(@PathVariable Long surveyId) throws JsonProcessingException{
        SurveyQuestionDto surveyQuestionDto = dtoService.getSurveyQuestionBySurveyId(surveyId);
        ObjectMapper objectMapper = new ObjectMapper();
        String surveyJson = objectMapper.writeValueAsString(surveyQuestionDto);
        return surveyJson;
    }

    // userId를 통해 해당 유저의  survey, question, answer 정보 모두 불러오기
    @GetMapping("/survey/total/{userToken}")
    @ApiOperation(value = "사용자 정보 가져오기", notes = "사용자 token을 사용해서 사용자가 생성한 설문조사 리스트 가져오기. (마이페이지)")
    public String getInfoByUserAccount(@PathVariable String userToken){
        return String.valueOf(dtoService.getSurveysByUserToken(userToken));
    }
    // 테스트용
    @GetMapping("/survey")
    @ApiOperation(value = "Test 용")
    public String getAllSurvey(){
        List<User> users = userRepository.findAll();
        List<List<SurveyQuestionDto>> allInfo = new ArrayList<>();
        for (User user : users) {
            List<SurveyQuestionDto> surveyQuestionDtos =  dtoService.getSurveysByUserToken(user.getToken());
            allInfo.add(surveyQuestionDtos);
        }

        return String.valueOf(allInfo);
    }
}