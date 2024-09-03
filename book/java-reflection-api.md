## Java Reflection API

Java Reflection - это инструмент, позволяющий программе исследовать и изменять свое собственное поведение во время выполнения. 

### Основные возможности Reflection:

1. Доступ к приватным частям объектов
2. Изменение поведения методов классов
3. Адаптация кода к входным данным
4. Работа с типами, неизвестными на этапе компиляции

### Преимущества использования Reflection:

- **Гибкость**: Код может эволюционировать и адаптироваться
- **Независимость от реализации**: Можно работать с классами, не зная их структуру заранее
- **Расширяемость**: Возможность вызывать приватные методы или изменять логику существующих

### Основные методы java.lang.reflect:

- `getName()`: Возвращает полное имя класса с пакетом
- `getSimpleName()`: Возвращает только имя класса
- `getModifiers()`: Возвращает модификаторы класса
- `getConstructors()`: Возвращает список конструкторов
- `getMethods()`: Возвращает список публичных методов
- `getFields()`: Возвращает список публичных полей

## Аннотации в Java

Аннотации - это специальные метки, добавляющие метаданные к коду.

### Применение аннотаций:

1. Информация для компилятора
2. Обработка во время компиляции и развертывания
3. Обработка во время выполнения программы

### Классификация аннотаций:

1. **Мета-аннотации**:
   - `@Target`: Определяет, где можно использовать аннотацию
   - `@Retention`: Указывает, когда аннотация доступна

2. **Аннотации для кода**:
   - `@Override`: Метод переопределяет метод суперкласса
   - `@Deprecated`: Помечает код как устаревший
   - `@SuppressWarnings`: Отключает предупреждения компилятора

### Создание собственной аннотации:

### Пример 1. Базовый пример

Создание экземпляра класса `Foo` с использованием рефлексии в Java может быть выполнено с помощью класса `Class` и его метода `newInstance()`. Однако, начиная с Java 9, метод `newInstance()` считается устаревшим, и рекомендуется использовать метод `getDeclaredConstructor().newInstance()`. Вот простой пример:

```java
public class Foo {
  // Конструктор по умолчанию
  public Foo() {
    System.out.println("Экземпляр Foo создан!");
  }
}

public class ReflectionExample {
  public static void main(String[] args) {
    try {
      // Получаем объект Class для класса Foo
      Class<Foo> fooClass = Foo.class;

      // Создаем экземпляр Foo через рефлексию
      Foo fooInstance = fooClass.getDeclaredConstructor().newInstance();

      // Используем созданный экземпляр
      System.out.println("Экземпляр создан: " + fooInstance);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
```

Объяснение

1. **Получение объекта `Class`**:
   - `Class<Foo> fooClass = Foo.class;` — получаем объект `Class` для класса `Foo`.

2. **Создание экземпляра**:
   - `fooClass.getDeclaredConstructor().newInstance();` — используем метод `getDeclaredConstructor()` для получения конструктора по умолчанию и вызываем `newInstance()` для создания нового экземпляра `Foo`.

3. **Обработка исключений**:
   - Поскольку создание экземпляра через рефлексию может привести к различным исключениям (например, `InstantiationException`, `IllegalAccessException`, `InvocationTargetException`), мы обрабатываем их в блоке `try-catch`.

Этот пример демонстрирует, как использовать рефлексию для создания экземпляра класса, который может быть полезен в ситуациях, когда тип класса неизвестен на этапе компиляции.

Попробуйте создать таким образом экземпляр класса в котором нет конструктора по умолчанию

## Пример 2. Создание экземпляров класса не через конструктор

Конечно! Вот пример метода, который принимает тип класса в качестве аргумента и создает его экземпляр с использованием рефлексии:

