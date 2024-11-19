


## springboot-flyway-standings
Это приложение, которое предоставляет данные по турнирной
таблице чемпионата.
В данной програме реализованна возможность регистрации результата матча - registerMatch, а также получения турнирной таблицы чемпионата
на определенную дату - getTournamentTable.

## Содержание

- Java 23, Spring Boot, Lombok, Spring Data JPA, Flyway, PostgreSQL, JUnit. 
- Скачайте .zip файл, разархивируйте архив, откройте проект в idea. Запустите класс Main.https://github.com/Daskp368/springboot-flyway-standings.git


## Настройка базы данных
- Установите и запустите PostgreSQL.
- Создайте базу данных
- Настройте параметры подключения в application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=none