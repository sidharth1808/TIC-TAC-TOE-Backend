package com.pm.tictac.models;

import com.pm.tictac.factory.BotPlayingStratergyFactory;
import com.pm.tictac.stratergies.botPlayingStratergies.BotPlayingStratergy;

public class Bot extends Player {

    private BotDifficultyLevel botDifficultyLevel;
private BotPlayingStratergy botPlayingStratergy;
   public Bot(String name,Symbol symbol,BotDifficultyLevel botDifficultyLevel) {
       super(name,PlayerType.BOT,symbol);
 this.botDifficultyLevel = botDifficultyLevel;
 this.botPlayingStratergy = BotPlayingStratergyFactory.getBotPlayingStratergy(botDifficultyLevel);


   }
    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
    @Override
    public Move makeMove(Board board){
        Move move = botPlayingStratergy.makeMove(board);
        move.setPlayer(this);
        return move;
    }

}
