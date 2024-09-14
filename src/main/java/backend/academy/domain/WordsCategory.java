package backend.academy.domain;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Getter
class WordsCategory {
    private List<String> categories = new ArrayList<>(Arrays.asList("города мира", "страны", "математика", "штаты", "медицина"));
    private String category;
    private Random randomGenerator;
    public WordsCategory (String category) {
        this.randomGenerator = new Random();
        if (category.equals("")) {
            int index = randomGenerator.nextInt(this.categories.size());
            this.category = this.categories.get(index);
        } else {
            this.category = category.toLowerCase();
        }
    }

    public Category getCategory() {
        switch (category) {
            case "математика": return new Math();
            case "города мира": return new City();
            case "страны": return new Country();
            case "штаты": return new State();
            case "медицина": return new Medicine();
            default: return null;
        }
    }

}
