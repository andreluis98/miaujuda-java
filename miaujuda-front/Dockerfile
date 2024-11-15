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
FROM node:18-buster-slim AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos package.json e package-lock.json para o container
COPY ./miaujuda-front/package*.json /app/

# Instale as dependências do projeto Angular
RUN npm install --legacy-peer-deps

# Copie todo o código do projeto para o diretório /app no container
COPY . /app/

# Construa o projeto Angular em modo de produção
RUN npm run build --prod

# Etapa 2: Configurar o Nginx para servir os arquivos estáticos
FROM nginx:alpine

# Remova a configuração padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copie os arquivos gerados pela build do Angular para o Nginx
COPY --from=build /app/dist/miaujuda-front/ /usr/share/nginx/html/

# Exponha a porta 80 para o Nginx
EXPOSE 80

# Comando para rodar o Nginx no container
CMD ["nginx", "-g", "daemon off;"]


