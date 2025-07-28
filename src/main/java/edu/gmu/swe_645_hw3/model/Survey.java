package edu.gmu.swe_645_hw3.model;

import edu.gmu.swe_645_hw3.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "surveys")
public class Survey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @NotBlank(message = "Street address is required")
    @Column(name = "street_address", nullable = false)
    private String streetAddress;
    
    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String city;
    
    @NotBlank(message = "State is required")
    @Column(nullable = false)
    private String state;
    
    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "\\d{5}(-\\d{4})?", message = "ZIP code must be in format 12345 or 12345-6789")
    @Column(nullable = false)
    private String zip;
    
    @NotBlank(message = "Telephone number is required")
    @Pattern(regexp = "\\(?\\d{3}\\)?[-.]?\\d{3}[-.]?\\d{4}", message = "Invalid telephone number format")
    @Column(name = "telephone_number", nullable = false)
    private String telephoneNumber;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotNull(message = "Date of survey is required")
    @Column(name = "date_of_survey", nullable = false)
    private LocalDate dateOfSurvey;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "liked_most")
    private CampusLikedMost likedMost;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "interest_source")
    private InterestSource interestSource;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "recommendation_likelihood")
    private RecommendationLikelihood recommendationLikelihood;
    
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public Survey() {}
    
    public Survey(String firstName, String lastName, String streetAddress, String city, 
                 String state, String zip, String telephoneNumber, String email, 
                 LocalDate dateOfSurvey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.dateOfSurvey = dateOfSurvey;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}