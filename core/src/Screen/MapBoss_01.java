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
        System.out.println("MAP_BOSS_1");
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
