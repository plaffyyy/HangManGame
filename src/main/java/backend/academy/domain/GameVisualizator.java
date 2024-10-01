package backend.academy.domain;

import java.io.PrintStream;
import java.util.List;

public final class GameVisualizator {
    private static final int MISTAKE_COUNT_ONE = 1;
    private static final int MISTAKE_COUNT_TWO = 2;
    private static final int MISTAKE_COUNT_THREE = 3;
    private static final int MISTAKE_COUNT_FOUR = 4;
    private static final int MISTAKE_COUNT_FIVE = 5;
    private static final int MISTAKE_COUNT_SIX = 6;
    private static final int MISTAKE_COUNT_SEVEN = 7;

    private static final String TOP_BORDER = "  _______";
    private static final String SIDE_BORDER = "  |     |";
    private static final String EMPTY_SIDE = "  |    ";
    private static final String BASE = "__|__";
    private static final String HEAD = "  |     O";
    private static final String BODY = "  |    /|\\";
    private static final String LEGS = "  |    / \\";
    private static final Character EMPTY_ELEMENT = '_';


    private PrintStream out;
    private String word;

    public GameVisualizator(PrintStream out, String word) {
        this.out = out;
        this.word = word;
    }

    public GameVisualizator(String word) {
        this.word = word;
    }

    public void printOne() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(BASE);
    }

    public void printTwo() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(BASE);
    }

    public void printThree() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(SIDE_BORDER);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(BASE);
    }

    public void printFour() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(BODY);
        out.println(EMPTY_SIDE);
        out.println(EMPTY_SIDE);
        out.println(BASE);
    }

    public void printFive() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(BODY);
        out.println(LEGS);
        out.println(EMPTY_SIDE);
        out.println(BASE);
    }

    public void printSix() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(BODY);
        out.println(LEGS);
        out.println(EMPTY_SIDE + " ``");
        out.println(BASE + "    ``");
    }

    public void printSeven() {
        out.println(TOP_BORDER);
        out.println(SIDE_BORDER);
        out.println(HEAD);
        out.println(BODY);
        out.println(LEGS);
        out.println(EMPTY_SIDE + " ```");
        out.println(BASE + "    ______");
    }

    public void print(int mistakesCount) {
        switch (mistakesCount) {
            case MISTAKE_COUNT_ONE -> printOne();
            case MISTAKE_COUNT_TWO -> printTwo();
            case MISTAKE_COUNT_THREE -> printThree();
            case MISTAKE_COUNT_FOUR -> printFour();
            case MISTAKE_COUNT_FIVE -> printFive();
            case MISTAKE_COUNT_SIX -> printSix();
            case MISTAKE_COUNT_SEVEN -> printSeven();
            default -> {
                return;
            }
        }
    }

    public void printWord(List<Integer> indexes) {
        for (int i = 0; i < this.word.length(); i++) {
            if (indexes.contains(i)) {
                out.print(this.word.charAt(i));
            } else {
                out.print(EMPTY_ELEMENT);
            }
        }
        out.println();
    }

    public String printWordTest(List<Integer> indexes) {
        StringBuilder bld = new StringBuilder();
        for (int i = 0; i < this.word.length(); i++) {
            if (indexes.contains(i)) {
                bld.append(this.word.charAt(i));
            } else {
                bld.append(EMPTY_ELEMENT);
            }
        }
        return bld.toString();
    }

}
