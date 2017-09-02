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
import com.mygdx.game.StatsHero;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class MapBoss_01 extends BaseMap {
    public static final int STARTING_POS_X = 250;
    public static final int STARTING_POS_Y = 350;
    public static final int DEAD_POS_X = 350; //TODO all boss map
    public static final int DEAD_POS_Y = 350;
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_BOSS_01.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_01(Game g) {
        super(g, mapImage);
        bossInstance = true;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[7][];

        //Group 1
        int countEnemy = 2;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map2/p5.png", "enemy/map2/5.png", "enemy/map2/w5.png", true, "Ognisty wilk" ,25 ,      750, 59, 60, 7.6f, 50, 60,       13, 1850, 590, 136);//345
            enemies[0][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("pants3", "shoes3");
        }
        enemies[0][0].setPosition(461, 377);
        enemies[0][1].setPosition(349, reversePosYBoss(430));

        //Group 2
        countEnemy = 2;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map2/p6.png", "enemy/map2/6.png", "enemy/map2/w6.png", false, "Stwór ziemny" ,27 ,     770, 67, 50, 8.1f, 70, 50,       15, 2545, 661, 143);//360
            enemies[1][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("wapons8", "helmet5");
        }
        enemies[1][0].setPosition(396, reversePosYBoss(691));
        enemies[1][1].setPosition(661, reversePosYBoss(752));


        //Group 3
        countEnemy = 2;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map2/p7.png", "enemy/map2/7.png", "enemy/map2/w7.png", false, "Czerwony trol" ,28 ,        800, 60, 64, 8.6f, 60, 55,       13.7f, 2725, 698, 145);//370
            enemies[2][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("ring5", "wapons8");
        }
        enemies[2][0].setPosition(947, reversePosYBoss(718));
        enemies[2][1].setPosition(293, reversePosYBoss(539));

        //Group 4
        countEnemy = 3;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map2/p8.png", "enemy/map2/8.png", "enemy/map2/w8.png", false, "Wilkołak" ,30 ,     880, 80, 64, 8.8f, 60, 55,       12.9f, 2870, 775, 155);//400
            enemies[3][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("armor5", "helmet6", "wapons9");
        }
        enemies[3][0].setPosition(279, reversePosYBoss(718));
        enemies[3][1].setPosition(796, reversePosYBoss(737));
        enemies[3][2].setPosition(587, reversePosYBoss(612));

        //Group 5
        countEnemy = 4;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map2/p9.png", "enemy/map2/9.png", "enemy/map2/w9.png", true, "Tygrys biały" ,32 ,      900, 85, 62, 9.3f, 70, 60,        13.5f, 2900, 856, 156);//425
            enemies[4][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("wapons10", "pants4");
        }
        enemies[4][0].setPosition(599, reversePosYBoss(507));
        enemies[4][1].setPosition(847, reversePosYBoss(614));
        enemies[4][2].setPosition(551, reversePosYBoss(687));
        enemies[4][3].setPosition(389, reversePosYBoss(568));

        //Group 6
        countEnemy = 7;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map2/p10.png", "enemy/map2/10.png", "enemy/map2/w10.png", true, "Krabogon" ,33 ,       800, 80, 60, 10.3f, 90, 82,       13.6f, 3365, 898, 160);//460
            enemies[5][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("rekawice5", "tarcza4", "shoes4", "helmet7");
        }
        enemies[5][0].setPosition(937, reversePosYBoss(539));
        enemies[5][1].setPosition(576, reversePosYBoss(429));
        enemies[5][2].setPosition(480, reversePosYBoss(497));
        enemies[5][3].setPosition(486, reversePosYBoss(630));
        enemies[5][4].setPosition(701, reversePosYBoss(630));
        enemies[5][5].setPosition(699, reversePosYBoss(483));
        enemies[5][6].setPosition(794, reversePosYBoss(557));

        //Group 7//TODO boss and all position
        countEnemy = 1;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/boss/boss1.png", "enemy/boss/boss1h.png", "enemy/boss/boss1w.png", false, "Maven" ,40 ,      1400, 100, 80, 12f, 95, 90,         12, 4335, 815, 199);//590
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("pants5", "shoes5");
        }
        enemies[6][0].setPosition(820, reversePosYBoss(455));
        enemies[6][0].collisionUpdate();

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                enemy.collisionUpdate();
            }
        }
    }

    @Override
    public void addNpcToMap() {
        //Npc is not here
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(150, 217, 200, 100));
        indexToLoadNextMap.add(1);
        entriencesPosition.add(new Vector2(2005, 1909));
        //TODO above npc
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();

        addObjectCollision(new float[]{338,317,434,373,1022,380,1027,698,877,782,228,778,236,525,213,460,163,422,158,328,204,301,77,321,124,946,1200,950,1200,10});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(214, 302), new Vector2(220,451), new Vector2(243,521), new Vector2(256,766), new Vector2(875,773), new Vector2(971,410), new Vector2(420,398), new Vector2(316, 320)});
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

        pref.putInteger("POS_X", 2005).flush();
        pref.putInteger("POS_Y", 1909).flush();
        pref.putInteger("MAP", 1).flush();
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
