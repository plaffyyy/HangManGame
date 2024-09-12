package backend.academy.domain;

import lombok.Setter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.io.InputStreamReader;

@Setter
public class GameLogic {
    private final BufferedReader reader;
    private final PrintStream out;
    private String word;
    private String hint;
    private String level;

    public GameLogic(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public void start() throws IOException {
        out.println("Здравствуй! Ты зашел в игру 'Виселица', " +
            "укажи категорию слов, которая нравится больше всего из следующих:");
        out.println("Математика   Медицина   Города мира   Штаты США   Страны");
        out.println("Просто нажми 'Enter' для выбора случайной категории.");

        String category = reader.readLine();
        Map.Entry<String, String> wordHint = new WordsCategory(category.toLowerCase()).getCategory().getElement();
        this.word = wordHint.getKey();
        this.hint = wordHint.getValue();

        out.println("Теперь выбери уровень сложности из трех, " +
            "число справа обозначает количество допустимых ошибок");
        out.println("Легкий - 7 ошибок   Средний - 6 ошибок  Сложный - 5 ошибок");
        out.println("Просто нажми 'Enter' для выбора случайного уровня сложности.");

        this.level = reader.readLine();

    }
}
