package br.com.smaug.periodictales.desktop;

import br.com.smaug.periodictales.screens.PeriodicTales;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Periodic Tales";
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new PeriodicTales(), config);
	}
}
