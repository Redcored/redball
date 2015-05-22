package com.redcored.redball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Ville on 21.5.2015.
 */
public class Renderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private World world;

    public Renderer(World world) {
        this.world = world;
        init();
    }

    private void init() {
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        batch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(0, 0, 0);
        camera.update();
    }

    public void render(float deltaTime) {

        // Clearing the screen before rendering.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderTestObjects();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void renderTestObjects() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (Sprite sprite : world.testSprites) {
            sprite.draw(batch);
        }
        batch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = (Constants.VIEWPORT_HEIGHT / height) * width;
        camera.update();
    }
}
