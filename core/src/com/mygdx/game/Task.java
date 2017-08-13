package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.Menu;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class Task {
    private final Preferences PREF = Gdx.app.getPreferences(Quest.PREF_TASK);
    private static final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private static final TextButton.TextButtonStyle BUTTON_STYLE = new TextButton.TextButtonStyle();
    private Asset asset = new Asset();
    private Image taskBackground;
    private Image taskProgressBackground;
    private Image taskProgressForefround;
    public static final int MAX_PROGRESS_PERCENT = 264;

    private Label lNpcName;
    private Label lTarget;
    private Label lProgress;
    private TextButton cancel;

    private int indexTask;
    private int idTask;

    static {
        STYLE.font = FONT;
        BUTTON_STYLE.font = FONT;
    }

    public Task(final int idTask, final int indexTask){
        this.indexTask = indexTask;
        this.idTask = idTask;

        asset.loadTask();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            taskBackground = new Image(asset.manager.get("taskBackground.png", Texture.class));
            taskProgressBackground = new Image(asset.manager.get("taskProgressBackground.png", Texture.class));
            taskProgressForefround = new Image(asset.manager.get("taskProgress.png", Texture.class));
            BUTTON_STYLE.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonTaskCancel.png", Texture.class)));

            lNpcName = new Label(BaseTask.getNpcName(idTask), STYLE);
            lTarget = new Label(BaseTask.getTarget(idTask), STYLE);
            lProgress = new Label("W toku   " + PREF.getInteger("TASK" + idTask + "_PROGRESS") + " / " + BaseTask.getProgressMax(idTask), STYLE);
            lNpcName.setFontScale(0.6f);
            lTarget.setFontScale(0.5f);
            lProgress.setFontScale(0.5f);

            cancel = new TextButton("Anuluj", BUTTON_STYLE);
            cancel.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    deleteTask(indexTask, idTask);
                    BaseTask.setTaskComplete(idTask, false);
                    return false;
                }
            });
        }
    }

    public static void deleteTask(int indexTask, int idTask){
        Preferences pref = Gdx.app.getPreferences(Quest.PREF_TASK);
        pref.putInteger("TASK" +pref.getInteger("TASK" + indexTask) + "_PROGRESS", 0).flush();
        pref.putInteger("TASK" + indexTask, -1).flush();
        sortPrefTask(pref);
        //showPref();
        Bag.initCardQuest();
    }

    public static void sortPrefTask(Preferences pref){
        for(int i = 0;; i++){
            if(pref.getInteger("TASK" + i, -1) == -1){
                if(pref.getInteger("TASK" +(i+1), -1) == -1) {
                    break;
                }
                else {
                    int temporaryTask = pref.getInteger("TASK" +(i+1));
                    int temporaryProgress = pref.getInteger("TASK" +pref.getInteger("TASK" +(i+1)) + "_PROGRESS");
                    pref.putInteger("TASK" +i, temporaryTask).flush();
                    pref.putInteger("TASK" + pref.getInteger("TASK" +i) + "_PROGRESS", temporaryProgress).flush();
                    pref.putInteger("TASK" +(i+1), -1).flush();
                    pref.putInteger("TASK" + pref.getInteger("TASK" +(i+1)) + "_PROGRESS", 0).flush();
                }
            }
        }
    }

    /*private static void showPref(){
        for(int i = 0; i < 3; i++){
            System.out.println(PREF.getInteger("TASK" +i, -1));
            System.out.println(PREF.getInteger("TASK" +i +"_PROGRESS", -1));
        }
    }*/

    public int getProgressPercent(){
        float actualStep = PREF.getInteger("TASK" +idTask + "_PROGRESS", 0);
        float endStep = BaseTask.getProgressMax(idTask);

        float percent = actualStep /endStep;
        if(percent >= 1) {
            lProgress.setText("Zrobione! Odbierz nagrode");
            lProgress.setColor(Color.OLIVE);
            BaseTask.setTaskComplete(idTask, true);
        }

        return (int)(MAX_PROGRESS_PERCENT *percent);
    }

    public Image getTaskBackground(){
        return taskBackground;
    }

    public Label getNpcName(){
        return lNpcName;
    }

    public Label getTarget(){
        return lTarget;
    }

    public Label getProgress(){
        return lProgress;
    }

    public TextButton getCancel(){
        return cancel;
    }

    public Image getTaskProgressBackground(){
        return taskProgressBackground;
    }

    public Image getTaskProgressForefround(){
        return taskProgressForefround;
    }
}
