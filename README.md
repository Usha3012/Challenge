# Project Name

SPO Coding Challenge

## Building Instruction

- Java 11+
- Docker
- Run ```mvn clean package spring-boot:repackage```
- Run ```docker-compose build```
- RUN ```docker-compose up```


## API SPEC

```text
POST /api/optimize/cleaners
Header Content-type: "application/json"
Request Body 

{
	"rooms": [35, 21, 17, 28],
	"senior": 10,
	"junior": 6 
}

Response Body

[
    {
        "senior": 3,
        "junior": 1
    },
    {
        "senior": 1,
        "junior": 2
    },
    {
        "senior": 2,
        "junior": 0
    },
    {
        "senior": 3,
        "junior": 0
    }
]
```
