package br.com.smaug.periodictales.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import br.com.smaug.periodictales.uteis.Assets;
import br.com.smaug.periodictales.uteis.Settings;

public class MainMenuScreen extends ScreenAdapter {
	PeriodicTales game;
	OrthographicCamera guiCam;
	Rectangle soundBounds;
	Rectangle playBounds;
	Rectangle creditosBounds;
	Rectangle sobreBounds;
	Rectangle opcoesBounds;
	Vector3 touchPoint;
	private boolean idioma;

	public MainMenuScreen (PeriodicTales game) {
		this.game = game;
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		soundBounds = new Rectangle(0, 0, 64, 64);
		playBounds = new Rectangle(160 - 150, 200 + 20 , 300, 55);
		//highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 55);
		sobreBounds = new Rectangle(160 - 150, 200 - 35, 300, 55);
		creditosBounds = new Rectangle(160 - 150, 200 - 70, 300, 55);
		opcoesBounds = new Rectangle(160 - 150, 200 - 105, 300, 55);
		touchPoint = new Vector3();
	}

	public void update () {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (playBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			if (sobreBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new SobreScreen(game));
				return;
			}
			if (creditosBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new CreditosScreen(game));
				return;
			}
			if (opcoesBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new OpcoesScreen(game));
				return;
			}
			if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
				Assets.playSound(Assets.clickSound);
				Settings.soundEnabled = !Settings.soundEnabled;
				if (Settings.soundEnabled)
					Assets.music.play();
				else
					Assets.music.pause();
			}
		}
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);

		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0, 320, 480);
		game.batcher.end();

		game.batcher.enableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.logo, 20, 300, 280, 160);
		if(idioma = true) {
			game.batcher.draw(Assets.mainMenu, 35, 85, 250, 200);
		}else {
			game.batcher.draw(Assets.mainMenuIn, 35, 85, 250, 200);
		}
				game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
				game.batcher.end();
			}
	@Override
	public void render (float delta) {
		update();
		draw();
	}

	@Override
	public void pause () {
		Settings.save();
	}
}
