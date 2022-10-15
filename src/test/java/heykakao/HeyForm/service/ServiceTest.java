//package heykakao.HeyForm.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import heykakao.HeyForm.model.*;
//import heykakao.HeyForm.model.dto.SurveyQuestionInfo;
//import heykakao.HeyForm.repository.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class ServiceTest {
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
//    @Test
//    void test() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SurveyQuestionInfo surveyQuestionInfo1 = new SurveyQuestionInfo();
//        SurveyQuestionInfo surveyQuestionInfo2 = new SurveyQuestionInfo();
//
//        User user1 = new User();
//        user1.setAccount("user1!");
//
//        Survey survey1 = new Survey();
//        survey1.setUser(user1);
//        survey1.setUrl("www.heykakao.com");
//        survey1.setState(0);
//
//        Question question1 = new Question();
//        question1.setSurvey(survey1);
//        question1.setType(2);
//        question1.setOrder(1);
//
//        Question question2 = new Question();
//        question2.setSurvey(survey1);
//        question2.setType(1);
//        question2.setOrder(2);
//
//        userRepository.save(user1);
//        surveyRepository.save(survey1);
//        questionRepository.save(question1);
//        questionRepository.save(question2);
//
//        Choice choice1 = new Choice();
//        choice1.setOrder(0);
//        choice1.setContents("qs1 bla bla bla");
//        choice1.setQuestion(question1);
//
//        Choice choice2 = new Choice();
//        choice2.setOrder(0);
//        choice2.setContents("qs2 bla bla bla");
//        choice2.setQuestion(question2);
//
//        Choice choice3 = new Choice();
//        choice3.setOrder(1);
//        choice3.setContents("ch1 bla bla bla");
//        choice3.setQuestion(question2);
//
//
//        Choice choice4 = new Choice();
//        choice4.setOrder(2);
//        choice4.setContents("ch2 bla bla bla");
//        choice4.setQuestion(question2);
//
//
//        Answer answer1 = new Answer();
//        answer1.setOrder(0);
//        answer1.setContents("answer1 bla bla");
//        answer1.setQuestion(question1);
//
//
//        Answer answer2 = new Answer();
//        answer2.setOrder(0);
//        answer2.setContents("answer2 bla bla");
//        answer2.setQuestion(question1);
//
////        question1.getChoices().add(choice1);
////        question2.getChoices().add(choice2);
////        question2.getChoices().add(choice3);
////        question2.getChoices().add(choice4);
////        question1.getAnswers().add(answer1);
////        question1.getAnswers().add(answer2);
//
//
//        choiceRepository.save(choice1);
//        choiceRepository.save(choice2);
//        choiceRepository.save(choice3);
//        choiceRepository.save(choice4);
//        answerRepository.save(answer1);
//        answerRepository.save(answer2);
//
//        User user11 = userRepository.findById(user1.getId()).get();
//        Survey survey11 = surveyRepository.findById(survey1.getId()).get();
//        Question question11 = questionRepository.findById(question1.getId()).get();
//        Question question22 = questionRepository.findById(question2.getId()).get();
//        List<Choice> choices11 = choiceRepository.findByQuestion_Id(question11.getId());
//        List<Choice> choices22 = choiceRepository.findByQuestion_Id(question22.getId());
//        question11.getChoices().addAll(choices11);
//        question22.getChoices().addAll(choices22);
//
//        surveyQuestionInfo1.setUser(user11);
//        surveyQuestionInfo1.setSurvey(survey11);
//        surveyQuestionInfo1.getQuestions().add(question11);
//        surveyQuestionInfo1.getQuestions().add(question22);
//
//        String surveyJson = objectMapper.writeValueAsString(surveyQuestionInfo1);
//        System.out.println(surveyJson);
//
//        SurveyQuestionInfo surveyQuestionInfo11 = objectMapper.readValue(surveyJson, SurveyQuestionInfo.class);
//        //////////////////////////////////////////////////////////////////////
//        User usr = surveyQuestionInfo11.getUser();
//        usr.setId(null);
//        userRepository.save(usr);
//
//        Survey srv = surveyQuestionInfo11.getSurvey();
//        srv.setId(null);
//        srv.setUser(usr);
//        surveyRepository.save(srv);
//
//        List<Question> qss = surveyQuestionInfo11.getQuestions();
//
//        for (Question qs : qss) {
//            qs.setId(null);
//
//            qs.setSurvey(srv);
//            questionRepository.save(qs);
//
//            List<Choice> choices = qs.getChoices();
//
//            for (Choice ch : choices) {
//                ch.setQuestion(qs);
//                ch.setId(null);
//            }
//            choiceRepository.saveAll(choices);
//
//            List<Answer> answers = qs.getAnswers();
//
//            for (Answer asr : answers) {
//                asr.setQuestion(qs);
//                asr.setId(null);
//            }
//            answerRepository.saveAll(answers);
//        }
//        ///////////////////////////////////////////////////////////////////
//
//        System.out.println("after size user: " + userRepository.findAll().size());
//        System.out.println("after size survey: " + surveyRepository.findAll().size());
//        System.out.println("after size qs: " + questionRepository.findAll().size());
//        System.out.println("after size choice: " + choiceRepository.findAll().size());
//
////        String surveyJson = objectMapper.writeValueAsString(surveyInfo1);
//    }
//
//}
//
///*
//문제점 1: 양방향 구조로 짰는데 그래서 answer 하고 choice 중 하나만 필요할 떄 한쪽을 null 로 바꿔서 보내보려고함
//문제점 2: 양방향 구조를 db에 넣어 줄 때
// */
