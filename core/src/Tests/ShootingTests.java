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
    @Test
    public void cannonBallSpawns(){
        PirateGame p = new PirateGame();
        (new GameScreen(p)).playerFire();
        assertTrue("A cannonball is spawned when the player fires",!(new GameScreen(p)).GetPlayer().getCannonBalls().isEmpty());
    }
    @Test
    public void cannonHasToReload(){
        PirateGame p = new PirateGame();
        (new GameScreen(p)).playerFire();
        assertTrue("The cannon has to reload before being fired again",(new GameScreen(p)).getTimeToReload()!=0);
    }
    @Test
    public void cannonReloadsOverTime(){
        PirateGame p = new PirateGame();
        (new GameScreen(p)).playerFire();
        float startingTime = (new GameScreen(p)).getTimeToReload();
        (new GameScreen(p)).update(0.2f);
        assertTrue("The cannon is closer to being reloaded after a game tick",(new GameScreen(p)).getTimeToReload()<startingTime);
    }
    @Test
    public void cannonWontFireIfJammed(){
        PirateGame p = new PirateGame();
        (new GameScreen(p)).setCannonJammed(true);
        (new GameScreen(p)).setTimeToReload(1.0f);
        (new GameScreen(p)).update(1.0f);
        (new GameScreen(p)).playerFire();
        assertTrue("A cannonball is not spawned when the player fires if the cannon is jammed",(new GameScreen(p)).GetPlayer().getCannonBalls().isEmpty());

    }
    @Test
    public void playerCanFireAgainAfterReload(){
        PirateGame p = new PirateGame();
        (new GameScreen(p)).playerFire();
        float startingTime = (new GameScreen(p)).getTimeToReload();
        (new GameScreen(p)).update((new GameScreen(p)).getTimeToReload());
        (new GameScreen(p)).playerFire();
        assertTrue("The cannon has been fired a second time and the reload time has been reset",startingTime == (new GameScreen(p)).getTimeToReload());
    }
}
