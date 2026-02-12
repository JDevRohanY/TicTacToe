package src.TicTacToe.controller;

import src.TicTacToe.exceptions.DuplicateSymbolFoundException;
import src.TicTacToe.exceptions.InvalidPlayerCountException;
import src.TicTacToe.exceptions.MultipleBotCountException;
import src.TicTacToe.models.Game;
import src.TicTacToe.models.Player;
import src.TicTacToe.models.enums.GameState;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players) throws InvalidPlayerCountException, MultipleBotCountException, DuplicateSymbolFoundException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }
    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){

    }

    public void displayBoard(Game game){
        game.displayBoard();
    }
}
