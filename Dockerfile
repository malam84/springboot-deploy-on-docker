FROM docker.io/library/openjdk:11
EXPOSE 8080
COPY springbootdeployondocker.jar springbootdeployondocker.jar
ENTRYPOINT ["java", "-jar", "springbootdeployondocker.jar"]
