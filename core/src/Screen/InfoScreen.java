package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Sebastian on 2017-08-05.
 */

public class InfoScreen {
    private static final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private static final Label.LabelStyle STYLE = new Label.LabelStyle();
    private final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("backgroundInfoScreen.png")));

    static {
        STYLE.font = FONT;
        STYLE.fontColor = new Color(Color.RED);
    }

    public InfoScreen(String description, float duration, Stage stage) {
        Label label = new Label(description, STYLE);
        label.setPosition(BaseScreen.VIEW_WIDTH /2 -label.getWidth() /2, BaseScreen.VIEW_HEIGHT /2 -label.getHeight() /2);
        BACKGROUND.setPosition(BaseScreen.VIEW_WIDTH /2 -BACKGROUND.getWidth() /2, BaseScreen.VIEW_HEIGHT /2 -BACKGROUND.getHeight() /2);

        label.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(0.5f), Actions.delay(duration), Actions.fadeOut(0.5f)));
        BACKGROUND.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(0.5f), Actions.delay(duration), Actions.fadeOut(0.5f)));

        stage.addActor(BACKGROUND);
        stage.addActor(label);
    }
}
