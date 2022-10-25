package heykakao.HeyForm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.*;
import heykakao.HeyForm.model.dto.*;
import heykakao.HeyForm.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
//    void DelTest() {
//        surveyService.delSurvey(1L);
//    }

    @Test
    @Transactional
    public void pushTestSample() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
//
        User user = userRepository.getReferenceById(1L);

//        Survey survey1 = new Survey(0, "www.test.com/sample", user, Timestamp.valueOf("2022-12-11 12:00:00"),Timestamp.valueOf("2022-12-12 13:00:00"), "choice", "testsurvey");
//        surveyRepository.save(survey1);
//
////        //        JWTService jwtService = new JWTService();
////
////        //tokenizer화
//////        var token = jwtService.createToken(jwtService.SECRET_KEY,user.getEmail());
//////        System.out.println(token);
//////        System.out.println(jwtService.getClaims(token, jwtService.SECRET_KEY));
//
//        Question question1 = new Question(2, 1, "qs sample1 bla bla", survey1);
//
//        Question question2 = new Question(1, 2, "qs sample2 bla bla", survey1);
//
//
//        questionRepository.save(question1);
//        questionRepository.save(question2);
//
//
//        Choice choice3 = new Choice(1, "ch_sample1 bla bla bla", question2);
//
//        Choice choice4 = new Choice(2, "ch_sample2 bla bla bla", question2);
//
//        Answer answer1 = new Answer(0, "answer1 bla bla", question1);
//
//        Answer answer2 = new Answer(0, "answer2 bla bla", question1);
//
//        choiceRepository.save(choice3);
//        choiceRepository.save(choice4);
//        answerRepository.save(answer1);
//        answerRepository.save(answer2);
//

//        List<AnswerDto> surveyAnswerDto = dtoService.getSurveyAnswerBySurveyId(1L,user.getToken());
//
//        String jsonInString = mapper.writeValueAsString(surveyAnswerDto);
        SurveyQuestionDto surveyQuestionDto = dtoService.getSurveyQuestionBySurveyId(1L);
        String jsonInString = mapper.writeValueAsString(surveyQuestionDto);
        System.out.println(jsonInString);
    }
}