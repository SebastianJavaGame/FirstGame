package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-26.
 */

public class DialogNpc {
    private static final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("dialogueBackground.png")));
    private static final Image UP_LABEL = new Image(new Texture(Gdx.files.internal("dialogueUpLabel.png")));
    private static final Button CLOSE_BUTTON = new Button(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));

    static{
        UP_LABEL.setPosition(0, 360);
        CLOSE_BUTTON.setSize(46, 46);
        CLOSE_BUTTON.setPosition(BaseScreen.VIEW_WIDTH -CLOSE_BUTTON.getWidth() -10, UP_LABEL.getY() +2);
    }

    private Npc npc;
    private Stage stage;

    public DialogNpc(Hero hero, Npc npc, Stage stage) {
        this.npc = npc;
        this.stage = stage;

        //hero.getHero3D().setRenderHero3d(false);
        //hero.setActiveMove(true);

        addActors(BACKGROUND, UP_LABEL, CLOSE_BUTTON);
    }

    private void addActors(Actor ... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }
}
