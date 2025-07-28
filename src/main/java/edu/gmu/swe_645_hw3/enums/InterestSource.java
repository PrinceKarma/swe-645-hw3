package edu.gmu.swe_645_hw3.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum InterestSource {
    FRIENDS("Friends"),
    TELEVISION("Television"),
    INTERNET("Internet"),
    OTHER("Other");
    
    private final String displayName;
    
    InterestSource(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static InterestSource fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        String trimmedValue = value.trim();
        
        // Try to match by display name first
        for (InterestSource option : InterestSource.values()) {
            if (option.displayName.equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // Try to match by enum constant name
        for (InterestSource option : InterestSource.values()) {
            if (option.name().equalsIgnoreCase(trimmedValue)) {
                return option;
            }
        }
        
        // If no match found, throw exception with valid values
        String validValues = java.util.Arrays.stream(InterestSource.values())
            .map(InterestSource::getDisplayName)
            .collect(java.util.stream.Collectors.joining(", "));
            
        throw new IllegalArgumentException("Invalid value '" + value + "' for interestSource. Valid values are: " + validValues);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}