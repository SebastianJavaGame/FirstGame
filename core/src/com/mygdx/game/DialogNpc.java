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
    private static final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("dialogueBackground.png")));
    private static final Image UP_LABEL = new Image(new Texture(Gdx.files.internal("dialogueUpLabel.png")));
    private static final Button CLOSE_BUTTON = new Button(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));

    public final int POS_X = (int)BaseScreen.camera.position.x - BaseMap.VIEW_WIDTH /2;
    public static int POS_Y = (int)BaseScreen.camera.position.y - BaseMap.VIEW_HEIGHT /2;
    private static int POS_TEXT_FIELD_NPC = POS_Y +360;

    private static final int START_NPC_TEXT = 7;
    private static final int START_ANSWER_ONE = 0;
    private static final int START_ANSWER_TWO = 1;
    private static final int START_ANSWER_THREE = 3;

    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    static {
        style.font = font;
    }

    private static Npc npc;
    private Stage stage;

    private static FieldDialogue[] fieldTextList;

    private static Label lName;
    private static Label lLevel;
    private static Image imageHead;

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
        fieldTextList[0] = new FieldDialogue(npc.getId(), START_NPC_TEXT).setPosition(POS_TEXT_FIELD_NPC);
        fieldTextList[1] = new FieldDialogue(npc.getId(), START_ANSWER_ONE);
        fieldTextList[2] = new FieldDialogue(npc.getId(), START_ANSWER_TWO);
        fieldTextList[3] = new FieldDialogue(npc.getId(), START_ANSWER_THREE);

        updatePosition();
        setListener(1, START_ANSWER_ONE);
        setListener(2, START_ANSWER_TWO);
        setListener(3, START_ANSWER_THREE);
    }

    public static void updatePosition(){
        if(fieldTextList[0] != null) {
            fieldTextList[0].setPosition(POS_TEXT_FIELD_NPC);
            fieldTextList[0].getLabel().clearListeners();
        }
        for(int i = 1; i < 4; i++){
            if(fieldTextList[i] != null){
                fieldTextList[i].setPosition(fieldTextList[i -1].getYlABEL() -15);
            }
        }
    }

    public static void setListener(int idFieldDialogue, int idText){
        int chooseListener;
        try {
            chooseListener = BaseDialogs.getIndexListener(npc.getId(), idText);
        }catch (ArrayIndexOutOfBoundsException e){
            return;
        }

        switch (chooseListener){
            case 0:
                fieldTextList[idFieldDialogue].info();
                break;
            case 1:
                System.out.println("set exit");
                fieldTextList[idFieldDialogue].exit();
                break;
            case 2:
                fieldTextList[idFieldDialogue].shop(imageHead, npc.getName(), npc.getLevel(), npc.getIdShop());
                break;
            case 3:
                fieldTextList[idFieldDialogue].task(npc.getIdTask());
                break;
            case 4:
                fieldTextList[idFieldDialogue].replace();
                break;
            default:
                try {
                    throw new MyException();
                } catch (MyException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void removeAll(){
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

    public static FieldDialogue[] getFieldTextList(){
        return fieldTextList;
    }
}