package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import Screen.Menu;

/**
 * Created by Sebastian on 2017-07-26.
 */

public class Npc extends Character{
    private Image head;
    private String name;
    private int level;

    private int id;
    private int idTask;

    private int idShop;

    public Npc(Texture texture, Image head, String name, int level, int idShop, int idTask, int id) {
        super(texture);
        this.head = head;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        this.idTask = idTask;
        this.id = id;
        setSize(getWidth() *0.65f, getHeight()*0.65f);
    }

    @Override
    public void collisionDo() {
        Hero.setStopStep();
        Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        preferences.putInteger("POS_X", (int)hero.getX()).flush();
        preferences.putInteger("POS_Y", (int)hero.getY()).flush();
        Menu.setIsFirstSpawnHeroPosition(true);
        new DialogNpc(this);
    }

    public Image getHead() {
        return head;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public int getIdShop(){
        return idShop;
    }

    public int getIdTask(){
        return idTask;
    }

}
