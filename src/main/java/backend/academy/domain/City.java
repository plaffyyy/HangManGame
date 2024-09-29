package backend.academy.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public non-sealed class City extends Category {
    private Map<String, String> cities = new HashMap<String, String>() {{
        put("Токио", "Столица Японии");
        put("Париж", "Столица Франции, известная Эйфелевой башней");
        put("Лондон", "Столица Великобритании");
        put("Шанхай", "Крупнейший город Китая");
        put("Москва", "Столица России");
        put("Мумбаи", "Крупнейший город Индии");
        put("Сидней", "Крупнейший город Австралии");
        put("Рим", "Столица Италии");
        put("Берлин", "Столица Германии");
        put("Торонто", "Крупнейший город Канады");
        put("Дубай", "Город в ОАЭ, известный своей архитектурой");
        put("Пекин", "Столица Китая");
        put("Сингапур", "Город-государство в Юго-Восточной Азии");
        put("Мадрид", "Столица Испании");
        put("Бангкок", "Столица Таиланда");
        put("Каир", "Столица Египта");
        put("Амстердам", "Столица Нидерландов");
    }};

    @Override
    public Map<String, String> getData() {
        return cities;
    }

    @Override
    public String toString() {
        return "города мира";
    }
}
