# picpay-simplificado

Este projeto usa Quarkus, the Supersonic Subatomic Java Framework.

## Executando o aplicativo no modo desenvolvedor

Você pode executar seu aplicativo no modo de desenvolvimento que permite codificação ao vivo usando:

```shell script
./mvnw compile quarkus:dev
```
## Para realizar cadastro de usuário usando o insomnia
```shell script
http://localhost:8080/users
```
## Cadastrar um novo usuário comun

```shell script
{
    "firstName": "Nome",
    "lastName": "Sobrenome",
    "document": "12345678910",
    "email": "nome@example.com",
    "password": "minhaSenha123",
    "balance": 500.00,
    "userType": "COMMON"
}
```
## Cadastrar um novo usuário logista
```shell script
{
    "firstName": "Logista",
    "lastName": "Sobrenome",
    "document": "12345678912",
    "email": "Logista@example.com",
    "password": "minhaSenha123",
    "balance": 500.00,
    "userType": "MERCHANT"
}
```
## Para realizar transferências entre usuários usando o insomnia

```shell script
http://localhost:8080/transaction
```

## Realizar uma Transação

```shell script
{
    "senderId": ?,
    "receiverId": ?,
    "amount": 50.00
}
```
