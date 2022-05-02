package Tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pirategame.GameObject.Enemy.Whirlpool;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;
//import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayerControlTests {
    @Test
    public void playerUp(){
        PirateGame p = new PirateGame();
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        GameScreen Gscreen = new GameScreen();
        Player player = new Player(Gscreen,true);
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerDown(){
        PirateGame p = new PirateGame();
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        GameScreen Gscreen = new GameScreen();
        Player player = new Player(Gscreen,true);
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerLeft(){
        PirateGame p = new PirateGame();
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        GameScreen Gscreen = new GameScreen();
        Player player = new Player(Gscreen,true);
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerRight(){
        PirateGame p = new PirateGame();
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        GameScreen Gscreen = new GameScreen();
        Player player = new Player(Gscreen,true);
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

}
