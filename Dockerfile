FROM adoptopenjdk/openjdk16:jre-nightly

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    SLEEP=0 \
    JAVA_OPTS=""

WORKDIR /app
COPY app/build/libs/app.jar ./app.jar

CMD java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar
