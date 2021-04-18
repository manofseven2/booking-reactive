FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir /data
RUN mkdir /config
COPY src/main/resources/config.properties /config/config.properties

RUN cd /
EXPOSE 8081
EXPOSE 1616
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom", "-Djava.rmi.server.hostname=127.0.0.1", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=1616", "-Dcom.sun.management.jmxremote.rmi.port=1616", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.authenticate=false","-Dconfig.properties.location=file:/config/config.properties","-jar","/app.jar"]
