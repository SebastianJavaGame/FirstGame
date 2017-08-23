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

public class MapBoss_02 extends BaseMap {
    public static final int STARTING_POS_X = 300;
    public static final int STARTING_POS_Y = 590;
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
        mapImage = new Image(new Texture("MAP_BOSS_02.jpg"));
        //}
        mapWidth = (int)(mapImage.getWidth() *0.8f);
        mapHeight = (int)(mapImage.getHeight() *0.8f);
    }

    public MapBoss_02(Game g) {
        super(g, mapImage);
        System.out.println("MAP_BOSS_2");
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
            pref.putInteger("MAP", 2).flush();
            Menu.setIsFirstSpawnHeroPosition(false);
        }else{
            pref.putInteger("POS_X", STARTING_POS_X).flush();
            pref.putInteger("POS_Y", STARTING_POS_Y).flush();
            pref.putInteger("MAP", 2).flush();
        }
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
