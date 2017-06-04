FROM fabric8/java-alpine-openjdk8-jdk:latest

COPY target/*.jar /deployments/

EXPOSE 8080

ENTRYPOINT [ "/deployments/run-java.sh" ]
