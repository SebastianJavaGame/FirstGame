package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class Quest {
    public static final String PREF_TASK = "TASKS";
    private final Preferences PREF = Gdx.app.getPreferences(PREF_TASK);
    private static final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private Asset asset;
    private Stage stage;

    private static ArrayList<Integer> idTaskList;
    private Label lTitle;

    private static Sound soundComplite;

    public Quest(Stage stage) {
        this.stage = stage;
        idTaskList = new ArrayList<Integer>();

        asset = new Asset();
        asset.loadQuest();
        asset.manager.finishLoading();
        if(asset.manager.update()){
            soundComplite = asset.manager.get("sound/taskComplite.ogg", Sound.class);
        }

        STYLE.font = FONT;
        lTitle = new Label("Lista zadan", STYLE);
        lTitle.setPosition(BaseScreen.VIEW_WIDTH /2 -lTitle.getWidth()/2, 340);

        for(int i = 0;; i++){
            if(PREF.getInteger("TASK" + i, -1) != -1){
                idTaskList.add(PREF.getInteger("TASK" + i));
            }else
                break;
        }

        final Table scrollTable = new Table();

        int[] indexArray = new int[idTaskList.size()];
        for(int i = 0; i < idTaskList.size(); i++) {
            indexArray[i] = i;
        }

        for(int i = 0; i < idTaskList.size(); i++){
            Task task = new Task(idTaskList.get(i), indexArray[i]);
            scrollTable.add(task.getTaskBackground());
            scrollTable.row();
            scrollTable.add(task.getCancel()).padTop(-150).align(Align.right);
            scrollTable.row();
            scrollTable.add(task.getNpcName()).padTop(-150).padLeft((task.getProgress().getWidth() -task.getCancel().getWidth()) /2 -task.getNpcName().getWidth() -5);
            scrollTable.row();
            scrollTable.add(task.getTarget()).padTop(-90);
            scrollTable.row();
            scrollTable.add(task.getTaskProgressBackground()).padTop(-20);
            scrollTable.row();
            scrollTable.add(task.getTaskProgressForefround()).padTop(-20).width(task.getProgressPercent()).expandX().align(Align.left);
            scrollTable.row();
            scrollTable.add(task.getProgress()).padTop(-20);
            scrollTable.row().padTop(30);
        }

        final ScrollPane scroll = new ScrollPane(scrollTable);
        scroll.setScrollingDisabled(true, false);

        final Table table = new Table();
        table.setBounds(28, 20, 264, 310);
        table.add(scroll);

        addActors(lTitle, table);
    }

    public static void checkKillTargetWithTask(String enemyName){
        for(int i = 0; i < idTaskList.size(); i++){
            int idTask = idTaskList.get(i);
            if(enemyName.equals(BaseTask.getTargetName(idTask).toLowerCase())){
                Preferences preferences = Gdx.app.getPreferences(PREF_TASK);
                for(int j = 0; j != -1; j++){
                    if(preferences.getInteger("TASK" +j) == idTask){
                        preferences.putInteger("TASK" +preferences.getInteger("TASK" +j) + "_PROGRESS", preferences.getInteger("TASK" +preferences.getInteger("TASK" +j) + "_PROGRESS") +1);
                        preferences.flush();
                        j = -2;

                        float actualStep = preferences.getInteger("TASK" +idTask + "_PROGRESS", 0);
                        float endStep = BaseTask.getProgressMax(idTask);
                        float percent = actualStep /endStep;
                        if(percent >= 1) {
                            soundComplite.play();
                            BaseTask.setTaskComplete(idTask, true);
                        }
                    }
                    if(preferences.getInteger("TASK" +j, -1) == -1) {
                        if (preferences.getInteger("TASK" +(j +1), -1) == -1) {
                            j = -2;
                        }
                    }
                    if(j > 30) {
                        try {
                            throw new MyException();
                        } catch (MyException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    private void addActors(Actor ...actors){
        for(Actor a: actors)
            stage.addActor(a);
    }
}
