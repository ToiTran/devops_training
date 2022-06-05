FROM openjdk:17-alpine
RUN mkdir /opt/app
COPY *.jar /opt/app/
RUN ln -s /opt/app/*.jar console
CMD ["java", "-jar", "/opt/app/*.jar"]
