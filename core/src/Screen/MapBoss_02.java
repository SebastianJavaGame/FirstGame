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

public class MapBoss_02 extends BaseMap {
    public static final String NAME = "Quaregis-land";
    public static final int STARTING_POS_X = 300;
    public static final int STARTING_POS_Y = 590;
    private static final Image bgFight = new Image(new Texture(Gdx.files.internal("jaskinia.jpg")));
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_BOSS_02.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_02(Game g) {
        super(g, mapImage);
        bossInstance = true;
        deadPosX = 338;
        deadPosY = 1737;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[7][];

        //Group 1
        int countEnemy = 2;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map3/p5.png", "enemy/map3/5.png", "enemy/map3/w5.png", false, "Mendrec" ,40 ,       1400, 90, 80, 13f, 95, 90,         12, 4335, 815, -1);//590
            enemies[0][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("pants5", "shoes5");
        }
        enemies[0][0].setPosition(280, reversePosYBoss(471));
        enemies[0][1].setPosition(305, reversePosYBoss(742));

        //Group 2
        countEnemy = 2;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map3/p6.png", "enemy/map3/6.png", "enemy/map3/w6.png", true, "Stary skalniak" ,42 ,        1550, 80, 90, 13f, 90, 100,          12.3f, 4800, 898, -1);//610
            enemies[1][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("rekawice6", "armor7");
        }
        enemies[1][0].setPosition(373, reversePosYBoss(415));
        enemies[1][1].setPosition(402, reversePosYBoss(659));


        //Group 3
        countEnemy = 2;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map3/p7.png", "enemy/map3/7.png", "enemy/map3/w7.png",  false, "Stoban" ,44 ,      1750, 115, 85, 15f, 100, 90,         11.7f, 4900, 985, -1);//680
            enemies[2][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("wapons15", "armor8");
        }
        enemies[2][0].setPosition(413, reversePosYBoss(514));
        enemies[2][1].setPosition(676, reversePosYBoss(714));

        //Group 4
        countEnemy = 3;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map3/p8.png", "enemy/map3/8.png", "enemy/map3/w8.png", false, "Skalniak" ,45 ,    1700, 90, 120, 15.5f, 95, 95,        11.5f, 5000, 1030, -1);//690
            enemies[3][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("helmet9", "pants6");
        }
        enemies[3][0].setPosition(479, reversePosYBoss(590));
        enemies[3][1].setPosition(588, reversePosYBoss(488));
        enemies[3][2].setPosition(779, reversePosYBoss(671));

        //Group 5
        countEnemy = 4;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map3/p9.png", "enemy/map3/9.png", "enemy/map3/w9.png", true, "Jaszczur" ,47 ,        1400, 140, 120, 14.5f, 105, 95,           12, 5250, 1123, -1);//710
            enemies[4][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("ring7", "tarcza5");
        }
        enemies[4][0].setPosition(494, reversePosYBoss(687));
        enemies[4][1].setPosition(660, reversePosYBoss(411));
        enemies[4][2].setPosition(641, reversePosYBoss(616));
        enemies[4][3].setPosition(950, reversePosYBoss(570));

        //Group 6
        countEnemy = 7;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map3/p10.png", "enemy/map3/10.png", "enemy/map3/w10.png", false, "Jugger" ,48 ,       1750, 135, 85, 16f, 110, 90,           11, 5365, 1171, -1);//720
            enemies[5][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("wapons16", "armor8", "pants6");
        }
        enemies[5][0].setPosition(484, reversePosYBoss(438));
        enemies[5][1].setPosition(569, reversePosYBoss(584));
        enemies[5][2].setPosition(701, reversePosYBoss(556));
        enemies[5][3].setPosition(751, reversePosYBoss(464));
        enemies[5][4].setPosition(845, reversePosYBoss(581));
        enemies[5][5].setPosition(579, reversePosYBoss(739));
        enemies[5][6].setPosition(909, reversePosYBoss(689));

        //Group 7
        countEnemy = 1;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/boss/boss2.png", "enemy/boss/boss2h.png", "enemy/boss/boss2w.png", false, "Quaregis" ,55 ,      2300, 120, 100, 14f, 125, 100,         10.4f, 6135, 1535, -1);//805
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("pants7", "shoes7");
        }
        enemies[6][0].setPosition(850, reversePosYBoss(472));
        enemies[6][0].collisionUpdate();

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
                enemy.collisionUpdate();
            }
        }
    }

    @Override
    public void addNpcToMap() {
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        entriaceToMapRectangle.add(new Rectangle(142, 550, 100, 80));
        indexToLoadNextMap.add(2);
        entriencesPosition.add(new Vector2(240, 1773));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();

        addObjectCollision(new float[]{258,579,267,386,1018,386,1026,698,903,767,267,770,264,736,221,681,233,623,145,625,143,869,1167,880,1167,20,20,20});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(259,590), new Vector2(238,622), new Vector2(271,734)});
    }

    @Override
    public void generateMap() {
        characters = new ArrayList<Character>();
        bgTexture.setSize(mapWidth, mapHeight);
        mapView = new Image(new Texture("map02BossView.jpg"));
        addCollisionToMap();
        addEnemyToMap();
        addNpcToMap();
        addEntranceToMap();

        stage.addActor(bgTexture);

        mapName = NAME;
        mapId = 7;
        actualMap = this;
        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
        charactersList = characters;
        backgroundFight = bgFight;

        for(Character character: characters) {
            stage.addActor(character);
        }
    }

    @Override
    public void saveOrginalPosition(){
        pref = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);

        pref.putInteger("POS_X", 240).flush();
        pref.putInteger("POS_Y", 1773).flush();
        pref.putInteger("MAP", 2).flush();
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
