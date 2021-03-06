package com.mygdx.pirategame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.pirategame.Collectable.Coin;
import com.mygdx.pirategame.Collectable.Powerup;
import com.mygdx.pirategame.GameObject.AvailableSpawn;
import com.mygdx.pirategame.GameObject.College.College;
import com.mygdx.pirategame.GameObject.Enemy.EnemyShip;
import com.mygdx.pirategame.GameObject.Enemy.Whirlpool;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.Menu.LevelChoice;
import com.mygdx.pirategame.Menu.LoadScreen;
import com.mygdx.pirategame.Menu.Options;
import com.mygdx.pirategame.World.WorldContactListener;
import com.mygdx.pirategame.World.WorldCreator;

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Game Screen
 * Class to generate the various screens used to play the game.
 * Instantiates all screen types and displays current screen.
 *
 *@author Ethan Alabaster, Adam Crook, Joe Dickinson, Sam Pearson, Tom Perry, Edward Poulter
 *@version 1.0
 */
public class GameScreen implements Screen {
    private static float maxSpeed = 2.5f;
    private static float accel = 0.05f;
    private static float timeToReload = 0f;
    private static float reloadDelay = 1f;
    private float stateTime;
    private static float powerupTime = 0;
    private static Integer activePowerup = 0;
    private static Boolean cannonJammed = false;
    private Boolean loaded=false;
    private static int extraDamageDelt=0;
    public static boolean testing = false;


    public static PirateGame game;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;

    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public static Player player;
    public static HashMap<String, College> colleges = new HashMap<>();
    private static ArrayList<EnemyShip> ships = new ArrayList<>();
    private static ArrayList<Whirlpool> whirlpools = new ArrayList<>();
    private static ArrayList<Coin> Coins = new ArrayList<>();
    private static ArrayList<Powerup> Powerups = new ArrayList<>();
    private AvailableSpawn invalidSpawn = new AvailableSpawn();
    private Hud hud;

    public static final int GAME_RUNNING = 0;
    public static final int GAME_PAUSED = 1;
    private static int gameStatus;
    private static float POSX;
    private static float POSY;
    private Table pauseTable;
    private Table table;

    public Random rand = new Random();


    public GameScreen(){


    }
    /**
     * Initialises the Game Screen,
     * generates the world data and data for entities that exist upon it,
     * @param game passes game data to current class,
     */
    public void init(PirateGame game){
        gameStatus = GAME_RUNNING;
        this.game = game;
        // Initialising camera and extendable viewport for viewing game
        camera = new OrthographicCamera();
        camera.zoom = 0.0155f;
        viewport = new ScreenViewport(camera);
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Initialize a hud
        hud = new Hud(game.batch);

        // Initialising box2d physics
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();
        player = new Player(this);

        // making the Tiled tmx file render as a map
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PirateGame.PPM);
        new WorldCreator(this);

        // Setting up contact listener for collisions
        world.setContactListener(new WorldContactListener());

        // Spawning enemy ship and coin. x and y is spawn location
        colleges = new HashMap<>();
        colleges.put("Alcuin", new College(this, "Alcuin", 1900 / PirateGame.PPM, 2100 / PirateGame.PPM,
                "alcuin_flag.png", "alcuin_ship.png", 0, invalidSpawn));
        colleges.put("Anne Lister", new College(this, "Anne Lister", 6304 / PirateGame.PPM, 1199 / PirateGame.PPM,
                "anne_lister_flag.png", "anne_lister_ship.png", 8, invalidSpawn));
        colleges.put("Constantine", new College(this, "Constantine", 6240 / PirateGame.PPM, 6703 / PirateGame.PPM,
                "constantine_flag.png", "constantine_ship.png", 8, invalidSpawn));
        colleges.put("Goodricke", new College(this, "Goodricke", 1760 / PirateGame.PPM, 6767 / PirateGame.PPM,
                "goodricke_flag.png", "goodricke_ship.png", 8, invalidSpawn));

