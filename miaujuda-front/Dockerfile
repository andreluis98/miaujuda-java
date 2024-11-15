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


# Use uma imagem do Node.js com a versão específica para garantir compatibilidade
FROM node:18-alpine

# Definir o diretório de trabalho no contêiner
WORKDIR /usr/src/app

# Copiar os arquivos de dependências para o contêiner
COPY package*.json ./

# Limpar o cache do npm e instalar as dependências
RUN npm cache clean --force
RUN npm ci --legacy-peer-deps

# Instalar o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiar o restante dos arquivos do projeto
COPY . .

# Construir o projeto Angular para produção
RUN npm run build --prod

# Instalar o http-server para servir os arquivos estáticos
RUN npm install -g http-server

# Expôr a porta 8080 para servir a aplicação
EXPOSE 8080

# Comando para rodar o servidor HTTP e servir os arquivos Angular
CMD ["http-server", "dist/"]




