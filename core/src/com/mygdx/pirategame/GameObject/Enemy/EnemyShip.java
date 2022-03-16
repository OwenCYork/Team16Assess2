package com.mygdx.pirategame.GameObject.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import java.util.ArrayList;
import com.badlogic.gdx.utils.Array;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.PirateGame;
import com.mygdx.pirategame.GameObject.Player;
import com.mygdx.pirategame.GameObject.Enemy.EnemyFire;

/**
 * Enemy Ship
 * Generates enemy ship data
 * Instantiates an enemy ship
 *
 *@author Ethan Alabaster, Sam Pearson, Edward Poulter
 *@version 1.0
 */
public class EnemyShip extends Enemy{
    private Texture enemyShip;
    public String college;
    private Sound destroy;
    private Sound hit;
    private Array<EnemyFire> cannonBalls;
    public float t;
    /**
     * Instantiates enemy ship
     *
     * @param screen Visual data
     * @param x x coordinates of entity
     * @param y y coordinates of entity
     * @param path path of texture file
     * @param assignment College ship is assigned to
     */
    public EnemyShip(GameScreen screen, float x, float y, String path, String assignment) {
        super(screen, x, y);
        enemyShip = new Texture(path);
        //Assign college
        college = assignment;
        //Set audios
        destroy = Gdx.audio.newSound(Gdx.files.internal("ship-explosion-2.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("ship-hit.wav"));
        //Set the position and size of the college
        setBounds(0,0,64 / PirateGame.PPM, 110 / PirateGame.PPM);
        setRegion(enemyShip);
        setOrigin(32 / PirateGame.PPM,55 / PirateGame.PPM);
        cannonBalls = new Array<>();
        damage = 20;
        t = 0.0f;
    }

    /**
     * Updates the state of each object with delta time
     * Checks for ship destruction
     *
     * @param dt Delta time (elapsed time since last game tick)
     */
    public void update(float dt) {
        //If ship is set to destroy and isnt, destroy it
        if(setToDestroy && !destroyed) {
            //Play death noise
            if (screen.game.getPreferences().isEffectsEnabled()) {
                destroy.play(screen.game.getPreferences().getEffectsVolume());
            }
            world.destroyBody(b2body);
            destroyed = true;
            //Change player coins and points
            Hud.changePoints(20);
            Hud.changeCoins(5);
        }
        else if(!destroyed) {
            //Update position and angle of ship
            setPosition(b2body.getPosition().x - getWidth() / 2f, b2body.getPosition().y - getHeight() / 2f);
            float angle = (float) Math.atan2(b2body.getLinearVelocity().y, b2body.getLinearVelocity().x);
            b2body.setTransform(b2body.getWorldCenter(), angle - ((float) Math.PI) / 2.0f);
            setRotation((float) (b2body.getAngle() * 180 / Math.PI));
            //Update health bar
            bar.update();
        }
        if(health <= 0) {
            setToDestroy = true;
        }
        for(EnemyFire ball : cannonBalls) {
            ball.update(dt);
            if(ball.isDestroyed())
                cannonBalls.removeValue(ball, true);
        }
        // below code is to move the ship to a coordinate (target)
        t-=dt;
        moveTowardPlayer();
        if(t<0  && this.college != "Alcuin"){
            //System.out.print("Shoot");
            attack();
            t=1.0f;
        }
    }
    public void moveTowardPlayer(){
        Player player = screen.GetPlayer();
        float range = this.getHeight();
        if(Math.abs(player.getX()-this.getX())<3*range || Math.abs(player.getY()-this.getY())<3*range){
            Vector2 target = new Vector2(player.getX() / PirateGame.PPM, player.getY() / PirateGame.PPM);
            target.sub(b2body.getPosition());
            target.nor();
            float speed = 0.5f;
            b2body.setLinearVelocity(target.scl(speed));
        }
    }

    public void attack(){
        Player player = screen.GetPlayer();
        float range = this.getHeight();
        if(Math.abs(player.getX()-this.getX())<range || Math.abs(player.getY()-this.getY())<range){
            fire();
        }
    }

    public void fire() {
        cannonBalls.add(new EnemyFire(screen, b2body.getPosition().x, b2body.getPosition().y));
    }

    /**
     * Constructs the ship batch
     *
     * @param batch The batch of visual data of the ship
     */
    public void draw(Batch batch) {
        if(!destroyed) {
            super.draw(batch);
            //Render health bar
            bar.render(batch);

            for(EnemyFire ball : cannonBalls)
                ball.draw(batch);
        }
    }

    /**
     * Defines the ship as an enemy
     * Sets data to act as an enemy
     */
    @Override
    protected void defineEnemy() {
        //sets the body definitions
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //Sets collision boundaries
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(55 / PirateGame.PPM);
        // setting BIT identifier
        if(this.college == "Alcuin"){
            fdef.filter.categoryBits = PirateGame.FRIEND_BIT;
            // determining what this BIT can collide with
            fdef.filter.maskBits = PirateGame.DEFAULT_BIT | PirateGame.PLAYER_BIT | PirateGame.CANNON_BIT | PirateGame.ENEMY_BIT | PirateGame.FRIEND_BIT;

        }
        else{
            fdef.filter.categoryBits = PirateGame.ENEMY_BIT;
            // determining what this BIT can collide with
            fdef.filter.maskBits = PirateGame.DEFAULT_BIT | PirateGame.PLAYER_BIT | PirateGame.ENEMY_BIT | PirateGame.CANNON_BIT;
        }
        System.out.println(fdef.filter.categoryBits);
        System.out.println(fdef.filter.maskBits);

        fdef.shape = shape;
        fdef.restitution = 0.7f;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Checks contact
     * Changes health in accordance with contact and damage
     */
    @Override
    public void onContact() {
        System.out.println(this.college);
        Gdx.app.log("enemy", "collision");
        //Play collision sound
        if (screen.game.getPreferences().isEffectsEnabled()) {
            hit.play(screen.game.getPreferences().getEffectsVolume());
        }
        //Deal with the damage
        health -= damage;
        bar.changeHealth(damage);
        Hud.changePoints(5);
    }

    /**
     * Updates the ship image. Particuarly change texture on college destruction
     *
     * @param alignment Associated college
     * @param path Path of new texture
     */
    public void updateTexture(String alignment, String path){
        this.college = alignment;
        if(alignment == "Alcuin"){
            defineEnemy();            
        }
        enemyShip = new Texture(path);
        setRegion(enemyShip);
    }
}
