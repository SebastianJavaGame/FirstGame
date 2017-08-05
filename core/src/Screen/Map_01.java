package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Character;
import com.mygdx.game.Enemy;
import com.mygdx.game.Npc;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Map_01 extends BaseMap {
    public static final Image MAP_IMAGE = new Image(new Texture(Gdx.files.internal("Map_01.png")));
    public final static int MAP_WIDTH = (int)MAP_IMAGE.getWidth();
    public final static int MAP_HEIGHT = (int)MAP_IMAGE.getHeight();

    private static boolean firstRun;

    private static ArrayList<Polygon> objectCollision;
    private static ArrayList<Vector2[]> verticalCollision;
    private static ArrayList<Character> characters;

    public Map_01(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, MAP_IMAGE);
    }

    @Override
    public void addEnemyToMap() {
        addEnemy("glomin.png", "glominHead.png", "glominWapon.png", 380).setDropItemName("gold_armor");
        addEnemy("ragon.png", "ragonHead.png", "ragonWapon.png", 300);
    }

    @Override
    public void addNpcToMap() {
        addNpc("mag.png", "glominHead.png", "Witherman", 20, 0, 0);
    }

    /**
     * indexToLoadMap.get(x); x = Hero finishWalk switch(i)
     */
    @Override
    public void addEntranceToMap() {
        //first entriance
        //entriaceToMapRectangle.add(new Rectangle(500, 300, 100, 100));
        //indexToLoadNextMap.add(1);
        //
    }

    @Override
    public void addCollisionToMap() {
        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{637, 539, 650, 590, 606, 675, 426, 654, 423, 601, 340, 581, 303, 529, 389, 471, 522, 513, 590, 529});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(648, 528), new Vector2(666, 595), new Vector2(617, 693), new Vector2(410, 670), new Vector2(283, 530), new Vector2(386, 456)});
        //addObjectCollision(new float[]{825, 613, 933, 572, 894, 531, 829, 572, 803, 547, 761, 573});
        //addVerticalToObjectCollision(new Vector2[]{new Vector2(893, 518), new Vector2(951, 579), new Vector2(821, 628), new Vector2(743, 571), new Vector2(801, 530)});
        //addObjectCollision(new float[]{1082,683,1131,698,1202,652,1476,656,1480,1320,1242,1320,1208,1125,1238,1079,1210,1052,1160,1073,1110,997,1126,843,994,861,944,801,959,721});
        //addVerticalToObjectCollision(new Vector2[]{new Vector2(1500, 1350), new Vector2(1223, 1350), new Vector2(930, 816), new Vector2(944, 710), new Vector2(1192, 639), new Vector2(1500, 645)});
    }

    @Override
    public void generateMap() {
        if(!firstRun) {
            characters = new ArrayList<Character>();
            actualMap = this;
            firstRun = true;

            bgTexture.setSize(MAP_WIDTH, MAP_HEIGHT);

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

    private void addObjectCollision(float[] position) {
        objectCollision.add(new Polygon(position));
    }

    private void addVerticalToObjectCollision(Vector2[] point){
        verticalCollision.add(point);
    }

    private Enemy addEnemy(String path, String head, String wapon, int x){
        Enemy enemy = new Enemy(new Texture(Gdx.files.internal(path)), new Image(new Texture(Gdx.files.internal(head))),
                new Image(new Texture(Gdx.files.internal(wapon))),  "Glomin", 5, 180, 8, 8, 16, 10, 8, 10, 40, 70);
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
}
