FROM openjdk:8
ADD target/springboot-discount-application.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]