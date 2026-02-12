package src.TicTacToe.factory;

import src.TicTacToe.models.enums.BotDifficultyLevel;
import src.TicTacToe.stratergy.botPlayingStratergy.BotPlayingStrategy;
import src.TicTacToe.stratergy.botPlayingStratergy.EasyBotPlayingStrategy;
import src.TicTacToe.stratergy.botPlayingStratergy.HardBotPlayingStrategy;
import src.TicTacToe.stratergy.botPlayingStratergy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)){
            return new HardBotPlayingStrategy();
        }
        return null;
    }
}
