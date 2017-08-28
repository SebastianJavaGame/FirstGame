package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Character;
import com.mygdx.game.Enemy;
import com.mygdx.game.Npc;
import com.mygdx.game.StatsHero;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Map_04 extends BaseMap {
    public static final int STARTING_POS_X = 1352;
    public static final int STARTING_POS_Y = 360;
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_04.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_04(Game g) {
        super(g, mapImage);
        bossInstance = false;
        deadPosX = STARTING_POS_X;
        deadPosY = STARTING_POS_Y;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[10][7];

        //Group 1
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map4/p1.png", "enemy/map4/1.png", "enemy/map4/w1.png", true, "Zombie dzik" ,50 ,      1900, 125, 105, 14.5f, 110, 100,        1, 5760, 1270, 190);//740
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
            enemies[0][i].setDropItemName("wapons17", "armor9");
        }
        enemies[0][0].setPosition(0, 0);

        //Group 2
        for (int i = 0; i < 7; i++) {
            enemies[1][i] = new Enemy("enemy/map4/p2.png", "enemy/map4/2.png", "enemy/map4/w2.png", true, "Umarlak" ,52 ,       2000, 125, 130, 13.5f, 110, 110,      1, 6050, 1373, 210);//775
            enemies[1][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[1][i]);
            enemies[1][i].collisionUpdate();
            enemies[1][i].setDropItemName("helmet10", "ring8");
        }
        enemies[1][0].setPosition(0, 0);

        //Group 3
        for (int i = 0; i < 7; i++) {
            enemies[2][i] = new Enemy("enemy/map4/p3.png", "enemy/map4/3.png", "enemy/map4/w3.png", true, "Dinotop" ,54 ,          2000, 125, 130, 13.5f, 110, 110,      1, 6090, 1480, 205);//800
            enemies[2][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[2][i]);
            enemies[2][i].collisionUpdate();
            enemies[2][i].setDropItemName("wapons18", "wapons19");
        }
        enemies[2][0].setPosition(0, 0);

        //Group 4
        for (int i = 0; i < 7; i++) {
            enemies[3][i] = new Enemy("enemy/map4/p4.png", "enemy/map4/4.png", "enemy/map4/w4.png", false, "Gonomet" ,55 ,      2300, 120, 100, 14f, 125, 100,         1, 6135, 1535, 220);//805
            enemies[3][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[3][i]);
            enemies[3][i].collisionUpdate();
            enemies[3][i].setDropItemName("pants7", "shoes7");
        }
        enemies[3][0].setPosition(0, 0);

        //Group 5
        for (int i = 0; i < 7; i++) {
            enemies[4][i] = new Enemy("enemy/map4/p5.png", "enemy/map4/5.png", "enemy/map4/5.png", false, "Bevter" ,57 ,        2450, 150, 100, 12f, 130, 115,      1, 6215, 1648, 236);//845
            enemies[4][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[4][i]);
            enemies[4][i].collisionUpdate();
            enemies[4][i].setDropItemName("armor10", "helmet11");
        }
        enemies[4][0].setPosition(0, 0);

        //Group 6
        for (int i = 0; i < 7; i++) {
            enemies[5][i] = new Enemy("enemy/map4/p6.png", "enemy/map4/6.png", "enemy/map4/w6.png", false, "Skvergan" ,58 ,      2700, 100, 150, 10f, 100, 145,      1, 6250, 1706, 263);//850
            enemies[5][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[5][i]);
            enemies[5][i].collisionUpdate();
            enemies[5][i].setDropItemName("rekawice8", "ring9");
        }
        enemies[5][0].setPosition(0, 0);

        //Group 7
        for (int i = 0; i < 7; i++) {
            enemies[6][i] = new Enemy("enemy/map4/p7.png", "enemy/map4/7.png", "enemy/map4/w7.png", false, "Muzovert" ,60 ,     2800, 110, 160, 11f, 100, 125,        1, 6750, 1825,258);//870
            enemies[6][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[6][i]);
            enemies[6][i].collisionUpdate();
            enemies[6][i].setDropItemName("wapons20", "ring10");
        }
        enemies[6][0].setPosition(0, 0);

        //Group 8
        for (int i = 0; i < 7; i++) {
            enemies[7][i] = new Enemy("enemy/map4/p8.png", "enemy/map4/8.png", "enemy/map4/w8.png", false, "Skarab" ,62 ,       3000, 135, 135, 11f, 100, 120,       1, 7150, 1948, 271);//885
            enemies[7][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[7][i]);
            enemies[7][i].collisionUpdate();
            enemies[7][i].setDropItemName("wapons21", "armoe11");
        }
        enemies[7][0].setPosition(0, 0);

        //Group 9
        for (int i = 0; i < 7; i++) {
            enemies[8][i] = new Enemy("enemy/map4/p9.png", "enemy/map4/9.png", "enemy/map4/w9.png", false, "Krather" ,64 ,      3600, 170, 120, 10f, 140, 110,         1, 7500, 2075, 249);//1000
            enemies[8][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[8][i]);
            enemies[8][i].collisionUpdate();
            enemies[8][i].setDropItemName("pants8", "rekawice9");
        }
        enemies[8][0].setPosition(0, 0);

        //Group 10
        for (int i = 0; i < 7; i++) {
            enemies[9][i] = new Enemy("enemy/map4/p10.png", "enemy/map4/10.png", "enemy/map4/w10.png", true, "Verdos" ,65 ,     3500, 180, 135, 10f, 140, 120,          1, 7580, 2140, 264);//1025
            enemies[9][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[9][i]);
            enemies[9][i].collisionUpdate();
            enemies[9][i].setDropItemName("ring11", "tarcza7", "shoes8", "helmet12");
        }
        enemies[9][0].setPosition(0, 0);
    }

    @Override
    public void addNpcToMap() {
        //addNpc("mag.png", "glominHead.png", "Witherman", 20, 0, 0);
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(940, 200, 500, 100));
        indexToLoadNextMap.add(2);
        entriencesPosition.add(new Vector2(1104, 2290));
        //second entrience
        entriaceToMapRectangle.add(new Rectangle(215, 1863, 60, 100));
        indexToLoadNextMap.add(8);
        entriencesPosition.add(new Vector2(MapBoss_03.STARTING_POS_X, MapBoss_03.STARTING_POS_Y));
        //third entrience
        entriaceToMapRectangle.add(new Rectangle(2150, 1435, 100, 200));
        indexToLoadNextMap.add(4);
        entriencesPosition.add(new Vector2(Map_05.STARTING_POS_X, Map_05.STARTING_POS_Y));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{927,246,956,323,1020,330,1065,383,1134,425,1196,436,1257,478,1394,563,1445,592,1493,637,1538,617,1572,638,1584,690,1515,814,1473,843,1436,848,1374,840,1363,808,1331,799,1285,730,1252,762,1219,720,1184,722,1110,625,1070,626,1015,584,962,569,923,569,885,539,837,475,795,462,725,367,709,414,657,435,578,462,560,452,525,357,303,441,336,594,332,667,285,715,289,785,378,821,368,947,375,996,318,1187,402,1217,350,1534,404,1592,308,1682,320,1728,213,1871,227,338,678,242});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(326,1731), new Vector2(413,1594), new Vector2(408,1213), new Vector2(384,995), new Vector2(384,816), new Vector2(342, 669), new Vector2(346, 588), new Vector2(571, 477), new Vector2(715,423), new Vector2(789,471), new Vector2(920,585), new Vector2(1064,634), new Vector2(1180,730), new Vector2(1250,773), new Vector2(1368,851), new Vector2(1440,863), new Vector2(1478,853), new Vector2(1521,824), new Vector2(1592,694), new Vector2(1580,633),new Vector2(1540,609) ,new Vector2(1201,422) ,new Vector2(1025,322)});
        addObjectCollision(new float[]{1323,297,1436,328,1489,310,1654,342,1707,295,1743,309,1792,253,1831,337,1877,353,1925,305,1978,315,1991,349,2025,459,2052,553,1970,644,2037,771,2008,814,2047,924,1979,951,1962,1015,1871,1011,1697,1140,1620,1169,1590,1137,1534,1146,1481,1183,1465,1262,1401,1274,1355,1347,1371,1422,1470,1550,1631,1501,1702,1471,1722,1468,1783,1402,1939,1272,1966,1273,2013,1208,2006,1308,2037,1450,2090,1463,2126,1397,2177,1434,2187,308,1629,185});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1317,307), new Vector2(1435,342), new Vector2(1654,358), new Vector2(1744,320), new Vector2(1823,347), new Vector2(1878,366), new Vector2(1959,644), new Vector2(1996,816), new Vector2(1971,945), new Vector2(1865,1005), new Vector2(1592,1131), new Vector2(1529,1140), new Vector2(1472,1179), new Vector2(1395,1265), new Vector2(1342,1346), new Vector2(1360,1428), new Vector2(1469,1562), new Vector2(1729,1481), new Vector2(1968,1287), new Vector2(2029, 1462), new Vector2(2093,1477)});
        addObjectCollision(new float[]{2210,1518,2147,1541,2120,1681,2102,2076,2024,2214,1854,2214,1777,2225,1756,2262,1723,2254,1677,2213,1541,2238,1457,2243,1416,2258,1353,2261,1332,2208,1291,2205,1276,2250,1089,2187,1006, 2238, 955, 2215, 832, 2225,790,2258,739,2256,732,2213,611,2199,593,2151,543,2141,428,2197,393,2076,333,1985,281,1935,256,2286,2216,2328});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(2139,1536), new Vector2(2110,1679), new Vector2(1772,2214), new Vector2(1678,2203), new Vector2(1455,2235), new Vector2(1284,2200), new Vector2(1088,2182), new Vector2(951,2208), new Vector2(828,2216), new Vector2(737,2208), new Vector2(600,2146), new Vector2(538,2133), new Vector2(401,2071), new Vector2(338,1980)});
        addObjectCollision(new float[]{1912,1640,2050,1705,2057,1821,2017,1898,1989,1901,1776,1791,1783,1681,1870,1635});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1868,1630), new Vector2(1912,1632), new Vector2(2059,1702), new Vector2(2064,1824), new Vector2(2022,1909), new Vector2(1990,1914), new Vector2(1765,1795), new Vector2(1771,1677)});
        addObjectCollision(new float[]{924,1752,993,1775,1024,1790,1025,1857,991,1933,963,1962,917,1955,904,1928,828,1911,822,1821,840,1807});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1031,1788), new Vector2(1031,1861), new Vector2(998,1940), new Vector2(965,1973), new Vector2(912,1963), new Vector2(820,1918), new Vector2(813,1814), new Vector2(921,1744)});
        addObjectCollision(new float[]{1139,1466,1264,1533,1264,1643,1164,1702,1108,1773,1082,1777,867,1669,878,1558,990,1501,1040,1520});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(870,1555), new Vector2(990,1494), new Vector2(1137,1458), new Vector2(1271,1529), new Vector2(1271,1649), new Vector2(1112,1788), new Vector2(1079,1788), new Vector2(852,1677)});
        addObjectCollision(new float[]{1000,1087,1043,1086,1128,1131,1136,1227,922,1338,863,1325,744,1340,699,1327,699,1211,742,1181,819,1173,854,1175,871,1149});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(688,1336), new Vector2(689,1208), new Vector2(738,1173), new Vector2(865,1143), new Vector2(999,1079), new Vector2(1045,1078), new Vector2(1136,1127), new Vector2(1145,1235), new Vector2(920,1351), new Vector2(746,1352)});
    }

    @Override
    public void generateMap() {
        if(!firstRun) {
            characters = new ArrayList<Character>();
            firstRun = true;

            bgTexture.setSize(mapWidth, mapHeight);

            addEnemyToMap();
            addNpcToMap();
            addCollisionToMap();
        }
        addEntranceToMap();

        stage.addActor(bgTexture);

        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
        charactersList = characters;

        for(Character character: characters) {
            stage.addActor(character);
        }
    }

    @Override
    public void saveOrginalPosition(){
        pref = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);

        if(Menu.getIsFirstSpawnHeroPosition()) {
            pref.putInteger("POS_X", pref.getInteger("POS_X", STARTING_POS_X)).flush();
            pref.putInteger("POS_Y", pref.getInteger("POS_Y", STARTING_POS_Y)).flush();
            pref.putInteger("MAP", 3).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 3).flush();
        }
    }

    private void addObjectCollision(float[] position) {
        objectCollision.add(new Polygon(position));
    }

    private void addVerticalToObjectCollision(Vector2[] point){
        verticalCollision.add(point);
    }

    private void addNpc(String path, String head, String name, int level, int idShop, int idTask){
        Npc npc = new Npc(new Texture(Gdx.files.internal(path)), new Image(new Texture(Gdx.files.internal(head))), name, level, idShop, idTask);
        npc.setPosition(500, 400);
        npc.setSize(60, 100);
        characters.add(npc);
        npc.setRectangle(0, 0, 0, 0);
        npc.collisionUpdate();
    }

    public ArrayList<Character> getCharacter(){
        return characters;
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
