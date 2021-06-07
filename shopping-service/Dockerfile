FROM maven:3.6.3-ibmjava-8-alpine AS shoppingServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/shoppingservice
COPY . .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:8-jdk-alpine AS shoppingServiceRuntime
WORKDIR /app
COPY --from=shoppingServiceBuild /usr/src/shoppingservice/target/shoppingService.jar ./
EXPOSE 8080
CMD java -jar shoppingService.jar