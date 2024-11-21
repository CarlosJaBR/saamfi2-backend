# Etapa 1: Construcción
FROM maven:3.8.6-openjdk-11 AS builder

# Copiar el código fuente
WORKDIR /source
ADD . /source

RUN chmod +x mvnw

# Añadir la dependencia ojdbc6 a Maven
ADD libs/ojdbc6-11.2.0.3.jar /source/libs/ojdbc6-11.2.0.3.jar
RUN mvn install:install-file \
    -Dfile=/source/libs/ojdbc6-11.2.0.3.jar \
    -DgroupId=oracle \
    -DartifactId=ojdbc6 \
    -Dversion=11.2.0.3 \
    -Dpackaging=jar

# Compilar el proyecto
RUN mvn package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:11-jre-slim

WORKDIR /application

# Copiar el archivo WAR desde la etapa de construcción
COPY --from=builder /source/target/saamfiapi.war /application/app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/application/app.war"]