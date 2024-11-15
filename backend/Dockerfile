# Usando a imagem base do OpenJDK
FROM openjdk:21-jdk-slim AS build

# Atualizando o sistema e instalando o Maven
RUN apt-get update && apt-get install -y maven

# Definindo o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo pom.xml
COPY backend/pom.xml /app/pom.xml

# Copiar o código-fonte
COPY backend/src /app/src

# Compilando o aplicativo com Maven
RUN mvn clean package -DskipTests -U

# Definindo a imagem final para execução
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]
