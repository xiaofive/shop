FROM java:8
VOLUME /tmp
ADD shop-wms-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 1111
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/app.jar"]


