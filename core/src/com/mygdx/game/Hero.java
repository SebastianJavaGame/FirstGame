package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.awt.Point;
import java.util.ArrayList;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Hero extends Character {
    public final static int SPEED_MOVE = 30;
    private Camera camera;
    private Hero3D hero3D;

    private ArrayList<Polygon> objectMap;
    private ArrayList<Point[]> vertical;
    private ArrayList<Point> queueWay = null;

    private Polygon actualCollision;
    private Polygon actualPointObject;
    private Polygon heroPolygon;
    private Polygon finishWalkPosition;

    private Rectangle rectangle;

    private Point start = new Point(0, 0);
    private Point end = new Point(0 ,0);

    private boolean moveStop;
    private boolean aroundMove;
    private boolean animationPlay;

    private boolean changeTrack;

    private int level;
    private int money;
    private int hp;
    private int maxHp;
    private int exp;
    private int maxExp;

    public int strong = 10;
    public int wiedza = 10;
    public int defense = 10;

    public int hpEq = 100 + 25;
    public int strongEq = strong +5;
    public int wiedzaEq = wiedza +5;
    public int defenseEq = defense +5;

    public Hero(Texture texture, ArrayList<Polygon> objectMap, ArrayList<Point[]> vertical, Camera camera, Hero3D hero3D) {
        super(texture);
        this.objectMap = objectMap;
        this.vertical = vertical;
        this.camera = camera;
        this.rectangle = new Rectangle();
        this.hero3D = hero3D;
        create();
    }

    private void create(){
        setMaxExp(140);
        setMoney(184420);
        setMaxHp(1200);
        setHp(800);
        setExp(100);
        setLevel(25);
    }

    public void move(final float posX, final float posY) {
        clearActions();
        start.setLocation((int) getX() + getWidth() / 2, (int) getY() + getHeight() / 2);
        end.setLocation((int) posX + getWidth() / 2, (int) posY + getHeight() / 2);
        Polygon track = new Polygon(new float[]{start.x - 1, start.y, end.x - 1,
                end.y, end.x + 1, end.y, start.x + 1, start.y});
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
            Point[] verticalObject = vertical.get(i);
            if (!moveStop && countCollision < 2) {
                setActualCollision(object);
                if (Intersector.overlapConvexPolygons(point, object)) {
                    setAnimationPlayTrue();
                    addAction(Actions.sequence(
                            calculateRotate(posX, posY),
                            Actions.moveTo(end.x - getWidth() / 2, end.y - getHeight() / 2, timeSpeed(getX(), getY(), end.x - getWidth() / 2, end.y - getHeight() / 2))));
                    setMoveStop(true);
                    System.out.println(0.1);
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
                queueWay = new ArrayList<Point>();
                queueWay.add(null);

                heroPolygonUpdate();
                for(Polygon objectPoint: objectMap){
                    if(object != objectPoint) {
                        if (Intersector.overlapConvexPolygons(point, objectPoint))
                            setActualPointObject(objectPoint);
                    }

                    if(Intersector.overlapConvexPolygons(heroPolygon, object))
                        setActualCollision(object);//TODO rework objectPoint -> object
                }

                float shorterWay = 999;
                int indexShorterWay = -1;
                int amountVertical = verticalObject.length;
                Point vertical = null;

                for (int j = 0; j < amountVertical; j++) {
                    Polygon lineEndVertical = new Polygon(new float[]{end.x - 1, end.y, end.x + 1, end.y,
                            verticalObject[j].x + 1, verticalObject[j].y, verticalObject[j].x - 1, verticalObject[j].y});
                    if (!Intersector.overlapConvexPolygons(object, lineEndVertical)) {
                        float duration = timeSpeed(end.x, end.y, verticalObject[j].x, verticalObject[j].y);
                        if (duration < shorterWay) {
                            shorterWay = duration;
                            indexShorterWay = j;
                            queueWay.set(0, new Point(verticalObject[j].x, verticalObject[j].y));
                            vertical = new Point(verticalObject[j].x, verticalObject[j].y);
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
                        queueWay.add(new Point(verticalObject[index].x, verticalObject[index].y));
                        lineStartVertical = new Polygon(new float[]{start.x - 1, start.y, start.x + 1, start.y,
                                verticalObject[index].x + 1, verticalObject[index].y, verticalObject[index].x - 1, verticalObject[index].y});
                    } else {
                        if (index - 1 < 0)
                            index = amountVertical - 1;
                        else
                            index--;
                        queueWay.add(new Point(verticalObject[index].x, verticalObject[index].y));
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
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + 387,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + 341,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + 481,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + 397,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + 480,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + 458,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + 455,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + 498,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 + 452,
                -camera.position.y + BaseMap.VIEW_HEIGHT / 2 + 526});
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[0].x +1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[0].y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[0].x -1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[0].y});
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[1].x +1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[1].y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[1].x -1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[1].y});
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[2].x +1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[2].y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[2].x -1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[2].y});
        shapeRenderer.polygon(new float[]{
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x - 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +end.x + 1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +end.y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[3].x +1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[3].y,
                -camera.position.x + BaseMap.VIEW_WIDTH / 2 +vertical.get(0)[3].x -1, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +vertical.get(0)[3].y});
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
    public void setFinishWalkPosition(Polygon position){
        this.finishWalkPosition = position;
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

    public Polygon getFinishWalkPosition(){
        return finishWalkPosition;
    }

    public Polygon getActualPointObject() {
        return actualPointObject;
    }

    public int getLevel() {
        return level;
    }

    public String getMoney() {
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

    public Polygon getActualCollision(){
        return actualCollision;
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
}
