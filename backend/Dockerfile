# Etapa 1: Usar a imagem do OpenJDK 21 e instalar o Maven manualmente
FROM openjdk:21-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho para o build
WORKDIR /app

# Copiar o arquivo pom.xml para o container
COPY pom.xml /app/pom.xml

# Verificar o conteúdo do diretório após copiar o pom.xml
RUN ls -l /app

# Baixar as dependências do projeto
RUN mvn dependency:go-offline

# Copiar o diretório src do contexto atual para o container
COPY src /app/src

# Verificar o conteúdo do diretório após copiar o src
RUN ls -l /app/src

# Compilar o projeto e gerar o arquivo JAR
RUN mvn clean package -DskipTests -U

# Etapa 2: Usar uma imagem mais leve para executar a aplicação com OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado da etapa anterior para a imagem final
COPY --from=build /app/target/*.jar backend.jar

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "backend.jar"]
