package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Sebastian on 2017-05-31.
 */

public abstract class Character extends Actor{
    public Rectangle rectangle;
    protected TextureRegion texture;
    protected Hero hero;

    public Character(Texture texture){
        this.texture = new TextureRegion();
        setTexture(texture);

        rectangle = new Rectangle();

        setOrigin(texture.getWidth() /2, texture.getHeight() /2);
    }

    public abstract void collisionDo();

    public void collisionUpdate(){
        getCollision().set(getX(), getY(), this.getWidth(), this.getHeight());
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

    public Polygon convertRectangleToPolygon(){
        return new Polygon(new float[]{rectangle.getX(), rectangle.getY(), rectangle.getX(),
                rectangle.getY() + rectangle.getHeight(), rectangle.getX() + rectangle.getHeight(),
                rectangle.getY() + rectangle.getWidth(), rectangle.getX() + rectangle.getWidth(), rectangle.getY()});
    }

    public Polygon convertRectangleToPolygon(Rectangle rectangle){
        return new Polygon(new float[]{rectangle.getX(), rectangle.getY(), rectangle.getX(),
                rectangle.getY() + rectangle.getHeight(), rectangle.getX() + rectangle.getHeight(),
                rectangle.getY() + rectangle.getWidth(), rectangle.getX() + rectangle.getWidth(), rectangle.getY()});
    }

    public Rectangle getCollision() {
        return rectangle;
    }

    public TextureRegion getTexture(){
        return texture;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
