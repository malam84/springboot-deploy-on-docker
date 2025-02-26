FROM docker.io/library/openjdk:11
EXPOSE 8080
ADD /target/*.jar springbootdeployondocker.jar
ENTRYPOINT ["java", "-jar", "springbootdeployondocker.jar"]
