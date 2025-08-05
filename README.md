Student Survey Application with Spring Boot, Docker & Kubernetes
================================================================

This project is a Spring Boot microservice for managing student survey data. It provides RESTful APIs for CRUD operations (Create, Read, Update, Delete) on student surveys.

* * * * *
## Links:
 - Survey Frontend: http://ec2-13-223-116-1.compute-1.amazonaws.com:30081/
 - API: http://ec2-13-223-116-1.compute-1.amazonaws.com:30080/api/surveys
 - Rancher: https://ec2-13-223-116-1.compute-1.amazonaws.com/dashboard/home
 - Jenkins: http://ec2-34-236-60-189.compute-1.amazonaws.com:8080/




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
├── frontend/
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   ├── SurveyList.js
│   │   │   ├── SurveyForm.js
│   │   │   └── ... (other React components)
│   │   ├── App.js
│   │   └── index.js
│   ├── Dockerfile
│   ├── nginx.conf
│   └── package.json
├── k8s/
│   ├── deployment.yaml
│   └── service.yaml
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

```

API File Descriptions
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

Frontend Application with React
-------------

The frontend is a single-page application (SPA) built with React. It provides a full-featured user interface for all CRUD operations.

- **Features**:
    - View all surveys in a card-based layout.
    - Create new surveys using a survey form.
    - Edit existing surveys.
    - Delete surveys.
    - View the complete details of any survey.
    - Display a live count of the total number of surveys.
- **Technologies**:
    - **React**: For building the user interface components.
    - **Axios**: For making HTTP requests to the backend API.
    - **Nginx**: A lightweight web server used inside the Docker container to serve the static React files and proxy API requests.

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

Phase 3: Set Up a Kubernetes Cluster with Rancher
------------------------------------

We will first set up the Rancher management server and then use it to provision a new Kubernetes cluster.

1.  **Provision the Rancher & Kubernetes Server**
    * Using the AWS Management Console, launch a new EC2 instance.
    * **AMI**: `Ubuntu Server 24.04 LTS`.
    * **Instance Type**: `t3.large`
    * **Storage**: Increase the default storage to **30 GB**.
    * **Security Group**: Create a group named `rancher-k8s-sg` and add these inbound rules:
        * **Type**: `SSH` (Port 22) | **Source**: `My IP`
        * **Type**: `HTTP` (Port 80) | **Source**: `Anywhere`
        * **Type**: `HTTPS` (Port 443) | **Source**: `Anywhere`
        * **Type**: `Custom TCP` (Port 30080) | **Source**: `Anywhere`
        * **Type**: `Custom TCP` (Port 30081) | **Source**: `Anywhere`
    * **Assign an Elastic IP**: After the instance is running, assign an Elastic IP address to it so its public IP address does not change.


2.  **Install Rancher**
    * Connect to the server via SSH.
    * Install Docker:

        ```bash
        sudo apt update && sudo apt upgrade
        sudo apt install docker.io
        sudo usermod -aG docker $USER
        # Log out and log back in for the group change to take effect
        ```

    * Install and run the Rancher server software in a Docker container:

        ```bash
        docker run --privileged -d --restart=unless-stopped -p 80:80 -p 443:443 rancher/rancher
        ```

    * Wait a few minutes, then in your web browser, navigate to the public IP of your server.
    * The first time, Rancher will show you a command to get your initial admin password. Run it in your SSH terminal and use the password to log in. Set a new permanent password when prompted.

3.  **Create the Kubernetes Cluster**
    * Click **Create**.
    * Select **Custom**.
    * Enter **Cluster Name** (e.g., `swe645-cluster`).
    * Leave the other settings as default and click **Next**.
    * On the next screen, under **Node Role**, select all three roles: **etcd**, **Control Plane**, **Worker**, **Insecure**.
    * Copy the long registration command shown on the screen.
    * Run the registration command in the terminal

4.  **Get Your kubeconfig File**
    * On the **Cluster Management** page, click the three vertical dots (**⋮**) next to the your cluster and select **Download KubeConfig**.
    * Save this file. You will need to paste its contents into a Jenkins credential later.

* * * * *

Phase 4: Set Up the Jenkins Server
------------------------------------

You need a separate server to run Jenkins.

-   **Launch EC2 Instance**: Create a new `t3.medium` EC2 instance for Jenkins with a security group that allows inbound traffic on port `22` (SSH) and `8080` (Jenkins UI).
    * **Assign an Elastic IP**: After the instance is running, assign an Elastic IP address to it so its public IP address does not change.

-   **Install Tools**: SSH into the Jenkins server and run this script to install Jenkins, Docker, and kubectl.
    ```bash
    # Update, install Java and Git
    sudo apt update && sudo apt upgrade
    sudo apt install -y openjdk-17-jdk git

    # Install Jenkins
    sudo wget -O /etc/apt/keyrings/jenkins-keyring.asc \
    https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
    echo "deb [signed-by=/etc/apt/keyrings/jenkins-keyring.asc]" \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null
    sudo apt-get update
    sudo apt-get install jenkins

    # Install Docker
    sudo apt-get install -y docker.io
    sudo usermod -aG docker jenkins # Allow Jenkins to use Docker

    # Install kubectl
    sudo snap install kubectl --classic

    # Restart Jenkins to apply permissions
    sudo systemctl restart jenkins
    ```
-   **Jenkins Setup Wizard**: Access Jenkins in your browser at `http://<JENKINS_SERVER_IP>:8080` and complete the setup wizard. The initial password can be found by running `sudo cat /var/lib/jenkins/secrets/initialAdminPassword` on your Jenkins server. Choose to "Install suggested plugins".
-   **Configure Credentials**:
    1.  Navigate to **Manage Jenkins > Credentials > System > Global credentials**.
    2.  **Docker Hub**: Add new "**Username with password**" credentials. Use your Docker Hub username and password, and set the ID to the one you defined in your `Jenkinsfile` (e.g., `DockerCreds`).
    3.  **Kubernetes**: Add new "**Secret file**" credentials. Paste the `kubeconfig` content you copied from Rancher, and set the ID to the one you defined in your `Jenkinsfile` (e.g., `KubeCreds`).
    4. Add additional plugins
        - Docker pipeline
        - Kubernetes
        - Rancher

* * * * *

Phase 5: Create and Run the CI/CD Pipeline
------------------------------------

-   **Push Code to GitHub**: Commit all your project files and push them to a new GitHub repository.
-   **Create Pipeline in Jenkins**:
    1.  On the Jenkins dashboard, click **New Item**.
    2.  Enter a name (e.g., `swe645-pipeline`) and select **Pipeline**.
    3.  In the configuration, scroll to the **Pipeline** section.
        -   **Definition**: Pipeline script from SCM.
        -   **SCM**: Git.
        -   **Repository URL**: Enter your GitHub repository's URL.
        -   **Branch**: `*/main`.
    4.  Click **Save**.
-   **Run the Build**: Click **Build Now** to start your CI/CD pipeline. You can watch its progress in the "Console Output".

---

### References
- https://www.jenkins.io/doc/tutorials/tutorial-for-installing-jenkins-on-AWS/
- https://www.rancher.com/quick-start
- https://kubernetes.io/docs/concepts/services-networking/service/
- https://docs.docker.com/get-started/
