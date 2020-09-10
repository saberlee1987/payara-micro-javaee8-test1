FROM qaware/zulu-alpine-payara-micro:8u242-5.201
COPY target/payara-micro-javaee8-test1-1.0-SNAPSHOT.war /opt/payara/deployments
