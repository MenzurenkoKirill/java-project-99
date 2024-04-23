FROM gradle:8.4-jdk20

WORKDIR /app

COPY . .

RUN gradle installDist

CMD java -jar build/libs/app-0.0.1-SNAPSHOT.jar

EXPOSE 8080