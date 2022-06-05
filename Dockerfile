FROM openjdk:17-alpine
RUN mkdir /opt/app
COPY console.jar /opt/app/
RUN ln -s /opt/app/console.jar console
CMD ["java", "-jar", "console"]
