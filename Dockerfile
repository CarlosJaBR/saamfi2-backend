#...Etapa 1...
# Build project
FROM maven:3.8.6-openjdk-11 AS builder
# root source
WORKDIR /source
# add files to contenedor
ADD . /source

RUN chmod +x mvnw

# add ojdbc6 dependency to maven
ADD libs/ojdbc6-11.2.0.3.jar /source/libs/ojdbc6-11.2.0.3.jar
RUN mvn install:install-file \
    -Dfile=libs/ojdbc6-11.2.0.3.jar \
    -DgroupId=oracle \
    -DartifactId=ojdbc6 \
    -Dversion=11.2.0.3 \
    -Dpackaging=jar \

# build project and skip some test
RUN mvn package -DskipTests

#...Etapa 2...
#Runtime
FROM openjdk:11-jre-slim

#directory to app
WORKDIR /application

# coppy war file of past stage
COPY --from=builder /source/saamfi-rest/target/saamfiapi.war /application/app.war

# expose port 8080
EXPOSE 8080

# commant to run the app
ENTRYPOINT ["java", "-jar", "/application/app.war"]