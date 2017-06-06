package com.mygdx.game;

import com.badlogic.gdx.Game;

import Screen.Menu;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		Menu lvl = new Menu(this);
		setScreen(lvl);
	}
}
