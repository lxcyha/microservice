FROM openjdk:8-jre
MAINTAINER lxcyha lxcyha@gmail.com

COPY ../target/user-thrift-service-1.0-SNAPSHOT.jar /user-service.jar

ENTRYPOINT ["java","-jar","/user-service.jar"]