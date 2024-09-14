package backend.academy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
}
