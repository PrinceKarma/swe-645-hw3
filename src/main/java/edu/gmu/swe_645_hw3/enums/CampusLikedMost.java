package edu.gmu.swe_645_hw3.enums;

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
    
    public String getDisplayName() {
        return displayName;
    }
}