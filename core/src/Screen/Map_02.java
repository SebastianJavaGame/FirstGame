package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Npc;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Map_02 extends BaseMap {
    public final static int MAP_WIDTH = 1000;
    public final static int MAP_HEIGHT = 1000;

    public Map_02(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, "background.png");
    }

    @Override
    public void generateMap() {
        bgTexture.setSize(MAP_WIDTH, MAP_HEIGHT);
        npcList.add(new Npc(new Texture(Gdx.files.internal("badlogic.jpg"))));
        mapStage.addActor(bgTexture);
        mapStage.addActor(npcList.get(0));
        mapStage.addActor(hero);
    }

    @Override
    public void collisionEndMap() {
        System.out.println("map 01");
        game.setScreen(new Map_01(game));
    }
}
