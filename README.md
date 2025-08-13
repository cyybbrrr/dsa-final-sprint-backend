# DSA Final Sprint Backend (Spring Boot + MongoDB)

## Prerequisites
- Java 21+
- Maven 3.9+
- MongoDB running (default URI: `mongodb://localhost:27017/bstdb`)

## Run
```bash
export MONGODB_URI="mongodb://localhost:27017/bstdb" # optional
mvn spring-boot:run
```
API:
- `POST /process-numbers` — body `{ "numbers": "8,3,10,1,6,14,4,7,13" }`
- `GET /previous-trees`

TESTING:

- Run `mvn test`.
