# Projeto API — Pneus e Veículos

API REST para gerenciamento de **veículos** e **pneus**, permitindo cadastro, consulta e controle de vinculação entre eles.

Projeto desenvolvido como teste técnico para demonstrar boas práticas de:

- Arquitetura em camadas  
- Padrões REST
- Testes automatizados
- Versionamento de banco com Flyway
- Containerização com Docker

---

## Tecnologias utilizadas

- linguagem: Java 17  
- Framework: Spring Boot 3  
- Persistência: Spring Data JPA
- Banco de dados: PostgreSQL
- Banco para testes: H2
- Migração de banco: Flyway
- Mapeamento DTO: MapStruct
- Documentação: OpenAPI / Swagger
- Testes: JUnit 5, Mockito
- Testes E2E: SpringBootTest + RestTemplate
- Containerização: Docker + Docker Compose
- Build: Maven
  
---
## Arquitetura do projeto

controller  →  service  →  repository  →  database
        ↓
       dto
        ↓
      mapper

### Camadas
- Controller: Exposição dos endpoints REST
- Service: Regras de negócio
- Repository: Acesso a dados
- Model: Entidades JPA
- DTO: Objetos de transporte
- Mapper: Conversão entidade ↔ DTO
  
---

## Funcionalidades
**Veículos**
- Cadastro de veículos
- Listagem paginada
- Consulta veículo por ID

**Pneus**
- Cadastro de pneus
- Listagem paginada
- Vincular pneu a veículo
- Desvincular pneu de veículo
  
---

## Endpoints principais
### Veículos
Método | Endpoint | Descrição
--- | --- | ---
GET | `/api/v1/veiculo` | Lista veículos paginados
GET | `/api/v1/veiculo/{id}` | Busca veículo por ID
POST | `/api/v1/veiculo` | Cria novo veículo

### Pneus
Método | Endpoint | Descrição
--- | --- | ---
GET | `/api/v1/pneu` | Lista pneus paginados
POST | `/api/v1/pneu/cadastrar` | Cadastra novo pneu
PUT | `/api/v1/pneu/vincular` | Vincula pneu a veículo
PUT | `/api/v1/pneu/desvincular` | Desvincula pneu

---

## Documentação da API
Após subir a aplicação:
```bash
http://localhost:8089/api/v1/swagger-ui.html
```

---

## Perfis de execução
Perfil | Banco de dados | Uso
--- | --- | ---
dev | H2 em memória | Desenvolvimento local
test | H2 em memória | Testes automatizados
prod | PostgreSQL | Produção

--- 

## Como rodar o projeto localmente
### Clonar o repositório

```bash
git clone https://github.com/jphsc/teste-projeto.git
cd teste-projeto
```

### Rodar o projeto com Maven
O projeto deve ser executado conforme o perfil desejado, sendo possível 3 perfils: dev, prod e test.
Obs: Os testes e2e só podem ser executados no profile test

- dev
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

- prod
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

- test
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

### Rodando o projeto com Docker
***Subir aplicação e banco***
```bash
docker-compose up --build
```

A aplicação ficará disponível em:
```bash
http://localhost:8089/api/v1
```

## Testes
### Rodar todos os testes
```bash
mvn test
```

### Tipos de testes
Tipo | Descrição
--- | ---
Unitários | Testes isolados de service e utilitários
E2E | Testes de fluxo completo via HTTP

---

## Tratamento de erros
A API possui
- Exception global
- Padronização de respostas de erro
- Códigos de erro de negócio

Exemplo de resposta:
```bash
{
  "codigoErro": 200,
  "descricaoErro": "Pneu não encontrado"
}
```

---

## Autor
### Rafael Costa

Desenvolvedor Java

---

## Licença
Projeto desenvolvido para fins de avaliação técnica.
Uso livre para estudos e demonstrações.

