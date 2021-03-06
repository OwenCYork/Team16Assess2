package com.mygdx.pirategame.GameObject.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.pirategame.GameScreen;
import com.mygdx.pirategame.Menu.Hud;
import com.mygdx.pirategame.PirateGame;

public class Whirlpool extends Enemy{
    private Texture whirlpoolTexture;
    private Sound destroy;
    private Sound hit;
    private int animationIndex;
    private String basePath;

    /**
     * Instantiates Whirlpool
     *
     * @param screen Visual data
     * @param x x coordinates of entity
     * @param y y coordinates of entity
     * @param path path of texture file
     */
    public Whirlpool(GameScreen screen, float x, float y, String path) {
        super(screen, x, y);
        whirlpoolTexture = new Texture(path);
        animationIndex=0;
        basePath=path;
        //Set audios
        destroy = Gdx.audio.newSound(Gdx.files.internal("ship-explosion-2.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("ship-hit.wav"));
        //Set the position and size of the college
        setBounds(0,0,64 / PirateGame.PPM, 110 / PirateGame.PPM);
        setRegion(whirlpoolTexture);
        setOrigin(32 / PirateGame.PPM,55 / PirateGame.PPM);

        damage = 20;
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
            Hud.setCoinText();
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
        //Doesn/'t take damage
        /*
        if(health <= 0) {
            setToDestroy = true;
        }*/

        // below code is to move the ship to a coordinate (target)
        //Vector2 target = new Vector2(960 / PirateGame.PPM, 2432 / PirateGame.PPM);
        //target.sub(b2body.getPosition());
        //target.nor();
        //float speed = 1.5f;
        //b2body.setLinearVelocity(target.scl(speed));
    }

    /**
     * Constructs the ship batch
     *
     * @param batch The batch of visual data of the ship
     */
    public void draw(Batch batch) {
        animationIndex++;
        if(animationIndex==0){
            whirlpoolTexture = new Texture(basePath);
        }else{ 
            String p = basePath.split(".png")[0];
            if(animationIndex==1){
                whirlpoolTexture = new Texture(p+"1"+".png");
            }else if(animationIndex==2){
                whirlpoolTexture = new Texture(p+"2"+".png");
            }else if(animationIndex==3){
                whirlpoolTexture = new Texture(p+"3"+".png");
            }else if(animationIndex==4){
                whirlpoolTexture = new Texture(p+"4"+".png");
                animationIndex = -1;
            }
        }


        setRegion(whirlpoolTexture);
        
        if(!destroyed) {
            super.draw(batch);
            //Render health bar
            bar.render(batch);
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
        shape.setRadius(30 / PirateGame.PPM);
        // setting BIT identifier
        fdef.filter.categoryBits = PirateGame.ENEMY_BIT;
        // determining what this BIT can collide with
        fdef.filter.maskBits = PirateGame.DEFAULT_BIT | PirateGame.PLAYER_BIT | PirateGame.ENEMY_BIT | PirateGame.CANNON_BIT;
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
        Gdx.app.log("Whirlpool", "collision");
        Hud.changePoints(50);
        //Play collision sound
        if (screen.game.getPreferences().isEffectsEnabled()) {
            hit.play(screen.game.getPreferences().getEffectsVolume());
        }
        
        GameScreen.Shoot(200F,screen.GetPlayer());
    }

    /**
     * Updates the ship image. Particuarly change texture on college destruction
     *
     * @param alignment Associated college
     * @param path Path of new texture
     */
    public void updateTexture(String alignment, String path){
        whirlpoolTexture = new Texture(path);
        setRegion(whirlpoolTexture);
    }

    public int getAnimationIndex(){
        return this.animationIndex;
    }
}