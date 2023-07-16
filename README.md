# MoneyFlow Microservice

## Описание

MoneyFlow Microservice - это микросервис, предоставляющий API для проведения денежных операций и интеграции с имитацией внешней платежной системы.

Основные функции:
- Перевод денежных средств между счетами
- Получение истории транзакций
- Получение баланса счета

## Использование API
После запуска микросервис будет доступен по следующему URL: http://localhost:8081

Создание нового счета
URL: /accounts

Метод: POST

Тело запроса:
```
{
"accountNumber": "4543564786",
"balance": 5000.00,
"ownerName": "Zhanibek Kairatuly",
"accountType": "CREDIT",
"currency": "KZT"
}
```

Пример ответа:
```
{
"id": 1,
"accountNumber": "4543564786",
"balance": 5000.0,
"ownerName": "Zhanibek Kairatuly",
"accountType": "CREDIT",
"currency": "KZT",
"outgoingTransactions": null,
"incomingTransactions": null
}
```

Получение баланса счета
URL: /accounts/{accountId}

Метод: GET

Пример ответа:
```
{
"accountId": 1,
"balance": 500.0,
"currency": "USD"
}
```
Перевод денежных средств
URL: /transfer

Метод: POST

Тело запроса:
```
{
"senderAccountId": 1,
"recipientAccountId": 2,
"amount": 100.0
}
```

Пример ответа:
```
{
"id": 1,
"senderAccountId": 1,
"recipientAccountId": 2,
"amount": 100.0,
"date": "2023-07-14T10:30:00"
}
```