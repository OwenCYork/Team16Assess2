package com.mygdx.pirategame.Tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.pirategame.GameObject.Enemy.Whirlpool;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import static org.junit.Assert.assertTrue;

public class PlayerControlTests {
    @Test
    public void playerUp(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen(p);
        Player player = new Player(Gscreen);
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerDown(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen(p);
        Player player = new Player(Gscreen);
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerLeft(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen(p);
        Player player = new Player(Gscreen);
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerRight(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen(p);
        Player player = new Player(Gscreen);
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            Gscreen.handleInput(1);
            assertTrue(player.getV2()!=0);
        }
    }

    @Test
    public void playerShoot(){
        PirateGame p = new PirateGame();
        GameScreen Gscreen = new GameScreen(p);
        Player player = new Player(Gscreen);
        if (Gdx.input.isKeyPressed(Input.Keys.E)){
            Gscreen.handleInput(1);
            assertTrue(player.getCannonBalls().isEmpty() == false);
        }
    }
}
