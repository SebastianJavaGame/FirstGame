package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Npc;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Map_01 extends BaseMap {

    public final static int MAP_WIDTH = 1000;
    public final static int MAP_HEIGHT = 1000;

    public Map_01(Game g) {
        super(g, MAP_WIDTH, MAP_HEIGHT, "background.png");
    }

    @Override
    public void generateMap() {
        npcList.add(new Npc(new Texture(Gdx.files.internal("badlogic.jpg"))));
    }

    @Override
    public void collisionEndMap() {
        game.setScreen(new Screen.Map_02(game));
    }

}
