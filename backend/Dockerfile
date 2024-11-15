# Etapa 1: Usar a imagem do OpenJDK 21 e instalar o Maven manualmente
FROM openjdk:21-jdk-slim AS build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Configurar o diretório de trabalho para o build
WORKDIR /app

# Copiar o arquivo pom.xml para o container
COPY ./pom.xml ./pom.xml

# Baixar as dependências do projeto
RUN mvn dependency:go-offline

# Verificar se as dependências foram baixadas corretamente
RUN echo "Verificando as dependências Maven:"
RUN ls -l /root/.m2/repository

# Copiar o código do projeto para o container
COPY ./src ./src

# Compilar o projeto e gerar o arquivo JAR
RUN mvn clean package -DskipTests -U

# Verificar se o JAR foi gerado corretamente
RUN echo "Verificando o conteúdo do diretório /app/target no Render:"
RUN ls -l /app/target

# Etapa 2: Usar uma imagem mais leve para executar a aplicação com OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho para a aplicação
WORKDIR /app

# Copiar o JAR gerado da etapa anterior para a imagem final
COPY --from=build /app/target/*.jar backend.jar

# Verificar se o JAR foi copiado corretamente
RUN echo "Verificando se o arquivo JAR foi copiado corretamente:"
RUN ls -l /app

# Garantir permissões adequadas
RUN chmod -R 755 /app

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "backend.jar"]
