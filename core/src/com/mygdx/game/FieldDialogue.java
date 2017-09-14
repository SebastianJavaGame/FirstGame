package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

import Screen.*;

/**
 * Created by Sebastian on 2017-07-28.
 */

public class FieldDialogue {
    private Asset asset = new Asset();
    private Image barVerticalLeft;
    private Image barVerticalRight;
    private Image barHorizontalUp;
    private Image barHorizontalDown;
    public static ArrayList<Integer> actualTask;
    private final Stage STAGE = BaseMap.getStageUi();
    private static final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private static final Label.LabelStyle STYLE_WHITE = new Label.LabelStyle();
    private static final Label.LabelStyle STYLE_GREEN = new Label.LabelStyle();
    public final int POSITION_X = 35;
    private static final int LINE_LENGTH = 27;
    private final FieldDialogue[] arrayDialog = DialogNpc.getFieldTextList();
    private final Preferences PREF = Gdx.app.getPreferences(Quest.PREF_TASK);

    static {
        STYLE_WHITE.font = FONT;
        STYLE_GREEN.font = FONT;
        STYLE_GREEN.fontColor = new Color(Color.OLIVE);
        actualTask = new ArrayList<Integer>();
    }

    private static Label lExp;
    private static Label lMoney;
    private static Label lClose;
    private static Image iExp;
    private static Image iMoney;
    private static Image barLeft;
    private static Image barUp;
    private static Image barRight;
    private static Image barDown;
    private static Image barLeftTwo;
    private static Image barUpTwo;
    private static Image barRightTwo;
    private static Image barDownTwo;

    private int idNpc;
    private int idIndexText;

    private Label label;
    private int textNpc = -1;
    private int answerFirst = -1;
    private int answerSecond = -1;
    private int answerThird = -1;

    private Sound textDialog;
    private String text;

    public FieldDialogue(int idNpc, int indexText){
        asset.loadFieldDialogue();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            textDialog = asset.manager.get("sound/textDialog.ogg", Sound.class);
            barVerticalLeft = new Image(asset.manager.get("dialogueShortBar.png", Texture.class));
            barVerticalRight = new Image(asset.manager.get("dialogueShortBar.png", Texture.class));
            barHorizontalUp = new Image(asset.manager.get("dialogueLongBar.png", Texture.class));
            barHorizontalDown = new Image(asset.manager.get("dialogueLongBar.png", Texture.class));
            this.idNpc = idNpc;
            this.idIndexText = indexText;
            String getText = BaseDialogs.getText(idNpc, indexText);
            this.text = getText;
            String text = "";
            int i = -1;
            int textLength;

            try {
                int start = 0;
                int end = 1;
                int minus = 0;
                if (getText.length() - 1 >= LINE_LENGTH) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (indexText < BaseDialogs.INDEX_LISTENER[idNpc].length)
                label = new Label(text, STYLE_GREEN);
            else
                label = new Label(text, STYLE_WHITE);
            label.setFontScale(0.6f);

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
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
    }

    public FieldDialogue setPosition(int downY){
        int y = downY -(int)(label.getHeight()*0.6f);
        barHorizontalUp.setSize(barHorizontalUp.getWidth() +10, barHorizontalUp.getHeight());
        barHorizontalDown.setSize(barHorizontalDown.getWidth() +10, barHorizontalDown.getHeight());
        barHorizontalUp.setPosition(POSITION_X + 2, y + label.getHeight()*0.6f - 5);
        barHorizontalDown.setPosition(POSITION_X + 2, y - 5);
        label.setHeight((barHorizontalUp.getY() -barHorizontalDown.getY()) +8);
        label.setPosition(POSITION_X + 2, barHorizontalDown.getY() +2);

        barVerticalLeft.setSize(barVerticalLeft.getWidth(), label.getHeight()*0.6f +10);
        barVerticalLeft.setPosition(POSITION_X -5, barHorizontalDown.getY());
        barVerticalRight.setSize(barVerticalRight.getWidth(), label.getHeight()*0.6f +10);
        barVerticalRight.setPosition(POSITION_X + barHorizontalDown.getWidth() +1, barHorizontalDown.getY());

        addActors(barHorizontalDown, barHorizontalUp, barVerticalLeft, barVerticalRight, label);
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 0;
     */
    public FieldDialogue info(){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textDialog.play(0.5f);
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
                textDialog.play(0.5f);
                DialogNpc.removeAll();
                Hero.setActiveMove(false);
                Hero3D.setRenderHero3d(true);
                Compass.appear();
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
                textDialog.play(0.5f);
                DialogNpc.removeAll();
                Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
                preferences.putInteger("HP", preferences.getInteger("HP")).flush();
                BaseScreen.getGame().setScreen(new Screen.Shop(BaseScreen.getGame(), image, name, level, idShop));
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialogs.INDEX_LISTENER = 3;
     */
    public FieldDialogue task(final int idTask){
        label.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textDialog.play(0.5f);
                DialogNpc.removeAll();
                Hero.setActiveMove(false);
                Hero3D.setRenderHero3d(true);
                for(int i = 0;; i++){
                    for(Integer a: actualTask)
                        if (a == idTask)
                            return false;

                    if(PREF.getInteger("TASK" + i, -1) == -1) {
                        PREF.putInteger("TASK" + i, idTask).flush();
                        actualTask.add(idTask);
                        BaseMap.addRedLight();
                        Bag.addRedLight(2);
                        break;
                    }
                }
                Compass.appear();
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
                Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
                Hero.setActiveMove(false);
                preferences.putInteger("COLLISION", 0).flush();
                preferences.putInteger("POS_X", MapBoss_01.STARTING_POS_X).flush();
                preferences.putInteger("POS_Y", MapBoss_01.STARTING_POS_Y).flush();
                BaseScreen.getGame().setScreen(new MapBoss_01(BaseScreen.getGame()));
                return false;
            }
        });
        return this;
    }

