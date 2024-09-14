package backend.academy.domain;

import java.io.PrintStream;

public class GameVisualizator {
    private PrintStream out;
    private String word;
    private String hint;
    private int amountMistakes;
    public GameVisualizator(PrintStream out, String word,
        String hint, int amountMistakes) {
        this.out = out;
        this.word = word;
        this.hint = hint;
        this.amountMistakes = amountMistakes;
    }
    public void print() {

    }

}
