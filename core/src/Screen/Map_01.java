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

    public static Enemy e1;
    public static Enemy e2;
    public static Enemy e3;
    public static Enemy e4;
    public static Enemy e5;
    public static Enemy e6;
    public static Enemy e7;
    public static Enemy e8;
    public static Enemy e9;
    public static Enemy e10;

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
        int countEnemy = 1;
        Enemy[][] enemies = new Enemy[10][countEnemy];

        //Group 1
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map1/p1.png", "enemy/map1/1.png", "enemy/map1/w1.png", true, "Grzybox" ,1 ,     30, 4, 4, 0.3f, 2, 2,       0, 50, 1, 75); //20
            enemies[0][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[0][i]);
            enemies[0][i].collisionUpdate();
            enemies[0][i].setDropItemName("wapon1", "armor1");
        }
        enemies[0][0].setPosition(400, 680);

        e1 = enemies[0][0];
        //TODO 0 ... 10


        //Group 2
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map1/p2.png", "enemy/map1/2.png", "enemy/map1/w2.png", true, "Goblin" ,2 ,     60, 6, 7, 0.6f, 4, 5,        0, 75, 1, 75); //35
            enemies[1][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("helmet1", "shoes1");
        }
        enemies[1][0].setPosition(500, 680);
        e2 = enemies[1][0];
        //Group 3
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map1/p3.png", "enemy/map1/3.png", "enemy/map1/w3.png", false, "Dzik" ,4 ,      90, 10, 6, 1f, 8, 4,         1, 146, 1, 80); //46
            enemies[2][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("helmet2");
        }
        enemies[2][0].setPosition(600, 680);
        e3 = enemies[2][0];
        //Group 4
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map1/p4.png", "enemy/map1/4.png", "enemy/map1/w4.png", false, "Zombie" ,7 ,        150, 9, 15, 1.6f, 8, 13,       1, 240, 1, 95);//75
            enemies[3][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons2");
        }
        enemies[3][0].setPosition(700, 680);
        e4 = enemies[3][0];
        //Group 5
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map1/p5.png", "enemy/map1/5.png", "enemy/map1/w5.png", false, "Toksyk" ,10 ,       280, 18, 27, 3.3f, 14, 24,      1, 330, 1, 100);//140
            enemies[4][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("rekawice2", "helmet2");
        }
        enemies[4][0].setPosition(800, 680);
        e5 = enemies[4][0];
        //Group 6
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map1/p6.png", "enemy/map1/6.png", "enemy/map1/w6.png", false, "Trol" ,11 ,     300, 28, 18, 3.8f, 25, 15,        1, 315, 1, 100);//150
            enemies[5][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("ring2", "tarcza2");
        }
        enemies[5][0].setPosition(900, 680);
        e6 = enemies[5][0];
        //Group 7
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/map1/p7.png", "enemy/map1/7.png", "enemy/map1/w7.png", false, "Gadzina" ,12 ,      330, 26, 20, 3.5f, 24, 20,         1, 330, 1, 100);//154
            enemies[6][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("shoes2", "helmet3");
        }
        enemies[6][0].setPosition(1000, 680);
        e7 = enemies[6][0];
        //Group 8
        for (int i = 0; i < countEnemy; i++) {
            enemies[7][i] = new Enemy("enemy/map1/p8.png", "enemy/map1/8.png", "enemy/map1/w8.png", true, "Czerwony pająk" ,13 ,        340, 18, 30, 3.9f, 18, 27,       1, 415, 1, 105);//159
            enemies[7][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[7][i]);
            enemies[7][i].setDropItemName("wapon4");
        }
        enemies[7][0].setPosition(1100, 680);
        e8 = enemies[7][0];
        //Group 9
        for (int i = 0; i < countEnemy; i++) {
            enemies[8][i] = new Enemy("enemy/map1/p9.png", "enemy/map1/9.png", "enemy/map1/w9.png", true, "Ropuchacz" ,15 ,     410, 34, 48, 4.6f, 31, 42,        1, 500, 1, 110);//215
            enemies[8][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[8][i]);
            enemies[8][i].setDropItemName("wapons4", "wapons3");
        }
        enemies[8][0].setPosition(1200, 680);
        e9 = enemies[8][0];
        //Group 10
        for (int i = 0; i < countEnemy; i++) {
            enemies[9][i] = new Enemy("enemy/map1/p10.png", "enemy/map1/10.png", "enemy/map1/w10.png", true, "Zerd" ,17 ,       450, 50, 35, 5f, 42, 30,      1, 555, 1, 110); //225
            enemies[9][i].setRectangle(0, 0, 0, 0);
            characters.add(enemies[9][i]);
            enemies[9][i].setDropItemName("armor3", "ring3", "rekawice3");
        }
        enemies[9][0].setPosition(1350, 680);
        e10 = enemies[9][0];

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                //if(enemy != null)//TODO implements all enemies and active code
                    enemy.collisionUpdate();
            }
        }
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

        addObjectCollision(new float[]{2060,450,2070,652,2068,740,2061,795,2080,843,1957,929,1902,1044,1949,1242,1927,1326,2035,1688,2006,1728,2048,1930,1950,1878,1745,1979,1728,2030,1555,2141,1455,2085,1359,2124,1287,2216,1272,2279,2327,2461});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1945,920), new Vector2(1882,1044), new Vector2(1914,1330), new Vector2(1989, 1727), new Vector2(1954,1873), new Vector2(1462,2071), new Vector2(1271,2199)});
        positionOptimalise.add(new Vector2(1878, 1838)); arrayBoolean.add(new boolean[]{false, false});

        addObjectCollision(new float[]{221,784,313,843,292,1005,250,1037,254,1077,420,1169,372,1378,420,1169,372,1378,420,1388,457,1495,373,1675,416,1750,383,1853,352,1928,467,1993,471,2033,850,2233,919,2181,1006,2188,1103,2150,1214,2242,105,2434});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(320, 842), new Vector2(428,1165), new Vector2(466,1495), new Vector2(519,1928), new Vector2(806, 2090), new Vector2(1108,2133)});
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
