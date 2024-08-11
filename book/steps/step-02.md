1. Переименуйте файл `index.html` в `start.html`
2. Создайте пакет `controllers` 
3. Создайте java-класс `HomeController.java`

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "start.html";
    }
}
```

### Попробуйте объяснить что произошло

[Далее](./step-02theory.md)
