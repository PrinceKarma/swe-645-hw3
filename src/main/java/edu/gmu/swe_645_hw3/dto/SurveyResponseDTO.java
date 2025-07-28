package edu.gmu.swe_645_hw3.dto;

import edu.gmu.swe_645_hw3.enums.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SurveyResponseDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String telephoneNumber;
    private String email;
    private LocalDate dateOfSurvey;
    private CampusLikedMost likedMost;
    private InterestSource interestSource;
    private RecommendationLikelihood recommendationLikelihood;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public SurveyResponseDTO() {}
    
    public SurveyResponseDTO(Long id, String firstName, String lastName, String streetAddress,
                           String city, String state, String zip, String telephoneNumber,
                           String email, LocalDate dateOfSurvey, CampusLikedMost likedMost,
                           InterestSource interestSource, RecommendationLikelihood recommendationLikelihood,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.dateOfSurvey = dateOfSurvey;
        this.likedMost = likedMost;
        this.interestSource = interestSource;
        this.recommendationLikelihood = recommendationLikelihood;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and Setters 
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
        
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}