# Usando a imagem base do OpenJDK
FROM openjdk:21-jdk-slim AS build

# Atualizando o sistema e instalando o Maven
RUN apt-get update && apt-get install -y maven

# Definindo o diretório de trabalho no container
WORKDIR /app

# Copiar o arquivo pom.xml para o diretório de trabalho
COPY pom.xml /app/pom.xml

# Copiar o código-fonte (src) para o diretório de trabalho
COPY src /app/src

# Compilando o aplicativo com Maven
RUN mvn clean package -DskipTests -U

# Definindo a imagem final para execução
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copiar o arquivo .jar compilado da imagem anterior
COPY --from=build /app/target/*.jar backend.jar

# Definindo o comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "backend.jar"]
