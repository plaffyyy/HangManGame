package backend.academy.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WordsCategory {
    private List<String> categories = new ArrayList<>(Arrays.asList("города мира", "страны", "математика", "штаты", "медицина"));
    String category;
    WordsCategory (String category) {
        this.category = category;
    }


}
