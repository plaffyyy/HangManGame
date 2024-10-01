package backend.academy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class LogicCheck {
    @ParameterizedTest
    @CsvSource({"asdasd", "математка"})
    void categoryCheck(String first) {
        Category category = new WordsCategory(first).getCategory();
        assertThat(category).isNull();
    }
    @ParameterizedTest
    @CsvSource({"ам, false", "аь, false", "а, true"})
    void inputLengthCheck(String element, String right) throws IOException {
        boolean value = Boolean.valueOf(right);
        GameLogic gameLogic = new GameLogic();
        assertThat(gameLogic.lenCheck(element)).isEqualTo(value);
    }

    @Test
    void printWordCheck() {
        GameVisualizator gameVisualizator =
            new GameVisualizator("abcdefgh");
        List<Integer> indexes =
            new ArrayList<>(Arrays.asList(1, 2, 5, 7));
        String print = "_bc__f_h";
        assertThat(gameVisualizator.printWordTest(indexes)).isEqualTo(print);
    }
}
