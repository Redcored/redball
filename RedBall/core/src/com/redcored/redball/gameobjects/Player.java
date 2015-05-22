package com.redcored.redball.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.redcored.redball.GameWorld;

/**
 * Created by Ipismai on 22.5.2015.
 */
public class Player extends GameObject {

    final float radius = 0.6f;
    final float acceleration = 6f;

    public Player(GameWorld world) {
        super(world);
    }

    @Override
    public void update(float tickDuration) {

        // Phone input.
        Gdx.app.log("Accelerometer", " Value: " +Gdx.input.getAccelerometerX());
        this.getPhysicsBody().applyForceToCenter(-acceleration * Gdx.input.getAccelerometerX()/2f, 0, true);

        if (Gdx.input.isTouched()) {
            this.setPosition(new Vector2(0, -5));
            this.getPhysicsBody().setLinearVelocity(0, 7);
        }


        // Keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.getPhysicsBody().applyForceToCenter(-acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.getPhysicsBody().applyForceToCenter(acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            this.setPosition(new Vector2(0, -5));
            this.getPhysicsBody().setLinearVelocity(0, 7);
        }
    }

    @Override
    Body createPhysicsBody() {
        // 1. Creating the body definition.
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(0, 0);

        // 2. Creating the physics body.
        Body body = getGameWorld().getPhysics().createBody(bDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        // 3. Creating the fixture.
        FixtureDef platformFixtureDef = new FixtureDef();
        platformFixtureDef.shape = shape;
        platformFixtureDef.density = 0.3f;
        platformFixtureDef.friction = 0.4f;
        platformFixtureDef.restitution = 0.2f;

        body.createFixture(platformFixtureDef);

        return body;
    }

    @Override
    Sprite createSprite() {
        int size = (int)(256*radius);
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);

        pixmap.setColor(1, 0, 0, 1f);
        pixmap.fillCircle(size/2, size/2, size/2);
        pixmap.setColor(0.6f, 0, 0, 1f);
        pixmap.drawCircle(size/2, size/2, size/2);

        Texture t = new Texture(pixmap);
        Sprite spr = new Sprite(t);
        spr.setSize(radius*2, radius*2);
        spr.setOriginCenter();

        return spr;
    }
}
