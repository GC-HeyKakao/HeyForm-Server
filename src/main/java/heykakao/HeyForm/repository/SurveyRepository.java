package heykakao.HeyForm.repository;

import heykakao.HeyForm.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s where s.user.id = ?1")
    List<Survey> findByUser_Id(@Nullable Long id);

    @Query("select s from Survey s where s.user.account = ?1")
    List<Survey> findByUser_Account(String account);

    @Query("select s from Survey s where s.url = ?1")
    List<Survey> findByUrl(String url);

}