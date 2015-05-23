package com.redcored.redball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.redcored.redball.gameobjects.BoxTemplate;
import com.redcored.redball.gameobjects.GameObject;
import com.redcored.redball.gameobjects.GameObjectFactory;
import com.redcored.redball.gameobjects.Player;
import com.redcored.redball.util.UpdateTarget;

/**
 * Created by Ville on 21.5.2015.
 */
public class GameWorld implements UpdateTarget {
    private World physics;
    public Array<GameObject> objects = new Array<GameObject>();
    GameObjectFactory factory;
    private float boxSpawn = 0.0f;

    GameObject player;

    public GameWorld() {
        physics = new World(new Vector2(0.0f, -3.81f), true);
        objects = new Array<GameObject>();
        factory = new GameObjectFactory(this);
        generateDemoScene();
    }

    private void generateDemoScene() {
        GameObject o;
        // Creating the objects at random positions.
        for (int i = 0; i < 5; i++) {
            o = factory.createBox(1f,1f);

            float randomX = MathUtils.random(0f, 10.0f);
            o.setPosition(new Vector2(randomX, -2f));

            objects.add(o);
        }

        player = factory.createPlayer(0.6f);
        player.setPosition(new Vector2(5f, 5f));
        objects.add(player);

    }

    @Override
    public void update(float tickDuration) {
        // Spawning new boxes!
        boxSpawn += tickDuration;

        if (boxSpawn >= 1.5f) {
            GameObject o = factory.createBox(MathUtils.random(0.5f, 2.5f), MathUtils.random(0.5f, 2.5f));
            o.getPhysicsBody().applyForceToCenter(0, MathUtils.random(0f, 100f), true);
            float randomX = MathUtils.random(0f, Constants.LEVEL_WIDTH);
            o.setPosition(new Vector2(randomX, -2f));
            objects.add(o);
            boxSpawn -= MathUtils.random(0.5f, 1.5f);
        }

        // Updating each object before the physics step and removing escaped objects.
        for (int i = 0; i < objects.size ; i++) {
            GameObject o = objects.get(i);
            if (o.getPosition().y > Constants.LEVEL_HEIGHT + 5) {
                physics.destroyBody(o.getPhysicsBody());
                objects.removeIndex(i);
                Gdx.app.log("Object removed!", "Amount of objects: " + objects.size + " Amount of physics bodies: " +
                physics.getBodyCount());
            } else {
                o.update(tickDuration);
            }
        }

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

    public GameObject getPlayer() {
        return player;
    }

}
