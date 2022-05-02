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
        SkillTree sk = new SkillTree(p);
        assertTrue("Does SkillTree Exist",sk!=null);

    }


    @Test
    public void clickOnUnlockedSkill(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";
        Hud.health = 100;
        Hud.coinMulti = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        Hud.coins = 10;
        skillTree.clicked(4);
        assertTrue("Can press unlocked skill",skillTree.getStates().get(4)!=0);
    }

    @Test
    public void cantClickOnLockedSkill(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.health = 100;
        skillTree.setUpStates();
        skillTree.setUpTree();


        Hud.coins = 0;
        skillTree.getStates().set(5,-1);
        skillTree.clicked(5);
        System.out.println(skillTree.getStates());
        assertTrue("Can\'t press on locked skill",skillTree.getStates().get(5)!=1);
    }
    
    @Test
    public void canClickOnNewlyUnlockedSkill(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";
        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        Hud.coins = 10;
        skillTree.clicked(4);
        skillTree.clicked(5);
        assertTrue("Can press on skill that is unlocked",skillTree.getStates().get(5)!=0);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasPlentyOfGold(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";
        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        Hud.coins = 58;
        skillTree.clicked(4);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Plenty Of Gold",skillTree.getStates().get(5)!=0);
    }

    @Test
    public void canClickOnButtonWhenPlayerHasExactAmountOfGold(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";
        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        Hud.coins = 5;
        skillTree.clicked(4);
        skillTree.clicked(5);
        assertTrue("Can Click On Button When Player Has Exact Amount Of Gold",skillTree.getStates().get(5)==1);
    }

    @Test

    public void cannotClickOnButtonWhenPlayerHasInvalidAmountOfGold(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        //System.out.println(skillTree.getStates().get(5));
        Hud.coins = -5;
        skillTree.clicked(5);
        //System.out.println(skillTree.getStates().get(5));
        assertTrue("Cannot Click On Button When Player Has Invalid Amount Of Gold",skillTree.getStates().get(5)<=0);
    }

    @Test
    public void cannotClickOnButtonWhenPlayerHasNotEnoughGold(){
        PirateGame p = new PirateGame();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.states.set(5,0);
        Hud.coins = 1;
        skillTree.clicked(4);
        skillTree.clicked(5);
        //System.out.println(skillTree.getStates().get(5));
        assertTrue("Cannot Click On Button When Player Has Not Enough Gold",skillTree.getStates().get(5)==0);
    }

    @Test
    public void healthSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        skillTree.states.set(5,0);

        Hud.coins=500;

        int currentHealth = Hud.health;

        skillTree.clicked(5);
        skillTree.applyEffects(skillTree.last,g);
        //System.out.println(h.getHealth());
        assertTrue("Health Skill Applied Correctly",Hud.health==currentHealth+50);
    }

    @Test
    public void maxHealthSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        Hud.maxHealth = 100;
        int maxHealth = Hud.maxHealth;


        skillTree.clicked(5);
        skillTree.clicked(6);
        skillTree.applyEffects(skillTree.last,g);
        //System.out.println(maxHealth);
        //System.out.println(Hud.GetMaxHealth());
        assertTrue("Max Health Skill Applied Correctly",Hud.maxHealth == maxHealth+75);
    }

    @Test
    public void regenSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);

        skillTree.clicked(5);
        skillTree.clicked(7);
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Regen Skill Applied Correctly",Hud.healthRegen==2);
    }

    @Test
    public void speedSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);

        float originMaxSpeed = g.getMaxSpeed();
        skillTree.clicked(8);
        float n = originMaxSpeed * (1 + (1/5));//2.5F * (1 + (1/5));
        System.out.println(g.getMaxSpeed());
        System.out.println(n);
        //skillTree.applyEffects(skillTree.last,g);
        assertTrue("Speed Skill Applied Correctly",g.getMaxSpeed()==n);
    }

    @Test
    public void coinsMultiSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);

        skillTree.clicked(9);
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Coins Multiplyer Skill Applied Correctly",Hud.coinMulti==2);
    }

    @Test
    public void damageIncreaseSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);

        int currentChangeDamage = g.getChangeDamage();
        //System.out.println((g).getChangeDamage());
        skillTree.clicked(10);
        skillTree.clicked(11);
        skillTree.applyEffects(skillTree.last,g);
        //System.out.println((g).getChangeDamage());
        assertTrue("Damage Increase Skill Applied Correctly",(g).getChangeDamage()==(currentChangeDamage+5));
    }

    @Test
    public void reloadSpeedSkillAppliedCorrectly(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);

        float reloadDelay = (g).getReloadDelay();
        //System.out.println((g).getReloadDelay());

        skillTree.clicked(10);
        skillTree.clicked(13);
        skillTree.applyEffects(skillTree.last,g);
        //System.out.println((g).getReloadDelay());
        //System.out.println(Math.round((reloadDelay- (g).getReloadDelay())*10.00)/10.00);
        assertTrue("Reload Speed Skill Applied Correctly",(Math.round((reloadDelay -g.getReloadDelay()) * 10.00)/10.00)==0.1);
    }

   /*@Test
    public void shotTypeSkillAppliedCorrectly(){
        //PirateGame p = new PirateGame();
        PirateGame p = spy(new PirateGame());
        SpriteBatch sb = mock(SpriteBatch.class);
        p.batch = sb;
        //p.create();
        p.skillTreeScreen = spy(new SkillTree(p));
        SkillTree skillTree = p.getskillTreeScreen();
        skillTree.last = "0...........";
        skillTree.setUpStates();
        skillTree.clicked(4);
        GameScreen g = new GameScreen();
        g.createHud(p);
        Hud h = (g).getHud();
        //Hud h = (new GameScreen(p)).getHud();
        h.addCoins(500);
        skillTree.setupTest(p);
        skillTree.clicked(10);
        skillTree.clicked(15);
        System.out.println((g).GetPlayer(true).getShotType());
        skillTree.applyEffects(skillTree.last,g);
        assertTrue("Shot Type Skill Applied Correctly",(g).GetPlayer().getShotType()==1);
    }*/
    
    @Test
    public void maxOutHealthSkill(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();

        skillTree.clicked(4);
        skillTree.getStates().set(5,0);
        //System.out.println(skillTree.getStates().get(5));
        skillTree.clicked(5);
        //System.out.println(skillTree.getStates().get(5));
        skillTree.clicked(5);
        //System.out.println(skillTree.getStates().get(5));
        skillTree.clicked(5);
        //System.out.println(skillTree.getStates().get(5));
        skillTree.clicked(5);
        skillTree.applyEffects(skillTree.last,g);
        System.out.println(skillTree.getStates().get(5));
        assertTrue("Health Skill Maxed Out",skillTree.getStates().get(5)==3);
    }

    @Test
    public void skillToOne(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        System.out.println(skillTree.getStates().get(4));
        assertTrue("Skill to One",skillTree.getStates().get(4)>=1);
    }

    @Test
    public void skillToTwo(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        System.out.println(skillTree.getStates().get(4));
        skillTree.clicked(4);
        System.out.println(skillTree.getStates().get(4));
        assertTrue("Skill to Two",skillTree.getStates().get(4)>=2);
    }   

    @Test
    public void skillToThree(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThree(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        assertTrue("Skill to Only Three",skillTree.getStates().get(4)==3);
    }

    @Test
    public void skillNotAboveThreeTenAbove(){
        PirateGame p = new PirateGame();
        GameScreen g = new GameScreen();
        GameScreen.testing = true;
        SkillTree skillTree = new SkillTree(p);
        skillTree.last = "0...........";

        Hud.health = 100;
        Hud.coinMulti = 1;
        Hud.healthRegen = 1;
        Hud.coins = 500;
        skillTree.setUpStates();
        skillTree.setUpTree();
        skillTree.clicked(4);
        skillTree.clicked(4);
        skillTree.clicked(4);
        for(int i=0;i<10;i++){
            skillTree.clicked(4);
        }
        assertTrue("Skill to Only Three (even when called ten more times)",skillTree.getStates().get(4)==3);
    }

}