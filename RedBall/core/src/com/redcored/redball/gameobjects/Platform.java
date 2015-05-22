package com.redcored.redball.gameobjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.redcored.redball.GameWorld;

/**
 * Created by Ipismai on 22.5.2015.
 */
public class Platform extends GameObject {
    private final float width = 30f;
    private final float height = 1f;

    public Platform(GameWorld world) {
        super(world);
    }

    @Override
    Body createPhysicsBody() {

        // 1. Creating the body definition.
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(0, 0);

        // 2. Creating the physics body.
        Body body = getGameWorld().getPhysics().createBody(bDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2.0f, height / 2.0f);

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
        int sw = (int) (32 * width);
        int sh = (int) (32 * height);
        Pixmap pixmap = new Pixmap(sw, sh, Pixmap.Format.RGBA8888);

        pixmap.setColor(1, 1, 0, 0.5f);
        pixmap.fill();

        // Draw a border
        pixmap.setColor(0.2f, 0.6f, 0.2f, 1);
        pixmap.drawRectangle(0, 0, sw, sh);

        Texture t = new Texture(pixmap);
        Sprite s = new Sprite(t);
        s.setSize(width, height);
        s.setOriginCenter();
        //s.setOriginCenter();

        return s;
    }
}
