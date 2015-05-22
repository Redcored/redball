package com.redcored.redball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.redcored.redball.gameobjects.Box;
import com.redcored.redball.gameobjects.GameObject;
import com.redcored.redball.gameobjects.Platform;
import com.redcored.redball.gameobjects.Player;
import com.redcored.redball.util.UpdateTarget;

/**
 * Created by Ville on 21.5.2015.
 */
public class GameWorld implements UpdateTarget {
    public Sprite[] testSprites;
    public int selectedSprite;


    private World physics;
    public Array<GameObject> objects = new Array<GameObject>();

    public GameWorld() {
        physics = new World(new Vector2(0.0f, -9.81f), true);
        objects = new Array<GameObject>();

        generateDemoScene();
    }

    private void generateDemoScene() {
        GameObject o;
        // Creating the objects at random positions.
        for (int i = 0; i < 5; i++) {
            o = new Box(this);

            float randomX = MathUtils.random(-2.0f, 2.0f);
            float randomY = MathUtils.random(-2.0f, 2.0f);
            o.setPosition(new Vector2(randomX, randomY));

            objects.add(o);
        }

        o = new Player(this);
        o.setPosition(new Vector2(0f, 3f));
        objects.add(o);

        o = new Platform(this);
        o.setPosition(new Vector2(-0f, -2f));
        objects.add(o);
    }

    @Override
    public void update(float tickDuration) {
        // Updating each object before the physics step.
        for (GameObject o : objects) { o.update(tickDuration); }

        // Performing the physics step.
        physics.step(tickDuration, 6, 2);

        // Performing the location updates etc after physics tick.
        for (GameObject o : objects) { o.postPhysicsUpdate(); }
    }

    public World getPhysics() {
        return physics;
    }

    public Array<GameObject> getGameObjects() {
        return objects;
    }

}
