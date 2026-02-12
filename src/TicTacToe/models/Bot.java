package src.TicTacToe.models;

import src.TicTacToe.factory.BotPlayingStrategyFactory;
import src.TicTacToe.models.enums.BotDifficultyLevel;
import src.TicTacToe.models.enums.PlayerType;
import src.TicTacToe.stratergy.botPlayingStratergy.BotPlayingStrategy;
import src.TicTacToe.stratergy.botPlayingStratergy.EasyBotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(name, id, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
