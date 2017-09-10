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
    public static final String NAME = "Canavar";
    public static final int STARTING_POS_X = 1352;
    public static final int STARTING_POS_Y = 360;
    private static final Image bgFight = new Image(new Texture(Gdx.files.internal("pustynia.jpg")));
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Vector2> positionOptimalise;
    private static ArrayList<boolean[]> arrayBoolean;
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
        Enemy[][] enemies = new Enemy[10][];

        //Group 1
        int countEnemy = 6;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map4/p1.png", "enemy/map4/1.png", "enemy/map4/w1.png", true, "Zombie dzik" ,50 ,      1900, 125, 105, 14.5f, 110, 100,        11.1f, 5760, 1270, 190);//740
            enemies[0][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("wapons17", "armor9");
        }
        enemies[0][0].setPosition(1431, reversePosY(2041));
        enemies[0][1].setPosition(1326, reversePosY(2097));
        enemies[0][2].setPosition(1734, reversePosY(2034));
        enemies[0][3].setPosition(1605, reversePosY(2139));
        enemies[0][4].setPosition(1887, reversePosY(2049));
        enemies[0][5].setPosition(1770, reversePosY(2139));

        //Group 2
        countEnemy = 6;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map4/p2.png", "enemy/map4/2.png", "enemy/map4/w2.png", true, "Umarlak" ,52 ,       2000, 125, 130, 13.5f, 110, 110,      10.6f, 6050, 1373, 210);//775
            enemies[1][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("helmet10", "ring8");
        }
        enemies[1][0].setPosition(1753, reversePosY(1899));
        enemies[1][1].setPosition(1882, reversePosY(1852));
        enemies[1][2].setPosition(1929, reversePosY(1753));
        enemies[1][3].setPosition(1900, reversePosY(1690));
        enemies[1][4].setPosition(1597, reversePosY(1465));

        //Group 3
        countEnemy = 7;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map4/p3.png", "enemy/map4/3.png", "enemy/map4/w3.png", true, "Dinotop" ,54 ,          2000, 125, 130, 13.5f, 110, 110,      11.8f, 6090, 1480, 205);//800
            enemies[2][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("wapons18", "wapons19");
        }
        enemies[2][0].setPosition(1485, reversePosY(1696));
        enemies[2][1].setPosition(1693, reversePosY(1560));
        enemies[2][2].setPosition(1432, reversePosY(1467));
        enemies[2][3].setPosition(1368, reversePosY(1609));
        enemies[2][4].setPosition(1260, reversePosY(1717));
        enemies[2][5].setPosition(1099, reversePosY(1785));
        enemies[2][6].setPosition(1164, reversePosY(1644));

        //Group 4
        countEnemy = 5;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map4/p4.png", "enemy/map4/4.png", "enemy/map4/w4.png", false, "Gonomet" ,55 ,      2300, 120, 100, 14f, 125, 100,         10.4f, 6135, 1535, 220);//805
            enemies[3][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("pants7", "shoes7");
        }
        enemies[3][0].setPosition(925, reversePosY(1903));
        enemies[3][1].setPosition(757, reversePosY(1827));
        enemies[3][2].setPosition(904, reversePosY(1687));
        enemies[3][3].setPosition(657, reversePosY(2023));
        enemies[3][4].setPosition(558, reversePosY(1835));

        //Group 5
        countEnemy = 5;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map4/p5.png", "enemy/map4/5.png", "enemy/map4/5.png", false, "Bevter" ,57 ,        2450, 150, 100, 12f, 130, 115,      10.9f, 6215, 1648, 236);//845
            enemies[4][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("armor10", "helmet11");
        }
        enemies[4][0].setPosition(661, reversePosY(1656));
        enemies[4][1].setPosition(744, reversePosY(1489));
        enemies[4][2].setPosition(544, reversePosY(1495));
        enemies[4][3].setPosition(448, reversePosY(1671));
        enemies[4][4].setPosition(1290, reversePosY(1299));

        //Group 6
        countEnemy = 8;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map4/p6.png", "enemy/map4/6.png", "enemy/map4/w6.png", false, "Skvergan" ,58 ,      2700, 100, 150, 10f, 100, 145,      11.2f, 6250, 1706, 263);//850
            enemies[5][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("rekawice8", "ring9");
        }
        enemies[5][0].setPosition(438, reversePosY(978));
        enemies[5][1].setPosition(601, reversePosY(1093));
        enemies[5][2].setPosition(411, reversePosY(1144));
        enemies[5][3].setPosition(1234, reversePosY(826));
        enemies[5][4].setPosition(556, reversePosY(1287));
        enemies[5][5].setPosition(783, reversePosY(1165));
        enemies[5][6].setPosition(430, reversePosY(1932));
        enemies[5][7].setPosition(708, reversePosY(829));

        //Group 7
        countEnemy = 7;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/map4/p7.png", "enemy/map4/7.png", "enemy/map4/w7.png", false, "Muzovert" ,60 ,     2800, 110, 160, 11f, 100, 125,        10.5f, 6750, 1825,258);//870
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("wapons20", "ring10");
        }
        enemies[6][0].setPosition(394, reversePosY(658));
        enemies[6][1].setPosition(534, reversePosY(583));
        enemies[6][2].setPosition(463, reversePosY(2080));
        enemies[6][3].setPosition(1266, reversePosY(673));
        enemies[6][4].setPosition(597, reversePosY(478));
        enemies[6][5].setPosition(417, reversePosY(487));
        enemies[6][6].setPosition(768, reversePosY(426));

        //Group 8
        countEnemy = 6;
        enemies[7] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[7][i] = new Enemy("enemy/map4/p8.png", "enemy/map4/8.png", "enemy/map4/w8.png", false, "Skarab" ,62 ,       3000, 135, 135, 11f, 100, 120,       11, 7150, 1948, 271);//885
            enemies[7][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[7][i]);
            enemies[7][i].setDropItemName("wapons21", "armoe11");
        }
        enemies[7][0].setPosition(1066, reversePosY(427));
        enemies[7][1].setPosition(1287, reversePosY(412));
        enemies[7][2].setPosition(1213, reversePosY(538));
        enemies[7][3].setPosition(1023, reversePosY(555));
        enemies[7][4].setPosition(837, reversePosY(534));
        enemies[7][5].setPosition(1092, reversePosY(697));

        //Group 9
        countEnemy = 7;
        enemies[8] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[8][i] = new Enemy("enemy/map4/p9.png", "enemy/map4/9.png", "enemy/map4/w9.png", false, "Krather" ,64 ,      3600, 170, 120, 10f, 140, 110,         10.1f, 7500, 2075, 249);//1000
            enemies[8][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[8][i]);
            enemies[8][i].setDropItemName("pants8", "rekawice9");
        }
        enemies[8][0].setPosition(1405, reversePosY(595));
        enemies[8][1].setPosition(1642, reversePosY(777));
        enemies[8][2].setPosition(1522, reversePosY(685));
        enemies[8][3].setPosition(1437, reversePosY(816));
        enemies[8][4].setPosition(1921, reversePosY(609));
        enemies[8][5].setPosition(1632, reversePosY(1047));
        enemies[8][6].setPosition(1569, reversePosY(532));

        //Group 10
        countEnemy = 8;
        enemies[9] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[9][i] = new Enemy("enemy/map4/p10.png", "enemy/map4/10.png", "enemy/map4/w10.png", true, "Verdos" ,65 ,     3500, 180, 135, 10f, 140, 120,          10.4f, 7580, 2140, 264);//1025
            enemies[9][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[9][i]);
            enemies[9][i].setDropItemName("ring11", "tarcza7", "shoes8", "helmet12");
        }
        enemies[9][0].setPosition(1860, reversePosY(478));
        enemies[9][1].setPosition(1467, reversePosY(406));
        enemies[9][2].setPosition(1738, reversePosY(540));
        enemies[9][3].setPosition(1780, reversePosY(670));
        enemies[9][4].setPosition(1654, reversePosY(421));
        enemies[9][5].setPosition(2023, reversePosY(930));
        enemies[9][6].setPosition(1809, reversePosY(1108));
        enemies[9][7].setPosition(1920, reversePosY(1200));

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                enemy.collisionUpdate();
            }
        }
    }

    @Override
    public void addNpcToMap() {
        Npc npc9 = new Npc(new Texture(Gdx.files.internal("npc/10.png")), new Image(new Texture(Gdx.files.internal("npc/10h.png"))), "Tumvill", 55, 3, 5, 9);
        npc9.setPosition(937, 1075);
        characters.add(npc9);
        npc9.setRectangle(8, 2, -16, -8);
        npc9.collisionUpdate();

        Npc npc10 = new Npc(new Texture(Gdx.files.internal("npc/11.png")), new Image(new Texture(Gdx.files.internal("npc/11h.png"))), "Simza", 57, 3, 6, 10);
        npc10.setPosition(1031, 1473);
        characters.add(npc10);
        npc10.setRectangle(8, 2, -16, -8);
        npc10.collisionUpdate();

        Npc npc11 = new Npc(new Texture(Gdx.files.internal("npc/12.png")), new Image(new Texture(Gdx.files.internal("npc/12h.png"))), "Tahar", 64, 3, 7, 11);
        npc11.setPosition(1944, 1626);
        characters.add(npc11);
        npc11.setRectangle(8, 2, -16, -8);
        npc11.collisionUpdate();
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
        positionOptimalise = new ArrayList<Vector2>();
        arrayBoolean = new ArrayList<boolean[]>();

        addObjectCollision(new float[]{927,246,956,323,1020,330,1065,383,1134,425,1196,436,1257,478,1394,563,1445,592,1493,637,1538,617,1572,638,1584,690,1515,814,1473,843,1436,848,1374,840,1363,808,1331,799,1285,730,1252,762,1219,720,1184,722,1110,625,1070,626,1015,584,962,569,923,569,885,539,837,475,795,462,725,367,709,414,657,435,578,462,560,452,525,357,303,441,336,594,332,667,285,715,289,785,378,821,368,947,375,996,318,1187,402,1217,350,1534,404,1592,308,1682,320,1728,213,1871,227,338,678,242});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(326,1731), new Vector2(413,1594), new Vector2(408,1213), new Vector2(384,995), new Vector2(384,816), new Vector2(342, 669), new Vector2(346, 588), new Vector2(571, 477), new Vector2(715,423), new Vector2(789,471), new Vector2(920,585), new Vector2(1064,634), new Vector2(1180,730), new Vector2(1250,773), new Vector2(1368,851), new Vector2(1440,863), new Vector2(1478,853), new Vector2(1521,824), new Vector2(1592,694), new Vector2(1580,633),new Vector2(1540,609) ,new Vector2(1201,422) ,new Vector2(1025,322)});
        positionOptimalise.add(new Vector2(426, BaseMap.reversePosY(1617)));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{1323,297,1436,328,1489,310,1654,342,1707,295,1743,309,1792,253,1831,337,1877,353,1925,305,1978,315,1991,349,2025,459,2052,553,1970,644,2037,771,2008,814,2047,924,1979,951,1962,1015,1871,1011,1697,1140,1620,1169,1590,1137,1534,1146,1481,1183,1465,1262,1401,1274,1355,1347,1371,1422,1470,1550,1631,1501,1702,1471,1722,1468,1783,1402,1939,1272,1966,1273,2013,1208,2006,1308,2037,1450,2090,1463,2126,1397,2177,1434,2187,308,1629,185});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1317,307), new Vector2(1435,342), new Vector2(1654,358), new Vector2(1744,320), new Vector2(1823,347), new Vector2(1878,366), new Vector2(1959,644), new Vector2(1996,816), new Vector2(1971,945), new Vector2(1865,1005), new Vector2(1592,1131), new Vector2(1529,1140), new Vector2(1472,1179), new Vector2(1395,1265), new Vector2(1342,1346), new Vector2(1360,1428), new Vector2(1469,1562), new Vector2(1729,1481), new Vector2(1968,1287), new Vector2(2029, 1462), new Vector2(2093,1477)});
        positionOptimalise.add(new Vector2(1317, BaseMap.reversePosY(2166)));     arrayBoolean.add(new boolean[]{false, true});

        addObjectCollision(new float[]{2210,1518,2147,1541,2120,1681,2102,2076,2024,2214,1854,2214,1777,2225,1756,2262,1723,2254,1677,2213,1541,2238,1457,2243,1416,2258,1353,2261,1332,2208,1291,2205,1276,2250,1089,2187,1006, 2238, 955, 2215, 832, 2225,790,2258,739,2256,732,2213,611,2199,593,2151,543,2141,428,2197,393,2076,333,1985,281,1935,256,2286,2216,2328});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(2139,1536), new Vector2(2110,1679), new Vector2(1772,2214), new Vector2(1678,2203), new Vector2(1455,2235), new Vector2(1284,2200), new Vector2(1088,2182), new Vector2(951,2208), new Vector2(828,2216), new Vector2(737,2208), new Vector2(600,2146), new Vector2(538,2133), new Vector2(401,2071), new Vector2(338,1980)});
        positionOptimalise.add(new Vector2(2052, BaseMap.reversePosY(627)));     arrayBoolean.add(new boolean[]{false, false});

        addObjectCollision(new float[]{1912,1640,2050,1705,2057,1821,2017,1898,1989,1901,1776,1791,1783,1681,1870,1635});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1868,1630), new Vector2(1912,1632), new Vector2(2059,1702), new Vector2(2064,1824), new Vector2(2022,1909), new Vector2(1990,1914), new Vector2(1765,1795), new Vector2(1771,1677)});
        positionOptimalise.add(new Vector2(1704, -1));     arrayBoolean.add(new boolean[]{false, true});

        addObjectCollision(new float[]{924,1752,993,1775,1024,1790,1025,1857,991,1933,963,1962,917,1955,904,1928,828,1911,822,1821,840,1807});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1031,1788), new Vector2(1031,1861), new Vector2(998,1940), new Vector2(965,1973), new Vector2(912,1963), new Vector2(820,1918), new Vector2(813,1814), new Vector2(921,1744)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(825)));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{1139,1466,1264,1533,1264,1643,1164,1702,1108,1773,1082,1777,867,1669,878,1558,990,1501,1040,1520});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(870,1555), new Vector2(990,1494), new Vector2(1137,1458), new Vector2(1271,1529), new Vector2(1271,1649), new Vector2(1112,1788), new Vector2(1079,1788), new Vector2(852,1677)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(1089)));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{1000,1087,1043,1086,1128,1131,1136,1227,922,1338,863,1325,744,1340,699,1327,699,1211,742,1181,819,1173,854,1175,871,1149});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(688,1336), new Vector2(689,1208), new Vector2(738,1173), new Vector2(865,1143), new Vector2(999,1079), new Vector2(1045,1078), new Vector2(1136,1127), new Vector2(1145,1235), new Vector2(920,1351), new Vector2(746,1352)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(1494)));     arrayBoolean.add(new boolean[]{true, false});
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

        mapView = new Image(new Texture("map04View.jpg"));
        mapName = NAME;

        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
        optimiseMap = positionOptimalise;
        optimiseToward = arrayBoolean;
        charactersList = characters;
        backgroundFight = bgFight;

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

    public ArrayList<Character> getCharacter(){
        return characters;
    }

    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public String getName(){
        return NAME;
    }
}
