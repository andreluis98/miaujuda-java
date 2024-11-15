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


# Use uma versão estável do Node.js com a versão 18 ou superior
FROM node:18-alpine

# Definir o diretório de trabalho no contêiner
WORKDIR /usr/src/app

# Copiar os arquivos de dependências para o contêiner
COPY package*.json ./

# Instalar dependências do projeto
RUN npm install --legacy-peer-deps

# Instalar o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiar o restante dos arquivos do projeto para o contêiner
COPY . .

# Expôr a porta que o servidor Angular irá rodar
EXPOSE 4200

# Comando para rodar o servidor Angular
CMD ["ng", "serve", "--host", "0.0.0.0"]





