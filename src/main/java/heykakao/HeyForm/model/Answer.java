package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "question")
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerId")
    private Long id;

    @Column(name = "answerOrder")
    private Integer order;

    @Column(name = "answerContents")
    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;
}
