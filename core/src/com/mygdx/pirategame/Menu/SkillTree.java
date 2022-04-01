package com.mygdx.pirategame.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;

import java.util.ArrayList;
import java.util.List;

/**
 * The type for the skill tree screen.
 * It is a visual representation for the skills that the game automatically unlocks for the player.
 * Automatically unlocked when a points threshold is reached
 *
 * @author Sam Pearson
 * @version 1.0
 */
public class SkillTree implements Screen {

    private final PirateGame parent;
    private final Stage stage;
    private final SkillTree T;
    //private final String last;

    //To store whether buttons are enabled or disabled
    private static final List<Integer> states = new ArrayList<Integer>();

    private static TextButton movement1;
    private TextButton damage1;
    private TextButton GoldMulti1;
    private TextButton movement2;
    private TextButton[] boxTags = new TextButton[12];
    private Node tree;
    private static String last;
    public TextButton backButton;

    Texture background = new Texture(Gdx.files.internal("WoodBackground.png"));


    /**
     * Instantiates a new Skill tree.
     *
     * @param pirateGame the main starting body of the game. Where screen swapping is carried out.
     */
//In the constructor, the parent and stage are set. Also the states list is set
    public SkillTree(PirateGame pirateGame){
        parent = pirateGame;
        stage = new Stage(new ScreenViewport());
        T = this;
        //0 = enabled, 1 = disabled
        states.add(1);
        states.add(1);
        states.add(1);
        states.add(1);

        Skin skin = new Skin(Gdx.files.internal("skin\\uiskin.json"));
        String[] boxvalues = {"Skills","Health","Max Health","Regen Speed","Movement Speed","Plunder Multiplier","Cannon","Damage Per Shot","Range","Reload Speed","Ammo","Shot Types"};
        last = "0...........";
        if (LoadScreen.getisload()) {
            last = LoadScreen.Last;
            System.out.println(last);
        }
        
        //-1 = unavalible, 0 = avalible, >1 = got to level
        int[] l = {0,-1,-1,-1,
                    -1,-1,-1,
                    -1,-1,-1,
                    -1,-1};
        if(last!="0..........."){
            for(int i=0;i<12;i++){
                System.out.println(last.charAt(i));
                if(last.charAt(i)=='.'){
                    l[i]=-1;
                }else{
                    l[i] = Integer.parseInt(""+last.charAt(i));
                }
            }
        }
        //"0..........."
        //"000000000000"
        
        
        for(int i=0;i<12;i++){
            System.out.println(l[i]);
            states.add(l[i]);
            boxTags[i] = new TextButton(boxvalues[i], skin);
            
        }
        this.applyEffects("000000000000");
        //states.set(4,0);

        backButton = new TextButton("Return", skin);
        

        //0 = nothing
        //1 = Health
        //11= Max Health
        //12= Regen Speed
        //2 = Movement Speed
        //3 = Plunder Multiplier
        //4 = Cannon
        //41= Damage Per Shot
        //42= Range
        //43= Reload Speed
        //44= Ammo
        //45=Shot Types
        this.tree = new Node(4);
        this.tree.AddBranch(5);
        this.tree.AddBranch(8);
        this.tree.AddBranch(9);
        this.tree.AddBranch(10);
        Node hBranch = this.tree.GetBranch(0);
        //System.out.println(this.tree.branch.GetLen());
        hBranch.AddBranch(6);
        hBranch.AddBranch(7);
        Node cBranch = this.tree.GetBranch(3);
        cBranch.AddBranch(11);
        cBranch.AddBranch(12);
        cBranch.AddBranch(13);
        cBranch.AddBranch(14);
        cBranch.AddBranch(15);
        
    }

