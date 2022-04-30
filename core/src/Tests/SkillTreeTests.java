package Tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;

import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.SkillTree;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;


@RunWith(GdxTestRunner.class)
public class SkillTreeTests{

    @Test
    public void testSkillTreeExistance(){
        PirateGame p = new PirateGame();
        p.create();
        assertTrue("Does SkillTree Exist",p.getskillTreeScreen()!=null);

    }


    @Test
    public void clickOnUnlockedSkill(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        assertTrue("Can press unlocked skill",skillTree.getStates().get(4)==1);
    }

    @Test
    public void cantClickOnLockedSkill(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(5);
        assertTrue("Can\'t press on locked skill",skillTree.getStates().get(5)==-1);
    }
    
    @Test
    public void canClickOnNewlyUnlockedSkill(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        h.addCoins(5);
        skillTree.clicked(5);
        assertTrue("Can press on skill that is unlocked",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasPlentyOfGold(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(50);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Plenty Of Gold",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasExactAmountOfGold(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        //Hud h = (new GameScreen(p)).getHud();
        Hud h = (new GameScreen(p)).getHud();
        h.addCoins(5);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Exact Amount Of Gold",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasInvalidAmountOfGold(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(-5);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Invalid Amount Of Gold",skillTree.getStates().get(5)==-1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasNotEnoughGold(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(4);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Not Enough Gold",skillTree.getStates().get(5)==-1);
    }

    @Test
    public void healthSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        assertTrue("Health Skill Applied Correctly",h.getHealth()==150);
    }

    @Test
    public void maxHealthSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(6);
        assertTrue("Max Health Skill Applied Correctly",Hud.GetMaxHealth()==175);
    }

    @Test
    public void regenSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(7);
        assertTrue("Regen Skill Applied Correctly",h.getRegen()==2);
    }

    @Test
    public void speedSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g.getHud());
        //Hud h = p.gameScreen.getHud();
        h.addCoins(500);
        skillTree.clicked(8);
        Float n = 2.5f + (2.5f/5);
        assertTrue("Speed Skill Applied Correctly",g.getMaxSpeed()==n);
    }

    @Test
    public void coinsMultiSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(9);
        assertTrue("Coins Multiplyer Skill Applied Correctly",h.getCoinsMultiplyer()==2);
    }

    @Test
    public void damageIncreaseSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(10);
        skillTree.clicked(11);
        assertTrue("Damage Increase Skill Applied Correctly",(g).getChangeDamage()==5);
    }

    @Test
    public void reloadSpeedSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(10);
        skillTree.clicked(13);
        assertTrue("Reload Speed Skill Applied Correctly",(g).getReloadDelay()==0.9f);
    }

    @Test
    public void shotTypeSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(10);
        skillTree.clicked(15);
        assertTrue("Shot Type Skill Applied Correctly",(g).GetPlayer().getShotType()==1);
    }
    
    @Test
    public void maxOutHealthSkill(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(5);
        skillTree.clicked(5);
        skillTree.clicked(5);
        assertTrue("Health Skill Maxed Out",skillTree.getStates().get(5)==3);
    }

    @Test
    public void skillToOne(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        assertTrue("Skill to One",skillTree.getStates().get(4)==1);
    }

    @Test
    public void skillToTwo(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Two",skillTree.getStates().get(4)==2);
    }   

    @Test
    public void skillToThree(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThree(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Only Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThreeTenAbove(){
        PirateGame p = new PirateGame();
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        for(int i=0;i<10;i++){
            skillTree.clicked(4);
        }
        assertTrue("Skill to Only Three (even when called ten more times)",skillTree.getStates().get(4)==3);
    }

}