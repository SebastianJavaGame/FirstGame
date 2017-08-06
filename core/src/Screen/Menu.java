package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Quest;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Menu extends BaseScreen {
    private Image texture;
    private static boolean isFirstSpawnHeroPosition = true;

    public Menu(Game g) {
        super(g);
        texture = new Image(new Texture(Gdx.files.internal("background.png")));
        create();
    }

    @Override
    public void create() {
        stage.addActor(texture);
    }

    @Override
    public void update(float v) {
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        new Quest(stage);
        setMap();
        return false;
    }

    public static void setMap(){
        Preferences pref = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);

        int idMap = pref.getInteger("MAP", 0);
        System.out.println(pref.getInteger("POS_X"));
        switch (idMap){
            case 0:
                game.setScreen( new Map_01(game) );
                break;
            case 1:
                game.setScreen( new Map_02(game) );
                break;
            default:
                game.setScreen( new Map_01(game) );
                break;
        }
    }

    public static void setIsFirstSpawnHeroPosition(boolean set){
        isFirstSpawnHeroPosition = set;
    }

    public static boolean getIsFirstSpawnHeroPosition(){
        return isFirstSpawnHeroPosition;
    }
}
