# First Stage
# ...Build project...
FROM maven:3.8.6-openjdk-11 AS builder

# workspace
WORKDIR /source

# Copy code on image
COPY . .

# Use maven command
RUN chmod +x mvnw

# Copy and install JAR file ojdbc6 on Maven local repository
COPY libs/ojdbc6-11.2.0.3.jar /source/libs/ojdbc6-11.2.0.3.jar
RUN mvn install:install-file \
    -Dfile=/source/libs/ojdbc6-11.2.0.3.jar \
    -DgroupId=oracle \
    -DartifactId=ojdbc6 \
    -Dversion=11.2.0.3 \
    -Dpackaging=jar

# clean and install with maven
RUN mvn clean install -Dmaven.test.skip=true

# Second stage:
#...Execute...
FROM openjdk:11-jre-slim

# workspace
WORKDIR /application

# copy war file
COPY --from=builder /source/saamfi-rest/target/saamfiapi.war /application/saamfi-backend.war

# Expose port 8080
EXPOSE 8080

# Execute Spring Boot app
CMD ["java", "-jar", "/application/saamfi-backend.war"]