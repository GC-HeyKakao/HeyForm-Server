package heykakao.HeyForm.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;

import java.util.List;

@Data
public class SurveyInfo {
//    private Long surveyId;
//    private Long userId;
//    private Survey survey;
//    private List<Question> questions;
    private List<Choice> choices;
}

/*
{"surveyId": xx, "userId": xx, "survey": { "state": xx, "URL": xx, "userKey":
 */