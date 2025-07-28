package edu.gmu.swe_645_hw3.service;

import edu.gmu.swe_645_hw3.dto.*;
import java.util.List;

public interface SurveyService {
    
    SurveyResponseDTO createSurvey(SurveyRequestDTO surveyRequest);
    
    SurveyResponseDTO getSurveyById(Long id);
    
    SurveyResponseDTO updateSurvey(Long id, SurveyRequestDTO surveyRequest);
    
    void deleteSurvey(Long id);

    List<SurveyResponseDTO> getAllSurveys();
    
    long getSurveyCount();
}
