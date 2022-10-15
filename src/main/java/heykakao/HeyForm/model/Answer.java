package heykakao.HeyForm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import heykakao.HeyForm.model.dto.AnswerDto;
import heykakao.HeyForm.model.dto.ChoiceDto;
import lombok.*;

import javax.persistence.*;

@Entity @Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "question")
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Answer_id")
    private Long id;

    @Column(name = "answer_order")
    private Integer order;

    @Column(name = "answer_contents")
    private String contents;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public void setByDto(AnswerDto answerDto) {
        this.order = answerDto.getAnswer_order();
        this.contents = answerDto.getAnswer_contents();
    }

    public void setByDto(AnswerDto answerDto, Question question) {
        this.order = answerDto.getAnswer_order();
        this.contents = answerDto.getAnswer_contents();
        this.question = question;
    }
}
