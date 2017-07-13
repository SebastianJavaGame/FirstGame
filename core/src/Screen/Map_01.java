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
        addEnemy("glomin.png", "glominHead.png", "glominWapon.png", 400);
        number++;
        addEnemy("ragon.png", "ragonHead.png", "ragonWapon.png", 550);

        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Vector2[]>();
        addObjectCollision(new float[]{287, 241, 381, 297, 380, 358, 355, 398, 352, 426});
        //addObjectCollision(new float[]{387, 341, 481, 397, 480, 458, 455, 498, 452, 526});
        addVerticalToObjectCollision(new Vector2[]{new Vector2(397, 356), new Vector2(397,292), new Vector2(267, 221), new Vector2(352,447)});
        //addVerticalToObjectCollision(new Point[]{new Point(497, 456), new Point(497,392), new Point(367, 321), new Point(452,547)});
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
        Enemy enemy = new Enemy(new Texture(Gdx.files.internal(path)), stage, new Image(new Texture(Gdx.files.internal(head))),
                new Image(new Texture(Gdx.files.internal(wapon))),  "Goltral", 5, 180, 8, 8, 16, 10, 8, 10, 30, 45, 60, 75, game);
        enemy.setSize(100, 100);
        enemy.setPosition(x, x);
        //TODO if not equals null add item drop;     enemy.setDropItemName();
        enemyList.add(enemy);
        stage.addActor(enemyList.get(number));
    }
}
