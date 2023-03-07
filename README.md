# Spring-security-app

This repository contains a Spring Security application that demonstrates JWT authentication. The project uses Spring Boot, Spring Security, and the jjwt library for JWT authentication.

## Getting Started
To get started with this project, follow these steps:

1. Clone the repository to your local machine.
2. Import the project into your preferred IDE.
3. Build the project using Maven.
4. Run the project using the Spring Boot Maven plugin.

## Endpoints

The following endpoints are available in the application:

Register a new user
* POST /api/auth/signup<br/>
This endpoint allows a user to register a new account. <br/>
The request should include the following parameters:<br/>
* username: the username for the new account
* email: the email address for the new account
* password: the password for the new account

## Authenticate a user

* POST /api/auth/signin<br/>
This endpoint allows a user to authenticate using their username and password. <br/>
The request should include the following parameters:<br/>
* username: the username for the account
* password: the password for the account<br/>
The response will include a JWT token that can be used to authenticate subsequent requests.

## Get user information

* GET /api/user/{username}.<br/>
This endpoint allows a user to retrieve their own information.<br/>
The request should include the JWT token in the Authorization header as follows:


## Technologies Used
* Spring Boot
* Spring Security
* jjwt

## Contributing

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Submit a pull request.
