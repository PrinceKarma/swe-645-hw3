package edu.gmu.swe_645_hw3.enums;

public enum RecommendationLikelihood {
    VERY_LIKELY("Very Likely"),
    LIKELY("Likely"),
    UNLIKELY("Unlikely");
    
    private final String displayName;
    
    RecommendationLikelihood(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
