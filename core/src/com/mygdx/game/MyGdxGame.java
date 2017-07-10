package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import Screen.Menu;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		new LoadAllItemToGame().loadItems();

        Preferences firstLoad = Gdx.app.getPreferences("START");
        firstLoad.clear();
        if(firstLoad.getString("FIRST").equals("")) {
            Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
            prefEq.clear();
            prefEq.putString("ARMOR", "gold_armor");
            prefEq.putString("SLOT0", "gold_armor");
            prefEq.putString("SLOT1", "silver_sword");
            prefEq.putString("SLOT2", "fire_sword");
            prefEq.flush();

            Preferences prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
            prefStats.clear();
            prefStats.putInteger("FREE_POINT", 5);
            prefStats.putInteger("EXP", 50);
            prefStats.putInteger("MAX_EXP", 100);
            prefStats.putInteger("MONEY", 10000);

            prefStats.putInteger("MAX_HP", 200);
            prefStats.putInteger("ARMOR", 10);
            prefStats.putInteger("STRONG", 10);
            prefStats.putInteger("WIEDZA", 10);
            prefStats.putInteger("DEFENSE_FIZ", 2);
            prefStats.putInteger("DEFENSE_MAG", 3);
            prefStats.flush();

            firstLoad.putString("FIRST", "IS LOAD");
            firstLoad.flush();
        }
        //TODO delete all and load in hero... with default values

		Menu lvl = new Menu(this);
		setScreen(lvl);
	}
}
