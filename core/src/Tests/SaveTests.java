package Tests;

import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;


public class SaveTests {

    @Test
    public void SavefileExistance(){
        File file = new File("saved.txt");
        assertTrue("Save file exist",file.exists());

    }



}
    

