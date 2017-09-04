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

public class Map_02 extends BaseMap {
    public static final String NAME = "Erendia";
    public static final int STARTING_POS_X = 1251;
    public static final int STARTING_POS_Y = 347;
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
        mapImage = new Image(new Texture("MAP_02.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_02(Game g) {
        super(g, mapImage);
        bossInstance = false;
        deadPosX = STARTING_POS_X;
        deadPosY =STARTING_POS_Y;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[10][];

        //Group 1
        int countEnemy = 6;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map2/p1.png", "enemy/map2/1.png", "enemy/map2/w1.png", false, "Emon" ,18 ,     450, 50, 37, 5f, 42, 32,       15, 590, 373, 120);//230
            enemies[0][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("helmet4", "wapons5");
        }
        enemies[0][0].setPosition(1529, 545);
        enemies[0][1].setPosition(1417, 537);
        enemies[0][2].setPosition(1448, 471);
        enemies[0][3].setPosition(1113, 701);
        enemies[0][4].setPosition(1404, 399);
        enemies[0][5].setPosition(1116, 566);

        //Group 2
        countEnemy = 6;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map2/p2.png", "enemy/map2/2.png", "enemy/map2/w2.png", true, "Zemund" ,20 ,        460, 38, 50, 5f, 39, 43,       14.4f, 760, 430, 125);//240
            enemies[1][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("wapons6", "ring4");
        }
        enemies[1][0].setPosition(1800, 575);
        enemies[1][1].setPosition(1677, 560);
        enemies[1][2].setPosition(1874, 470);
        enemies[1][3].setPosition(1673, 451);
        enemies[1][4].setPosition(1776, 414);
        enemies[1][5].setPosition(1953, 881);

        //Group 3
        countEnemy = 6;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map2/p3.png", "enemy/map2/3.png", "enemy/map2/w3.png", true, "Grykon" ,21 ,        500, 52, 38, 5f, 42, 44,       14.8f, 955, 460, 125);//250
            enemies[2][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("ring4", "wapons6");
        }
        enemies[2][0].setPosition(1371, 696);
        enemies[2][1].setPosition(1543, 884);
        enemies[2][2].setPosition(1676, 806);
        enemies[2][3].setPosition(1395, 846);
        enemies[2][4].setPosition(1512, 771);
        enemies[2][5].setPosition(328, 520);

        //Group 4
        countEnemy = 5;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map2/p4.png", "enemy/map2/4.png", "enemy/map2/w4.png", true, "Tratogon" ,23 ,      700, 64, 45, 7f, 56, 50,        13.2f, 1230, 523, 133);//325
            enemies[3][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons7", "armor4");
        }
        enemies[3][0].setPosition(322, 672);
        enemies[3][1].setPosition(422, 756);
        enemies[3][2].setPosition(478, 666);
        enemies[3][3].setPosition(512, 552);
        enemies[3][4].setPosition(1678, 978);

        //Group 5
        countEnemy = 6;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map2/p5.png", "enemy/map2/5.png", "enemy/map2/w5.png", true, "Ognisty wilk" ,25 ,      750, 59, 60, 7.6f, 50, 60,       13, 1850, 590, 136);//345
            enemies[4][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("pants3", "rekawice4", "shoes3");

        }
        enemies[4][0].setPosition(1494, 990);
        enemies[4][1].setPosition(416, 972);
        enemies[4][2].setPosition(636, 1060);
        enemies[4][3].setPosition(540, 860);
        enemies[4][4].setPosition(780, 978);
        enemies[4][5].setPosition(872, 738);

        //Group 6
        countEnemy = 6;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map2/p6.png", "enemy/map2/6.png", "enemy/map2/w6.png", false, "Stwór ziemny" ,27 ,     770, 67, 50, 8.1f, 70, 50,       15, 2545, 661, 143);//360
            enemies[5][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("wapons8", "helmet5");
        }
        enemies[5][0].setPosition(958, 998);
        enemies[5][1].setPosition(1094, 952);
        enemies[5][2].setPosition(1138, 1170);
        enemies[5][3].setPosition(890, 1128);
        enemies[5][4].setPosition(960, 834);
        enemies[5][5].setPosition(1448, 1088);

        //Group 7
        countEnemy = 7;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/map2/p7.png", "enemy/map2/7.png", "enemy/map2/w7.png", false, "Czerwony trol" ,28 ,        800, 60, 64, 8.6f, 60, 55,       13.7f, 2725, 698, 145);//370
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("ring5", "wapons8");
        }
        enemies[6][0].setPosition(680, 1250);
        enemies[6][1].setPosition(352, 1184);
        enemies[6][2].setPosition(522, 1342);
        enemies[6][3].setPosition(784, 1386);
        enemies[6][4].setPosition(492, 1508);
        enemies[6][5].setPosition(802, 2060);
        enemies[6][6].setPosition(1546, 2016);

        //Group 8
        countEnemy = 7;
        enemies[7] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[7][i] = new Enemy("enemy/map2/p8.png", "enemy/map2/8.png", "enemy/map2/w8.png", false, "Wilkołak" ,30 ,     880, 80, 64, 8.8f, 60, 55,       12.9f, 2870, 775, 155);//400
            enemies[7][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[7][i]);
            enemies[7][i].setDropItemName("armor5", "helmet6", "wapons9");
        }
        enemies[7][0].setPosition(368, 1418);
        enemies[7][1].setPosition(682, 1510);
        enemies[7][2].setPosition(1568, 1404);
        enemies[7][3].setPosition(1200, 1428);
        enemies[7][4].setPosition(1322, 1600);
        enemies[7][5].setPosition(1490, 1608);
        enemies[7][6].setPosition(390, 1870);

