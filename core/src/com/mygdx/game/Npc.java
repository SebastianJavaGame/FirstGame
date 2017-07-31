package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Sebastian on 2017-07-26.
 */

public class Npc extends Character{
    private Image head;
    private String name;
    private int level;

    private int id;
    private static int idIteration = -1;

    private int idShop;

    static {
        idIteration++;
    }

    public Npc(Texture texture, Image head, String name, int level, int idShop) {
        super(texture);
        this.head = head;
        this.name = name;
        this.level = level;
        this.id = idIteration;
        this.idShop = idShop;
    }

    @Override
    public void collisionDo() {
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
}
