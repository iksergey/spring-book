Для создания приложения для управления контактами на Java с использованием трехуровневой архитектуры, вам потребуется организовать структуру проекта и определить основные компоненты. 

## Структура проекта

1. **Основная структура проекта**

   ```
   contact-management-app/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   │   └── ru/
   │   │   │       └── ksergey/
   │   │   │           └── ContactsApp/
   │   │   │               ├── controller/
   │   │   │               ├── service/
   │   │   │               ├── repository/
   │   │   │               └── model/
   │   │   └── resources/
   │   └── test/
   │       └── java/
   │           └── ru/
   │               └── ksergey/
   │                   └── ContactsApp/
   ├── pom.xml
   └── README.md
   ```

2. **Описание папок и файлов**

- **src/main/java/ru/ksergey/ContactsApp**
  - **controller**
    - `ContactController.java` - класс для обработки HTTP-запросов.
  - **service**
    - `ContactService.java` - интерфейс для бизнес-логики.
    - `InMemoryContactService.java` - реализация сервиса с хранением данных в памяти.
    - `DatabaseContactService.java` - реализация сервиса с хранением данных в базе данных.
  - **repository**
    - `ContactRepository.java` - интерфейс для доступа к данным.
    - `InMemoryContactRepository.java` - реализация репозитория для хранения данных в памяти.
    - `DatabaseContactRepository.java` - реализация репозитория для работы с базой данных.
  - **model**
    - `Contact.java` - модель данных для контакта.
    - `ContactDTO.java` - DTO модель для обновления данных через PUT запросы.
  - **config**
    - `AppConfig.java` - вариативно, конфигурационный файл для управления зависимостями и аннотациями.

- **src/main/resources**
  - `application.properties` - файл конфигурации для настройки базы данных и других параметров.

- **src/test/java/ru/ksergey/ContactsApp**
  - **controller**
    - `ContactControllerTest.java` - тесты для контроллера.
  - **service**
    - `ContactServiceTest.java` - тесты для сервиса.
  - **repository**
    - `ContactRepositoryTest.java` - тесты для репозитория.
