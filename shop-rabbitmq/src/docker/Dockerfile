FROM java:8
VOLUME /tmp
ADD shop-rabbitmq-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8771
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/app.jar"]


