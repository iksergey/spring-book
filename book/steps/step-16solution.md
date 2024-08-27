Чтобы изменить API для возврата полной информации об инженере вместе с данными об автомобиле, следуйте этим шагам:

## Шаг 1: Обновление модели

```java
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer;
}
```

## Шаг 2: всё

[Следующий шаг](./step-16mark.md)
