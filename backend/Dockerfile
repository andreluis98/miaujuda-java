# Etapa de Build
FROM openjdk:21-jdk-slim AS build

# Atualizando o sistema e instalando o Maven
RUN apt-get update && apt-get install -y maven

# Definindo o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo pom.xml
COPY pom.xml /app/pom.xml

# Copiar o código-fonte
COPY src /app/src

# Compilando o aplicativo
RUN mvn clean package -DskipTests -U

# Etapa de Execução
FROM openjdk:21-jdk-slim

# Definindo o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo JAR gerado pela etapa anterior
COPY --from=build /app/target/*.jar backend.jar

# Expondo a porta padrão do Spring Boot (geralmente 8080)
EXPOSE 8080

# Definindo o ponto de entrada para rodar o JAR
ENTRYPOINT ["java", "-jar", "backend.jar"]
