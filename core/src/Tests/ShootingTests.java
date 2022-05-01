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
        (new GameScreen()).playerFire();
        assertTrue("A cannonball is spawned when the player fires",!(new GameScreen()).GetPlayer().getCannonBalls().isEmpty());
    }
    @Test
    public void cannonHasToReload(){
        PirateGame p = new PirateGame();
        (new GameScreen()).playerFire();
        assertTrue("The cannon has to reload before being fired again",(new GameScreen()).getTimeToReload()!=0);
    }
    @Test
    public void cannonReloadsOverTime(){
        PirateGame p = new PirateGame();
        (new GameScreen()).playerFire();
        float startingTime = (new GameScreen()).getTimeToReload();
        (new GameScreen()).update(0.2f);
        assertTrue("The cannon is closer to being reloaded after a game tick",(new GameScreen()).getTimeToReload()<startingTime);
    }
    @Test
    public void cannonWontFireIfJammed(){
        PirateGame p = new PirateGame();
        (new GameScreen()).setCannonJammed(true);
        (new GameScreen()).setTimeToReload(1.0f);
        (new GameScreen()).update(1.0f);
        (new GameScreen()).playerFire();
        assertTrue("A cannonball is not spawned when the player fires if the cannon is jammed",(new GameScreen()).GetPlayer().getCannonBalls().isEmpty());

    }
    @Test
    public void playerCanFireAgainAfterReload(){
        PirateGame p = new PirateGame();
        (new GameScreen()).playerFire();
        float startingTime = (new GameScreen()).getTimeToReload();
        (new GameScreen()).update((new GameScreen()).getTimeToReload());
        (new GameScreen()).playerFire();
        assertTrue("The cannon has been fired a second time and the reload time has been reset",startingTime == (new GameScreen()).getTimeToReload());
    }
}
