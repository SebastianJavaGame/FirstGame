package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import Screen.Menu;

public class MyGdxGame extends Game{
    public static final String PREF_EQ_SAVE = "START";
    private static DialogInfo dialogInfo;

    public MyGdxGame(DialogInfo dialogInfo){
        this.dialogInfo = dialogInfo;
    }

    public MyGdxGame(){}

	@Override
	public void create() {
		Menu lvl = new Menu(this);
		setScreen(lvl);
	}

    public static void dialog(String message){
        dialogInfo.showDialog(message);
    }

    public static BitmapFont createDistanceFont(){
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        return font;
    }

    public static BitmapFont createFontName(){
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/name.fnt"));
        return font;
    }

    public static BitmapFont createButtonFont(){
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/button.fnt"));
        return font;
    }

    public static void loadDefaultEq(){
        Preferences firstLoad = Gdx.app.getPreferences(PREF_EQ_SAVE);
        if(firstLoad.getString("FIRST").equals("")) {
            Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
            prefEq.putString("ARMOR", "armor1");
            prefEq.putString("HELMET", "helmet1");
            prefEq.putString("PANTS", "pants1");
            prefEq.putString("SHOES", "shoes1");
            prefEq.putString("WAPON", "wapons1");
            prefEq.putString("ITEM_BLOCK", "tarcza1");
            prefEq.putString("RING", "ring1");
            prefEq.putString("ITEM_HAND", "rekawice1");
            prefEq.flush();

            firstLoad.putString("FIRST", "IS LOAD");
            firstLoad.flush();
        }
    }
}
