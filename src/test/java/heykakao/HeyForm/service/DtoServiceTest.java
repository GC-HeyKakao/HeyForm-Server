//package heykakao.HeyForm.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import heykakao.HeyForm.model.*;
//import heykakao.HeyForm.model.dto.ChoiceDto;
//import heykakao.HeyForm.model.dto.QuestionDto;
//import heykakao.HeyForm.model.dto.SurveyDto;
//import heykakao.HeyForm.model.dto.SurveyQuestionDto;
//import heykakao.HeyForm.repository.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class DtoServiceTest {
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    SurveyRepository surveyRepository;
//    @Autowired
//    QuestionRepository questionRepository;
//    @Autowired
//    ChoiceRepository choiceRepository;
//    @Autowired
//    AnswerRepository answerRepository;
//
//    @Autowired
//    DtoService dtoService;
//
//    @Autowired
//    SurveyService surveyService;
//
////    @Test
////    void DelTest() {
////        surveyService.delSurvey(1L);
////    }
//
//    @Test
//    public void pushTestSample() {
//
//        User user = userRepository.getReferenceById(1L);
//
//        Survey survey1 = new Survey(0, "www.heykakao.com/sample", user);
//
//        Question question1 = new Question(2, 1, "qs sample1 bla bla", survey1);
//
//        Question question2 = new Question(1, 2, "qs sample2 bla bla", survey1);
//
//        userRepository.save(user);
//        surveyRepository.save(survey1);
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
//    }
//}