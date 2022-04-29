package Tests;

import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.Menu.LoadScreen;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;


public class SaveTests {

    private String Health;
    private Boolean checker;

    @Test
    public void SavefileExistance() throws IOException {
        File file = new File("saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();

        checker = Health.isEmpty();
        assertTrue("Save file exist",checker);

    }



}
    

