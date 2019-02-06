FROM openjdk:8u121-jdk-alpine
ARG USER=root
ARG VERSION=0.0.1-SNAPSHOT
ARG TICK_JAR_LOCATION=kite-tick/target
ARG TICK_PROPERTIES_RESOURCE=external/module/kite-tick/application.properties

RUN echo $TICK_JAR_LOCATION
RUN mkdir -p /home/zerodha-kite/tick
RUN cd /home && chmod -R 755 zerodha-kite/tick

ADD $TICK_JAR_LOCATION/kite-tick-$VERSION.jar /home/zerodha-kite/tick/kite-tick.jar 
WORKDIR /home/zerodha-kite/tick/
