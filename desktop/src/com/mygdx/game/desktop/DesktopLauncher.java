package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

import Screen.BaseScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Alone Hero";
		config.height = BaseScreen.VIEW_HEIGHT;
		config.width = BaseScreen.VIEW_WIDTH;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
