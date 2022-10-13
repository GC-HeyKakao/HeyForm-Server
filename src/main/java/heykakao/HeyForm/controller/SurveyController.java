package heykakao.HeyForm.controller;

import heykakao.HeyForm.exception.ResourceNotFoundException;
import heykakao.HeyForm.model.Question;
import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.repository.AnswerRepository;
import heykakao.HeyForm.repository.QuestionRepository;
import heykakao.HeyForm.repository.SurveyRepository;
import heykakao.HeyForm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@RequestMapping("/api/v1/")
@ResponseBody
@RequiredArgsConstructor
public class SurveyController {

    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;
    public ArrayList<Integer> surveynum;

    @GetMapping("/")
    public String Survey(){
        return "survey";
    }

    @GetMapping("/survey")
    public List<Survey> getAllSurvey(){
        return surveyRepository.findAll();
    }
    @GetMapping("/question")
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }
    @PostMapping("/survey")
    public Survey createSurvey(@RequestBody Survey survey){
        return surveyRepository.save(survey);
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id){
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Survey with id :" + id));
        return ResponseEntity.ok(survey);
    }

    // 맞는 surveynum 담기
//    @GetMapping("/{userkey}/total")
//    public String getTotal(@PathVariable Long UserId){
//        List<Survey> surveys = surveyRepository.findByKey(UserId);
//        surveynum = new ArrayList<>();
//        for(Survey survey : surveys){
//            if(!surveynum.contains(survey.getId()))
//                surveynum.add(survey.getId());
//        }
//        String tmp = "";
//        for(Integer num : surveynum){
//            List<Question> questions =  questionRepository.findById(num);
//            tmp += questions.;
//        }
//        return tmp;
//    }
//
//    //userkey 로 find
//    @GetMapping("/{userkey}/survey")
//    public List<Survey> getSurveyByUserkey(@PathVariable int userkey){
//        return surveyRepository.findByUserkey(userkey);
//    }
//
//    @PutMapping("/survey/{id}")
//    public ResponseEntity<Survey> updateSurvey(@PathVariable int id, @RequestBody Survey surveyDetails){
//        Survey survey = surveyRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Survey not exist with id" + id));
//        survey.setSurveyNum(surveyDetails.getSurveyNum());
//        survey.setUserKey((surveyDetails.getUserKey()));
//
//        Survey updatedSurvey = surveyRepository.save(survey);
//        return ResponseEntity.ok(updatedSurvey);
//    }
//
//    @DeleteMapping("/survey/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteSurvey(@PathVariable int id){
//        Survey survey = surveyRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("survey not exist with id "+ id));
//        surveyRepository.delete(survey);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

}
