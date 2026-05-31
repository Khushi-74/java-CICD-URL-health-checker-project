# URL Health Checker - End-to-End CI/CD Project


A simple Spring Boot application that checks the health of a URL and returns its HTTP status code, response time, and availability status. The project demonstrates a complete CI/CD pipeline using Azure DevOps, Docker, Docker Hub, and Azure Virtual Machines.

```code generated for application via AI```

---

## Project Overview

This project was built to gain hands-on experience with:

- Java Spring Boot
- REST APIs
- Maven Build Automation
- Git & GitHub
- Docker Containerization
- Azure DevOps Pipelines
- Docker Hub Registry
- Azure Linux Virtual Machines
- SSH-based Automated Deployment
- End-to-End CI/CD

---

## Application Features

The application:

- Accepts a URL as input
- Sends an HTTP request to the URL
- Measures response time
- Retrieves HTTP status code
- Returns application status

### Sample Request

```http
GET /check?url=https://google.com
```

### Sample Response

```json
{
  "url": "https://google.com",
  "responseCode": 200,
  "responseTime": 143,
  "status": "UP"
}
```

---

# Project Architecture

```text
                   +------------------+
                   |     Developer    |
                   +---------+--------+
                             |
                             | git push
                             v
                   +------------------+
                   |      GitHub      |
                   +---------+--------+
                             |
                             | Trigger
                             v
                +------------------------+
                | Azure DevOps Pipeline  |
                +-----------+------------+
                            |
                +-----------+------------+
                | Maven Build            |
                | Unit Tests             |
                | Package JAR            |
                +-----------+------------+
                            |
                            v
                +------------------------+
                | Docker Image Build     |
                +-----------+------------+
                            |
                            v
                +------------------------+
                | Docker Hub Repository  |
                +-----------+------------+
                            |
                            v
                +------------------------+
                | Azure Linux VM         |
                | Docker Installed       |
                +-----------+------------+
                            |
                            v
                +------------------------+
                | Spring Boot Container  |
                +-----------+------------+
                            |
                            v
                     Browser Access
```

---

# Technology Stack

| Component | Technology |
|------------|------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Build Tool | Maven |
| Source Control | Git |
| Repository | GitHub |
| Containerization | Docker |
| Registry | Docker Hub |
| CI/CD | Azure DevOps |
| Hosting | Azure Virtual Machine |
| Operating System | Ubuntu Linux |

---

# Project Structure

```text
url-health-checker
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.khushi.url_health_checker
│   │   │       ├── controller
│   │   │       ├── service
│   │   │       ├── model
│   │   │       └── UrlHealthCheckerApplication.java
│   │
│   └── resources
│       └── application.properties
│
├── Dockerfile
├── pom.xml
├── azure-pipelines.yml
└── README.md
```

---

# Development Journey

## Phase 1 - Application Development

Created a Spring Boot application with:

### Controller Layer

Responsible for exposing REST endpoints.

```java
@GetMapping("/check")
```

### Service Layer

Responsible for:

- URL validation
- HTTP connection creation
- Response code retrieval
- Response time measurement

### Model Layer

Created a response model containing:

```java
url
responseCode
responseTime
status
```

---

# Local Build & Testing

## Build Project

```bash
mvn clean package
```

### What it does

```text
clean   -> Deletes previous build artifacts
package -> Compiles code and creates executable JAR
```

Generated artifact:

```text
target/url-health-checker-1.0-SNAPSHOT.jar
```

---

## Run Application

```bash
java -jar target/url-health-checker-1.0-SNAPSHOT.jar
```

Application starts on:

```text
http://localhost:8080
```

---

# Dockerization

## Dockerfile

```dockerfile
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/url-health-checker-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

---

## Build Docker Image

```bash
docker build -t url-health-checker:v1 .
```

---

## Run Docker Container

```bash
docker run -d \
--name url-health-checker \
-p 8080:8080 \
url-health-checker:v1
```

---

## Verify Container

```bash
docker ps
```

---

# Docker Hub Integration

## Login

```bash
docker login
```

---

## Tag Image

```bash
docker tag url-health-checker:v1 <dockerhub-user>/url-health-checker-dockerhub:latest
```

---

## Push Image

```bash
docker push <dockerhub-user>/url-health-checker-dockerhub:latest
```

---

# Azure DevOps CI Pipeline

Pipeline performs:

1. Source checkout
2. Maven build
3. Package JAR
4. Publish artifacts
5. Build Docker image
6. Push Docker image to Docker Hub

---

## CI Pipeline YAML

```yaml
trigger:
- main

pool:
  vmImage: ubuntu-latest

steps:

- task: Maven@4
  inputs:
    mavenPomFile: 'pom.xml'
    javaHomeOption: 'JDKVersion'
    jdkVersionOption: '1.17'
    goals: 'clean package'