    /**
     * BaseDialog.INDEX_LISTENER = 5;
     */
    public FieldDialogue rewardFromTask(final int idTask){
        label.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                textDialog.play(0.5f);
                clearFieldDialogue();

                for(int i = 0; i < actualTask.size(); i++){
                    if(actualTask.get(i) == idTask)
                        actualTask.remove(i);
                }

                for(int j = 0; j != -1; j++) {
                    if (PREF.getInteger("TASK" + j) == idTask) {
                        BaseTask.setTaskComplete(idTask, false);
                        Task.deleteTask(j, idTask);
                        j = -2;
                    }
                    if (PREF.getInteger("TASK" + j, -1) == -1) {
                        if (PREF.getInteger("TASK" + (j + 1), -1) == -1) {
                            j = -2;
                        }
                    }
                }

                int exp = BaseTask.getRewardExperience(idTask);
                int money = BaseTask.getRewardMoney(idTask);
                float temporary = MathUtils.random(-12, 12);

                temporary = (100 +temporary) /100;
                exp *= temporary;

                temporary = MathUtils.random(-12, 12);
                temporary = (100 +temporary) /100;
                money *= temporary;

                Hero.setMoney(Hero.getMoney() +money);


                do{
                    Hero.setExp(Hero.getExp() + exp);
                    if(Hero.getExp() >= Hero.getMaxExp()){
                        Hero.setExp(Hero.getExp() -Hero.getMaxExp());
                       Hero.levelUp();
                    }
                }
                while(Hero.getExp() +exp >= Hero.getMaxExp());

                lExp = new Label("Otrzymales " + exp + " dos.", STYLE_WHITE);
                lMoney = new Label("Otrzymales " + money + " zlota", STYLE_WHITE);
                lClose = new Label("Ruszam dalej", STYLE_GREEN);

                lExp.setFontScale(0.6f);
                lMoney.setFontScale(0.6f);
                lClose.setFontScale(0.6f);

                lExp.setColor(new Color(Color.OLIVE));
                lMoney.setColor(new Color(Color.GOLD));

                barLeft = new Image(asset.manager.get("dialogueShortBar.png", Texture.class));
                barRight = new Image(asset.manager.get("dialogueShortBar.png", Texture.class));
                barUp = new Image(asset.manager.get("dialogueLongBar.png", Texture.class));
                barDown = new Image(asset.manager.get("dialogueLongBar.png", Texture.class));

                barLeftTwo = barVerticalLeft;
                barRightTwo = barVerticalRight;
                barDownTwo = barHorizontalDown;
                barUpTwo = barHorizontalUp;

                iMoney = new Image(asset.manager.get("uiMoney.png", Texture.class));
                iExp = new Image(asset.manager.get("uiExp.png", Texture.class));

                lExp.setPosition(POSITION_X +4, 360 -lExp.getHeight());
                lMoney.setPosition(POSITION_X +4, lExp.getY() -25);
                lClose.setPosition(POSITION_X +4, lMoney.getY() -45);

                lClose.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        DialogNpc.removeAll();
                        clearDialogueReward();
                        Hero.setActiveMove(false);
                        Hero3D.setRenderHero3d(true);
                        return false;
                    }
                });

                barUp.setPosition(POSITION_X +2, lExp.getY() +lExp.getHeight() -2);
                barDown.setPosition(POSITION_X +2, lMoney.getY() -8);
                barLeft.setSize(barLeft.getWidth(), lExp.getHeight() +lExp.getHeight());
                barLeft.setPosition(POSITION_X -5, barDown.getY());
                barRight.setSize(barRight.getWidth(), lExp.getHeight() +lExp.getHeight());
                barRight.setPosition(POSITION_X +barDown.getWidth() +1, barDown.getY());

                iExp.setSize(lExp.getHeight()/2, lExp.getHeight()/2);
                iExp.setPosition(lExp.getX() + lExp.getWidth()*0.6f +4, lExp.getY() +4);
                iMoney.setSize(lMoney.getHeight()/2, lMoney.getHeight()/2);
                iMoney.setPosition(lMoney.getX() + lMoney.getWidth()*0.6f +4, lMoney.getY() +4);

                barUpTwo.setPosition(POSITION_X +2, lClose.getY() +lClose.getHeight() -6);
                barDownTwo.setPosition(POSITION_X +2, lClose.getY() -2);
                barLeftTwo.setSize(barVerticalLeft.getWidth(), lClose.getHeight());
                barLeftTwo.setPosition(POSITION_X -5, barHorizontalDown.getY());
                barRightTwo.setSize(barVerticalRight.getWidth(), lClose.getHeight());
                barRightTwo.setPosition(POSITION_X + barHorizontalDown.getWidth() +1, barHorizontalDown.getY());

                addActors(lExp, lMoney, lClose, barUp, barDown, barLeft, barRight, iExp, iMoney, barDownTwo, barLeftTwo, barRightTwo, barUpTwo);
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

    public static void clearDialogueReward(){
        if(barUp != null) {
            barUpTwo.remove();
            barDownTwo.remove();
            barRightTwo.remove();
            barLeftTwo.remove();
            lClose.remove();
            lExp.remove();
            lMoney.remove();
            barDown.remove();
            barLeft.remove();
            barRight.remove();
            barUp.remove();
            iExp.remove();
            iMoney.remove();
        }
    }

    private void addActors(Actor... actor){
        for(Actor object: actor)
            STAGE.addActor(object);
    }

    public void clearField(){
        barVerticalLeft.remove();
        barHorizontalUp.remove();
        barVerticalRight.remove();
        barHorizontalDown.remove();
        label.remove();
    }

    public Label getLabel(){
        return label;
    }

    public int getYlABEL(){
        return (int)label.getY();
    }

    public String getText(){
        return text;
    }
}
