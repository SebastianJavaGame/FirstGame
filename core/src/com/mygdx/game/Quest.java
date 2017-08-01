package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ArrayList;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-08-01.
 */

public class Quest {
    public static final String PREF_TASK = "TASKS";
    private final Preferences PREF = Gdx.app.getPreferences(PREF_TASK);
    private static final BitmapFont FONT = new BitmapFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private Stage stage;

    private ArrayList<Integer> indexTaskList;
    private Label lTitle;

    public Quest(Stage stage) {
        this.stage = stage;
        indexTaskList = new ArrayList<Integer>();

        STYLE.font = FONT;
        lTitle = new Label("Lista zadan", STYLE);
        lTitle.setPosition(BaseScreen.VIEW_WIDTH /2 -lTitle.getWidth()*1.8f /2, 350);
        lTitle.setFontScale(1.8f);

        for(int i = 0;; i++){
            if(PREF.getInteger("TASK" + i, -1) != -1){
                indexTaskList.add(PREF.getInteger("TASK" + i));
            }else
                break;
        }

        final Table scrollTable = new Table();

        for(int index: indexTaskList){
            System.out.println(index);
            Task task = new Task(index);
            scrollTable.add(task.getTASK_BACKGROUND());
            scrollTable.row();
            scrollTable.add(task.getNpcName()).padTop(-150);
            scrollTable.row();
            scrollTable.add(task.getTarget()).padTop(-90);
            scrollTable.row();
            scrollTable.add(task.getProgress()).padTop(-30);
            scrollTable.row().padTop(30);
        }

        final ScrollPane scroll = new ScrollPane(scrollTable);
        scroll.setScrollingDisabled(true, false);

        final Table table = new Table();
        table.setBounds(5, 20, 310, 310);
        table.add(scroll);

        addActors(lTitle, table);
    }

    private void addActors(Actor ...actors){
        for(Actor a: actors)
            stage.addActor(a);
    }
}