    /**
     * What should be displayed on the skill tree screen
     *
     */
    @Override
    public void show() {
        //Set the input processor
        Gdx.input.setInputProcessor(stage);
        // Create a table that fills the screen
        /*Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);*/


        // Table for the return button
        /*final Table Other = new Table();
        Other.setFillParent(true);
        stage.addActor(Other);*/


        //The skin for the actors
        Skin skin = new Skin(Gdx.files.internal("skin\\uiskin.json"));
        //create skill tree buttons

        /*
        movement1 = new TextButton("Movement Speed + 20%", skin);

        //Sets enabled or disabled
        if (states.get(0) == 1){
            movement1.setDisabled(true);
        }
        GoldMulti1 = new TextButton("Gold Multiplier x2", skin);
        if (states.get(1) == 1){
            GoldMulti1.setDisabled(true);
        }
        movement2 = new TextButton("Movement Speed + 20%", skin);
        if (states.get(2) == 1){
            movement2.setDisabled(true);
        }

        damage1 = new TextButton("Damage + 5", skin);

        if (states.get(3) == 1){
            damage1.setDisabled(true);

        }
        */
        Skin bronzeSkin = new Skin(Gdx.files.internal("Bronze\\uiskin.json"));
        Skin silverSkin = new Skin(Gdx.files.internal("Silver\\uiskin.json"));
        Skin goldSkin = new Skin(Gdx.files.internal("Gold\\uiskin.json"));
        String[] boxvalues = {"Skills","Health","Max Health","Regen Speed","Movement Speed","Plunder Multiplier","Cannon","Damage Per Shot","Range","Reload Speed","Ammo","Shot Types"};
        int[][] prices = {{0,0,0,0},{5,10,15,0},
                            {10,20,30,0}, {15,30,45,0},
                            {5,10,15,0}, {20,80,200,0},
                            {20,1,1,0}, {30,100,250,0}, 
                            {10,20,30,0}, {15,45,60,0},
                            {15,40,50,0}, {120,120,120,0}};
        for(int i=0;i<12;i++){
            if(states.get(4+i)==-1){
                boxTags[i].setDisabled(true);
            }else{
                Skin[] vals = {skin,bronzeSkin,silverSkin,goldSkin};
                if(states.get(4+i)>=0){
                    boxTags[i] = new TextButton(boxvalues[i]+"\n"+(Integer.toString(prices[i][states.get(4+i)]))+" Coins", vals[states.get(4+i)]);
                }
            }
        }

        //Point unlock labels
        /*
        final Label unlock100 = new Label("100 points",skin);
        final Label unlock200 = new Label("200 points",skin);
        final Label unlock300 = new Label("300 points",skin);
        final Label unlock400 = new Label("400 points",skin);
        */
        //Return Button
        //TextButton backButton
        backButton = new TextButton("Return", skin);
        //backButton.setDisabled(false);
        this.applyEffects(last);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.last = "";
                for(int i=0;i<12;i++){
                    if(T.states.get(4+i)==-1){
                        T.last= T.last + ".";
                    }else{
                        T.last = T.last + Integer.toString(T.states.get(4+i));
                    }
                }
                parent.changeScreen(PirateGame.GAME); //Return to game
            }
        });

        boxTags[0].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(4);
            }
        });
        boxTags[1].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(5);
            }
        });
        boxTags[2].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(6);
            }
        });
        boxTags[3].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(7);
            }
        });
        boxTags[4].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(8);
            }
        });
        boxTags[5].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(9);
            }
        });
        boxTags[6].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(10);
            }
        });
        boxTags[7].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(11);
            }
        });
        boxTags[8].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(12);
            }
        });
        boxTags[9].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(13);
            }
        });
        boxTags[10].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(14);
            }
        });
        boxTags[11].addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                T.clicked(15);
            }
        });
        

        //add buttons and labels to main table
        /*table.add(movement1);
        table.add(unlock100);
        table.row().pad(10, 0, 10, 0);
        table.add(GoldMulti1);
        table.add(unlock200);
        table.row().pad(10, 0, 10, 0);
        table.add(movement2);
        table.add(unlock300);
        table.row().pad(10, 0, 10, 0);
        table.add(damage1);
        table.add(unlock400);*/
        //Label[] coinLabels = new Label[12];
        /*int[][] prices = {{0,0,0},{5,10,15},
                            {10,20,30}, {15,30,45},
                            {5,10,15}, {20,80,200},
                            {20,1,1}, {30,100,250}, 
                            {10,20,30}, {15,45,60},
                            {15,40,50}, {120,120,120}};
        */
        /*for(int i=0;i<12;i++){
            if(states.get(4+i)>=0){
                coinLabels[i] = new Label(Integer.toString(prices[i][states.get(4+i)])+" Coins",skin);
            }
        }*/

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        /*Table table2 = new Table();
        table2.setFillParent(true);
        stage.addActor(table2);*/


        table.row().pad(10,0,10,0);
        table.add(backButton);
        table.row().pad(10, 0, 10, 0);
        table.add(boxTags[0]);
        //table2.row().pad(20, 0, 200, 0);
        //table2.add(coinLabels[0]);
        

        if(states.get(5)>-1){
        table.row().pad(10, -900, 10, 0);
        table.add(boxTags[1]);
        //table2.row().pad(10, -900, 10, 0);
        //table2.add(coinLabels[1]);
        }
        if(states.get(8)>-1){
            table.row().pad(-100, -300, 10, 0);
            table.add(boxTags[4]);
            //table2.row().pad(-100, -300, 10, 0);
            //table2.add(coinLabels[4]);
        }

        if(states.get(9)>-1){
            table.row().pad(-100, 300, 10, 0);
            table.add(boxTags[5]);
            //table2.row().pad(-100, 300, 10, 0);
            //table2.add(coinLabels[5]);
        }
    
        if(states.get(10)>-1){
            table.row().pad(-100, 800, 10, 0);
            table.add(boxTags[6]);
            //table2.row().pad(-100, 800, 10, 0);
            //table2.add(coinLabels[6]);
        }

        if(states.get(6)>-1){
        table.row().pad(10, -1200, 10, 0);
        table.add(boxTags[2]);
        //table2.row().pad(10, -1200, 10, 0);
        //table2.add(coinLabels[2]);
        table.row().pad(-100, -700, 10, 0);
        table.add(boxTags[3]);
        //table2.row().pad(-100, -700, 10, 0);
        //table2.add(coinLabels[3]);
        }
        


        if(states.get(11)>-1){
        table.row().pad(10, -200, 10, 0);
        table.add(boxTags[7]);
        //table2.row().pad(10, -200, 10, 0);
        //table2.add(coinLabels[7]);
        table.row().pad(30, 0, 10, 0);
        table.add(boxTags[8]);
        //table2.row().pad(30, 0, 10, 0);
        //table2.add(coinLabels[8]);
        table.row().pad(-330, 300, 10, 0);
        table.add(boxTags[9]);
        //table2.row().pad(-330, 300, 10, 0);
        //table2.add(coinLabels[9]);
        table.row().pad(-330, 800, 10, 0);
        table.add(boxTags[10]);
        //table2.row().pad(-330, 800, 10, 0);
        //table2.add(coinLabels[10]);
        table.row().pad(100, 1200, 10, 0);
        table.add(boxTags[11]);
        //table2.row().pad(100, 1200, 10, 0);
        //table2.add(coinLabels[11]);
        }

        //table.row().pad(10,0,10,0);
        //table.add(backButton);

        for(int i=0;i<12;i++){
            //table.row().pad(10, 0, 10, 0);
            //table.add(boxTags[i]);
            System.out.println("tableIndex:"+states.get(4+i));
        }

        table.top();
        //table2.top();

        //add return button
        //Other.add(backButton);
        //Other.bottom().left();

    }
    public static String Getlast(){
        return(last);
    }
    /**
     * Allows the game to check whether a points threshold has been reached
     *
     * @param points the current amount of points
     */
    public static void pointsCheck(int points){


        //States.get() checks whether it has already been unlocked. 1 = not unlocked, 0 = unlocked
        if(states.get(0) == 1 && points >= 100){
            //Change acceleration
            GameScreen.changeAcceleration(20F);
            //Change Max speed
            GameScreen.changeMaxSpeed(20F);
            states.set(0, 0);

        }
        else if(states.get(1) == 1 && points >= 200){
            //Change multiplier

            Hud.changeCoinsMulti(2);
            states.set(1, 0);
        }
        else if(states.get(2) == 1 && points >= 300){
            //Change acceleration
            GameScreen.changeAcceleration(20F);
            //Change Max speed
            GameScreen.changeMaxSpeed(20F);
            states.set(2, 0);

        }else if(states.get(3) == 1 && points >= 400){
            //Increase damage
            GameScreen.changeDamage(5);
            states.set(3, 0);
        }
        
        

    }

    public void clicked(int code){
            System.out.println("Code:"+code);
            int[] addedNodes = new int[10];
            int index=0;
            Integer coins = Hud.GetCoins();
            if(states.get(4)==0 && code==4){
                states.set(4,1);
                //
                for(int i=0;i<4;i++){
                    addedNodes[index] = this.tree.GetBranch(i).nodeCode;
                    index++;
                }
            }else if(states.get(4)>0 && states.get(4)<3 && code==4 && Hud.DeductCoins(1)){
                states.set(4,states.get(4)+1);
            }
            //System.out.println("Value:"+states.get(4));
            //System.out.println("Index:"+index);
            if(states.get(5)==0 && code==5 && Hud.DeductCoins(5)){
                states.set(5,1);
                //
                Node root = this.tree.GetBranch(0);
                for(int i=0;i<2;i++){
                    addedNodes[index] = root.GetBranch(i).nodeCode;
                    index++;
                }
            }else if(states.get(5)==1 && code==5 && Hud.DeductCoins(10)){
                states.set(5,states.get(5)+1);
            }else if(states.get(5)==2 && code==5 && Hud.DeductCoins(15)){
                states.set(5,states.get(5)+1);
            }

            if(states.get(10)==0 && code==10 && Hud.DeductCoins(20)){
                states.set(10,1);
                Node root = this.tree.GetBranch(3);
                for(int i=0;i<5;i++){
                    addedNodes[index] = root.GetBranch(i).nodeCode;
                    index++;
                }
            }else if(states.get(10)>0 && states.get(10)<3 && code==10 && Hud.DeductCoins(1)){
                states.set(10,states.get(10)+1);
            }

            int[][] prices = {{10,20,30}, {15,30,45},
                            {5,10,15}, {20,80,200},
                            {}, {30,100,250}, 
                            {10,20,30}, {15,45,60},
                            {15,40,50}, {120,120,120}};
            for(int i=6;i<16;i++){
                if(i!=10){
                    if(states.get(i)==0 && code==i && Hud.DeductCoins(prices[i-6][0])){
                        states.set(i,1);
                    }else if(states.get(i)==1 && code==i && Hud.DeductCoins(prices[i-6][1])){
                        states.set(i,states.get(i)+1);
                    }else if(states.get(i)==2 && code==i && Hud.DeductCoins(prices[i-6][2])){
                        states.set(i,states.get(i)+1);
                    }
                }
            }


            //Open up unlocked skills
            for(int i=0;i<index;i++){
                if(states.get(addedNodes[i])==-1){
                    states.set(addedNodes[i], 0);
                }
            }
            //this.applyEffects();
            
            this.show();
    }

    public void applyEffects(String last){
        for(int i=4;i<16;i++){
            int amount = states.get(i);
            if(last.charAt(i-4)!= '.'){
                int l = Integer.parseInt(""+last.charAt(i-4));
                if(l!=-1){
                    amount = amount-l;
                if(amount==-1){
                    //Do nothing
                }else if(amount==0){
                    //Also nothing
                }else if(amount==1){
                    if(i==8){
                        GameScreen.changeAcceleration(20F);
                        GameScreen.changeMaxSpeed(20F); 
                    }else if(i==9){
                        Hud.changeCoinsMulti(2);
                    }else if(i==11){
                        GameScreen.changeDamage(5);
                    }else if(i==5){
                        Hud.AddHealth(50);
                    }else if(i==6){
                        Hud.IncreaseMaxHealth(75);
                    }else if(i==7){
                        Hud.AddHealthRegen(1);
                    }else if(i==12){
                        //Range Increase
                    }else if(i==13){
                        //Reload speed increase
                        GameScreen.changeReloadDelay(-0.1f);
                    }else if(i==14){
                        //Ammo increase
                    }else if(i==15){
                        //Enable/switch to next shot type
                        GameScreen.changeShotType(parent.gameScreen);
                    }
                    //apply effect
                    
                }else if(amount==2){
                    if(i==8){
                        GameScreen.changeAcceleration(40F);
                        GameScreen.changeMaxSpeed(40F); 
                    }else if(i==9){
                        Hud.changeCoinsMulti(4);
                    }else if(i==11){
                        GameScreen.changeDamage(10);
                    }else if(i==5){
                        Hud.AddHealth(150);
                    }else if(i==6){
                        Hud.IncreaseMaxHealth(50);
                    }else if(i==6){
                        Hud.IncreaseMaxHealth(175);
                    }else if(i==7){
                        Hud.AddHealthRegen(3);
                    }else if(i==12){
                        //Range Increase
                    }else if(i==13){
                        //Reload speed increase
                        GameScreen.changeReloadDelay(-0.3f);
                    }else if(i==14){
                        //Ammo increase
                    }else if(i==15){
                        //Enable/switch to next next shot type
                        
                        GameScreen.changeShotType(parent.gameScreen);
                        GameScreen.changeShotType(parent.gameScreen);
                    }
                    //apply effect
                    
                }else if(amount==3){
                    if(i==8){
                        GameScreen.changeAcceleration(60F);
                        GameScreen.changeMaxSpeed(60F); 
                    }else if(i==9){
                        Hud.changeCoinsMulti(6);
                    }else if(i==11){
                        GameScreen.changeDamage(15);
                    }else if(i==5){
                        Hud.AddHealth(250);
                    }else if(i==6){
                        Hud.IncreaseMaxHealth(275);
                    }else if(i==7){
                        Hud.AddHealthRegen(6);
                    }else if(i==12){
                        //Range Increase
                    }else if(i==13){
                        //Reload speed increase
                        GameScreen.changeReloadDelay(-0.5f);
                    }else if(i==14){
                        //Ammo increase
                    }else if(i==15){
                        //Enable/switch to last shot type
                        GameScreen.changeShotType(parent.gameScreen);
                        GameScreen.changeShotType(parent.gameScreen);
                        GameScreen.changeShotType(parent.gameScreen);
                    }
                    //apply effect
                    
                }
            }
        }
        }
    }

    /**
     * Renders the visual data for all objects
     * @param delta Delta Time
     */

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();
    }

    /**
     * Changes the camera size, Scales the hud to match the camera
     *
     * @param width the width of the viewable area
     * @param height the height of the viewable area
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }


    /**
     * (Not Used)
     * Pauses game
     */
    @Override
    public void pause() {
    }
    /**
     * (Not Used)
     * Resumes game
     */
    @Override
    public void resume() {
    }
    /**
     * (Not Used)
     * Hides game
     */
    @Override
    public void hide() {
    }
    /**
     * Disposes game data
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    public List<Integer> getStates(){
        return(states);
    }
}




