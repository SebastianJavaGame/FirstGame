package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Menu extends BaseScreen {
    private Texture texture;

    public Menu(Game g) {
        super(g);
        texture = new Texture(Gdx.files.internal("background.png"));
        create();
    }

    @Override
    public void create() {
    }

    @Override
    public void update(float v) {
        spriteBatch.draw(texture, 0, 0);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen( new Map_01(game) );
        return false;
    }
}
