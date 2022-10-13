package heykakao.HeyForm.model;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class Choice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChoiceId")
    private Long id;

    @Column(name = "choiceOrder")
    private Integer order;

    @Column(name = "choiceContents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "QuestionKey")
    private Question question;
}
// 0: 질문 1...: 보기 숫자