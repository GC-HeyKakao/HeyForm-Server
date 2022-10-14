package heykakao.HeyForm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.Question;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.SurveyInfo;
import heykakao.HeyForm.repository.ChoiceRepository;
import heykakao.HeyForm.repository.QuestionRepository;
import heykakao.HeyForm.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SurveyInfoService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @Autowired
    public SurveyInfoService(final SurveyRepository surveyRepository,
                             QuestionRepository questionRepository,
                             ChoiceRepository choiceRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }

    public void getSurveyInfo(Long surveyId) {
        SurveyInfo surveyInfo = new SurveyInfo();
//        surveyInfo.setSurveyId(surveyId);

        Survey survey = surveyRepository.findById(surveyId).get();
//        surveyInfo.setSurvey(survey);

        List<Question> questionList = questionRepository.findBySurvey_Id(survey.getId());

    }

    public void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyInfo surveyInfo = new SurveyInfo();

        String surveyJson = objectMapper.writeValueAsString(surveyInfo);
        System.out.println(surveyJson);
    }
}