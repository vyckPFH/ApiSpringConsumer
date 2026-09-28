# Ministério da Educação
## Instituto Federal do Paraná - Campus Cascavel

**AVALIAÇÃO 3º BIMESTRE**  
**DISCIPLINA:** Programação Orientada à Objetos  
**ANO/TURMA:** 3º Informática  

---

### Descrição
Cada dupla deverá escolher um tema para sua aplicação. Exemplos: biblioteca, catálogo de filmes ou séries, viagens, jogos, eventos, receitas, turismo, sistema acadêmico, etc ...  
O tema deve ser aprovado.

---

### Requisitos obrigatórios
A aplicação deverá utilizar:
* Java e Spring Boot
* Spring Web
* Spring Data JPA
* Banco de dados
* Thymeleaf e HTML

---

### Arquitetura MVC
O projeto deverá manter a organização trabalhada em aula, contendo:
* **Model/Entity** 🡪 O sistema deverá possuir pelo menos uma classe de modelo representando uma entidade da aplicação (por exemplo: Livro, Filme, Pet, Viagem, Evento ou Jogo), com atributos coerentes com o problema escolhido.
* **Repository** 🡪 A entidade principal deverá ser armazenada em banco de dados utilizando JPA. O projeto deverá possuir um Repository utilizando `JpaRepository`.
* **Controller** 🡪 O sistema deverá permitir no mínimo: Cadastrar Registros, Listar Registros, Editar Registros e Excluir Registros.
* **Páginas HTML utilizando Thymeleaf**

---

### Integração com API externa
A aplicação deverá consumir pelo menos uma API disponível na Internet. A integração deverá possuir uma funcionalidade visível para o usuário.

**Fluxo esperado:** usuário realiza uma ação → aplicação consulta a API externa → recebe os dados (normalmente em JSON) → utiliza a resposta e apresenta informações na interface.

*Exemplo:* o usuário pesquisa um livro; a aplicação consulta uma API de livros e apresenta título, autor, ano e capa. Opcionalmente, o usuário poderá selecionar um resultado e cadastrá-lo no banco da aplicação.

**Importante:** não será considerada integração válida apenas imprimir ou exibir o JSON bruto retornado pela API. Os dados recebidos deverão ser utilizados de forma significativa pela aplicação.

---

### Interface Com Usuário GUI
A interface deverá ser simples, organizada e funcional. A aparência visual não será o principal critério de avaliação, mas todas as funcionalidades deverão poder ser utilizadas corretamente.

---

### Entrega
Deverão estar disponíveis em repositório no GitHub:
* Código-fonte completo do projeto.
* Endereço/documentação da API utilizada.
* Breve descrição da funcionalidade implementada com a API.
* README contendo nomes dos integrantes, tema, descrição do sistema e API utilizada.

---

### Critérios de avaliação

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

### Conteúdos que NÃO são obrigatórios
Neste trabalho não será necessário implementar conteúdos que ainda não foram desenvolvidos em aula, tais como:
* Camada Service
* DTO
* Spring Security/autenticação
* Tratamento avançado de exceções
* Arquiteturas avançadas

O objetivo é consolidar os conhecimentos efetivamente trabalhados durante o bimestre.