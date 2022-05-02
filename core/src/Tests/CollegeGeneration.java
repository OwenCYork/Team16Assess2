package Tests;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import java.util.HashMap;
import com.mygdx.pirategame.GameObject.College.College;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;

//@RunWith(GdxTestRunner.class)
public class CollegeGeneration{
/*
    @Test
    public void CollegesExist(){
        PirateGame p = mock(PirateGame.class);
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        GameScreen g = mock(GameScreen.class);



        HashMap<String,College> colleges = g.getCollages();
        assertTrue("Do Colleges Exist",!g.getCollages().isEmpty());
    }

    @Test
    public void EachCollegeExists(){
        PirateGame p = new PirateGame();
        String[] collegeNames = {"Alcuin","Anne Lister","Constantine","Goodricke","Kraken"};
        Boolean notNull = true;
        GameScreen g = new GameScreen();
        for (String name : collegeNames) {
            notNull = notNull && ((g).getCollege(name)!=null);
        }
        assertTrue("Do All named Colleges Exist",notNull==true);
    }
   
    @Test
    public void CheckLocation(){
        PirateGame p = new PirateGame();
        String[] collegeNames = {"Alcuin","Anne Lister","Constantine","Goodricke","Kraken"};
        float[][] locations = {
            {1900 / PirateGame.PPM, 2100 / PirateGame.PPM},
            {6304 / PirateGame.PPM, 1199 / PirateGame.PPM},
            {6240 / PirateGame.PPM, 6703 / PirateGame.PPM},
            {1760 / PirateGame.PPM, 6767 / PirateGame.PPM},
            {3560 / PirateGame.PPM, 4767 / PirateGame.PPM}
        };
        GameScreen g = new GameScreen();
        Boolean correctLocation = true;
        for (int i=0;i<collegeNames.length;i++) {
            College c = (g).getCollege(collegeNames[i]);
            //p.gameScreen
            float xdiff = locations[i][0]-c.getX();
            float ydiff = locations[i][1]-c.getY();
            correctLocation = correctLocation && (xdiff==0 && ydiff==0);
        }
        assertTrue("Do All named Colleges in correct location",correctLocation==true);
    }
*/
}