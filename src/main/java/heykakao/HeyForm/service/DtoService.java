package heykakao.HeyForm.service;

import heykakao.HeyForm.model.Choice;
import heykakao.HeyForm.model.Question;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.User;
import heykakao.HeyForm.model.dto.ChoiceDto;
import heykakao.HeyForm.model.dto.QuestionDto;
import heykakao.HeyForm.model.dto.SurveyDto;
import heykakao.HeyForm.model.dto.SurveyQuestionDto;
import heykakao.HeyForm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoService {
    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public DtoService(UserRepository userRepository, SurveyRepository surveyRepository, QuestionRepository questionRepository,
                             ChoiceRepository choiceRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.answerRepository = answerRepository;
    }
    // Save
    public void saveSurvey(String user_account, SurveyQuestionDto surveyQuestionDto) {
        User user = userRepository.findByAccount(user_account).get();

        SurveyDto surveyDto = surveyQuestionDto.getSurveyDto();

        Survey survey = new Survey();
        survey.setByDto(surveyDto, user);
        surveyRepository.save(survey);

        List<QuestionDto> questionDtos = surveyQuestionDto.getQuestionDtos();

        for (QuestionDto questionDto : questionDtos) {
            Question question = new Question();
            question.setByDto(questionDto, survey);
            questionRepository.save(question);

            List<ChoiceDto> choiceDtos = questionDto.getChoiceDtos();

            for (ChoiceDto choiceDto : choiceDtos) {
                Choice choice = new Choice();
                choice.setByDto(choiceDto, question);
                choiceRepository.save(choice);
            }
        }
    }
    // Update
    public void updateSurveyInfo(Long survey_id, SurveyDto surveyDto) {
        Survey survey = surveyRepository.findById(survey_id).get();
        survey.setByDto(surveyDto);
        surveyRepository.save(survey);
    }

    public void updateQuestion(Long survey_id, QuestionDto questionDto) {
        Question question = questionRepository.findByOrderAndSurvey_Id(questionDto.getQuestion_order(), survey_id).get();
        question.setByDto(questionDto);
        questionRepository.save(question);
    }

    public void updateChoice(Long question_id, ChoiceDto choiceDto) {
        Choice choice = choiceRepository.findByOrderAndQuestion_Id(choiceDto.getChoice_order(), question_id).get();
        choice.setByDto(choiceDto);
        choiceRepository.save(choice);
    }

    public void updateAllChoices(Long question_id, List<ChoiceDto> choiceDtos) {
        List<Choice> choices = choiceRepository.findByQuestion_Id(question_id);
        for (Choice choice : choices) {
            ChoiceDto choiceDto = choiceDtos.stream().filter(ch_dto -> ch_dto.getChoice_order().equals(choice.getOrder())).collect(Collectors.toList()).get(0);
            choice.setByDto(choiceDto);
            choiceRepository.save(choice);
        }
    }

    public void updateAllQuestions(Long survey_id, List<QuestionDto> questionDtos) {
        List<Question> questions = questionRepository.findBySurvey_Id(survey_id);
        for (Question question : questions) {
            QuestionDto questionDto = questionDtos.stream().filter(qs_dto -> qs_dto.getQuestion_order().equals(question.getOrder())).collect(Collectors.toList()).get(0);
            question.setByDto(questionDto);
            questionRepository.save(question);
            updateAllChoices(question.getId(), questionDto.getChoiceDtos());
        }
    }

    public void updateSurvey(Long survey_id, SurveyQuestionDto surveyQuestionDto) {
        SurveyDto surveyDto = surveyQuestionDto.getSurveyDto();
        List<QuestionDto> questionDtos = surveyQuestionDto.getQuestionDtos();

        updateSurveyInfo(survey_id, surveyDto);
        updateAllQuestions(survey_id, questionDtos);
    }
    // Get
    public List<ChoiceDto> getChoiceDtos(Long question_id) {
        List<ChoiceDto> choiceDtos = new ArrayList<>();
        List<Choice> choices = choiceRepository.findByQuestion_Id(question_id);
        for(Choice choice : choices) {
            ChoiceDto choiceDto = new ChoiceDto(choice);
            choiceDtos.add(choiceDto);
        }
        return choiceDtos;
    }
    public List<QuestionDto> getQuestionDtos(Long survey_id) {
        List<Question> questions = questionRepository.findBySurvey_Id(survey_id);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions) {
            List<ChoiceDto> choiceDtos = getChoiceDtos(question.getId());

            QuestionDto questionDto = new QuestionDto(question, choiceDtos);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public SurveyQuestionDto getSurveyQuestionDto(Long survey_id) {
        Survey survey = surveyRepository.findById(survey_id).get();
        SurveyDto surveyDto = new SurveyDto(survey);

        List<QuestionDto> questionDtos = getQuestionDtos(survey_id);

        return new SurveyQuestionDto(surveyDto, questionDtos);
    }
}
