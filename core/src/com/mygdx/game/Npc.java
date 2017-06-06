package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Npc extends Character {

    public Npc(Texture texture) {
        super(texture);
        setPosition(400, 450);
        //TODO random position at map location - npc
    }

    public void collisionDo(){
        System.out.println("Collision!!!");

        //TODO implement work if object collision
    }
}
