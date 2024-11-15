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


# Usar uma imagem mínima
FROM node:18-buster-slim

# Definir o diretório de trabalho no container
WORKDIR /usr/src/app

# Copiar o arquivo package.json e package-lock.json para instalar as dependências
COPY package*.json ./

# Instalar dependências
RUN npm install

# Copiar os demais arquivos para o container
COPY . .

# Confirmar que o arquivo angular.json está presente (opcional)
RUN ls -la angular.json

# Construir a aplicação Angular
RUN npm run build -- --output-path=dist

# Instalar o servidor para servir os arquivos estáticos
RUN npm install -g http-server

# Expor a porta 4200
EXPOSE 4200

# Comando de execução para o servidor
CMD ["http-server", "dist", "-p", "4200"]











