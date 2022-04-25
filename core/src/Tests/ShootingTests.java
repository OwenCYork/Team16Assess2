package Tests;
import com.mygdx.pirategame.PirateGame;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.GameObject.Enemy.EnemyFire;
import com.mygdx.pirategame.Menu.Hud;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

//@RunWith(GdxTestRunner.class)
public class ShootingTests {
    @Test
    public void cannonBallSpawns(){
        PirateGame p = new PirateGame();
        p.gameScreen.playerFire();
        assertTrue("A cannonball is spawned when the player fires",!p.gameScreen.GetPlayer().getCannonBalls().isEmpty());
    }
    @Test
    public void cannonHasToReload(){
        PirateGame p = new PirateGame();
        p.gameScreen.playerFire();
        assertTrue("The cannon has to reload before being fired again",p.gameScreen.getTimeToReload()!=0);
    }
    @Test
    public void cannonReloadsOverTime(){
        PirateGame p = new PirateGame();
        p.gameScreen.playerFire();
        float startingTime = p.gameScreen.getTimeToReload();
        p.gameScreen.update(0.2f);
        assertTrue("The cannon is closer to being reloaded after a game tick",p.gameScreen.getTimeToReload()<startingTime);
    }
    @Test
    public void cannonWontFireIfJammed(){
        PirateGame p = new PirateGame();
        p.gameScreen.setCannonJammed(true);
        p.gameScreen.setTimeToReload(1.0f);
        p.gameScreen.playerFire();
        assertTrue("A cannonball is not spawned when the player fires if the cannon is jammed",p.gameScreen.GetPlayer().getCannonBalls().isEmpty());

    }
    @Test
    public void playerCanFireAgainAfterReload(){
        PirateGame p = new PirateGame();
        p.gameScreen.playerFire();
        float startingTime = p.gameScreen.getTimeToReload();
        p.gameScreen.update(p.gameScreen.getTimeToReload());
        p.gameScreen.playerFire();
        assertTrue("The cannon has been fired a second time and the reload time has been reset",startingTime == p.gameScreen.getTimeToReload());
    }
}
