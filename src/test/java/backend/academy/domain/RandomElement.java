package backend.academy.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}
