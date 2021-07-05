FROM maven:3.6.3-ibmjava-8-alpine AS productServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/productservice
COPY . .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:8-jdk-alpine AS productServiceRuntime
WORKDIR /app
COPY --from=productServiceBuild /usr/src/productservice/target/productService.jar ./
EXPOSE 8080
CMD java -jar productService.jar