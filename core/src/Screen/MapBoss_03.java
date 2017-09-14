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

public class MapBoss_03 extends BaseMap {
    public static final String NAME = "Vedvarr-land";
    public static final int STARTING_POS_X = 1010;
    public static final int STARTING_POS_Y = 530;
    private static final Image bgFight = new Image(new Texture(Gdx.files.internal("pustynia.jpg")));
    private static Image mapImage;
    private static int mapWidth;
    private static int mapHeight;
    private Preferences pref;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    static {
        mapImage = new Image(new Texture("MAP_BOSS_03.jpg"));
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_03(Game g) {
        super(g, mapImage);
        bossInstance = true;
        deadPosX = 308;
        deadPosY = 1849;
    }

    @Override
    public void addEnemyToMap() {
        Enemy[][] enemies = new Enemy[7][];

        //Group 1
        int countEnemy = 2;
        enemies[0] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[0][i] = new Enemy("enemy/map4/p5.png", "enemy/map4/5.png", "enemy/map4/w5.png",  false, "Bevter" ,57 ,        2450, 150, 100, 12f, 130, 115,      10.9f, 6215, 1648, -1);//845
            enemies[0][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[0][i]);
            enemies[0][i].setDropItemName("armor10", "helmet11");
        }
        enemies[0][0].setPosition(874, reversePosYBoss(464));
        enemies[0][1].setPosition(863, reversePosYBoss(764));

        //Group 2
        countEnemy = 2;
        enemies[1] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[1][i] = new Enemy("enemy/map4/p6.png", "enemy/map4/6.png", "enemy/map4/w6.png",false, "Skvergan" ,58 ,      2700, 100, 150, 10f, 100, 145,      11.2f, 6250, 1706, -1);//850
            enemies[1][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[1][i]);
            enemies[1][i].setDropItemName("rekawice8", "ring9");
        }
        enemies[1][0].setPosition(804, reversePosYBoss(560));
        enemies[1][1].setPosition(795, reversePosYBoss(670));


        //Group 3
        countEnemy = 2;
        enemies[2] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[2][i] = new Enemy("enemy/map4/p7.png", "enemy/map4/7.png", "enemy/map4/w7.png", false, "Muzovert" ,60 ,     2800, 110, 160, 11f, 100, 125,        10.5f, 6750, 1825,-1);//870
            enemies[2][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[2][i]);
            enemies[2][i].setDropItemName("wapons20", "ring10");
        }
        enemies[2][0].setPosition(746, reversePosYBoss(429));
        enemies[2][1].setPosition(698, reversePosYBoss(614));

        //Group 4
        countEnemy = 3;
        enemies[3] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[3][i] = new Enemy("enemy/map4/p8.png", "enemy/map4/8.png", "enemy/map4/w8.png",  false, "Skarab" ,62 ,       3000, 135, 135, 11f, 100, 120,       11, 7150, 1948, -1);//885
            enemies[3][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[3][i]);
            enemies[3][i].setDropItemName("wapons21", "armoe11");
        }
        enemies[3][0].setPosition(713, reversePosYBoss(513));
        enemies[3][1].setPosition(501, reversePosYBoss(757));
        enemies[3][2].setPosition(619, reversePosYBoss(534));

        //Group 5
        countEnemy = 4;
        enemies[4] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[4][i] = new Enemy("enemy/map4/p9.png", "enemy/map4/9.png", "enemy/map4/w9.png",false, "Krather" ,64 ,      3600, 170, 120, 10f, 140, 110,         10.1f, 7500, 2075, -1);//1000
            enemies[4][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[4][i]);
            enemies[4][i].setDropItemName("pants8", "rekawice9");
        }
        enemies[4][0].setPosition(646, reversePosYBoss(443));
        enemies[4][1].setPosition(722, reversePosYBoss(747));
        enemies[4][2].setPosition(400, reversePosYBoss(600));
        enemies[4][3].setPosition(551, reversePosYBoss(626));

        //Group 6
        countEnemy = 7;
        enemies[5] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[5][i] = new Enemy("enemy/map4/p10.png", "enemy/map4/10.png", "enemy/map4/w10.png", true, "Verdos" ,65 ,     3500, 180, 135, 10f, 140, 120,          10.4f, 7580, 2140, -1);//1025
            enemies[5][i].setRectangle(2, 2, -4, -4);
            characters.add(enemies[5][i]);
            enemies[5][i].setDropItemName("ring11", "tarcza7", "shoes8", "helmet12");
        }
        enemies[5][0].setPosition(632, reversePosYBoss(679));
        enemies[5][1].setPosition(597, reversePosYBoss(754));
        enemies[5][2].setPosition(481, reversePosYBoss(552));
        enemies[5][3].setPosition(508, reversePosYBoss(478));
        enemies[5][4].setPosition(304, reversePosYBoss(569));
        enemies[5][5].setPosition(565, reversePosYBoss(411));
        enemies[5][6].setPosition(471, reversePosYBoss(669));

        //Group 7
        countEnemy = 1;
        enemies[6] = new Enemy[countEnemy];
        for (int i = 0; i < countEnemy; i++) {
            enemies[6][i] = new Enemy("enemy/boss/boss3.png", "enemy/boss/boss3h.png", "enemy/boss/boss3w.png",  false, "Vedvarr" ,70 ,     3400, 160, 160, 10f, 150, 150,       11.2f, 9725, 2480, -1);//1080
            enemies[6][i].setRectangle(5, 5, -10, -10);
            characters.add(enemies[6][i]);
            enemies[6][i].setDropItemName("wapons24", "wapons23", "helmet13", "armor13");
        }
        enemies[6][0].setPosition(385, reversePosYBoss(471));
        enemies[6][0].collisionUpdate();

        for(Enemy enemiesList[]: enemies){
            for(Character enemy: enemiesList){
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
        entriaceToMapRectangle.add(new Rectangle(1035, 520, 100, 85));
        indexToLoadNextMap.add(3);
        entriencesPosition.add(new Vector2(292, 1864));
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();

        addObjectCollision(new float[]{262,382,1035,374,1032,477,1081,555,1189,538,1079,228,173,288,165,743,372,872,1130,845,1031,602,997,625,989,686,954,710,934,778,389,781,262,694});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1021,482), new Vector2(987,620), new Vector2(948,705)});
    }

    @Override
    public void generateMap() {
        characters = new ArrayList<Character>();
        bgTexture.setSize(mapWidth, mapHeight);
        mapView = new Image(new Texture("map03BossView.jpg"));
        addCollisionToMap();
        addEnemyToMap();
        addNpcToMap();
        addEntranceToMap();

        stage.addActor(bgTexture);

        mapName = NAME;
        mapId = 8;
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

        pref.putInteger("POS_X", 292).flush();
        pref.putInteger("POS_Y", 1864).flush();
        pref.putInteger("MAP", 3).flush();
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
