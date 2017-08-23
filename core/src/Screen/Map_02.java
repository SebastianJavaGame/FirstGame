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
    public static final int STARTING_POS_X = 1251;
    public static final int STARTING_POS_Y = 347;
    public static final Vector2[] ENTRIENCES = new Vector2[]{new Vector2(2024, 1921), new Vector2(253, 1717)};
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
        mapImage = new Image(new Texture("MAP_02.jpg"));
        //}
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public Map_02(Game g) {
        super(g, mapImage);
        System.out.println("MAP2");
        bossInstance = false;
    }

    @Override
    public void addEnemyToMap() {
        addEnemy("glomin.png", "glominHead.png", "glominWapon.png", 1000);
        //addEnemy("ragon.png", "ragonHead.png", "ragonWapon.png", 300);
    }

    @Override
    public void addNpcToMap() {
        //addNpc("mag.png", "glominHead.png", "Witherman", 20, 0, 1);
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
        addObjectCollision(new float[]{2001,956,1825,1056,1817,1488,1843,1762,1990,1842,1999,1906,2056,1908,2068,1832,2195,1836,2151,1884,2130,1956,2160,2012,1845,2054,1866,2325,1756,2317,1757,2226,1647,2196,1554,2253,1354,2221,1305,2179,1192,2225,1131,2204,1005,2144,865,2214,814,2197,652,2190,558,2130,429,2189,322,2161,328,2012,284,1755,253,1734,177,1769,234,2297,1992,2440,2198,2022,2263,1707,2198,940});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1813, 1042), new Vector2(1803, 1505), new Vector2(1830, 1772), new Vector2(1993, 1919), new Vector2(2062, 1917), new Vector2(1833, 2043), new Vector2(1765, 2217), new Vector2(1644, 2190), new Vector2(1305, 2172), new Vector2(1003, 2137), new Vector2(558, 2124), new Vector2(336, 2004), new Vector2(293, 1739), new Vector2(251, 1726)});
        addObjectCollision(new float[]{1312,251,1302,380,1388,374,1525,283,1485,342,1475,418,1546,527,1606,516,1694,403,1813,300,1893,476,1977,456,2030,507,1908,618,1910,675,1990,770,2094,775,2120,817,2081,849,2073,944,2199,933, 2176,642,1850,218});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1293,389), new Vector2(1391,388), new Vector2(1463, 417), new Vector2(1542, 540), new Vector2(1611, 527), new Vector2(1889, 488), new Vector2(1890, 613), new Vector2(1900,679), new Vector2(1983, 780)});
        addObjectCollision(new float[]{1186,276,1039,403,1145,403,1151,510,1116,579,1012,628,949,620,873,530,865,460,971,352,860,355,756,461,645,490,560,472,458,545,354,326,260,630,277,760,284,956,307,1066,317,1700,161,1705,132,302,380,229});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(327, 1714), new Vector2(318, 1064), new Vector2(455, 559), new Vector2(643, 545), new Vector2(853, 453), new Vector2(860, 535), new Vector2(940, 634), new Vector2(1015, 643), new Vector2(1124, 592), new Vector2(1159, 516), new Vector2(1153, 400)});
        addObjectCollision(new float[]{729,596,842,682,756,803,702,823,622,734,616,678,639,619});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(730, 589), new Vector2(856, 678), new Vector2(760, 815), new Vector2(700, 833), new Vector2(612, 737), new Vector2(607, 670), new Vector2(630, 610)});
        addObjectCollision(new float[]{542,1078,659,1179,555,1304,513,1311,437,1219,426,1091});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(543, 1071), new Vector2(671, 1178), new Vector2(556, 1316), new Vector2(509, 1320), new Vector2(425, 1223), new Vector2(416, 1083)});
        addObjectCollision(new float[]{602,954,691,978,691,978,690,1024,633,1052,572,997,572,957});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(566, 948), new Vector2(696, 970), new Vector2(697, 1032), new Vector2(632, 1062), new Vector2(563, 1001)});
        addObjectCollision(new float[]{1439,1206,1586,1174,1585,1124,1632,1118,1710,1161,1711,1276,1591,1400,1533,1391,1353,1475,1299,1428,1213,1348,1292,1273,1361,1333,1351,1251});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1603, 1105), new Vector2(1634, 1111), new Vector2(1718, 1154), new Vector2(1719, 1284), new Vector2(1591, 1408), new Vector2(1351, 1488), new Vector2(1200, 1349), new Vector2(1299, 1248)});
        addObjectCollision(new float[]{1692,1927,1754,1954,1781,2038,1750,2082,1711,2078,1666,2036,1645,1940});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1691, 1919), new Vector2(1759, 1947), new Vector2(1788, 2039), new Vector2(1753, 2093), new Vector2(1707, 2087), new Vector2(1656, 2040), new Vector2(1636, 1937)});
        addObjectCollision(new float[]{1383,1679,1556,1769,1482,1831,1482,1900,1379,1960,1359,2002,1335,2010,1293,1987,1228,1873,1160,1838,1171,1786,1248,1754,1290,1776,1302,1715});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1384, 1674), new Vector2(1573, 1768), new Vector2(1488, 1907), new Vector2(1350, 2021), new Vector2(1285, 2004), new Vector2(1148, 1843), new Vector2(1162, 1782), new Vector2(1289, 1713)});
        addObjectCollision(new float[]{875,1741,1075,1854,1081,1983,995,2049,928,2019,857,2064,800,2026,680,1951,670,1850});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(875, 1733), new Vector2(1083, 1846), new Vector2(1089, 1989), new Vector2(995, 2062), new Vector2(855, 2074), new Vector2(668, 1957), new Vector2(658, 1846)});
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

    private void addEnemy(String path, String head, String wapon, int x){
        Enemy enemy = new Enemy(path, head, wapon, true, "Goltral", 5, 180, 8, 8, 16, 10, 8, 10, 40, 70, 30);
        enemy.setRectangle(0, 0, 0, 0);
        enemy.setPosition(x, x);
        //TODO if not equals null add item drop;     enemy.setDropItemName();
        characters.add(enemy);
        enemy.collisionUpdate();
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
