package com.mygdx.pirategame.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.PirateGame;

/**
 * Sets up the class for all the Islands. Deals with what happens on collision and its properties
 * @author Ethan Alabaster
 * @version 1.0
 */
public class Islands extends InteractiveTileObject {
    /**
     * Instantiates a new Islands.
     *
     * @param screen visual data
     * @param bounds Rectangle boundary (world boundary)
     */
    public Islands(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);
        //Set the category bit
        setCategoryFilter(PirateGame.DEFAULT_BIT);
    }

    /**
     * When contact occurs between the ship and island. deal damage to the ship
     */
    @Override
    public void onContact() {
        Gdx.app.log("island", "collision");
        //Deal damage to the boat
        if(GameScreen.getActivePowerup() != 5){
            Hud.changeHealth(-10);
        }
        
    }
}
