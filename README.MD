**API Utilizada:** [Rick and Morty API](https://rickandmortyapi.com/)
<br>
**Tema do Projeto:** Rick and Morty Akinator simplificado

Paleta de cores da interface/frontend: ![Paleta de cores](./AdobeColor-rick%20and%20morty.jpeg)

**Nível de dificuldade:** Médio-Fácil

---

# Documentação do Projeto: Rick and Morty Akinator Simplificado

Este documento descreve as especificações, regras, requisitos e estrutura do projeto desenvolvido em Java com Spring Boot, baseado no tema Rick and Morty e consumo da API oficial da série. O objetivo da aplicação é criar uma experiência de jogo interativa, inspirada no mecanismo de adivinhação de personagens, com uso de pistas, palpites e manutenção do estado da partida na sessão do usuário.

**API Utilizada:** [Rick and Morty API](https://rickandmortyapi.com)  
**Disciplina:** Programação Orientada a Objetos  
**Avaliação:** 3º Bimestre

---

## 1. Visão Geral do Projeto

O sistema consiste em uma aplicação web que sorteia aleatoriamente um personagem da API do Rick and Morty e apresenta pistas para o usuário tentar descobrir qual é o personagem escolhido. O jogo funciona em fluxo de perguntas e respostas, em que o usuário recebe dicas progressivas sobre o personagem oculto e tenta adivinhar o nome correto.

A aplicação deverá seguir a arquitetura MVC do Spring, utilizar Thymeleaf para renderização das páginas, persistir os dados da aplicação via JPA e utilizar recursos estáticos para imagem, fontes e elementos visuais do tema.

---

## 2. Objetivo

O objetivo principal do projeto é consolidar o uso dos conceitos de:

- Java
- Spring Boot
- MVC
- JPA
- Thymeleaf
- HTML/CSS
- Consumo de API externa
- Persistência em banco de dados
- Manipulação de sessão e estado do jogo

O jogo deve ser funcional, de interface simples e organizada, com foco na lógica e no uso correto dos recursos exigidos pela disciplina.

---

## 3. Requisitos do Projeto

### 3.1 Requisitos Funcionais

* **RF-001:** O sistema deve sortear um ID aleatório de personagem da API do Rick and Morty.
* **RF-002:** A aplicação deve realizar uma requisição HTTP para buscar os dados do personagem sorteado.
* **RF-003:** O sistema deve transformar os dados da API em informações úteis para o jogo, como pistas textuais.
* **RF-004:** O sistema deve exibir ao usuário uma pista por vez, sem repetir informações já mostradas.
* **RF-005:** O usuário deve conseguir informar um palpite com o nome do personagem.
* **RF-006:** A resposta do usuário deve ser validada de forma insensível a maiúsculas, minúsculas e acentos.
* **RF-007:** Se o usuário acertar corretamente, o jogo deve encerrar com vitória.
* **RF-008:** Se o usuário errar repetidamente, o jogo deve encerrar com derrota.
* **RF-009:** Ao final da partida, a aplicação deve mostrar uma tela de resultado com mensagem, imagem e opções de reinício.
* **RF-010:** O jogo deve permitir reiniciar uma nova rodada após o resultado.

### 3.2 Requisitos Não Funcionais

* **RNF-001:** A interface deve possuir contraste adequado e texto legível.
* **RNF-002:** Os elementos visuais e os textos devem estar organizados e acessíveis.
* **RNF-003:** Os dados da sessão do jogo devem ser mantidos no backend, evitando acesso indevido via inspeção de código HTML.
* **RNF-004:** O carregamento inicial da aplicação e a chamada à API devem ocorrer de forma eficiente.
* **RNF-005:** A arquitetura da aplicação deve respeitar o padrão MVC.

---

## 4. Fluxo de Funcionamento do Jogo

1. O usuário acessa a página inicial.
2. A aplicação apresenta o tema e o botão para iniciar o jogo.
3. O sistema sorteia um personagem aleatório da API.
4. Os dados do personagem são salvos em um objeto Java para uso pela lógica do jogo.
5. O sistema gera pistas a partir de informações relevantes do personagem, como:
   - status
   - espécie
   - gênero
   - origem
   - localização
6. A cada tentativa ou etapa, uma nova pista é exibida ao usuário.
7. O jogador envia um palpite.
8. O sistema compara o palpite com o nome do personagem escolhido.
9. Se o palpite estiver correto, o jogador vence.
10. Se o palpite estiver errado, o jogo continua até atingir a regra de derrota ou o acerto.
11. Ao fim, a aplicação redireciona para a tela de resultado.
12. O usuário pode jogar novamente, iniciando uma nova partida.

---

## 5. Lógica de Jogo

A dinâmica do projeto pode ser entendida da seguinte forma:

- Um número aleatório é gerado e usado como ID da API.
- A aplicação realiza a requisição para buscar o personagem correspondente.
- O JSON retornado pela API é convertido em objeto Java.
- A partir dos atributos do personagem, são geradas frases ou pistas para o jogador.
- A pontuação ou condição de vitória/derrota é calculada conforme o fluxo definido no projeto.
- O jogo pode seguir o modelo de perguntas e respostas simples, com o usuário tentando identificar o personagem secretamente escolhido.

### Exemplo de lógica

- O personagem sorteado é salvo em um objeto.
- A partir desse objeto, são criadas pistas como:
  - "Este personagem é da espécie X"
  - "Seu status é Y"
  - "Ele é do gênero Z"
  - "Sua origem é W"
- O sistema compara o palpite do usuário com o nome real do personagem.
- Se o usuário acertar, a partida termina com vitória.
- Se ele errar, o sistema pode continuar ou encerrar conforme as regras do jogo.

---

## 6. Regras da Partida

### Regra de vitória

O usuário vence quando acerta corretamente o nome do personagem oculto dentro do limite de jogo estabelecido.

### Regra de derrota

O usuário perde quando:

- acumula um número de erros maior que o permitido; ou
- esgota as pistas disponíveis sem acertar; ou
- atinge a condição definida no fluxo do jogo.

### Mensagens previstas

- Vitória: mensagem de confirmação do acerto, como "Boa NERDOLA".
- Derrota: mensagem de falha, como "you suck".

---

## 7. Estrutura das Telas

### 7.1 home.html

Tela inicial da aplicação, com:

- introdução do jogo
- descrição das regras
- botão para iniciar a partida
- elementos visuais e tema da série
- imagem animada ou GIF posicionada ao lado direito da tela

A tela deve possuir layout organizado, com centralização dos elementos principais e os componentes visualmente alinhados ao tema Rick and Morty.

### 7.2 perguntas.html

Tela de partida, com:

- pista atual exibida ao usuário
- botão ou campo para resposta
- possibilidade de envio do palpite
- imagem ou visual auxiliar ao lado direito da tela
- fluxo de interação com a lógica do jogo

### 7.3 resultado.html

Tela final com:

- mensagem de vitória ou derrota
- nome do personagem oculto
- imagem do personagem
- animação/GIF relacionado ao resultado
- botão para jogar novamente

---

## 8. Identidade Visual

### 8.1 Paleta de Cores

A interface deve seguir uma estética inspirada na série:

- **Fundo geral:** `#202329`
- **Container de pistas:** `#22A2C4`
- **Botões e destaques:** `#A2E8DD`
- **Texto principal:** `#FFFFFF`

A paleta do projeto já está representada na imagem do repositório:

`./AdobeColor-rick%20and%20morty.jpeg`

### 8.2 Tipografia

A fonte principal do projeto deve estar localizada na pasta de recursos estáticos do projeto. A ideia é manter o aspecto visual do tema Rick and Morty na interface, combinando uma fonte temática com textos legíveis.

### 8.3 Acessibilidade

- contraste adequado entre fundo e texto
- tamanho mínimo de fonte adequado para leitura
- organização visual na tela
- posicionamento claro dos elementos e ações do usuário

---

## 9. Arquitetura do Projeto

### 9.1 Backend

A aplicação deve ser desenvolvida em Java com Spring Boot, seguindo o padrão MVC.

Estrutura esperada:

- **Controller**: responsável por receber requisições e encaminhar para a view ou lógica correspondente
- **Model/Entity**: representa os dados da aplicação e do jogo
- **Repository**: responsável pela persistência de dados com JPA
- **Service**: opcional para organização da lógica de negócio, caso desejado

### 9.2 Frontend

A interface deverá ser renderizada com Thymeleaf e HTML, de forma dinâmica, com conteúdo gerado conforme os dados da sessão do usuário.

### 9.3 Persistência

A entidade principal do sistema deve ser persistida em banco de dados com uso de JPA e `JpaRepository`, conforme exigido pela avaliação.

---

## 10. Integração com API Externa

A aplicação utilizará a API pública do Rick and Morty para obter dados de personagens.

### Fluxo esperado

1. Usuário dispara uma ação dentro da aplicação.
2. A aplicação consulta a API externa.
3. Recebe o JSON de resposta.
4. Converte os dados em objetos Java.
5. Utiliza as informações de forma significativa na lógica do jogo.

**Importante:** a integração não pode se resumir apenas a mostrar o JSON bruto. Os dados devem ser usados para construir as pistas, validar entrada do usuário e definir o estado do jogo.

---

## 11. Estrutura do Repositório

```text
ApiSpringConsumer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/
│   │   │       └── edu/
│   │   │           └── ifpr/
│   │   │               └── ApiSpringConsumer/
│   │   │                   ├── controller/
│   │   │                   ├── model/
│   │   │                   ├── repository/
│   │   │                   └── ApiSpringConsumerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       │   ├── images/
│   │       │   └── fonts/
│   │       └── templates/
│   └── test/
│       └── java/
├── pom.xml
├── README.MD
├── AdobeColor-rick and morty.jpeg
├── readme_avalia_o_3_bimestre_poo.md
├── mvnw
├── mvnw.cmd
└── HELP.md
```

---

## 12. Recursos Estáticos do Projeto

O repositório já contém diversos elementos visuais e recursos que podem ser utilizados na aplicação, como:

- paleta de cores do tema
- imagem do projeto
- GIFs de tela inicial e resultados
- imagens de personagens e perguntas
- fonte temática da série

Esses recursos devem ser organizados dentro da pasta `src/main/resources/static/` para uso pela aplicação.

---

## 13. Critérios de Avaliação

A avaliação do trabalho segue os critérios da disciplina:

| Critério | Valor |
| :--- | :---: |
| Modelagem da entidade e aplicação dos conceitos de POO | 2,0 |
| JPA, Repository e persistência de dados | 1,0 |
| CRUD funcionando | 2,0 |
| Organização MVC e uso do Thymeleaf | 1,0 |
| Consumo e utilização significativa da API externa | 2,0 |
| Organização, apresentação e domínio do projeto | 2,0 |
| **TOTAL** | **10,0** |

---

## 14. Observações Importantes

- O projeto deve respeitar o documento de avaliação do 3º bimestre.
- O README deve ser uma documentação clara do sistema e do tema.
- O uso da API externa deve ser funcional e visível para o usuário.
- A aplicação deve manter o padrão da arquitetura MVC.
- O tema deve seguir o contexto do Rick and Morty, mas sem perder funcionalidade e organização.

---

## 15. Informações dos Integrantes

A preencher conforme os dados do grupo:

- Nome do aluno 1
- Nome do aluno 2

---

## 16. Status do Projeto

Em desenvolvimento conforme as diretrizes da disciplina e a documentação oficial da avaliação do 3º bimestre.



