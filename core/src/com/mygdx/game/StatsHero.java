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
import Screen.Menu;

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
        font = MyGdxGame.createDistanceFont();
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
            expirience = new Label("Doświadczenie", styleWhite);
            expLabel = new Label(hero.getExp() + " / " + hero.getMaxExp(), styleWhite);
            uiBarEmptyExp = new Image(asset.manager.get("barEmpty.png", Texture.class));
            money = new Image(asset.manager.get("uiMoney.png", Texture.class));
            moneyValue = new Label(" " + hero.getMoney(), styleWhite);
            moneyValue.setColor(Color.GOLD);
            freePoint = new Label("Wolne punkty:", styleWhite);
            pointToAdd = new Label(" " + preferences.getInteger("FREE_POINT"), styleGreen);
            infoOne = new Label("Bez Eq", styleWhite);
            infoTwo = new Label("Razem z Eq", styleGreen);
            attribute[0] = new Label("Punkty\nżycia", styleWhite);
            attribute[1] = new Label("Pancerz", styleWhite);
            attribute[2] = new Label("Siła", styleWhite);
            attribute[3] = new Label("Wiedza", styleWhite);
            attribute[4] = new Label("Zręczność", styleWhite);
            attribute[5] = new Label("Magia", styleWhite);

            final float scale = 0.5f;
            expirience.setFontScale(scale);
            expLabel.setFontScale(scale);
            moneyValue.setFontScale(scale);
            freePoint.setFontScale(scale);
            pointToAdd.setFontScale(scale);
            infoOne.setFontScale(scale);
            infoTwo.setFontScale(scale);
            attribute[0].setFontScale(scale);
            attribute[1].setFontScale(scale);
            attribute[2].setFontScale(scale);
            attribute[3].setFontScale(scale);
            attribute[4].setFontScale(scale);
            attribute[5].setFontScale(scale);

            for (int i = 0; i < barEmptyStats.length; i++) {
                barEmptyStats[i] = new Image(asset.manager.get("barStats.png", Texture.class));
                barEmptyStats[i].setSize(15, 10);
            }

            buttonAdd[0] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[0].setSize(37, 30);
            buttonAdd[0].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        Menu.getSoundClick().play();
                        hero.setMaxHp(hero.getMaxHp() + 10);
                        hero.setHpEq(hero.getHpEq() + 10);
                        hero.setPoint(hero.getPoint() - 1);
                        pointToAdd.setText("" + hero.getPoint());
                        attributeStats[0].setText("" + hero.getMaxHp());
                        attributeStats[1].setText("" + hero.getHpEq());
                        attributeStats[0].setFontScale(scale);
                        attributeStats[1].setFontScale(scale);
                        preferences.putInteger("FREE_POINT", hero.getPoint());
                        preferences.putInteger("MAX_HP", hero.getMaxHp());
                        preferences.flush();
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
            buttonAdd[1].setSize(37, 30);
            buttonAdd[1].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        Menu.getSoundClick().play();
                        hero.setArmor(hero.getArmor() + 0.1f);
                        hero.setArmorEq(hero.getArmorEq() + 0.1f);
                        hero.setPoint(hero.getPoint() - 1);
                        pointToAdd.setText("" + hero.getPoint());
                        attributeStats[2].setText(String.format("%, .2f", hero.getArmor()) + "%");
                        attributeStats[3].setText(String.format("%, .2f", hero.getArmorEq()) + "%");
                        attributeStats[2].setFontScale(scale);
                        attributeStats[3].setFontScale(scale);
                        preferences.putInteger("FREE_POINT", hero.getPoint());
                        preferences.putFloat("ARMOR", hero.getArmorEq());
                        preferences.flush();
                    }
                    return false;
                }
            });

            buttonAdd[2] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[2].setSize(37, 30);
            buttonAdd[2].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        Menu.getSoundClick().play();
                        hero.setStrong(hero.getStrong() + 1);
                        hero.setStrongEq(hero.getStrongEq() + 1);
                        implementListener(4, hero.getStrong(), hero.getStrongEq(), "STRONG");
                    }
                    return false;
                }
            });

            buttonAdd[3] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[3].setSize(37, 30);
            buttonAdd[3].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    if (hero.getPoint() > 0) {
                        hero.setWiedza(hero.getWiedza() + 1);
                        hero.setWiedzaEq(hero.getWiedzaEq() + 1);
                        implementListener(6, hero.getWiedza(), hero.getWiedzaEq(), "WIEDZA");
                    }
                    return false;
                }
            });

            buttonAdd[4] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[4].setSize(37, 30);
            buttonAdd[4].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    if (hero.getPoint() > 0) {
                        Menu.getSoundClick().play();
                        hero.setDefenseFiz(hero.getDefenseFiz() + 1);
                        hero.setDefenseFizEq(hero.getDefenseFizEq() + 1);
                        implementListener(8, hero.getDefenseFiz(), hero.getDefenseFizEq(), "DEFENSE_FIZ");
                    }
                    return false;
                }
            });

            buttonAdd[5] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("add.png", Texture.class))));
            buttonAdd[5].setSize(37, 30);
            buttonAdd[5].addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
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

            expLabel.setPosition(BaseMap.VIEW_WIDTH / 2 - expLabel.getWidth()*scale / 2, -2);
            expirience.setPosition(BaseMap.VIEW_WIDTH / 2 - expirience.getWidth()*scale / 2, expLabel.getY() + 22);
            uiBarEmptyExp.setPosition(10, 10);
            uiBarEmptyExp.setSize(BaseMap.VIEW_WIDTH - 20, 24);
            uiBarExp.setPosition(uiBarEmptyExp.getX() + 5, uiBarEmptyExp.getY() + 3);

            float procent = (float) hero.getExp() / hero.getMaxExp() * 100;
            if (procent < 3)
                procent = 3;
            uiBarExp.setSize(procent / 100 * uiBarEmptyExp.getWidth() - 9, uiBarEmptyExp.getHeight() - 6);

            barUp.setBounds(0, 345, BaseMap.VIEW_WIDTH + 15, 35);
            money.setBounds(40 + moneyValue.getWidth()*scale +10, barUp.getY() +2, 32, 31);
            money.setColor(Color.GOLD);

            table.row().padTop(40);
            table.add();
            table.add(moneyValue).align(Align.left).padLeft(-35).fill();
            table.add(freePoint).fill();
            table.add(pointToAdd).align(Align.left).fill();
            table.row().padTop(15);
            table.add();
            table.add(infoOne).fill();
            table.add(infoTwo).fill();
            table.row();
            table.add(attribute[0]).align(Align.center);
            table.add(barEmptyStats[0]).fill();
            table.add(barEmptyStats[1]).fill();
            table.add(buttonAdd[0]).fill();
            table.row().padTop(10);
            table.add(attribute[1]).align(Align.center);
            table.add(barEmptyStats[2]).fill();
            table.add(barEmptyStats[3]).fill();
            table.add(buttonAdd[1]).fill();
            table.row().padTop(10);
            table.add(attribute[2]).align(Align.center);
            table.add(barEmptyStats[4]).fill();
            table.add(barEmptyStats[5]).fill();
            table.add(buttonAdd[2]).fill();
            table.row().padTop(10);
            table.add(attribute[3]).align(Align.center);
            table.add(barEmptyStats[6]).fill();
            table.add(barEmptyStats[7]).fill();
            table.add(buttonAdd[3]).fill();
            table.row().padTop(10);
            table.add(attribute[4]).align(Align.center);
            table.add(barEmptyStats[8]).fill();
            table.add(barEmptyStats[9]).fill();
            table.add(buttonAdd[4]).fill();
            table.row().padTop(10);
            table.add(attribute[5]).align(Align.center);
            table.add(barEmptyStats[10]).fill();
            table.add(barEmptyStats[11]).fill();
            table.add(buttonAdd[5]).fill();

            table.setFillParent(true);
            table.pack();

            attributeStats[0] = new Label(String.valueOf(hero.getMaxHp()), styleWhite);
            attributeStats[0].setPosition(123 - attributeStats[0].getWidth()*scale / 2, 263);
            attributeStats[0].setFontScale(scale);

            attributeStats[1] = new Label(String.valueOf(hero.getHpEq()), styleGreen);
            attributeStats[1].setPosition(224 - attributeStats[1].getWidth()*scale / 2, 263);
            attributeStats[1].setFontScale(scale);

            attributeStats[2] = new Label(String.format("%, .2f", hero.getArmor()) + "%", styleWhite);
            attributeStats[2].setPosition(123 - attributeStats[2].getWidth()*scale / 2, 217);
            attributeStats[2].setFontScale(scale);

            attributeStats[3] = new Label(String.format("%, .2f", hero.getArmorEq()) + "%", styleGreen);
            attributeStats[3].setPosition(224 - attributeStats[3].getWidth()*scale / 2, 217);
            attributeStats[3].setFontScale(scale);

            attributeStats[4] = new Label(String.valueOf(hero.getStrong()), styleWhite);
            attributeStats[4].setPosition(123 - attributeStats[4].getWidth()*scale / 2, 177);
            attributeStats[4].setFontScale(scale);

            attributeStats[5] = new Label(String.valueOf(hero.getStrongEq()), styleGreen);
            attributeStats[5].setPosition(224 - attributeStats[5].getWidth()*scale / 2, 177);
            attributeStats[5].setFontScale(scale);

            attributeStats[6] = new Label(String.valueOf(hero.getWiedza()), styleWhite);
            attributeStats[6].setPosition(123 - attributeStats[6].getWidth()*scale / 2, 137);
            attributeStats[6].setFontScale(scale);

            attributeStats[7] = new Label(String.valueOf(hero.getWiedzaEq()), styleGreen);
            attributeStats[7].setPosition(224 - attributeStats[7].getWidth()*scale / 2, 137);
            attributeStats[7].setFontScale(scale);

            attributeStats[8] = new Label(String.valueOf(hero.getDefenseFiz()), styleWhite);
            attributeStats[8].setPosition(123 - attributeStats[6].getWidth()*scale / 2, 97);
            attributeStats[8].setFontScale(scale);

            attributeStats[9] = new Label(String.valueOf(hero.getDefenseFizEq()), styleGreen);
            attributeStats[9].setPosition(224 - attributeStats[7].getWidth()*scale / 2, 97);
            attributeStats[9].setFontScale(scale);

            attributeStats[10] = new Label(String.valueOf(hero.getDefenseMag()), styleWhite);
            attributeStats[10].setPosition(123 - attributeStats[6].getWidth()*scale / 2, 56);
            attributeStats[10].setFontScale(scale);

            attributeStats[11] = new Label(String.valueOf(hero.getDefenseMagEq()), styleGreen);
            attributeStats[11].setPosition(224 -attributeStats[7].getWidth()*scale /2, 56);
            attributeStats[11].setFontScale(scale);

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
