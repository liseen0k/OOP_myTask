package vsu.oop;

import vsu.oop.model.Game;
import vsu.oop.service.GameService;

public class Application {

    public static void main(String[] args) {
        GameService gameService = new GameService();
        Game game = gameService.createNewGame();
        gameService.play(game);
        String gameAsString = gameService.printGame(game);
        System.out.println(gameAsString);
    }
}
