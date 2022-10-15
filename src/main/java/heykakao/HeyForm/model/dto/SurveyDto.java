package heykakao.HeyForm.model.dto;

import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.model.Survey;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SurveyDto {
    private Integer survey_state; //0: during, 1: complete(before release) 2: terminate(after release)
    private String survey_url;

    @Builder
    public SurveyDto(Survey survey) {
        this.survey_state = survey.getState();
        this.survey_url = survey.getUrl();
    }
}
