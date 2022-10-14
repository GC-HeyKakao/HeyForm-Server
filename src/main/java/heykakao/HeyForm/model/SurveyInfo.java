package heykakao.HeyForm.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SurveyInfo {
    private User user;
    private Survey survey;
    private List<Question> questions = new ArrayList<>();
}