```java
import java.lang.reflect.Constructor;

public class ReflectionUtils {

  public static <T> T createInstance(Class<T> clazz) throws ReflectiveOperationException {
    Constructor<T> constructor = clazz.getDeclaredConstructor();
    return constructor.newInstance();
  }

  public static void main(String[] args) throws ReflectiveOperationException {
    Foo foo = createInstance(Foo.class);
    Bar bar = createInstance(Bar.class);
  }
}

class Foo {
  public Foo() {
    System.out.println("Конструктор Foo вызван");
  }
}

class Bar {
  public Bar() {
    System.out.println("Конструктор Bar вызван");
  }
}

```

Объяснение:

1. Метод `createInstance` является обобщенным (generic) методом, который принимает `Class<T>` в качестве аргумента и возвращает объект типа `T`.

2. Внутри метода мы используем `getDeclaredConstructor()` для получения конструктора без параметров для указанного класса.

3. Затем мы вызываем `newInstance()` на полученном конструкторе, чтобы создать новый экземпляр класса.

4. Метод объявляет, что он может выбросить `ReflectiveOperationException`, который является суперклассом для различных исключений, связанных с рефлексией (например, `InstantiationException`, `IllegalAccessException`, `InvocationTargetException`).

5. В `main` методе мы демонстрируем использование `createInstance` для создания экземпляров разных классов.

Этот подход имеет несколько преимуществ:

- Он универсален и может работать с любым классом, у которого есть конструктор без параметров.
- Использование обобщенных типов обеспечивает типобезопасность.
- Метод легко расширить для поддержки конструкторов с параметрами.

Обратите внимание, что этот метод будет работать только для классов с доступным конструктором без параметров. Если вам нужно создавать экземпляры классов с параметризованными конструкторами, вам потребуется более сложная реализация.

## Пример 3. "Нарушение" инкапсуляции

```java
import java.lang.reflect.Field;

public class ReflectionExample {
  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
    // Создаем экземпляр класса Bar
    Bar bar = new Bar();

    // Выводим начальное значение
    System.out.println("Начальное значение: " + bar.getBar());

    // Получаем класс
    Class<?> barClass = bar.getClass();

    // Получаем приватное поле 'bar'
    Field barField = barClass.getDeclaredField("bar");

    // Делаем поле доступным
    barField.setAccessible(true);

    // Изменяем значение поля
    barField.set(bar, "new bar");

    // Выводим новое значение
    System.out.println("Новое значение: " + bar.getBar());
  }
}

class Bar {
  private String bar = "bar";

  public String getBar() {
    return bar;
  }
}

```

## Пример 4

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
```

## Аннотация LogExecutionTime

Эта аннотация предназначена для маркировки методов, время выполнения которых нужно логировать.

Компоненты аннотации

1. **`@Retention(RetentionPolicy.RUNTIME)`**:
   - Эта мета-аннотация указывает, как долго аннотация `LogExecutionTime` должна сохраняться и быть доступной.
   - `RetentionPolicy.RUNTIME` означает, что аннотация будет доступна во время выполнения программы через механизм рефлексии.
   - Это важно, так как позволяет нашему прокси-классу проверять наличие аннотации на методах во время выполнения программы.

2. **`@Target(ElementType.METHOD)`**:
   - Эта мета-аннотация определяет, к каким элементам Java-кода может быть применена аннотация `LogExecutionTime`.
   - `ElementType.METHOD` указывает, что аннотацию можно применять только к методам.
   - Это ограничивает использование аннотации и предотвращает её неправильное применение к классам, полям или другим элементам.

3. **`@interface LogExecutionTime`**:
   - Это объявление самой аннотации.
   - Ключевое слово `@interface` используется в Java для определения аннотаций.
   - В данном случае, аннотация не имеет элементов (параметров), поэтому тело интерфейса пустое.

Применение

Эта аннотация используется следующим образом:

```java
@LogExecutionTime
public void someMethod() {
  // Код метода
}
```

Особенности и использование

1. **Простота**: Аннотация не имеет параметров, что делает её использование очень простым.

2. **Целевое использование**: Предназначена только для методов, что помогает избежать ошибок при её применении.

3. **Доступность во время выполнения**: Благодаря `RetentionPolicy.RUNTIME`, аннотацию можно обнаружить и обработать во время выполнения программы.

4. **Интеграция с прокси**: Эта аннотация работает в паре с классом-обработчиком (например, `LoggingHandler`), который проверяет наличие аннотации и выполняет соответствующие действия.

5. **Разделение ответственности**: Позволяет отделить логику логирования от бизнес-логики метода.

Эта аннотация является ключевым элементом в реализации аспектно-ориентированного подхода к логированию в данном примере. Она позволяет декларативно указать, какие методы должны логироваться, не изменяя их реализацию.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}
```

