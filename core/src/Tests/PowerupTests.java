package Tests;

import com.badlogic.gdx.Game;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.GameObject.Enemy.EnemyFire;
import com.mygdx.pirategame.Menu.Hud;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
@RunWith(GdxTestRunner.class)
public class PowerupTests{

    @Test
    public void repairIncreasesHealth(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        Hud.health = 100;
        int startingHealth = Hud.getHealth();
        g.changeActivePowerup(1);
        assertTrue("Health is increased by 100 if a Repair Powerup is activated", Hud.getHealth() == startingHealth+100);
    }

    @Test 
    public void damageIncreaseDamageDealt(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        int startingDamage = g.getChangeDamage();
        g.changeActivePowerup(2);
        assertTrue("Damage is increased by 10 if a Damage Powerup is activated", g.getChangeDamage() == startingDamage+10);
    }
    @Test
    public void maxSpeedIncreased(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        float startingSpeed = g.getMaxSpeed();
        g.changeActivePowerup(3);
        assertTrue("Maximum speed is increased by 30% if a Movement Speed Powerup is activated", g.getMaxSpeed() == startingSpeed*1.3f);
    }
    @Test
    public void accelerationIncreased(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        float startingAcceleration = g.getAcceleration();
        g.changeActivePowerup(3);
        assertTrue("Acceleration is increased by 30% if a Movement Speed Powerup is activated", g.getAcceleration() == startingAcceleration*1.3f);
    }
    @Test
    public void attackSpeedIncreased(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        if(GameScreen.getReloadDelay() != 1.0f){
            GameScreen.changeReloadDelay(GameScreen.getReloadDelay()-1.0f);
        }

        float startingAtkSpeed = g.getReloadDelay();
        g.changeActivePowerup(4);
        assertTrue("The delay between attacks is reduced by 0.5 seconds (to a minimum of 0.01 seconds)", g.getReloadDelay()<=startingAtkSpeed-0.5 || g.getReloadDelay() == 0.01);
    }
    @Test
    public void immunityToDamage(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        Hud.health = 100;
        int startingHealth = Hud.getHealth();
        g.changeActivePowerup(5);
        Hud.AddHealth(-15);

        assertTrue("The player has taken no damage whilst the Immunity powerup is active",Hud.getHealth() == startingHealth);
    }

/*
    @Test
    public void powerupsSpawned(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        g.createCoinsAndPowerups();
        assertTrue("Some powerups have spawned",!g.getPowerups().isEmpty());
    }*/
}