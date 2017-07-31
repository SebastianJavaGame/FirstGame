package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class FieldDialogue {
    private final Image BAR_VERTICAL_LEFT = new Image(new Texture(Gdx.files.internal("dialogueShortBar.png")));
    private final Image BAR_VERTICAL_RIGHT = new Image(new Texture(Gdx.files.internal("dialogueShortBar.png")));
    private final Image BAR_HORIZONTAL_UP = new Image(new Texture(Gdx.files.internal("dialogueLongBar.png")));
    private final Image BAR_HORIZONTAL_DOWN = new Image(new Texture(Gdx.files.internal("dialogueLongBar.png")));
    private final Stage STAGE = BaseScreen.getStage();
    private static final BitmapFont FONT = new BitmapFont();
    private static final Label.LabelStyle STYLE_WHITE = new Label.LabelStyle();
    private static final Label.LabelStyle STYLE_GREEN = new Label.LabelStyle();
    private final int POSITION_X = (int)BaseScreen.camera.position.x - BaseMap.VIEW_WIDTH /2 +40;
    private static final int LINE_LENGTH = 33;
    private static final float FONT_SIZE = 1f;
    private final FieldDialogue[] arrayDialog = DialogNpc.getFieldTextList();

    static {
        STYLE_WHITE.font = FONT;
        STYLE_GREEN.font = FONT;
        STYLE_GREEN.fontColor = new Color(Color.OLIVE);
    }

    private int idNpc;
    private int idIndexText;

    private Label label;
    private int textNpc = -1;
    private int answerFirst = -1;
    private int answerSecond = -1;
    private int answerThird = -1;

    public FieldDialogue(int idNpc, int indexText){
        this.idNpc = idNpc;
        this.idIndexText = indexText;
        String getText = BaseDialogs.getText(idNpc, indexText);
        String text = "";
        int i = -1;
        int textLength;

        try {
            int start = 0;
            int end = 1;
            int minus = 0;
            if(getText.length() >= LINE_LENGTH) {
                do {
                    i++;
                    int j = LINE_LENGTH * (i + 1) - minus;
                    while (!getText.substring(j, j + 1).equals(" ")) {
                        if (j == 0) throw new MyException();
                        else j--;
                        minus++;
                    }
                    textLength = getText.length() - j;

                    end = j;
                    text += getText.substring(start + 1, end);
                    start = j;

                    if (textLength > 0)
                        text += "\n";

                } while (textLength > LINE_LENGTH);
            }

            text += getText.substring(end, getText.length());
        }catch (Exception e){
            BaseScreen.showException(e);
            e.printStackTrace();
        }

        if (indexText < BaseDialogs.COUNT_HERO_TEXT_OPTION)
            label = new Label(text, STYLE_GREEN);
        else
            label = new Label(text, STYLE_WHITE);
        label.setFontScale(FONT_SIZE);

        try {
            if (indexText < 3) {
                int[] fieldsText = new int[BaseDialogs.getIndexToNextText(idNpc, indexText).length];
                textNpc = fieldsText[0];
                answerFirst = fieldsText[1];
                if (fieldsText.length > 2)
                    answerSecond = fieldsText[2];
                if (fieldsText.length > 3)
                    answerThird = fieldsText[3];
            }
        }catch (Exception e){
            BaseScreen.showException(e);
            e.printStackTrace();
        }
    }

    public FieldDialogue setPosition(int downY){
        int y = downY -(int)label.getHeight();
        label.setPosition(POSITION_X +2, y);
        BAR_HORIZONTAL_UP.setPosition(POSITION_X +2, y +label.getHeight() -5);
        BAR_HORIZONTAL_DOWN.setPosition(POSITION_X +2, y -5);

        BAR_VERTICAL_LEFT.setSize(BAR_VERTICAL_LEFT.getWidth(), label.getHeight() +10);
        BAR_VERTICAL_LEFT.setPosition(POSITION_X -5, BAR_HORIZONTAL_DOWN.getY());
        BAR_VERTICAL_RIGHT.setSize(BAR_VERTICAL_RIGHT.getWidth(), label.getHeight() +10);
        BAR_VERTICAL_RIGHT.setPosition(POSITION_X + BAR_HORIZONTAL_DOWN.getWidth() +1, BAR_HORIZONTAL_DOWN.getY());

        addActors(BAR_HORIZONTAL_DOWN, BAR_HORIZONTAL_UP, BAR_VERTICAL_LEFT, BAR_VERTICAL_RIGHT, label);
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 0;
     */
    public FieldDialogue info(){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                clearFieldDialogue();
                int[] arrayNewDialog = BaseDialogs.getIndexToNextText(idNpc, idIndexText);
                for(int i = 0; i < arrayNewDialog.length; i++) {
                    arrayDialog[i] = new FieldDialogue(idNpc, arrayNewDialog[i]);
                    DialogNpc.setListener(i, arrayNewDialog[i]);
                }
                DialogNpc.updatePosition();
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 1;
     */
    public FieldDialogue exit(){
        label.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DialogNpc.removeAll();
                Hero.setActiveMove(false);
                Hero3D.setRenderHero3d(true);
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 2;
     */
    public FieldDialogue shop(final Image image, final String name, final int level, final int idShop){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DialogNpc.removeAll();
                BaseScreen.getGame().setScreen(new Shop(BaseScreen.getGame(), image, name, level, idShop));
                //Hero.setActiveMove(false);
                //Hero3D.setRenderHero3d(true);
                System.out.println("shop");
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 3;
     */
    public FieldDialogue task(){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // int[] nextDialogueText = BaseDialogs.getIndexToNextText(idNpc, idIndexText);
                //add new Field with text;
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 4;
     */
    public FieldDialogue replace(){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // int[] nextDialogueText = BaseDialogs.getIndexToNextText(idNpc, idIndexText);
                //add new Field with text;
                return false;
            }
        });
        return this;
    }

    private void clearFieldDialogue(){
        for(int i = 0; i < 4; i++)
            if(arrayDialog[i] != null) {
                arrayDialog[i].clearField();
                arrayDialog[i] = null;
            }
    }

    private void addActors(Actor... actor){
        for(Actor object: actor)
            STAGE.addActor(object);
    }

    public void clearField(){
        BAR_VERTICAL_LEFT.remove();
        BAR_HORIZONTAL_UP.remove();
        BAR_VERTICAL_RIGHT.remove();
        BAR_HORIZONTAL_DOWN.remove();
        label.remove();
    }

    public int getHeightFieldText(){
        return (int)BAR_HORIZONTAL_UP.getY() +(int)BAR_HORIZONTAL_UP.getHeight() -(int)BAR_HORIZONTAL_DOWN.getY();
    }

    public Label getLabel(){
        return label;
    }

    public int getYlABEL(){
        return (int)label.getY();
    }
}