## Класс LoggingHandler

Этот класс является ключевым компонентом в реализации логирования времени выполнения методов с использованием аннотации `@LogExecutionTime`.

Назначение

`LoggingHandler` реализует интерфейс `InvocationHandler`, что позволяет ему перехватывать вызовы методов объекта, для которого создается прокси.

Структура

1. **Поле `target`**: 
   - Это оригинальный объект, методы которого мы хотим логировать.
   - Он передается в конструктор и сохраняется для последующего использования.

2. **Метод `invoke`**:
   - Этот метод вызывается каждый раз, когда вызывается любой метод прокси-объекта.
   - Он принимает три параметра:
   - `proxy`: сам прокси-объект (обычно не используется)
   - `method`: метод, который был вызван
   - `args`: аргументы, переданные методу

Логика работы

1. Проверяется, присутствует ли аннотация `@LogExecutionTime` у вызываемого метода.
2. Если аннотация присутствует:
   - Получается текущее время в заданном формате.
   - Выводится сообщение с именем метода и временем вызова.
3. Затем вызывается оригинальный метод объекта `target` с переданными аргументами.

Особенности

- Логирование происходит только для методов, помеченных аннотацией `@LogExecutionTime`.
- Логирование выполняется до вызова оригинального метода, что позволяет фиксировать точное время начала выполнения.
- Оригинальный метод вызывается в любом случае, независимо от наличия аннотации.

Применение

Этот класс используется в сочетании с `java.lang.reflect.Proxy` для создания динамических прокси-объектов. Когда метод прокси-объекта вызывается, управление передается методу `invoke` этого обработчика, что позволяет выполнить дополнительную логику (в данном случае - логирование) до или после выполнения оригинального метода.

Такой подход позволяет добавить функциональность логирования без изменения исходного кода классов, что соответствует принципу разделения ответственности и облегчает поддержку кода.

```java
class LoggingHandler implements InvocationHandler {
  private final Object target;

  public LoggingHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isAnnotationPresent(LogExecutionTime.class)) {
      String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println("Метод " + method.getName() + " вызван в " + currentTime);
    }
    return method.invoke(target, args);
  }
}
```

Вот объяснение к классу `LoggingProxyFactory`:

## Класс LoggingProxyFactory

Этот класс служит фабрикой для создания прокси-объектов, которые добавляют дополнительную функциональность к существующим объектам, в данном случае — логирование времени вызова методов.

Назначение

`LoggingProxyFactory` предоставляет статический метод `createProxy`, который создает и возвращает прокси-объект для заданного интерфейса. Этот прокси-объект перехватывает вызовы методов и обрабатывает их с помощью `LoggingHandler`.

Структура

1. **Метод `createProxy`**:
   - Это статический метод, который принимает два параметра:
   - `target`: объект, для которого создается прокси. Этот объект должен реализовывать интерфейс, указанный в `interfaceType`.
   - `interfaceType`: класс интерфейса, который реализует `target`. Прокси будет реализовывать этот интерфейс.

2. **Аннотация `@SuppressWarnings("unchecked")`**:
   - Используется для подавления предупреждений компилятора о непроверенном приведении типов. Это необходимо, поскольку метод `Proxy.newProxyInstance` возвращает объект типа `Object`, который мы приводим к типу `T`.

