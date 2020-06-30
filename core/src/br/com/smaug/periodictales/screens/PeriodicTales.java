package br.com.smaug.periodictales.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import br.com.smaug.periodictales.uteis.Assets;
import br.com.smaug.periodictales.uteis.Settings;

public class PeriodicTales extends Game {
	// used by all screens
	public SpriteBatch batcher;

	@Override
	public void create () {
		batcher = new SpriteBatch();
		Settings.load();
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}

