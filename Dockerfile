FROM java:8u111-jre-alpine

Add target/project-template-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=9066", "--server.address=0.0.0.0", "--server.register=false"]
