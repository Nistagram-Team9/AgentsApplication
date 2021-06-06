FROM maven:3.6.3-ibmjava-8-alpine AS agentReportsBuild
ARG STAGE=dev
WORKDIR /usr/src/agentreports
COPY . .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:8-jdk-alpine AS agentReportsRuntime
WORKDIR /app
COPY --from=agentReportsBuild /usr/src/agentreports/target/agentReports.jar ./
EXPOSE 8080
CMD java -jar agentReports.jar