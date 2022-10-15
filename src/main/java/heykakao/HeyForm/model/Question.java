package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heykakao.HeyForm.model.dto.QuestionDto;
import heykakao.HeyForm.repository.SurveyRepository;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "survey")
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_type")
    private Integer type;

    @Column(name = "question_order")
    private Integer order;

    @Column(name = "question_contents")
    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    public void setByDto(QuestionDto questionDto) {
        this.type = questionDto.getQuestion_type();
        this.order = questionDto.getQuestion_order();
    }

    public void setByDto(QuestionDto questionDto, Survey survey) {
        this.type = questionDto.getQuestion_type();
        this.order = questionDto.getQuestion_order();
        this.survey = survey;
    }

//    @OneToMany(mappedBy = "question")
//    private List<Choice> choices = new ArrayList<>();
//
//    @OneToMany(mappedBy = "question")
//    private List<Answer> answers = new ArrayList<>();
}
// 1: 주관식, 2: 객관식, 3: 별점, 4: 리커트, 5: 선형