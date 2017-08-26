package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import Screen.BaseMap;
import Screen.BaseScreen;
import Screen.FightScreen;
import Screen.Menu;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Enemy extends Character implements Cloneable{
    private Asset asset = new Asset();
    private Image head;
    private Image wapon;
    private Image flip;
    private String name;
    private int level;
    private int hp;
    private int strong;
    private int wiedza;
    private float armor;
    private int defensePhysics;
    private int defenseMagic;
    private ArrayList<String> dropItemName;
    private float randomDrop;

    private String texturePath;
    private String headPath;
    private String waponPath;

    private int expDrop;
    private int moneyDrop;

    private float defaultScreenZeroX;
    private float defaultScreenZeroY;

    private boolean attackType;
    private int spawnSecond;

    private Sound soundCollision;
    private Sound soundView;

    public Enemy(String texturePath, String headPath, String waponPath, boolean attackType, String name, int level, int hp, int strong, int wiedza, float armor, int defensePhysics, int defenseMagic,
                 float randomDrop, int expDrop ,int moneyDrop, int spawnSecond) {
        super(new Texture(Gdx.files.internal(texturePath)));
        this.texturePath = texturePath;
        this.headPath = headPath;
        this.waponPath = waponPath;
        this.head = new Image(new Texture(Gdx.files.internal(headPath)));
        this.wapon = new Image(new Texture(Gdx.files.internal(waponPath)));
        this.attackType = attackType;
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.strong = strong;
        this.wiedza = wiedza;
        this.armor = armor;
        this.defensePhysics = defensePhysics;
        this.defenseMagic = defenseMagic;
        this.randomDrop = randomDrop;
        this.moneyDrop = moneyDrop;
        this.expDrop = expDrop;
        this.spawnSecond = spawnSecond;
        dropItemName = new ArrayList();

        TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal(texturePath)));
        texture.flip(true, false);
        flip = new Image(texture);
        setSize(getOryginalWidth() *0.35f, getOryginalHeight() *0.35f);
    }

    public void setDropItemName(String ... itemName){
        for(String item: itemName)
            dropItemName.add(item);
    }

    @Override
    public void collisionDo() {
        asset.loadEnemy();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            soundCollision = asset.manager.get("sound/collisionEnemy.ogg", Sound.class);
            soundView = asset.manager.get("sound/card.ogg", Sound.class);
            soundCollision.play(0.3f);
            final Image shadow = new Image(asset.manager.get("shadow.png", Texture.class));
            final ImageButton attackScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonAttack.png", Texture.class))));
            final ImageButton infoEnemy = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonInfo.png", Texture.class))));
            final ImageButton cancel = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonCancel.png", Texture.class))));
            final Image infoBackground = new Image(asset.manager.get("infoEnemy.png", Texture.class));

            defaultScreenZeroX = hero.getX() + hero.getWidth() / 2 - BaseMap.VIEW_WIDTH / 2;
            defaultScreenZeroY = hero.getY() + hero.getHeight() / 2 - BaseMap.VIEW_HEIGHT / 2;
            hero.setActiveMove(true);

            shadow.setPosition(defaultScreenZeroX, defaultScreenZeroY);
            attackScreen.setPosition(defaultScreenZeroX + 21, defaultScreenZeroY + 90);
            infoEnemy.setPosition(defaultScreenZeroX + 21 + attackScreen.getWidth() + 22, defaultScreenZeroY + 90);
            cancel.setPosition(defaultScreenZeroX + BaseScreen.VIEW_WIDTH / 2 - cancel.getWidth() / 2, defaultScreenZeroY + 280);

            final Enemy enemy = this;

            attackScreen.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
                    preferences.putInteger("POS_X", (int) hero.getX()).flush();
                    preferences.putInteger("POS_Y", (int) hero.getY()).flush();
                    shadow.remove();
                    attackScreen.remove();
                    infoEnemy.remove();
                    cancel.remove();
                    hero.setActiveMove(false);
                    hero.getGame().setScreen(new FightScreen(hero.getGame(), hero, enemy, true));
                    return false;
                }
            });

            infoEnemy.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    soundView.play();
                    shadow.remove();
                    attackScreen.remove();
                    infoEnemy.remove();
                    cancel.remove();
                    infoBackground.setPosition(defaultScreenZeroX -2, defaultScreenZeroY -2);
                    infoBackground.setSize(BaseMap.VIEW_WIDTH +4, BaseMap.VIEW_HEIGHT - 46);
                    hero.getHero3D().setRenderHero3d(false);

                    BitmapFont font = MyGdxGame.createDistanceFont();
                    Label.LabelStyle style = new Label.LabelStyle();
                    style.font = font;

                    final Label lName = new Label(getName().toUpperCase(), style);
                    final Label lLevel = new Label("Poziom " + level, style);
                    final Label lHp = new Label("Punkty Życia " + hp, style);
                    final Label lArmor = new Label("Pancerz " + armor + "%", style);
                    final Label lStrong = new Label("Siła " + strong, style);
                    final Label lWiedza = new Label("Wiedza " + wiedza, style);
                    final Label lDefensePhysics = new Label("ObronaFizyczna " + getDefensePhysics(), style);
                    final Label lDefenseMagic = new Label("ObronaMagiczna " + getDefenseMagic(), style);
                    final Label lRandomDrop = new Label("Szansa na zdobycie przedmiotu " + getRandomDrop() + "%", style);
                    final Image imageEnemy = new Image(getTexture());
                    Vector2 sizeEnemyInfo = scale(getTexture());
                    imageEnemy.setSize(sizeEnemyInfo.x, sizeEnemyInfo.y);

                    lName.setFontScale(0.5f);
                    lLevel.setFontScale(0.5f);
                    lHp.setFontScale(0.5f);
                    lArmor.setFontScale(0.5f);
                    lStrong.setFontScale(0.5f);
                    lWiedza.setFontScale(0.5f);
                    lDefensePhysics.setFontScale(0.45f);
                    lDefenseMagic.setFontScale(0.45f);
                    lRandomDrop.setFontScale(0.4f);

                    imageEnemy.setTouchable(Touchable.disabled);
                    lName.setTouchable(Touchable.disabled);
                    lHp.setTouchable(Touchable.disabled);
                    lLevel.setTouchable(Touchable.disabled);
                    lArmor.setTouchable(Touchable.disabled);
                    lStrong.setTouchable(Touchable.disabled);
                    lWiedza.setTouchable(Touchable.disabled);
                    lDefensePhysics.setTouchable(Touchable.disabled);
                    lDefenseMagic.setTouchable(Touchable.disabled);
                    lRandomDrop.setTouchable(Touchable.disabled);

                    if(lName.getWidth()*0.5f +lLevel.getWidth()*0.5f +35 > 200) {
                        lName.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 -(lName.getWidth()*0.5f +lLevel.getWidth()*0.5f +5) /2, defaultScreenZeroY + 330);
                        lLevel.setPosition(lName.getX() + lName.getWidth() * 0.5f + 5, defaultScreenZeroY + 330);
                    }
                    else {
                        lName.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 - (lName.getWidth() * 0.5f + lLevel.getWidth() * 0.5f + 35) / 2, defaultScreenZeroY + 330);
                        lLevel.setPosition(lName.getX() + lName.getWidth() * 0.5f + 35, defaultScreenZeroY + 330);
                    }
                    lHp.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 110);
                    lArmor.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 + 10, defaultScreenZeroY + 110);
                    lStrong.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 80);
                    lWiedza.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 + 10, defaultScreenZeroY + 80);
                    lDefensePhysics.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 50);
                    lDefenseMagic.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 + 10, defaultScreenZeroY + 50);
                    lRandomDrop.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH / 2 - lRandomDrop.getWidth()*0.4f / 2, defaultScreenZeroY + 20);
                    imageEnemy.setPosition(defaultScreenZeroX + (BaseMap.VIEW_WIDTH / 2 - imageEnemy.getWidth() / 2), defaultScreenZeroY + (BaseMap.VIEW_HEIGHT / 2 - imageEnemy.getHeight() / 2));

                    addActor(infoBackground, lName, lLevel, imageEnemy, lHp, lArmor, lStrong, lWiedza, lDefensePhysics, lDefenseMagic, lRandomDrop);

                    infoBackground.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            lName.remove();
                            lLevel.remove();
                            lHp.remove();
                            lArmor.remove();
                            lStrong.remove();
                            lWiedza.remove();
                            lDefensePhysics.remove();
                            lDefenseMagic.remove();
                            lRandomDrop.remove();
                            imageEnemy.remove();
                            infoBackground.remove();
                            hero.getHero3D().setRenderHero3d(true);
                            hero.setActiveMove(false);
                            return false;
                        }
                    });
                    return false;
                }
            });

            cancel.addListener(new InputListener() {
                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                    shadow.remove();
                    attackScreen.remove();
                    infoEnemy.remove();
                    cancel.remove();
                    Menu.getSoundClick().play();
                    hero.setActiveMove(false);
                    return false;
                }
            });

            addActor(shadow, attackScreen, infoEnemy, cancel);
            attackScreen.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() / 2 - cancel.getWidth() / 2 + 10, getY() + getHeight() / 2 - cancel.getHeight() / 2), Actions.parallel(Actions.moveTo(defaultScreenZeroX + 21, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
            infoEnemy.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() / 2 - cancel.getWidth() / 2 + 10, getY() + getHeight() / 2 - cancel.getHeight() / 2), Actions.parallel(Actions.moveTo(defaultScreenZeroX + 21 + attackScreen.getWidth() + 22, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
            cancel.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() / 2 - cancel.getWidth() / 2 + 10, getY() + getHeight() / 2 - cancel.getHeight() / 2), Actions.parallel(Actions.moveTo(defaultScreenZeroX + BaseScreen.VIEW_WIDTH / 2 - cancel.getWidth() / 2, defaultScreenZeroY + 280, 0.3f), Actions.fadeIn(0.6f))));
        }
    }

    private Vector2 scale(TextureRegion texture) {
        float x = texture.getRegionWidth();
        float y = texture.getRegionHeight();

        float result = 290 / x;
        float resultTwo = 200 /y;

        if(result < 1 || resultTwo < 1) {
            if (result <= resultTwo)
                return new Vector2(x * result, y * result);
            else
                return new Vector2(x * resultTwo, y * resultTwo);
        }
        return new Vector2(x, y);
    }

    private void addActor(Actor... actors){
        for(Actor actor: actors)
            hero.getStage().addActor(actor);
    }

    /**
     * Setter and Getter
     */

    public String getName(){
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp(){
        return hp;
    }

    public int getStrong() {
        return strong;
    }

    public int getWiedza() {
        return wiedza;
    }

    public float getArmor() {
        return armor;
    }

    public int getDefensePhysics() {
        return defensePhysics;
    }

    public int getDefenseMagic() {
        return defenseMagic;
    }

    public float getRandomDrop(){
        return randomDrop;
    }

    public int getExpDrop(){
        return expDrop;
    }

    public int getMoneyDrop(){
        return moneyDrop;
    }

    public boolean getAttackType(){
        return attackType;
    }

    public Image getHead() {
        return head;
    }

    public Image getWapon(){
        return wapon;
    }

    public ArrayList<String> getDropItem(){
        return dropItemName;
    }

    public int getSpawnSecond(){
        return spawnSecond;
    }

    public Image getFlip(){
        return flip;
    }

    public String getTexturePath(){
        return texturePath;
    }

    public String getHeadPath(){
        return headPath;
    }

    public String getWaponPath(){
        return waponPath;
    }
}
