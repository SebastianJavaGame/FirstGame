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
        //Asset asset = new Asset();
        //asset.manager.load("MAP_01.jpg", Texture.class);
        //asset.manager.finishLoading();
        //if(asset.manager.update()) {
        mapImage = new Image(new Texture("MAP_BOSS_04.jpg"));
        //}
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_04(Game g) {
        super(g, mapImage);
        bossInstance = true;
        System.out.println("MAP_BOSS_4");
    }

    @Override
    public void addEnemyToMap() {
        addEnemy("glomin.png", "glominHead.png", "glominWapon.png", 500).setDropItemName("gold_armor");
        //addEnemy("ragon.png", "ragonHead.png", "ragonWapon.png", 300).setDropItemName("silver_sword");
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

    private Enemy addEnemy(String path, String head, String wapon, int x){
        Enemy enemy = new Enemy(path, head, wapon, true, "Glomin", 5, 180, 8, 8, 16, 10, 8, 24.5f, 60, 70, 10);
        enemy.setRectangle(0, 0, 0, 0);
        enemy.setPosition(x, x);
        //TODO if not equals null add item drop;     enemy.setDropItemName();
        characters.add(enemy);
        enemy.collisionUpdate();
        return enemy;
    }

    public ArrayList<Character> getCharacter(){
        return characters;
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
