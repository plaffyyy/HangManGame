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
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter public final class GameLogic {
    private static final int MATH_CATEGORY = 1;
    private static final int MEDICINE_CATEGORY = 2;
    private static final int CITIES_CATEGORY = 3;
    private static final int STATES_CATEGORY = 4;
    private static final int COUNTRIES_CATEGORY = 5;
    private static final int RANDOM_CATEGORY_INTEGER = 0;
    private static final String RANDOM_CATEGORY = "0";
    private static final int EASY_MISTAKES_LIMIT = 7;
    private static final int MEDIUM_MISTAKES_LIMIT = 6;
    private static final int HARD_MISTAKES_LIMIT = 5;
    private static final String HINT_COMMAND = "подсказка";
    private static final SecureRandom RANDOM = new SecureRandom();

    private BufferedReader reader;
    private PrintStream out;
    private final int hintsLimit = 1;
    private final List<Character> alphabet = new ArrayList<>(
        Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'));
    private String word;
    private String hint;
    private String level;
    private String category;
    private int amountMistakes = EASY_MISTAKES_LIMIT;

    public GameLogic(InputStream in, PrintStream out) {
        this.reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        this.out = out;
    }

    public GameLogic() {

    }

    private void chooseCategory() {
        out.println(MATH_CATEGORY + " - Математика   " + MEDICINE_CATEGORY + " - Медицина   "
            + CITIES_CATEGORY + " - Города мира   " + STATES_CATEGORY + " - Штаты   "
            + COUNTRIES_CATEGORY + " - Страны");
        out.println("Напиши соответсвующую цифру либо просто нажми 'Enter' "
            + "или любое другое число для выбора случайной категории.");
    }

    private void firstWords() {
        out.println("Здравствуй! Ты зашел в игру 'Виселица', "
            + "укажи категорию слов, которая нравится больше всего из следующих:");
        chooseCategory();
    }

    private void wrongInputCategory() {
        out.println("Введите категорию (число) или нажмите пробел для выбора случайной категории:");
        chooseCategory();
    }

    private void wrongInputLevel() {
        out.println("Введите количество допустимых ошибок (число) или "
            + "нажмите пробел для выбора случайного уровня сложности:");
    }

    private void chooseLevel() {
        out.println(
            "Теперь выбери уровень сложности из трех(введи соответсвующее "
                + "количество ошибок), число справа "
                + "обозначает количество допустимых ошибок");
        out.println("Легкий - " + EASY_MISTAKES_LIMIT + " ошибок   Средний - "
            + MEDIUM_MISTAKES_LIMIT + " ошибок  Сложный - "
            + HARD_MISTAKES_LIMIT + " ошибок");
        out.println("Просто нажми 'Enter' для выбора случайного уровня сложности.");
    }

    private String getCategoryFromNumber(int number) {
        return switch (number) {
            case MATH_CATEGORY -> "Математика";
            case MEDICINE_CATEGORY -> "Медицина";
            case CITIES_CATEGORY -> "Города мира";
            case STATES_CATEGORY -> "Штаты";
            case COUNTRIES_CATEGORY -> "Страны";
            default -> "";
        };
    }

    public boolean start() throws IOException {
        firstWords();
        String categoryString = "";
        String categoryNumber;
        boolean validInput = false; // Флаг для выхода из цикла

        while (!validInput) { // Цикл будет работать, пока ввод не станет валидным
            categoryNumber = reader.readLine();

            if (categoryNumber.trim().isEmpty()) {
                categoryString = ""; // Например, если пробел - выбираем случайную категорию
                validInput = true; // Ввод корректный, выходим из цикла
            } else {
                try {
                    categoryString = getCategoryFromNumber(Integer.parseInt(categoryNumber));
                    validInput = true; // Если число корректное, выходим из цикла
                } catch (NumberFormatException e) {
                    wrongInputCategory(); // Если ошибка ввода, сообщаем и продолжаем цикл
                }
            }
        }
        Category selectedCategory = new WordsCategory(categoryString).getCategory();
        while (selectedCategory == null) {
            wrongInputCategory();
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

        chooseLevel();
        boolean flag = false;
        while (!flag) {
            this.level = reader.readLine();
            flag = defineAmountMistakes();
        }
        return true;
    }

    public boolean defineAmountMistakes() {
        switch (this.level) {
            case "7" ->
                this.amountMistakes = EASY_MISTAKES_LIMIT;
            case "6" ->
                this.amountMistakes = MEDIUM_MISTAKES_LIMIT;
            case "5" ->
                this.amountMistakes = HARD_MISTAKES_LIMIT;
            case "" ->
                this.amountMistakes = RANDOM.nextInt(HARD_MISTAKES_LIMIT, EASY_MISTAKES_LIMIT);
            default -> {
                wrongInputLevel();
                return false;
            }
        }
        return true;
    }

    private void printAlphabet(List<Character> mistakesElements, List<Character> usedElements) {
        for (Character element : alphabet) {
            if (mistakesElements.contains(element) || usedElements.contains(element)) {
                continue;
            } else {
                out.print(element + " ");
            }
        }
        out.println();
    }

    public boolean lenCheck(String element) {
        return element.length() == 1;
    }

    public void play() throws IOException {
        out.println("В любой момент игры ты можешь получить подсказку(кроме некорректных вводов), "
            + "просто напиши слово '" + HINT_COMMAND + "', но учти, что данная опция работает только один раз.");
        out.println("Категория: " + this.category + ", Количество попыток: " + this.amountMistakes);
        int mistakesCount = 0;
        int hintsCount = 0;
        GameVisualizator visualizator = new GameVisualizator(out, word);

        List<Integer> guessedIndexes = new ArrayList<>();
        List<Character> mistakesElements = new ArrayList<>();
        List<Character> usedElements = new ArrayList<>(); //store the element, which was used

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
            while (!lenCheck(stringElement)) {
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
        } else {
            visualizator.printWord(guessedIndexes);
            out.println("Поздравляем! Все верно!");
        }
    }

}
