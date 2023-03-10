# IV1201-Group-9
In this repository is the backend for the Job Applicaton project in course IV1201 VT23 Arkitektur och design av globala applikationer. The README file is a description of the project and instructions on how to deploy the project. For working application using the front-end client, see https://frontend-iv1201-group9.herokuapp.com/ 

## Project Description 

The aim of the project for this course is to learn how to make good choices about the architecture in order to create a web application that meets high standards and is easily built upon by other developers. This ensures that the code is flexible and clear, making it convenient for others to continue working on the project.

The web application serves as a tool for people to register and apply for a job at an amusement park. Individuals can log in using their credentials and submit their job application. See https://github.com/IV1201-Group-9/IV1201-Group-9-frontend for client-application meant to be used together with this backend. 

## Tools and Frameworks
Below are some of the tools and framworks used for this backend application:
- Spring Boot
- Spring Security
- Postgres
- JSON Web Token (JWT)

For more see the MAVEN project file.

## Architecture
The backend, which is responsible for processing data and handling requests, is located in src/main/java/com/iv1201/recapp/. It is built using the Java programming language and the Spring Boot framework to provide a RESTful API. Layers included are: 
- Config - including exceptions, validation, security configuration and JSON web token filter.
- Controller - client interface with endpoints.
- Models - entities and DTO:s.
- Service - business logic. 
- Integration - repositories for service use.

The application is designed to have a low coupling and high cohesion. The controller layer, which is responsible for receiving all calls from the frontend, is designed as a @RestController and handles all the API calls with endpoints. The integration layer is responsible for all the communication with the database, while the model layer contains all the models and DTOs. With this approach, it ensures that each layer is independent and can be modified without affecting the other layers, leading to a more maintainable and scalable backend.

### Database
The PostgreSQL database management system is used for the relational database of this application.

## Installation
- Clone this git repository.
- Install postgres if you don't already have it. You can check installed version by running the command: psql --version. Log in with your postgres credentials and create the database.
- Install all required MAVEN packages by running the file in an appropiate IDE. Should be sufficient for installing all the dependencies.

## Running the application in development mode
- Start your database. 
- Some CORES configuration has to be made in src/main/java/com/iv1201/recapp/Config/SecConfig.java and src/main/java/com/iv1201/recapp/RecAppApplication.java for making it possible to interact with front-end application on for example http://localhost:3000.
- Start the server type mvn spring-boot:run in terminal from root directory of the project or alternatively simply find the main class that has the @SpringBootApplication annotation in this case src/main/java/com/iv1201/recapp/RecAppApplication.java Right-click on the class and select "Run as" > "Java Application".

## Deployment
The deployed backend application can be viewed at https://backend-iv1201-group9.herokuapp.com/api/v1/testEndpoint/anotherTestEndpoint which is a test end-point. For deploying new versions of this application the main branch of this repository has to be manually deployed using HEROKU. 

## Developers
- Parosh Shaways
- Gustav Normelli
- Farzaneh Tajik

