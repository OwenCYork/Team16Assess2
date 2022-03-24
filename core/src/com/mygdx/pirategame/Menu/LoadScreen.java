package com.mygdx.pirategame.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.pirategame.PirateGame;

import java.io.*;

/**
 * Save Screen
 * save the game data
 *
 *@author Alan Yang
 *@version 1.0
 */
public class LoadScreen implements Screen {


    private final PirateGame parent;
    private final Stage stage;
    public static String Health;
    public static String Score;
    public static String Coins;
    public static String HealthRe;
    public static String MaxHealth;
    public static String Powerup;
    public static String Last;
    private static Boolean isload=false;
    /**
     * Creates a new screen
     *
     * @param pirateGame Game data
     */
    public LoadScreen(PirateGame pirateGame){
        parent = pirateGame;
        stage = new Stage(new ScreenViewport());
    }

    /**
     * Shows new screen
     */
    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin\\uiskin.json"));
        Gdx.input.setInputProcessor(stage);

        // Create tables for the text and button
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Table table2 = new Table();
        table2.bottom().left();
        table2.setFillParent(true);

        Table table3 = new Table();
        table3.center();
        table3.setFillParent(true);

        Label loadMsg = new Label("Do you want to load the game state from the save file?", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        loadMsg.setFontScale(3f);
        table.add(loadMsg).top();
        stage.addActor(table);

        //Creat button
        TextButton backButton = new TextButton("<=Back to the menu", skin);
        TextButton yesButton = new TextButton("YES", skin);

        //Return to game without saving
        backButton.addListener(new ChangeListener() {
            /**
             * Switches screen
             * Returns to menu
             *
             * @param event Updates system event state to meny
             * @param actor updates scene
             */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(PirateGame.MENU);
                parent.killEndScreen();
            }
        });

        table2.add(backButton).fillX().uniformX();
        table2.bottom().left();
        //Return to the game and save the data(not yet done)
        stage.addActor(table2);

        yesButton.addListener(new ChangeListener() {
            /**
             * Switches screen
             * Returns to menu
             *
             * @param event Updates system event state to meny
             * @param actor updates scene
             */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    loadfile();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                parent.changeScreen(PirateGame.GAME);
                parent.killEndScreen();
            }
        });

        table3.add(yesButton).fillX().uniformX();
        table3.center();

        stage.addActor(table3);
    }
    public void loadfile() throws Exception{
//        new File("saved.txt").createNewFile();
        File file = new File("saved.txt");
        InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader bf = new BufferedReader(inputReader);
        Health = bf.readLine();
        Score = bf.readLine();
        Coins = bf.readLine();
        HealthRe = bf.readLine();
        MaxHealth = bf.readLine();
        Powerup = bf.readLine();
        Last = bf.readLine();
        bf.close();
        isload = true;
    }

    /**
     * (Not Used)
     * Updates the state of each object
     */
    public static Boolean getisload(){
        return isload;
    }
    public void update(){
    }

    /**
     * Renders visual data with delta time
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    /**
     * Changes the camera size
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
     * (Not Used)
     * Disposes game data
     */
    @Override
    public void dispose() {
    }
}
