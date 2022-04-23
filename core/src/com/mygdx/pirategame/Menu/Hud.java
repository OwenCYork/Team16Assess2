package com.mygdx.pirategame.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pirategame.GameScreen;

/**
 * Hud
 * Produces a hud for the player
 *
 *@author Ethan Alabaster, Sam Pearson
 *@version 1.0
 */
public class Hud implements Disposable {
    public static Stage stage;
    private Viewport viewport;

    private float timeCount;
    private static Integer score;
    private static Integer health;
    private static Integer healthRegen;
    private static Integer maxHealth;
    private static Integer activePowerup;
    private Texture hp;
    private Texture boxBackground;
    private Texture coinPic;
    private Texture repairPowerupPic;
    private Texture damagePowerupPic;
    private Texture movePowerupPic;
    private Texture attackspeedPowerupPic;
    private Texture immunityPowerupPic;
    private Texture emptyPowerup;

    private static Label scoreLabel;
    private static Label healthLabel;
    private static Label coinLabel;
    private static Label pointsText;
    private static Label powerText;
    private static Label cannonText;
    private static Integer coins;
    private static Integer coinMulti;
    private Image hpImg;
    private Image box;
    private Image coin;
    private Image powerupImg;
    final Slider reloadSlider;

    Skin skin = new Skin(Gdx.files.internal("skin\\uiskin.json"));

