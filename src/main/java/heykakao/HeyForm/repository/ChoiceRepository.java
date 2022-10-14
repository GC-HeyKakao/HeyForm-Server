package heykakao.HeyForm.repository;

import heykakao.HeyForm.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    @Query("select c from Choice c where c.question.id = ?1")
    List<Choice> findByQuestion_Id(@Nullable Long id);

}