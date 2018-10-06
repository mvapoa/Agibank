FROM openjdk:8-jdk-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS="-Xmx124m -Xms124m"

ADD target/*.jar /app.jar

CMD mkdir /root/data \
    mkdir /root/data/in \
    mkdir /root/data/out

CMD java ${JAVA_OPTS} \
    -Djava.security.egd=file:/dev/./urandom -jar /app.jar