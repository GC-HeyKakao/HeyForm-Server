package heykakao.HeyForm.service;

import heykakao.HeyForm.model.Survey;
import heykakao.HeyForm.model.SurveyInfo;
import heykakao.HeyForm.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;

    @Autowired public SurveyService(final SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    // 1. User id 값으로 설문 리스트 전부가져오기 (설문 리스트: question 이랑 choice 다 포함)
//    public List<SurveyInfo> getSurveyInfoById(Long userId) {return new SurveyInfo();}
    // 2. Json 받아서 저장
    public boolean saveSurveyInfo(SurveyInfo surveyInfo) {return true;}
    // 3. 설문 수정
    public boolean updateSurveyInfo(SurveyInfo surveyInfo) {return true;}
    // 4. 배포 URL
    public String getReleaseURL(Long surveyKey){return "";}
}

/*
    찬빈 : Restful API naming 하고 Swagger? 써서 프론트에 뿌려주기, 테스트 코드
    두원 : DI 부분 다시 공부하고 Service 작성
 */
