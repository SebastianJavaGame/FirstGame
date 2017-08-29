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

public class Map_03 extends BaseMap {
    public static final int STARTING_POS_X = 2116;
    public static final int STARTING_POS_Y = 594;
    public static final Vector2[] ENTRIENCES = new Vector2[]{new Vector2(243, 1780), new Vector2(1105, 2300)};
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        //Asset asset = new Asset();
        //asset.manager.load("MAP_01.jpg", Texture.class);
        //asset.manager.finishLoading();
        //if(asset.manager.update()) {
        mapImage = new Image(new Texture("MAP_03.jpg"));
        //}
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_03(Game g) {
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
            enemies[0][i] = new Enemy("enemy/map3/p1.png", "enemy/map3/1.png", "enemy/map3/w1.png", true, "Wojowniczy dzik" ,34 ,       1000, 85, 65, 10f, 80, 75,      13.6f, 3240, 941, 167);//470
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
            enemies[0][i].setDropItemName("shoes4", "tarcza4");
        }
        enemies[0][0].setPosition(0, 0);

        //Group 2
        for (int i = 0; i < 7; i++) {
            enemies[1][i] = new Enemy("enemy/map3/p2.png", "enemy/map3/2.png", "enemy/map3/w2.png", true, "Biała tarantula" ,35 ,       950, 75, 85, 10f, 75, 80,      13, 3200, 985, 177);//475
            enemies[1][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[1][i]);
            enemies[1][i].collisionUpdate();
            enemies[1][i].setDropItemName("wapons11");
        }
        enemies[1][0].setPosition(0, 0);

        //Group 3
        for (int i = 0; i < 7; i++) {
            enemies[2][i] = new Enemy("enemy/map3/p3.png", "enemy/map3/3.png", "enemy/map3/w3.png", false, "Kamieniak" ,37 ,        1200, 80, 70, 11.5f, 85, 75,       12.4f, 3315, 1076, 179);//510
            enemies[2][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[2][i]);
            enemies[2][i].collisionUpdate();
            enemies[2][i].setDropItemName("wapons12", "armor6");
        }
        enemies[2][0].setPosition(0, 0);

        //Group 4
        for (int i = 0; i < 7; i++) {
            enemies[3][i] = new Enemy("enemy/map3/p4.png", "enemy/map3/4.png", "enemy/map3/w4.png", false, "Wachlacz" ,39 ,     1350, 90, 70, 12.5f, 95, 80,       12.7f, 3700, 1171, 184);//560
            enemies[3][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[3][i]);
            enemies[3][i].collisionUpdate();
            enemies[3][i].setDropItemName("ring6", "shoes5");
        }
        enemies[3][0].setPosition(0, 0);

        //Group 5
        for (int i = 0; i < 7; i++) {
            enemies[4][i] = new Enemy("enemy/map3/p5.png", "enemy/map3/5.png", "enemy/map3/w5.png", false, "Mendrec" ,40 ,       1400, 90, 80, 13f, 95, 90,         12, 4335, 815, 199);//590
            enemies[4][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[4][i]);
            enemies[4][i].collisionUpdate();
            enemies[4][i].setDropItemName("pants5", "shoes5");
        }
        enemies[4][0].setPosition(0, 0);

        //Group 6
        for (int i = 0; i < 7; i++) {
            enemies[5][i] = new Enemy("enemy/map3/p6.png", "enemy/map3/6.png", "enemy/map3/w6.png", true, "Stary skalniak" ,42 ,        1550, 80, 90, 13f, 90, 100,          12.3f, 4800, 898, 200);//610
            enemies[5][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[5][i]);
            enemies[5][i].collisionUpdate();
            enemies[5][i].setDropItemName("rekawice6", "armor7");
        }
        enemies[5][0].setPosition(0, 0);

        //Group 7
        for (int i = 0; i < 7; i++) {
            enemies[6][i] = new Enemy("enemy/map3/p7.png", "enemy/map3/7.png", "enemy/map3/w7.png", false, "Stoban" ,44 ,      1750, 115, 85, 15f, 100, 90,         11.7f, 4900, 985, 206);//680
            enemies[6][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[6][i]);
            enemies[6][i].collisionUpdate();
            enemies[6][i].setDropItemName("wapons15", "armor8");
        }
        enemies[6][0].setPosition(0, 0);

