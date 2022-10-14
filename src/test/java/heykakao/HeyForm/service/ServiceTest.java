package heykakao.HeyForm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Test
    void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyInfo surveyInfo1 = new SurveyInfo();
        SurveyInfo surveyInfo2 = new SurveyInfo();

        User user1 = new User();
        user1.setAccount("user1!");

        Survey survey1 = new Survey();
        survey1.setUser(user1);
        survey1.setUrl("www.heykakao.com");
        survey1.setState(0);

        Question question1 = new Question();
        question1.setSurvey(survey1);
        question1.setType(2);
        question1.setOrder(1);

        Question question2 = new Question();
        question2.setSurvey(survey1);
        question2.setType(1);
        question2.setOrder(2);

        Choice choice1 = new Choice();
        choice1.setOrder(0);
        choice1.setContents("qs1 bla bla bla");
        choice1.setQuestion(question1);

        Choice choice2 = new Choice();
        choice2.setOrder(0);
        choice2.setContents("qs2 bla bla bla");
        choice2.setQuestion(question2);

        Choice choice3 = new Choice();
        choice3.setOrder(1);
        choice3.setContents("ch1 bla bla bla");
        choice3.setQuestion(question2);

        Choice choice4 = new Choice();
        choice4.setOrder(2);
        choice4.setContents("ch2 bla bla bla");
        choice4.setQuestion(question2);

//        surveyInfo1.setUserId(user1.getId());
//        surveyInfo1.setSurveyId(survey1.getId());
//        surveyInfo1.setSurvey(survey1);

        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
//        surveyInfo1.setQuestions(questions);

        List<Choice> choices = new ArrayList<>();
        choices.add(choice1);
//        choices.add(choice2);
//        choices.add(choice3);
//        choices.add(choice4);
        surveyInfo1.setChoices(choices);

        String surveyJson = objectMapper.writeValueAsString(surveyInfo1);
        System.out.println(surveyJson);
    }

}
