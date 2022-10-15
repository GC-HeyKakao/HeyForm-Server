//package heykakao.HeyForm.service;
//
//import heykakao.HeyForm.model.*;
//import heykakao.HeyForm.model.dto.SurveyQuestionInfo;
//import heykakao.HeyForm.repository.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
////@Transactional
//class SurveyQuestionInfoServiceTest {
//
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
//    @Autowired
//    SurveyInfoService surveyInfoService;
//
//    @Test
//    void saveBySurveyInfo() {
//        SurveyQuestionInfo surveyQuestionInfo1 = new SurveyQuestionInfo();
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
//        question1.getChoices().add(choice1);
//        question2.getChoices().add(choice2);
//        question2.getChoices().add(choice3);
//        question2.getChoices().add(choice4);
//        question1.getAnswers().add(answer1);
//        question1.getAnswers().add(answer2);
//
//        surveyQuestionInfo1.setUser(user1);
//        surveyQuestionInfo1.setSurvey(survey1);
//        surveyQuestionInfo1.getQuestions().add(question1);
//        surveyQuestionInfo1.getQuestions().add(question2);
//
//
//        surveyInfoService.saveBySurveyInfo(surveyQuestionInfo1);
//
////        assertThat(i).isEqualTo(1);
//    }
//
//    @Test
//    void updateBySurveyInfo() {
//    }
//
//    @Test
//    void findByUserId() {
//    }
//}