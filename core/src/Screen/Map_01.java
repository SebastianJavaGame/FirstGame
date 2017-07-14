package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Enemy;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Map_01 extends BaseMap {
    public final static int MAP_WIDTH = 1480;
    public final static int MAP_HEIGHT = 1320;

    private ArrayList<Polygon> objectCollision;
    private ArrayList<Vector2[]> verticalCollision;

    private int number = 0;

    public Map_01(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, "Map_01.png");
    }

    @Override
    public void generateMap() {
        bgTexture.setSize(MAP_WIDTH, MAP_HEIGHT);
        stage.addActor(bgTexture);
        addEnemy("glomin.png", "glominHead.png", "glominWapon.png", 380);
        number++;
        addEnemy("ragon.png", "ragonHead.png", "ragonWapon.png", 300);

        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{637, 539, 650, 590, 606, 675, 426, 654, 423, 601, 340, 581, 303, 529, 389, 471, 522, 513, 590, 529});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(648, 528), new Vector2(666, 595), new Vector2(617, 693), new Vector2(410, 670), new Vector2(283, 530), new Vector2(386, 456)});
        addObjectCollision(new float[]{825, 613, 933, 572, 894, 531, 829, 572, 803, 547, 761, 573});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(893, 518), new Vector2(951, 579), new Vector2(821, 628), new Vector2(743, 571), new Vector2(801, 530)});
        addObjectCollision(new float[]{1082,683,1131,698,1202,652,1476,656,1480,1320,1242,1320,1208,1125,1238,1079,1210,1052,1160,1073,1110,997,1126,843,994,861,944,801,959,721});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(1500, 1350), new Vector2(1223, 1350), new Vector2(930, 816), new Vector2(944, 710), new Vector2(1192, 639), new Vector2(1500, 645)});
        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
    }

    private void addObjectCollision(float[] position) {
        objectCollision.add(new Polygon(position));
    }

    private void addVerticalToObjectCollision(Vector2[] point){
        verticalCollision.add(point);
    }

    private void addEnemy(String path, String head, String wapon, int x){
        Enemy enemy = new Enemy(new Texture(Gdx.files.internal(path)), new Image(new Texture(Gdx.files.internal(head))),
                new Image(new Texture(Gdx.files.internal(wapon))),  "Goltral", 5, 180, 8, 8, 16, 10, 8, 10, 30, 45, 60, 75);
        enemy.setPosition(x, x);
        //TODO if not equals null add item drop;     enemy.setDropItemName();
        enemyList.add(enemy);
        stage.addActor(enemyList.get(number));
    }
}
