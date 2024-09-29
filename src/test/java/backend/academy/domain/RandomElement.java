package backend.academy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;


public class RandomElement {
    @Test
    void randomCategory() {
        String emptyString = "";
        WordsCategory category = new WordsCategory(emptyString);
        assertThat(category.categories()).contains(category.category());
    }
    @Test
    void randomWord() {
        Math math = new Math();
        assertThat(math.mathTerms().keySet()).contains(math.getElement().getKey());
    }
    @ParameterizedTest
    @CsvSource({"легкий,7", "средний,6", "сложный,5"})
    void checkLevel(String level, String amountMistakes)  {
        GameLogic gameLogic = new GameLogic();
        gameLogic.level(level);
        gameLogic.defineAmountMistakes();
        assertThat(Integer.parseInt(amountMistakes)).isEqualTo(gameLogic.amountMistakes());
    }
}
