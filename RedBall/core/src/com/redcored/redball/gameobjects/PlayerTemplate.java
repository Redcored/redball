package com.redcored.redball.gameobjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Ipismai on 22.5.2015.
 */
public class PlayerTemplate {
    static BodyDef bodyDef;
    static FixtureDef fixtureDef;

    static {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 0);

        fixtureDef = new FixtureDef();
        fixtureDef.density = 0.3f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.2f;
    }

    static Body createPhysicsBody(World world, float radius) {
        // 1. Creating the physics body.
        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);

        return body;
    }

    static Sprite createSprite(float radius) {
        int size = (int) (256 * radius);
        Pixmap pixmap = new Pixmap(size, size, Pixmap.Format.RGBA8888);

        pixmap.setColor(1, 0, 0, 1f);
        pixmap.fillCircle(size / 2, size / 2, size / 2);
        pixmap.setColor(0.6f, 0, 0, 1f);
        pixmap.drawCircle(size / 2, size / 2, size / 2);

        Texture t = new Texture(pixmap);
        Sprite spr = new Sprite(t);
        spr.setSize(radius * 2, radius * 2);
        spr.setOriginCenter();

        return spr;
    }
}
