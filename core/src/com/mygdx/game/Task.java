package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class Task {
    private final Preferences PREF = Gdx.app.getPreferences(Quest.PREF_TASK);
    private static final BitmapFont FONT = new BitmapFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private static final TextButton.TextButtonStyle BUTTON_STYLE = new TextButton.TextButtonStyle();
    private final Image TASK_BACKGROUND = new Image(new Texture(Gdx.files.internal("taskBackground.png")));
    private final Image TASK_PROGRESS_BACKGROUND = new Image(new Texture(Gdx.files.internal("taskProgressBackground.png")));
    private final Image TASK_PROGRESS_FOREFROUND = new Image(new Texture(Gdx.files.internal("taskProgress.png")));

    private Label lNpcName;
    private Label lTarget;
    private Label lProgress;
    private TextButton cancel;

    static {
        STYLE.font = FONT;
        BUTTON_STYLE.font = FONT;
        BUTTON_STYLE.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonTaskCancel.png"))));
    }

    public Task(int idTask, final int indexTask){
        lNpcName = new Label(BaseTask.getNpcName(idTask), STYLE);
        lTarget = new Label(BaseTask.getTarget(idTask), STYLE);
        lProgress = new Label("W toku " + PREF.getInteger("TASK" + idTask + "_PROGRESS") + " / " + BaseTask.getProgressMax(idTask), STYLE);

        cancel = new TextButton("Anuluj", BUTTON_STYLE);
        cancel.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println(indexTask + "idtask");
                PREF.putInteger("TASK" + indexTask, -1);
                PREF.putInteger("TASK" +indexTask + "_PROGRESS", 0);
                PREF.flush();
                sortPrefTask();
                showPref();
                Bag.initCardQuest();
                return false;
            }
        });
    }

    public void sortPrefTask(){
        for(int i = 0;; i++){
            if(PREF.getInteger("TASK" + i, -1) == -1){
                System.out.println("a");
                if(PREF.getInteger("TASK" +(i+1), -1) == -1) {
                    break;
                }
                else {
                    int temporaryTask = PREF.getInteger("TASK" +(i+1));
                    int temporaryProgress = PREF.getInteger("TASK" +(i+1) + "_PROGRESS");
                    PREF.putInteger("TASK" +i, temporaryTask);
                    PREF.putInteger("TASK" +i + "_PROGRESS", temporaryProgress);
                    PREF.putInteger("TASK" +(i+1), -1);
                    PREF.putInteger("TASK" +(i+1) + "_PROGRESS", -1);
                    PREF.flush();
                }
            }
        }
    }

    private void showPref(){
        for(int i = 0; i < 3; i++){
            System.out.println(PREF.getInteger("TASK" +i, -1));
        }
    }

    public Image getTASK_BACKGROUND(){
        return TASK_BACKGROUND;
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

    public Image getTASK_PROGRESS_BACKGROUND(){
        return TASK_PROGRESS_BACKGROUND;
    }

    public Image getTASK_PROGRESS_FOREFROUND(){
        return TASK_PROGRESS_FOREFROUND;
    }
}
