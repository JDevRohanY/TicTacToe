package src.TicTacToe.stratergy.gameWinningStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Move;
import src.TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements GameWinningStrategy {
    Map<Symbol, Integer> leftDiagonalMap = new HashMap<>();
    Map<Symbol, Integer> rightDiagonalMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Symbol symbol = move.getPlayer().getSymbol();

        if (row == col) {
            //Move is present on left diagonal.
            if (!leftDiagonalMap.containsKey(symbol)) {
                leftDiagonalMap.put(symbol, 1);
            } else {
                leftDiagonalMap.put(symbol,
                        leftDiagonalMap.get(symbol) + 1);
            }

            if (leftDiagonalMap.get(symbol) == board.getSize()) {
                return true;
            }
        }

        if (row + col == board.getSize() - 1) {
            //right diagonal

            if (!rightDiagonalMap.containsKey(symbol)) {
                rightDiagonalMap.put(symbol, 1);
            } else {
                rightDiagonalMap.put(symbol,
                        rightDiagonalMap.get(symbol) + 1);
            }

            return rightDiagonalMap.get(symbol) == board.getSize();
        }

        return false;
    }
}
