package heykakao.HeyForm.model.dto;

import heykakao.HeyForm.model.Question;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor @ToString
public class QuestionDto {
    private Integer question_type;
    private Integer question_order;
    private List<ChoiceDto> choiceDtos;


    @Builder
    public QuestionDto(Integer question_type, Integer question_order) {
        this.question_type = question_type;
        this.question_order = question_order;
    }

    @Builder
    public QuestionDto(Question question) {
        this.question_type = question.getType();
        this.question_order = question.getOrder();
    }

    @Builder
    public QuestionDto(Question question, List<ChoiceDto> choiceDtos) {
        this.question_type = question.getType();
        this.question_order = question.getOrder();
        this.choiceDtos = choiceDtos;
    }

    public void setChoiceDtos(List<ChoiceDto> choiceDtos){
        this.choiceDtos = choiceDtos;
    }
}
