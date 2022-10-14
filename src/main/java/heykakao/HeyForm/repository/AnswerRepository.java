package heykakao.HeyForm.repository;

import heykakao.HeyForm.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Answer a where a.question.id = ?1")
    List<Answer> findByQuestion_Id(@Nullable Long id);
}