# Дипломная работа профессии «Тестировщик ПО»

Автоматизация тестирования веб-сервиса, который предлагает купить тур по определённой цене двумя способами (обычная оплата по дебетовой карте и выдача кредита по данным банковской карты), а также взаимодействействие сервиса с СУБД и API Банка.

[План автоматизации тестирования](https://github.com/IlyaaIvanovv/QA_Diploma/blob/main/docs/Plan.md).\
[Отчёт по итогам тестирования](https://github.com/IlyaaIvanovv/QA_Diploma/blob/main/docs/Report.md).\
[Отчёт по итогам автоматизации](https://github.com/IlyaaIvanovv/QA_Diploma/blob/main/docs/Summary.md).

## Начало работы

1. Склонировать репозиторий `git clone` по [ссылке](https://github.com/IlyaaIvanovv/QA_Diploma).
2. Открыть проект в IntelliJ IDEA.
3. Запустить Docker Desktop.

### Prerequisites

1. IntelliJ IDEA [(ссылка на установку)](https://www.jetbrains.com/idea/download/#section=windows).
2. Docker Desktop [(ссылка на установку)](https://www.docker.com/get-started).
3. Браузер Google Chrome [(ссылка на установку)](https://www.google.com/intl/ru_ru/chrome/).

### Установка и запуск

1. Ввести в терминале команду для запуска контейнеров с базами данных MySQL и PostgreSQL и тестируемым приложением на NodeJS:
```
docker-compose up
```

2. В новой вкладке терминала ввести команду для запуска тестируемого приложения в зависимости от БД:

- для работы с базой данных MySQL выполнить команду:
```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
```

- для работы с базой данных PostgreSQL выполнить команду:
```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
```

3. В новой вкладке терминала ввести команду для запуска авто-тестов в зависимости от запущенной ранее БД:

- для запуска тестов с базой данных MySQL выполнить команду:
```
./gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
```

- для запуска тестов с базой данных PostgreSQL выполнить команду:
```
./gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app
```

4. Перезапуск приложения, тестов и/или отчёта:

- Для перезапуска приложения, тестов и/или отчёта (например, для использования другой БД) необходимо выполнить остановку их работы, нажав в соответствующих вкладках терминала Ctrl+С.

5. Просмотр отчёта:

- для генерации отчёта после прохождения тестов выполнить команду:
```
./gradlew allureReport 
```

- для генерации отчёта и автоматическое открытие в браузере по умолчанию выполнить команду:
```
./gradlew allureServe 
```

6. Завершение работы.
- остановить контейнеры командой:
```
docker-compose down
```
