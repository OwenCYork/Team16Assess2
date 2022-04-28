package Tests;

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
        Hud h = (new GameScreen(p)).getHud();
        int startingHealth = h.getHealth();
        (new GameScreen(p)).changeActivePowerup(1);
        assertTrue("Health is increased by 100 if a Repair Powerup is activated", h.getHealth() == startingHealth+100);
    }

    @Test 
    public void damageIncreaseDamageDealt(){
        PirateGame p = new PirateGame();
        int startingDamage = (new GameScreen(p)).getChangeDamage();
        (new GameScreen(p)).changeActivePowerup(2);
        assertTrue("Damage is increased by 10 if a Damage Powerup is activated", (new GameScreen(p)).getChangeDamage() == startingDamage+10);
    }
    @Test
    public void maxSpeedIncreased(){
        PirateGame p = new PirateGame();        
        float startingSpeed = (new GameScreen(p)).getMaxSpeed();
        (new GameScreen(p)).changeActivePowerup(3);
        assertTrue("Maximum speed is increased by 30% if a Movement Speed Powerup is activated", (new GameScreen(p)).getMaxSpeed() == startingSpeed*1.3f);
    }
    @Test
    public void accelerationIncreased(){
        PirateGame p = new PirateGame();        
        float startingAcceleration = (new GameScreen(p)).getAcceleration();
        (new GameScreen(p)).changeActivePowerup(3);
        assertTrue("Acceleration is increased by 30% if a Movement Speed Powerup is activated", (new GameScreen(p)).getAcceleration() == startingAcceleration*1.3f);
    }
    @Test
    public void attackSpeedIncreased(){
        PirateGame p = new PirateGame();        
        float startingAtkSpeed = (new GameScreen(p)).getReloadDelay();
        (new GameScreen(p)).changeActivePowerup(4);
        assertTrue("The delay between attacks is reduced by 0.5 seconds (to a minimum of 0.01 seconds)", (new GameScreen(p)).getReloadDelay()==startingAtkSpeed-0.5 || (new GameScreen(p)).getReloadDelay() == 0.01);
    }
    @Test
    public void immunityToDamage(){
        PirateGame p = new PirateGame();
        Hud h = (new GameScreen(p)).getHud();
        int startingHealth = h.getHealth();
        (new GameScreen(p)).changeActivePowerup(5);
        EnemyFire cannonBall = new EnemyFire((new GameScreen(p)), (new GameScreen(p)).getPOSX(),(new GameScreen(p)).getPOSY());
        cannonBall.update(1);
        assertTrue("The player has taken no damage whilst the Immunity powerup is active",h.getHealth() == startingHealth);
    }


    @Test
    public void powerupsSpawned(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen(p));
        assertTrue("Some powerups have spawned",!g.getPowerups().isEmpty());
    }
}