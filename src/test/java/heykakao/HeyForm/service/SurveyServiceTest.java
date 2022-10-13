package heykakao.HeyForm.service;

import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.repository.SurveyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SurveyServiceTest {

    @Autowired SurveyRepository surveyRepository;
    @Autowired SurveyService surveyService;

    @Test
    void addSurvey() {
        Survey survey = new Survey();
        survey.setState(3);
        surveyService.addSurvey(survey);

        Iterator<Survey> surveyIterator =  surveyService.findAllSurveys().iterator();
        int i = 0;
        while(surveyIterator.hasNext()) {
            System.out.println(i + " : " + surveyIterator.next());
            i++;
        }

        assertThat(i).isEqualTo(1);
    }

    @Test
    void findSurveys() {

    }
}