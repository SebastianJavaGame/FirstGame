package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		LvlClass lvl = new LvlClass(this);
		setScreen(lvl);
	}
}
