FROM openjdk:8-jdk-alpine
VOLUME /tmp
RUN bin/bash -c echo pwd
COPY ../../module/kite-tick/ /home/
RUN ls -ltr
RUN PWD
ENTRYPOINT ["java","-cp","kite-tick.jar"]