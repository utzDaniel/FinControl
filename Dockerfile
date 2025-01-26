# Use a imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Adicione o JAR da aplicação ao container
COPY target/fincontrol-0.0.1-SNAPSHOT.jar /fincontrol.jar

# Exponha a porta que a aplicação Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/fincontrol.jar"]
