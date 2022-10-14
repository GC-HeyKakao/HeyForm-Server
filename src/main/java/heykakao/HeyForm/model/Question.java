package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "questionId")
    private Long id;

    @Column(name = "questionType")
    private Integer type;

    @Column(name = "questionOrder")
    private Integer order;

//    @Column(name = "questionCentents")
//    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "survey")
    private Survey survey;

    @OneToMany(mappedBy = "question")
    private List<Choice> choices = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();
}
// 1: 주관식, 2: 객관식, 3: 별점, 4: 리커트, 5: 선형