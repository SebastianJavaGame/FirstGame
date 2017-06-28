package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Npc;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Map_01 extends BaseMap {
    public final static int MAP_WIDTH = 1480;
    public final static int MAP_HEIGHT = 1320;

    private ArrayList<Polygon> objectCollision;
    private ArrayList<Point[]> verticalCollision;

    public Map_01(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, "Map_01.png");
    }

    @Override
    public void generateMap() {
        bgTexture.setSize(MAP_WIDTH, MAP_HEIGHT);
        npcList.add(new Npc(new Texture(Gdx.files.internal("badlogic.jpg"))));
        mapStage.addActor(bgTexture);
        //mapStage.addActor(npcList.get(0));

        objectCollision = new ArrayList<Polygon>();
        verticalCollision = new ArrayList<Point[]>();
        addObjectCollision(new float[]{287, 241, 381, 297, 380, 358, 355, 398, 352, 426});
        addObjectCollision(new float[]{387, 341, 481, 397, 480, 458, 455, 498, 452, 526});
        addVerticalToObjectCollision(new Point[]{new Point(397, 356), new Point(397,292), new Point(267, 221), new Point(352,447)});
        addVerticalToObjectCollision(new Point[]{new Point(497, 456), new Point(497,392), new Point(367, 321), new Point(452,547)});
        objectPolygon = objectCollision;
        verticalPolygon = verticalCollision;
    }

    private void addObjectCollision(float[] position) {
        objectCollision.add(new Polygon(position));
    }

    private void addVerticalToObjectCollision(Point[] point){
        verticalCollision.add(point);
    }

    @Override
    public void collisionEndMap() {
        System.out.println("map 02");
        game.setScreen(new Screen.Map_02(game));
    }
}
