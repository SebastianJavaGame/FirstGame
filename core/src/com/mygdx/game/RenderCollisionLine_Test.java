package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-06-17.
 */

public class RenderCollisionLine_Test {
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private Hero hero;

    public RenderCollisionLine_Test(Camera camera, Hero hero) {
        shapeRenderer = new ShapeRenderer();
        this.camera = camera;
        this.hero = hero;
    }

    public void draw(){
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //DRAW
        //renderPolygonTest(287, 241, 381, 297, 380, 358, 355, 398, 352, 426);
        //renderPolygonTest((int)hero.getX(), (int)hero.getY(), (int)hero.getX(), (int)hero.getY() + (int)hero.getHeight(),
                //(int)hero.getX() + (int)hero.getWidth(), (int)hero.getY() + (int)hero.getHeight(), (int)hero.getX() + (int)hero.getWidth(), (int)hero.getY());
        /*shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 440, 40, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 480, 120, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 480, 200, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 480, 280, 480});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 480, 320, 440});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 440, 320, 360});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 360, 320, 280});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 280, 320, 200});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 200, 320, 120});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 320, 120, 320, 40});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 344, 10, 295, -30});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 280, 0, 200, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 200, 0, 120, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 120, 0, 40, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 40, 0, -32, 0});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 40, 0, 120});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 120, 0, 200});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 200, 0, 280});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 280, 0, 360});
        shapeRenderer.polygon(new float[]{BaseMap.VIEW_WIDTH /2 , BaseMap.VIEW_HEIGHT /2, 0, 360, 0, 440});
        */
        hero.drawCollisionLine();
        //renderPolygonTest(637, 539, 650, 590, 606, 675, 426, 654, 423, 601, 340, 581, 303, 529, 389, 471, 522, 513, 590, 529);
        //renderPolygonTest(825, 613, 933, 572, 894, 531, 829, 572, 803, 547, 761, 573);
        //renderPolygonTest(1082,683,1131,698,1202,652,1476,656,1480,1320,1242,1320,1208,1125,1238,1079,1210,1052,1160,1073,1110,997,1126,843,994,861,944,801,959,721);
        //renderPolygonTest(1500, 1350, 1223, 1350, 930, 816, 944, 710, 1192, 639, 1500, 645);
        //
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +hero.getX() -7, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +hero.getY() -24, 22, 32);
        shapeRenderer.end();
    }

    private void renderPolygonTest(int ... position) {
        ArrayList<Integer> listPosition = new ArrayList<Integer>();
        for(int i = -1; i < position.length-1; i+=2){
            listPosition.add((int)-camera.position.x + BaseMap.VIEW_WIDTH / 2 + position[i+1]);
            listPosition.add((int)-camera.position.y + BaseMap.VIEW_HEIGHT / 2 + position[i+2]);
        }
        float[] tabPosition = new float[listPosition.size()];

        int i =0;
        for(int element : listPosition){
            tabPosition[i] = element;
            i++;
        }

        shapeRenderer.polygon(tabPosition);
    }
}
