package Tests;

import com.badlogic.gdx.Gdx;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.Menu.SkillTree;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;


public class LevelChoiceTests {
    /*
    @Test
    public void DifficultyExistance(){
        //PirateGame p = new PirateGame();
        assertTrue("LevelChoice screen Exist", Gdx.files.internal("LevelChoice.java").exists());
    }*/


    @Test
    public void LevelEasy(){
        //PirateGame p = new PirateGame();
        LevelChoice.Level=1;
        Hud.maxHealth = 100;
        Hud.healthRegen = 1;
        Hud.health = 100;
        Hud.coins = 0;
        Hud.coinMulti = 1;
        Hud.setLevel();
        assertTrue("Coins are set to 100 on easy",Hud.coins==100);
        assertTrue("Max health is set to 200 on easy", Hud.GetMaxHealth()==200);
        assertTrue("Health Regen is set to 4 on easy",Hud.healthRegen == 4);
    }

    @Test
    public void LevelHard(){

        LevelChoice.Level=3;
        Hud.health = 100;
        Hud.maxHealth = 100;
        Hud.setLevel();
        assertTrue("Health is set to 50 on hard",Hud.GetMaxHealth()==50);
    }
    


}