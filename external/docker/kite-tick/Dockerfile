FROM bash:4.4
RUN mkdir -p /home/zerodha-kite/tick
RUN cd /home && chmod -R 755 zerodha-kite
ARG JAR_FILE
RUN cd /
COPY /F_DRIVE/reconnect/java/zerodha-kite/code/zerodha-kite/kite-tick/target/kite-tick-0.0.1-SNAPSHOT.jar /home/zerodha-kite/tick/
WORKDIR /home/zerodha-kite/tick/