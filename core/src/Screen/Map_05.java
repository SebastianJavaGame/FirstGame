package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
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

public class Map_05 extends BaseMap {
    public static final int STARTING_POS_X = 286;
    public static final int STARTING_POS_Y = 646;
    public static final Vector2[] ENTRIENCES = new Vector2[]{new Vector2(2113, 1428), new Vector2(1195, 2280)};
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
        mapImage = new Image(new Texture("MAP_05.jpg"));
        //}
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_05(Game g) {
        super(g, mapImage);
        bossInstance = false;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[10][7];

        //Group 1
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/1.png", "enemy/map5/1.png", "enemy/map5/1.png", false, "Żniwiarz" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 2
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/2.png", "enemy/map5/2.png", "enemy/map5/2.png", false, "Upadły wilkołak" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 3
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/3.png", "enemy/map5/3.png", "enemy/map5/3.png", false, "Minotaur" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 4
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/4.png", "enemy/map5/4.png", "enemy/map5/4.png", false, "Kasztel" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 5
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/5.png", "enemy/map5/5.png", "enemy/map5/5.png", true, "Cybris" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 6
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/6.png", "enemy/map5/6.png", "enemy/map5/6.png", true, "Monoris" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 7
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/7.png", "enemy/map5/7.png", "enemy/map5/7.png", false, "Upadły" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 8
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/8.png", "enemy/map5/8.png", "enemy/map5/8.png", false, "Glomer" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 9
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/9.png", "enemy/map5/9.png", "enemy/map5/9.png", false, "Zombie siłacz" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");

