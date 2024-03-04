Of course, here's the further updated technical description of the voting system, now including Maven, MySQL, and CDN technologies, translated into English:

## System Overview

This voting system is an all-encompassing monolithic application designed to provide a secure and equitable platform for users to vote. It guarantees that each user can only cast one vote and enables them to view their vote afterwards.

## System Architecture

*   **Monolithic Architecture**: An integrated application that combines front-end and back-end functionalities for easy deployment and management. The front-end is closely integrated with the Spring Boot back-end through Thymeleaf.

## Key Features

*   **User Authentication**: Ensures that users log in through a secure verification process.
*   **Role-Based Access Control (RBAC)**: Distributes different voting permissions based on the user's role.
*   **Captcha Verification**: Implements captchas during the login process to prevent automated attacks.
*   **CSRF Prevention**: Utilizes Spring Security to safeguard against Cross-Site Request Forgery attacks.
*   **Voting Functionality**: Allows authenticated users to vote once, ensuring the integrity of the voting process.
*   **View Personal Voting Results**: Users can review their voting choices after participation.
*   **View Vote Counts**: Public access to view the total votes each candidate has received.
*   **Password Protection**: Uses `BCryptPasswordEncoder` for robust encryption of user passwords.
*   **Unified Exception Handling**: Provides a cohesive exception handling mechanism to enhance user experience.

## Programming Technologies

*   **Spring Boot**: Acts as the foundational framework, facilitating development and configuration.
*   **Spring Security**: Handles authentication, authorization, CSRF protection, and password security.
*   **Hibernate ORM**: Manages database interactions, ensuring data accuracy and security.
*   **Thymeleaf**: For dynamic generation of the front-end, integrated with Spring Boot.
*   **jQuery**: Streamlines HTML document traversing, event handling, and Ajax interactions.
*   **Bootstrap**: For rapid development of responsive and mobile-first web pages.
*   **Maven**: Used for project management and build automation, simplifying dependency management and project builds.
*   **MySQL**: The database of choice for storing user data, vote records, and other necessary information.
*   **CDN (Content Delivery Network)**: Enhances the delivery of static assets (like CSS and JavaScript files) for faster load times and improved user experience.

## Security Features

*   **Password Encryption**: Leverages `BCryptPasswordEncoder` for securing user passwords.
*   **CSRF Protection**: Integrates CSRF protection features provided by Spring Security.

## How to Run

1.  Ensure JDK, Maven, and MySQL are installed on your system.
2.  Install the Database: Set up MySQL on your system and create a database named `vote_demo` and execute `init.sql` to create the necessary database schema and tables.
3.  Build the project with mvn clean package .
4.  Launch the application using `java -jar target/voting.jar`.
5.  Create Users Manually: Execute `CreateUserTest` to manually add users to the system. This step ensures that user records are available for authentication and voting.
6.  Visit `localhost:8080` in your browser to engage with the voting system.

Adding these steps provides clear instructions for setting up the database and adding initial user data, ensuring that the voting system is ready for use upon startup. If you have any more questions or need further adjustments, please let me know!
