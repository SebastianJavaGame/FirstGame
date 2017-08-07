package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-07-03.
 */

public class StatsHero {
    private Asset asset = new Asset();
    public static final String PREF_NAME_STATS = "ATRIBUTE";
    public static final int AMOUNT_ATTRIBUTE = 6;

    private final Preferences preferences = Gdx.app.getPreferences(PREF_NAME_STATS);

    private static Hero hero;
    private static Stage stage;

    private static Table table;
    private static Image money;
    private static Label moneyValue;
    private static Label infoOne;
    private static Label infoTwo;
    private static Label attribute[];
    private static Label freePoint;
    private static Label pointToAdd;
    private static Label attributeStats[];
    private static Label expirience;
    private static Label expLabel;

    private static BitmapFont font;
    private static Label.LabelStyle styleWhite;
    private static Label.LabelStyle styleGreen;

    private static Image barUp;
    private static Image uiBarEmptyExp;
    private static Image uiBarExp;
    private static Image[] barEmptyStats;
    private static ImageButton[] buttonAdd;

    public StatsHero(Stage card, Hero hero) {
        this.hero = hero;
        this.stage = card;

        attribute = new Label[AMOUNT_ATTRIBUTE];
        attributeStats = new Label[AMOUNT_ATTRIBUTE *2];
        barEmptyStats = new Image[AMOUNT_ATTRIBUTE *2];
        buttonAdd = new ImageButton[AMOUNT_ATTRIBUTE];
        create();
    }

    private void create() {
        table = new Table();
        font = new BitmapFont();
        styleWhite = new Label.LabelStyle();
        styleGreen = new Label.LabelStyle();
        styleWhite.font = font;
        styleGreen.font = font;
        styleWhite.fontColor = new Color(Color.WHITE);
        styleGreen.fontColor = new Color(Color.GREEN);

        asset.loadStatsHero();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            barUp = new Image(asset.manager.get("barX.png", Texture.class));
            uiBarExp = new Image(asset.manager.get("barExp.png", Texture.class));
            expirience = new Label("Expirience", styleWhite);
            expLabel = new Label(hero.getExp() + " / " + hero.getMaxExp(), styleWhite);
            uiBarEmptyExp = new Image(asset.manager.get("barEmpty.png", Texture.class));
            money = new Image(asset.manager.get("uiMoney.png", Texture.class));
            moneyValue = new Label(" " + hero.getMoney(), styleWhite);
            freePoint = new Label("Wolne punkty:", styleWhite);
            pointToAdd = new Label(" " + hero.getPoint(), styleGreen);
            infoOne = new Label("Non EQ", styleWhite);
            infoTwo = new Label("With Eq", styleGreen);
            attribute[0] = new Label("Hp", styleWhite);
            attribute[1] = new Label("Armor", styleWhite);
            attribute[2] = new Label("Strong", styleWhite);
            attribute[3] = new Label("Wiedza", styleWhite);
            attribute[4] = new Label("Defense\nphysic", styleWhite);
            attribute[5] = new Label("Defense\nmagic", styleWhite);

            for (int i = 0; i < barEmptyStats.length; i++) {
                barEmptyStats[i] = new Image(asset.manager.get("barStats.png", Texture.class));
                barEmptyStats[i].setSize(15, 10);
            }

            buttonAdd[0] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[0].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setMaxHp(hero.getMaxHp() + 10);
                        hero.setHpEq(hero.getHpEq() + 10);
                        hero.setPoint(hero.getPoint() - 1);
                        pointToAdd.setText("" + hero.getPoint());
                        attributeStats[0].setText("" + hero.getMaxHp());
                        attributeStats[1].setText("" + hero.getHpEq());
                        preferences.putInteger("FREE_POINT", hero.getPoint());
                        preferences.putInteger("MAX_HP", hero.getMaxHp());
                        preferences.flush();
                        System.out.println(hero.getMaxHp());
                        try {
                            new UpdateHeroStats(hero);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
            });

