package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import Screen.Map_01;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Hero extends Character {
    private Vector2 velocity;
    private float speed;

    public Hero(Texture texture, float speed) {
        super(texture);
        velocity = new Vector2();
        this.speed = speed;
    }

    public void move(float delta){
        velocity.set(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            velocity.y += (speed * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            velocity.x -= (speed * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            velocity.x += (speed * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            velocity.y -= (speed * delta);

        moveBy(velocity.x, velocity.y);
        velocity.scl(delta);

        setX( MathUtils.clamp( getX(), 0,  Map_01.MAP_WIDTH - texture.getWidth() ));
        setY( MathUtils.clamp( getY(), 0,  Map_01.MAP_HEIGHT - texture.getHeight() ));
    }
}
