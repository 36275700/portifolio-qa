# Automação de testes de Login - OrangeHRM

Projeto de automação de testes usando **Selenium WebDriver** e **JUunit 5**, aplicando o padrão **Page Object Model (POM)**, testando o fluxo de login da aplicação demo do OrangeHRM.

## Objetivo 

Automatizar cenários de testes de login (credenciais válidas e inválidas) que foram previamente documentados manualmente em User Stories e critérios de aceite (Gherkin) no projeto de testes manuais do OrangeHRM.

## Tecnologias utilizadas 

-**Java 17**
-**Selenium WebDriver 4.21.0**
-**JUnit 5 (Jupiter)**
-**Maven** (gerenciador de dependências)
-**WebDriverManager** (configuração automática do ChromeDriver)

## Arquitetura

O projeto segue o padrão **Page Object Model**, separando:
-**`pages/`** - representa as telas de Aplicação e suas ações (ex: `Login Page`)
-**`tests/`** - contém a lógica de teste e as verificações (ex: `Login Test`)

Essa separação facilita a manuntenção: se um elemento da tela mudar, só é preciso atualizar a classe `Page`, sem tocar nos testes

## Cenários testados

1. **Login com credenciais inválidas** - verifica se a mensagem "Invalid credentials" é exibida corretamente
2. **Login com credenciais válidas** - verifca se o Dashboard é exibido após autenticação bem-sucedida

## Evidências de falha

Em caso de falha em qualquer teste, um screenshot é salvo automaticamente na pasta `screenchots/`, facilitando o diagnostico do problema 

## Como executar

1. Clone o Repositório
2. Abra o projeto no Intellij IDEA (ou a sua IDE de preferência)
3. Aguarde o Maven baixar a dependências
4. Execute a classe `LoginTest` (botão direito > Run)

## Aplicação testada

 [OrangeHRM Demo]("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")

## Contexto

Este projeto complementa o projeto de testes manuais do OrangeHRM (documentação de User Stories, critérios de Gherkin e casos de teste), demostrando a evolução de testes manuais para testes automatizados.

