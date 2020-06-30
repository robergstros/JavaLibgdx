package br.com.smaug.periodictales.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import br.com.smaug.periodictales.Animation;
import br.com.smaug.periodictales.uteis.Assets;

public class WinScreen extends ScreenAdapter {
	PeriodicTales game;
	OrthographicCamera cam;
	TextureRegion princess;
	String[] messages = {
			              "Ainda bem que consegui sair desse pesadelo\n",
			              "Não aguentava mais pular essas plataformas",
			};
	int currentMessage = 0;
	
	public WinScreen(PeriodicTales game) {
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 320, 480);
		princess = new TextureRegion(Assets.arrow.getTexture(), 210, 122, -40, 38);
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched()) {
			currentMessage++;
			if(currentMessage == messages.length) {
				currentMessage--;
				game.setScreen(new MainMenuScreen(game));
			}
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		game.batcher.setProjectionMatrix(cam.combined);
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegion, 0, 0);
		game.batcher.draw(Assets.castle, 60, 120, 200, 200);
		game.batcher.draw(Assets.bobFall.getKeyFrame(0, Animation.ANIMATION_LOOPING), 120, 200);
		Assets.font.draw(game.batcher, messages[currentMessage], 0, 400, 320, Align.center, false);
		game.batcher.draw(princess,150, 200);
		game.batcher.end();
	}
}
