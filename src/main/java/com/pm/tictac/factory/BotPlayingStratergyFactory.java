package com.pm.tictac.factory;

import com.pm.tictac.models.BotDifficultyLevel;
import com.pm.tictac.stratergies.botPlayingStratergies.BotPlayingStratergy;
import com.pm.tictac.stratergies.botPlayingStratergies.EasyBotPlayingStratergy;

public class BotPlayingStratergyFactory {
    public static BotPlayingStratergy getBotPlayingStratergy(BotDifficultyLevel difficulty) {
        if(difficulty == BotDifficultyLevel.EASY){
            return new EasyBotPlayingStratergy();
        }
        return  null;
    }
}
