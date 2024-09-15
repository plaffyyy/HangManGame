package backend.academy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

public class LogicCheck {
    @ParameterizedTest
    @CsvSource({"asdasd", "математка"})
    void categoryCheck(String first) {
        Category category = new WordsCategory(first).getCategory();
        assertThat(category).isNull();
    }

    @Test
    void inputLengthCheck() {
        String rightElement = "a";
        String[] elements = new String[4];
        elements[0] = "as";
        elements[1] = "";
        elements[2] = "a";
        elements[3] = "asd";
        int ind = 0;
        while (elements[ind].length() != 1) {
            ind++;
        }
        assertThat(rightElement).isEqualTo(elements[ind]);
    }

    @Test
    void printWordCheck() {
        String word = "abcdefgh";
        String ans = "_bc__f_h";
        String check = "";
        ArrayList<Integer> guessedIndexes = new ArrayList<>(Arrays.asList(1, 2, 5, 7));
        for (int i = 0; i < word.length(); i++) {
            if (guessedIndexes.contains(i)) {
                check += word.charAt(i);
            } else {
                check += "_";
            }
        }
        assertThat(ans).isEqualTo(check);
    }
}
