package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import Screen.BaseMap;
import Screen.BaseScreen;
import Screen.FightScreen;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Enemy extends Character {
    private final static Image shadow = new Image(new Texture(Gdx.files.internal("shadow.png")));
    private final static ImageButton  attackScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAttack.png")))));
    private final static ImageButton infoEnemy = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonInfo.png")))));
    private final static ImageButton cancel = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));
    private final static Image infoBackground = new Image(new Texture(Gdx.files.internal("infoEnemy.png")));

    private Game game;

    private static boolean activeMoveScreen;
    private Stage stage;

    private float defaultScreenZeroX;
    private float defaultScreenZeroY;

    private Image head;
    private Image wapon;
    private String name;
    private int level;
    private int hp;
    private int strong;
    private int wiedza;
    private int armor;
    private int defensePhysics;
    private int defenseMagic;
    private ArrayList<String> dropItemName;
    private int randomDrop;

    private Vector2 expToKill;
    private Vector2 moneyToKill;

    public Enemy(Texture texture, Stage stage, Image head, Image wapon, String name, int level, int hp, int strong, int wiedza, int armor, int defensePhysics, int defenseMagic,
                 int randomDrop, int expToKillMin, int expToKillMax, int moneyToKillMin, int moneyToKillMax, Game game) {
        super(texture);
        this.head = head;
        this.wapon = wapon;
        this.name = name;
        this.stage = stage;
        this.level = level;
        this.hp = hp;
        this.strong = strong;
        this.wiedza = wiedza;
        this.armor = armor;
        this.defensePhysics = defensePhysics;
        this.defenseMagic = defenseMagic;
        this.randomDrop = randomDrop;
        this.expToKill = new Vector2(expToKillMin, expToKillMax);
        this.moneyToKill = new Vector2(moneyToKillMin, moneyToKillMax);
        dropItemName = new ArrayList();
        this.game = game;
    }

    public void setDropItemName(String ... itemName){
        for(String item: itemName)
            dropItemName.add(item);
    }

    public void collisionDo(final Hero hero){
        defaultScreenZeroX = hero.getX() + hero.getWidth() /2 - BaseMap.VIEW_WIDTH /2;
        defaultScreenZeroY = hero.getY() + hero.getHeight() /2 - BaseMap.VIEW_HEIGHT /2;
        setActiveMove(true);

        shadow.setPosition(defaultScreenZeroX, defaultScreenZeroY);
        attackScreen.setPosition(hero.getX() + hero.getWidth() /2 - attackScreen.getWidth() /2, hero.getY() + hero.getHeight() /2 - attackScreen.getHeight() /2);
        infoEnemy.setPosition(hero.getX() + hero.getWidth() /2 - infoEnemy.getWidth() /2, defaultScreenZeroY + infoEnemy.getHeight());
        cancel.setPosition(hero.getX() + hero.getWidth() /2 - cancel.getWidth() /2 +10, hero.getY() + hero.getHeight() /2 - cancel.getHeight() /2);

        final Enemy enemy = this;

        attackScreen.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                removeAll();
                setActiveMove(false);
                game.setScreen(new FightScreen(game, hero, enemy, true));
                return false;
            }
        });

        infoEnemy.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                removeAll();

                infoBackground.setPosition(hero.getX() + hero.getWidth() /2 - BaseMap.VIEW_WIDTH /2, hero.getY() + hero.getHeight() /2 - BaseMap.VIEW_HEIGHT /2);
                infoBackground.setSize(BaseMap.VIEW_WIDTH, BaseMap.VIEW_HEIGHT - 50);
                hero.getHero3D().setRenderHero3d(false);

                BitmapFont font = new BitmapFont();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = font;

                final Label lName = new Label(name.toUpperCase(), style);
                final Label lLevel = new Label("Level " + level, style);
                final Label lHp = new Label("Hp: " + hp, style);
                final Label lArmor = new Label("Armor: " + armor + "%", style);
                final Label lStrong = new Label("Strong: " + strong, style);
                final Label lWiedza = new Label("Wiedza: " + wiedza, style);
                final Label lDefensePhysics = new Label("Defense Physics: " + defensePhysics, style);
                final Label lDefenseMagic = new Label("Defense Magic: " + defenseMagic, style);
                final Label lRandomDrop = new Label("Chance to drop: " + randomDrop + "%", style);
                final Image imageEnemy = new Image(texture);

                lName.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 - lName.getWidth() -20, defaultScreenZeroY + 339);
                lLevel.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 + lName.getWidth() /2, defaultScreenZeroY + 339);
                lHp.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 120);
                lArmor.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 +10, defaultScreenZeroY + 120);
                lStrong.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 80);
                lWiedza.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 +10, defaultScreenZeroY + 80);
                lDefensePhysics.setPosition(defaultScreenZeroX + 25, defaultScreenZeroY + 50);
                lDefenseMagic.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 + 10, defaultScreenZeroY + 50);
                lRandomDrop.setPosition(defaultScreenZeroX + BaseMap.VIEW_WIDTH /2 - lRandomDrop.getWidth() /2, defaultScreenZeroY + 20);
                imageEnemy.setPosition(defaultScreenZeroX + (BaseMap.VIEW_WIDTH /2 - imageEnemy.getWidth() /2), defaultScreenZeroY + (BaseMap.VIEW_HEIGHT /2 - imageEnemy.getHeight() /2));

                infoBackground.addListener(new InputListener(){
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
                        setActiveMove(false);
                        return false;
                    }
                });

                addActor(infoBackground, lName, lLevel, imageEnemy, lHp, lArmor, lStrong, lWiedza, lDefensePhysics, lDefenseMagic, lRandomDrop);

                return false;
            }
        });

        cancel.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                removeAll();
                setActiveMove(false);
                return false;
            }
        });

        addActor(shadow, attackScreen, infoEnemy, cancel);
        attackScreen.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.moveTo(defaultScreenZeroX +21, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
        infoEnemy.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.moveTo(defaultScreenZeroX + 21 + attackScreen.getWidth() + 22, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
        cancel.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.moveTo(defaultScreenZeroX + BaseScreen.VIEW_WIDTH /2 - cancel.getWidth() /2, defaultScreenZeroY + 280, 0.3f), Actions.fadeIn(0.6f))));
    }

    private void addActor(Actor... actors){
        for(Actor actor: actors)
            stage.addActor(actor);
    }

    private void removeAll(){
        shadow.remove();
        attackScreen.remove();
        infoEnemy.remove();
        cancel.remove();
    }

    /**
     * Setter and Getter
     */
    public static boolean getActiveMove(){
        return activeMoveScreen;
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

    public int getArmor() {
        return armor;
    }

    public int getDefensePhysics() {
        return defensePhysics;
    }

    public int getDefenseMagic() {
        return defenseMagic;
    }

    public Image getHead() {
        return head;
    }

    private static void setActiveMove(boolean block){
        activeMoveScreen = block;
    }

    public Image getWapon(){
        return wapon;
    }
}
