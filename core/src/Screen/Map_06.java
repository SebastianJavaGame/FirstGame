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

public class Map_06 extends BaseMap {
    public static final int STARTING_POS_X = 1201;
    public static final int STARTING_POS_Y = 296;
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_06.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_06(Game g) {
        super(g, mapImage);
        bossInstance = false;
        deadPosX = STARTING_POS_X;
        deadPosY = STARTING_POS_Y;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[10][7];

        //Group 1
        int countEnemy = 7;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map6/p1.png", "enemy/map6/1.png", "enemy/map6/w1.png", true, "Argona" ,82 ,        4850, 150, 130, 10f, 160, 150,        10, 11380, 3398, 240);//1195
            enemies[0][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
            enemies[0][i].setDropItemName("pants10");
        }
        enemies[0][0].setPosition(1309, reversePosY(2194));
        enemies[0][1].setPosition(1482, reversePosY(2163));
        enemies[0][2].setPosition(1405, reversePosY(2254));
        enemies[0][3].setPosition(934, reversePosY(2139));
        enemies[0][4].setPosition(1030, reversePosY(2169));
        enemies[0][5].setPosition(891, reversePosY(2224));
        enemies[0][6].setPosition(1054, reversePosY(2274));

        //Group 2
        countEnemy = 7;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map6/p2.png", "enemy/map6/2.png", "enemy/map6/w2.png", true, "Santria" ,85 ,       4950, 160, 150, 12f, 160, 160,       9.6f, 11730, 3650, 245);//1265
            enemies[1][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[1][i]);
            enemies[1][i].collisionUpdate();
            enemies[1][i].setDropItemName("helmet15", "rekawice12");
        }
        enemies[1][0].setPosition(1912, reversePosY(2085));
        enemies[1][1].setPosition(1830, reversePosY(2184));
        enemies[1][2].setPosition(1671, reversePosY(2164));
        enemies[1][3].setPosition(771, reversePosY(2145));
        enemies[1][4].setPosition(651, reversePosY(2218));
        enemies[1][5].setPosition(1014, reversePosY(1896));
        enemies[1][6].setPosition(475, reversePosY(1830));

