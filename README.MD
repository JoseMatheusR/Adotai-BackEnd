# Adotai Pets Backend

## Description

Adotai Pets Backend is the server-side application for managing pet adoption processes. It provides APIs to handle user accounts and pet data ensuring a seamless experience for both adopters and administrators.

## Features

- User authentication and authorization.
- CRUD operations for pet management. (DELETE and EDIT are in WIP)
- Adoption module (WIP)

## Installation

### Prerequisites

- Ensure you have Maven installed. You can download it from [Maven's official website](https://maven.apache.org/download.cgi).

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/adotai-pets-backend.git
   ```
2. Navigate to the project directory:
   ```bash
   cd adotai-pets-backend
   ```
3. Install dependencies:

   ```bash
   mvn clean install
   ```

4. Start the database using Docker:

   ```bash
   docker-compose up -d
   ```

5. Start the development server:
   ```bash
   mvn spring-boot:run
   ```

## Front-End Application

The front-end application for this project can be found at [Adotai FrontEnd](https://github.com/JoseMatheusR/Adotai-FrontEnd).

## Project Structure

The project follows a clean architecture approach, dividing responsibilities into distinct layers. Below is an overview of the directory structure:

```
src
├───main
│   ├───java
│   │   └───com
│   │       └───adotai
│   │           └───pets
│   │               ├───core
│   │               │   ├───domain
│   │               │   ├───DTOs
│   │               │   │   ├───queryParams
│   │               │   │   ├───request
│   │               │   │   └───response
│   │               │   ├───exceptions
│   │               │   │   └───base
│   │               │   ├───gateways
│   │               │   ├───services
│   │               │   └───usecases
│   │               ├───data
│   │               │   ├───entities
│   │               │   ├───gatewayImpl
│   │               │   ├───mapper
│   │               │   └───repository
│   │               └───infra
│   │                   ├───config
│   │                   ├───controllers
│   │                   ├───exceptionHandlers
│   │                   │   └───DTOs
│   │                   ├───security
│   │                   │   ├───filters
│   │                   │   ├───handlers
│   │                   │   └───utils
│   │                   └───servicesImpl
│   └───resources
│       └───db
│           └───migration
└───test
    ├───java
    │   └───com
    │       └───adotai
    │           └───pets
    │               └───core
    │                   └───usecases
    └───resources
```

### Major Sections:

- **Core**: Contains the domain models, DTOs, exceptions, gateways, services, and use cases that define the business logic.
- **Data**: Handles database-related operations, including entities, repositories, and mappers.
- **Infra**: Manages application infrastructure, including configurations, controllers, security, and service implementations.
- **Resources**: Includes database migration scripts and other resource files.
- **Test**: Contains unit tests and test resources for validating the application.

## Usage

- Use tools like Postman or cURL to interact with the APIs.
- Integrate the backend with the frontend application.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
