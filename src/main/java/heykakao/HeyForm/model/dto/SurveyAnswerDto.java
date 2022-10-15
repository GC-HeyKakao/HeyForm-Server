package heykakao.HeyForm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter @NoArgsConstructor @AllArgsConstructor @ToString
public class SurveyAnswerDto {
    private String user_account;
    private List<AnswerDto> answerDtos;
}
