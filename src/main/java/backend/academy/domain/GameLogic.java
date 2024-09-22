package backend.academy.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter public final class GameLogic {
    private static final int EASY_MISTAKES_LIMIT = 7;
    private static final int MEDIUM_MISTAKES_LIMIT = 6;
    private static final int HARD_MISTAKES_LIMIT = 5;
    private static final String HINT_COMMAND = "подсказка";
    private static final SecureRandom RANDOM = new SecureRandom();

    private BufferedReader reader;
    private PrintStream out;
    private final int hintsLimit = 1;
    private final ArrayList<Character> alphabet = new ArrayList<>(
        Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'));
    private String word;
    private String hint;
    private String level;
    private String category;
    private int amountMistakes;

    public GameLogic(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        this.out = out;
    }

    public GameLogic() {

    }

    public boolean start() throws IOException {
        out.println("Здравствуй! Ты зашел в игру 'Виселица', "
            + "укажи категорию слов, которая нравится больше всего из следующих:");
        out.println("Математика   Медицина   Города мира   Штаты   Страны");
        out.println("Просто нажми 'Enter' для выбора случайной категории.");
        String categoryString = reader.readLine();
        Category selectedCategory = new WordsCategory(categoryString).getCategory();
        while (selectedCategory == null) {
            out.println("Неверный инпут, проверь язык ввода");
            out.println("Нажми 'Enter' для выбора случайного слова или напиши категорию из следующих");
            out.println("Математика   Медицина   Города мира   Штаты США   Страны");
            categoryString = reader.readLine();
            selectedCategory = new WordsCategory(categoryString).getCategory();
        }
        this.category = selectedCategory.toString();
        Map.Entry<String, String> wordHint = selectedCategory.getElement();
        this.word = wordHint.getKey().toLowerCase();
        if (this.word.length() <= 1) {
            return false;
        }
        this.hint = wordHint.getValue();

        out.println(
            "Теперь выбери уровень сложности из трех, " + "число справа обозначает количество допустимых ошибок");
        out.println("Легкий - 7 ошибок   Средний - 6 ошибок  Сложный - 5 ошибок");
        out.println("Просто нажми 'Enter' для выбора случайного уровня сложности.");

        this.level = reader.readLine().toLowerCase();
        return true;
    }

    public void defineAmountMistakes() {
        switch (this.level) {
            case "легкий":
                this.amountMistakes = EASY_MISTAKES_LIMIT;
                break;
            case "средний":
                this.amountMistakes = MEDIUM_MISTAKES_LIMIT;
                break;
            case "сложный":
                this.amountMistakes = HARD_MISTAKES_LIMIT;
                break;
            default:
                this.amountMistakes = RANDOM.nextInt(HARD_MISTAKES_LIMIT, EASY_MISTAKES_LIMIT);
                break;
        }
    }

    public void printAlphabet(ArrayList<Character> mistakesElements, ArrayList<Character> usedElements) {
        for (Character element : alphabet) {
            if (mistakesElements.contains(element) || usedElements.contains(element)) {
                continue;
            } else {
                out.print(element + " ");
            }
        }
        out.println();
    }

    public boolean play() throws IOException {
        out.println("В любой момент игры ты можешь получить подсказку(кроме некорректных вводов), "
            + "просто напиши слово '" + HINT_COMMAND + "', но учти, что данная опция работает только один раз.");
        out.println("Категория: " + this.category + ", Количество попыток: " + this.amountMistakes);
        int mistakesCount = 0;
        int hintsCount = 0;
        GameVisualizator visualizator = new GameVisualizator(out, word);

        ArrayList<Integer> guessedIndexes = new ArrayList<>();
        ArrayList<Character> mistakesElements = new ArrayList<>();
        ArrayList<Character> usedElements = new ArrayList<>(); //store the element, which was used

        while (mistakesCount < this.amountMistakes && guessedIndexes.size() < this.word.length()) {
            out.println("Введи букву из следующих:");

            printAlphabet(mistakesElements, usedElements);

            visualizator.print(mistakesCount);
            visualizator.printWord(guessedIndexes);

            boolean flag = false;
            String stringElement = reader.readLine().toLowerCase();
            if (hintsCount < hintsLimit && HINT_COMMAND.equals(stringElement)) {
                out.println(this.hint);
                hintsCount++;
                continue;
            } else if (hintsCount >= hintsLimit && HINT_COMMAND.equals(stringElement)) {
                out.println("Ты уже пользовался возможностью взять подсказку!");
                continue;
            }
            while (!alphabet.contains(stringElement.charAt(0))) {
                out.println("Введи символ на латинице!");
                stringElement = reader.readLine().toLowerCase();
            }
            while (stringElement.length() != 1) {
                out.println("Ты ввел больше одного символа или вообще не ввел, попробуй еще раз.");
                stringElement = reader.readLine().toLowerCase();
            }
            while (usedElements.contains(stringElement.charAt(0))
                || mistakesElements.contains(stringElement.charAt(0))) {
                out.println("Ты уже вводил этот символ, введи из списка ниже:");
                printAlphabet(mistakesElements, usedElements);
                stringElement = reader.readLine().toLowerCase();
            }

            char element = stringElement.toLowerCase().charAt(0);
            for (int i = 0; i < this.word.length(); i++) {
                if (this.word.charAt(i) == element) {
                    guessedIndexes.add(i);
                    flag = true;
                }
            }

            if (!flag) {
                mistakesElements.add(element);
                mistakesCount++;
            } else {
                usedElements.add(element);
            }
        }

        if (mistakesCount == this.amountMistakes) {
            visualizator.print(mistakesCount);
            out.println("Вы проиграли(");
            out.println("Это было слово: " + this.word);
            return false;
        } else {
            visualizator.printWord(guessedIndexes);
            out.println("Поздравляем! Все верно!");
            return true;
        }
    }

}
