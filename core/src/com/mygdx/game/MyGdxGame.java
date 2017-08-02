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
		new LoadAllItemToGame().loadItems();

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

            Preferences prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
            prefStats.clear();
            prefStats.putInteger("LEVEL", 2);
            prefStats.putInteger("FREE_POINT", 5);
            prefStats.putInteger("EXP", 80);
            prefStats.putInteger("MONEY", 3500);
            prefStats.putInteger("DEAD", 0);

            prefStats.putInteger("MAX_HP", 200);
            prefStats.putInteger("ARMOR", 10);
            prefStats.putInteger("STRONG", 10);
            prefStats.putInteger("WIEDZA", 10);
            prefStats.putInteger("DEFENSE_FIZ", 2);
            prefStats.putInteger("DEFENSE_MAG", 3);

            //prefStats.putInteger("COLLISION", ???);
            prefStats.flush();

            Preferences prefTask = Gdx.app.getPreferences(Quest.PREF_TASK);
            prefTask.clear();
            prefTask.putInteger("TASK0", 0);
            prefTask.putInteger("TASK0_PROGRESS", 0);
            prefTask.putInteger("TASK1", 1);
            prefTask.putInteger("TASK1_PROGRESS", 11);
            prefTask.putInteger("TASK2", 2);
            prefTask.putInteger("TASK2_PROGRESS", 1);
            prefTask.putInteger("TASK3", 3);
            prefTask.putInteger("TASK3_PROGRESS", 1);
            prefTask.flush();

            firstLoad.putString("FIRST", "IS LOAD");
            firstLoad.flush();
        }
        //TODO delete all and load in hero... with default values

        ExperienceRequired.loadExperienceList();
        BaseDialogs.loadNpcTextList();
        BaseDialogs.loadIndexOptions();
        BaseDialogs.loadIndexListener();
        BaseTask.loadAllTasks();

		Menu lvl = new Menu(this);
		//FightWin lvl = new FightWin(this, 10, 34, 67, 10000, 4510, "fire_sword");
		setScreen(lvl);
	}

	public static void dialog(String message){
        dialogInfo.showDialog(message);
    }
}
