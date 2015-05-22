package com.redcored.redball.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.redcored.redball.GameWorld;

/**
 * Created by Ipismai on 22.5.2015.
 */
public class Player extends GameObject {

    final float radius = 0.6f;
    final float acceleration = 10f;

    public Player(GameWorld world) {
        super(world);
    }

    @Override
    public void update(float tickDuration) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.getPhysicsBody().applyForceToCenter(-acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.getPhysicsBody().applyForceToCenter(acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.getPhysicsBody().applyForceToCenter(0, acceleration, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.getPhysicsBody().applyForceToCenter(0, -acceleration, true);
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
        platformFixtureDef.density = 0.5f;
        platformFixtureDef.friction = 0.4f;
        platformFixtureDef.restitution = 0.6f;

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
