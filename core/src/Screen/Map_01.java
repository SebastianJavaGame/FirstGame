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

public class Map_01 extends BaseMap {
    public static final String NAME = "Awirdan";
    public static final int STARTING_POS_X = 626;
    public static final int STARTING_POS_Y = 416;
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
        mapImage = new Image(new Texture("MAP_01.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_01(Game g) {
        super(g, mapImage);
        bossInstance = false;
        deadPosX = 700;
        deadPosY = 890;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[10][];

        //Group 1
        int countEnemy = 4;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map1/p1.png", "enemy/map1/1.png", "enemy/map1/w1.png", true, "Grzybox" ,1 ,     30, 4, 4, 0.3f, 2, 2,       25, 50, 50, 50); //20
            enemies[0][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
            enemies[0][i].setDropItemName("wapons1", "armor1");
        }
        enemies[0][0].setPosition(462, 763);
        enemies[0][1].setPosition(492, 647);
        enemies[0][2].setPosition(586, 717);
        enemies[0][3].setPosition(634, 628);

        //Group 2
        countEnemy = 4;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map1/p2.png", "enemy/map1/2.png", "enemy/map1/w2.png", true, "Goblin" ,2 ,     60, 6, 7, 0.6f, 4, 5,        24.5f, 75, 61, 75); //35
            enemies[1][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("helmet1", "shoes1");
        }
        enemies[1][0].setPosition(1019, 561);
        enemies[1][1].setPosition(1163, 587);
        enemies[1][2].setPosition(1098, 468);
        enemies[1][3].setPosition(1094, 708);

        //Group 3
        countEnemy = 5;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map1/p3.png", "enemy/map1/3.png", "enemy/map1/w3.png", false, "Dzik" ,4 ,      90, 10, 6, 1f, 8, 4,         24, 146, 86, 80); //46
            enemies[2][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("helmet2");
        }
        enemies[2][0].setPosition(1459, 486);
        enemies[2][1].setPosition(1880, 490);
        enemies[2][2].setPosition(1501, 637);
        enemies[2][3].setPosition(1856, 785);
        enemies[2][4].setPosition(1936, 678);

        //Group 4
        countEnemy = 4;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map1/p4.png", "enemy/map1/4.png", "enemy/map1/w4.png", false, "Zombie" ,7 ,        150, 9, 15, 1.6f, 8, 13,       22.8f, 240, 131, 95);//75
            enemies[3][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons2");
        }
        enemies[3][0].setPosition(1121, 1071);
        enemies[3][1].setPosition(1223, 776);
        enemies[3][2].setPosition(1036, 1107);
        enemies[3][3].setPosition(1674, 518);

        //Group 5
        countEnemy = 5;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map1/p5.png", "enemy/map1/5.png", "enemy/map1/w5.png", false, "Toksyk" ,10 ,       280, 18, 27, 3.3f, 14, 24,      20, 330, 185, 100);//140
            enemies[4][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("rekawice2", "helmet2");
        }
        enemies[4][0].setPosition(429, 1218);
        enemies[4][1].setPosition(373, 1056);
        enemies[4][2].setPosition(535, 1121);
        enemies[4][3].setPosition(556, 1370);
        enemies[4][4].setPosition(646, 1158);

        //Group 6
        countEnemy = 5;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map1/p6.png", "enemy/map1/6.png", "enemy/map1/w6.png", false, "Trol" ,11 ,     300, 28, 18, 3.8f, 25, 15,        21, 315, 205, 100);//150
            enemies[5][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("ring2", "tarcza2");
        }
        enemies[5][0].setPosition(1029, 1385);
        enemies[5][1].setPosition(993, 1622);
        enemies[5][2].setPosition(1116, 1619);
        enemies[5][3].setPosition(1097, 1268);
        enemies[5][4].setPosition(1214, 1722);

        //Group 7
        countEnemy = 5;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/map1/p7.png", "enemy/map1/7.png", "enemy/map1/w7.png", false, "Gadzina" ,12 ,      330, 26, 20, 3.5f, 24, 20,         17.4f, 330, 226, 100);//154
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("shoes2", "helmet3");
        }
        enemies[6][0].setPosition(1436, 1000);
        enemies[6][1].setPosition(1597, 881);
        enemies[6][2].setPosition(1602, 1109);
        enemies[6][3].setPosition(1767, 973);
        enemies[6][4].setPosition(1648, 1002);

        //Group 8
        countEnemy = 5;
        enemies[7] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[7][i] = new Enemy("enemy/map1/p8.png", "enemy/map1/8.png", "enemy/map1/w8.png", true, "Czerwony pająk" ,13 ,        340, 18, 30, 3.9f, 18, 27,       19.2f, 415, 248, 105);//159
            enemies[7][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[7][i]);
            enemies[7][i].setDropItemName("wapon4");
        }
        enemies[7][0].setPosition(1881, 1572);
        enemies[7][1].setPosition(1643, 1478);
        enemies[7][2].setPosition(1794, 1182);
        enemies[7][3].setPosition(632, 1239);
        enemies[7][4].setPosition(1808, 1330);

