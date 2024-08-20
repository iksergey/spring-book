обновите конфигурацию подключения к базе данных в файл `application.properties`:

## 1. Настройка файла `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:4444/contacts_db
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 2. Реализация JdbcContactRepository

Создайте класс `JdbcContactRepository`, который реализует интерфейс ContactRepository и использует JDBC для взаимодействия с базой данных MySQL:

Измените `JdbcContactRepository` для использования `DataSource`, который будет автоматически настроен Spring. Вам больше не нужно вручную загружать драйвер или управлять подключениями. Вместо этого вы можете использовать внедрение зависимости `DataSource`.

Обращаемся к ранее [описанной шпаргалке](https://github.com/iksergey/spring-boot-todo-mastery) 

[Далее](./step-09theory.md)
