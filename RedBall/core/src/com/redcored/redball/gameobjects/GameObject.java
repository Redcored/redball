package com.redcored.redball.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.redcored.redball.GameWorld;
import com.redcored.redball.util.UpdateTarget;

/**
 * Created by Ipismai on 22.5.2015.
 * <p/>
 * A basic GameObject class that should be used to implement actual the actual objects of the game.
 * Contains a sprite and a physics body which are managed by the owner of this object.
 */
public class GameObject implements UpdateTarget {

    // GameWorld this object belongs to.
    private GameWorld gameWorld;

    // Position data
    private Transform currentPosition;
    private Transform oldPosition;

    // Physics data
    private Body physicsBody;

    // Graphics data
    private Sprite sprite;

    protected GameObject() {
    }

    @Override
    public void update(float updateStepLength) {
    }

    /**
     * Used by this object's owner to update it's member variables after a physics update.
     */
    public void postPhysicsUpdate() {
        //Gdx.app.log("Positions", currentPosition.getPosition().x + " - " + physicsBody.getTransform().getPosition().x);
        oldPosition.setPosition(currentPosition.getPosition());
        oldPosition.setRotation(currentPosition.getRotation());
        currentPosition = physicsBody.getTransform();
    }

    /**
     * Sets the position of this object and it's physics, and updates the internal position history fields.
     *
     * @param position New position of this object.
     */
    public void setPosition(Vector2 position) {
        physicsBody.setTransform(position, currentPosition.getRotation());
        oldPosition.setPosition(currentPosition.getPosition());
        oldPosition.setRotation(currentPosition.getRotation());
        currentPosition = physicsBody.getTransform();
    }

    public void setGameWorld(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public Vector2 getPosition() {
        return currentPosition.getPosition();
    }


    public void setSprite(Sprite newSprite) {
        sprite = newSprite;
    }

    public void setPhysicsBody(Body newPhysicsBody) {
        physicsBody = newPhysicsBody;
        // Setting the transforms based on the physics body.
        currentPosition = new Transform(physicsBody.getPosition(), physicsBody.getAngle());
        oldPosition = new Transform(physicsBody.getPosition(), physicsBody.getAngle());
    }

    public Body getPhysicsBody() {
        return physicsBody;
    }

    public Sprite getSprite() { return sprite; }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Transform getRenderPosition(float alpha) {

        float renderX = oldPosition.getPosition().x * (1.0f - alpha) +
                currentPosition.getPosition().x * alpha;
        float renderY = oldPosition.getPosition().y * (1.0f - alpha) +
                currentPosition.getPosition().y * alpha;

        // TODO: Improve rotation interpolation
        float renderRotation = oldPosition.getRotation();
        //* (1.0f - interpolation) +
        //currentPosition.getRotation() * interpolation;

        return new Transform(new Vector2(renderX, renderY), renderRotation);
    }
}
