package Tests;

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

//@RunWith(GdxTestRunner.class)
public class CollegeGeneration{
    
    @Test
    public void CollegesExist(){
        PirateGame p = new PirateGame();
        HashMap<String,College> colleges = p.gameScreen.getCollages();
        assertTrue("Do Colleges Exist",colleges!=null);
    }

    @Test
    public void EachCollegeExists(){
        PirateGame p = new PirateGame();
        String[] collegeNames = {"Alcuin","Anne Lister","Constantine","Goodricke","Kraken"};
        Boolean notNull = true;
        for (String name : collegeNames) {
            notNull = notNull && (p.gameScreen.getCollege(name)!=null);
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
        Boolean correctLocation = true;
        for (int i=0;i<collegeNames.length;i++) {
            College c = p.gameScreen.getCollege(collegeNames[i]);
            float xdiff = locations[i][0]-c.getX();
            float ydiff = locations[i][1]-c.getY();
            correctLocation = correctLocation && (xdiff==0 && ydiff==0);
        }
        assertTrue("Do All named Colleges in correct location",correctLocation==true);
    }

}