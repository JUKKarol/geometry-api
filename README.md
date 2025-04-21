# Geometry API

A simple RESTful API for managing geometric shapes. The application supports saving shapes with parameters and retrieving them based on their type.

## Technologies

- Java 24
- Spring Boot
- Maven
- JPA (Hibernate)
- JUnit 5
- MockMvc

## Endpoints

### POST `/api/v1/shapes`

Creates and saves a new shape with its parameters.

**Request Body Example**:
```json
{
  "type": "CIRCLE",
  "parameters": [
    {
      "name": "r",
      "value": 5.0
    }
  ]
}
```

Response Example:

HTTP 200 OK
```json
{
    "Shape saved: CIRCLE"
}
```

GET /api/v1/shapes/{type}
Returns a list of shapes of the given type.

Path Variable:

type: Must be one of the following: CIRCLE, SQUARE, RECTANGLE.

Response Example:
```json
[
  {
    "type": "CIRCLE",
    "parameters": [
      {
        "name": "r",
        "value": 5.0
      }
    ]
  }
]
```

## Tests
### Unit Tests
There are 5 unit tests that verify:

- Validation logic

- Service methods

- Strategy pattern logic for shape creation

### Integration Tests
There are 4 integration tests that cover:

- Saving a shape via POST

- Validating bad requests

- Retrieving shapes via GET

- Behavior when no shapes are found

## Running the Tests
To run the tests:
```shell
mvn test
```
