package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class Task {
    private final Preferences PREF = Gdx.app.getPreferences(Quest.PREF_TASK);
    private static final BitmapFont FONT = new BitmapFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private final Image TASK_BACKGROUND = new Image(new Texture(Gdx.files.internal("taskBackground.png")));

    private Label lNpcName;
    private Label lTarget;
    private Label lProgress;

    static {
        STYLE.font = FONT;
    }

    public Task(int idTask){
        lNpcName = new Label(BaseTask.getNpcName(idTask), STYLE);
        lTarget = new Label(BaseTask.getTarget(idTask), STYLE);
        lProgress = new Label("W toku " + PREF.getInteger("TASK" + idTask + "_PROGRESS") + " / " + BaseTask.getProgressMax(idTask), STYLE);
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
}
