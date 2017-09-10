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
import Screen.Menu;

/**
 * Created by Sebastian on 2017-07-26.
 */

public class DialogNpc {
    private static final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("dialogueBackground.jpg")));
    private static final Image UP_LABEL = new Image(new Texture(Gdx.files.internal("dialogueUpLabel.png")));
    private static final Button CLOSE_BUTTON = new Button(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonExit.png")))));

    private int textNpc;
    private int textHero0;
    private int textHero1;
    private int textHero2;
    private int taskReward;

    private static final BitmapFont FONT_NAME = MyGdxGame.createFontName();
    private static final Label.LabelStyle STYLE_NAME = new Label.LabelStyle();
    static {
        STYLE_NAME.font = FONT_NAME;
    }

    private static Npc npc;
    private Stage stage;

    private static FieldDialogue[] fieldTextList;

    private static Label lName;
    private static Label lLevel;
    private static Image imageHead;

    public DialogNpc(Npc npc) {
        this.npc = npc;
        this.stage = BaseMap.getStageUi();
        fieldTextList = new FieldDialogue[4];

        Compass.disapear();

        textNpc = BaseDialogs.STARTING_TEXT[npc.getId()][0];
        textHero0 = BaseDialogs.STARTING_TEXT[npc.getId()][1];
        textHero1 = BaseDialogs.STARTING_TEXT[npc.getId()][2];
        textHero2 = BaseDialogs.STARTING_TEXT[npc.getId()][3];
        taskReward = BaseDialogs.STARTING_TEXT[npc.getId()][4];

        BACKGROUND.setSize(270, 400);
        BACKGROUND.setPosition((BaseScreen.VIEW_WIDTH -BACKGROUND.getWidth()) /2, 360 -345);
        UP_LABEL.setSize(BACKGROUND.getWidth() +20, UP_LABEL.getHeight());//lTODO upLabel add background convert to one object
        UP_LABEL.setPosition(BACKGROUND.getX() -15, 370);
        CLOSE_BUTTON.setSize(60, 60);
        CLOSE_BUTTON.setPosition(BaseScreen.VIEW_WIDTH -CLOSE_BUTTON.getWidth() +1, UP_LABEL.getY() -6);
        CLOSE_BUTTON.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeAll();
                FieldDialogue.clearDialogueReward();
                Hero.setActiveMove(false);
                Hero3D.setRenderHero3d(true);
                Compass.appear();
                return false;
            }
        });

        lName = new Label("" + npc.getName(), STYLE_NAME);
        lLevel = new Label("Poziom " + npc.getLevel(), STYLE_NAME);

        lName.setFontScale(0.5f);
        lLevel.setFontScale(0.5f);

        imageHead = npc.getHead();

        int lengthX = 122;

        imageHead.setSize(60, 60);
        imageHead.setPosition(UP_LABEL.getX() -9, UP_LABEL.getY() -6);

        Hero3D.setRenderHero3d(false);
        Hero.setActiveMove(true);

        lName.setPosition(lengthX - lName.getWidth()*0.5f / 2, UP_LABEL.getY() -2);
        lLevel.setPosition(lengthX - lLevel.getWidth()*0.5f / 2, UP_LABEL.getY() -22);
        addActors(BACKGROUND, UP_LABEL, CLOSE_BUTTON, lName, lLevel, imageHead);

        create();
    }

    private void create(){
        //Hero.setActiveMove(false);
        fieldTextList[0] = new FieldDialogue(npc.getId(), textNpc);
        fieldTextList[1] = new FieldDialogue(npc.getId(), textHero0);
        setListener(1, textHero0);

        if(textHero1 != -1) {
            fieldTextList[2] = new FieldDialogue(npc.getId(), textHero1);
            setListener(2, textHero1);
        }

        if(BaseTask.isComplete(npc.getIdTask())) {
            if(taskReward != -1) {
                fieldTextList[3] = new FieldDialogue(npc.getId(), taskReward);
                setListener(3, taskReward);
            }
        }
        else {
            if(textHero2 != -1) {
                fieldTextList[3] = new FieldDialogue(npc.getId(), textHero2);
                setListener(3, textHero2);
            }
        }
        updatePosition();
    }

    public static void updatePosition(){
        if(fieldTextList[0] != null) {
            fieldTextList[0].setPosition(360);
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
            case 5:
                fieldTextList[idFieldDialogue].rewardFromTask(npc.getIdTask());
                break;
            default:
                try {
                    throw new MyException();
                } catch (MyException e) {
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