        //Group 3
        countEnemy = 6;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map6/p3.png", "enemy/map6/3.png", "enemy/map6/w3.png", false, "Hydra" ,87 ,        5100, 150, 200, 12.5f, 130, 150,      10.3f, 11785, 3823, 260);//1275
            enemies[2][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[2][i]);
            enemies[2][i].collisionUpdate();
            enemies[2][i].setDropItemName("ring16", "shoes10");
        }
        enemies[2][0].setPosition(496, reversePosY(2172));
        enemies[2][1].setPosition(459, reversePosY(2074));
        enemies[2][2].setPosition(597, reversePosY(2062));
        enemies[2][3].setPosition(198, reversePosY(2185));
        enemies[2][4].setPosition(358, reversePosY(1912));
        enemies[2][5].setPosition(337, reversePosY(2095));

        //Group 4
        countEnemy = 7;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map6/p4.png", "enemy/map6/4.png", "enemy/map6/w4.png", false, "Harpia" ,90 ,       5000, 200, 190, 13f, 140, 140,     9.4f, 11935, 4090, 270);//1310
            enemies[3][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[3][i]);
            enemies[3][i].collisionUpdate();
            enemies[3][i].setDropItemName("shoes11", "wapons29");
        }
        enemies[3][0].setPosition(1434, reversePosY(1717));
        enemies[3][1].setPosition(1305, reversePosY(1666));
        enemies[3][2].setPosition(1309, reversePosY(1765));
        enemies[3][3].setPosition(1263, reversePosY(1888));
        enemies[3][4].setPosition(1987, reversePosY(1938));
        enemies[3][5].setPosition(903, reversePosY(1617));
        enemies[3][6].setPosition(931, reversePosY(1750));

        //Group 5
        countEnemy = 6;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map6/p5.png", "enemy/map6/5.png", "enemy/map6/w5.png", true, "Gorgona" ,92 ,       5000, 160, 230, 14f, 140, 140,     9.6f, 11940, 4273, 280);//1320
            enemies[4][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[4][i]);
            enemies[4][i].collisionUpdate();
            enemies[4][i].setDropItemName("wapons30", "pants11");
        }
        enemies[4][0].setPosition(706, reversePosY(1606));
        enemies[4][1].setPosition(597, reversePosY(1476));
        enemies[4][2].setPosition(550, reversePosY(1651));
        enemies[4][3].setPosition(408, reversePosY(1552));
        enemies[4][4].setPosition(372, reversePosY(1683));
        enemies[4][5].setPosition(366, reversePosY(1417));

        //Group 6
        countEnemy = 8;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map6/p6.png", "enemy/map6/6.png", "enemy/map6/w6.png", true, "Vassa" ,93 ,     4500, 220, 230, 14f, 140, 140,      9.2f, 11860, 4366, 290);//1330
            enemies[5][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[5][i]);
            enemies[5][i].collisionUpdate();
            enemies[5][i].setDropItemName("tarcza10", "armor17");
        }
        enemies[5][0].setPosition(1969, reversePosY(1309));
        enemies[5][1].setPosition(1807, reversePosY(1590));
        enemies[5][2].setPosition(1683, reversePosY(1500));
        enemies[5][3].setPosition(1495, reversePosY(1554));
        enemies[5][4].setPosition(1525, reversePosY(1419));
        enemies[5][5].setPosition(1918, reversePosY(1485));
        enemies[5][6].setPosition(1920, reversePosY(1705));
        enemies[5][7].setPosition(723, reversePosY(1374));

        //Group 7
        countEnemy = 10;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/map6/p7.png", "enemy/map6/7.png", "enemy/map6/w7.png", true, "Tassan" ,95 ,        5000, 270, 200, 13.5f, 130, 120,        9.5f, 11870, 4555, 300);//1365
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].collisionUpdate();
            enemies[6][i].setDropItemName("helmet16", "ring17");
        }
        enemies[6][0].setPosition(766, reversePosY(1119));
        enemies[6][1].setPosition(916, reversePosY(1092));
        enemies[6][2].setPosition(379, reversePosY(1234));
        enemies[6][3].setPosition(556, reversePosY(1305));
        enemies[6][4].setPosition(795, reversePosY(1242));
        enemies[6][5].setPosition(393, reversePosY(1056));
        enemies[6][6].setPosition(1056, reversePosY(1167));
        enemies[6][7].setPosition(672, reversePosY(924));
        enemies[6][8].setPosition(1636, reversePosY(1266));
        enemies[6][9].setPosition(2077, reversePosY(1032));

        //Group 8
        countEnemy = 10;
        enemies[7] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[7][i] = new Enemy("enemy/map6/p8.png", "enemy/map6/8.png", "enemy/map6/w8.png", false, "Xantes" ,96 ,        4600, 200, 270, 12f, 150, 160,     10, 12166, 4651, 320);//1370
            enemies[7][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[7][i]);
            enemies[7][i].collisionUpdate();
            enemies[7][i].setDropItemName("wapons31", "armor19");
        }
        enemies[7][0].setPosition(1863, reversePosY(1182));
        enemies[7][1].setPosition(1779, reversePosY(1060));
        enemies[7][2].setPosition(1948, reversePosY(1074));
        enemies[7][3].setPosition(1888, reversePosY(936));
        enemies[7][4].setPosition(1459, reversePosY(1105));
        enemies[7][5].setPosition(1345, reversePosY(1038));
        enemies[7][6].setPosition(948, reversePosY(804));
        enemies[7][7].setPosition(397, reversePosY(888));
        enemies[7][8].setPosition(545, reversePosY(829));
        enemies[7][9].setPosition(1050, reversePosY(894));

        //Group 9
        countEnemy = 10;
        enemies[8] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[8][i] = new Enemy("enemy/map6/p9.png", "enemy/map6/9.png", "enemy/map6/w9.png", true, "Selenus" ,98 ,      4500, 255, 240, 12f, 170, 170,       9.1f, 12555, 4846, 310);//1415
            enemies[8][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[8][i]);
            enemies[8][i].collisionUpdate();
            enemies[8][i].setDropItemName("shoes12", "tarcza11", "wapons32");
        }
        enemies[8][0].setPosition(511, reversePosY(621));
        enemies[8][1].setPosition(399, reversePosY(717));
        enemies[8][2].setPosition(681, reversePosY(687));
        enemies[8][3].setPosition(627, reversePosY(568));
        enemies[8][4].setPosition(1686, reversePosY(762));
        enemies[8][5].setPosition(1531, reversePosY(745));
        enemies[8][6].setPosition(1609, reversePosY(844));
        enemies[8][7].setPosition(1717, reversePosY(901));
        enemies[8][8].setPosition(1597, reversePosY(951));
        enemies[8][9].setPosition(783, reversePosY(555));

        //Group 10
        countEnemy = 12;
        enemies[9] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[9][i] = new Enemy("enemy/map6/p10.png", "enemy/map6/10.png", "enemy/map6/w10.png", true, "Raplieton" ,99 ,      6000, 300, 200, 10f, 110, 100,         10, 13333, 4945, 360);//1420
            enemies[9][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[9][i]);
            enemies[9][i].collisionUpdate();
            enemies[9][i].setDropItemName("ring18", "pants12", "armor20", "wapons33");
        }
        enemies[9][0].setPosition(1645, reversePosY(493));
        enemies[9][1].setPosition(1434, reversePosY(540));
        enemies[9][2].setPosition(1339, reversePosY(556));
        enemies[9][3].setPosition(1491, reversePosY(645));
        enemies[9][4].setPosition(1243, reversePosY(526));
        enemies[9][5].setPosition(972, reversePosY(609));
        enemies[9][6].setPosition(1161, reversePosY(475));
        enemies[9][7].setPosition(1057, reversePosY(537));
        enemies[9][8].setPosition(1662, reversePosY(628));
        enemies[9][9].setPosition(1344, reversePosY(685));
        enemies[9][10].setPosition(1189, reversePosY(642));
        enemies[9][11].setPosition(1830, reversePosY(529));
    }

    @Override
    public void addNpcToMap() {
        Npc npc15 = new Npc(new Texture(Gdx.files.internal("npc/16.png")), new Image(new Texture(Gdx.files.internal("npc.16h.png"))), "Bertrand", 90, 5, 11, 15);
        npc15.setPosition(885, 522);
        characters.add(npc15);
        npc15.setRectangle(8, 2, -16, -8);
        npc15.collisionUpdate();

        Npc npc16 = new Npc(new Texture(Gdx.files.internal("npc/17.png")), new Image(new Texture(Gdx.files.internal("npc.17h.png"))), "Nevil", 95, 5, 12, 16);
        npc16.setPosition(1388, 448);
        characters.add(npc16);
        npc16.setRectangle(8, 2, -16, -8);
        npc16.collisionUpdate();
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(1020, 143, 450, 100));
        indexToLoadNextMap.add(4);
        entriencesPosition.add(new Vector2(1199, 2280));
        //second entrience
        entriaceToMapRectangle.add(new Rectangle(1330, 2070, 120, 65));
        indexToLoadNextMap.add(10);
        entriencesPosition.add(new Vector2(MapBoss_05.STARTING_POS_X, MapBoss_05.STARTING_POS_Y));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{1034,236,1006,324,981,328,958,237,921,256,903,247,867,339,847,340,812,237,783,335,753,342,724,247,716,280,691,280,662,238,624,351,593,349,552,248,534,293,510,298,435,363,406,371,377,276,332,283,289,496,259,638,284,657,264,902,306,921,267,1173,306,1191,282,1336,313,1359,283,1544,328,1580,283,1852,368,1889,360,1969,159,1939,203,185});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1011,335), new Vector2(975,336), new Vector2(872,350), new Vector2(840,346), new Vector2(788,344), new Vector2(747,353), new Vector2(685,290), new Vector2(628,358), new Vector2(587,358), new Vector2(537,305), new Vector2(400,382), new Vector2(290,654), new Vector2(312,917), new Vector2(311,1186), new Vector2(318,1355), new Vector2(334,1575), new Vector2(373,1884)});
        addObjectCollision(new float[]{1428,270,1524,242,1546,285,1561,285,1584,243,1618,336,1644,338,1685,238,1729,353,1764,356,1799,240,1841,324,1870,319,1888,233,1921,241,1944,336,1977,336,1994,259,2062,254,2080,317,2107,323,2115,294,2174,311,2155,410,2146,557,2177,698,2141,769,2159,868,2204,1154,2172,1189,2180,1294,2182,1374,2213,1509,2180,1650,2330,1682,2281,182, 1523,190});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(2169,1371), new Vector2(2160,1185), new Vector2(2130,766), new Vector2(2135,559), new Vector2(2109,331), new Vector2(2073,326), new Vector2(1981,347), new Vector2(1937,347), new Vector2(1836,332), new Vector2(1767,367), new Vector2(1722,363), new Vector2(1648,348), new Vector2(1610,346), new Vector2(1565,293), new Vector2(1539,295)});
        addObjectCollision(new float[]{2180,1648,1913,1792,1772,1721,1755,1739,1759,1780,1906,1859,1937,1859,1941,1922,2110,2008,2048,2048,2052,2118,2014,2115,1990,2160,1862,2167,1852,2151,1817,2158,1792,2172,1603,2172,1603,2161,1605,2088,1538,2037,1494,2050,1465,2072,1469,2122,1524,2172,1122,2168,1116,2145,1019,2111,1022,2099,895,2025,703,2141,588,2085,633,2069,618,2022,550,2045,536,2026,481,2029,363,1972,733,2302,2146,2305});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1772,1715), new Vector2(1746,1736), new Vector2(1747,1784), new Vector2(1903,1870), new Vector2(1930,1926), new Vector2(2040,2044), new Vector2(2009,2110), new Vector2(1855,2143), new Vector2(1812,2152), new Vector2(1610,2084), new Vector2(1539,2030), new Vector2(1491,2045), new Vector2(1458,2068), new Vector2(1458,2125), new Vector2(1121,2139), new Vector2(1028,2097), new Vector2(894,2017), new Vector2(614,2074), new Vector2(622,2016), new Vector2(542,2020)});
        addObjectCollision(new float[]{1586,1705,1586,1651,1515,1614,1475,1621,1424,1641,1363,1612,1302,1640,1245,1618,1181,1650,1181,1734,713,1963,722,2010,1194,1797,1234,1822,1257,1821,1308,1795,1348,1821,1434,1797,1476,1824,1495,1823,1539,1795,1548,1700});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(717,2030), new Vector2(701,1958), new Vector2(1166,1643), new Vector2(1244,1606), new Vector2(1361,1598), new Vector2(1472,1613), new Vector2(1514,1604), new Vector2(1595,1644), new Vector2(1594,1715), new Vector2(1547,1802), new Vector2(1496,1832), new Vector2(1471,1833), new Vector2(1347,1831), new Vector2(1257,1831), new Vector2(1231,1832)});
        addObjectCollision(new float[]{745,1494,730,1471,699,1456,669,1447,624,1418,575,1423,550,1449,543,1567,568,1609,597,1609,597,1621,621,1606,689,1573,706,1547,740,1538});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(596,1631), new Vector2(563,1617), new Vector2(534,1569), new Vector2(541,1447), new Vector2(624,1410), new Vector2(729,1455), new Vector2(754,1492), new Vector2(745,1546), new Vector2(692,1580)});
        addObjectCollision(new float[]{1076,979,1116,1008,1126,1058,1165,1073,1186,1092,1195,1114,1197,1162,1222,1194,1184,1393,1155,1401,1122,1316,1094,1226,1042,1388,1009,1382,989,1297,963,1292,906,1069,999,1053,1003,993});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(897,1064), new Vector2(996,988), new Vector2(1075,972), new Vector2(1121,1004), new Vector2(1168,1069), new Vector2(1201,1111), new Vector2(1230,1196), new Vector2(1190,1401), new Vector2(1148,1409), new Vector2(1045,1397), new Vector2(1000,1389), new Vector2(956,1301)});
        addObjectCollision(new float[]{1796,1156,1893,1172,1930,1198,1931,1236,1916,1258,1874,1280,1841,1316,1796,1332,1747,1309,1730,1208,1753,1179});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1793,1148), new Vector2(1892,1164), new Vector2(1938,1196), new Vector2(1937,1238), new Vector2(1918,1267), new Vector2(1849,1326), new Vector2(1795,1342), new Vector2(1738,1316), new Vector2(1722,1206), new Vector2(1746,1175)});
        addObjectCollision(new float[]{1040,628,928,674,923,722,856,816,796,839,762,824,760,786,617,719,558,644,507,619,504,548,566,577,604,566,638,578,761,537,926,616,1028,560});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1047,636), new Vector2(1030,553), new Vector2(761,531), new Vector2(602,561), new Vector2(500,542), new Vector2(496,625), new Vector2(609,727), new Vector2(752,828), new Vector2(794,849), new Vector2(860,825), new Vector2(929,728)});
        addObjectCollision(new float[]{1417,508,1536,454,1571,477,1593,446,1643,437,1680,474,1733,480,1835,539,1906,557,1903,761,1843,797,1842,866,1690,937,1561,873,1557,804,1455,759,1446,714,1391,713,1360,696,1363,564});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1691,949), new Vector2(1849,874), new Vector2(1911,767), new Vector2(1912,551), new Vector2(1734,471), new Vector2(1645,432), new Vector2(1587,438), new Vector2(1536,448), new Vector2(1410,500), new Vector2(1354,562), new Vector2(1353,701), new Vector2(1389,724), new Vector2(1445,765), new Vector2(1550,878)});
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
            pref.putInteger("MAP", 5).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 5).flush();
        }
    }

    private void addObjectCollision(float[] position) {
        objectCollision.add(new Polygon(position));
    }

    private void addVerticalToObjectCollision(Vector2[] point){
        verticalCollision.add(point);
    }

    public ArrayList<Character> getCharacter(){
        return characters;
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
