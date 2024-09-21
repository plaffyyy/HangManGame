package backend.academy.domain;

import java.io.PrintStream;
import java.util.ArrayList;

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


    private PrintStream out;
    private String word;

    public GameVisualizator(PrintStream out, String word) {
        this.out = out;
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
            case MISTAKE_COUNT_ONE:
                printOne();
                break;
            case MISTAKE_COUNT_TWO:
                printTwo();
                break;
            case MISTAKE_COUNT_THREE:
                printThree();
                break;
            case MISTAKE_COUNT_FOUR:
                printFour();
                break;
            case MISTAKE_COUNT_FIVE:
                printFive();
                break;
            case MISTAKE_COUNT_SIX:
                printSix();
                break;
            case MISTAKE_COUNT_SEVEN:
                printSeven();
                break;
            default:
                break;
        }
    }

    public void printWord(ArrayList<Integer> indexes) {
        for (int i = 0; i < this.word.length(); i++) {
            if (indexes.contains(i)) {
                out.print(this.word.charAt(i));
            } else {
                out.print("_");
            }
        }
        out.println();
    }

}
