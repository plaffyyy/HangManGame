package backend.academy.domain;


import java.io.IOException;


public class GameVizualizator {
    public static void main(String[] args) throws IOException {
        GameLogic game = new GameLogic(System.in, System.out);
        game.start();

    }


}
