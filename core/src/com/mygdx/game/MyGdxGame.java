package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import Screen.Menu;

public class MyGdxGame extends Game{
    private static DialogInfo dialogInfo;

    public MyGdxGame(DialogInfo dialogInfo){
        this.dialogInfo = dialogInfo;
    }

    public MyGdxGame(){}

	@Override
	public void create() {

        Preferences firstLoad = Gdx.app.getPreferences("START");
        firstLoad.clear();
        firstLoad.flush();
        if(firstLoad.getString("FIRST").equals("")) {
            Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
            prefEq.clear();
            prefEq.putString("ARMOR", "gold_armor");
            prefEq.putString("WAPON", "silver_sword");
            prefEq.putString("SLOT0", "gold_armor");
            prefEq.putString("SLOT1", "fire_sword");
            prefEq.flush();

            firstLoad.putString("FIRST", "IS LOAD");
            firstLoad.flush();
        }
        //TODO delete all and load in hero... with default values

		Menu lvl = new Menu(this);
		setScreen(lvl);
	}

	public static void dialog(String message){
        dialogInfo.showDialog(message);
    }
}
