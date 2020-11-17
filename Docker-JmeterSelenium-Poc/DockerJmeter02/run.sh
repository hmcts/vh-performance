#!/bin/bash -e

# override the HEAP settings and run the jmeter script.
JVM_ARGS="-Xms512m -Xmx2048m" jmeter -n -t /opt/apache-jmeter-5.2/bin/examples/CSVSample.jmx -l /jmeter.jtl 2>&1
java -jar /opt/apache-jmeter-5.2/lib/cmdrunner-2.2.jar --tool Reporter --plugin-type AggregateReport --input-jtl /jmeter.jtl --generate-csv /results/results.csv 2>&1
cat /results/results.csv