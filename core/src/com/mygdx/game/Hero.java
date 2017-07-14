package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Hero extends Character {
    public final static int SPEED_MOVE = 25;
    public static final Texture ARM = new Texture(Gdx.files.internal("heroArm.png"));

    private Game game;
    private Camera camera;
    private Hero3D hero3D;
    private Stage stage;

    private ArrayList<Polygon> objectMap;
    private ArrayList<Vector2[]> vertical;
    private ArrayList<Vector2> queueWay = null;
    private ArrayList<Enemy> enemy;
    private Enemy actualEnemy;

    private Polygon actualCollision;
    private Polygon actualPointObject;
    private Polygon heroPolygon;
    private Polygon finishWalkPosition;
    private Polygon track;

    private Rectangle rectangle;

    private Vector2 start;
    private Vector2 end;

    private boolean moveStop;
    private boolean aroundMove;
    private boolean animationPlay;
    private boolean changeTrack;
    private boolean npcCollision;
    private static boolean activeMove;

    private int actualIndexNpc;

    private int level;
    private int money;
    private int maxHp;
    private int exp;
    private int maxExp;

    private int fullHp;
    private int hp;
    private int strong;
    private int wiedza;
    private int armor;
    private int defenseFiz;
    private int defenseMag;

    private int hpNonEq;
    private int hpEq;
    private int strongEq;
    private int wiedzaEq;
    private int armorEq;
    private int defenseFizEq;
    private int defenseMagEq;

    private int point;

    private float defaultScreenZeroX;
    private float defaultScreenZeroY;

    public Hero(Texture texture, ArrayList<Polygon> objectMap, ArrayList<Vector2[]> vertical, Camera camera, Hero3D hero3D, ArrayList<Enemy> enemy, Stage stage, Game game) {
        super(texture);
        this.objectMap = objectMap;
        this.vertical = vertical;
        this.camera = camera;
        this.rectangle = new Rectangle();
        this.enemy = enemy;
        this.hero3D = hero3D;
        hero3D.setRenderHero3d(true);
        start = new Vector2();
        end = new Vector2();
        this.stage = stage;
        this.game = game;
        create();
    }

    private void create(){
        Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        setLevel(26);

        setMaxHp(preferences.getInteger("MAX_HP"));
        setHpNonEq(getMaxHp());
        setHp(getHpNonEq());
        strong = preferences.getInteger("STRONG");
        wiedza = preferences.getInteger("WIEDZA");
        armor = preferences.getInteger("SPEED_ATTACK");
        defenseFiz = preferences.getInteger("DEFENSE_FIZ");
        defenseMag = preferences.getInteger("DEFENSE_MAG");

        setExp(preferences.getInteger("EXP"));
        setMaxExp(preferences.getInteger("MAX_EXP"));
        setMoney(preferences.getInteger("MONEY"));
        point = preferences.getInteger("FREE_POINT");

        try {
            new UpdateHeroStats(this);
        } catch (CloneNotSupportedException e) {}
    }

    public void move(final float posX, final float posY) {
        clearActions();
        start.set((int) getX() + getWidth() / 2, (int) getY() + getHeight() / 2);
        end.set((int) posX + getWidth() / 2, (int) posY + getHeight() / 2);
        track = new Polygon(new float[]{start.x - 1, start.y, end.x - 1,
                end.y, end.x + 1, end.y, start.x + 1, start.y});
        for(int i = 0; i < enemy.size(); i++) {
            actualIndexNpc = i;
            actualEnemy = enemy.get(i);
            enemy.get(i).collisionUpdate();
            heroPolygonUpdate();
            if (Intersector.overlapConvexPolygons(heroPolygon, actualEnemy.convertRectangleToPolygon()))
                setNpcCollision(true);
            if(Intersector.overlapConvexPolygons(actualEnemy.convertRectangleToPolygon(), track)){
                if(isNpcCollision() && Intersector.overlapConvexPolygons(heroPolygon, actualEnemy.convertRectangleToPolygon())){
                    setNpcCollision(true);
                    return;
                }else {
                    setNpcCollision(true);
                    break;
                }
            }else {
                setNpcCollision(false);
            }
        }

        Polygon point = new Polygon(new float[]{end.x - 1, end.y - 1, end.x - 1, end.y + 1, end.x + 1, end.y + 1, end.x + 1, end.y - 1});
        setFinishWalkPosition(point);

        rectangle = track.getBoundingRectangle();

        heroPolygonUpdate();
        int countCollision = 0;
        boolean pointCollision = false;
        for(Polygon object : objectMap){
            if(Intersector.overlapConvexPolygons(heroPolygon, object) && Intersector.overlapConvexPolygons(point, object))
                return;
            if(Intersector.overlapConvexPolygons(track, object))
                countCollision++;
            if(Intersector.overlapConvexPolygons(object, point)){
                pointCollision = true;
            }
        }

        if(countCollision >= 2 && !pointCollision)
            return;

        //if point END at collision object
        int i = -1;
        int numberObject = 0;
        int allObject = objectMap.size();
        for (Polygon object : objectMap) {
            i++;
            Vector2[] verticalObject = vertical.get(i);
            if (!moveStop && countCollision < 2) {
                setActualCollision(object);
                if (Intersector.overlapConvexPolygons(point, object)) {
                    setAnimationPlayTrue();
                    addAction(Actions.sequence(
                            calculateRotate(posX, posY),
                            Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                    setMoveStop(true);
                    break;
                }
            }

            //if this moment collision one object and click second object
            if (moveStop) {
                if(countCollision == 1) {
                    for (Polygon object1 : objectMap) {
                        if (Intersector.overlapConvexPolygons(point, object1)) {
                            setActualCollision(object1);
                            setAnimationPlayTrue();
                            addAction(Actions.sequence(
                                    calculateRotate(posX, posY),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                        }
                    }
                }
            }

            //if track don't collision with other object
            if (!Intersector.overlapConvexPolygons(track, object)) {
                numberObject++;
                if (numberObject == allObject) {
                    setChangeTrack(false);
                    setAnimationPlayTrue();
                    calculateRotate(posX, posY);
                    addAction(Actions.sequence(
                            calculateRotate(posX, posY),
                            Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                    setMoveStop(false);
                    break;
                }
            }

            //if track at collision object
            if (Intersector.overlapConvexPolygons(track, object) && !Intersector.overlapConvexPolygons(point, object)) {
                actualPointObject = null;
                setActualCollision(object);
                queueWay = new ArrayList<Vector2>();
                queueWay.add(null);

                heroPolygonUpdate();
                for(Polygon objectPoint: objectMap){
                    if(object != objectPoint) {
                        if (Intersector.overlapConvexPolygons(point, objectPoint))
                            setActualPointObject(objectPoint);
                    }

                    if(Intersector.overlapConvexPolygons(heroPolygon, object))
                        setActualCollision(object);
                }

                float shorterWay = 999;
                int indexShorterWay = -1;
                int amountVertical = verticalObject.length;
                Vector2 vertical = null;

                for (int j = 0; j < amountVertical; j++) {
                    Polygon lineEndVertical = new Polygon(new float[]{end.x - 1, end.y, end.x + 1, end.y,
                            verticalObject[j].x + 1, verticalObject[j].y, verticalObject[j].x - 1, verticalObject[j].y});
                    if (!Intersector.overlapConvexPolygons(object, lineEndVertical)) {
                        float duration = timeSpeed(end.x, end.y, verticalObject[j].x, verticalObject[j].y);
                        if (duration < shorterWay) {
                            shorterWay = duration;
                            indexShorterWay = j;
                            queueWay.set(0, new Vector2(verticalObject[j].x, verticalObject[j].y));
                            vertical = new Vector2(verticalObject[j].x, verticalObject[j].y);
                        }
                    }
                }
                if (vertical == null) {
                    System.out.println("ERROR NULL VERTICAL");
                    break;
                }
                Polygon lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                        vertical.x + 1, vertical.y, vertical.x - 1, vertical.y});

                int index = indexShorterWay;

                float durationUpIncrement = 0;
                while (Intersector.overlapConvexPolygons(lineStartVertical, object)) {
                    if (index + 1 < amountVertical)
                        index++;
                    else
                        index = 0;

                    if (index == 0)
                        durationUpIncrement += timeSpeed(verticalObject[amountVertical - 1].x, verticalObject[amountVertical - 1].y, verticalObject[index].x, verticalObject[index].y);
                    else
                        durationUpIncrement += timeSpeed(verticalObject[index - 1].x, verticalObject[index - 1].y, verticalObject[index].x, verticalObject[index].y);

                    lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                            verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y});

                    if (!Intersector.overlapConvexPolygons(lineStartVertical, object))
                        durationUpIncrement += timeSpeed(start.x, start.y, verticalObject[index].x, verticalObject[index].y);
                }
                index = indexShorterWay;
                if (index - 1 < 0)
                    index = amountVertical - 1;
                else
                    index--;

                if (!Intersector.overlapConvexPolygons(object, new Polygon(new float[]{end.x - 1, end.y, end.x + 1, end.y,
                        verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y})))
                    durationUpIncrement += timeSpeed(end.x, end.y, verticalObject[indexShorterWay].x, verticalObject[indexShorterWay].y);
                else
                    durationUpIncrement -= timeSpeed(end.x, end.y, verticalObject[indexShorterWay].x, verticalObject[indexShorterWay].y);

                lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                        vertical.x + 1, vertical.y, vertical.x - 1, vertical.y});

                index = indexShorterWay;

                float durationDownIncrement = 0;
                while (Intersector.overlapConvexPolygons(lineStartVertical, object)) {
                    if (index - 1 < 0)
                        index = amountVertical - 1;
                    else
                        index--;

                    if (index == amountVertical - 1)
                        durationDownIncrement += timeSpeed(verticalObject[0].x, verticalObject[0].y, verticalObject[index].x, verticalObject[index].y);
                    else
                        durationDownIncrement += timeSpeed(verticalObject[index + 1].x, verticalObject[index + 1].y, verticalObject[index].x, verticalObject[index].y);

                    lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                            verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y});

                    if (!Intersector.overlapConvexPolygons(lineStartVertical, object))
                        durationDownIncrement += timeSpeed(start.x, start.y, verticalObject[index].x, verticalObject[index].y);
                }
                index = indexShorterWay;
                if (index + 1 > amountVertical - 1)
                    index = 0;
                else
                    index++;

                if (!Intersector.overlapConvexPolygons(object, new Polygon(new float[]{end.x - 1, end.y, end.x + 1, end.y,
                        verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y})))
                    durationDownIncrement += timeSpeed(end.x, end.y, verticalObject[indexShorterWay].x, verticalObject[indexShorterWay].y);
                else
                    durationDownIncrement -= timeSpeed(end.x, end.y, verticalObject[indexShorterWay].x, verticalObject[indexShorterWay].y);

                lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                        vertical.x + 1, vertical.y, vertical.x - 1, vertical.y});
                index = indexShorterWay;
                while (Intersector.overlapConvexPolygons(lineStartVertical, object)) {
                    if (durationUpIncrement <= durationDownIncrement) {
                        if (index + 1 < amountVertical)
                            index++;
                        else
                            index = 0;
                        queueWay.add(new Vector2(verticalObject[index].x, verticalObject[index].y));
                        lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                                verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y});
                    } else {
                        if (index - 1 < 0)
                            index = amountVertical - 1;
                        else
                            index--;
                        queueWay.add(new Vector2(verticalObject[index].x, verticalObject[index].y));
                        lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                                verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y});
                    }
                }
                if (queueWay != null) {
                    clearActions();
                    setAroundMove(true);
                    setAnimationPlayTrue();
                    switch (queueWay.size()) {
                        case 1:
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(posX, posY, timeSpeed(calculateAction(1, true), calculateAction(1, false), posX, posY))));
                            break;
                        case 2:
                            setChangeTrack(true);
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(calculateAction(2, true), calculateAction(2, false)),
                                    Actions.moveTo(calculateAction(2, true), calculateAction(2, false), timeSpeed(calculateAction(1, true), calculateAction(1, false), calculateAction(2, true), calculateAction(2, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                        case 3:
                            setChangeTrack(true);
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(calculateAction(2, true), calculateAction(2, false)),
                                    Actions.moveTo(calculateAction(2, true), calculateAction(2, false), timeSpeed(calculateAction(1, true), calculateAction(1, false), calculateAction(2, true), calculateAction(2, false))),
                                    calculateRotate(calculateAction(3, true), calculateAction(3, false)),
                                    Actions.moveTo(calculateAction(3, true), calculateAction(3, false), timeSpeed(calculateAction(2, true), calculateAction(2, false), calculateAction(3, true), calculateAction(3, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                        case 4:
                            setChangeTrack(true);
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(calculateAction(2, true), calculateAction(2, false)),
                                    Actions.moveTo(calculateAction(2, true), calculateAction(2, false), timeSpeed(calculateAction(1, true), calculateAction(1, false), calculateAction(2, true), calculateAction(2, false))),
                                    calculateRotate(calculateAction(3, true), calculateAction(3, false)),
                                    Actions.moveTo(calculateAction(3, true), calculateAction(3, false), timeSpeed(calculateAction(2, true), calculateAction(2, false), calculateAction(3, true), calculateAction(3, false))),
                                    calculateRotate(calculateAction(4, true), calculateAction(4, false)),
                                    Actions.moveTo(calculateAction(4, true), calculateAction(4, false), timeSpeed(calculateAction(3, true), calculateAction(3, false), calculateAction(4, true), calculateAction(4, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                        case 5:
                            setChangeTrack(true);
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(calculateAction(2, true), calculateAction(2, false)),
                                    Actions.moveTo(calculateAction(2, true), calculateAction(2, false), timeSpeed(calculateAction(1, true), calculateAction(1, false), calculateAction(2, true), calculateAction(2, false))),
                                    calculateRotate(calculateAction(3, true), calculateAction(3, false)),
                                    Actions.moveTo(calculateAction(3, true), calculateAction(3, false), timeSpeed(calculateAction(2, true), calculateAction(2, false), calculateAction(3, true), calculateAction(3, false))),
                                    calculateRotate(calculateAction(4, true), calculateAction(4, false)),
                                    Actions.moveTo(calculateAction(4, true), calculateAction(4, false), timeSpeed(calculateAction(3, true), calculateAction(3, false), calculateAction(4, true), calculateAction(4, false))),
                                    calculateRotate(calculateAction(5, true), calculateAction(5, false)),
                                    Actions.moveTo(calculateAction(5, true), calculateAction(5, false), timeSpeed(calculateAction(4, true), calculateAction(4, false), calculateAction(5, true), calculateAction(5, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                        default:
                            setChangeTrack(true);
                            this.addAction(Actions.sequence(
                                    calculateRotate(calculateAction(1, true), calculateAction(1, false)),
                                    Actions.moveTo(calculateAction(1, true), calculateAction(1, false), timeSpeed(this.getX(), this.getY(), calculateAction(1, true), calculateAction(1, false))),
                                    calculateRotate(calculateAction(2, true), calculateAction(2, false)),
                                    Actions.moveTo(calculateAction(2, true), calculateAction(2, false), timeSpeed(calculateAction(1, true), calculateAction(1, false), calculateAction(2, true), calculateAction(2, false))),
                                    calculateRotate(calculateAction(3, true), calculateAction(3, false)),
                                    Actions.moveTo(calculateAction(3, true), calculateAction(3, false), timeSpeed(calculateAction(2, true), calculateAction(2, false), calculateAction(3, true), calculateAction(3, false))),
                                    calculateRotate(calculateAction(4, true), calculateAction(4, false)),
                                    Actions.moveTo(calculateAction(4, true), calculateAction(4, false), timeSpeed(calculateAction(3, true), calculateAction(3, false), calculateAction(4, true), calculateAction(4, false))),
                                    calculateRotate(calculateAction(5, true), calculateAction(5, false)),
                                    Actions.moveTo(calculateAction(5, true), calculateAction(5, false), timeSpeed(calculateAction(4, true), calculateAction(4, false), calculateAction(5, true), calculateAction(5, false))),
                                    calculateRotate(calculateAction(6, true), calculateAction(6, false)),
                                    Actions.moveTo(calculateAction(6, true), calculateAction(6, false), timeSpeed(calculateAction(5, true), calculateAction(5, false), calculateAction(6, true), calculateAction(6, false))),
                                    calculateRotate(end.x - getWidth() / 2, end.y - getHeight() / 2),
                                    Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                            break;
                    }
                }
                setMoveStop(false);
                break;
            }
        }
    }

    public void collisionDo(final Enemy enemy){
        final Image shadow = new Image(new Texture(Gdx.files.internal("shadow.png")));
        final ImageButton attackScreen = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAttack.png")))));
        final ImageButton infoEnemy = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonInfo.png")))));
        final ImageButton cancel = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));
        final Image infoBackground = new Image(new Texture(Gdx.files.internal("infoEnemy.png")));

        defaultScreenZeroX = getX() + getWidth() /2 - BaseMap.VIEW_WIDTH /2;
        defaultScreenZeroY = getY() + getHeight() /2 - BaseMap.VIEW_HEIGHT /2;
        setActiveMove(true);

        shadow.setPosition(defaultScreenZeroX, defaultScreenZeroY);
        attackScreen.setPosition(defaultScreenZeroX +21, defaultScreenZeroY + 90);
        infoEnemy.setPosition(defaultScreenZeroX + 21 + attackScreen.getWidth() + 22, defaultScreenZeroY + 90);
        cancel.setPosition(defaultScreenZeroX + BaseScreen.VIEW_WIDTH /2 - cancel.getWidth() /2, defaultScreenZeroY + 280);

        final Hero hero = this;

        attackScreen.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                shadow.remove();
                attackScreen.remove();
                infoEnemy.remove();
                cancel.remove();
                setActiveMove(false);
                game.setScreen(new FightScreen(game, hero, actualEnemy, true));
                return false;
            }
        });

        infoEnemy.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                //TODO click everywhere and close window also when click label or image first plan
                shadow.remove();
                attackScreen.remove();
                infoEnemy.remove();
                cancel.remove();
                infoBackground.setPosition(getX() + getWidth() /2 - BaseMap.VIEW_WIDTH /2, getY() + getHeight() /2 - BaseMap.VIEW_HEIGHT /2);
                infoBackground.setSize(BaseMap.VIEW_WIDTH, BaseMap.VIEW_HEIGHT - 50);
                getHero3D().setRenderHero3d(false);

                BitmapFont font = new BitmapFont();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = font;

                final Label lName = new Label(enemy.getName().toUpperCase(), style);
                final Label lLevel = new Label("Level " + level, style);
                final Label lHp = new Label("Hp: " + hp, style);
                final Label lArmor = new Label("Armor: " + armor + "%", style);
                final Label lStrong = new Label("Strong: " + strong, style);
                final Label lWiedza = new Label("Wiedza: " + wiedza, style);
                final Label lDefensePhysics = new Label("Defense Physics: " + enemy.getDefensePhysics(), style);
                final Label lDefenseMagic = new Label("Defense Magic: " + enemy.getDefenseMagic(), style);
                final Label lRandomDrop = new Label("Chance to drop: " + enemy.getRandomDrop() + "%", style);
                final Image imageEnemy = new Image(enemy.getTexture());
                Vector2 sizeEnemyInfo = scaleUp(enemy.getTexture());
                imageEnemy.setSize(sizeEnemyInfo.x, sizeEnemyInfo.y);

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

                addActor(infoBackground, lName, lLevel, imageEnemy, lHp, lArmor, lStrong, lWiedza, lDefensePhysics, lDefenseMagic, lRandomDrop);

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
                        getHero3D().setRenderHero3d(true);
                        setActiveMove(false);
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
                setActiveMove(false);
                return false;
            }
        });

        addActor(shadow, attackScreen, infoEnemy, cancel);
        attackScreen.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() /2 - cancel.getWidth() /2 +10, getY() + getHeight() /2 - cancel.getHeight() /2), Actions.parallel(Actions.moveTo(defaultScreenZeroX +21, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
        infoEnemy.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() /2 - cancel.getWidth() /2 +10, getY() + getHeight() /2 - cancel.getHeight() /2), Actions.parallel(Actions.moveTo(defaultScreenZeroX + 21 + attackScreen.getWidth() + 22, defaultScreenZeroY + 90, 0.3f), Actions.fadeIn(0.6f))));
        cancel.addAction(Actions.sequence(Actions.fadeOut(0), Actions.moveTo(getX() + getWidth() /2 - cancel.getWidth() /2 +10, getY() + getHeight() /2 - cancel.getHeight() /2), Actions.parallel(Actions.moveTo(defaultScreenZeroX + BaseScreen.VIEW_WIDTH /2 - cancel.getWidth() /2, defaultScreenZeroY + 280, 0.3f), Actions.fadeIn(0.6f))));
    }

    private Vector2 scaleUp(TextureRegion texture) {
        float x = texture.getRegionWidth();
        float y = texture.getRegionHeight();

        do{
           x++;
            y++;
        }while(x <= 300 && y <= 200);

        return new Vector2(x, y);
    }

    private void addActor(Actor... actors){
        for(Actor actor: actors)
            stage.addActor(actor);
    }

    private float calculateAction(int index, boolean xOrY){
        if(xOrY)
            return queueWay.get(queueWay.size() - index).x - this.getWidth() /2;
        else
            return queueWay.get(queueWay.size() - index).y - this.getHeight() /2;
    }

    private Action calculateRotate(final float x, final float y){
        return Actions.run(new Runnable() {
            @Override
            public void run() {
                hero3D.moveRotate(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x,
                        -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y);
            }
        });
    }

    public void objectCollision(){
        heroPolygonUpdate();

        if(Intersector.overlapConvexPolygons(heroPolygon, getActualCollision())){
            hero3D.setStopAnimation();
            this.clearActions();
        }
    }

    public void objectPointCollision(){
        heroPolygonUpdate();
        if(actualPointObject != null){
            if(Intersector.overlapConvexPolygons(heroPolygon, getActualPointObject()))
                clearActions();
        }
    }

    public void collisionEnemy() {
        if(Intersector.overlapConvexPolygons(heroPolygon, enemy.get(getActualIndexNpc()).convertRectangleToPolygon())){
            clearActions();
            collisionDo(actualEnemy);
            setNpcCollision(false);
            hero3D.setStopAnimation();
        }
    }

    public void changeTrack(){
        Polygon lineHeroEnd = new Polygon(new float[]{getX() + getWidth() /2 -1, getY() + getHeight() /2,
                getX() + getWidth() /2 +1, getY() + getHeight() /2, end.x +1, end.y, end.x -1, end.y});
        if(!Intersector.overlapConvexPolygons(lineHeroEnd, getActualCollision())){
            if(queueWay.size() < 2)
                return;

            clearActions();
            addAction(Actions.sequence(
                    calculateRotate(end.x - getWidth() /2, end.y - getHeight() /2),
                    Actions.moveTo(end.x - getWidth() /2, end.y - getHeight() /2, timeSpeed(getX() - getWidth() /2, getY() - getHeight() /2, end.x - getWidth() /2, end.y - getHeight() /2))));
            setChangeTrack(false);
        }
    }

    public void finishWalk() {
        heroPolygonUpdate();
        if(Intersector.overlapConvexPolygons(heroPolygon, getFinishWalkPosition())) {
            hero3D.setStopAnimation();
            animationPlay = false;
        }
    }

    private void heroPolygonUpdate() {
        heroPolygon = new Polygon(new float[]{getX(), getY(), getX(), getY() + getHeight(),
                getX() + getWidth(), getY() + getHeight(), getX() + getWidth(), getY()});
    }

    public void drawCollisionLine(){
        float lineFromCenter = this.getWidth() /2;
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + start.x,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + start.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + end.x,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + end.y);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.polygon(new float[]{(
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX() -2+ rectangle.getWidth() /2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY()  -2) + rectangle.getHeight() /2,
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX()  -2+ rectangle.getWidth() /2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY()  +2+ rectangle.getHeight() /2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX()  +2+ rectangle.getWidth() /2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY()  +2+ rectangle.getHeight() /2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX() +2 + rectangle.getWidth() /2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY() -2+ rectangle.getHeight() /2)});
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + start.x - lineFromCenter,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + start.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + end.x - lineFromCenter,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + end.x + lineFromCenter,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + start.x + lineFromCenter,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + start.y});
        shapeRenderer.setColor(Color.GOLD);
        for(int i = 0; i < vertical.get(0).length; i++) {
            shapeRenderer.polygon(new float[]{
                    -camera.position.x + BaseMap.VIEW_WIDTH / 2 + end.x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + end.y,
                    -camera.position.x + BaseMap.VIEW_WIDTH / 2 + end.x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + end.y,
                    -camera.position.x + BaseMap.VIEW_WIDTH / 2 + vertical.get(0)[i].x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + vertical.get(0)[i].y,
                    -camera.position.x + BaseMap.VIEW_WIDTH / 2 + vertical.get(0)[i].x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + vertical.get(0)[i].y});
        }
        shapeRenderer.end();
    }

    private float timeSpeed(float actualX, float actualY, float positionX, float positionY){
        float distanceX = positionX - actualX;
        float distanceY = positionY - actualY;
        double duration;

        if(distanceX < 0)
            distanceX = actualX - positionX;
        if (distanceY < 0)
            distanceY = actualY - positionY;

        if(distanceX < distanceY){
            duration = distanceY / SPEED_MOVE + (distanceX * 0.40) / SPEED_MOVE;
        }else{
            duration = distanceX / SPEED_MOVE + (distanceY * 0.40) / SPEED_MOVE;
        }

        return (float) duration;
    }

    /**
     * Getters and Setters
     */
    public int getFullHp() {
        return fullHp;
    }

    public int getHpNonEq() {
        return hpNonEq;
    }

    public Polygon getFinishWalkPosition(){
        return finishWalkPosition;
    }

    public Polygon getActualPointObject() {
        return actualPointObject;
    }

    public int getActualIndexNpc() {
        return actualIndexNpc;
    }

    public int getLevel() {
        return level;
    }

    public int getMoney(){
        return money;
    }

    public String getMoneyString() {
        if(money >= 1000000)
            return " " + money / 1000000 + " KK";
        else if(money >= 1000)
            return " " + money / 1000 + " K";
        else
            return " " + money;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getExp() {
        return exp;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public int getPoint() {
        return point;
    }

    public Polygon getActualCollision(){
        return actualCollision;
    }

    public Hero3D getHero3D(){
        return hero3D;
    }

    public boolean isMoveStop(){
        return moveStop;
    }

    public boolean isChangeTrack() {
        return changeTrack;
    }

    public boolean isAroundMove() {
        return aroundMove;
    }

    public boolean isAnimation(){
        return animationPlay;
    }

    public boolean isNpcCollision() {
        return npcCollision;
    }

    public int getStrong() {
        return strong;
    }

    public void setStrong(int strong) {
        this.strong = strong;
    }

    public int getWiedza() {
        return wiedza;
    }

    public void setWiedza(int wiedza) {
        this.wiedza = wiedza;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getDefenseFiz() {
        return defenseFiz;
    }

    public void setDefenseFiz(int defenseFiz) {
        this.defenseFiz = defenseFiz;
    }

    public int getDefenseMag() {
        return defenseMag;
    }

    public void setDefenseMag(int defenseMag) {
        this.defenseMag = defenseMag;
    }

    public int getHpEq() {
        return hpEq;
    }

    public void setHpEq(int hpEq) {
        this.hpEq = hpEq;
    }

    public int getStrongEq() {
        return strongEq;
    }

    public void setStrongEq(int strongEq) {
        this.strongEq = strongEq;
    }

    public int getWiedzaEq() {
        return wiedzaEq;
    }

    public void setWiedzaEq(int wiedzaEq) {
        this.wiedzaEq = wiedzaEq;
    }

    public int getArmorEq() {
        return armorEq;
    }

    public void setArmorEq(int armorEq) {
        this.armorEq = armorEq;
    }

    public int getDefenseFizEq() {
        return defenseFizEq;
    }

    public void setDefenseFizEq(int defenseFizEq) {
        this.defenseFizEq = defenseFizEq;
    }

    public int getDefenseMagEq() {
        return defenseMagEq;
    }

    public static boolean getActiveMove(){
        return activeMove;
    }

    public static void setActiveMove(boolean moveStop){
        activeMove = moveStop;
    }

    public void setDefenseMagEq(int defenseMagEq) {
        this.defenseMagEq = defenseMagEq;
    }

    public void setFinishWalkPosition(Polygon position){
        this.finishWalkPosition = position;
    }

    public void setFullHp(int fullHp) {
        this.fullHp = fullHp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public void setMaxExp(int maxExp){
        this.maxExp = maxExp;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void  setActualCollision(Polygon polygon){
        actualCollision = polygon;
    }

    public void setNpcCollision(boolean npcCollision) {
        this.npcCollision = npcCollision;
    }

    public void setMoveStop(boolean value){
        moveStop = value;
    }

    public void setChangeTrack(boolean changeTrack) {
        this.changeTrack = changeTrack;
    }

    public void setAroundMove(boolean aroundMove){
        this.aroundMove = aroundMove;
    }

    public void setActualPointObject(Polygon actualPointObject) {
        this.actualPointObject = actualPointObject;
    }

    public void setAnimationPlayTrue(){
        this.animationPlay = true;
        hero3D.setPlayAnimation();
    }

    public void setHpNonEq(int hpNonEq) {
        this.hpNonEq = hpNonEq;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}
