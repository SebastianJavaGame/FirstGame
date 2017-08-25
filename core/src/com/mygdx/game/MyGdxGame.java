package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import Screen.Menu;

public class MyGdxGame extends Game{
    private static DialogInfo dialogInfo;

    private static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private static FreeTypeFontGenerator generator;

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

    public static BitmapFont createBitmapFont(float size, Color color){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/fonts.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Math.round(size);
        generator.scaleForPixelHeight((int)Math.ceil(size));
        parameter.minFilter = Texture.TextureFilter.Nearest;
        parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;

        BitmapFont bitmapFont = generator.generateFont(parameter);
        bitmapFont.setColor(color);
        return bitmapFont;
    }

    public static BitmapFont createDistanceFont(){
        Texture texture = new Texture(Gdx.files.internal("font/font.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        BitmapFont font = new BitmapFont(Gdx.files.internal("font/font.fnt"), new TextureRegion(texture), false);
        return font;
    }

    public static void loadDefaultEq(){
        Preferences firstLoad = Gdx.app.getPreferences("START");
        firstLoad.clear(); //TODO delete if first load must be starting during first play
        firstLoad.flush();
        if(firstLoad.getString("FIRST").equals("")) {
            Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
            prefEq.clear();
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
        //TODO delete all and load in hero... with default values
    }
}