- task: PublishBuildArtifacts@1
  inputs:
    PathtoPublish: 'target'
    ArtifactName: 'java-artifact'

- task: Docker@2
  inputs:
    containerRegistry: 'dockerhub-connection'
    repository: 'url-health-checker-dockerhub'
    command: 'buildAndPush'
```

---

# Azure Virtual Machine Setup

Created:

- Ubuntu Linux VM
- Public IP
- Network Security Group

Opened ports:

```text
22    SSH
8080  Application
```

---

# VM Access

```bash
ssh -i "private-key.pem" azureuser@<public-ip>
```

---

# Docker Installation on VM

```bash
curl -fsSL https://get.docker.com | sudo sh
```

### Meaning

```text
curl  -> download script
pipe  -> pass output
sudo  -> root privileges
sh    -> execute script
```

---

# Deploy Application on VM

## Pull Image

```bash
docker pull <dockerhub-user>/url-health-checker-dockerhub:latest
```

---

## Run Container

```bash
docker run -d \
--restart unless-stopped \
--name url-health-checker \
-p 8080:8080 \
<dockerhub-user>/url-health-checker-dockerhub:latest
```

---

# Continuous Deployment (CD)

Created SSH Service Connection in Azure DevOps.

Pipeline automatically:

```text
Build
↓
Package
↓
Docker Build
↓
Docker Push
↓
SSH into VM
↓
Pull Latest Image
↓
Replace Existing Container
```

---

## Deployment Task

```yaml
- task: SSH@0
  displayName: Deploy to Azure VM
  inputs:
    sshEndpoint: 'azure-vm-ssh'
    runOptions: 'commands'
    commands: |
      docker pull <dockerhub-user>/url-health-checker-dockerhub:latest

      docker stop url-health-checker || true

      docker rm url-health-checker || true

      docker run -d --restart unless-stopped --name url-health-checker -p 8080:8080 <dockerhub-user>/url-health-checker-dockerhub:latest
```

---

# End-to-End Deployment Flow

```text
Code Change
     ↓
Git Commit
     ↓
Git Push
     ↓
Azure DevOps Trigger
     ↓
Maven Build
     ↓
Create JAR
     ↓
Docker Build
     ↓
Push to Docker Hub
     ↓
SSH to Azure VM
     ↓
Pull Latest Image
     ↓
Stop Old Container
     ↓
Run New Container
     ↓
Application Updated
```

---

# Lessons Learned

### Spring Boot

- Controller-Service architecture
- REST API creation
- Dependency Injection

### Maven

- Build lifecycle
- Artifact generation
- Dependency management

### Docker

- Images vs Containers
- Dockerfile creation
- Image tagging
- Registry operations

### Azure DevOps

- YAML pipelines
- Service Connections
- Build automation
- Deployment automation

### Linux

- SSH access
- Docker installation
- Process management

### Azure

- VM provisioning
- NSG rules
- Public IP configuration

---

# Challenges Faced

### Docker Hub Rate Limit

```text
429 Too Many Requests
```

Solved by authenticating Docker client.

---

### SSH Connectivity Issues

```text
Connection timed out
```

Resolved by configuring NSG inbound rules.

---

### Container Removal During Failed Deployment

Container was deleted before new container creation.

Resolved by improving deployment logic.

---

### Docker Run Syntax Error

```text
docker: invalid reference format
```

Cause:

Azure DevOps SSH task did not handle multiline command formatting.

Resolved by converting command to a single line.

---

# Future Improvements

### Versioned Docker Tags

Instead of:

```text
latest
```

Use:

```text
v1
v2
v3
$(Build.BuildId)
```

Benefits:

- Rollbacks
- Release tracking
- Deployment history

---

### Multi-Stage Docker Build

Reduce image size and improve security.

---

### Health Checks

Add:

```dockerfile
HEALTHCHECK
```

to container.

---

### HTTPS

Configure reverse proxy using:

- Nginx
- Azure Application Gateway

---

### Monitoring

Integrate:

- Prometheus
- Grafana
- Azure Monitor

---

### Infrastructure as Code

Provision infrastructure using:

- Terraform
- Azure Bicep

---

### Kubernetes Deployment

Deploy containers to:

- Azure Kubernetes Service (AKS)

---

# Key Takeaway

This project demonstrates a complete DevOps lifecycle from application development to automated deployment.

Implemented:

✅ Java Spring Boot Application

✅ Docker Containerization

✅ GitHub Source Control

✅ Azure DevOps CI Pipeline

✅ Docker Hub Integration

✅ Azure Linux VM Hosting

✅ SSH-Based Continuous Deployment

✅ End-to-End Automated CI/CD Pipeline

---
