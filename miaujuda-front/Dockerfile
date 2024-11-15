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


# Usar a imagem oficial do Node.js
FROM node:18-buster-slim

# Definir o diretório de trabalho no container
WORKDIR /app

# Copiar apenas o package.json e package-lock.json primeiro
COPY /miaujuda-front/package*.json /app/

# Limpar o cache e instalar as dependências do projeto
RUN npm cache clean --force && npm install --legacy-peer-deps

# Copiar o restante dos arquivos da aplicação
COPY . /app/

# Expor a porta para o aplicativo
EXPOSE 8080

# Comando para rodar o aplicativo
CMD ["npm", "start"]
