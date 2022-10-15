package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heykakao.HeyForm.model.dto.SurveyDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "user")
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @Column(name = "survey_state")
    private Integer state; //0: during, 1: complete(before release) 2: terminate(after release)

    @Column(name = "survey_url")
    private String url;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setByDto(SurveyDto surveyDto) {
        this.state = surveyDto.getSurvey_state();
        this.url = surveyDto.getSurvey_url();
    }

    public void setByDto(SurveyDto surveyDto, User user) {
        this.state = surveyDto.getSurvey_state();
        this.url = surveyDto.getSurvey_url();
        this.user = user;
    }
}

//질문 : {주관식, something}, {객관식, {1,2,3,4,5} }
//답변 : {주관식, something}, {객관식, "1,2" }