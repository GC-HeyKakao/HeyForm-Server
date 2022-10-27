package heykakao.HeyForm.service;

import heykakao.HeyForm.model.*;
import heykakao.HeyForm.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@Transactional
class DtoServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    DtoService dtoService;

    @Autowired
    SurveyService surveyService;
//    @Test
//    public void DelTest() {
////        surveyService.delSurvey(1L);
//
//        Optional<User> user = userRepository.findByEmail("Test");
//        System.out.println(user.get().getToken());
//
//    }

    //AIService 테스트
//    @Test
//    public void AIService() throws Exception{
//        ObjectMapper mapper = new ObjectMapper();
//        AIService aiService = new AIService();
//        String[] tmp ={"환경","스포츠","정치","학교"};
//        System.out.println("RESULT : "+aiService.Category_recommend("환경을 보호해야 된다고 생각하나요?", tmp));
//    }

    //DtoService saveUser() 테스트
//    @Test
//    @Transactional
//    public void DtoService_saveUser() throws Exception{
//        User user = new User("Test1","Test2","M",1L,"Test");
//        dtoService.saveUser(user);
//    }

    //DtoService saveSurvey() 테스트
//    @Test
//    @Transactional
//    public void DtoService_saveSurvey() throws Exception {
//        User user = userRepository.getReferenceById(1L);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String testJson = "{\"surveyDto\":{\"survey_state\":0,\"start_time\":\"2022-12-11 12:00\",\"end_time\":\"2022-12-11 13:00\",\"category\":null,\"description\":null},\"questionDtos\":[{\"question_type\":1,\"question_order\":2,\"question_contents\":\"qs sample2 bla bla\",\"choiceDtos\":[{\"choice_order\":1,\"choice_contents\":\"ch_sample1 bla bla bla\"}]}]}";
//        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(testJson, SurveyQuestionDto.class);
//        dtoService.saveSurvey(user.getToken(),surveyQuestionDto);
//    }

    //DtoService saveAnswer() 테스트
//    @Test
//    @Transactional
//    public void DtoService_saveAnswer() throws Exception{
//        String testJson = "{\"user_token\":\"Token\",\"survey_id\": 1,\"answerDtos\":[{\"question_order\":1,\"answer_contents\":\"testansewr1\"}]}";
//        ObjectMapper objectMapper = new ObjectMapper();
//        SurveyAnswerDto surveyAnswerDto = objectMapper.readValue(testJson, SurveyAnswerDto.class);
//        Long survey_id = surveyAnswerDto.getSurvey_id();
//        dtoService.saveAnswer(survey_id, surveyAnswerDto);
//    }

    //DtoService deleteSurvey() 테스트
//    @Test
//    @Transactional
//    public void DtoService_deleteSurvey() throws Exception{
//        Long test_id = 1L;
//        surveyService.delSurvey(test_id);
//    }

    //JWTService 테스트
//    @Test
//    @Transactional
//    public void JWTService(){
//        JWTService jwtService = new JWTService();
//        String test_email = "Test@Test.com";
//        var token = jwtService.createToken(jwtService.SECRET_KEY,test_email);
//        System.out.println(token);
//        System.out.println(jwtService.getClaims(token, jwtService.SECRET_KEY));
//    }
}