        //Group 8
        for (int i = 0; i < 7; i++) {
            enemies[7][i] = new Enemy("enemy/map3/p8.png", "enemy/map3/8.png", "enemy/map3/w8.png", false, "Skalniak" ,45 ,    1700, 90, 120, 15.5f, 95, 95,        11.5f, 5000, 1030, 214);//690
            enemies[7][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[7][i]);
            enemies[7][i].collisionUpdate();
            enemies[7][i].setDropItemName("helmet9", "pants6");
        }
        enemies[7][0].setPosition(0, 0);

        //Group 9
        for (int i = 0; i < 7; i++) {
            enemies[8][i] = new Enemy("enemy/map3/p9.png", "enemy/map3/9.png", "enemy/map3/w9.png", true, "Jaszczur" ,47 ,        1400, 140, 120, 14.5f, 105, 95,           12, 5250, 1123, 220);//710
            enemies[8][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[8][i]);
            enemies[0][i].collisionUpdate();
            enemies[8][i].setDropItemName("ring7", "tarcza5");
        }
        enemies[8][0].setPosition(0, 0);

        //Group 10
        for (int i = 0; i < 7; i++) {
            enemies[9][i] = new Enemy("enemy/map3/p10.png", "enemy/map3/10.png", "enemy/map3/w10.png", false, "Jugger" ,48 ,       1750, 135, 85, 16f, 110, 90,           11, 5365, 1171, 210);//720
            enemies[9][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[9][i]);
            enemies[0][i].collisionUpdate();
            enemies[9][i].setDropItemName("wapons16", "armor8", "pants6");
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
        entriaceToMapRectangle.add(new Rectangle(2160, 560, 100, 100));
        indexToLoadNextMap.add(1);
        entriencesPosition.add(new Vector2(263, 1712));
        //second entrience
        entriaceToMapRectangle.add(new Rectangle(1060, 2320, 120, 100));
        indexToLoadNextMap.add(3);
        entriencesPosition.add(new Vector2(Map_04.STARTING_POS_X, Map_04.STARTING_POS_Y));
        //third entirnce
        entriaceToMapRectangle.add(new Rectangle(200, 1797, 70, 100));
        indexToLoadNextMap.add(7);
        entriencesPosition.add(new Vector2(MapBoss_02.STARTING_POS_X, MapBoss_02.STARTING_POS_Y));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{264,1836,316,1854,340,1916,314,1971,369,1994,386,2041,437,2055,459,2128,608,2199,662,2166,762,2219,853,2216,922,2271,981,2243,1061,2285,1059,2322,207,2344});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(320, 1850), new Vector2(347, 1917), new Vector2(442, 2048), new Vector2(662, 2157), new Vector2(857, 2209), new Vector2(983, 2236), new Vector2(1067, 2280)});
        addObjectCollision(new float[]{1172,2330,1174,2280,1230,2238,1330,2241,1420,2197,1520,2221,1592,2187,1682,2179,1875,2205,1964,2210,1985,2161,2040,2142,2118,2161,2091,1989,2078,1858,2075,1752,2014,1786,1944,1694,1939,1486,1958,1449,2062,1427,2074,919,2039,928,1962,817,1960,744,1989,712,2060,692,2089,691,2098,634,2131,615,2215,641,2250,2412});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1166, 2275), new Vector2(1226,2228), new Vector2(1418,2193), new Vector2(1592,2176), new Vector2(2038, 2132), new Vector2(2011,1798), new Vector2(1929, 1698), new Vector2(2034,939), new Vector2(1951, 823)});
        addObjectCollision(new float[]{2212,558,2089,556,2090,343,2062,344,2003,322,1925,386,1890,384,1838,284,1792,301,1728,263,1681,325,1661,327,1631,313,1615,280,1602,312,1575,322,1554,324,1505,295,1493,323,1390,315,1354,288,1315,308,1226,306,1172,284,1135,251,1134,275,1021,387,989,386,956,361,919,283,847,294,831,341,782,371,759,364,726,312,707,307,686,283,675,309,656,320,607,243,532,298,484,285,449,363,375,462,338,439,281,381,300,540,278,591,279,683,316,741,269,949,303,1005,259,1134,305,1182,252,1355,303,1428,264,1537,289,1571,257,1707,206,1749,204,1798,138,1628,238,318,656,132,2170,132,2170,238,2254,479});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(2080,564), new Vector2(1928,394), new Vector2(1883,391), new Vector2(1789,312), new Vector2(1683,338), new Vector2(1605,323), new Vector2(1499, 352), new Vector2(1383, 328), new Vector2(1223, 319), new Vector2(1024, 399), new Vector2(984,396), new Vector2(836, 367), new Vector2(782, 382), new Vector2(655, 347), new Vector2(375, 474), new Vector2(323, 740), new Vector2(311, 1004), new Vector2(312, 1132), new Vector2(310, 1430), new Vector2(297, 1572), new Vector2(265, 1713)});
        addObjectCollision(new float[]{1086,564,1196,607,1258,654,1326,680,1337,726,1306,832,1278,877,1243,892,1217,874,1183,828,1137,895,1096,896,1057,875,1024,838,995,835,964,778,941,776,909,703,930,637,987,622,1004,590});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1086, 556), new Vector2(1339, 668), new Vector2(1346, 729), new Vector2(1286, 889), new Vector2(1242, 903), new Vector2(1139, 910), new Vector2(1093, 907), new Vector2(991, 847), new Vector2(933, 788), new Vector2(899, 703), new Vector2(922, 631), new Vector2(1000, 584)});
        addObjectCollision(new float[]{581,973,635,1016,635,1055,520,1136,367,1054,379,1003,480,964,541,966});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(583, 962), new Vector2(644, 1012), new Vector2(643, 1062), new Vector2(518, 1149), new Vector2(357, 1057), new Vector2(374, 998), new Vector2(476, 955)});
        addObjectCollision(new float[]{847,1338,1148,1465,1127,1622,1090,1642,1052,1618,1027,1590,878,1511,868,1557,824,1542,819,1480,728,1421,761,1372});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(846, 1330), new Vector2(1154, 1460), new Vector2(1134, 1629), new Vector2(1087, 1652), new Vector2(1038, 1620), new Vector2(871, 1567), new Vector2(814, 1548), new Vector2(718, 1421), new Vector2(754, 1366)});
        addObjectCollision(new float[]{1209,1119,1278,1104,1493,1226,1487,1272,1454,1312,1379,1339,1338,1366,1325,1332,1281,1297,1283,1230,1210,1274,1108,1206,1113,1166});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1278, 1097), new Vector2(1502, 1221), new Vector2(1496, 1276), new Vector2(1456, 1326), new Vector2(1336, 1378), new Vector2(1272, 1303), new Vector2(1208, 1284), new Vector2(1098, 1207), new Vector2(1102, 1162)});
        addObjectCollision(new float[]{1402,1442,1499,1444,1670,1559,1680,1615,1607,1660,1532,1771,1439,1797,1236,1603,1239,1564,1242,1528});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1399, 1434), new Vector2(1500, 1434), new Vector2(1677, 1557), new Vector2(1689, 1619), new Vector2(1537, 1781), new Vector2(1436, 1812), new Vector2(1227, 1606), new Vector2(1231, 1522)});
        addObjectCollision(new float[]{948,1744,1048,1833,1039,1891,1063,1949,1054,1986,934,2076,878,2019,792,2095,751,2089,710,1993,696,1800,792,1747,853,1772});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1057,1827), new Vector2(1070, 1947), new Vector2(1060, 1994), new Vector2(934, 2087), new Vector2(793, 2104), new Vector2(744, 2098), new Vector2(699, 1994), new Vector2(687, 1797), new Vector2(790, 1738), new Vector2(947, 1737)});
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
            pref.putInteger("MAP", 2).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 2).flush();
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
