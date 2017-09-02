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

public class MapBoss_04 extends BaseMap {
    public static final String NAME = "Nathagan-land";
    public static final int STARTING_POS_X = 265;
    public static final int STARTING_POS_Y = 530;
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_BOSS_04.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_04(Game g) {
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
            enemies[0][i] = new Enemy("enemy/map5/p5.png", "enemy/map5/5.png", "enemy/map5/w5.png",  true, "Cybris" ,71 ,      3600, 170, 150, 11.5f, 150, 130,         10.6f, 9870, 2551, 243);//1095
            enemies[0][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("helmet13", "armor13");
        }
        enemies[0][0].setPosition(280, reversePosYBoss(471));
        enemies[0][1].setPosition(305, reversePosYBoss(742));

        //Group 2
        countEnemy = 2;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map5/p6.png", "enemy/map5/6.png", "enemy/map5/w6.png", true, "Monoris" ,73 ,     3800, 175, 150, 11.5f, 150, 130,        10, 10335, 2696, 255);//1120
            enemies[1][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("wapons25", "helmet13");
        }
        enemies[1][0].setPosition(373, reversePosYBoss(415));
        enemies[1][1].setPosition(402, reversePosYBoss(659));


        //Group 3
        countEnemy = 2;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map5/p7.png", "enemy/map5/7.png", "enemy/map5/w7.png", false, "Upadły" ,75 ,     3550, 130, 200, 10.5f, 170, 180,        10.3f, 10500, 2845, 261);//1160
            enemies[2][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("rekawice11", "armor14");
        }
        enemies[2][0].setPosition(413, reversePosYBoss(514));
        enemies[2][1].setPosition(676, reversePosYBoss(714));

        //Group 4
        countEnemy = 3;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map5/p8.png", "enemy/map5/8.png", "enemy/map5/w8.png", false, "Glomer" ,77 ,     3850, 170, 140, 11.5f, 180, 160,         9.8f, 10770, 2998, 209);//1170
            enemies[3][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons26", "pants9");
        }
        enemies[3][0].setPosition(479, reversePosYBoss(590));
        enemies[3][1].setPosition(588, reversePosYBoss(488));
        enemies[3][2].setPosition(779, reversePosYBoss(671));

        //Group 5
        countEnemy = 4;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map5/p9.png", "enemy/map5/9.png", "enemy/map5/w9.png", false, "Zombie siłacz" ,79 ,      5000, 160, 100, 13.5f, 160, 100,       9.5f, 10950, 3155, 267);//1175
            enemies[4][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("ring14", "armor15");
        }
        enemies[4][0].setPosition(494, reversePosYBoss(687));
        enemies[4][1].setPosition(660, reversePosYBoss(411));
        enemies[4][2].setPosition(641, reversePosYBoss(616));
        enemies[4][3].setPosition(950, reversePosYBoss(570));

        //Group 6
        countEnemy = 7;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map5/p10.png", "enemy/map5/10.png", "enemy/map5/w10.png",false, "Diabeuza" ,80 ,            4000, 200, 180, 13f, 130, 130,       10, 11150, 3235, 245);//1190
            enemies[5][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("pants10", "wapons27", "armor15");
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
            enemies[6][i] = new Enemy("enemy/boss/boss4.png", "enemy/boss/boss4h.png", "enemy/boss/boss4w.png", false, "Nathagan" ,90 ,      5000, 200, 190, 13f, 140, 140,     9.4f, 11935, 4090, 270);//1310
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("shoes11", "wapons29", "ring16");
        }
        enemies[6][0].setPosition(825, reversePosYBoss(470));
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
        entriaceToMapRectangle.add(new Rectangle(200, 520, 40, 120));
        indexToLoadNextMap.add(4);
        entriencesPosition.add(new Vector2(2169, 1467));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();

        addObjectCollision(new float[]{246,386,1017,382,1024,691,895,780,345,779,334,719,284,674,280,623,243,602,223,861,1110,874,1102,280,151,296,155,588,205,539,233,512,251,473});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(257,477), new Vector2(237,518), new Vector2(249,597), new Vector2(287,620), new Vector2(341,714)});
    }

    @Override
    public void generateMap() {
        if(!firstRun) {
            characters = new ArrayList<Character>();
            firstRun = true;

            bgTexture.setSize(mapWidth, mapHeight);

            mapView = new Image(new Texture("map04BossView.jpg"));//TODO
            mapName = NAME;

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

        pref.putInteger("POS_X", 2169).flush();
        pref.putInteger("POS_Y", 1467).flush();
        pref.putInteger("MAP", 4).flush();
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
