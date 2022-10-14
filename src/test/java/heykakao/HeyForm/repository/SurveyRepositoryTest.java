//package heykakao.HeyForm.repository;
//
//import heykakao.HeyForm.model.Survey;
//import heykakao.HeyForm.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Iterator;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
////@Transactional
//class SurveyRepositoryTest {
//
//    @Autowired UserRepository userRepository;
//    @Autowired SurveyRepository surveyRepository;
//
//    @Test
//    void addSurvey() {
//        User user1 = new User();
//        user1.setId("usr1");
//        userRepository.save(user1);
//
//        User user2 = new User();
//        user2.setId("usr2");
//        userRepository.save(user2);
//
//        Survey survey = new Survey();
//        survey.setState(3);
//        survey.setUser(user1);
//        surveyRepository.save(survey);
//
//        Survey survey2 = new Survey();
//        survey2.setState(4);
//        survey2.setUser(user1);
//        surveyRepository.save(survey2);
//
//        Survey survey3 = new Survey();
//        survey3.setState(2);
//        survey3.setUser(user2);
//        surveyRepository.save(survey3);
//
//        Iterator<Survey> surveyIterator =  surveyRepository.findAll().iterator();
//        int i = 0;
//        while(surveyIterator.hasNext()) {
//            System.out.println(i + " : " + surveyIterator.next());
//            i++;
//        }
//
//        assertThat(i).isEqualTo(3);
//    }
//}