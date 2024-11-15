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


# Usar uma imagem mínima do Node.js
FROM node:18-buster-slim

# Crie o diretório de trabalho no container
WORKDIR /usr/src/app

# Copie os arquivos package.json e package-lock.json para o container
COPY miaujuda-front/package*.json ./

# Instale as dependências antes de copiar o restante dos arquivos
RUN npm install

# Copie o restante dos arquivos da aplicação para o container
COPY . .  

# Exponha a porta que a aplicação irá usar
EXPOSE 3000

# Comando para iniciar a aplicação
CMD ["npm", "start"]

