package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.BaseDialogs;
import com.mygdx.game.BaseEnemyAI;
import com.mygdx.game.BaseTask;
import com.mygdx.game.Equipment;
import com.mygdx.game.ExperienceRequired;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.Quest;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Menu extends BaseScreen {
    private final BitmapFont FONT = new BitmapFont();
    private final Label.LabelStyle STYLE = new Label.LabelStyle();
    private TextButton.TextButtonStyle textStyle;
    private Image texture;
    private static boolean isFirstSpawnHeroPosition = true;

    private Label lNewGame;
    private Label lLoadGame;
    private Label lMore;
    private Label lExit;

    public Menu(Game g) {
        super(g);
        textStyle = new TextButton.TextButtonStyle();
        textStyle.font = FONT;
        create();
    }

    @Override
    public void create() {
        STYLE.font = FONT;
        texture = new Image(new Texture(Gdx.files.internal("menu.png")));
        textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("confirmButtonNewGame.png"))));

        lNewGame = new Label("Nowa gra", STYLE);
        lLoadGame = new Label("Wczytaj gre", STYLE);
        lMore = new Label("Jak grac?", STYLE);
        lExit = new Label("Wyjscie", STYLE);

        lNewGame.setPosition(BaseScreen.VIEW_WIDTH /2 -lNewGame.getWidth() /2, 330);
        lLoadGame.setPosition(BaseScreen.VIEW_WIDTH /2 -lNewGame.getWidth() /2, 270);
        lMore.setPosition(BaseScreen.VIEW_WIDTH /2 -lNewGame.getWidth() /2, 210);
        lExit.setPosition(BaseScreen.VIEW_WIDTH /2 -lNewGame.getWidth() /2, 160);

        lNewGame.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                confirmDialog();
                return false;
            }
        });

        addActors(texture, lNewGame, lLoadGame, lMore, lExit);
    }

    @Override
    public void update(float v) {
    }

    /*public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        new Quest(stage);
        setMap();
        return false;
    }*/

    private void confirmDialog() {
        Image background = new Image(new Texture(Gdx.files.internal("confirmNewGame.png")));
        TextButton bYes = new TextButton("Tak", textStyle);
        TextButton bNo = new TextButton("Nie", textStyle);

        background.setPosition(BaseScreen.VIEW_WIDTH /2 -background.getWidth() /2, 350);
        bYes.setPosition(BaseScreen.VIEW_WIDTH /4 -bYes.getWidth() /2, 340);
        bNo.setPosition(BaseScreen.VIEW_WIDTH /4 *3 -bYes.getWidth() /2, 340);

        bYes.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                new Quest(stage);
                clearAllPreference();
                new LoadAllItemToGame().loadItems();
                ExperienceRequired.loadExperienceList();
                BaseDialogs.loadNpcTextList();
                BaseDialogs.loadIndexOptions();
                BaseDialogs.loadIndexListener();
                BaseTask.loadAllTasks();
                BaseEnemyAI.loadAI();
                setMap();
                return false;
            }
        });

        bNo.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.clear();
                create();
                return false;
            }
        });

        addActors(background, bYes, bNo);
    }

    private void clearAllPreference() {
        Preferences prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        Preferences prefEq = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
        Preferences prefTask = Gdx.app.getPreferences(Quest.PREF_TASK);
        Preferences prefFight = Gdx.app.getPreferences(Equipment.PREF_NAME_FIGHT);

        prefStats.clear();
        prefEq.clear();
        prefTask.clear();
        prefFight.clear();

        prefStats.flush();
        prefEq.flush();
        prefTask.flush();
        prefFight.flush();
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

    public void addActors(Actor... actors){
        for(Actor a: actors)
            stage.addActor(a);
    }

    public static void setIsFirstSpawnHeroPosition(boolean set){
        isFirstSpawnHeroPosition = set;
    }

    public static boolean getIsFirstSpawnHeroPosition(){
        return isFirstSpawnHeroPosition;
    }
}
