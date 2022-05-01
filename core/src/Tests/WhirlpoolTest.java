package Tests;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;
import com.mygdx.pirategame.GameObject.Enemy.Whirlpool;

import static org.junit.Assert.*;


public class WhirlpoolTest {

    @Test
    public void SpawnLocationTest(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        Whirlpool whirlpool = new Whirlpool(Gscreen,10,10,"Whirlpool.png");
        assertTrue("Spawning whirlpool", whirlpool.getX() == 10 && whirlpool.getY() == 10);
    }

    @Test
    public void animation(){
        PirateGame p = new PirateGame();
        SpriteBatch b = new SpriteBatch();
        GameScreen Gscreen = new GameScreen();
        Whirlpool whirlpool = new Whirlpool(Gscreen,10,10,"Whirlpool.png");
        int currentindex = whirlpool.getAnimationIndex();
        whirlpool.draw(b);
        assertTrue("animation has changed", currentindex != whirlpool.getAnimationIndex());

    }

    @Test
    public void ContactTest(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen();
        Whirlpool whirlpool = new Whirlpool(Gscreen,10,10,"Whirlpool.png");
        Player player = new Player(Gscreen);
        whirlpool.onContact();
        assertTrue("Player contact", player.getV2() != 0);
    }

}