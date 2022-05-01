package Tests;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;

import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.SkillTree;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;


@RunWith(GdxTestRunner.class)
public class SkillTreeTests{

    @InjectMocks
    public PirateGame p;



    @Test
    public void testSkillTreeExistance(){
        MockitoAnnotations.initMocks(this);
        //PirateGame p = mock(PirateGame.class);
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree s = p.getskillTreeScreen();
        s.last = "0...........";
        s.SetStates();
        System.out.println(p);
        System.out.println(s);
        assertTrue("Does SkillTree Exist",s!=null);

    }


    @Test
    public void clickOnUnlockedSkill(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        assertTrue("Can press unlocked skill",skillTree.getStates().get(4)==1);
    }

    @Test
    public void cantClickOnLockedSkill(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(5);
        assertTrue("Can\'t press on locked skill",skillTree.getStates().get(5)==-1);
    }
    
    @Test
    public void canClickOnNewlyUnlockedSkill(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        h.addCoins(5);
        skillTree.clicked(5);
        assertTrue("Can press on skill that is unlocked",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasPlentyOfGold(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(50);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Plenty Of Gold",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasExactAmountOfGold(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        //Hud h = (new GameScreen(p)).getHud();
        Hud h = (new GameScreen(p)).getHud();
        h.addCoins(5);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Exact Amount Of Gold",skillTree.getStates().get(5)==1);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasInvalidAmountOfGold(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(-5);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Invalid Amount Of Gold",skillTree.getStates().get(5)==0);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasNotEnoughGold(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        Hud h = (new GameScreen(p)).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(4);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Not Enough Gold",skillTree.getStates().get(5)==0);
    }

    @Test
    public void healthSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        System.out.println(h.getHealth());
        skillTree.clicked(5);
        skillTree.applyEffects(skillTree.last,g);
        System.out.println(h.getHealth());
        assertTrue("Health Skill Applied Correctly",h.getHealth()==100);
    }

    @Test
    public void maxHealthSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(6);
        skillTree.applyEffects(skillTree.last,g);
        System.out.println(Hud.GetMaxHealth());
        assertTrue("Max Health Skill Applied Correctly",Hud.GetMaxHealth()==125);
    }

    @Test
    public void regenSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(7);
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Regen Skill Applied Correctly",h.getRegen()==2);
    }

    @Test
    public void speedSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g.getHud());
        //Hud h = p.gameScreen.getHud();
        h.addCoins(500);
        skillTree.clicked(8);
        float n = 2.5F * (1 + (1/5));
        System.out.println(g.getMaxSpeed());
        System.out.println(n);
        //skillTree.applyEffects(skillTree.last,g);
        assertTrue("Speed Skill Applied Correctly",g.getMaxSpeed()==n);
    }

    @Test
    public void coinsMultiSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(9);
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Coins Multiplyer Skill Applied Correctly",h.getCoinsMultiplyer()==2);
    }

    @Test
    public void damageIncreaseSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(10);
        skillTree.clicked(11);
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Damage Increase Skill Applied Correctly",(g).getChangeDamage()==5);
    }

    @Test
    public void reloadSpeedSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        System.out.println((g).getReloadDelay());
        skillTree.clicked(10);
        skillTree.clicked(13);
        skillTree.applyEffects(skillTree.last,g);
        System.out.println((g).getReloadDelay());
        assertTrue("Reload Speed Skill Applied Correctly",(g).getReloadDelay()==0.9f);
    }

    @Test
    public void shotTypeSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(10);
        skillTree.clicked(15);
        System.out.println((g).GetPlayer().getShotType());
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Shot Type Skill Applied Correctly",(g).GetPlayer().getShotType()==1);
    }
    
    @Test
    public void maxOutHealthSkill(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        skillTree.clicked(4);
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.clicked(5);
        skillTree.clicked(5);
        skillTree.clicked(5);
        skillTree.clicked(5);
        //skillTree.applyEffects(skillTree.last,g);
        assertTrue("Health Skill Maxed Out",skillTree.getStates().get(5)==3);
    }

    @Test
    public void skillToOne(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        skillTree.clicked(4);
        assertTrue("Skill to One",skillTree.getStates().get(4)==1);
    }

    @Test
    public void skillToTwo(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        h.addCoins(500);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Two",skillTree.getStates().get(4)==2);
    }   

    @Test
    public void skillToThree(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        h.addCoins(500);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThree(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        h.addCoins(500);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Only Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThreeTenAbove(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.SetStates();
        GameScreen g = spy(new GameScreen(p));
        Hud h = (g).getHud();
        h.addCoins(500);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        for(int i=0;i<10;i++){
            skillTree.clicked(4);
        }
        assertTrue("Skill to Only Three (even when called ten more times)",skillTree.getStates().get(4)==3);
    }

}