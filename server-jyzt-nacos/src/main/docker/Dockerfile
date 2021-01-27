FROM java:8
VOLUME /tmp
ADD server-jyzt-nacos-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8761
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/app.jar"]
