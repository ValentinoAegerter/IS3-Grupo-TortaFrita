FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
RUN apk add --no-cache bash curl git
ENTRYPOINT ["tail", "-f", "/dev/null"]