package edu.gmu.swe_645_hw3.service;


import edu.gmu.swe_645_hw3.dto.*;
import edu.gmu.swe_645_hw3.model.Survey;

import edu.gmu.swe_645_hw3.exception.SurveyNotFoundException;
import edu.gmu.swe_645_hw3.repository.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {
    
    private static final Logger logger = LoggerFactory.getLogger(SurveyServiceImpl.class);
    
    private final SurveyRepository surveyRepository;
    
    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }
    
    @Override
    public SurveyResponseDTO createSurvey(SurveyRequestDTO surveyRequest) {
        logger.info("Creating new survey for email: {}", surveyRequest.getEmail());
        
        Survey survey = mapToEntity(surveyRequest);
        Survey savedSurvey = surveyRepository.save(survey);
        
        logger.info("Survey created successfully with ID: {}", savedSurvey.getId());
        return mapToResponseDTO(savedSurvey);
    }
    
    @Override
    @Transactional(readOnly = true)
    public SurveyResponseDTO getSurveyById(Long id) {
        logger.info("Fetching survey with ID: {}", id);
        
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException("Survey not found with ID: " + id));
        
        return mapToResponseDTO(survey);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SurveyResponseDTO> getAllSurveys() {
        logger.info("Fetching all surveys");
        
        List<Survey> surveys = surveyRepository.findAll();
        return surveys.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public SurveyResponseDTO updateSurvey(Long id, SurveyRequestDTO surveyRequest) {
        logger.info("Updating survey with ID: {}", id);
        
        Survey existingSurvey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException("Survey not found with ID: " + id));
        
        updateSurveyFields(existingSurvey, surveyRequest);
        Survey updatedSurvey = surveyRepository.save(existingSurvey);
        
        logger.info("Survey updated successfully with ID: {}", updatedSurvey.getId());
        return mapToResponseDTO(updatedSurvey);
    }
    
    @Override
    public void deleteSurvey(Long id) {
        logger.info("Deleting survey with ID: {}", id);
        
        if (!surveyRepository.existsById(id)) {
            throw new SurveyNotFoundException("Survey not found with ID: " + id);
        }
        
        surveyRepository.deleteById(id);
        logger.info("Survey deleted successfully with ID: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getSurveyCount() {
        return surveyRepository.count();
    }
    
    // Helper methods for mapping
    private Survey mapToEntity(SurveyRequestDTO dto) {
        Survey survey = new Survey();
        survey.setFirstName(dto.getFirstName());
        survey.setLastName(dto.getLastName());
        survey.setStreetAddress(dto.getStreetAddress());
        survey.setCity(dto.getCity());
        survey.setState(dto.getState());
        survey.setZip(dto.getZip());
        survey.setTelephoneNumber(dto.getTelephoneNumber());
        survey.setEmail(dto.getEmail());
        survey.setDateOfSurvey(dto.getDateOfSurvey());
        survey.setLikedMost(dto.getLikedMost());
        survey.setInterestSource(dto.getInterestSource());
        survey.setRecommendationLikelihood(dto.getRecommendationLikelihood());
        return survey;
    }
    
    private SurveyResponseDTO mapToResponseDTO(Survey survey) {
        return new SurveyResponseDTO(
                survey.getId(),
                survey.getFirstName(),
                survey.getLastName(),
                survey.getStreetAddress(),
                survey.getCity(),
                survey.getState(),
                survey.getZip(),
                survey.getTelephoneNumber(),
                survey.getEmail(),
                survey.getDateOfSurvey(),
                survey.getLikedMost(),
                survey.getInterestSource(),
                survey.getRecommendationLikelihood(),
                survey.getCreatedAt(),
                survey.getUpdatedAt()
        );
    }
    
    private void updateSurveyFields(Survey survey, SurveyRequestDTO dto) {
        survey.setFirstName(dto.getFirstName());
        survey.setLastName(dto.getLastName());
        survey.setStreetAddress(dto.getStreetAddress());
        survey.setCity(dto.getCity());
        survey.setState(dto.getState());
        survey.setZip(dto.getZip());
        survey.setTelephoneNumber(dto.getTelephoneNumber());
        survey.setEmail(dto.getEmail());
        survey.setDateOfSurvey(dto.getDateOfSurvey());
        survey.setLikedMost(dto.getLikedMost());
        survey.setInterestSource(dto.getInterestSource());
        survey.setRecommendationLikelihood(dto.getRecommendationLikelihood());
    }
}