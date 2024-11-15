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


# Use uma imagem do Node.js
FROM node:18-buster-slim

# Crie o diretório de trabalho
WORKDIR /app

# Copie os arquivos do projeto Angular para o container
COPY . .

# Instale as dependências do projeto
RUN npm install

# Faça o build do projeto Angular
RUN npm run build --prod

# Instale o http-server para servir os arquivos estáticos
RUN npm install -g http-server

# Exponha a porta 8080 (ou a porta que você deseja)
EXPOSE 8080

# Comando para rodar o http-server e servir os arquivos
CMD ["http-server", "dist/miaujuda-front", "-p", "8080"]
