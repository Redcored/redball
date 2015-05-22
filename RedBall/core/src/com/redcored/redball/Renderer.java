package com.redcored.redball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.redcored.redball.gameobjects.GameObject;

/**
 * Created by Ville on 21.5.2015.
 */
public class Renderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Box2DDebugRenderer debugRenderer;

    private GameWorld gameWorld;

    public Renderer(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        debugRenderer = new Box2DDebugRenderer();
        init();
    }

    private void init() {
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void render(float alpha) {
        // Clearing the screen before rendering.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderGameWorld(alpha);

        // Comment out this line to use Box2D debug rendering.
        //debugRenderer.render(gameWorld.getPhysics(), camera.combined);
    }

    private void renderGameWorld(float alpha) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (GameObject o : gameWorld.getGameObjects()) {
            o.getSprite(alpha).draw(batch);
        }
        batch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }
}
