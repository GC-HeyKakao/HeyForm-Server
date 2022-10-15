package heykakao.HeyForm.model.dto;

import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.model.Choice;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnswerDto {
    private Integer answer_order;
    private String answer_contents;

    @Builder
    public AnswerDto(Answer answer) {
        this.answer_order = answer.getOrder();
        this.answer_contents = answer.getContents();
    }
}
