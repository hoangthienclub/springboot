# Run coverage unit test
    - ./mvnw test jacoco:report

# Spotless
    - ./mvnw spotless:check
    - ./mvnw spotless:apply

# SonarQube
    - docker pull sonarqube:lts-community
    - docker run --name sonar-qube -p 9000:9000 -d sonarqube:lts-community
    -  ./mvnw clean verify sonar:sonar -Dsonar.projectKey=SpringBoot1 -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_6db86691f08190c12665272d79d07dfd1f82a1c6
    - plugin: sonarlint

# Profile
    - VM option: -Dspring.profiles.active=prod

# Deploy
## Solution 1
    - clean: ./mvnw clean
    - build: ./mvnw package 
    - build without test: ./mvnw package -DskipTests
    - run: java -jar ./target/demo-0.0.1-SNAPSHOT.jar
## Solution 2
    - install maven
    - clean: mvn clean
    - build: mvn package
    - build without test: mvn package -DskipTests
    - run: java -jar ./target/demo-0.0.1-SNAPSHOT.jar