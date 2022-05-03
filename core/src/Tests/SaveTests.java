package Tests;

import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.Menu.LoadScreen;
import com.mygdx.pirategame.Menu.SaveScreen;
import com.mygdx.pirategame.PirateGame;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;


public class SaveTests {

    private String Health;
    private Integer health;
    private Boolean HealthCheck;
    private String Score;
    private Integer score;
    private Boolean ScoreCheck;
    private String Coins;
    private Integer coins;

    private Boolean CoinsCheck;
    private String HealthRe;

    private Boolean HealthReCheck;
    private Integer healthRe;

    @Test
    public void SavefileHealth() throws Exception {

        File file = new File("assets\\saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();
        Score = bf.readLine();
        Coins = bf.readLine();
        HealthRe = bf.readLine();
        health = Integer.valueOf(Health);
        HealthCheck = health.equals(health);


        bf.close();
        assertTrue("Health exist in savefile.", HealthCheck);

    }
    @Test
    public void SavefileScore()throws Exception {

        File file = new File("assets\\saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();
        Score = bf.readLine();
        Coins = bf.readLine();
        HealthRe = bf.readLine();
        score = Integer.valueOf(Score);
        ScoreCheck = score.equals(score);
        bf.close();
        assertTrue("Score exist in savefile.", ScoreCheck);

    }
    @Test
    public void SavefileCoins() throws Exception {
        File file = new File("assets\\saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();
        Score = bf.readLine();
        Coins = bf.readLine();
        HealthRe = bf.readLine();
        coins = Integer.valueOf(Coins);
        CoinsCheck = true;
        bf.close();
        assertTrue("Score exist in savefile.", CoinsCheck);

    }
    @Test
    public void SavefileHealthRe() throws Exception {
        File file = new File("assets\\saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();
        Score = bf.readLine();
        Coins = bf.readLine();
        HealthRe = bf.readLine();

        healthRe = Integer.valueOf(Coins);
        HealthReCheck = true;
        bf.close();
        assertTrue("HealthRe exist in savefile.", HealthReCheck);

    }





}
    

