FROM openjdk:21-jdk-slim
WORKDIR /app
#COPY linear/rest/restLinear-core.jar /app/app.jar
#COPY linear/rest/restLinear-product-service.jar /app/app.jar
COPY linear/rest/restLinear-payment-service.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]