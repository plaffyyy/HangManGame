package backend.academy.domain;


import lombok.Getter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
public class City extends Category{
    private Random randomGenerator = new Random();
    private HashMap<String, String> cities = new HashMap<String, String>() {{
        put("Токио", "Столица Японии");
        put("Нью-Йорк", "Город в США, известный своим Центральным парком");
        put("Париж", "Столица Франции, известная Эйфелевой башней");
        put("Лондон", "Столица Великобритании");
        put("Шанхай", "Крупнейший город Китая");
        put("Москва", "Столица России");
        put("Мумбаи", "Крупнейший город Индии");
        put("Сидней", "Крупнейший город Австралии");
        put("Рим", "Столица Италии");
        put("Берлин", "Столица Германии");
        put("Лос-Анджелес", "Город в США, известный Голливудом");
        put("Торонто", "Крупнейший город Канады");
        put("Дубай", "Город в ОАЭ, известный своей архитектурой");
        put("Пекин", "Столица Китая");
        put("Сингапур", "Город-государство в Юго-Восточной Азии");
        put("Мадрид", "Столица Испании");
        put("Бангкок", "Столица Таиланда");
        put("Каир", "Столица Египта");
        put("Буэнос-Айрес", "Столица Аргентины");
        put("Амстердам", "Столица Нидерландов");
    }};
    public Map.Entry<String, String> getElement() {
        int index = randomGenerator.nextInt(cities.size()-1);
        return cities.entrySet().stream().toList().get(index);
    }
}
