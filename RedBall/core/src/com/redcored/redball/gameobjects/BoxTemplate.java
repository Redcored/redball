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
public class BoxTemplate {
    static BodyDef bodyDef;
    static FixtureDef fixtureDef;
    static {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.2f;
    }

    static Body createPhysicsBody(World world, float width, float height) {
        // 1. Creating the physics body.
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2.0f, height / 2.0f);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        return body;
    }

    static Sprite createSprite(float width, float height) {
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
