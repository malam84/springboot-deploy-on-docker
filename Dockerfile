FROM openjdk:8
EXPOSE 8080
ADD /target/springbootdeployondocker.jar springbootdeployondocker.jar
ENTRYPOINT ["java", "-jar", "springbootdeployondocker.jar"]
