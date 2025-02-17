## Installation

- Clone the repository

```console
git@github.com:datastaxdevs/spring-boot-astra-spring-data-cassandra.git
```

- 1. Create an account in Astra DB for free
https://astra.datastax.com/

- 2. Create a Database
https://docs.datastax.com/en/astra-db-serverless/databases/create-database.html

- 3. Create an Application token
https://docs.datastax.com/en/astra-db-serverless/administration/manage-application-tokens.html

## Setup

- Open `application.propoerties` and change `datastax.astra.token` with your token and `datastax.astra.endpoint` with the endpoint of your database

```ini
datastax.astra.token=<change_me>
datastax.astra.endpoint=<change_me>
```

## use the application

- Start the application (first start could take up a few seconds as the table is created for you)

```bash
mvn spring-boot:run
```

- List your todos (or in your browser [http://localhost:8080/tables/todos](http://localhost:8080/todos))

```bash
curl -X GET http://localhost:8080/collections/todos
curl -X GET http://localhost:8080/tables/todos
```

- Create a todo

```bash
curl -X POST http://localhost:8080/collections/todos \
     -H "Content-Type: application/json" \
     -d '{"title": "New Todo", "description": "Todo details", "completed": false}'
     
curl -X POST http://localhost:8080/tables/todos \
     -H "Content-Type: application/json" \
     -d '{"title": "New Todo", "description": "Todo details", "completed": false}'
```

- You can go data explorer:
![Screenshot 2025-02-17 at 15 06 28](https://github.com/user-attachments/assets/403d8553-05f0-4b9e-900a-226072464a91)


