# Miaujuda - Projeto Angular e Java

Este é um sistema completo desenvolvido com **Angular** no frontend e **Java Spring Boot** no backend para o gerenciamento de animais abandonados. O projeto permite registrar, visualizar e gerenciar informações sobre animais e suas localizações.

## Tecnologias

### Frontend (Angular)

- [Angular](https://angular.io/)
- [TypeScript](https://www.typescriptlang.org/)
- [RxJS](https://rxjs.dev/)
- [Bootstrap](https://getbootstrap.com/) (opcional)
- [Google Maps API](https://developers.google.com/maps) (para localização de endereços)

### Backend (Java Spring Boot)

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data)
- [PostgreSQL](https://www.postgresql.org/)
- [Spring Security](https://spring.io/projects/spring-security) (para autenticação)
- [Docker](https://www.docker.com/) (para containerização)

## Instalação

### Backend (Java Spring Boot)

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/seu-usuario/miaujuda.git
   cd miaujuda
   ```

2. **Instale as dependências (backend):**

   Se você estiver usando Maven:

   ```bash
   mvn clean install
   ```

   Ou, se for Gradle:

   ```bash
   gradle build
   ```

3. **Configure o banco de dados PostgreSQL**:

   No `application.properties` ou `application.yml`, configure a URL do banco de dados, usuário e senha:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/miaujuda
   spring.datasource.username=usuario
   spring.datasource.password=senha
   ```

4. **Inicie o servidor backend:**

   Se você estiver usando Maven:

   ```bash
   mvn spring-boot:run
   ```

   Ou, com Gradle:

   ```bash
   gradle bootRun
   ```

   O servidor backend estará disponível em [http://localhost:8080](http://localhost:8080).

### Frontend (Angular)

1. **Clone o repositório frontend (caso esteja em repositórios separados):**

   ```bash
   git clone https://github.com/seu-usuario/miaujuda-frontend.git
   cd miaujuda-frontend
   ```

2. **Instale as dependências (frontend):**

   ```bash
   npm install
   ```

3. **Inicie o servidor de desenvolvimento:**

   ```bash
   ng serve
   ```

   O frontend estará disponível em [http://localhost:4200](http://localhost:4200).

## Estrutura do Projeto

### Backend (Java Spring Boot)

- `src/main/java/com/miaujuda/`: Contém os componentes principais do backend, como entidades, controladores, serviços e repositórios.
- `src/main/resources/`: Contém o arquivo `application.properties` para configuração e recursos estáticos.

### Frontend (Angular)

- `src/app/`: Contém todos os componentes, serviços e módulos do frontend.
- `src/assets/`: Contém os arquivos estáticos (imagens, fontes, etc.).
- `src/environments/`: Contém os arquivos de configuração para diferentes ambientes (desenvolvimento, produção).

## Funcionalidades

### Backend

- **Cadastro de usuários**: Permite a criação, atualização, e exclusão de usuários.
- **Cadastro de animais**: Permite a criação, visualização e atualização de registros de animais abandonados.
- **Autenticação**: Utiliza Spring Security para autenticação de usuários com JWT.

### Frontend

- **Página de login**: Tela de autenticação do usuário.
- **Formulário de cadastro de animais**: Permite a criação de novos registros de animais.
- **Visualização de animais**: Exibe os animais cadastrados no sistema.
- **Google Maps**: Utilizado para buscar e exibir o endereço do animal.
