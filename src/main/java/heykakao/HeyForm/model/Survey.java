package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SurveyId")
    private Long id;

    @Column(name = "surveyState")
    private Integer state; //0: during, 1: complete(before release) 2: terminate(after release)

    @Column(name = "surveyURL")
    private String url;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "UserKey")
    private User user;
}

//질문 : {주관식, something}, {객관식, {1,2,3,4,5} }
//답변 : {주관식, something}, {객관식, "1,2" }