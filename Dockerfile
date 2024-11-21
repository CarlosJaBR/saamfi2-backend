# Etapa 1: Construcción
FROM maven:3.8.6-openjdk-11 AS builder

# Directorio raíz
WORKDIR /source

# Copiar todos los archivos al contenedor
ADD . /source

# Hacer ejecutable el script mvnw
RUN chmod +x mvnw

# Añadir la dependencia ojdbc6 a Maven
ADD libs/ojdbc6-11.2.0.3.jar /source/libs/ojdbc6-11.2.0.3.jar
RUN mvn install:install-file \
    -Dfile=/source/libs/ojdbc6-11.2.0.3.jar \
    -DgroupId=oracle \
    -DartifactId=ojdbc6 \
    -Dversion=11.2.0.3 \
    -Dpackaging=jar

# Construir el proyecto y saltar las pruebas
RUN mvn clean install -Dmaven.test.skip=true

# Etapa 2: Ejecución
FROM openjdk:11-jre-slim

# Directorio para la aplicación
WORKDIR /application

# Copiar el archivo WAR o JAR desde la etapa anterior
COPY --from=build /app/saamfi-rest/target/saamfiapi.war /app/saamfi-backend.war

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/saamfi-backend.war"]