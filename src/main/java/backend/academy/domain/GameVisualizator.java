package backend.academy.domain;

import lombok.Setter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;


public class GameVisualizator {
    private PrintStream out;
    private String word;
    public GameVisualizator(PrintStream out, String word) {
        this.out = out;
        this.word = word;
    }
    public void printOne() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |    ");
        out.println("  |    ");
        out.println("  |    ");
        out.println("  |");
        out.println("__|__");
    }
    public void printTwo() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |    ");
        out.println("  |    ");
        out.println("  |");
        out.println("__|__");
    }
    public void printThree() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |     |");
        out.println("  |    ");
        out.println("  |");
        out.println("__|__");
    }
    public void printFour() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |    /|\\");
        out.println("  |    ");
        out.println("  |");
        out.println("__|__");
    }
    public void printFive() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |    /|\\");
        out.println("  |    / \\");
        out.println("  |");
        out.println("__|__");
    }
    public void printSix() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |    /|\\");
        out.println("  |    / \\");
        out.println("  |");
        out.println("__|__");
    }
    public void printSeven() {
        out.println("  _______");
        out.println("  |     |");
        out.println("  |     O");
        out.println("  |    /|\\");
        out.println("  |    / \\");
        out.println("  |");
        out.println("__|__");
    }
    public void print(int mistakesCount) {
        switch (mistakesCount) {
            case 1:
                printOne();
                break;
            case 2:
                printTwo();
                break;
            case 3:
                printThree();
                break;
            case 4:
                printFour();
                break;
            case 5:
                printFive();
                break;
            case 6:
                printSix();
                break;
            case 7:
                printSeven();
                break;
            default:
                break;
        }
    }

}
