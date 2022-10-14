package heykakao.HeyForm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.*;
import heykakao.HeyForm.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@Transactional
public class ServiceTest {
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

//        userRepository.save(user1);
//        surveyRepository.save(survey1);
//        questionRepository.save(question1);
//        questionRepository.save(question2);

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


        Answer answer1 = new Answer();
        answer1.setOrder(0);
        answer1.setContents("answer1 bla bla");
        answer1.setQuestion(question1);


        Answer answer2 = new Answer();
        answer2.setOrder(0);
        answer2.setContents("answer2 bla bla");
        answer2.setQuestion(question1);

        question1.getChoices().add(choice1);
        question2.getChoices().add(choice2);
        question2.getChoices().add(choice3);
        question2.getChoices().add(choice4);
        question1.getAnswers().add(answer1);
        question1.getAnswers().add(answer2);


//        choiceRepository.save(choice1);
//        choiceRepository.save(choice2);
//        choiceRepository.save(choice3);
//        choiceRepository.save(choice4);
//        answerRepository.save(answer1);
//        answerRepository.save(answer2);
//
//        System.out.println("before size1: " + surveyRepository.findAll().size());
//        System.out.println("before size: " + choiceRepository.findAll().size());
//
//        User user11 = userRepository.findById(user1.getId()).get();
//        Survey survey11 = surveyRepository.findById(survey1.getId()).get();
//        Question question11 = questionRepository.findById(question1.getId()).get();
//        Question question22 = questionRepository.findById(question2.getId()).get();
//        List<Choice> choices11 = choiceRepository.findByQuestion_Id(question11.getId());
//        List<Choice> choices22 = choiceRepository.findByQuestion_Id(question22.getId());
//        question11.getChoices().addAll(choices11);
//        question22.getChoices().addAll(choices22);

        surveyInfo1.setUser(user1);
        surveyInfo1.setSurvey(survey1);
        surveyInfo1.getQuestions().add(question1);
        surveyInfo1.getQuestions().add(question2);

        String surveyJson = objectMapper.writeValueAsString(surveyInfo1);
        System.out.println(surveyJson);

        SurveyInfo surveyInfo11 = objectMapper.readValue(surveyJson, SurveyInfo.class);


        List<Question> qss = surveyInfo11.getQuestions();
        Iterator<Question> qsIter = qss.iterator();

        surveyInfo11.getSurvey().setUser(surveyInfo11.getUser());

        User usr = surveyInfo11.getUser();
        usr.setId(null);

        Survey srv = surveyInfo11.getSurvey();
        srv.setId(null);
        srv.setUser(usr);

        while (qsIter.hasNext()) {
            Question qs = qsIter.next();
            qs.setId(null);

            qs.setSurvey(srv);
            Iterator<Choice> choiceIterator = qs.getChoices().iterator();

            while (choiceIterator.hasNext()) {
                Choice ch = choiceIterator.next();
                ch.setQuestion(qs);
                ch.setId(null);
            }
        }

//        for (Question question : qss) {
//            System.out.println(question);
//        }

        userRepository.save(usr);
        surveyRepository.save(srv);
        questionRepository.saveAll(qss);

//        for (Question question : questionRepository.findAll()) {
//            System.out.println(question);
//        }

        System.out.println("after size user: " + userRepository.findAll().size());
        System.out.println("after size survey: " + surveyRepository.findAll().size());
        System.out.println("after size qs: " + questionRepository.findAll().size());
        System.out.println("after size choice: " + choiceRepository.findAll().size());

//        String surveyJson = objectMapper.writeValueAsString(surveyInfo1);
    }

}

/*
문제점 1: 양방향 구조로 짰는데 그래서 answer 하고 choice 중 하나만 필요할 떄 한쪽을 null 로 바꿔서 보내보려고함
문제점 2: 양방향 구조를 db에 넣어 줄 때
 */
