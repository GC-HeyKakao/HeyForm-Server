package heykakao.HeyForm.model.dto;

import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.model.Survey;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SurveyDto {
    private Long survey_id;
    private Integer survey_state; //0: during, 1: complete(before release) 2: terminate(after release)
    private String survey_url;
    private Timestamp start_time;
    private Timestamp end_time;
    public SurveyDto(Survey survey) {
        this.survey_id = survey.getId();
        this.survey_state = survey.getState();
        this.survey_url = survey.getUrl();
        this.start_time = survey.getStarttime();
        this.end_time = survey.getEndtime();
    }
}
