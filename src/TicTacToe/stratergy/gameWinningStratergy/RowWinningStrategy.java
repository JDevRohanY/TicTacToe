package src.TicTacToe.stratergy.gameWinningStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Move;
import src.TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements GameWinningStrategy {
    Map<Integer, Map<Symbol, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(col)){
            rowMap.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> currentRowMap = rowMap.get(col);

        if(!currentRowMap.containsKey(symbol)){
            currentRowMap.put(symbol, 1);
        }else{
            currentRowMap.put(symbol, currentRowMap.get(symbol) + 1);
        }

        return currentRowMap.get(symbol)==board.getSize();
    }
}
