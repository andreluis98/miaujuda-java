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




# Usando a imagem oficial do Node.js com base no Alpine Linux (leve)
FROM node:18-alpine

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /usr/src/app

# Copiando os arquivos de dependências para dentro do contêiner
COPY miaujuda-front/package*.json ./

# Instalando as dependências
RUN npm install --legacy-peer-deps

# Instalando o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copiando todos os arquivos do diretório atual para o contêiner
COPY . .

# Rodando o build de produção do Angular
RUN ng build --prod

# Instalando o http-server para servir o app Angular de forma otimizada
RUN npm install -g http-server

# Expondo a porta que o app Angular vai rodar
EXPOSE 4200

# Comando para rodar o servidor HTTP e servir a versão otimizada do build
CMD ["http-server", "dist/miaujuda-front", "-p", "4200"]









