package src.TicTacToe.stratergy.gameWinningStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Move;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
}