            buttonAdd[1] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[1].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setArmor(hero.getArmor() + 1);
                        hero.setArmorEq(hero.getArmorEq() + 1);
                        hero.setPoint(hero.getPoint() - 1);
                        pointToAdd.setText("" + hero.getPoint());
                        attributeStats[2].setText("" + hero.getArmor() + "%");
                        attributeStats[3].setText("" + hero.getArmorEq() + "%");
                        preferences.putInteger("FREE_POINT", hero.getPoint());
                        preferences.putInteger("ARMOR", hero.getArmorEq());
                        preferences.flush();
                    }
                    return false;
                }
            });

            buttonAdd[2] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[2].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setStrong(hero.getStrong() + 1);
                        hero.setStrongEq(hero.getStrongEq() + 1);
                        implementListener(4, hero.getStrong(), hero.getStrongEq(), "STRONG");
                    }
                    return false;
                }
            });

            buttonAdd[3] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[3].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setWiedza(hero.getWiedza() + 1);
                        hero.setWiedzaEq(hero.getWiedzaEq() + 1);
                        implementListener(6, hero.getWiedza(), hero.getWiedzaEq(), "WIEDZA");
                    }
                    return false;
                }
            });

            buttonAdd[4] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[4].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setDefenseFiz(hero.getDefenseFiz() + 1);
                        hero.setDefenseFizEq(hero.getDefenseFizEq() + 1);
                        implementListener(8, hero.getDefenseFiz(), hero.getDefenseFizEq(), "DEFENSE_FIZ");
                    }
                    return false;
                }
            });

            buttonAdd[5] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[5].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        hero.setDefenseMag(hero.getDefenseMag() + 1);
                        hero.setDefenseMagEq(hero.getDefenseMagEq() + 1);
                        implementListener(10, hero.getDefenseMag(), hero.getDefenseMagEq(), "DEFENSE_MAG");
                    }
                    return false;
                }
            });

            for (ImageButton imageSize : buttonAdd)
                imageSize.setSize(15, 10);

            attributeStats[0] = new Label(String.valueOf(hero.getHpNonEq()), styleWhite);
            attributeStats[0].setPosition(115 - attributeStats[0].getWidth() / 2, 290);

            attributeStats[1] = new Label(String.valueOf(hero.getHpEq()), styleGreen);
            attributeStats[1].setPosition(215 - attributeStats[1].getWidth() / 2, 290);

            attributeStats[2] = new Label(hero.getArmor() + "%", styleWhite);
            attributeStats[2].setPosition(115 - attributeStats[2].getWidth() / 2, 249);

            attributeStats[3] = new Label(hero.getArmorEq() + "%", styleGreen);
            attributeStats[3].setPosition(215 - attributeStats[3].getWidth() / 2, 249);

            attributeStats[4] = new Label(String.valueOf(hero.getStrong()), styleWhite);
            attributeStats[4].setPosition(115 - attributeStats[4].getWidth() / 2, 210);

            attributeStats[5] = new Label(String.valueOf(hero.getStrongEq()), styleGreen);
            attributeStats[5].setPosition(215 - attributeStats[5].getWidth() / 2, 210);

            attributeStats[6] = new Label(String.valueOf(hero.getWiedza()), styleWhite);
            attributeStats[6].setPosition(115 - attributeStats[6].getWidth() / 2, 170);

            attributeStats[7] = new Label(String.valueOf(hero.getWiedzaEq()), styleGreen);
            attributeStats[7].setPosition(215 - attributeStats[7].getWidth() / 2, 170);

            attributeStats[8] = new Label(String.valueOf(hero.getDefenseFiz()), styleWhite);
            attributeStats[8].setPosition(115 - attributeStats[6].getWidth() / 2, 127);

            attributeStats[9] = new Label(String.valueOf(hero.getDefenseFizEq()), styleGreen);
            attributeStats[9].setPosition(215 - attributeStats[7].getWidth() / 2, 127);

            attributeStats[10] = new Label(String.valueOf(hero.getDefenseMag()), styleWhite);
            attributeStats[10].setPosition(115 - attributeStats[6].getWidth() / 2, 80);

            attributeStats[11] = new Label(String.valueOf(hero.getDefenseMagEq()), styleGreen);
            attributeStats[11].setPosition(215 - attributeStats[7].getWidth() / 2, 80);

            expLabel.setPosition(BaseMap.VIEW_WIDTH / 2 - expLabel.getWidth() / 2, 14);
            expirience.setPosition(BaseMap.VIEW_WIDTH / 2 - expirience.getWidth() / 2, expLabel.getY() + 20);
            uiBarEmptyExp.setPosition(10, 10);
            uiBarEmptyExp.setSize(BaseMap.VIEW_WIDTH - 20, 24);
            uiBarExp.setPosition(uiBarEmptyExp.getX() + 5, uiBarEmptyExp.getY() + 3);

            float procent = (float) hero.getExp() / hero.getMaxExp() * 100;
            if (procent < 3)
                procent = 3;
            uiBarExp.setSize(procent / 100 * uiBarEmptyExp.getWidth() - 9, uiBarEmptyExp.getHeight() - 6);

            barUp.setBounds(0, 340, BaseMap.VIEW_WIDTH + 15, 35);
            money.setBounds(40 + moneyValue.getWidth(), barUp.getY() + 2, 32, 31);

            table.row().padTop(40);
            table.add();
            table.add(moneyValue).align(Align.left).padLeft(-35);
            table.add(freePoint);
            table.add(pointToAdd).align(Align.left);
            table.row().padTop(15);
            table.add();
            table.add(infoOne);
            table.add(infoTwo);
            table.row();
            table.add(attribute[0]);
            table.add(barEmptyStats[0]);
            table.add(barEmptyStats[1]);
            table.add(buttonAdd[0]);
            table.row().padTop(10);
            table.add(attribute[1]);
            table.add(barEmptyStats[2]);
            table.add(barEmptyStats[3]);
            table.add(buttonAdd[1]);
            table.row().padTop(10);
            table.add(attribute[2]);
            table.add(barEmptyStats[4]);
            table.add(barEmptyStats[5]);
            table.add(buttonAdd[2]);
            table.row().padTop(10);
            table.add(attribute[3]);
            table.add(barEmptyStats[6]);
            table.add(barEmptyStats[7]);
            table.add(buttonAdd[3]);
            table.row().padTop(10);
            table.add(attribute[4]);
            table.add(barEmptyStats[8]);
            table.add(barEmptyStats[9]);
            table.add(buttonAdd[4]);
            table.row().padTop(10);
            table.add(attribute[5]);
            table.add(barEmptyStats[10]);
            table.add(barEmptyStats[11]);
            table.add(buttonAdd[5]);

            table.setFillParent(true);
            table.pack();

            stage.addActor(barUp);
            stage.addActor(money);
            stage.addActor(table);
            stage.addActor(uiBarEmptyExp);
            stage.addActor(uiBarExp);
            stage.addActor(expLabel);
            stage.addActor(expirience);

            for (Label attribute : attributeStats)
                stage.addActor(attribute);
        }
    }

    private void implementListener(int i, int nonEq, int withEq, String key){
        hero.setPoint(hero.getPoint() -1);
        pointToAdd.setText("" + hero.getPoint());
        attributeStats[i].setText("" + nonEq);
        attributeStats[i +1].setText("" + withEq);
        preferences.putInteger("FREE_POINT", hero.getPoint());
        preferences.putInteger(key, nonEq);
        preferences.flush();
    }
}
