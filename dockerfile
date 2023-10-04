FROM openjdk:17-alpine3.14
COPY target/inventory-1.0.0.jar java-app.jar
RUN mkdir -p /imagenes/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "java-app.jar"]