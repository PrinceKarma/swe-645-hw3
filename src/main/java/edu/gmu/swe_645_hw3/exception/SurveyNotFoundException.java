package edu.gmu.swe_645_hw3.exception;

public class SurveyNotFoundException extends RuntimeException {
    
    public SurveyNotFoundException(String message) {
        super(message);
    }
    
    public SurveyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}