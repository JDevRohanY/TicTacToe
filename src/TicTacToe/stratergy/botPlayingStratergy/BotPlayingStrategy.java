package src.TicTacToe.stratergy.botPlayingStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
