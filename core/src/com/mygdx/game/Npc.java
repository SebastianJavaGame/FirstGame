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

    public Npc(Texture texture, Image head, String name, int level, int id) {
        super(texture);
        this.head = head;
        this.name = name;
        this.level = level;
        this.id = id;

    }

    public void drawBox(){
        RenderCollisionLine_Test.drawPublic(getX(), getY(), getWidth(), getHeight());
    }
}
