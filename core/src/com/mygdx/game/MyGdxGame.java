package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import Screen.Menu;

public class MyGdxGame extends Game{

	@Override
	public void create() {
		new LoadAllItemToGame().loadItems();

        Preferences firstLoad = Gdx.app.getPreferences("START");
        firstLoad.clear();
        firstLoad.flush();
        if(firstLoad.getString("FIRST").equals("")) {
            Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
            prefEq.clear();
            prefEq.putString("ARMOR", "gold_armor");
            prefEq.putString("SLOT0", "gold_armor");
            prefEq.putString("SLOT1", "silver_sword");
            prefEq.putString("SLOT2", "silver_sword");
            prefEq.putString("SLOT3", "silver_sword");
            prefEq.putString("SLOT4", "silver_sword");
            prefEq.putString("SLOT5", "silver_sword");
            prefEq.putString("SLOT6", "silver_sword");
            prefEq.putString("SLOT7", "silver_sword");
            prefEq.putString("SLOT8", "silver_sword");
            prefEq.putString("SLOT9", "silver_sword");
            prefEq.putString("SLOT10", "silver_sword");
            prefEq.putString("SLOT11", "silver_sword");
            prefEq.putString("SLOT12", "silver_sword");
            prefEq.putString("SLOT13", "silver_sword");
            prefEq.putString("SLOT14", "silver_sword");
            prefEq.putString("SLOT15", "silver_sword");
            prefEq.putString("SLOT16", "silver_sword");
            prefEq.putString("SLOT17", "silver_sword");
            prefEq.putString("WAPON", "fire_sword");
            prefEq.flush();

            Preferences prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
            prefStats.clear();
            prefStats.putInteger("LEVEL", 1);
            prefStats.putInteger("FREE_POINT", 5);
            prefStats.putInteger("EXP", 80);
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

        ExperienceRequired.loadExperienceList();

		Menu lvl = new Menu(this);
		//FightWin lvl = new FightWin(this, 10, 34, 67, 10000, 4510, "fire_sword");
		setScreen(lvl);
	}
}
