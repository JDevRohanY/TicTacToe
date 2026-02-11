package src.TicTacToe.models;

import src.TicTacToe.models.enums.BotDifficultyLevel;
import src.TicTacToe.models.enums.PlayerType;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel){
        super(name, id, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
