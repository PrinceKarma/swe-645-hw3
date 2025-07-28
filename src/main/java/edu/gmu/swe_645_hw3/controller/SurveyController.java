package edu.gmu.swe_645_hw3.controller;

import edu.gmu.swe_645_hw3.dto.*;
import edu.gmu.swe_645_hw3.service.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "*")
public class SurveyController {
    
    private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    
    private final SurveyService surveyService;
    
    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    
    @PostMapping
    public ResponseEntity<SurveyResponseDTO> createSurvey(@Valid @RequestBody SurveyRequestDTO surveyRequest) {
        logger.info("Creating new survey for email: {}", surveyRequest.getEmail());
        
        SurveyResponseDTO createdSurvey = surveyService.createSurvey(surveyRequest);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<SurveyResponseDTO>> getAllSurveys() {
        logger.info("Fetching all surveys");
        
        List<SurveyResponseDTO> surveys = surveyService.getAllSurveys();
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> getSurveyById(@PathVariable Long id) {
        logger.info("Fetching survey with ID: {}", id);
        
        SurveyResponseDTO survey = surveyService.getSurveyById(id);
        return new ResponseEntity<>(survey, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SurveyResponseDTO> updateSurvey(
            @PathVariable Long id, 
            @Valid @RequestBody SurveyRequestDTO surveyRequest) {
        
        logger.info("Updating survey with ID: {}", id);
        
        SurveyResponseDTO updatedSurvey = surveyService.updateSurvey(id, surveyRequest);
        return new ResponseEntity<>(updatedSurvey, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        logger.info("Deleting survey with ID: {}", id);
        
        surveyService.deleteSurvey(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getSurveyCount() {
        logger.info("Fetching survey count");
        
        long count = surveyService.getSurveyCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}