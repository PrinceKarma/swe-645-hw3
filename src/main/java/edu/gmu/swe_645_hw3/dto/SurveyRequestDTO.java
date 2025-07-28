package edu.gmu.swe_645_hw3.dto;

import edu.gmu.swe_645_hw3.enums.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class SurveyRequestDTO {
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    private String lastName;
    
    @NotBlank(message = "Street address is required")
    private String streetAddress;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "\\d{5}(-\\d{4})?", message = "ZIP code must be in format 12345 or 12345-6789")
    private String zip;
    
    @NotBlank(message = "Telephone number is required")
    @Pattern(regexp = "\\(?\\d{3}\\)?[-.]?\\d{3}[-.]?\\d{4}", message = "Invalid telephone number format")
    private String telephoneNumber;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotNull(message = "Date of survey is required")
    private LocalDate dateOfSurvey;
    
    private CampusLikedMost likedMost;
    private InterestSource interestSource;
    private RecommendationLikelihood recommendationLikelihood;
    
    // Constructors
    public SurveyRequestDTO() {}
    
    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }
    
    public String getTelephoneNumber() { return telephoneNumber; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public LocalDate getDateOfSurvey() { return dateOfSurvey; }
    public void setDateOfSurvey(LocalDate dateOfSurvey) { this.dateOfSurvey = dateOfSurvey; }
    
    public CampusLikedMost getLikedMost() { return likedMost; }
    public void setLikedMost(CampusLikedMost likedMost) { this.likedMost = likedMost; }
    
    public InterestSource getInterestSource() { return interestSource; }
    public void setInterestSource(InterestSource interestSource) { this.interestSource = interestSource; }
    
    public RecommendationLikelihood getRecommendationLikelihood() { return recommendationLikelihood; }
    public void setRecommendationLikelihood(RecommendationLikelihood recommendationLikelihood) { 
        this.recommendationLikelihood = recommendationLikelihood; 
    }
}