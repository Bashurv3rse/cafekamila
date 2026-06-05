# Paso 1: Compilar la aplicación utilizando Maven y Java 25
FROM maven:3-eclipse-temurin-25 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación con el entorno de ejecución de Java 25
FROM eclipse-temurin:25-jre
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
