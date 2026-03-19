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