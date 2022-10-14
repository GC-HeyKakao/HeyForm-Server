package heykakao.HeyForm.repository;

import heykakao.HeyForm.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.survey.id = ?1")
    List<Question> findBySurvey_Id(@Nullable Long id);
}