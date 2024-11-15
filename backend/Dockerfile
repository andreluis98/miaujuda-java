# Usar a imagem base do Maven com JDK 21 para compilar o projeto
FROM maven:3.8.7-openjdk-21-slim AS build

# Configurar o diretório de trabalho para o build
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências do projeto
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código do projeto para o container
COPY src ./src

# Compilar o projeto e gerar o arquivo JAR
RUN mvn clean package -DskipTests

# Verificar se o JAR foi gerado corretamente
RUN echo "Verificando o conteúdo do diretório target:"
RUN ls -l /app/target

# Segunda etapa: usar uma imagem menor apenas para execução do JAR
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado da etapa anterior para a imagem final
# Usando coringa para garantir que qualquer JAR gerado seja copiado corretamente
COPY --from=build /app/target/*.jar backend.jar

# Verificar se o JAR foi copiado corretamente
RUN echo "Verificando se o arquivo JAR foi copiado corretamente:"
RUN ls -l /app

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "backend.jar"]
