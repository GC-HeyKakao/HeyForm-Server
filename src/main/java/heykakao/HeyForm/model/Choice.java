package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "question")
public class Choice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choiceId")
    private Long id;

    @Column(name = "choiceOrder")
    private Integer order;

    @Column(name = "choiceContents")
    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question")
    private Question question;
}
// 0: 질문 1...: 보기 숫자