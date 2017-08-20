package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseScreen;
import Screen.Menu;

/**
 * Created by Sebastian on 2017-06-10.
 */

public class Bag {
    private Hero hero;
    private Asset asset = new Asset();

    private Stage stage;
    private static Stage card;

    private ImageButton buttonEq;
    private ImageButton buttonStats;
    private ImageButton buttonQuest;
    private ImageButton buttonExit;
    private ImageButton closeGame;

    private Image background;
    private Image separator;

    private BitmapFont font;
    private Label.LabelStyle styleWhite;
    private Label infoCloseCard;
    private Label infoCloseGame;

    private Sound page;

    public Bag(Stage card){
        this.card = card;
    }

    public Bag(Stage stage, Stage card, Hero hero){
        this.hero = hero;
        this.stage = stage;
        this.card = card;

        create();
    }

    private void create() {
        asset.loadBag();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            page = asset.manager.get("sound/card.ogg", Sound.class);

            background = new Image(asset.manager.get("statsBackground.png", Texture.class));
            background.setSize(BaseScreen.VIEW_WIDTH, BaseScreen.VIEW_HEIGHT - 50);

            buttonEq = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("eq.png", Texture.class))));
            buttonEq.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (!Equipment.getBlockClick()) {
                        try {
                            page.play();
                            initCardEq();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            });
            buttonEq.setPosition(background.getX() + 30, 385);

            buttonStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("stats.png", Texture.class))));
            buttonStats.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (!Equipment.getBlockClick()) {
                        page.play();
                        initCardStats();
                    }
                    return false;
                }
            });
            buttonStats.setPosition(background.getX() + 103, 385);

            buttonQuest = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("quest.png", Texture.class))));
            buttonQuest.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (!Equipment.getBlockClick()){
                        page.play();
                        initCardQuest();
                    }
                    return false;
                }
            });
            buttonQuest.setPosition(background.getX() + 176, 385);

            buttonExit = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("exit.png", Texture.class))));
            buttonExit.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (!Equipment.getBlockClick()){
                        page.play();
                        initCardExit();
                    }
                    return false;
                }
            });
            buttonExit.setPosition(background.getX() + 250, 385);

            buttonExit.setDebug(true);
            buttonEq.setDebug(true);
            buttonQuest.setDebug(true);
            buttonStats.setDebug(true);

            stage.addActor(background);
            stage.addActor(buttonEq);
            stage.addActor(buttonStats);
            stage.addActor(buttonQuest);
            stage.addActor(buttonExit);

            if (!Equipment.getBlockClick()) {
                try {
                    initCardEq();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initCardEq() throws CloneNotSupportedException {
        card.clear();
        new Equipment(card, hero);
    }

    private void initCardStats() {
        card.clear();
        new StatsHero(card, hero);
    }

    public static void initCardQuest(){
        card.clear();
        new Quest(card);
    }

    private void initCardExit() {
        card.clear();

        font = MyGdxGame.createDistanceFont();
        styleWhite = new Label.LabelStyle();
        styleWhite.font = font;

        infoCloseCard = new Label("Kliknij ikonę plecaka\naby zamknąć tę kartę", styleWhite);
        infoCloseCard.setFontScale(0.7f);
        infoCloseCard.setPosition((background.getWidth() - infoCloseCard.getWidth() *0.7f) /2, 300);

        separator = new Image(asset.manager.get("bar.png", Texture.class));
        separator.setPosition(5, 250);
        separator.setSize(310, 3);

        infoCloseGame = new Label("Wyjście z gry", styleWhite);
        infoCloseGame.setPosition((background.getWidth() - infoCloseGame.getWidth() *0.9f) /2, 180);
        infoCloseGame.setFontScale(0.9f);

        closeGame = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("exitGame.png", Texture.class))));
        closeGame.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                //TODO point saving game
                Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);

                preferences.putInteger("FREE_POINT", preferences.getInteger("FREE_POINT")).flush();
                preferences.putInteger("EXP", hero.getExp()).flush();
                preferences.putInteger("MONEY", hero.getMoneyNoStatic()).flush();
                preferences.putInteger("HP", hero.getHp()).flush();
                preferences.putInteger("ARMOR", hero.getArmor()).flush();
                preferences.putInteger("STRONG", hero.getStrong()).flush();
                preferences.putInteger("WIEDZA", hero.getWiedza()).flush();
                preferences.putInteger("DEFENSE_FIZ", hero.getDefenseFiz()).flush();
                preferences.putInteger("DEFENSE_MAG", hero.getDefenseMag()).flush();
                preferences.putInteger("POS_X", (int)hero.getX()).flush();
                preferences.putInteger("POS_Y", (int)hero.getY()).flush();
                Menu.getSoundClick().play();
                Hero3D.dispose();
                stage.dispose();
                BaseScreen.getGame().dispose();
                Gdx.app.exit();
                return false;
            }
        });
        closeGame.setPosition((background.getWidth() - closeGame.getWidth()) /2, 80);

        card.addActor(infoCloseCard);
        card.addActor(separator);
        card.addActor(infoCloseGame);
        card.addActor(closeGame);
    }
}
