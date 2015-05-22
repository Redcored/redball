package com.redcored.redball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.redcored.redball.util.StepBasedUpdater;

import static com.badlogic.gdx.Application.LOG_DEBUG;

public class RedBall extends ApplicationAdapter {
	World world;
	StepBasedUpdater worldUpdater;
	Renderer renderer;

	@Override
	public void create () {
		Gdx.app.setLogLevel(LOG_DEBUG);
		world = new World();
		renderer = new Renderer(world);

		// Creating the updater.
		worldUpdater = new StepBasedUpdater(world, 60);
	}

	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
        worldUpdater.addTime(deltaTime);
		renderer.render(deltaTime);
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}
}
