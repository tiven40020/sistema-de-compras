# Imagen base: Amazon Corretto 22 (JDK)
FROM amazoncorretto:22-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado por Maven
COPY target/sistema_compras-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que utiliza Spring Boot
EXPOSE 8080

# Comando que ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
