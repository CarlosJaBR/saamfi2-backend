# Etapa 1: Construcción
FROM maven:3.8.6-openjdk-11 AS builder

# Establecer el directorio de trabajo
WORKDIR /source

# Copiar el código fuente de la aplicación al contenedor
COPY . .

# Hacer que el script mvnw sea ejecutable (si usas Maven Wrapper)
RUN chmod +x mvnw

# Copiar e instalar el JAR personalizado (ojdbc6) en el repositorio local de Maven
COPY libs/ojdbc6-11.2.0.3.jar /source/libs/ojdbc6-11.2.0.3.jar
RUN mvn install:install-file \
    -Dfile=/source/libs/ojdbc6-11.2.0.3.jar \
    -DgroupId=oracle \
    -DartifactId=ojdbc6 \
    -Dversion=11.2.0.3 \
    -Dpackaging=jar

# Compilar y empaquetar la aplicación Spring Boot, sin ejecutar pruebas
RUN mvn clean install -Dmaven.test.skip=true

# Etapa 2: Ejecución
FROM openjdk:11-jre-slim

# Establecer el directorio de trabajo
WORKDIR /application

# Copiar el archivo WAR generado en la etapa de construcción
COPY --from=builder /source/saamfi-rest/target/saamfiapi.war /application/saamfi-backend.war

# Exponer el puerto para la aplicación
EXPOSE 8080

# Ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "/application/saamfi-backend.war"]