package Tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.pirategame.Collectable.Coin;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.World.Islands;
import com.mygdx.pirategame.World.WorldContactListener;
import com.mygdx.pirategame.World.WorldCreator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MapTests {
    /*@Test
    public void mapGeneration(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldCreator worldcreator = new WorldCreator(Gscreen);
        TmxMapLoader maploader = new TmxMapLoader();
        assertTrue("Map creation", Gscreen.getMap() == maploader.load("map.tmx"));

    }

    @Test
    public void collegeLocations(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        //assertTrue("College locations ");
    }

    @Test
    public void playerSpawnLocation(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        Player player = new Player(Gscreen);
        assertTrue("player spawn location", player.getX() == 1200  / PirateGame.PPM && player.getY() == 2500 / PirateGame.PPM);
    }
    @Test
    public void CoinPlayerCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        World world = new World(new Vector2(0, 0), true);
        WorldContactListener wcl = new WorldContactListener();
        world.setContactListener(wcl);
        Coin coin = new Coin(Gscreen,10,10);
        Hud hud = new Hud(new SpriteBatch());
        int currentPoints = hud.getPoints();
        coin.entityContact();
        assertTrue("Coin contact",world.getContactCount()==1 && currentPoints+1 == hud.getPoints());

    }
    @Test
    public void LandPlayerCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        World world = new World(new Vector2(0, 0), true);
        WorldContactListener wcl = new WorldContactListener();
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        int currentHealth = hud.getPlayerHealth();
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1 && hud.getPlayerHealth()==currentHealth-10);
    }
    @Test
    public void LandEnemyCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
    @Test
    public void EnemyPlayerCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
    @Test
    public void CollegeCannonCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
    @Test
    public void EnemyCannonCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
    @Test
    public void CollegeFirePlayerCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
    @Test
    public void EnemyfirePlayerCollision(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        WorldContactListener wcl = new WorldContactListener();
        World world = new World(new Vector2(0, 0), true);
        world.setContactListener(wcl);
        Hud hud = new Hud(new SpriteBatch());
        Islands island = new Islands(Gscreen, new Rectangle());
        island.onContact();
        assertTrue("Coin contact",world.getContactCount()==1);
    }
*/



}
