package com.redcored.redball.gameobjects;

import com.redcored.redball.GameWorld;

/**
 * Created by Ville on 22.5.2015.
 */
public class GameObjectFactory {
    private GameWorld gameWorld;

    public GameObjectFactory(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    public GameObject createBox(float width, float height) {
        GameObject box = new GameObject();
        box.setGameWorld(gameWorld);
        box.setPhysicsBody(BoxTemplate.createPhysicsBody(gameWorld.getPhysics(), width, height));
        box.getPhysicsBody().setGravityScale(-0.2f);
        box.setSprite(BoxTemplate.createSprite(width, height));
        return box;
    }

    public GameObject createPlayer(float radius) {
        GameObject player = new Player();
        player.setGameWorld(gameWorld);
        player.setPhysicsBody(PlayerTemplate.createPhysicsBody(gameWorld.getPhysics(), radius));
        player.setSprite(PlayerTemplate.createSprite(radius));
        player.getPhysicsBody().setGravityScale(0.6f);
        return player;
    }
}
