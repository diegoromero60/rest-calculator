# use a node base image
FROM openjdk:8-jre-alpine

# set maintainer
LABEL maintainer "diegoromero60@gmail.com"

# set a health check
HEALTHCHECK --interval=15s \
            --timeout=15s \
            CMD curl -f http://127.0.0.1:9001/health/status || exit 1
COPY target/app.jar .
# tell docker what port to expose
EXPOSE 9001
ENTRYPOINT ["java","-jar","-Dspring.data.mongodb.host=${HOST}", "-Djava.security.egd=file:/dev/./urandom","/app.jar"]