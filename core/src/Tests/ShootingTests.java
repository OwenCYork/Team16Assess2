package Tests;
import com.mygdx.pirategame.PirateGame;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.GameObject.Enemy.EnemyFire;
import com.mygdx.pirategame.Menu.Hud;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class ShootingTests {
    /*@Test
    public void cannonBallSpawns(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        g.playerFire();
        assertTrue("A cannonball is spawned when the player fires",!g.GetPlayer().getCannonBalls().isEmpty());
    }*/
    @Test
    public void cannonHasToReload(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        g.playerFire();
        assertTrue("The cannon has to reload before being fired again",g.getTimeToReload()!=0);
    }
    @Test
    public void cannonReloadsOverTime(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        g.playerFire();
        float startingTime = g.getTimeToReload();
        g.reduceReload(0.2f);
        assertTrue("The cannon is closer to being reloaded after a game tick",g.getTimeToReload()<startingTime);
    }
    /*@Test
    public void cannonWontFireIfJammed(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        g.setCannonJammed(true);
        g.setTimeToReload(1.0f);
        g.reduceReload(1.0f);
        g.playerFire();
        assertTrue("A cannonball is not spawned when the player fires if the cannon is jammed",(new GameScreen()).GetPlayer().getCannonBalls().isEmpty());

    }*/
    @Test
    public void playerCanFireAgainAfterReload(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        g.playerFire();
        float startingTime = g.getTimeToReload();
        g.reduceReload(5.0f);
        g.playerFire();
        assertTrue("The cannon has been fired a second time and the reload time has been reset",startingTime <= g.getTimeToReload());
    }
}
