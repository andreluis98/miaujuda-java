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





# Etapa 1: Construir o projeto Angular
FROM node:18 AS build

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar apenas o package.json e package-lock.json
COPY miaujuda-front/package.json miaujuda-front/package-lock.json ./

# Verificar os arquivos copiados para o diretório /app
RUN ls -al /app

# Instalar as dependências do Node.js
RUN npm install

# Copiar o restante dos arquivos do projeto para o contêiner
COPY . .

# Construir o projeto Angular em modo de produção
RUN npm run build --prod

# Etapa 2: Configurar o Nginx para servir os arquivos estáticos
FROM nginx:alpine

# Remover o arquivo padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiar os arquivos de build para o diretório de conteúdo estático do Nginx
COPY --from=build /app/dist/miaujuda-front /usr/share/nginx/html

# Expor a porta 80 para tráfego HTTP
EXPOSE 80

# Iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]

