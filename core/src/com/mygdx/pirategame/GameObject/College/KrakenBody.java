package com.mygdx.pirategame.GameObject.College;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.World.InteractiveTileObject;

/**
 * Kraken
 * Checks interaction with walls from map
 */
public class KrakenBody extends InteractiveTileObject {
    private GameScreen screen;

    /**
     * Sets bounds of college walls
     *
     * @param screen Visual data
     * @param bounds Wall bounds
     */
    public KrakenBody(GameScreen screen, Rectangle bounds) {
        super(screen, bounds);
        this.screen = screen;
        fixture.setUserData(this);
        //Set the category bit
        setCategoryFilter(PirateGame.COLLEGE_BIT);
    }

    /**
     * Checks for contact with cannonball
     */
    @Override
    public void onContact() {
        Gdx.app.log("Kraken", "collision");
        //Deal damage to the assigned college
        screen.getCollege("Kraken").onContact();
    }
}
