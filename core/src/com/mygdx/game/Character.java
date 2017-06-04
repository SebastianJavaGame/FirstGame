package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class Character extends Actor{
    protected Rectangle collision;
    protected Texture texture;

    public Character(Texture texture){
        this.texture = texture;
        collision = new Rectangle();

        setOrigin(texture.getWidth() /2, texture.getHeight() /2);
    }

    public void collisionUpdate(){
        collision.set(getX(), getY(), texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture(){
        return texture;
    }
}