        //Group 10
        for (int i = 0; i < 7; i++) {
            enemies[0][i] = new Enemy("enemy/map5/10.png", "enemy/map5/10.png", "enemy/map5/10.png", false, "Diabeuza" ,1 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
            enemies[0][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
        }
        enemies[0][0].setPosition(0, 0);
        enemies[0][0].setDropItemName("");
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
        entriaceToMapRectangle.add(new Rectangle(145, 570, 100, 100));
        indexToLoadNextMap.add(3);
        entriencesPosition.add(new Vector2(2135, 1491));
        //second entrience
        entriaceToMapRectangle.add(new Rectangle(1100,2300,150,100));
        indexToLoadNextMap.add(5);
        entriencesPosition.add(new Vector2(Map_06.STARTING_POS_X, Map_06.STARTING_POS_Y));
        //third entrience
        entriaceToMapRectangle.add(new Rectangle(2187,1370, 100, 200));
        indexToLoadNextMap.add(9);
        entriencesPosition.add(new Vector2(MapBoss_04.STARTING_POS_X, MapBoss_04.STARTING_POS_Y));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{178,624,276,645,242,835,282,856,274,916,276,1052,323,1054,319,1161,288,1188,347,1212,349,1276,321,1369,299,1361,303,1498,380,1544,373,1650,311,1723,341,1746,341,1790,314,1817,318,1890,389,1920,391,2007,305,2078,344,2142,413,2199,448,2170,507,2226,537,2192,601,2241,726,2207,833,2233,903,2200,1020,2215,1081,2193,1148,2233,1107,2304,430,2304,215,2152});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(171, 617), new Vector2(284,642), new Vector2(289,852), new Vector2(329,1048), new Vector2(326,1164), new Vector2(352,1205), new Vector2(357,1282), new Vector2(326, 1383), new Vector2(386, 1538), new Vector2(383,1654), new Vector2(347, 1739), new Vector2(348,1794), new Vector2(396,1912), new Vector2(400,2012), new Vector2(448,2162), new Vector2(536,2187), new Vector2(726,2201), new Vector2(902,2195), new Vector2(1080,2185), new Vector2(1156,2229)});
        addObjectCollision(new float[]{1218,2311,1223,2273,1546,2190,1570,2207,1617,2216,1615,2274,1657,2291,1666,2237,1710,2245,1714,2280,1806,2248,1859,2276,1904,2215,1932,2222,1936,2285,1977,2295,1971,2262,2006, 2230,2107,2213,2162,2237,2188,2192,2154,2176,2142,2098,2209,2034,2199,1973,2139,1871,2153,1830,2047,1756,2040,1506,2103,1470,2143,1506,2195,1569,2291,2307});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1216,2267), new Vector2(1543,2183), new Vector2(1648,2220), new Vector2(1717,2234), new Vector2(1805,2238), new Vector2(1900,2207), new Vector2(1937,2214), new Vector2(2000,2220), new Vector2(2120,2181), new Vector2(2132,2092), new Vector2(2127,1872), new Vector2(2033,1761), new Vector2(2030,1499), new Vector2(2101,1458)});
        addObjectCollision(new float[]{2205,1511,2191,1380,2158,1394,2109,1385,2106,1297,2155,1285,2127,1193,2134,1134,2109,1079,2107,1038,2099,960,2124,937,2101,863,2168,802,2155,722,2119,715,2116,669,2089,628,2080,572,2152,553,2143,477,2117,428,2095,420,2048,450,2020,446,2016,411,1968,407,1936,388,1887,304,1820,358,1763,332,1750,291,1692,248,1558,267,1438,353,1378,328,1364,286,1337,282,1261,320,1179,322,1158,365,1057,412,1051,468,903,510,894,459,857,432,784,463,767,409,746,382,742,329,810,304,804,260,748,258,629,337,553,308,501,317,439,374,358,381,333,426,286,504,281,580,241,586,213,266,904,181,1657,176,2186,257});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(2220, 1540), new Vector2(2193, 1530), new Vector2(2157,1408), new Vector2(2100,1393), new Vector2(2094,1290), new Vector2(2114,1193), new Vector2(2096,1078), new Vector2(2087,955), new Vector2(2089,859), new Vector2(2110,723), new Vector2(2077,630), new Vector2(2070,567), new Vector2(2050,460), new Vector2(2011,453), new Vector2(1962,417), new Vector2(1928,394), new Vector2(1822,369), new Vector2(1753,343), new Vector2(1440,363), new Vector2(1372,336), new Vector2(1261,332), new Vector2(1164,375), new Vector2(1058,476), new Vector2(897,521), new Vector2(780,472), new Vector2(735,387), new Vector2(733,325), new Vector2(630,347), new Vector2(446,383), new Vector2(288,589), new Vector2(233, 595)});
        addObjectCollision(new float[]{1061,1103,1058,1059,1157,993,1178,671,1209,655,1238,674,1255,635,1330,603,1467,621,1563,665,1613,707,1658,773,1842,836,1863,879,1887,899,1899,940,1946,964,1966,1003,1961,1033,2007,1051,2016,1077,1928,1192,1881,1203,1867,1256,1833,1262,1837,1300,1860,1332,1912,1345,1915,1404,1900,1450,1820,1506,1775,1506,1757,1524,1775,1575,1772,1642,1747,1711,1713,1748,1668,1750,1590,1800,1568,1834,1540,1833,1496,1808,1483,1792,1447,1815,1411,1811,1409,1773,1358,1776,1330,1750,1266,1730,1224,1660,1200,1691,1174,1689,1155,1636,1115,1628,1084,1591,1021,1545,957,1537,923,1491,868,1505,850,1450,804,1446,769,1380,724,1333,723,1293,804,1272,999,1139,1041,1163,1030,1194,1057,1216,1065,1319,1103,1339,1116,1390,1169,1401,1319,1308,1354,1325,1454,1260,1573,1331,1589,1300,1530,1210,1530,1175,1606,1125,1453,1076,1419,1024,1349,1026,1308,1013,1267,1043,1272,1137,1251,1187,1170,1146,1162,1077,1115,1104});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1166,668), new Vector2(1208,650), new Vector2(1249,628), new Vector2(1326,594), new Vector2(1469,613), new Vector2(1567,656), new Vector2(1620,703), new Vector2(1846,829), new Vector2(1892,892), new Vector2(1949,959), new Vector2(1971,1001), new Vector2(2011,1045), new Vector2(2025,1079), new Vector2(1932,1199), new Vector2(1871,1265), new Vector2(1918,1341), new Vector2(1923,1407), new Vector2(1908,1459), new Vector2(1820,1517), new Vector2(1783,1572), new Vector2(1780,1643), new Vector2(1754,1717), new Vector2(1716,1756), new Vector2(1571,1843), new Vector2(1536,1841), new Vector2(1492,1816), new Vector2(1451,1824), new Vector2(1404,1817), new Vector2(1354,1784), new Vector2(1261,1739), new Vector2(1203,1699), new Vector2(1169,1697), new Vector2(1111,1637), new Vector2(954,1544), new Vector2(865,1513), new Vector2(798,1454), new Vector2(712,1336), new Vector2(715,1289), new Vector2(998,1132), new Vector2(1050,1158), new Vector2(1066,1214), new Vector2(1108,1335), new Vector2(1316,1302), new Vector2(1453,1252), new Vector2(1516,1210), new Vector2(1512,1172), new Vector2(1447,1084), new Vector2(1345,1034), new Vector2(1279,1138), new Vector2(1253,1196), new Vector2(1160,1150), new Vector2(1116,1115), new Vector2(1054,1109), new Vector2(1051,1057)});
        addObjectCollision(new float[]{716,1849,744,1879,826,1893,858,1926,852,1960,814,1991,835,2005,950,1952,962,1987,951,2020,801,2094,782,2090,553,1974,551,1918});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(716,1844), new Vector2(827,1886), new Vector2(865,1926), new Vector2(859,1965), new Vector2(950,1946), new Vector2(970,1987), new Vector2(956,2026), new Vector2(794,2109), new Vector2(539,1982), new Vector2(544,1912)});
        addObjectCollision(new float[]{1696,2039,1711,2004,1775,1971,1875,1999,1912,2047,1912,2070,1876,2101,1843,2159,1791,2163,1757,2144,1717,2084,1700,2048});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1775,1965), new Vector2(1878,1992), new Vector2(1919,2042), new Vector2(1919,2076), new Vector2(1846,2169), new Vector2(1787,2170), new Vector2(1749,2149), new Vector2(1687,2042), new Vector2(1704,1999)});
    }

    @Override
    public void generateMap() {
        if(!firstRun) {
            characters = new ArrayList<Character>();
            actualMap = this;
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
            pref.putInteger("MAP", 4).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 4).flush();
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
        try {
            super.dispose();
        }catch (IllegalArgumentException e){
            FileHandle file = Gdx.files.external("MyFileaLog.txt");
            file.writeString(e.toString(), true);
        }
    }
}
