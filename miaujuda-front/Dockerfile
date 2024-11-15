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


# Defina a imagem base
FROM node:18-buster-slim

# Crie e defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos do projeto Angular para o container
COPY /miaujuda-front/package*.json /app/
RUN npm install --legacy-peer-deps

# Copie o restante dos arquivos do projeto para o container
COPY . /app/

# Exponha a porta para a aplicação Angular
EXPOSE 4200

# Comando para iniciar o servidor Angular
CMD ["npm", "start"]

