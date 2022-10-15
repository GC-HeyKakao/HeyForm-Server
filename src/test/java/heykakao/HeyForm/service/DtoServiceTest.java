package heykakao.HeyForm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.*;
import heykakao.HeyForm.model.dto.ChoiceDto;
import heykakao.HeyForm.model.dto.QuestionDto;
import heykakao.HeyForm.model.dto.SurveyDto;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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

    @Test
    void getTest() throws JsonProcessingException {
        SurveyQuestionDto surveyQuestionDto = dtoService.getSurveyQuestionDto(1L);
        ObjectMapper objectMapper = new ObjectMapper();

        String surveyJson = objectMapper.writeValueAsString(surveyQuestionDto);
        System.out.println(surveyJson);

    }

    @Test
    void UpdateTest() throws JsonProcessingException {
        String surveyJson = "{\"surveyDto\":{\"survey_state\":0,\"survey_url\":\"www.heykakao.com\"},\"questionDtos\":[{\"question_type\":2,\"question_order\":1,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs1 bla bla bla\"}]},{\"question_type\":1,\"question_order\":2,\"choiceDtos\":[{\"choice_order\":0,\"choice_contents\":\"qs2 bla bla bla\"},{\"choice_order\":1,\"choice_contents\":\"ch1 bla bla bla\"},{\"choice_order\":2,\"choice_contents\":\"ch2 bla bla bla\"}]}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyQuestionDto surveyQuestionDto = objectMapper.readValue(surveyJson, SurveyQuestionDto.class);

        dtoService.updateSurvey(1L, surveyQuestionDto);
    }
    @Test
    void DelTest() {
//        dtoService.
    }

    public void pushTestSample() {
        User user = new User();
        user.setAccount("user_sample!");

        Survey survey1 = new Survey();
        survey1.setUser(user);
        survey1.setUrl("www.heykakao.com/sample");
        survey1.setState(0);

        Question question1 = new Question();
        question1.setSurvey(survey1);
        question1.setType(2);
        question1.setOrder(1);
        question1.setContents("qs sample1 bla bla");

        Question question2 = new Question();
        question2.setSurvey(survey1);
        question2.setType(1);
        question2.setOrder(2);
        question1.setContents("qs sample2 bla bla");

        userRepository.save(user);
        surveyRepository.save(survey1);
        questionRepository.save(question1);
        questionRepository.save(question2);


        Choice choice3 = new Choice();
        choice3.setOrder(1);
        choice3.setContents("ch_sample1 bla bla bla");
        choice3.setQuestion(question2);


        Choice choice4 = new Choice();
        choice4.setOrder(2);
        choice4.setContents("ch_sample2 bla bla bla");
        choice4.setQuestion(question2);

        Answer answer1 = new Answer();
        answer1.setOrder(0);
        answer1.setContents("answer1 bla bla");
        answer1.setQuestion(question1);


        Answer answer2 = new Answer();
        answer2.setOrder(0);
        answer2.setContents("answer2 bla bla");
        answer2.setQuestion(question1);

        choiceRepository.save(choice3);
        choiceRepository.save(choice4);
        answerRepository.save(answer1);
        answerRepository.save(answer2);
    }
}