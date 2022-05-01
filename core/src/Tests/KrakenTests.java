package Tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pirategame.GameObject.AvailableSpawn;
import com.mygdx.pirategame.GameObject.College.College;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.SkillTree;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.GameScreen;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class KrakenTests {/*
    @Test
    public void Spawn(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        College kraken = new College(null, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        Float[] XY = {kraken.getX(),kraken.getY()};
        assertTrue("Spawning XY = ", XY.equals(new float[] {3560 / PirateGame.PPM, 4767 / PirateGame.PPM}));
    }

    @Test
    public void Killed(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        GameScreen gScreen = new GameScreen();
        College kraken = new College(gScreen, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        kraken.health = 0;
        assertTrue("kraken dead",kraken.destroyed == true);
    }

    @Test
    public void Shot(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        GameScreen gScreen = new GameScreen();
        College kraken = new College(gScreen, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        kraken.onContact();
        assertTrue("Kraken health = 90", kraken.health == 90);
    }

    @Test
    public void Shoot(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        GameScreen gScreen = new GameScreen();
        College kraken = new College(gScreen, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        kraken.fire();
        assertTrue("Kraken fire", kraken.isCannonballsEmpty() == false);
    }

    @Test
    public void SpawnShips(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        GameScreen gScreen = new GameScreen();
        College kraken = new College(null, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        assertTrue("Kraken fleet",kraken.fleet.isEmpty() == false);
    }

    @Test
    public void GiveReward(){
        PirateGame p = new PirateGame();
        AvailableSpawn invalidSpawn = new AvailableSpawn();
        GameScreen gScreen = new GameScreen();
        int coins = 0;
        SkillTree sktree = new SkillTree(p);
        Hud hud = new Hud(new SpriteBatch());
        College kraken = new College(gScreen, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,"Kraken.png", "GhostShip.png", 8, invalidSpawn);
        kraken.health = 0;
        assertTrue("kraken dead",(hud.getHUDCoins() == coins+20 && hud.getPoints() ==100));
    }*/
}

