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

public class MapBoss_05 extends BaseMap {
    public static final int STARTING_POS_X = 295;
    public static final int STARTING_POS_Y = 640;
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_BOSS_05.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_05(Game g) {
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
            enemies[0][i] = new Enemy("enemy/map6/p5.png", "enemy/map6/5.png", "enemy/map6/w5.png", true, "Gorgona" ,92 ,       5000, 160, 230, 14f, 140, 140,     9.6f, 11940, 4273, 280);//1320
            enemies[0][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("wapons30", "pants11");
        }
        enemies[0][0].setPosition(280, reversePosYBoss(471));
        enemies[0][1].setPosition(305, reversePosYBoss(742));

        //Group 2
        countEnemy = 2;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map6/p6.png", "enemy/map6/6.png", "enemy/map6/w6.png", true, "Vassa" ,93 ,     4500, 220, 230, 14f, 140, 140,      9.2f, 11860, 4366, 290);//1330
            enemies[1][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("tarcza10", "armor17");
        }
        enemies[1][0].setPosition(373, reversePosYBoss(415));
        enemies[1][1].setPosition(402, reversePosYBoss(659));


        //Group 3
        countEnemy = 2;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map6/p7.png", "enemy/map6/7.png", "enemy/map6/w7.png", true, "Tassan" ,95 ,        5000, 270, 200, 13.5f, 130, 120,        9.5f, 11870, 4555, 300);//1365
            enemies[2][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("helmet16", "ring17");
        }
        enemies[2][0].setPosition(413, reversePosYBoss(514));
        enemies[2][1].setPosition(676, reversePosYBoss(714));

        //Group 4
        countEnemy = 3;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map6/p8.png", "enemy/map6/8.png", "enemy/map6/w8.png", false, "Xantes" ,96 ,        4600, 200, 270, 12f, 150, 160,     10, 12166, 4651, 320);//1370
            enemies[3][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons31", "armor19");
        }
        enemies[3][0].setPosition(479, reversePosYBoss(590));
        enemies[3][1].setPosition(588, reversePosYBoss(488));
        enemies[3][2].setPosition(779, reversePosYBoss(671));

        //Group 5
        countEnemy = 4;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map6/p9.png", "enemy/map6/9.png", "enemy/map6/w9.png",true, "Selenus" ,98 ,      4500, 255, 240, 12f, 170, 170,       9.1f, 12555, 4846, 310);//1415
            enemies[4][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("shoes12", "tarcza11", "wapons32");
        }
        enemies[4][0].setPosition(494, reversePosYBoss(687));
        enemies[4][1].setPosition(660, reversePosYBoss(411));
        enemies[4][2].setPosition(641, reversePosYBoss(616));
        enemies[4][3].setPosition(950, reversePosYBoss(570));

        //Group 6
        countEnemy = 7;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map6/p10.png", "enemy/map6/10.png", "enemy/map6/w10.png",  true, "Raplieton" ,99 ,      6000, 300, 200, 10f, 110, 100,         10, 13333, 4945, 360);//1420
            enemies[5][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("ring18", "pants12", "armor20", "wapons33");
        }
        enemies[5][0].setPosition(484, reversePosYBoss(438));
        enemies[5][1].setPosition(569, reversePosYBoss(584));
        enemies[5][2].setPosition(701, reversePosYBoss(556));
        enemies[5][3].setPosition(751, reversePosYBoss(464));
        enemies[5][4].setPosition(845, reversePosYBoss(581));
        enemies[5][5].setPosition(579, reversePosYBoss(739));
        enemies[5][6].setPosition(909, reversePosYBoss(689));

        //Group 7//TODO boss and all position
        countEnemy = 1;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/boss/boss5.png", "enemy/boss/boss5h.png", "enemy/boss/boss5w.png", false, "Morgan" ,110 ,       8000, 350, 230, 10f, 130, 120,        33.3f, 20000, 6000, -1);//1750
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("wapons34", "armor21", "helmet17", "pants13", "ring19", "rekawice14");
        }
        enemies[6][0].setPosition(864, reversePosYBoss(472));
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
        entriaceToMapRectangle.add(new Rectangle(143, 560, 100, 150));
        indexToLoadNextMap.add(5);
        entriencesPosition.add(new Vector2(1406, 2039));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();

        addObjectCollision(new float[]{244,572,290,436,271,378,1012,383,1026,706,907,774,265,775,270,727,242,698,162,865,1136,873,1146,281,140,277});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(297,441), new Vector2(244, 596), new Vector2(241, 687), new Vector2(275,724)});
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

        pref.putInteger("POS_X", 1406).flush();
        pref.putInteger("POS_Y", 2039).flush();
        pref.putInteger("MAP", 5).flush();
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
