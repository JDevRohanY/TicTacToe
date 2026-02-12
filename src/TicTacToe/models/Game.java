package src.TicTacToe.models;

import src.TicTacToe.exceptions.DuplicateSymbolFoundException;
import src.TicTacToe.exceptions.InvalidPlayerCountException;
import src.TicTacToe.exceptions.MultipleBotCountException;
import src.TicTacToe.models.enums.PlayerType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private Board board;
    private int dimension;
    private List<Player> players;
    private List<Move> move;
    private Player winner;
    private int nextPlayerMoveIndex;

    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.move = new ArrayList<>();
        this.nextPlayerMoveIndex = 0;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getDimension() {
        return dimension;
    }

    public void displayBoard(){
        board.printBoard();
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMove() {
        return move;
    }

    public void setMove(List<Move> move) {
        this.move = move;
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
            return new Game(dimension, players);
        }
    }
}
