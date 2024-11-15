# FROM node:alpine

# Defina o diretório de trabalho no container
# WORKDIR /usr/src/app

# Copie o package.json e o package-lock.json primeiro para instalar as dependências
# COPY package*.json /usr/src/app/

# Instale as dependências do projeto
# RUN npm install --legacy-peer-deps

# Copie o restante dos arquivos da aplicação
# COPY . /usr/src/app

# Instale o Angular CLI localmente, se necessário
# RUN npm install @angular/cli

# Exponha a porta usada pela aplicação Angular
# EXPOSE 4200

# Comando para rodar o Angular na rede externa
# CMD ["ng", "serve", "--host", "0.0.0.0"]


# Use a imagem base do Node.js
FROM node:18-buster-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos package.json e package-lock.json para o container
COPY ./miaujuda-front/package*.json /app/

# Instale as dependências do projeto Angular
RUN npm install --legacy-peer-deps

# Copie todo o código do projeto para o diretório /app no container
COPY . /app/

# Exponha a porta 4200 que o Angular CLI usa por padrão
EXPOSE 4200

# Defina o comando para iniciar o servidor Angular
CMD ["npm", "start"]

