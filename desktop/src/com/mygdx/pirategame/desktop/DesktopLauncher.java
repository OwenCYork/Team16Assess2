package com.mygdx.pirategame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.pirategame.PirateGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = size.width;
		config.height = size.height;
		config.fullscreen = true;
		new LwjglApplication(new PirateGame(), config);
	}
}
