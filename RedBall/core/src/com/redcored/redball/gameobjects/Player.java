package com.redcored.redball.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.redcored.redball.Constants;

/**
 * Created by Ipismai on 22.5.2015.
 */
public class Player extends GameObject {
    final float acceleration = 6f;
    final Vector2 startPosition = new Vector2(5f, 5f);

    public Player() {}

    @Override
    public void update(float tickDuration) {
        // Check if player is out of game area
        if (isOutOfGameArea()) {
            resetPlayer();
        }

        // Phone input.
        //Gdx.app.log("Accelerometer", " Value: " + Gdx.input.getAccelerometerX());
        this.getPhysicsBody().applyForceToCenter(-acceleration * Gdx.input.getAccelerometerX() / 2f, 0, true);

        if (Gdx.input.isTouched()) {
            resetPlayer();
        }


        // Keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.getPhysicsBody().applyForceToCenter(-acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.getPhysicsBody().applyForceToCenter(acceleration, 0, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            resetPlayer();
        }
    }

    private boolean isOutOfGameArea() {
        float x = this.getPosition().x;
        float y = this.getPosition().y;
        return (x < -1 || x > Constants.LEVEL_WIDTH + 1 ||
                y < -1 || y > Constants.LEVEL_HEIGHT + 1);
    }

    private void resetPlayer() {
        this.setPosition(startPosition);
        this.getPhysicsBody().setLinearVelocity(0, this.getPhysicsBody().getGravityScale() * 10);
    }
}
