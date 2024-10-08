package backend.academy.domain;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public non-sealed class Country extends Category {
    private SecureRandom randomGenerator = new SecureRandom();
    private Map<String, String> countries = new HashMap<String, String>() {{
        put("Япония", "Страна восходящего солнца");
        put("США", "Страна с 50 штатами");
        put("Франция", "Страна с Эйфелевой башней");
        put("Великобритания", "Страна с монархией и Биг-Беном");
        put("Китай", "Самая населённая страна мира");
        put("Россия", "Самая большая страна мира по территории");
        put("Индия", "Вторая по численности населения страна");
        put("Австралия", "Страна и континент");
        put("Италия", "Страна, известная своей культурой и кухней");
        put("Германия", "Страна с богатой историей и промышленностью");
        put("Канада", "Вторая по размеру страна в мире");
        put("ОАЭ", "Страна с одним из самых высоких уровней жизни");
        put("Испания", "Страна с богатой культурой и фламенко");
        put("Бразилия", "Крупнейшая страна Южной Америки");
        put("Аргентина", "Страна, известная своим танго и футболом");
        put("Египет", "Страна с древними пирамидами");
        put("Мексика", "Страна, известная своей кухней и праздниками");
        put("ЮжнаяКорея", "Страна, известная своими технологиями и поп-культурой");
        put("СаудовскаяАравия", "Страна с крупнейшими запасами нефти");
        put("Нидерланды", "Страна с каналами и ветряными мельницами");
    }};

    @Override
    public Map<String, String> getData() {
        return countries;
    }

    @Override
    public String toString() {
        return "страны";
    }
}
