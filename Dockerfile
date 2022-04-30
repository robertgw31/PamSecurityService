FROM openjdk:17-oracle
LABEL maintainer="robertw@senutech.com"
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://PostgreSQL:5432/pam
ENV SPRING_DATASOURCE_USERNAME=pam
ENV SPRING_DATASOURCE_PASSWORD=pam
COPY target/pam-security-app-1.0-SNAPSHOT.jar /pam-security-app.jar
COPY target/pam-security-app-1.0-SNAPSHOT-jar-with-dependencies.jar /dependencies.jar
COPY config/* /config/
ENTRYPOINT ["java","-jar","/pam-security-app.jar", "-classpath", "/dependencies.jar"]


