package src.TicTacToe.stratergy.gameWinningStratergy;

import src.TicTacToe.models.Board;
import src.TicTacToe.models.Move;
import src.TicTacToe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements GameWinningStrategy {
    Map<Integer, Map<Symbol, Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMap.containsKey(col)){
            colMap.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> currentColMap = colMap.get(col);

        if(!currentColMap.containsKey(symbol)){
            currentColMap.put(symbol, 1);
        }else{
            currentColMap.put(symbol, currentColMap.get(symbol) + 1);
        }

        return currentColMap.get(symbol)==board.getSize();
    }
}