        //Group 9
        countEnemy = 8;
        enemies[8] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[8][i] = new Enemy("enemy/map1/p9.png", "enemy/map1/9.png", "enemy/map1/w9.png", true, "Ropuchacz" ,15 ,     410, 34, 48, 4.6f, 31, 42,        13.9f, 500, 295, 110);//215
            enemies[8][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[8][i]);
            enemies[8][i].setDropItemName("wapons4", "wapons3");
        }
        enemies[8][0].setPosition(1431, 1344);
        enemies[8][1].setPosition(1660, 1326);
        enemies[8][2].setPosition(421, 1707);
        enemies[8][3].setPosition(595, 1647);
        enemies[8][4].setPosition(932, 1998);
        enemies[8][4].setPosition(538, 1774);
        enemies[8][4].setPosition(452, 1604);
        enemies[8][4].setPosition(410, 1818);

        //Group 10
        countEnemy = 8;
        enemies[9] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[9][i] = new Enemy("enemy/map1/p10.png", "enemy/map1/10.png", "enemy/map1/w10.png", true, "Zerd" ,17 ,       450, 50, 35, 5f, 42, 30,      16.3f, 555, 346, 110); //225
            enemies[9][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[9][i]);
            enemies[9][i].setDropItemName("armor3", "ring3", "rekawice3");
        }
        enemies[9][0].setPosition(1488, 1854);
        enemies[9][1].setPosition(1430, 1499);
        enemies[9][2].setPosition(1033, 1932);
        enemies[9][3].setPosition(930, 1763);
        enemies[9][4].setPosition(1361, 1970);
        enemies[9][5].setPosition(1555, 1968);
        enemies[9][5].setPosition(610, 1890);
        enemies[9][5].setPosition(1454, 1926);

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                    enemy.collisionUpdate();
            }
        }
    }

    @Override
    public void addNpcToMap() {
        Npc npc = new Npc(new Texture(Gdx.files.internal("npc/1.png")), new Image(new Texture(Gdx.files.internal("npc/1h.png"))), "Aldern", 8, 0, 0, 0);
        npc.setPosition(560, 435);
        characters.add(npc);
        npc.setRectangle(2, 2, -12, -4);
        npc.collisionUpdate();

        Npc npc1 = new Npc(new Texture(Gdx.files.internal("npc/2.png")), new Image(new Texture(Gdx.files.internal("npc/2h.png"))), "Laura", 14, 0, 0, 1);
        npc1.setPosition(1690, 1960);
        characters.add(npc1);
        npc1.setRectangle(8, 2, -16, -8);
        npc1.collisionUpdate();

        Npc npc2 = new Npc(new Texture(Gdx.files.internal("npc/3.png")), new Image(new Texture(Gdx.files.internal("npc/3h.png"))), "Achius", 17, 0, 0, 2);
        npc2.setPosition(540, 2035);
        characters.add(npc2);
        npc2.setRectangle(8, 2, -16, -8);
        npc2.collisionUpdate();
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(1200, 2230, 100, 100));
        indexToLoadNextMap.add(1);
        entriencesPosition.add(new Vector2(Map_02.STARTING_POS_X, Map_02.STARTING_POS_Y));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        positionOptimalise = new ArrayList<Vector2>();
        arrayBoolean = new ArrayList<boolean[]>();

        addObjectCollision(new float[]{765,901,868,925,933,892,1167,1053,910,1189,1046,1261,975,1539,941,1539,893,1408,816,1422,751,1326,749,1215,828,1197,828,1156,756,1123,732,1144,695,1166,651,1143,652,1107,583,1073,546,1019,538,934,606,903});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(768,895), new Vector2(933,879), new Vector2(1186,1055), new Vector2(1059,1261), new Vector2(979,1551), new Vector2(934,1552), new Vector2(810,1441), new Vector2(738,1334), new Vector2(737,1210), new Vector2(693,1181), new Vector2(641,1152), new Vector2(569,1082), new Vector2(532,1024), new Vector2(526,928), new Vector2(604,892)});
        positionOptimalise.add(new Vector2(1195, -10));     arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{1368,840,1562,821,1490,701,1299,736});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1487,685), new Vector2(1570,830), new Vector2(1360,847), new Vector2(1287,733)});
        positionOptimalise.add(new Vector2(-10, 873));  arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{1177,1840,1260,1890,1240,1945,1188,1956,1119,1890,1147,1861});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1177,1822), new Vector2(1268,1887), new Vector2(1239,1946), new Vector2(1188,1956), new Vector2(1112,1889)});
        positionOptimalise.add(new Vector2(-10, 1790));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{966,1909,891,2012,812,2044,747,1964,728,1844,591,1908,570,1856,814,1715,520,1548,527,1505,565,1506,653,1522,695,1525,693,1575,817,1640,853,1621,885,1631,884,1682,927,1721,847,1790,975,1876});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(523, 1500), new Vector2(561, 1498), new Vector2(702,1518), new Vector2(852, 1613), new Vector2(892, 1625), new Vector2(938, 1726), new Vector2(984,1872), new Vector2(974, 1918), new Vector2(897, 2023), new Vector2(810, 2058), new Vector2(735, 1969), new Vector2(587, 1919), new Vector2(557, 1853), new Vector2(508, 1552)});
        positionOptimalise.add(new Vector2(526, 1466));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{172,809,422,669,340,582,584,455,562,430,631,395,804,490,1005,393,1155,405,1341,493,1410,491,1522,447,1754,540,1982,451,2300,456,2295,290,1000,200,79,462});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(434,674), new Vector2(803, 504), new Vector2(1755,557)});
        positionOptimalise.add(new Vector2(-10, 855));   arrayBoolean.add(new boolean[]{true, true});

        addObjectCollision(new float[]{2130,450,2060,450,2070,652,2068,740,2061,795,2080,843,1957,929,1902,1044,1949,1242,1927,1326,2035,1688,2006,1728,2048,1930,1950,1878,1745,1979,1728,2030,1555,2141,1455,2085,1359,2124,1287,2216,1272,2279,2327,2461});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1945,920), new Vector2(1882,1044), new Vector2(1914,1330), new Vector2(1989, 1727), new Vector2(1954,1873), new Vector2(1462,2071), new Vector2(1271,2199)});
        positionOptimalise.add(new Vector2(1878, 1838)); arrayBoolean.add(new boolean[]{false, false});

        addObjectCollision(new float[]{221,784,313,843,292,1005,250,1037,254,1077,420,1169,372,1378,420,1169,372,1378,420,1388,457,1495,373,1675,416,1750,383,1853,352,1928,467,1993,471,2033,850,2233,919,2181,1006,2188,1103,2150,1214,2242,105,2434});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(320, 842), new Vector2(428,1165), new Vector2(466,1495), new Vector2(519,1928), new Vector2(806, 2090), new Vector2(1108,2133), new Vector2(1231, 2250)});
        positionOptimalise.add(new Vector2(485, 1895));     arrayBoolean.add(new boolean[]{true, false});

        addObjectCollision(new float[]{1708,1567,1859,1634,1958,1714,1954,1763,1839,1878,1772,1825,1675,1829,1483,1743,1481,1696});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1708,1547), new Vector2(1865,1626), new Vector2(1966, 1710), new Vector2(1960, 1764), new Vector2(1839, 1888), new Vector2(1672,1852), new Vector2(1470,1760), new Vector2(1467, 1695)});
        positionOptimalise.add(new Vector2(-10, 1525));     arrayBoolean.add(new boolean[]{true, false});
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

        mapView = new Image(new Texture("map01View.jpg"));
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
            pref.putInteger("MAP", 0).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 0).flush();
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