        colleges.put("Kraken", new College(this, "Kraken", 3560 / PirateGame.PPM, 4767 / PirateGame.PPM,
                "Kraken.png", "GhostShip.png", 8, invalidSpawn));
        if (LoadScreen.getisload()) {
            colleges.get("Anne Lister").destroyed = Boolean.getBoolean(LoadScreen.isAl);
            colleges.get("Constantine").destroyed = Boolean.getBoolean(LoadScreen.isCO);
            colleges.get("Goodricke").destroyed = Boolean.getBoolean(LoadScreen.isGO);
            colleges.get("Kraken").destroyed = Boolean.getBoolean(LoadScreen.isKR);
        }


        ships = new ArrayList<>();
        ships.addAll(colleges.get("Alcuin").fleet);
        ships.addAll(colleges.get("Anne Lister").fleet);
        ships.addAll(colleges.get("Constantine").fleet);
        ships.addAll(colleges.get("Goodricke").fleet);

        ships.addAll(colleges.get("Kraken").fleet);
        whirlpools = new ArrayList<>();
        //Random ships
        Boolean validLoc;
        int a = 0;
        int b = 0;
        for (int i = 0; i < 20; i++) {
            validLoc = false;
            while (!validLoc) {
                //Get random x and y coords
                a = rand.nextInt(AvailableSpawn.xCap - AvailableSpawn.xBase) + AvailableSpawn.xBase;
                b = rand.nextInt(AvailableSpawn.yCap - AvailableSpawn.yBase) + AvailableSpawn.yBase;
                //Check if valid
                validLoc = checkGenPos(a, b);
            }
            //Add a ship at the random coords
            if(i%5==0){
                whirlpools.add(new Whirlpool(this, a, b, "Whirlpool.png"));
            }else{
                ships.add(new EnemyShip(this, a, b, "unaligned_ship.png", "Unaligned"));
            }
        }




        //Random coins
        createCoinsAndPowerups();

