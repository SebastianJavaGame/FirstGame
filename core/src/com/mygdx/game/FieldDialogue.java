package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class FieldDialogue {
    private static final Image BAR_VERTICAL_LEFT = new Image(new Texture(Gdx.files.internal("dialogueShortBar.png")));
    private static final Image BAR_VERTICAL_RIGHT = new Image(new Texture(Gdx.files.internal("dialogueShortBar.png")));
    private static final Image BAR_HORIZONTAL_UP = new Image(new Texture(Gdx.files.internal("dialogueLongBar.png")));
    private static final Image BAR_HORIZONTAL_DOWN = new Image(new Texture(Gdx.files.internal("dialogueLongBar.png")));
    private static final Stage STAGE = BaseScreen.getStage();
    private static final BitmapFont FONT = new BitmapFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private static final int POSITION_X = DialogNpc.POS_X +40;
    private static final int LINE_LENGTH = 35;
    private final float FONT_SIZE = 1;

    static {
        STYLE.font = FONT;
    }

    private Label label;
    private int textNpc = -1;
    private int answerFirst = -1;
    private int answerSecond = -1;
    private int answerThird = -1;

    public FieldDialogue(int idNpc, int indexText){
        String getText = TextDialogueList.getText(idNpc, indexText);
        String text = "";
        int i = -1;
        int textLength;

        try {
            int start = 0;
            int end;
            int minus = 0;
            do {
                i++;
                int j = LINE_LENGTH * (i + 1) -minus;
                while (!getText.substring(j, j +1).equals(" ")) {
                    if (j == 0) throw new MyException();
                    else j--;
                    minus++;
                }
                textLength = getText.length() -j;

                end = j;
                text += getText.substring(start +1, end);
                start = j;

                if (textLength > 0)
                    text += "\n";

            } while (textLength > LINE_LENGTH);

            text += getText.substring(end, getText.length());
        }catch (Exception e){
            BaseScreen.showException(e);
            e.printStackTrace();
        }

        label = new Label(text, STYLE);
        label.setFontScale(FONT_SIZE);

        if(indexText < 3) {
            int[] fieldsText = new int[TextDialogueList.getIndexToNextText(idNpc, indexText).length];
            textNpc = fieldsText[0];
            answerFirst = fieldsText[1];
            if (fieldsText.length > 2)
                answerSecond = fieldsText[2];
            if (fieldsText.length > 3)
                answerThird = fieldsText[3];
        }
    }

    public void setPosition(int y){
        label.setPosition(POSITION_X, y);
        BAR_HORIZONTAL_UP.setPosition(POSITION_X, y +label.getHeight());
        BAR_HORIZONTAL_DOWN.setPosition(POSITION_X, y -5);

        BAR_VERTICAL_LEFT.setSize(BAR_VERTICAL_LEFT.getWidth(), label.getHeight() +10);
        BAR_VERTICAL_LEFT.setPosition(POSITION_X -5, BAR_HORIZONTAL_DOWN.getY());
        BAR_VERTICAL_RIGHT.setSize(BAR_VERTICAL_RIGHT.getWidth(), label.getHeight() +10);
        BAR_VERTICAL_RIGHT.setPosition(POSITION_X + BAR_HORIZONTAL_DOWN.getWidth(), BAR_HORIZONTAL_DOWN.getY());

        addActors(BAR_HORIZONTAL_DOWN, BAR_HORIZONTAL_UP, BAR_VERTICAL_LEFT, BAR_VERTICAL_RIGHT, label);
    }

    private InputListener info(){
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //clear all FieldDialogue. wywołaj method with DialogNpc class
                //add new Field with text;
                return false;
            }
        };
    }

    private InputListener shop(){
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }
        };
    }

    private InputListener task(){
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }
        };
    }

    private InputListener replace(){
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return false;
            }
        };
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
}
