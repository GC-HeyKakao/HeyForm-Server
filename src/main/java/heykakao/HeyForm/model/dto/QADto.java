package heykakao.HeyForm.model.dto;

import heykakao.HeyForm.model.Answer;
import heykakao.HeyForm.model.Choice;
import heykakao.HeyForm.model.QA;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QADto {
    private String qa_title;
    private String qa_contents;

    private Long qa_id;
    private String qa_answer;
    public QADto(QA qa) {
        this.qa_title = qa.getQa_title();
        this.qa_contents = qa.getQa_content();
        this.qa_answer = qa.getQa_answer();
        this.qa_id = qa.getId();
    }
}
