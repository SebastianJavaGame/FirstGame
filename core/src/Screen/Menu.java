package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Menu extends BaseScreen {
    private Image texture;

    public Menu(Game g) {
        super(g);
        texture = new Image(new Texture(Gdx.files.internal("background.png")));
        create();
    }

    @Override
    public void create() {
        mapStage.addActor(texture);
    }

    @Override
    public void update(float v) {
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        game.setScreen( new Map_01(game) );
        return false;
    }
}
