package heykakao.HeyForm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import heykakao.HeyForm.model.*;
import heykakao.HeyForm.model.dto.*;
import heykakao.HeyForm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public Long saveSurvey(String user_account, SurveyQuestionDto surveyQuestionDto) throws NoSuchAlgorithmException {
        User user = userRepository.findByAccount(user_account).get();

        SurveyDto surveyDto = surveyQuestionDto.getSurveyDto();

        Survey survey = new Survey();
        survey.setByDto(surveyDto, user);

        String url = makeUrl(survey.getId());
        survey.setUrl(url);
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

        return survey.getId();
    }

    private String makeUrl(Long survey_id) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(String.valueOf(survey_id).getBytes());

        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }

        return hexText;
    }

    public Long saveUser(UserDto userDto){
        User user = new User();
        user.setByDto(userDto);
        userRepository.save(user);
        return user.getId();
    }

    public void saveAnswer(Long survey_id, SurveyAnswerDto surveyAnswerDto) {
        User user = userRepository.findByAccount(surveyAnswerDto.getUser_account()).get();
        List<AnswerDto> answerDtos = surveyAnswerDto.getAnswerDtos();

        for (AnswerDto answerDto : answerDtos) {
            Integer question_order = answerDto.getQuestion_order();
            Question question = questionRepository.findByOrderAndSurvey_Id(question_order, survey_id).get();
            Answer answer = new Answer();
            answer.setByDto(answerDto, user, question);
            answerRepository.save(answer);
        }
    }

    // Update
    public void updateSurveyInfo(SurveyDto surveyDto) {
        Survey survey = surveyRepository.findById(surveyDto.getSurvey_id()).get();
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

    public void updateSurvey(SurveyQuestionDto surveyQuestionDto) {
        SurveyDto surveyDto = surveyQuestionDto.getSurveyDto();
        List<QuestionDto> questionDtos = surveyQuestionDto.getQuestionDtos();

        updateSurveyInfo(surveyDto);
        updateAllQuestions(surveyDto.getSurvey_id(), questionDtos);
    }

    public void updateAnswer(Long survey_id, SurveyAnswerDto surveyAnswerDto) {
        User user = userRepository.findByAccount(surveyAnswerDto.getUser_account()).get();

        List<AnswerDto> answerDtos = surveyAnswerDto.getAnswerDtos();

        for (AnswerDto answerDto : answerDtos) {
            Integer question_order = answerDto.getQuestion_order();
            Question question = questionRepository.findByOrderAndSurvey_Id(question_order, survey_id).get();
            Answer answer =  answerRepository.findByUser_IdAndQuestion_Id(user.getId(), question.getId()).get();
            answer.setByDto(answerDto);
            answerRepository.save(answer);
        }
    }
    // Get
    public List<SurveyQuestionDto> getSurveysByUserAccount(String user_account) {
        Long user_id = userRepository.findByAccount(user_account).get().getId();
        return getSurveyQuestionDtos(user_id);
    }

    public SurveyQuestionDto getSurveyQuestionBySurveyId(Long survey_id) {
        return getSurveyQuestionDto(survey_id);
    }

    public SurveyQuestionDto getSurveyQuestionByUrl(String survey_url) {
        Survey survey = surveyRepository.findByUrl(survey_url).get();
        return survey2surveyQuestionDto(survey);
    }

    public List<AnswerDto> getAnswersBySurveyId(Long survey_id) {
        return getSurveyAnswerDto(survey_id);
    }

    public void getSurveyAnswerBySurveyId(Long survey_id, Long user_account) {
        //구현 예정
    }

    private SurveyQuestionDto survey2surveyQuestionDto(Survey survey) {
        SurveyDto surveyDto = new SurveyDto(survey);
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions = questionRepository.findBySurvey_Id(survey.getId());

        for(Question question : questions) {
            questionDtos.add(question2questionDto(question));
        }

        return new SurveyQuestionDto(surveyDto, questionDtos);
    }

    private QuestionDto question2questionDto(Question question) {
        List<Choice> choices = choiceRepository.findByQuestion_Id(question.getId());
        List<ChoiceDto> choiceDtos = new ArrayList<>();
        for(Choice choice : choices) {
            ChoiceDto choiceDto = new ChoiceDto(choice);
            choiceDtos.add(choiceDto);
        }

        return new QuestionDto(question, choiceDtos);
    }

    private List<ChoiceDto> getChoiceDtos(Long question_id) {
        List<ChoiceDto> choiceDtos = new ArrayList<>();
        List<Choice> choices = choiceRepository.findByQuestion_Id(question_id);
        for(Choice choice : choices) {
            ChoiceDto choiceDto = new ChoiceDto(choice);
            choiceDtos.add(choiceDto);
        }
        return choiceDtos;
    }
    private List<QuestionDto> getQuestionDtos(Long survey_id) {
        List<Question> questions = questionRepository.findBySurvey_Id(survey_id);
        List<QuestionDto> questionDtos = new ArrayList<>();
        for(Question question : questions) {
            List<ChoiceDto> choiceDtos = getChoiceDtos(question.getId());

            QuestionDto questionDto = new QuestionDto(question, choiceDtos);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    private SurveyQuestionDto getSurveyQuestionDto(Long survey_id) {
        Survey survey = surveyRepository.findById(survey_id).get();
        SurveyDto surveyDto = new SurveyDto(survey);

        List<QuestionDto> questionDtos = getQuestionDtos(survey_id);

        return new SurveyQuestionDto(surveyDto, questionDtos);
    }

    private List<SurveyQuestionDto> getSurveyQuestionDtos(Long user_id) {
        List<SurveyQuestionDto> surveyQuestionDtos = new ArrayList<>();
        List<Long> survey_ids = surveyRepository.findByUser_Id(user_id).stream().map(Survey::getId).collect(Collectors.toList());
        for(Long survey_id : survey_ids){
            surveyQuestionDtos.add(getSurveyQuestionDto(survey_id));
        }
        return surveyQuestionDtos;
    }

    private List<AnswerDto> getSurveyAnswerDto(Long survey_id) {
        List<AnswerDto> answerDtos = new ArrayList<>();

        List<Question> questions = questionRepository.findBySurvey_Id(survey_id);

        for(Question question : questions) {
            List<Long> question_ids = answerRepository.findByQuestion_Id(question.getId()).stream().map(Answer::getId).collect(Collectors.toList());

            for(Long question_id : question_ids) {
                List<Answer> answers = answerRepository.findByQuestion_Id(question_id);

                for(Answer answer : answers) {
                    AnswerDto answerDto = new AnswerDto(answer);
                    answerDtos.add(answerDto);
                }
            }
        }
        return answerDtos;
    }
}