        //Group 9
        countEnemy = 7;
        enemies[8] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[8][i] = new Enemy("enemy/map2/p9.png", "enemy/map2/9.png", "enemy/map2/w9.png", true, "Tygrys biały" ,32 ,      900, 85, 62, 9.3f, 70, 60,        13.5f, 2900, 856, 156);//425
            enemies[8][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[8][i]);
            enemies[8][i].setDropItemName("wapons10", "pants4");
        }
        enemies[8][0].setPosition(1390, 1472);
        enemies[8][1].setPosition(1386, 2126);
        enemies[8][2].setPosition(1236, 2070);
        enemies[8][3].setPosition(1174, 1984);
        enemies[8][4].setPosition(534, 1788);
        enemies[8][5].setPosition(1388, 1996);
        enemies[8][6].setPosition(1576, 1766);

        //Group 10
        countEnemy = 9;
        enemies[9] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[9][i] = new Enemy("enemy/map2/p10.png", "enemy/map2/10.png", "enemy/map2/w10.png", true, "Krabogon" ,33 ,       800, 80, 60, 10.3f, 90, 82,       13.6f, 3365, 898, 160);//460
            enemies[9][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[9][i]);
            enemies[9][i].setDropItemName("rekawice5", "tarcza4", "shoes4", "helmet7");
        }
        enemies[9][0].setPosition(520, 2018);
        enemies[9][1].setPosition(722, 1944);
        enemies[9][2].setPosition(978, 2036);
        enemies[9][3].setPosition(1736, 2100);
        enemies[9][4].setPosition(1904, 1956);
        enemies[9][5].setPosition(1784, 1880);
        enemies[9][6].setPosition(1578, 1884);
        enemies[9][7].setPosition(1716, 1544);
        enemies[9][8].setPosition(552, 1888);

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                enemy.collisionUpdate();
            }
        }
    }

    @Override
    public void addNpcToMap() {
        Npc npc3 = new Npc(new Texture(Gdx.files.internal("npc/4.png")), new Image(new Texture(Gdx.files.internal("npc/4h.png"))), "Herald", 23, 1, 0, 3);
        npc3.setPosition(1300, 1245);
        characters.add(npc3);
        npc3.setRectangle(8, 2, -16, -8);
        npc3.collisionUpdate();

        Npc npc4 = new Npc(new Texture(Gdx.files.internal("npc/5.png")), new Image(new Texture(Gdx.files.internal("npc/5h.png"))), "Lars", 27, 1, 1, 4);
        npc4.setPosition(1260, 1725);
        characters.add(npc4);
        npc4.setRectangle(8, 2, -16, -8);
        npc4.collisionUpdate();

        Npc npc5 = new Npc(new Texture(Gdx.files.internal("npc/6.png")), new Image(new Texture(Gdx.files.internal("npc/6h.png"))), "Marsel", 32, 1, 1, 5);
        npc5.setPosition(2050, 1889);
        characters.add(npc5);
        npc5.setRectangle(8, 2, -16, -8);
        npc5.collisionUpdate();
    }

    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(85, 1700, 100, 120));
        indexToLoadNextMap.add(2);
        entriencesPosition.add(new Vector2(Map_03.STARTING_POS_X, Map_03.STARTING_POS_Y));
        entriaceToMapRectangle.add(new Rectangle(1180, 185, 200, 100));
        indexToLoadNextMap.add(0);
        entriencesPosition.add(new Vector2(1231, 2190));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        positionOptimalise = new ArrayList<Vector2>();
        arrayBoolean = new ArrayList<boolean[]>();

        addObjectCollision(new float[]{2001,956,1825,1056,1817,1488,1843,1762,1990,1842,1999,1906,2056,1908,2068,1832,2195,1836,2151,1884,2130,1956,2160,2012,1845,2054,1866,2325,1756,2317,1757,2226,1647,2196,1554,2253,1354,2221,1305,2179,1192,2225,1131,2204,1005,2144,865,2214,814,2197,652,2190,558,2130,429,2189,322,2161,328,2012,284,1755,253,1734,177,1769,234,2297,1992,2440,2198,2022,2263,1707,2198,940});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1813, 1042), new Vector2(1803, 1505), new Vector2(1830, 1772), new Vector2(1993, 1919), new Vector2(2062, 1917), new Vector2(1833, 2043), new Vector2(1765, 2217), new Vector2(1644, 2190), new Vector2(1305, 2172), new Vector2(1003, 2137), new Vector2(558, 2124), new Vector2(336, 2004), new Vector2(293, 1739), new Vector2(251, 1726), new Vector2(170, 1730)});
        positionOptimalise.add(new Vector2(1782, BaseMap.reversePosY(831)));     arrayBoolean.add(new boolean[]{false, true});

        addObjectCollision(new float[]{1312,251,1302,380,1388,374,1525,283,1485,342,1475,418,1546,527,1606,516,1694,403,1813,300,1893,476,1977,456,2030,507,1908,618,1910,675,1990,770,2094,775,2120,817,2081,849,2073,944,2199,933, 2176,642,1850,218});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1293,389), new Vector2(1391,388), new Vector2(1463, 417), new Vector2(1542, 540), new Vector2(1611, 527), new Vector2(1889, 488), new Vector2(1890, 613), new Vector2(1900,679), new Vector2(1983, 780)});
        positionOptimalise.add(new Vector2(1872, BaseMap.reversePosY(1935)));     arrayBoolean.add(new boolean[]{false, false});

        addObjectCollision(new float[]{1186,276,1039,403,1145,403,1151,510,1116,579,1012,628,949,620,873,530,865,460,971,352,860,355,756,461,645,490,560,472,458,545,354,326,260,630,277,760,284,956,307,1066,317,1700,161,1705,132,302,380,229});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(170, 1750), new Vector2(327, 1714), new Vector2(318, 1064), new Vector2(455, 559), new Vector2(643, 545), new Vector2(853, 453), new Vector2(860, 535), new Vector2(940, 634), new Vector2(1015, 643), new Vector2(1124, 592), new Vector2(1159, 516), new Vector2(1153, 400)});
        positionOptimalise.add(new Vector2(360, BaseMap.reversePosY(1860)));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{729,596,842,682,756,803,702,823,622,734,616,678,639,619});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(730, 589), new Vector2(856, 678), new Vector2(760, 815), new Vector2(700, 833), new Vector2(612, 737), new Vector2(607, 670), new Vector2(630, 610)});
        positionOptimalise.add(new Vector2(-1, 846));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{542,1078,659,1179,555,1304,513,1311,437,1219,426,1091});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(543, 1071), new Vector2(671, 1178), new Vector2(556, 1316), new Vector2(509, 1320), new Vector2(425, 1223), new Vector2(416, 1083)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(1200)));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{602,954,691,978,691,978,690,1024,633,1052,572,997,572,957});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(566, 948), new Vector2(696, 970), new Vector2(697, 1032), new Vector2(632, 1062), new Vector2(563, 1001)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(1450)));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{1439,1206,1586,1174,1585,1124,1632,1118,1710,1161,1711,1276,1591,1400,1533,1391,1353,1475,1299,1428,1213,1348,1292,1273,1361,1333,1351,1251});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1603, 1105), new Vector2(1634, 1111), new Vector2(1718, 1154), new Vector2(1719, 1284), new Vector2(1591, 1408), new Vector2(1351, 1488), new Vector2(1200, 1349), new Vector2(1299, 1248)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(1431)));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{1692,1927,1754,1954,1781,2038,1750,2082,1711,2078,1666,2036,1645,1940});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1691, 1919), new Vector2(1759, 1947), new Vector2(1788, 2039), new Vector2(1753, 2093), new Vector2(1707, 2087), new Vector2(1656, 2040), new Vector2(1636, 1937)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(627)));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{1383,1679,1556,1769,1482,1831,1482,1900,1379,1960,1359,2002,1335,2010,1293,1987,1228,1873,1160,1838,1171,1786,1248,1754,1290,1776,1302,1715});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1384, 1674), new Vector2(1573, 1768), new Vector2(1488, 1907), new Vector2(1350, 2021), new Vector2(1285, 2004), new Vector2(1148, 1843), new Vector2(1162, 1782), new Vector2(1289, 1713)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(879)));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{875,1741,1075,1854,1081,1983,995,2049,928,2019,857,2064,800,2026,680,1951,670,1850});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(875, 1733), new Vector2(1083, 1846), new Vector2(1089, 1989), new Vector2(995, 2062), new Vector2(855, 2074), new Vector2(668, 1957), new Vector2(658, 1846)});
        positionOptimalise.add(new Vector2(-1, BaseMap.reversePosY(840)));     arrayBoolean.add(new boolean[]{true, false});
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

        mapView = new Image(new Texture("map02View.jpg"));//TODO
        mapName = NAME;

        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
        optimiseMap = positionOptimalise;
        optimiseToward = arrayBoolean;
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
            pref.putInteger("MAP", 1).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 1).flush();
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
