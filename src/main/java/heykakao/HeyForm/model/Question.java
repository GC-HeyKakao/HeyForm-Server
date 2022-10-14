package heykakao.HeyForm.model;

import heykakao.HeyForm.repository.SurveyRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Data
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionId")
    private Long id;

    @Column(name = "questionType")
    private Integer type;

    @Column(name = "questionOrder")
    private Integer order;

//    @Column(name = "questionCentents")
//    private String contents;

    @ManyToOne
    @JoinColumn(name = "SurveyKey")
    private Survey survey;
}
// 1: 주관식, 2: 객관식, 3: 별점, 4: 리커트, 5: 선형