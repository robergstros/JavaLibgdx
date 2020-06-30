package br.com.smaug.periodictales.screens;

import br.com.smaug.periodictales.uteis.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class OpcoesScreen extends ScreenAdapter {

    PeriodicTales game;

    OrthographicCamera guiCam;
    Texture opcoesImage;
    TextureRegion opcoesRegion;
    Rectangle portuBounds;
    Rectangle inglesBounds;
    Vector3 touchPoint;

    public OpcoesScreen (PeriodicTales game) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 320, 480);
        portuBounds = new Rectangle(40, 275, 90, 30);
        inglesBounds = new Rectangle(200, 275, 90, 30);
        touchPoint = new Vector3();
        opcoesImage = Assets.loadTexture("data/opcoes.png");
        opcoesRegion = new TextureRegion(opcoesImage, 0, 0, 512, 512);
    }
    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (portuBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
            }
            if (inglesBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }
    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(opcoesRegion, 0, 0);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.end();
    }

    @Override
    public void render (float delta) {
        draw();
        update();
    }

    @Override
    public void hide () {
        opcoesImage.dispose();
    }
}