    /**
     * Retrieves information and displays it in the hud
     * Adjusts hud with viewport
     *
     * @param sb Batch of images used in the hud
     */
    public Hud(SpriteBatch sb) {
        health = 100;
        score = 0;
        coins = 0;
        coinMulti = 1;
        healthRegen = 1;
        maxHealth = 100;
        activePowerup = 0;
        //Set images
        hp = new Texture("hp.png");
        boxBackground = new Texture("hudBG.png");
        coinPic = new Texture("coin.png");

        repairPowerupPic = new Texture("repairPower.png");
        damagePowerupPic = new Texture("damagePower.png");
        movePowerupPic = new Texture("movePower.png");
        attackspeedPowerupPic = new Texture("attackspeedPower.png");
        immunityPowerupPic = new Texture("immunityPower.png");
        emptyPowerup = new Texture("emptyPowerup.png");

        hpImg = new Image(hp);
        box = new Image(boxBackground);
        coin = new Image(coinPic);
        powerupImg = new Image(emptyPowerup);


        viewport = new ScreenViewport();
        stage = new Stage(viewport, sb);



        //Creates tables
        Table table1 = new Table(); //Counters
        Table table2 = new Table(); //Pictures or points label
        Table table3 = new Table(); //Background
        Table reloadtable = new Table(); //Used to display the reload bar

        table1.top().right();
        table1.setFillParent(true);
        table2.top().right();
        table2.setFillParent(true);
        table3.top().right();
        table3.setFillParent(true);
        reloadtable.bottom();
        reloadtable.setFillParent(true);

        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.RED));
        coinLabel = new Label(String.format("%03d", coins), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        pointsText = new Label("Points:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        powerText = new Label("Power:", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        cannonText = new Label("Cannon Ready", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        cannonText.setFontScale(2);


        table3.add(box).width(140).height(180).padBottom(15).padLeft(30);
        table2.add(hpImg).width(32).height(32).padTop(16).padRight(90);
        table2.row();
        table2.add(coin).width(32).height(32).padTop(8).padRight(90);
        table2.row();
        table2.add(pointsText).width(32).height(32).padTop(6).padRight(90);
        table2.row();
        table2.add(powerText).width(32).height(32).padTop(6).padRight(90);


        table1.add(healthLabel).padTop(20).top().right().padRight(40);
        table1.row();
        table1.add(coinLabel).padTop(20).top().right().padRight(40);
        table1.row();
        table1.add(scoreLabel).padTop(22).top().right().padRight(40);
        table1.row();
        table1.add(powerupImg).padTop(14).width(32).height(32).padRight(30);


        
        //Reload Slider
        reloadSlider = new Slider( 0f, 1f, 0.01f,false, skin );
        //reloadtable.row();
        reloadtable.add(reloadSlider).width(480).height(64).padTop(32).padRight(10);
        reloadtable.row();
        reloadtable.add(cannonText).top().padBottom(20).padRight(10);

        stage.addActor(table3);
        stage.addActor(table2);
        stage.addActor(table1);
        stage.addActor(reloadtable);
        if (LevelChoice.Level == 1) {
            IncreaseMaxHealth(100);
            AddHealth(97);
            changeCoins(100);
            AddHealthRegen(3);

        }
        if (LevelChoice.Level == 3) {
            IncreaseMaxHealth(-50);
            AddHealth(-50);
        }
        if (LoadScreen.getisload()) {
            health = Integer.valueOf(LoadScreen.Health);
            score = Integer.valueOf(LoadScreen.Score);
            changeCoins(Integer.valueOf(LoadScreen.Coins));
            healthRegen = Integer.valueOf(LoadScreen.HealthRe);
            activePowerup = Integer.valueOf(LoadScreen.Powerup);
        }

        boolean testing = false;//true;
        if(testing){
            IncreaseMaxHealth(1000);
            AddHealth(1000);
            AddHealthRegen(100);
            changeCoins(10000);
        }
    }

    /**
     * Updates the state of the hud
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    public void update(float dt) {
        timeCount += dt;
        if(GameScreen.getCannonJammed()){
            reloadSlider.setValue(GameScreen.getTimeToReload()/GameScreen.getReloadDelay()); //Set value to current option
            cannonText.setText("Cannon is jammed! Press E to reload!");
        }
        else if(GameScreen.getTimeToReload() > 0){
            reloadSlider.setValue(GameScreen.getTimeToReload()/GameScreen.getReloadDelay()); //Set value to current option
            cannonText.setText("Reloading...");
        }
        else{
            reloadSlider.setValue(0);
            cannonText.setText("Cannon Ready");
        }
        //Changes the image of the powerup if the player has one active
        switch (GameScreen.getActivePowerup()){
            case 0:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(emptyPowerup)));
                break;
            }
            case 1:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(repairPowerupPic)));
                break;
            }
            case 2:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(damagePowerupPic)));
                break;
            }
            case 3:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(movePowerupPic)));
                break;
            }
            case 4:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(attackspeedPowerupPic)));
                break;
            }
            case 5:{
                powerupImg.setDrawable(new SpriteDrawable(new Sprite(immunityPowerupPic)));
                break;
            }
        }        
        if(timeCount >= 1) {
            //Regen health every second
            if(health < maxHealth) {
                health += healthRegen;
            }
            healthLabel.setText(String.format("%02d", health));
            //Gain point every second
            score += 1;
            scoreLabel.setText(String.format("%03d", score));
            timeCount = 0;

            //Check if a points boundary is met
            SkillTree.pointsCheck(score);
            

        }
    }

    /**
     * Changes health by value increase
     *
     * @param value Increase to health
     */
    public static void AddHealth(Integer h){
        health+=h;
        healthLabel.setText(String.format("%02d", health));
    }

    public static void AddHealthRegen(Integer regen){
        healthRegen+=regen;
    }

    public static void IncreaseMaxHealth(Integer maxh){
        maxHealth+=maxh;
    }

    public static int GetCoins(){
        return(coins);
    }
    public static Integer GetScore(){
        return(score);
    }
    public static Integer GetHealthRegen(){
        return(healthRegen);
    }
    public static Integer GetMaxHealth(){
        return(maxHealth);
    }
    public static Integer GetPowerup(){
        return(activePowerup);
    }

    public static Boolean DeductCoins(Integer c){
        coins-=c;
        if(coins<0){
            coins+=c;
            coinLabel.setText(String.format("%03d", coins));
            return(false);
        }else{
            coinLabel.setText(String.format("%03d", coins));
            return(true);
        }
    }

/*
    public static void changeHealth(int value) {
        health += value;
        healthLabel.setText(String.format("%02d", health));
    }
*/
    /**
     * Changes coins by value increase
     *
     * @param value Increase to coins
     */
    public static void changeCoins(int value) {
        if (value > 0) {
            coins += value * coinMulti;
            coinLabel.setText(String.format("%03d", coins));
        }
    }

    /**
     * Changes points by value increase
     *
     * @param value Increase to points
     */
    public static void changePoints(int value) {
        score += value;
        scoreLabel.setText(String.format("%03d", score));
        //Check if a points boundary is met
        SkillTree.pointsCheck(score);
    }

    /**
     * Changes health by value factor
     *
     * @param value Factor of coin increase
     */
    public static void changeCoinsMulti(int value) {
        coinMulti = coinMulti * value;
    }



    /**
     * Changes the camera size, Scales the hud to match the camera
     *
     * @param width the width of the viewable area
     * @param height the height of the viewable area
     */
    public static void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    /**
     * Returns health value
     *
     * @return health : returns health value
     */
    public static Integer getHealth(){
        return health;
    }

    /**
     * (Not Used)
     * Returns coins value
     *
     * @return health : returns coins value
     */
    public static Integer getCoins(){
        return coins;
    }

    /**
     * Disposes game data
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    public void addCoins(int value){
        coins+=value;
    }

    public int getRegen(){
        return(healthRegen);
    }

    public int getHUDCoins(){return this.coins;}
    public int getPoints(){return this.score;}
    public int getPlayerHealth(){return this.health;}
    public int getCoinsMultiplyer(){
        return(coinMulti);
    }
}

