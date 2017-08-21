package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-06-17.
 */

public class RenderCollisionLine_Test implements Disposable{
    private static ShapeRenderer shapeRenderer;
    private static Camera camera;
    private static Hero hero;


    public RenderCollisionLine_Test(Camera camera, Hero hero) {
        shapeRenderer = new ShapeRenderer();
        this.camera = camera;
        this.hero = hero;
    }

    public static void draw(){
        shapeRenderer.setColor(Color.YELLOW);
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
        //hero.drawCollisionLine();
        //renderPolygonTest(768,906,867,929,653,1106,586,1071,548,1018,541,938,607,907);
        //renderPolygonTest(653,1106,867,929,932,899,1159,1048,760,1123,695,1163,654,1142);
        //renderPolygonTest(1159,1048,909,1189,760,1123);
        //renderPolygonTest(1041,1265,894,1407,817,1420,753,1324,752,1218,821,1194,909,1189);
        //renderPolygonTest(1041,1265,972,1536,943,1537,894,1407);
        renderPolygonTest(1368,840,1562,821,1490,701,1299,736);
        renderPolygonTest(765,901,868,925,933,892,1167,1053,910,1189,1046,1261,975,1539,941,1539,893,1408,816,1422,751,1326,749,1215,828,1197,828,1156,756,1123,732,1144,695,1166,651,1143,652,1107,583,1073,546,1019,538,934,606,903);
        renderPolygonTest(1177,1840,1260,1890,1240,1945,1188,1956,1119,1890,1147,1861);
        renderPolygonTest(966,1909,891,2012,812,2044,747,1964,728,1844,591,1908,570,1856,814,1715,520,1548,527,1505,565,1506,653,1522,695,1525,693,1575,817,1640,853,1621,885,1631,884,1682,927,1721,847,1790,975,1876);
        renderPolygonTest(172,809,422,669,340,582,584,455,562,430,631,395,804,490,1005,393,1155,405,1341,493,1410,491,1522,447,1754,540,1982,451,2300,456,2295,290,1000,200,79,462);
        renderPolygonTest(2060,450,2070,652,2068,740,2061,795,2080,843,1957,929,1902,1044,1949,1242,1927,1326,2035,1688,2006,1728,2048,1930,1950,1878,1745,1979,1728,2030,1555,2141,1455,2085,1359,2124,1287,2216,1272,2279,2327,2461);
        renderPolygonTest(221,784,313,843,292,1005,250,1037,254,1077,420,1169,372,1378,420,1169,372,1378,420,1388,457,1495,373,1675,416,1750,383,1853,352,1928,467,1993,471,2033,850,2233,919,2181,1006,2188,1103,2150,1214,2242,105,2434);
        renderPolygonTest(1708,1567,1859,1634,1958,1714,1954,1763,1839,1878,1772,1825,1675,1829,1483,1743,1481,1696);
        //
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +hero.getX() -7, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +hero.getY() -24, 22, 60);
        //shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +500, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +300, 100, 100);
        shapeRenderer.end();
    }

    public static void drawPublic(float x, float y, float w, float h){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x, -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y, w, h);
        shapeRenderer.end();
    }

    public static void drawPublic(Rectangle rectangle){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(-camera.position.x + BaseMap.VIEW_WIDTH / 2 +rectangle.getX(), -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        shapeRenderer.end();
    }

    public static void drawPointSquare(int x, int y){
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(new float[]{-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x -2,
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  -2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  -2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y -2)});
        shapeRenderer.end();
    }

    public static void drawPointSquare(Vector2 vector){
        int x = (int)vector.x;
        int y = (int)vector.y;
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        camera = BaseScreen.camera;
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.polygon(new float[]{-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x -2,
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  -2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  -2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x  +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y  +2),
                (-camera.position.x + BaseMap.VIEW_WIDTH / 2 +x +2),
                ( -camera.position.y + BaseMap.VIEW_HEIGHT / 2 +y -2)});
        shapeRenderer.end();
    }

    private static void renderPolygonTest(int ... position) {
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

    @Override
    public void dispose(){
        shapeRenderer.dispose();
    }
}
