package Tests;

import org.junit.Test;

//import javafx.scene.effect.ColorInput;

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
import com.mygdx.pirategame.GameObject.Entity;
import com.mygdx.pirategame.Collectable.Coin;
import com.mygdx.pirategame.Collectable.Powerup;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class EntityGeneration{
    
    @Test
    public void EntityExists(){
        //Coin & Powerup are entities
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        Entity coin = new Coin(g,0.0f,0.0f);
        Entity powerup = new Powerup(g, 0.1f, 0.1f, 1);
        assertTrue("Entity Objects can exist",(coin!=null && powerup!=null));
    }

    @Test
    public void GeneratedAsBatchCoins(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Coin> coins = g.getCoins();
        assertTrue("Coin Objects (Inherits from abstract Entity) is generated as a batch",(coins.size()>1));
    }

    @Test
    public void GeneratedAsBatchPowerups(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Powerup> powerups = g.getPowerups();
        assertTrue("Poswerup Objects (Inherits from abstract Entity) is generated as a batch",(powerups.size()>1));
    }

    @Test
    public void AssignedUniqueDataCoins(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Coin> coins = g.getCoins();
        Boolean different =true;
        ArrayList<Coin> detected = new ArrayList<>(coins.size());
        detected.add(0, coins.get(0));
        for(int i=1;i<coins.size();i++){
            Coin iCoin = coins.get(i);
            for(int i2=0;i2<detected.size();i2++){
                Coin i2Coin =detected.get(i2);
                different = different && (iCoin.getX()!=i2Coin.getX() && iCoin.getY()!=i2Coin.getY());
            }
            detected.add(i, coins.get(i));
        }
        assertTrue("Coin Objects (Inherits from abstract Entity) is assigned unique locations",(different==true));
    }

    @Test
    public void AssignedUniqueDataPowerups(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Powerup> powerups = g.getPowerups();
        Boolean different =true;
        ArrayList<Powerup> detected = new ArrayList<>(powerups.size());
        detected.add(0, powerups.get(0));
        for(int i=1;i<powerups.size();i++){
            Powerup iCoin = powerups.get(i);
            for(int i2=0;i2<detected.size();i2++){
                Powerup i2Coin =detected.get(i2);
                different = different && (iCoin.getX()!=i2Coin.getX() && iCoin.getY()!=i2Coin.getY());
            }
            detected.add(i, powerups.get(i));
        }
        assertTrue("Powerup Objects (Inherits from abstract Entity) is assigned unique locations",(different==true));
    }

    @Test
    public void OverriddenDataCoin(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Coin> coins = g.getCoins();
        Coin firstCoin = coins.get(0);
        firstCoin.setPosition(firstCoin.getX()+100.0f, firstCoin.getY()+100.0f);
        assertTrue("Coin Objects (Inherits from abstract Entity) can have it's data overriden",(coins.get(0).getX()!=firstCoin.getX() && coins.get(0).getY()!=firstCoin.getY()));
    }

    @Test
    public void OverriddenDataPowerup(){
        PirateGame p = new PirateGame();
        GameScreen g = (new GameScreen());
        ArrayList<Powerup> powerups = g.getPowerups();
        Powerup firstPowerup = powerups.get(0);
        firstPowerup.setPosition(firstPowerup.getX()+100.0f, firstPowerup.getY()+100.0f);
        assertTrue("Powerup Objects (Inherits from abstract Entity) can have it's data overriden",(powerups.get(0).getX()!=firstPowerup.getX() && powerups.get(0).getY()!=firstPowerup.getY()));
    }
    

}