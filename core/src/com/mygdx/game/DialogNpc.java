package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-26.
 */

public class DialogNpc {
    private final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("dialogueBackground.png")));
    private final Image UP_LABEL = new Image(new Texture(Gdx.files.internal("dialogueUpLabel.png")));
    private final Button CLOSE_BUTTON = new Button(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));

    public final int POS_X = (int)BaseScreen.camera.position.x - BaseMap.VIEW_WIDTH /2;
    public final int POS_Y = (int)BaseScreen.camera.position.y - BaseMap.VIEW_HEIGHT /2;
    private final int POS_TEXT_FIELD_NPC = POS_Y +360;

    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    static {
        style.font = font;
    }

    private Npc npc;
    private Stage stage;

    private FieldDialogue[] fieldTextList;

    private Label lName;
    private Label lLevel;

    private Image imageHead;

    public DialogNpc(Npc npc) {
        this.npc = npc;
        this.stage = BaseScreen.getStage();
        fieldTextList = new FieldDialogue[4];

        BACKGROUND.setSize(BACKGROUND.getWidth(), 400);
        BACKGROUND.setPosition(POS_X +(BaseScreen.VIEW_WIDTH -BACKGROUND.getWidth()) /2, POS_Y +15);
        UP_LABEL.setSize(BACKGROUND.getWidth() +20, UP_LABEL.getHeight());//TODO upLabel add background convert to one object
        UP_LABEL.setPosition(BACKGROUND.getX() -15, POS_Y +370);
        CLOSE_BUTTON.setSize(60, 60);
        CLOSE_BUTTON.setPosition(POS_X +BaseScreen.VIEW_WIDTH -CLOSE_BUTTON.getWidth() +1, UP_LABEL.getY() -6);
        CLOSE_BUTTON.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                removeAll();
                Hero.setActiveMove(false);
                Hero3D.setRenderHero3d(true);
                return false;
            }
        });

        lName = new Label("" + npc.getName(), style);
        lLevel = new Label("Poziom " + npc.getLevel(), style);

        imageHead = npc.getHead();

        int lengthX = POS_X +122;
        lName.setPosition(lengthX -lName.getWidth() /2, UP_LABEL.getY() +27);
        lLevel.setPosition(lengthX -lLevel.getWidth() /2, UP_LABEL.getY() +9);

        imageHead.setSize(60, 60);
        imageHead.setPosition(UP_LABEL.getX() -9, UP_LABEL.getY() -6);

        Hero3D.setRenderHero3d(false);
        Hero.setActiveMove(true);

        addActors(BACKGROUND, UP_LABEL, CLOSE_BUTTON, lName, lLevel, imageHead);
        create();
    }

    private void create(){
        fieldTextList[0] = new FieldDialogue(npc.getId(), 3).setPosition(POS_TEXT_FIELD_NPC);
    }

    private void removeAll(){
        BACKGROUND.remove();
        UP_LABEL.remove();
        CLOSE_BUTTON.remove();
        lLevel.remove();
        lName.remove();
        imageHead.remove();
        for(FieldDialogue dialogue: fieldTextList) {
            if(dialogue != null)
                dialogue.clearField();
        }
    }

    private void addActors(Actor ... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }
}
