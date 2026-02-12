package src.TicTacToe.stratergy.botPlayingStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Cell;
import src.TicTacToe.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for (List<Cell> cells : board.getBoard()) {
            for (Cell cell : cells) {
                if (cell.isEmpty()) {
                    return new Move(
                            null,
                            cell
                    );
                }
            }
        }
        return null;
    }
}
