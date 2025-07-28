package edu.gmu.swe_645_hw3.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CampusLikedMost {
    STUDENTS("Students"),
    LOCATION("Location"),
    CAMPUS("Campus"),
    ATMOSPHERE("Atmosphere"),
    DORM_ROOMS("Dorm Rooms"),
    SPORTS("Sports");
    
    private final String displayName;
    
    CampusLikedMost(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static CampusLikedMost fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        String trimmedValue = value.trim();
        
        // Try to match by display name first
        for (CampusLikedMost option : CampusLikedMost.values()) {
            if (option.displayName.equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // Try to match by enum constant name
        for (CampusLikedMost option : CampusLikedMost.values()) {
            if (option.name().equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // If no match found, throw exception with valid values
        String validValues = java.util.Arrays.stream(CampusLikedMost.values())
            .map(CampusLikedMost::getDisplayName)
            .collect(java.util.stream.Collectors.joining(", "));
            
        throw new IllegalArgumentException("Invalid value '" + value + "' for likedMost. Valid values are: " + validValues);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}