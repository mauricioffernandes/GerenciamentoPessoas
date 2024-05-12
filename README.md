## Gerenciamento de Pessoas - API REST

### Descrição do projeto<a id="descricao-do-projeto"></a>

---
O projeto tem como objetivo a realização da gerenciamento de pessoas via API Rest, que consiste nos CRUD's de Pessoa e Endereço.
É utilizado objetos JSON, para a realização das consistências em banco em memória (H2).

O projeto também conta com o uso do Sweggar para realizar os testes na api, além das opções do Insonmia ou Postman. 


### Tabela de conteúdos

---
* [Descrição do projeto](#descricao-do-projeto)
* [Definições Técnicas](#definicoes-tecnicas)
* [Pré-requisitos](#pre-requisitos)
* [Versionamento do código](#versionamento-do-codigo)
* [Tecnologias](#tecnologias)
* [Build](#build)
* [Testes](#testes)
* [Consumo da API](#consumo-da-api)


### Definições Técnicas<a id="definicoes-tecnicas"></a>

---

### Pré-requisitos<a id="pre-requisitos"></a>

---
Antes de começar, você vai precisar ter instalado em sua máquina:
````
Java 17
Maven 3.9.6
````

Além disso, ter um editor para trabalhar com o código como Eclipse ou Intellij.

### Versionamento do código<a id="versionamento-do-codigo"></a>

---
O versionamento do código é feito através do Git.
O repositório está hospedado no GitHub.
- Comando para clonar o repositório: `git clone https://github.com/mauricioffernandes/GerenciamentoPessoas.git`

### Tecnologias<a id="tecnologias"></a>

---
As seguintes tecnologias foram usadas na construção do código do projeto:
```
  Java 17
  Maven 3.9.6
  Spring Boot 3.2.5
  JUnit 5
  Jacoco 0.8.7
  Swagger 3.0.0
```

### Build<a id="build"></a>

---
O build do projeto (sem a execução de testes unitários) é feito com o Maven através do comando `mvn clean install -DskipTests`.

### Testes<a id="testes"></a>

---
Os testes unitários foram implementados utilizando JUnit 5 e Mockito, além do Jacoco para geração de relatório de cobertura de testes.

- Testes Unitários
    - Para executar os testes unitários com a geração do relatório pelo Jacoco, use o comando `mvn test jacoco:report -DskipTests=false`.
    - O relatório de cobertura de testes é gerado localmente em `target/site/jacoco/index.html`.

### Consumo da API<a id="consumo-da-api"></a>

---
Para consumir a API, basta acessar o Swagger através da URL `http://localhost:8081/swagger-ui.html`.

Ou se preferir, pode utilizar o Insomnia ou Postman para realizar as requisições Get, Post e Put para:
- http://localhost:8081/pessoa  
- http://localhost:8081/endereco 