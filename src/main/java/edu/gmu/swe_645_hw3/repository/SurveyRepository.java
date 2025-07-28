package edu.gmu.swe_645_hw3.repository;

import edu.gmu.swe_645_hw3.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // Spring Data JPA provides CRUD operations
}