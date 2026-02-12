package src.TicTacToe;

import src.TicTacToe.controller.GameController;
import src.TicTacToe.exceptions.DuplicateSymbolFoundException;
import src.TicTacToe.exceptions.InvalidPlayerCountException;
import src.TicTacToe.exceptions.MultipleBotCountException;
import src.TicTacToe.models.Bot;
import src.TicTacToe.models.Game;
import src.TicTacToe.models.Player;
import src.TicTacToe.models.Symbol;
import src.TicTacToe.models.enums.BotDifficultyLevel;
import src.TicTacToe.models.enums.GameState;
import src.TicTacToe.models.enums.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) throws InvalidPlayerCountException, MultipleBotCountException, DuplicateSymbolFoundException {
        Scanner sc = new Scanner(System.in);
        int dimension = 3;
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Rohan", 1L, new Symbol('X'), PlayerType.HUMAN));
        playerList.add(new Bot("ROB", 2L, new Symbol('O'), BotDifficultyLevel.EASY));
        GameController gameController = new GameController();

        //Build the game
        Game game = gameController.startGame(dimension, playerList);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);

            gameController.makeMove(game);
        }
        gameController.displayBoard(game);

        if (gameController.getGameState(game).equals(GameState.DRAW)) {
            gameController.displayBoard(game);
            System.out.println("Game has DRAWN.");
        } else {
            //Someone has WON the game.
            System.out.println(gameController.getWinner(game).getName() + " has WON the game. Congratulations.");
        }
    }
}
