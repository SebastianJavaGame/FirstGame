package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class Character extends Actor{
    public Rectangle collision;

    protected TextureRegion texture;

    public Character(Texture texture){
        this.texture = new TextureRegion();
        setTexture(texture);

        collision = new Rectangle();

        setOrigin(texture.getWidth() /2, texture.getHeight() /2);
    }

    public void collisionUpdate(){
        collision.set(getX(), getY(), this.getWidth(), this.getHeight());
    }

    public void draw(Batch batch, float parentAlpha)
    {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if ( isVisible() )
            batch.draw( texture, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation() );
    }

    public void setTexture(Texture t)
    {
        int w = t.getWidth();
        int h = t.getHeight();
        setWidth( w );
        setHeight( h );
        texture.setRegion( t );
    }
}