        //Setting stage
        stage = new Stage(new ScreenViewport());
    }

    public void createHud(PirateGame game){
        hud = new Hud(game.batch);
    }


    public void createCoinsAndPowerups(){
        Coins = new ArrayList<>();
        Powerups = new ArrayList<>();
        Boolean validLoc = false;
        int a = 0;
        int b = 0;
        for (int i = 0; i < 150; i++) {
            validLoc = false;
            while (!validLoc) {
                //Get random x and y coords
                a = rand.nextInt(AvailableSpawn.xCap - AvailableSpawn.xBase) + AvailableSpawn.xBase;
                b = rand.nextInt(AvailableSpawn.yCap - AvailableSpawn.yBase) + AvailableSpawn.yBase;
                validLoc = checkGenPos(a, b);
            }
            //Add a coins or powerups at the random coords
            if (rand.nextInt(15) == 0){
                Powerups.add(new Powerup(this, a, b,rand.nextInt(5)+1));
            }
            else{
                Coins.add(new Coin(this, a, b));
            }

        }
    }

    /**
     * Makes this the current screen for the game.
     * Generates the buttons to be able to interact with what screen is being displayed.
     * Creates the escape menu and pause button
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin\\uiskin.json"));

        //GAME BUTTONS
        final TextButton pauseButton = new TextButton("Pause",skin);
        final TextButton skill = new TextButton("Skill Tree", skin);
        final TextButton save = new TextButton("Save", skin);
        //PAUSE MENU BUTTONS
        final TextButton start = new TextButton("Resume", skin);
        final TextButton options = new TextButton("Options", skin);
        TextButton exit = new TextButton("Exit", skin);


        //Create main table and pause tables
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        pauseTable = new Table();
        pauseTable.setFillParent(true);
        stage.addActor(pauseTable);


        //Set the visability of the tables. Particuarly used when coming back from options or skillTree
        if (gameStatus == GAME_PAUSED){
            table.setVisible(false);
            pauseTable.setVisible(true);
        }
        else{
            pauseTable.setVisible(false);
            table.setVisible(true);
        }

        //ADD TO TABLES
        table.add(pauseButton);
        table.row().pad(10, 0, 10, 0);
        table.left().top();

        pauseTable.add(start).fillX().uniformX();
        pauseTable.row().pad(20, 0, 10, 0);
        pauseTable.add(skill).fillX().uniformX();
        pauseTable.row().pad(20, 0, 10, 0);
        pauseTable.add(save).fillX().uniformX();
        pauseTable.row().pad(20, 0, 10, 0);
        pauseTable.add(options).fillX().uniformX();
        pauseTable.row().pad(20, 0, 10, 0);
        pauseTable.add(exit).fillX().uniformX();
        pauseTable.center();


        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                table.setVisible(false);
                pauseTable.setVisible(true);
                pause();

            }
        });
        skill.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                pauseTable.setVisible(false);
                game.changeScreen(PirateGame.SKILL);
            }
        });
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor){
                pauseTable.setVisible(false);
                game.changeScreen(PirateGame.SAVE);
            }
        });
        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pauseTable.setVisible(false);
                table.setVisible(true);
                resume();
            }
        });
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pauseTable.setVisible(false);
                game.setScreen(new Options(game,game.getScreen()));
            }
        }
        );
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    public Player GetPlayer(){
        if(player==null) {
            player = new Player(this);

        }
        return(player);
    }

    public Player GetPlayer(boolean test){
        if(test) {
            player = new Player(this,true);

        }
        return(player);
    }

    public static void Shoot(float acc, Player player){
        Random directionRand= new Random();
        int direction = directionRand.nextInt(3);
        if (direction==0) {
            player.b2body.applyLinearImpulse(new Vector2(-acc, 0), player.b2body.getWorldCenter(), true);
        }
        // Right physics impulse on 'D'
        if (direction==1) {
            player.b2body.applyLinearImpulse(new Vector2(acc, 0), player.b2body.getWorldCenter(), true);
        }
        // Up physics impulse on 'W'
        if (direction==2) {
            player.b2body.applyLinearImpulse(new Vector2(0, acc), player.b2body.getWorldCenter(), true);
        }
        // Down physics impulse on 'S'
        if (direction==3) {
            player.b2body.applyLinearImpulse(new Vector2(0, -acc), player.b2body.getWorldCenter(), true);
        }
        //player.b2body.applyLinearImpulse(new Vector2(acc, 0), player.b2body.getWorldCenter(), true);
    }

    /**
     * Checks for input and performs an action
     * Applies to keys "W" "A" "S" "D" "E" "Esc"
     *
     * Caps player velocity
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    public void handleInput(float dt) {
        if (gameStatus == GAME_RUNNING) {
            // Left physics impulse on 'A'
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                player.b2body.applyLinearImpulse(new Vector2(-accel, 0), player.b2body.getWorldCenter(), true);
            }
            // Right physics impulse on 'D'
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                player.b2body.applyLinearImpulse(new Vector2(accel, 0), player.b2body.getWorldCenter(), true);
            }
            // Up physics impulse on 'W'
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                player.b2body.applyLinearImpulse(new Vector2(0, accel), player.b2body.getWorldCenter(), true);
            }
            // Down physics impulse on 'S'
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                player.b2body.applyLinearImpulse(new Vector2(0, -accel), player.b2body.getWorldCenter(), true);
            }
            // Cannon fire on 'E'
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                System.out.println(timeToReload);
                playerFire();
                
            }
            // Checking if player at max velocity, and keeping them below max
            if (player.b2body.getLinearVelocity().x >= maxSpeed) {
                player.b2body.applyLinearImpulse(new Vector2(-accel, 0), player.b2body.getWorldCenter(), true);
            }
            if (player.b2body.getLinearVelocity().x <= -maxSpeed) {
                player.b2body.applyLinearImpulse(new Vector2(accel, 0), player.b2body.getWorldCenter(), true);
            }
            if (player.b2body.getLinearVelocity().y >= maxSpeed) {
                player.b2body.applyLinearImpulse(new Vector2(0, -accel), player.b2body.getWorldCenter(), true);
            }
            if (player.b2body.getLinearVelocity().y <= -maxSpeed) {
                player.b2body.applyLinearImpulse(new Vector2(0, accel), player.b2body.getWorldCenter(), true);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if(gameStatus == GAME_PAUSED) {
                resume();
                table.setVisible(true);
                pauseTable.setVisible(false);
            }
            else {
                table.setVisible(false);
                pauseTable.setVisible(true);
                pause();
            }
        }
    }

    /**
     * Updates the state of each object with delta time
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    public void update(float dt) {
        stateTime += dt;
        handleInput(dt);
        reduceReload(dt);
        // Stepping the physics engine by time of 1 frame
        world.step(1 / 60f, 6, 2);

        // Update all players and entities
        player.update(dt);
        colleges.get("Alcuin").update(dt);
        colleges.get("Anne Lister").update(dt);
        colleges.get("Constantine").update(dt);
        colleges.get("Goodricke").update(dt);
        colleges.get("Kraken").update(dt);
        //Update ships
        for (int i = 0; i < ships.size(); i++) {
            ships.get(i).update(dt);
        }

        for (int i = 0; i< whirlpools.size();i++){
            whirlpools.get(i).update(dt);
        }
        //Updates coin
        for (int i = 0; i < Coins.size(); i++) {
            Coins.get(i).update();
        }
        //Updates powerups
        for (int i = 0; i < Powerups.size(); i++) {
            Powerups.get(i).update();
        }
        //After a delay check if a college is destroyed. If not, if can fire
        if (stateTime > 1) {
            if (!colleges.get("Anne Lister").destroyed) {
                colleges.get("Anne Lister").fire();
            }
            if (!colleges.get("Constantine").destroyed) {
                colleges.get("Constantine").fire();
            }
            if (!colleges.get("Goodricke").destroyed) {
                colleges.get("Goodricke").fire();
            
            }

            if (!colleges.get("Kraken").destroyed) {
                colleges.get("Kraken").fire();
            
            }
        stateTime = 0;
        }
        if(activePowerup != 0 && powerupTime <= 0){
            powerupTime = 0f;
            switch (activePowerup){
                case 1:{
                    break;
                }
                case 2:{
                    changeDamage(-10);
                    break;
                }
                case 3:{
                    changeAcceleration(-30F);
                    changeMaxSpeed(-30F);
                    break;
    
                }
                case 4:{
                    changeReloadDelay(-0.5f);
                    break;
                }
                case 5:{
                    break;
                }  
            }
            changeActivePowerup(0);
        }
        if(powerupTime > 0){
            powerupTime -= dt;
        }
        
        hud.update(dt);

        // Centre camera on player boat
        camera.position.x = player.b2body.getPosition().x;
        camera.position.y = player.b2body.getPosition().y;
        if(LoadScreen.getisload()&loaded==false){
            camera.position.x = Float.parseFloat(LoadScreen.posX);
            camera.position.y = Float.parseFloat(LoadScreen.posY);
            loaded = true;
        }
        camera.update();
        renderer.setView(camera);
        POSX = player.b2body.getPosition().x;
        POSY = player.b2body.getPosition().y;
    }
    public void reduceReload(float dt){
        //Reduces the time to the next reload
        if(timeToReload > 0 && !cannonJammed){
            timeToReload -= dt;
        }
    }
    /**
     * Renders the visual data for all objects
     * Changes and renders new visual data for ships
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    @Override
    public void render(float dt) {
        if (gameStatus == GAME_RUNNING) {
            update(dt);
        }
        else{handleInput(dt);}

        Gdx.gl.glClearColor(46/255f, 204/255f, 113/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        // b2dr is the hitbox shapes, can be commented out to hide
        //b2dr.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        // Order determines layering

        //Renders coins
        for(int i=0;i<Coins.size();i++) {
            Coins.get(i).draw(game.batch);
        }
        //Renders powerups
        for(int i=0;i<Powerups.size();i++) {
            Powerups.get(i).draw(game.batch);
        }

        //Renders colleges
        player.draw(game.batch);
        colleges.get("Alcuin").draw(game.batch);
        colleges.get("Anne Lister").draw(game.batch);
        colleges.get("Constantine").draw(game.batch);
        colleges.get("Goodricke").draw(game.batch);
        colleges.get("Kraken").draw(game.batch);

        //Updates all ships
        for (int i = 0; i < ships.size(); i++){
            if (ships.get(i).college != "Unaligned") {
                //Flips a colleges allegence if their college is destroyed
                if (colleges.get(ships.get(i).college).destroyed) {

                    ships.get(i).updateTexture("Alcuin", "alcuin_ship.png");
                }
            }
            ships.get(i).draw(game.batch);
        }

        for (int i = 0; i < whirlpools.size(); i++){
            whirlpools.get(i).draw(game.batch);
        }

        game.batch.end();
        Hud.stage.draw();
        stage.act();
        stage.draw();
        //Checks game over conditions
        gameOverCheck();
    }

    /**
     * Changes the camera size, Scales the hud to match the camera
     *
     * @param width the width of the viewable area
     * @param height the height of the viewable area
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
        Hud.resize(width, height);
        camera.update();
        renderer.setView(camera);
    }

    /**
     * Returns the map
     *
     * @return map : returns the world map
     */
    public TiledMap getMap() {
        return map;
    }

    /**
     * Returns the world (map and objects)
     *
     * @return world : returns the world
     */
    public World getWorld() {
        return world;
    }
    public void playerFire(){
        if(cannonJammed){
            timeToReload -= 0.2f;
            if(timeToReload <= 0){
                cannonJammed = false;
            }
        }
        if(timeToReload <= 0){
            cannonJammed = false;
            if(!testing) {
                player.fire();
            }
            timeToReload = reloadDelay;
            if(rand.nextInt(10)==0 && LevelChoice.Level == 3){
                cannonJammed = true;
            }

        }
    }
    /**
     * Returns the college from the colleges hashmap
     *
     * @param collegeName uses a college name as an index
     * @return college : returns the college fetched from colleges
     */
    public College getCollege(String collegeName) {
        return colleges.get(collegeName);
    }

    /**
     * Checks if the game is over
     * i.e. goal reached (all colleges bar "Alcuin" are destroyed)
     */
    public void gameOverCheck(){
        //Lose game if ship on 0 health or Alcuin is destroyed
        if (Hud.getHealth() <= 0 || colleges.get("Alcuin").destroyed) {
            game.changeScreen(PirateGame.DEATH);
            game.killGame();
        }
        //Win game if all colleges destroyed
        else if (colleges.get("Anne Lister").destroyed && colleges.get("Constantine").destroyed && colleges.get("Goodricke").destroyed){
            game.changeScreen(PirateGame.VICTORY);
            game.killGame();
        }
    }

    /**
     * Fetches the player's current position
     *
     * @return position vector : returns the position of the player
     */
    public Vector2 getPlayerPos(){
        return new Vector2(player.b2body. getPosition().x,player.b2body.getPosition().y);
    }

    public static Float getPOSX(){ return POSX; }
    public static Float getPOSY(){ return POSY; }
    /**
     * Fetches the current time until the next reload
     * @return float: time until next reload (Max should be 1.0 or 1 second)
     */
    public static float getTimeToReload(){
        return timeToReload;
    }

    public void setTimeToReload(float time){
        timeToReload = time;
    }
    /**
     * Fetches the time it will take to perform a full reload
     * @return float: time needed to reload (Max should be 1.0 or 1 second)
     */
    public static float getReloadDelay(){
        return reloadDelay;
    }
    public static boolean getCannonJammed(){
        return cannonJammed;
    }

    public void setCannonJammed(Boolean jammed){
        cannonJammed = jammed;
    }
    /**
     * Decreases the time needed to perform a full reload by the value given with a minimum reload delay of 0.01 seconds
     * @param value The amount to decrease the reload delay by (use negative numbers to increase it)
     */
    public static void changeReloadDelay(float value){
        reloadDelay -= value;
        if(reloadDelay < 0.01f){
            reloadDelay = 0.01f;
        }
    }
    /**
     * Updates acceleration by a given percentage. Accessed by skill tree
     *
     * @param percentage percentage increase
     */
    public static void changeAcceleration(Float percentage){
        accel = accel * (1 + (percentage / 100));
    }

    /**
     * Updates max speed by a given percentage. Accessed by skill tree
     *
     * @param percentage percentage increase
     */
    public static void changeMaxSpeed(Float percentage){
        maxSpeed = maxSpeed * (1 +(percentage/100));
    }
    /**
     * Changes the amount of damage done by each hit. Accessed by skill tree
     *
     * @param value damage dealt
     */
    public static void changeDamage(int value){
        extraDamageDelt+=value;
        if(!testing) {
            for (int i = 0; i < ships.size(); i++) {
                ships.get(i).changeDamageReceived(value);
            }
            colleges.get("Anne Lister").changeDamageReceived(value);
            colleges.get("Constantine").changeDamageReceived(value);
            colleges.get("Goodricke").changeDamageReceived(value);
            colleges.get("Kraken").changeDamageReceived(value);
        }
    }

    public static Integer getActivePowerup(){
        return activePowerup;
    }

    public static void changeActivePowerup(int value){
        activePowerup = value;
        switch (activePowerup){
            case 1:{
                Hud.AddHealth(100);
                if(!testing){
                    Hud.setHealthText();
                }
                break;
            }
            case 2:{
                powerupTime = 10;
                changeDamage(10);
                break;
            }
            case 3:{
                powerupTime = 20;
                changeAcceleration(30F);
                changeMaxSpeed(30F);
                break;

            }
            case 4:{
                powerupTime = 10;
                changeReloadDelay(0.5f);
                break;
            }
            case 5:{
                powerupTime = 8;
                break;
            }
        } 
    }


    public static void changeShotType(){//GameScreen g){
        //Player player = g.GetPlayer();
        player.changeShot();
        //player
    }




    /**
     * Tests validity of randomly generated position
     *
     * @param x random x value
     * @param y random y value
     */
    private Boolean checkGenPos(int x, int y){
        if (invalidSpawn.tileBlocked.containsKey(x)){
            ArrayList<Integer> yTest = invalidSpawn.tileBlocked.get(x);
            if (yTest.contains(y)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Pauses game
     */
    @Override
    public void pause() {
        gameStatus = GAME_PAUSED;
    }

    /**
     * Resumes game
     */
    @Override
    public void resume() {
        gameStatus = GAME_RUNNING;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        stage.dispose();
    }

    public Hud getHud(){
        return(hud);
    }

    public Float getMaxSpeed(){
        return(maxSpeed);
    }

    public Float getAcceleration(){
        return(accel);
    }
    public int getChangeDamage(){
        return(extraDamageDelt);
    }

    public ArrayList<EnemyShip> getShips(){
        return(this.ships);
    }

    public HashMap<String,College> getCollages(){
        return(this.colleges);
    }

    public ArrayList<Coin> getCoins(){
        return(this.Coins);
    }

    public ArrayList<Powerup> getPowerups(){
        return(this.Powerups);
    }
}
