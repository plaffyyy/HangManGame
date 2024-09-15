package backend.academy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.Getter;

@Getter
public class WordsCategory {
    private static final int INDEX_CITY = 0;
    private static final int INDEX_COUNTRY = 1;
    private static final int INDEX_MATH = 2;
    private static final int INDEX_STATE = 3;
    private static final int INDEX_MEDICINE = 4;

    private List<String> categories =
        new ArrayList<>(Arrays.asList("города мира", "страны", "математика", "штаты", "медицина"));
    private String category;
    private Random randomGenerator;

    public WordsCategory(String category) {
        this.randomGenerator = new Random();
        if (category.equals("")) {
            int index = randomGenerator.nextInt(this.categories.size());
            this.category = this.categories.get(index);
        } else {
            this.category = category.toLowerCase();
        }
    }

    public Category getCategory() {
        int index = categories.indexOf(category);
        return switch (index) {
            case INDEX_CITY -> new City();
            case INDEX_COUNTRY -> new Country();
            case INDEX_MATH -> new Math();
            case INDEX_STATE -> new State();
            case INDEX_MEDICINE -> new Medicine();
            default -> null;
        };
    }

}
