package backend.academy.domain;

import java.io.IOException;

@SuppressWarnings("UncommentedMain")
public final class GameContainer {

    private GameContainer() {

    }

    public static void main(String[] args) throws IOException {
        GameLogic game = new GameLogic(System.in, System.out);
        if (!game.start()) {
            System.exit(0);
        }
        game.defineAmountMistakes();
        game.play();
    }
}
