FROM alpine

RUN apk --update add wget
RUN wget http://mirror.switch.ch/mirror/apache/dist//jmeter/binaries/apache-jmeter-3.0.tgz
RUN tar zxvf apache-jmeter-3.0.tgz
RUN apk del wget
RUN rm -f apache-jmeter-3.0.tgz
RUN rm -fr /apache-jmeter-3.0/docs

VOLUME /apache-jmeter-3.0

CMD ["/bin/true"]