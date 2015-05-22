package com.redcored.redball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.redcored.redball.util.StepBasedUpdater;

import static com.badlogic.gdx.Application.LOG_DEBUG;

public class RedBall extends ApplicationAdapter {
	GameWorld gameWorld;
	StepBasedUpdater worldUpdater;
	Renderer renderer;

	@Override
	public void create () {
		Gdx.app.setLogLevel(LOG_DEBUG);
		gameWorld = new GameWorld();
		renderer = new Renderer(gameWorld);

		// Creating the updater.
		worldUpdater = new StepBasedUpdater(gameWorld, 100);
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
