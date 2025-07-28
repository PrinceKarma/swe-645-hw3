Student Survey Application with Spring Boot, Docker & Kubernetes
================================================================

This project is a Spring Boot microservice for managing student survey data. It provides RESTful APIs for CRUD operations (Create, Read, Update, Delete) on student surveys.

* * * * *

Phase 1: Project Setup & Development
------------------------------------

### 1.1 Spring Boot Application Setup

-   **Tools**: Spring Initializr (<https://start.spring.io/>)

-   **Dependencies to Include**:

    -   Spring Web

    -   Spring Data JPA

    -   MySQL Driver

    -   Spring Boot DevTools

    -   Spring Boot Actuator

    -   Validation

    -   Spring Boot Starter Test

### 1.2 Project Structure Setup

Create the following directory structure:

```
/
├── src/
│   ├── main/
│   │   ├── java/edu/gmu/swe_645_hw3/
│   │   │   ├── Swe645Hw3Application.java
│   │   │   ├── controller/
│   │   │   │   └── SurveyController.java
│   │   │   ├── dto/
│   │   │   │   ├── SurveyRequestDTO.java
│   │   │   │   └── SurveyResponseDTO.java
│   │   │   ├── enums/
│   │   │   │   ├── CampusLikedMost.java
│   │   │   │   ├── InterestSource.java
│   │   │   │   └── RecommendationLikelihood.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── SurveyNotFoundException.java
│   │   │   ├── model/
│   │   │   │   └── Survey.java
│   │   │   ├── repository/
│   │   │   │   └── SurveyRepository.java
│   │   │   └── service/
│   │   │       ├── SurveyService.java
│   │   │       └── SurveyServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/edu/gmu/swe_645_hw3/
│           └── Swe645Hw3ApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```

File Descriptions
-----------------

-   `pom.xml`: This file manages the project's dependencies and build configurations.

-   `mvnw` / `mvnw.cmd`: These are Maven wrapper scripts that allow you to build the project without installing Maven locally.

-   `.gitignore`: This file specifies which files and directories should be ignored by Git.

-   `application.properties`: This file contains the configuration for the application, such as database connection details.

-   `Swe645Hw3Application.java`: This is the main class that starts the Spring Boot application.

-   `SurveyController.java`: This class defines the RESTful API endpoints for handling survey-related requests.

-   `SurveyService.java` / `SurveyServiceImpl.java`: These files contain the business logic for managing surveys.

-   `SurveyRepository.java`: This interface provides the mechanism for interacting with the database.

-   `Survey.java`: This is the data model for a student survey.

-   `SurveyRequestDTO.java` / `SurveyResponseDTO.java`: These are data transfer objects used for sending and receiving survey data.

-   `GlobalExceptionHandler.java`: This class handles exceptions and formats error responses.

* * * * *

API Endpoints
-------------

The base URL for all endpoints is `/api/surveys`.

### 1\. Create a New Survey

-   **Endpoint**: `POST /api/surveys`

-   **Description**: Creates a new student survey.

-   **Request Body**:

    JSON

    ```
    {
      "firstName": "John",
      "lastName": "Doe",
      "streetAddress": "123 Main St",
      "city": "Anytown",
      "state": "CA",
      "zip": "12345",
      "telephoneNumber": "123-456-7890",
      "email": "john.doe@example.com",
      "dateOfSurvey": "2024-07-26",
      "likedMost": "Students",
      "interestSource": "Friends",
      "recommendationLikelihood": "Very Likely"
    }

    ```

-   **Response**:

    -   **201 Created**: Returns the created survey object.

    -   **400 Bad Request**: If the request body is invalid.

### 2\. Get All Surveys

-   **Endpoint**: `GET /api/surveys`

-   **Description**: Retrieves a list of all student surveys.

-   **Response**:

    -   **200 OK**: Returns an array of survey objects.

### 3\. Get a Survey by ID

-   **Endpoint**: `GET /api/surveys/{id}`

-   **Description**: Retrieves a single student survey by its ID.

-   **Response**:

    -   **200 OK**: Returns the survey object.

    -   **404 Not Found**: If no survey is found with the given ID.

### 4\. Update a Survey

-   **Endpoint**: `PUT /api/surveys/{id}`

-   **Description**: Updates an existing student survey.

-   **Request Body**: Same as the `POST` request.

-   **Response**:

    -   **200 OK**: Returns the updated survey object.

    -   **400 Bad Request**: If the request body is invalid.

    -   **404 Not Found**: If no survey is found with the given ID.

### 5\. Delete a Survey

-   **Endpoint**: `DELETE /api/surveys/{id}`

-   **Description**: Deletes a student survey by its ID.

-   **Response**:

    -   **204 No Content**: If the survey is deleted successfully.

    -   **404 Not Found**: If no survey is found with the given ID.

### 6\. Get Survey Count

-   **Endpoint**: `GET /api/surveys/count`

-   **Description**: Retrieves the total number of surveys.

-   **Response**:

    -   **200 OK**: Returns the total count of surveys.

* * * * *

Database Setup
--------------

### AWS RDS MySQL Setup & Integration

#### 2.1 AWS RDS Instance Setup

**Step 1: Create RDS MySQL Instance**

-   **Using AWS Console**

    1.  **Navigate to RDS Dashboard**

        -   Log into AWS Console

        -   Go to Services → Database → RDS

        -   Click "Create database"

    2.  **Database Creation Method**

        -   Select "Standard create"

        -   Choose "MySQL" as the engine type

    3.  **Engine Options**

        -   **Engine Version**: MySQL 8.0.35 (or latest)

        -   **Templates**: Choose "Free tier" for development

    4.  **Settings Configuration**

        ```
        DB Instance Identifier: student-survey-db
        Master Username: surveyadmin
        Master Password: [Create secure password - Save this!]
        Confirm Password: [Same as above]

        ```

    5.  **Instance Configuration**

        ```
        DB Instance Class: db.t3.micro (Free tier eligible)
        Storage Type: General Purpose SSD (gp2)
        Allocated Storage: 20 GiB
        Enable storage autoscaling: Yes
        Maximum storage threshold: 100 GiB

        ```

    6.  **Connectivity Settings**

        ```
        VPC: Default VPC
        Subnet group: default
        Public access: Yes
        VPC security group: Create new
        Security group name: student-survey-sg
        Availability Zone: No preference
        Database port: 3306

        ```

    7.  **Database Authentication**

        -   Database authentication: Password authentication

    8.  **Review and Create**

        -   Review all settings

        -   Click "Create database"

        -   Wait 10-15 minutes for the instance to be created.

#### 2.2 Security Configuration

-   **Using AWS Console**

    1.  **Navigate to EC2 → Security Groups**

    2.  **Find your security group** (student-survey-sg)

    3.  **Add Inbound Rules**:

| Type | Protocol | Port Range | Source Type | Source | Description |
| --- | --- | --- | --- | --- | --- |
| MySQL/Aurora | TCP | 3306 | My IP | Your IP/32 | Development access |
| MySQL/Aurora | TCP | 3306 | Custom | 10.0.0.0/16 | VPC access for applications |

#### 2.3 Spring Boot Integration

-   **Configuration (`application.properties`)**

    ```
    # Database Configuration
    spring.datasource.url=jdbc:mysql://swe-645-hw3-db.c2140q6ugl3n.us-east-1.rds.amazonaws.com:3306/student_survey_db?createDatabaseIfNotExist=true
    spring.datasource.username=admin
    spring.datasource.password=<password>
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    ```

* * * * *
