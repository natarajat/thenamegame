# The Name Game
The Name Game API
by Raj Thuppanna

Build and run the Name Game API server

Key documents:
1.	Application source code: download from github (thenamegame-master.zip)
2.	Postman collection with sample API calls (attached): “The Name Game API.postman_collection.json”
The game needs a couple of prerequisites installed.
Prerequisites to build and run application:
1.	Download and Install latest version of Apache Maven 
2.	Download and Install latest update of JDK 8
Once the prerequisites are installed, unzip thenamegame-master.zip into a folder of your choice. The folder named  thenamegame-master under the chosen folder is the root folder to build and run the application. Run following commands from the root folder to build and run the application.
Command to build: mvn install
Command to run the application: mvn spring-boot:run
This should get the application running on port 8080 with a root context of “thenamegame”. With the default configuration, application can be accessed using following base URL
http://localhost:8080/thenamegame/
API sample calls provided in the Postman collection file “The Name Game API.postman_collection.json” can be used to view and run sample API calls. Client UI is not provided.


Design

The Name game is developed using the Spring boot framework with following packaged dependencies - Spring Web, Spring security, Jakson, Lombok and Swagger2.

Application has been kept intentionally simple to be able to develop a meaningful content in the suggested timeframe. My limited familiarity/unfamiliarity with the tools used (Spring boot, Spring security, JPA Maven, Postman etc.,) is one of the key reasons for keeping it simple.

Authentication/Authorization

Spring authentication is used to authenticate and authorize (there is only one ROLE) users with in-memory authentication (hard coded list of users). Authentication uses a form login. To login, post username and password to following API
http://localhost:8080/thenamegame/login
Post to following URL to logout: http://localhost:8080/thenamegame/logout

Hardcoded username/passwords: user1/user1, user2/user2, user3/user3, user4/user4 and user5/user5

This cannot be used in a production system. This needs to extended to get user detail from a data store. 


API

Application provides auto-generated API documentation using Swagger2. With default application configuration, API documentation can be viewed at 
http://localhost:8080/thenamegame/swagger-ui.html
Postman Collection: API sample calls provided in the Postman collection file “The Name Game API.postman_collection.json”

API Listing:

GET /v1/challenge – Get a challenge to identify coworkers

GET /v1/mattchallenge – Get a Matt challenge (only coworkers with “Mat” in name) to identify coworkers

POST /v1/guess – Post user guess for the current challenge

GET /v1/sessionStats – Get user statistics for current session

GET /v1/userStats – Get complete user statistics 

POST /login – Login with username and password form parameters

POST /logout – Logout


Key classes

ChallengeService (Implemented in ChallengeServiceImpl) is designed to encapsulate the logic for the game. It uses EmployeeProfileService to get Employee profiles and uses CachingService to cache Questions submitted to users and user statistics. 
NameGameController class implements all API provided by the application.
Storage

External storage is not used to keep the application simple. Application uses users session and static variables to hold application data. Ideally data such as profile records, user lifetime statistics should be stored in a relational database. Session data such as user statistics for the session can be stored in in-memory databases such as Redis and shared across instances of the application.

Configuration

Application configuration data is locally stored in application.properties file. This could be captured and served using a independent configuration so the configuration can be shared across multiple instances of the application.
Scaling

Application does not scale as is. Making the changes discussed in storage and configuration sections would make the application scale very well.

