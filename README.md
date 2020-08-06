# 1. Java com SQLite

- [1. Java com SQLite](#1-java-com-sqlite)
  - [1.1. MVC (Model View Controller)](#11-mvc-model-view-controller)
  - [1.2. DAO (Data Access Object)](#12-dao-data-access-object)
  - [1.3. Singleton](#13-singleton)
  - [1.4. SQLite](#14-sqlite)
  - [1.5. DML e DQL](#15-dml-e-dql)
  - [1.6. java.sql](#16-javasql)
    - [1.6.1. java.sql.Connection](#161-javasqlconnection)
    - [1.6.2. java.sql.DriverManager](#162-javasqldrivermanager)
    - [1.6.3. java.sql.PreparedStatement](#163-javasqlpreparedstatement)
    - [1.6.4. java.sql.ResultSet](#164-javasqlresultset)
- [2. Referências](#2-referências)

## 1.1. MVC (Model View Controller)

<img align="right" width="200" height="200" src="https://www.onespan.com/sites/default/files/esign/blog/images/2000px-MVC-Process.svg_.png">

- MVC é um padrão de projeto estrutural que visa dividir o projeto em camadas bem definidas.
- O MVC isola as regras de negócio da lógica de apresentação, possibilitando que existam várias interfaces de acesso sem haja a necessidade de mudar as regras de negócio.
- É aplicável a vários tipos de projetos.

```
- Model: Manipulação dos dados.
- View: Interação com o usuário
- Controller: Controle.
```

## 1.2. DAO (Data Access Object)

<p align="center">
  <img width="624" height="314" src="https://www.journaldev.com/wp-content/uploads/2017/11/DAO-Pattern.png">
</p>

- DAO é um padrão de projeto que visa a separação da lógica de negócios com a lógica da persistência de dados.
- Classes DAO são responsáveis por trocar informações com o SGBD e fornecer operações CRUD e de pesquisas.

## 1.3. Singleton

<p align="center">
  <img width="321" height="401" src="https://www.tutorialspoint.com/design_pattern/images/singleton_pattern_uml_diagram.jpg">
</p>

- Permite criar objetos únicos no qual só possui uma instância.
- Oferece um ponto de acesso global.

## 1.4. SQLite


- Permite que um pequeno banco de dados seja embutido na aplicação, sem a necessidade de ter que acessar um SGBD a parte.
é recomendada para aplicações de pequeno porte que não necessitam de grande quantidade de dados.
- A grande vantagem de um banco de dados embutido é a sua simplicidade, pois é mais prático para desenvolver e administrar do que a implementação de um SGBD. Por outro lado, a performance e limitação de recursos são suas desvantagens.

  <img align="right" width="382" height="181" src="https://upload.wikimedia.org/wikipedia/commons/3/38/SQLite370.svg">

 - **Quando o uso do SQLite é recomendado:**
   - Aplicativos básicos desktop / mobile
   - Pequenos Web Sites
   - Sistemas utilizados por poucas pessoas
 - **Quando o uso do SQLite não é recomendado:**
   - Sistemas Web / aplicações cliente servidor
   - Sites que recebem muitas visitas

## 1.5. DML e DQL

- **DML**: São comandos que modificam o conteúdo das tabelas.
- **DQL:** Permite ao usuário especificar uma consulta.

## 1.6. java.sql

<p align="center">
  <img width="400" height="328" src="https://www.tutorialspoint.com/jdbc/images/jdbc-architecture.jpg">
</p>

- Oferece uma API para acessar e gerenciar dados armazenados em uma base de dados (geralmente em um banco de dados relacional).
- Os drivers podem ser instalados dinamicamente.
- Embora a seja voltada principalmente para passar instruções SQL para um banco de dados, ela fornece dados de leitura e gravação de qualquer base de dados.

###  1.6.1. java.sql.Connection

- Representa uma conexão com o banco de dados. as instruções são executadas e os resultados são retornados. 
- Fornecendo informações como:
  - Tabelas
  - Gramática SQL
  - Procedures;
- As aplicações não devem chamar comandos SQL diretamente para alterar a configuração da conexão quando houver um método para tal finalidade.
- Por padrão o modo de auto-commit está ativado, para alterar basta utilizar o método `setAutoCommit()`.
- Se o modo de auto-commit foi desativado, o método commit deve ser chamado explicitamente para confirmar mudanças; caso contrário, as alterações do banco de dados não serão salvas.

### 1.6.2. java.sql.DriverManager

- Oferece o serviço básico para gerenciar os drivers do banco de dados.
- Tenta carregar as classes de driver referenciadas na propriedade de sistema "jdbc.drivers".
- Quando o método getConnection é chamado, o DriverManager tentará localizar um driver adequado dentre aqueles carregados na inicialização e aqueles carregados explicitamente usando o mesmo carregador de classe que o applet ou aplicativo atual.

### 1.6.3. java.sql.PreparedStatement

- Representa uma instrução SQL pré-compilada.
- Uma instrução SQL é pré-compilada e armazenada em um objeto PreparedStatement. Este objeto pode ser usado para executar essa declaração com eficiência várias vezes.
- Os métodos setters para definir valores de um parâmetro devem especificar o tipo de dado que seja compatível com o tipo de dado SQL - definido do parâmetro de entrada.

### 1.6.4. java.sql.ResultSet
- Representa um conjuto de resultados do banco de dados, geralmente é gerado pela execução de uma instrução que consulta o banco de dados.
- O objeto de resultset incia mantendo um ponteiro antes da linha inicial, o método next() move o cursor para a próxima linha e retornará false caso não tenha mais linhas.
- Um objeto ResultSet padrão não é atualizável e tem um cursor que se move apenas para frente. Assim, você pode percorrer apenas uma vez e somente da primeira linha até a última linha.
- A interface ResultSet fornece métodos getter (getBoolean, getLong e assim por diante) para recuperar valores de coluna da linha atual. Os valores podem ser recuperados usando o número de índice da coluna ou o nome da coluna. Em geral, usar o índice da coluna será mais eficiente. 
-  Para os métodos getters, o driver JDBC tenta converter os dados subjacentes para o tipo  de dado especificado no método getter e retorna um valor adequado.

# 2. Referências
- Connection (Java Platform SE 8 ). Disponível em: <https://docs.oracle.com/javase/8/docs/api/java/sql/Connection.html>.
- DriverManager (Java Platform SE 8 ). Disponível em: <https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html>.
- ResultSet (Java Platform SE 8 ). Disponível em: <https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSet.html>.
- PreparedStatement (Java Platform SE 8 ). Disponível em: <https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html>.
- SQlite Java - How To Use JDBC To Interact with SQLite. SQLite Tutorial. Disponível em: <http://www.sqlitetutorial.net/sqlite-java/>.