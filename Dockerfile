FROM java:8
EXPOSE 8088
ADD /target/springbootdeployondocker.jar springbootdeployondocker.jar
ENTRYPOINT ["java", "-jar", "springbootdeployondocker.jar"]