package backend.academy.domain;

import lombok.Getter;
import lombok.Setter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.io.InputStreamReader;
import java.util.Random;

@Setter
@Getter
public class GameLogic {
    private BufferedReader reader;
    private PrintStream out;
    private final ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList(
        'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м',
        'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
        'ы', 'ь', 'э', 'ю', 'я'
    ));
    private String word;
    private String hint;
    private String level;
    private int amountMistakes;

    public GameLogic(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
    }

    public GameLogic() {

    }

    public void start() throws IOException {
        out.println("Здравствуй! Ты зашел в игру 'Виселица', " +
            "укажи категорию слов, которая нравится больше всего из следующих:");
        out.println("Математика   Медицина   Города мира   Штаты США   Страны");
        out.println("Просто нажми 'Enter' для выбора случайной категории.");

        String categoryString = reader.readLine();
        Category category = new WordsCategory(categoryString).getCategory();
        while (category == null) {
            out.println("Неверный инпут, проверь регистр");
            out.println("Нажми 'Enter' для выбора случайного слова или напиши категорию из следующих");
            out.println("Математика   Медицина   Города мира   Штаты США   Страны");
            categoryString = reader.readLine();
            category = new WordsCategory(categoryString).getCategory();
        }
        Map.Entry<String, String> wordHint = category.getElement();
        this.word = wordHint.getKey().toLowerCase();
        this.hint = wordHint.getValue();

        out.println("Теперь выбери уровень сложности из трех, " +
            "число справа обозначает количество допустимых ошибок");
        out.println("Легкий - 7 ошибок   Средний - 6 ошибок  Сложный - 5 ошибок");
        out.println("Просто нажми 'Enter' для выбора случайного уровня сложности.");

        this.level = reader.readLine().toLowerCase();

    }

    public void defineAmountMistakes() {
        switch (this.level) {
            case "легкий":
                this.amountMistakes = 7;
                break;
            case "средний":
                this.amountMistakes = 6;
                break;
            case "сложный":
                this.amountMistakes = 5;
                break;
            default:
                this.amountMistakes = new Random().nextInt(5, 7);
                break;
        }
    }
    public void printAlphabet(ArrayList<Character> mistakesElements, ArrayList<Character> usedElements) {
        for (Character element: alphabet) {
            if (mistakesElements.contains(element) || usedElements.contains(element)) {
                continue;
            } else {
                out.print(element + " ");
            }
        }
        out.println();
    }

    public boolean play() throws IOException {
        int mistakesCount = 0;
        GameVisualizator visualizator = new GameVisualizator(out, word);

        ArrayList<Integer> guessedIndexes = new ArrayList<>();
        ArrayList<Character> mistakesElements = new ArrayList<>();
        ArrayList<Character> usedElements =
            new ArrayList<>(); //store the element, which was used

        while (mistakesCount < this.amountMistakes && guessedIndexes.size() < this.word.length()) {
            out.println("Введи букву из следующих:");

            printAlphabet(mistakesElements, usedElements);

            visualizator.print(mistakesCount);

            boolean flag = false;
            String stringElement = reader.readLine();
            while (stringElement.length() != 1) {
                out.println("Ты ввел больше одного символа или вообще не ввел, попробуй еще раз.");
                stringElement = reader.readLine();
            }
            while (usedElements.contains(stringElement.toCharArray()[0]) ||
                    mistakesElements.contains(stringElement.toCharArray()[0])) {
                out.println("Ты уже вводил этот символ, введи из списка выше:");
                stringElement = reader.readLine();
            }

            char[] element = stringElement.toLowerCase().toCharArray();
            for (int i = 0; i < this.word.length(); i++) {
                if (this.word.charAt(i) == element[0]) {
                    guessedIndexes.add(i);
                    flag = true;
                }
            }

            if (!flag) {
                mistakesElements.add(element[0]);
                mistakesCount++;
            } else {
                usedElements.add(element[0]);
            }
        }

        if (mistakesCount == this.amountMistakes) {
            visualizator.print(mistakesCount);
            out.println("Вы проиграли(");
            return false;
        } else {
            out.println("Поздравляем! Все верно!");
            return true;
        }
    }
}
