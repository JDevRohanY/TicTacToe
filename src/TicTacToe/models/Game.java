package src.TicTacToe.models;

import src.TicTacToe.exceptions.DuplicateSymbolFoundException;
import src.TicTacToe.exceptions.InvalidPlayerCountException;
import src.TicTacToe.exceptions.MultipleBotCountException;
import src.TicTacToe.models.enums.CellState;
import src.TicTacToe.models.enums.GameState;
import src.TicTacToe.models.enums.PlayerType;
import src.TicTacToe.stratergy.gameWinningStratergy.ColumnWinningStrategy;
import src.TicTacToe.stratergy.gameWinningStratergy.DiagnolWinningStrategy;
import src.TicTacToe.stratergy.gameWinningStratergy.GameWinningStrategy;
import src.TicTacToe.stratergy.gameWinningStratergy.RowWinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private int dimension;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private int nextPlayerMoveIndex;
    private GameState gameState;
    private List<GameWinningStrategy> winningStrategies;

    public Game(int dimension, List<Player> players, List<GameWinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<GameWinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<GameWinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void displayBoard() {
        board.printBoard();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> move) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void makeMove() {
        Player currPlayer = players.get(getNextPlayerMoveIndex());

        System.out.println("It is " + currPlayer.getName() + "'s move.");

        Move move = currPlayer.makeMove(board);

        //validate the move
        boolean isValidMove = validateMove(move, board);

        //Make a move
        if (isValidMove) {
            int row = move.getCell().getRow();
            int col = move.getCell().getCol();

            Cell cell = board.getBoard().get(row).get(col);

            cell.setCellState(CellState.FILLED);
            cell.setPlayer(currPlayer);

            Move finalMove = new Move(currPlayer, cell);
            moves.add(finalMove);

            nextPlayerMoveIndex += 1;
            nextPlayerMoveIndex %= players.size();
            setNextPlayerMoveIndex(nextPlayerMoveIndex);

            //check winner
            if (checkWinner(board, finalMove)) {
                gameState = GameState.ENDED;
                setWinner(currPlayer);
            } else if (moves.size() == board.getSize() * board.getSize()) {
                gameState = GameState.DRAW;
            }
        }
    }

    private boolean checkWinner(Board board, Move move) {
        for(GameWinningStrategy gameStrategy : winningStrategies){
            return gameStrategy.checkWinner(board, move);
        }
        return false;
    }

    private boolean validateMove(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);

        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() && cell.isEmpty();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validateCount() throws InvalidPlayerCountException {
            if (players.size() != dimension - 1) {
                throw new InvalidPlayerCountException("Number of players should be 1 less than the dimension of the board.");
            }
        }

        private void validateSymbol() throws DuplicateSymbolFoundException {
            HashSet<Character> symbolSet = new HashSet<>();
            for (Player p : players) {
                symbolSet.add(p.getSymbol().getaChar());
            }
            if (symbolSet.size() != players.size()) {
                throw new DuplicateSymbolFoundException("Two players have duplicate symbol, invalid input.");
            }
        }

        private void validateBotCount() throws MultipleBotCountException {
            int botCount = 0;
            for (Player p : players) {
                if (p.getPlayerType() == PlayerType.BOT) {
                    botCount++;
                }
                if (botCount > 1) {
                    throw new MultipleBotCountException("Multiple bots found, only 1 bot is allowed per game.");
                }
            }
        }

        private void validateGame(int dimension, List<Player> players) throws InvalidPlayerCountException, DuplicateSymbolFoundException, MultipleBotCountException {
            validateCount();
            validateSymbol();
            validateBotCount();
        }

        public Game build() throws InvalidPlayerCountException, MultipleBotCountException, DuplicateSymbolFoundException {
            // validations
            validateGame(dimension, players);
            return new Game(dimension, players, List.of(
                    new RowWinningStrategy(),
                    new ColumnWinningStrategy(),
                    new DiagnolWinningStrategy()
            ));
        }
    }
}
