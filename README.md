<a id="readme-top"></a>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#summary">Summary</a></li>
        <li><a href="#pre-req">Prerequisites</a></li>
        <li>
            <a href="#installation">Installation</a>
            <ul>
                <li><a href="#docker">Docker</a></li>
                <li><a href="#PLACEHOLDER">PLACEHOLDER</a></li>
            </ul>
      </ul>
    </li>
    <li><a href="#usage">Usage</a>
        <ul>
            <li><a href="#api-reference">API Reference</a></li>
            <li><a href="#response-status-codes">Response Status Codes</a></li>
            <li><a href="#sample-request">Sample Request</a></li>
            <li><a href="#sample-response">Sample Response</a></li>
        </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#ownership">Ownership</a></li>
  </ol>
</details>

<a id="about-the-project"></a>
## About The Project - Salaholm
PLACEHOLDER FOR SALAHOLM INFO 

<a id="built-with"></a>
### Built With

* ![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?logo=openjdk&logoColor=white)
* ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff)
* ![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<a id="getting-started"></a>
## Getting Started

<a id="summary"></a>
### Summary

* Running the Project: Detailed steps to build and start the application.
* Tests: Guide on how to execute tests.
* Docker Setup: Instructions for containerizing the application with Docker.

<a id="pre-req"></a>
### Prerequisites

Ensure that the Java version is inclusively between [17](https://adoptium.net/temurin/releases/?version=17) and [20](https://adoptium.net/temurin/releases/?version=20).

* Check the installed Java version 
  ```sh
  java --version

Ensure that Docker is correctly installed (*Optional*)

* Check if a valid version of Docker is installed

  ```sh
  docker -v
  ```

<a id="installation"></a>

## Installation

*Below are the instructions provided to ensure that the project is built, tested and started correctly.*

<a id="docker"></a>

### Docker

1. Build the Project (*Ensure a clean build before creating the Docker image*)

   ```sh
   ./gradlew clean build
   ```
2. Build the Docker Image (*Creates a Docker image named `[docker-image-name]`*)

   ```sh
   docker build -t [docker-image-name] .
   ```
3. Run the Docker Container on Port 8000 (*Starts the container and maps port 8000 from the container to 8000 on the host*)

   ```sh
   docker run -p 8000:8000 [docker-image-name]
   ```
4. Run the Docker Container in Detached Mode (*Runs the container in the background so the terminal remains free*)

   ```sh
   docker run -d -p 8000:8000 [docker-image-name]
   ```
<a id="PLACEHOLDER"></a>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<a id="usage"></a>

## Usage

<a id="api-reference"></a>

### API Reference

```http
GET /api/v1/[endpoint]?param1={value1}&param2={value2}&param3={value3}
```

| Parameter | Type                    | Description                  |
| :-------- | :---------------------- | :--------------------------- |
| `param1`  | `string`                | **Required**. \[Description] |
| `param2`  | `integer`               | **Required**. \[Description] |
| `param3`  | `floating point number` | **Required**. \[Description] |

<a id="response-status-codes"></a>

### Response Status Codes

| Code  | Title                   | Description               |
| ----- | ----------------------- | ------------------------- |
| `200` | `OK`                    | Successful response       |
| `400` | `Bad request`           | Invalid input parameters  |
| `404` | `Not found`             | Resource not found        |
| `500` | `Internal server error` | General server-side error |

<a id="sample-request"></a>

### Sample Request

```http
curl http://localhost:8000/api/v1/[endpoint]?param1=value1&param2=value2&param3=value3
```

<a id="sample-response"></a>

### Sample Response

```json
{
    "key": "value",
    "another_key": {
        "nested_key": 123
    }
}
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<a id="roadmap"></a>

## Roadmap

*A rough estimate of how the features were developed*

* [x] PLACEHOLDER

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<a id="ownership"></a>

# Ownership

All code in this project was written by Muhammad Shamaeem as part of \[context: internship assignment / personal learning / etc.].
