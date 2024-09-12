package backend.academy.domain;

import lombok.Setter;

@Setter
public class GameLogic {
    private String word;
    private String hint;
    private String level;
    public GameLogic(String word, String hint) {
        this.word = word;
        this.hint = hint;
    }

}
