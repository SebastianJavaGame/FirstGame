package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-09-01.
 */

public class Compass {
    public static final String LIST = "BOSS_LIST";
    private Preferences preferences;
    private Asset asset;
    private Stage stage;
    private Hero hero;

    private static Image compass;
    private static Image compassToward;

    private Image background;
    private Image mapBg;
    private Image cross;
    private Image map;
    private Image borders;
    private Image list;
    private Image[] line;
    private Image[] shadow;

    private Label lList;
    private Label lMap;
    private Label lMapName;

    private float screenX;
    private float screenY;

    private boolean openCard;

    public Compass(final Hero hero, Stage stage, Image mapBg, float width, float height){
        this.stage = stage;
        this.hero = hero;
        this.screenX = width;
        this.screenY = height;
        this.mapBg = mapBg;
        line = new Image[5];
        shadow = new Image[5];

        openCard = false;
        asset = new Asset();
        asset.loadCompassClass();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            preferences = Gdx.app.getPreferences(LIST);
            createCompass();
        }
    }

    private void createCompass(){
        if(compass != null) {
            compass.remove();
            compassToward.remove();
        }

        compass = new Image(asset.manager.get("compass.png", Texture.class));
        compass.setSize(compass.getWidth() *0.65f, compass.getHeight() *0.65f);
        compass.setPosition(BaseScreen.VIEW_WIDTH -compass.getWidth(), 0);
        compass.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!openCard) {
                    openCard = true;
                    hero.clearActions();
                    Hero3D.setStopAnimation();
                    Hero.setStopStep();
                    hero.setCharacterCollisionLook(false);
                    Hero3D.setRenderHero3d(false);
                    BaseMap.setStopGameTwo(true);
                    createCard();
                }else{
                    openCard = false;
                    removeAllActor();
                    Hero3D.setRenderHero3d(true);
                    BaseMap.setStopGameTwo(false);
                }

                //TODO create class list and view map
                return false;

            }
        });

        compassToward = new Image(asset.manager.get("compassToward.png", Texture.class));
        compassToward.setSize(compassToward.getWidth() *0.55f, compassToward.getHeight() *0.55f);
        compassToward.setPosition(compass.getX() +compass.getWidth() /2, compass.getY() +compass.getHeight() /2);
        compassToward.setTouchable(Touchable.disabled);

        stage.addActor(compass);
        stage.addActor(compassToward);
    }

    private void createCard() {
        final BitmapFont font = MyGdxGame.createDistanceFont();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        background = new Image(asset.manager.get("map.png", Texture.class));
        map = mapBg;
        map.setVisible(true);
        cross = new Image(asset.manager.get("cross.png", Texture.class));
        borders = new Image(asset.manager.get("mapMap.png", Texture.class));
        list = new Image(asset.manager.get("list.png", Texture.class));

        lList = new Label("Lista czempionów", style);
        lMap = new Label("Mapa", style);
        lMapName = new Label(BaseMap.getMapName(), style);

        for(int i = 0; i < 5; i++){
            line[i] = new Image(asset.manager.get("deleteLine.png", Texture.class));
            shadow[i] = new Image(asset.manager.get("deleteBoss.png", Texture.class));
        }

        for(int i = 0; i < 5; i++){
            line[i].setSize(161, 10);
            shadow[i].setSize(69, 70);
        }

        line[0].setPosition(88, 46);
        line[1].setPosition(88, 120);
        line[2].setPosition(88, 193);
        line[3].setSize(216, 10);
        line[3].setPosition(90, 263);
        line[4].setSize(184, 10);
        line[4].setPosition(93, 337);

        shadow[0].setPosition(14, 16);
        shadow[1].setPosition(14, 87);
        shadow[2].setPosition(14, 160);
        shadow[3].setPosition(16, 233);
        shadow[4].setPosition(17, 305);

        background.setSize(300, 410);
        background.setPosition(10, 10);
        borders.setSize(300, 410);
        borders.setPosition(10, 10);
        list.setSize(300, 410);
        list.setPosition(10, 10);

        map.setSize(280, 302);
        cross.setSize(20, 20);
        map.setPosition(19, 70);

        lList.setFontScale(0.7f);

        lMap.setPosition(BaseScreen.VIEW_WIDTH /4 -lMap.getWidth() /2 -21, 382);
        lList.setPosition(BaseScreen.VIEW_WIDTH /3 *2 -lList.getWidth()*0.7f /2 +1, 382);
        lMapName.setPosition(20 +(205 -20) /2 -lMapName.getWidth() /2, 23);

        float scrX = screenX /280;
        float scrY = screenY /302;
        cross.setPosition(19 +(hero.getX() -40) /scrX, 70 +(hero.getY() -70) /scrY);

        lMap.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                for(int i = 0; i < 5; i++) {
                    line[i].setVisible(false);
                    shadow[i].setVisible(false);
                }
                list.setVisible(false);
                map.setVisible(true);
                borders.setVisible(true);
                lMapName.setVisible(true);
                cross.setVisible(true);
                return false;
            }
        });

        lList.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                map.setVisible(false);
                borders.setVisible(false);
                lMapName.setVisible(false);
                cross.setVisible(false);
                list.setVisible(true);

                if(preferences.getBoolean("BOSS_1", false)){
                    line[0].setVisible(true);
                    shadow[0].setVisible(true);
                }
                if(preferences.getBoolean("BOSS_2", false)){
                    line[1].setVisible(true);
                    shadow[1].setVisible(true);
                }
                if(preferences.getBoolean("BOSS_3", false)){
                    line[2].setVisible(true);
                    shadow[2].setVisible(true);
                }
                if(preferences.getBoolean("BOSS_4", false)){
                    line[3].setVisible(true);
                    shadow[3].setVisible(true);
                }
                if(preferences.getBoolean("BOSS_5", false)){
                    line[4].setVisible(true);
                    shadow[4].setVisible(true);
                }

                return false;
            }
        });

        addActors(background, borders, lList, lMap, lMapName, map, cross, list, line[0], shadow[0], line[1], shadow[1], line[2], shadow[2], line[3], shadow[3], line[4], shadow[4]);
        list.setTouchable(Touchable.disabled);
        list.setVisible(false);

        for(int i = 0; i < 5; i++) {
            line[i].setVisible(false);
            shadow[i].setVisible(false);
        }
        createCompass();
    }

    private void removeAllActor(){
        background.remove();
        lList.remove();
        lMap.remove();
        map.remove();
        lMapName.remove();
        cross.remove();
        borders.remove();
        list.remove();
        for(int i = 0; i < 5; i++){
            line[i].remove();
            shadow[i].remove();
        }
    }

    private void addActors(Actor... actor) {
        for (Actor object : actor) {
            stage.addActor(object);
        }
    }

    public static void upadteTowardCompass(float rotate){
        compassToward.setRotation(rotate);
    }

    public static void disapear(){
        compassToward.setVisible(false);
        compass.setVisible(false);
        compass.setTouchable(Touchable.disabled);
    }

    public static void appear(){
        compassToward.setVisible(true);
        compass.setVisible(true);
        compass.setTouchable(Touchable.enabled);
    }
}
