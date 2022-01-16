FROM maven:3.8.1-jdk-8
ADD target/shared.jar shared.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","shared.jar"]