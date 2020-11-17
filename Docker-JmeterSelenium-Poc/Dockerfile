from justb4/jmeter:5.3
RUN apk update && apk add --no-cache bash \
        chromium \
        chromium-chromedriver
copy lib /opt/apache-jmeter-5.3/lib
copy JmeterScripts /opt/apache-jmeter-5.3/bin
WORKDIR /opt/apache-jmeter-5.3/bin