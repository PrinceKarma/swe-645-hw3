package edu.gmu.swe_645_hw3.enums;

public enum InterestSource {
    FRIENDS("Friends"),
    TELEVISION("Television"),
    INTERNET("Internet"),
    OTHER("Other");
    
    private final String displayName;
    
    InterestSource(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}