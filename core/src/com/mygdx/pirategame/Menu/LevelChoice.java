package com.mygdx.pirategame.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

/**
 * Level of difficulty Screen
 * Change the difficulty of the game
 *
 *@author Alan Yang
 *@version 1.0
 */
public class LevelChoice implements Screen {
    public static int Level;
    private final PirateGame parent;
    private final Stage stage;
    Texture background = new Texture(Gdx.files.internal("background.png"));

    /**
     * Creates a new screen
     *
     * @param pirateGame Game data
     */
    public LevelChoice(PirateGame pirateGame){
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
        table.center();
        table.pad(0,0,200,0);
        table.setFillParent(true);

        Table table2 = new Table();
        table2.setFillParent(true);


        Label difficultyMsg = new Label("Choose the level of difficulty", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        difficultyMsg.setFontScale(3f);
        table.add(difficultyMsg);
        stage.addActor(table);

        //Creat button
        TextButton easyButton = new TextButton("EASY", skin);
        TextButton normalButton = new TextButton("NORMAL", skin);
        TextButton hardButton = new TextButton("HARD", skin);

        //Return to the mainMenu
        easyButton.addListener(new ChangeListener() {
            /**
             * Switches screen
             * Returns to menu
             *
             * @param event Updates system event state to meny
             * @param actor updates scene
             */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Level = 1;
                parent.changeScreen(PirateGame.MENU);
                parent.killEndScreen();
            }
        });


        //Return to the game and save the data(not yet done)

        normalButton.addListener(new ChangeListener() {
            /**
             * Switches screen
             * Returns to menu
             *
             * @param event Updates system event state to meny
             * @param actor updates scene
             */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Level = 2;
                parent.changeScreen(PirateGame.MENU);
                parent.killEndScreen();
            }
        });
        hardButton.addListener(new ChangeListener() {
            /**
             * Switches screen
             * Returns to menu
             *
             * @param event Updates system event state to meny
             * @param actor updates scene
             */
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Level = 3;
                parent.changeScreen(PirateGame.MENU);
                parent.killEndScreen();
            }
        });
        table2.add(easyButton).fillX().uniformX();
        table.row();
        table2.add(normalButton).fillX().uniformX();
        table.row();
        table2.add(hardButton).fillX().uniformX();
        table2.center();

        stage.addActor(table2);
    }

    /**
     * (Not Used)
     * Updates the state of each object
     */
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
        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();
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
