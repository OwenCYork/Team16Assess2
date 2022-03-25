package com.mygdx.pirategame.World;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.pirategame.GameObject.College.*;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.PirateGame;

/**
 * This is the class where all boundaries and collisions are created for the map.
 * @author Ethan Alabaster
 * @version 1.0
 */
public class WorldCreator {
    /**
     * Starts the creation of the boundaries
     *
     * @param screen the screen that the boundaries are relevant for
     */
    public WorldCreator(GameScreen screen) {
        TiledMap map = screen.getMap();

        // Object class is islands, stuff for boat to collide with
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Islands(screen, rect);
        }
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
             Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CollegeWalls(screen, rect);
        }
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CollegeWalls2(screen, rect);
        }
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new CollegeWalls3(screen, rect);
        }
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new CollegeWalls4(screen, rect);
        }
        Rectangle rect = new Rectangle(3400, 4650,300, 250);
        new KrakenBody(screen, rect);
    }
}
