package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class LvlClass extends BaseScreen {

    public final static int MAP_WIDTH = 1000;
    public final static int MAP_HEIGHT = 1000;

    private Hero hero;
    private ArrayList<Npc> npcList;

    public LvlClass(MyGdxGame game) {
        super(game);
        npcList = new ArrayList<Npc>();
    }

    @Override
    public void create() {
        bgTexture = new Texture(Gdx.files.internal("background.png"));
        hero = new Hero(new Texture(Gdx.files.internal("badlogic.jpg")));
        npcList.add(new Npc(new Texture(Gdx.files.internal("badlogic.jpg"))));
    }

    @Override
    public void update(float dt) {
        hero.move(dt);

        cameraUpdate();
        collisionDetected();
        drawObject();
    }

    private void cameraUpdate(){
        camera.position.set( hero.getX() + hero.getOriginX(), hero.getY() + hero.getOriginY(), 0 );
        camera.position.x = MathUtils.clamp(camera.position.x, VIEW_WIDTH /2,  MAP_WIDTH - VIEW_WIDTH /2);
        camera.position.y = MathUtils.clamp(camera.position.y, VIEW_HEIGHT /2, MAP_HEIGHT - VIEW_HEIGHT /2);
        camera.update();
    }
    private void collisionDetected(){
        hero.collisionUpdate();
        npcList.get(0).collisionUpdate();

        if(hero.collision.overlaps(npcList.get(0).collision)){
            npcList.get(0).collisionDo();
        }
    }

    private void drawObject() {
        spriteBatch.draw(bgTexture, 0, 0, MAP_WIDTH, MAP_HEIGHT);
        spriteBatch.draw(npcList.get(0).getTexture(), npcList.get(0).getX(), npcList.get(0).getY());
        spriteBatch.draw(hero.getTexture(), hero.getX(), hero.getY());
    }

    public void dispose(){
        hero.getTexture().dispose();
        for(Npc list: npcList){
            list.getTexture().dispose();
        }
    }
}
