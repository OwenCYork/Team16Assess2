package com.mygdx.pirategame.Tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;

import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.SkillTree;
import com.mygdx.pirategame.GameObject.Enemy.EnemyShip;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;


@RunWith(GdxTestRunner.class)
public class ShipGeneration{
    @Test
    public void ShipExists(){
        PirateGame p = new PirateGame();
        ArrayList<EnemyShip> ships = p.gameScreen.getShips();
        assertTrue("Do Ships Exist",ships!=null);
    }

    @Test
    public void ShipLocationRandom(){
        PirateGame p = new PirateGame();
        ArrayList<EnemyShip> ships = p.gameScreen.getShips();
        float xdiff = ships.get(1).getX()-ships.get(0).getX();
        float ydiff = ships.get(1).getY()-ships.get(0).getY();
        //not created using a 'step'
        assertTrue("Ships randomised",ships.get(2).getX()!=(ships.get(1).getX()+xdiff) && ships.get(2).getY()!=(ships.get(1).getY()+ydiff));
    }

    @Test
    public void ShipOverlap(){
        PirateGame p = new PirateGame();
        ArrayList<EnemyShip> ships = p.gameScreen.getShips();
        float xdiff = ships.get(1).getX()-ships.get(0).getX();
        float ydiff = ships.get(1).getY()-ships.get(0).getY();
        //not identical
        assertTrue("Ships not ontop of each over",xdiff!=0 && ydiff!=0);
    }
}