package backend.academy.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class RegisterCheck {

    @ParameterizedTest
    @CsvSource({"математика,Математика", "математика,математика"})
    void categoryRegisterCheck(String first, String second) {
        WordsCategory category = new WordsCategory(second);
        assertThat(first).isEqualTo(category.category());
    }
}
