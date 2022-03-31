package com.mygdx.pirategame.Tests;

import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.Menu.SkillTree;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;


public class LevelChoiceTests {

    @Test
    public void DifficultyExistance(){
        PirateGame p = new PirateGame();
        assertTrue("LevelChoice screen Exist",p.getLevelChoice()!=null);

    }


    @Test
    public void LevelEasy(){
        PirateGame p = new PirateGame();
        LevelChoice levelChoice = p.getLevelChoice();
        levelChoice.Level=1;
        assertTrue("EASY works",Hud.getCoins()==100);
    }

    @Test
    public void LevelHard(){
        PirateGame p = new PirateGame();
        LevelChoice levelChoice = p.getLevelChoice();
        levelChoice.Level=3;
        assertTrue("EASY works",Hud.getHealth()==50);
    }
    


}