Логика работы

- **Создание прокси**:
  - Метод `Proxy.newProxyInstance` используется для создания нового прокси-объекта.
  - Параметры метода:
  - `interfaceType.getClassLoader()`: загрузчик классов для прокси.
  - `new Class<?>[] { interfaceType }`: массив интерфейсов, которые должен реализовывать прокси.
  - `new LoggingHandler(target)`: обработчик вызовов методов, который будет перехватывать и обрабатывать вызовы.

- **Возврат прокси**:
  - Прокси возвращается как объект типа `T`, что позволяет использовать его как замену оригинальному объекту `target`.

Применение

Этот класс позволяет легко добавить функциональность логирования к любому объекту, реализующему интерфейс, без изменения его кода. Это достигается путем создания прокси-объекта, который делегирует вызовы методов оригинальному объекту, добавляя при этом дополнительную логику.

Такой подход полезен для реализации аспектно-ориентированного программирования (AOP), где можно добавлять кросс-срезные функции (например, логирование, транзакции, безопасность) без изменения бизнес-логики.

```java
class LoggingProxyFactory {
  @SuppressWarnings("unchecked")
  public static <T> T createProxy(T target, Class<T> interfaceType) {
    return (T) Proxy.newProxyInstance(
        interfaceType.getClassLoader(),
        new Class<?>[] { interfaceType },
        new LoggingHandler(target));
  }
}
```

## interface MyService

Этот интерфейс определяет контракт для сервиса, который выполняет некоторые действия. Он демонстрирует использование аннотации @LogExecutionTime для выборочного логирования методов.

```java
interface MyService {
  @LogExecutionTime
  void doSomething();

  void doSomethingElse();
}
```

## class MyServiceImpl
Этот класс представляет собой конкретную реализацию интерфейса MyService. Он демонстрирует, как можно реализовать методы, определенные в интерфейсе.

```java
class MyServiceImpl implements MyService {
  @Override
  public void doSomething() {
    System.out.println("Выполняется метод doSomething");
  }

  @Override
  public void doSomethingElse() {
    System.out.println("Выполняется метод doSomethingElse");
  }
}
```


```java
public class Program {
  public static void main(String[] args) {
    MyService service = LoggingProxyFactory.createProxy(new MyServiceImpl(), MyService.class);

    service.doSomething();
    service.doSomethingElse();
  }
}
```

### Весь код

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Program {
  public static void main(String[] args) {
    MyService service = LoggingProxyFactory.createProxy(new MyServiceImpl(), MyService.class);

    service.doSomething();
    service.doSomethingElse();
  }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

class LoggingHandler implements InvocationHandler {
  private final Object target;

  public LoggingHandler(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isAnnotationPresent(LogExecutionTime.class)) {
      String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println("Метод " + method.getName() + " вызван в " + currentTime);
    }
    return method.invoke(target, args);
  }
}

class LoggingProxyFactory {
  @SuppressWarnings("unchecked")
  public static <T> T createProxy(T target, Class<T> interfaceType) {
    return (T) Proxy.newProxyInstance(
        interfaceType.getClassLoader(),
        new Class<?>[] { interfaceType },
        new LoggingHandler(target));
  }
}

interface MyService {
  @LogExecutionTime
  void doSomething();

  void doSomethingElse();
}

class MyServiceImpl implements MyService {
  @Override
  public void doSomething() {
    System.out.println("Выполняется метод doSomething");
  }

  @Override
  public void doSomethingElse() {
    System.out.println("Выполняется метод doSomethingElse");
  }
}

```

Аналогичный пример на python

```python
import time
import functools

def log_execution_time(func):
    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        print(f"Метод {func.__name__} вызван в {time.strftime('%Y-%m-%d %H:%M:%S')}")
        return func(*args, **kwargs)
    return wrapper

@log_execution_time
def simple_function():
    time.sleep(1)  # Имитация работы
    print("simple_function выполняется")

simple_function()

```
