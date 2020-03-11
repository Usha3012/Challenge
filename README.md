# Project Name

SPO Coding Challenge

# Purpose
Minimize overcapacity at every structure

# Background
Build a workforce optimization tool for one of our cleaning partners! Our partner has
contracts with several structures all around Berlin. These structures are all of varying
size (measured in rooms). The workforce of our partner is made up of Senior
Cleaners and Junior Cleaners. Senior Cleaners have a higher work capacity than
Junior Cleaners. Our partner is free to decide how many Senior and Junior Cleaners
are to be sent to clean a structure but there always needs to be at least one Senior
cleaner at every structure to lead the juniors. 

# Input
- array of rooms (int) for every structure
- cleaning capacity Junior Cleaner (int)
- cleaning capacity Senior Cleaner (int)
# Output
- array of maps which include the optimal number of Juniors and Seniors for ever
structure

## Pre requisites
* jdk 1.8+
* Docker version 17.06.0+ (reason of using docker-compose 3.3)
* maven 

## Build & Run Instruction
Application contains a docker-compose and Dockerfile . This ramps up a postgres instance and the application.
* Run `mvn clean package spring-boot:repackage`
* Run `docker-compose build`
* Run `docker-compose up`

#Base url
http://localhost:8080
## API Implementation
+ve Scenario
```text
POST /workforce/cleaners/optimize
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

```
-Ve Scenario to check Validation

1. When rooms are more than 100
    Request Body
    {
    	"rooms": [102, 21, 17, 28],
    	"senior": 10,
    	"junior": 6 
    }

    Response Body
    {
        "timestamp": "2020-03-11T08:27:17.728+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Please select rooms less than 100 as you have selected InValid selection of 102",
        "path": "//workforce/cleaners/optimize"
    }
2. When rooms are provided with -ve value
    Request Body
    {
    	"rooms": [-35, 21, 17, 28],
    	"senior": 10,
    	"junior": 6 
    }

    Response Body
    {
        "timestamp": "2020-03-11T08:29:27.546+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Please select valid number of Rooms",
        "path": "//workforce/cleaners/optimize"
    }

3. When no of rooms are provided as input
    Request Body
    {
    	"rooms": [ ],
    	"senior": 10,
    	"junior": 6 
    }

    Response Body
    {
        "timestamp": "2020-03-11T08:30:48.592+0000",
        "status": 400,
        "error": "Bad Request",
        "message": "Please select valid number of Rooms",
        "path": "//workforce/cleaners/optimize"
    }
4. When rooms entity not provided as input
    Request Body
    {
        "senior": 10,
       	"junior": 6 
    }

    Response Body
    {
        "timestamp": "2020-03-11T08:33:58.850+0000",
        "status": 500,
        "error": "Internal Server Error",
        "message": "No message available",
        "path": "//workforce/cleaners/optimize"
    }

5. When Minimum value of senior is not 1
    Request Body
    {
	"rooms": [35, 21, 17, 28],
	"senior": 0,
	"junior": 6 
    }

    Response Body
    {
        "timestamp": "2020-03-11T08:37:20.580+0000",
        "status": 400,
        "error": "Bad Request",
        "errors": [
            {
                "codes": [
                    "Min.requestDTO.senior",
                    "Min.senior",
                    "Min.int",
                    "Min"
                ],
                "arguments": [
                    {
                        "codes": [
                            "requestDTO.senior",
                            "senior"
                        ],
                        "arguments": null,
                        "defaultMessage": "senior",
                        "code": "senior"
                    },
                    1
                ],
                "defaultMessage": "must be greater than or equal to 1",
                "objectName": "requestDTO",
                "field": "senior",
                "rejectedValue": 0,
                "bindingFailure": false,
                "code": "Min"
            }
        ],
        "message": "Validation failed for object='requestDTO'. Error count: 1",
        "path": "//workforce/cleaners/optimize"
    }

```

