package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.ArrayList;

import Screen.BaseMap;
import Screen.BaseScreen;
import Screen.Map_01;
import Screen.Map_02;
import Screen.Map_03;
import Screen.Map_04;
import Screen.Map_05;
import Screen.Map_06;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Hero extends Character {
    public final static int SPEED_MOVE = 25;
    public Texture arm;

    private final Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
    private Asset asset = new Asset();

    private Game game;
    private Camera camera;
    private Hero3D hero3D;
    private Stage stage;
    private Rectangle heroBox;

    private ArrayList<Polygon> objectMap;
    private ArrayList<Vector2[]> vertical;
    private ArrayList<Vector2> queueWay = null;

    private ArrayList<Character> characters;

    private Polygon actualCollision;
    private Polygon actualPointObject;
    private Polygon heroPolygon;
    private Polygon track;

    private Rectangle finishWalkPosition;
    private Rectangle rectangle;

    private Vector2 start;
    private Vector2 end;
    private Vector2[] cornersHero;
    private Vector2 cornerEnemy;
    private  Vector2 o2;
    private Vector2 o4;
    private Vector2 nonCollision;

    private boolean moveStop;
    private boolean aroundMove;
    private boolean animationPlay;
    private boolean changeTrack;
    private boolean characterCollision;
    private boolean characterCollisionLook;
    private boolean respawn;
    private static boolean activeMove;

    private static int level;
    private static int money;
    private int maxHp;
    private static int exp;
    private static int maxExp;

    private int actualIndexCharacter;
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

    private static int point;

    private Sound soundNextMap;
    private static Sound soundStep;
    private static Sound soundLvlUp;

    public Hero(Texture texture){
        super(texture);
    }

    public Hero(Texture texture, ArrayList<Polygon> objectMap, ArrayList<Vector2[]> vertical, Camera camera, Hero3D hero3D,
                ArrayList<Character> characters) {
        super(texture);
        this.objectMap = objectMap;
        this.vertical = vertical;
        this.camera = camera;
        this.rectangle = new Rectangle();
        this.characters = characters;
        this.hero3D = hero3D;
        hero3D.setRenderHero3d(true);
        start = new Vector2();
        end = new Vector2();
        this.stage = BaseScreen.getStage();
        this.game = BaseScreen.getGame();
        heroBox = new Rectangle();
        cornersHero = new Vector2[2];
        nonCollision = new Vector2(getX(), getY());

        for(Character character: characters)
            character.setHero(this);

        asset.loadHero();
        asset.manager.finishLoading();
        if(asset.manager.update()){
            arm = asset.manager.get("heroArm.png", Texture.class);
            soundNextMap = asset.manager.get("sound/nextMap.ogg", Sound.class);
            soundStep = asset.manager.get("sound/step.ogg", Sound.class);
            soundLvlUp = asset.manager.get("sound/lvlUp.ogg", Sound.class);
            create();
        }
    }

    private void create(){
        setLevel(preferences.getInteger("LEVEL", 1));
        setMaxExp(ExperienceRequired.getMaxExperience(getLevel()));

        setMaxHp(preferences.getInteger("MAX_HP", 200));
        setHpNonEq(getMaxHp());

        actualIndexCharacter = 0;//preferences.getInteger("COLLISION", 0);
        strong = preferences.getInteger("STRONG", 10);
        wiedza = preferences.getInteger("WIEDZA", 10);
        armor = preferences.getInteger("ARMOR", 10);
        defenseFiz = preferences.getInteger("DEFENSE_FIZ", 10);
        defenseMag = preferences.getInteger("DEFENSE_MAG", 10);

        int experience = preferences.getInteger("EXP", 1);
        if(experience <= getMaxExp())
            setExp(experience);
        else {
            System.out.println("ERROR #1: 'exp is bigger to max exp'");
            int calculate = experience -ExperienceRequired.getMaxExperience(getLevel());
            while(calculate > ExperienceRequired.getMaxExperience(getLevel())) {
                setLevel(getLevel() + 1);
                calculate = experience -ExperienceRequired.getMaxExperience(getLevel());
            }
            setLevel(getLevel() +1);
            setExp(calculate);
            setMaxExp(ExperienceRequired.getMaxExperience(getLevel()));
        }

        setMoney(preferences.getInteger("MONEY", 5000));
        point = preferences.getInteger("FREE_POINT", 5);

        try {
            new UpdateHeroStats(this);
        } catch (CloneNotSupportedException e) {}

        setHp(preferences.getInteger("HP", getFullHp()));

        nonCollision.set(preferences.getInteger("POS_X"), preferences.getInteger("POS_Y"));
    }

    @Override
    public void collisionDo() {
        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
            BaseScreen.showException(e);
        }
    }

    public void move(final float posX, final float posY) {
        clearActions();
        heroPolygonUpdate();
        heroUpdateCollisionBox();

        soundStep.stop();
        soundStep.loop(0.2f, 0.5f, 0);

        start.set((int) getX() + getWidth() / 2, (int) getY() + getHeight() / 2);
        end.set((int) posX + getWidth() / 2, (int) posY + getHeight() / 2);
        track = new Polygon(new float[]{start.x - 1, start.y, end.x - 1,
                end.y, end.x + 1, end.y, start.x + 1, start.y});
        rectangle = track.getBoundingRectangle();

        Polygon point = new Polygon(new float[]{end.x - 1, end.y - 1, end.x - 1, end.y + 1, end.x + 1, end.y + 1, end.x + 1, end.y - 1});
        setFinishWalkPosition(new Rectangle(end.x - 1, end.y - 1, 2, 2));

        actualIndexCharacter = preferences.getInteger("COLLISION", 0);

        if(calculateCollisionTwoRectangle(heroBox, characters.get(actualIndexCharacter).getCollision())) {
            if(cornersHero[0] != null && cornersHero[1] == null){
                int x = (int)cornersHero[0].x - (int)cornerEnemy.x;
                int y = (int)cornersHero[0].y - (int)cornerEnemy.y;

                if(x < 0)
                    x *= -1;
                if(y < 0)
                    y *= -1;

                if(x < y){
                    int heroBoxCenterX = (int)(heroBox.getX() + heroBox.getWidth() /2);
                    if(heroBoxCenterX < cornerEnemy.x){
                        if(posX < cornerEnemy.x)
                            setCharacterCollision(true);
                    }
                    else{
                        if(posX > cornerEnemy.x)
                            setCharacterCollision(true);
                    }
                }else {
                    int heroBoxCenterY = (int) (heroBox.getY() + heroBox.getHeight() / 2);
                    if(heroBoxCenterY < cornerEnemy.y) {
                        if(posY < cornerEnemy.y)
                            setCharacterCollision(true);
                    }
                    else{
                        if(posY > cornerEnemy.y)
                            setCharacterCollision(true);
                    }
                }
            }
            else if(cornersHero[0] != null && cornersHero[1] != null){
                if(cornerEnemy == o4 || cornerEnemy == o2){
                    int heroBoxCenterX = (int)(heroBox.getX() + heroBox.getWidth() /2);
                    if(heroBoxCenterX < cornerEnemy.x){
                        if(posX < cornerEnemy.x)
                            setCharacterCollision(true);
                    }
                    else{
                        if(posX > cornerEnemy.x)
                            setCharacterCollision(true);
                    }
                }else {
                    int heroBoxCenterY = (int) (heroBox.getY() + heroBox.getHeight() / 2);
                    if(heroBoxCenterY < cornerEnemy.y) {//TODO Refactor
                        if(posY < cornerEnemy.y)
                            setCharacterCollision(true);
                    }
                    else{
                        if(posY > cornerEnemy.y)
                            setCharacterCollision(true);
                    }
                }

            }else{
                try {
                    throw new MyException();
                } catch (MyException e) {
                    e.printStackTrace();
                    BaseScreen.showException(e);
                }
            }
        } else
            setCharacterCollision(false);
        setCharacterCollisionLook(true);

        //temporaryListVector.clear();
        //temporaryListVector.add(new Vector2(heroBox.getX(), heroBox.getY()));
        //temporaryListVector.add(new Vector2(heroBox.getX() +heroBox.getWidth(), heroBox.getY()));
        //temporaryListVector.add(new Vector2(heroBox.getX() +heroBox.getWidth(), heroBox.getY() +heroBox.getHeight()));
        //temporaryListVector.add(new Vector2(heroBox.getX(), heroBox.getY() +heroBox.getHeight()));

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

    public static void levelUp(){
        Preferences preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        soundLvlUp.play();
        preferences.putInteger("LEVEL", preferences.getInteger("LEVEL") +1).flush();
        preferences.putInteger("FREE_POINT", preferences.getInteger("FREE_POINT") +5).flush();
        setLevel(getLevel() + 1);
        setMaxExp(ExperienceRequired.getMaxExperience(getLevel()));
        setPoint(getPoint() +5);
        System.out.println(getPoint() + "point");
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
            soundStep.stop();
        }
    }

    public void objectPointCollision(){
        heroPolygonUpdate();
        if(actualPointObject != null){
            if(Intersector.overlapConvexPolygons(heroPolygon, getActualPointObject()))
                clearActions();
        }
    }

    public void collisionCharacter() {
        for (int i = 0; i < characters.size(); i++) {
            heroUpdateCollisionBox();
            if (calculateCollisionTwoRectangle(heroBox, characters.get(i).getCollision())) {
                if(characterCollision) {
                    if (i == actualIndexCharacter)
                        return;
                }
                clearActions();
                characters.get(i).collisionDo();
                setCharacterCollisionLook(false);
                setCharacterCollision(false);
                hero3D.setStopAnimation();
                actualIndexCharacter = i;
                preferences.putInteger("COLLISION", actualIndexCharacter).flush();
                System.out.println(actualIndexCharacter + "actual index character");
                soundStep.stop();
                setPosition(nonCollision.x, nonCollision.y);
            }else {
                if(i == characters.size() -1)
                    nonCollision.set(getX(), getY());
            }
        }
    }

    public boolean calculateCollisionTwoRectangle(Rectangle hero, Rectangle other){
        Vector2 h1 = new Vector2(hero.getX(), hero.getY());
        Vector2 h2 = new Vector2(hero.getX() +hero.getWidth(), hero.getY());
        Vector2 h3 = new Vector2(hero.getX() +hero.getWidth(), hero.getY() +hero.getHeight());
        Vector2 h4 = new Vector2(hero.getX(), hero.getY() +hero.getHeight());

        Vector2 o1 = new Vector2(other.getX(), other.getY());
        o2 = new Vector2(other.getX() +other.getWidth(), other.getY());
        Vector2 o3 = new Vector2(other.getX() +other.getWidth(), other.getY() +other.getHeight());
        o4 = new Vector2(other.getX(), other.getY() +other.getHeight());

        //temporaryListVector.add(o1);
        //temporaryListVector.add(o2);
        //temporaryListVector.add(o3);
        //temporaryListVector.add(o4);

        int indexCorner = 0;
        cornersHero[0] = null;
        cornersHero[1] = null;
        boolean temporaryValue = false;

        if(h1.x <= o3.x && h1.y <= o3.y && h1.x >= o1.x && h1.y >= o1.y)
        {
            cornersHero[indexCorner] = h1;
            cornerEnemy = o3;
            indexCorner++;
            temporaryValue = true;

            if(h4.x <= o2.x && h4.y >= o2.y && h4.x >= o4.x && h4.y <= o4.y){
                cornersHero[indexCorner] = h4;
                cornerEnemy = o2;
                indexCorner++;
                temporaryValue = true;
                if(indexCorner == 2) {
                    return true;
                }
            }
        }
        if(h2.x >= o4.x && h2.y <= o4.y && h2.x <= o2.x && h2.y >= o2.y){
            cornersHero[indexCorner] = h2;
            cornerEnemy = o4;
            indexCorner++;
            temporaryValue = true;
            if(indexCorner == 2) {
                cornerEnemy = o3;
                return true;
            }
        }
        if(h3.x >= o1.x && h3.y >= o1.y && h3.x <= o3.x && h3.y <= o3.y){
            cornersHero[indexCorner] = h3;
            cornerEnemy = o1;
            indexCorner++;
            temporaryValue = true;
            if(indexCorner == 2){
                cornerEnemy = o4;
                return true;
            }
        }
        if(h4.x <= o2.x && h4.y >= o2.y && h4.x >= o4.x && h4.y <= o4.y){
            cornersHero[indexCorner] = h4;
            cornerEnemy = o2;
            indexCorner++;
            temporaryValue = true;
            if(indexCorner == 2) {
                cornerEnemy = o1;
                return true;
            }
        }

        //throw ExceptionCollision
            //if(hero.getX() +hero.getWidth() <= o4.y && hero.getX() +hero.getWidth() >= o4.x && hero.getY() +hero.getHeight() /2 <= o2.x && hero.getY() +hero.getHeight() /2 >= o2.y)
            //    return true;
            //if(hero.getX() <= o3.x && hero.getY() +hero.getHeight() /2 <= o3.y && hero.getX() >= o1.x && hero.getY() +hero.getHeight() /2 >= o1.y)
            //    return true;

        return temporaryValue;
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
        heroUpdateCollisionBox();
        if (calculateCollisionTwoRectangle(getFinishWalkPosition(), heroBox)) {
            hero3D.setStopAnimation();
            animationPlay = false;
            setCharacterCollisionLook(false);
            soundStep.stop();
            nonCollision.set(getX(), getY());
        }

        for (int i = 0; i < BaseMap.getEntriaceToMapRectangle().size(); i++) {
            if (calculateCollisionTwoRectangle(heroBox, BaseMap.getEntriaceToMapRectangle().get(i))) {
                Hero3D.setStopAnimation();
                soundNextMap.play(0.8f);
                setStopStep();
                preferences.putInteger("COLLISION", 0).flush();
                /**
                 * i = indexToLoadMap
                 */
                switch (BaseMap.getIndexToLoadNextMap().get(i)) {
                    case 0:
                        System.out.println("map1");
                        game.setScreen(new Map_01(game));
                        break;
                    case 1:
                        System.out.println("map2");
                        game.setScreen(new Map_02(game));
                        break;
                    case 2:
                        System.out.println("map3");
                        game.setScreen(new Map_03(game));
                        break;
                    case 3:
                        System.out.println("map4");
                        game.setScreen(new Map_04(game));
                        break;
                    case 4:
                        System.out.println("map5");
                        game.setScreen(new Map_05(game));
                        break;
                    case 5:
                        System.out.println("map6");
                        game.setScreen(new Map_06(game));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void heroPolygonUpdate() {
        heroPolygon = new Polygon(new float[]{getX(), getY(), getX(), getY() + getHeight(),
                getX() + getWidth(), getY() + getHeight(), getX() + getWidth(), getY()});
    }

    public void heroUpdateCollisionBox(){
        heroBox.set(this.getX() -7,this.getY() -24, 22, 30);
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

    public Rectangle getFinishWalkPosition(){
        return finishWalkPosition;
    }

    public Rectangle getHeroBox(){
        return heroBox;
    }

    public Polygon getActualPointObject() {
        return actualPointObject;
    }

    public static int getLevel() {
        return level;
    }

    public static int getMoney(){
        return money;
    }

    public int getMoneyNoStatic(){return money;}

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

    public static int getExp() {
        return exp;
    }

    public static int getMaxExp() {
        return maxExp;
    }

    public static int getPoint() {
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

    public boolean isRespawn(){
        return respawn;
    }

    public boolean isAroundMove() {
        return aroundMove;
    }

    public boolean isAnimation(){
        return animationPlay;
    }

    public boolean isCharacterCollisionLook() {
        return characterCollisionLook;
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

    public void setFinishWalkPosition(Rectangle position){
        this.finishWalkPosition = position;
    }

    public void setFullHp(int fullHp) {
        this.fullHp = fullHp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setMaxHp(int setMaxHp){
        maxHp = setMaxHp;
    }

    public static void setExp(int experience){
        exp = experience;
    }

    public static void setMaxExp(int setMaxExp){
        maxExp = setMaxExp;
    }

    public static void setLevel(int setLevel){
        level = setLevel;
    }

    public static void setMoney(int setMoney){
        money = setMoney;
    }

    public void  setActualCollision(Polygon polygon){
        actualCollision = polygon;
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

    public static void setPoint(int setPoint) {
        point = setPoint;
    }

    public void setCharacterCollisionLook(boolean charactersCollisionLook) {
        this.characterCollisionLook = charactersCollisionLook;
    }

    public void setCharacterCollision(boolean charactersCollision) {
        this.characterCollision = charactersCollision;
    }

    public static void setStopStep(){
        soundStep.stop();
    }

    public static void playSoundLvlUp(){
        soundLvlUp.play();
    }

    public Hero getHero(){
        return this;
    }


    public Stage getStage(){
        return stage;
    }

    public Game getGame(){
        return game;
    }
}
