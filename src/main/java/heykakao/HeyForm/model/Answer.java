package heykakao.HeyForm.model;

import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerId")
    private Long id;

    @Column(name = "answerOrder")
    private Integer order;

    @Column(name = "answerContents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "QuestionKey")
    private Question question;
}
