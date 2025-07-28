package edu.gmu.swe_645_hw3.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RecommendationLikelihood {
    VERY_LIKELY("Very Likely"),
    LIKELY("Likely"),
    UNLIKELY("Unlikely");
    
    private final String displayName;
    
    RecommendationLikelihood(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static RecommendationLikelihood fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        String trimmedValue = value.trim();
        
        // Try to match by display name first
        for (RecommendationLikelihood option : RecommendationLikelihood.values()) {
            if (option.displayName.equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // Try to match by enum constant name
        for (RecommendationLikelihood option : RecommendationLikelihood.values()) {
            if (option.name().equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // If no match found, throw exception with valid values
        String validValues = java.util.Arrays.stream(RecommendationLikelihood.values())
            .map(RecommendationLikelihood::getDisplayName)
            .collect(java.util.stream.Collectors.joining(", "));
            
        throw new IllegalArgumentException("Invalid value '" + value + "' for recommendationLikelihood. Valid values are: " + validValues);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}