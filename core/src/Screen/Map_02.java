package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.mygdx.game.Npc;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Map_02 extends BaseMap {
    public final static int MAP_WIDTH = 1000;
    public final static int MAP_HEIGHT = 1000;

    private ArrayList<Polygon> objectCollision;

    public Map_02(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, "background.png");
    }

    @Override
    public void generateMap() {
        bgTexture.setSize(MAP_WIDTH, MAP_HEIGHT);
        npcList.add(new Npc(new Texture(Gdx.files.internal("badlogic.jpg"))));
        mapStage.addActor(bgTexture);
        mapStage.addActor(npcList.get(0));
        objectCollision = new ArrayList<Polygon>();
        objectPolygon = objectCollision;
    }

    @Override
    public void collisionEndMap() {
        System.out.println("map 01");
        game.setScreen(new Map_01(game));
    }
}
