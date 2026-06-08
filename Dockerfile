FROM amazoncorretto:17-alpine

WORKDIR /app

COPY src ./src

RUN javac src/Evento.java

CMD ["java", "-cp", "src", "Evento"]
