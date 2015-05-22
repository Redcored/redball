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
public class Box extends GameObject {

    private final float width = 1.5f;
    private final float height = 1.5f;

    public Box(GameWorld world) {
        super(world);
    }

    @Override
    public void update(float updateStepLength) {
        if (Gdx.input.isButtonPressed(Input.Keys.W)) {
            this.getPhysicsBody().applyForceToCenter(0, 20, true);
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

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2.0f, height / 2.0f);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = shape;
        boxFixtureDef.density = 0.5f;
        boxFixtureDef.friction = 0.4f;
        boxFixtureDef.restitution = 0.6f;

        body.createFixture(boxFixtureDef);

        return body;
    }

    @Override
    Sprite createSprite() {
        int sw = (int) (32 * width);
        int sh = (int) (32 * height);

        Pixmap pixmap = new Pixmap(sw, sh, Pixmap.Format.RGBA8888);
        // Fill square with red color at 50% opacity
        pixmap.setColor(1, 0, 0, 0.5f);
        pixmap.fill();
        // Draw a yellow-colored X shape on square
        pixmap.setColor(1, 1, 0, 1);
        pixmap.drawLine(0, 0, sw, sh);
        pixmap.drawLine(sw, 0, 0, sh);
        // Draw a cyan-colored border around square
        pixmap.setColor(0, 0.3f, 0.3f, 1);
        pixmap.drawRectangle(0, 0, sw, sh);

        Texture t = new Texture(pixmap);
        Sprite s = new Sprite(t);
        s.setSize(width, height);
        s.setOriginCenter();

        return s;
    }
}
