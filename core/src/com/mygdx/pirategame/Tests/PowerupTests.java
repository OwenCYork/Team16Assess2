package com.mygdx.pirategame.Tests;

import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.GameObject.Enemy.EnemyFire;
import com.mygdx.pirategame.Menu.Hud;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
//@RunWith(GdxTestRunner.class)
public class PowerupTests{

    @Test
    public void repairIncreasesHealth(){
        PirateGame p = new PirateGame();
        Hud h = p.gameScreen.getHud();
        int startingHealth = h.getHealth();
        p.gameScreen.changeActivePowerup(1);
        assertTrue("Health is increased by 100 if a Repair Powerup is activated", h.getHealth() == startingHealth+100);
    }

    @Test 
    public void damageIncreaseDamageDealt(){
        PirateGame p = new PirateGame();
        int startingDamage = p.gameScreen.getChangeDamage();
        p.gameScreen.changeActivePowerup(2);
        assertTrue("Damage is increased by 10 if a Damage Powerup is activated", p.gameScreen.getChangeDamage() == startingDamage+10);
    }
    @Test
    public void maxSpeedIncreased(){
        PirateGame p = new PirateGame();        
        float startingSpeed = p.gameScreen.getMaxSpeed();
        p.gameScreen.changeActivePowerup(3);
        assertTrue("Maximum speed is increased by 30% if a Movement Speed Powerup is activated", p.gameScreen.getMaxSpeed() == startingSpeed*1.3f);
    }
    @Test
    public void accelerationIncreased(){
        PirateGame p = new PirateGame();        
        float startingAcceleration = p.gameScreen.getAcceleration();
        p.gameScreen.changeActivePowerup(3);
        assertTrue("Acceleration is increased by 30% if a Movement Speed Powerup is activated", p.gameScreen.getAcceleration() == startingAcceleration*1.3f);
    }
    @Test
    public void attackSpeedIncreased(){
        PirateGame p = new PirateGame();        
        float startingAtkSpeed = p.gameScreen.getReloadDelay();
        p.gameScreen.changeActivePowerup(4);
        assertTrue("The delay between attacks is reduced by 0.5 seconds (to a minimum of 0.01 seconds)", p.gameScreen.getReloadDelay()==startingAtkSpeed-0.5 || p.gameScreen.getReloadDelay() == 0.01);
    }
    @Test
    public void immunityToDamage(){
        PirateGame p = new PirateGame();
        Hud h = p.gameScreen.getHud();
        int startingHealth = h.getHealth();
        p.gameScreen.changeActivePowerup(5);
        EnemyFire cannonBall = new EnemyFire(p.gameScreen, p.gameScreen.getPOSX(),p.gameScreen.getPOSY());
        cannonBall.update(1);
        assertTrue("The player has taken no damage whilst the Immunity powerup is active",h.getHealth() == startingHealth);
    }


    @Test
    public void powerupsSpawned(){
        PirateGame p = new PirateGame();
        GameScreen g = p.gameScreen;
        assertTrue("Some powerups have spawned",!g.getPowerups().isEmpty());
    }
}