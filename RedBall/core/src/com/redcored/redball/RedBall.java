package com.redcored.redball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Application.LOG_DEBUG;

public class RedBall extends ApplicationAdapter {
	World world;
	Renderer renderer;

	@Override
	public void create () {
		Gdx.app.setLogLevel(LOG_DEBUG);
		world = new World();
		renderer = new Renderer(world);
	}

	@Override
	public void render () {
		world.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);

		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.render();

	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}
}
