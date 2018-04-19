package com.medievalknight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.medievalknight.game.screens.ScreenKnight;

public class KnightGame extends Game {
	protected static KnightGame instance = null;

	public Stage gameStage;
	public SpriteBatch batch;
	public Renderer renderer;

	public MenuManager menu;
	public GameManager gM;

	public static KnightGame getInstance() {
		if (instance == null) {
			instance = new KnightGame();
		}
		return instance;
	}

	@Override
	public void create() {
		gameStage = new Stage();
		batch = new SpriteBatch();
		renderer = new Renderer();

		menu = new MenuManager();
		menu.createTables();

		Gdx.input.setInputProcessor(gameStage);

		gM = new GameManager();
		startGame();
	}

	public void startGame() {
		((Game) Gdx.app.getApplicationListener()).setScreen(new ScreenKnight());
	}

	@Override
	public void render() {
		super.render();
		Gdx.gl20.glClearColor(255f / 255f, 221f / 255f, 153f / 255f, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.update();

		batch.begin();
		gameStage.act();
		gameStage.draw();

		batch.end();
	}

	@Override
	public void dispose() {
	}
}


