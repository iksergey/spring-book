## ORM (Object-Relational Mapping)

ORM (объектно-реляционное отображение) — это технология, связывающая базы данных с концепциями объектно-ориентированных языков программирования. ORM позволяет разработчикам работать с базами данных, используя объекты Java, что упрощает взаимодействие с данными и позволяет использовать такие концепции, как наследование и инкапсуляция.

## Spring Data JPA

Spring Data JPA предоставляет интерфейсы репозиториев, которые позволяют легко взаимодействовать с данными. Эти репозитории автоматически создают запросы на основе имен методов. Например, метод `findAllByState(String state)` в интерфейсе `CityRepository` может использоваться для поиска всех городов в заданном штате.

## Сущности (Entities)

Сущности в JPA — это POJO (Plain Old Java Objects), представляющие данные, которые могут быть сохранены в базе данных. Каждая сущность соответствует таблице в базе данных, а каждый экземпляр сущности — строке в этой таблице.

## Контекст персистентности JPA

Контекст персистентности — это набор всех объектов сущностей, которые используются в приложении. Он управляется `EntityManager`, который отвечает за взаимодействие с базой данных и отслеживание изменений в сущностях.

## Жизненный цикл сущностей JPA

Жизненный цикл сущностей JPA включает четыре состояния:

- **Transient (переходное)**: Объект сущности только что создан и не сохранен в базе данных.
- **Managed (управляемое)**: Объект сущности сохранен в базе данных и отслеживается `EntityManager`.
- **Detached (отсоединенное)**: Объект сущности больше не отслеживается `EntityManager`, например, после закрытия контекста персистентности.
- **Removed (удаленное)**: Объект сущности помечен для удаления, но еще не удален из базы данных.

## Начало работы

### Пример кода для работы с жизненным циклом сущностей

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    // Конструкторы, геттеры и сеттеры
}

// Создание нового (переходного) объекта
Student student = new Student();
student.setFirstName("Andrew");
student.setLastName("Brown");

// Перевод объекта в управляемое состояние
EntityManager em = entityManagerFactory.createEntityManager();
em.getTransaction().begin();
em.persist(student);
em.getTransaction().commit();

// Отсоединение объекта
em.detach(student);

// Удаление объекта
em.getTransaction().begin();
em.remove(student);
em.getTransaction().commit();
```

## Начало работы с Spring Boot и JPA

1. **Создайте проект с помощью Spring Initializr**:
   - Добавьте зависимости: Spring Data JPA.
   ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

2. **Настройте подключение к базе данных**:
   - В файле `application.properties` добавьте настройки параметров JPA
    ```text
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

В Spring Boot параметры `spring.jpa.hibernate.ddl-auto=update` и `spring.jpa.show-sql=true` служат для управления схемой базы данных и логирования SQL-запросов.

`spring.jpa.hibernate.ddl-auto=update`

Этот параметр управляет автоматическим обновлением схемы базы данных при запуске приложения. Значение `update` означает, что Hibernate будет вносить необходимые изменения в схему базы данных, чтобы она соответствовала вашим сущностям JPA. Это полезно на этапах разработки и тестирования, когда вы часто изменяете модели сущностей, поскольку позволяет сохранять существующие данные и обновлять только схему.

`spring.jpa.show-sql=true`

Этот параметр включает вывод SQL-запросов в консоль. Он используется для отладки и позволяет разработчикам видеть, какие SQL-запросы генерируются Hibernate на основе операций с сущностями JPA. Однако, стоит отметить, что использование этого параметра в продакшене не рекомендуется, так как он просто выводит запросы в консоль, что может быть неэффективно и небезопасно.

[Шаг 13](./step-13.md)