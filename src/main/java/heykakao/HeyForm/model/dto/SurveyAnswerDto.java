package heykakao.HeyForm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class SurveyAnswerDto {
    private String user_token;
    private Long survey_id;
    private List<AnswerDto> answerDtos;

}
