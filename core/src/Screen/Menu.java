package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Asset;
import com.mygdx.game.BaseDialogs;
import com.mygdx.game.BaseEnemyAI;
import com.mygdx.game.BaseTask;
import com.mygdx.game.Equipment;
import com.mygdx.game.ExperienceRequired;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Quest;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-06-04.
 */

public class Menu extends BaseScreen {
    private Asset asset;
    private final BitmapFont FONT = MyGdxGame.createDistanceFont();//MyGdxGame.createBitmapFont(20, Color.BLACK);
    private final Label.LabelStyle STYLE = new Label.LabelStyle();
    private TextButton.TextButtonStyle textStyle;
    private TextButton.TextButtonStyle textStyleDisapear;
    private Image texture;
    private Image iLoad;

    private static boolean isFirstSpawnHeroPosition = true;

    private TextButton lNewGame;
    private TextButton lLoadGame;
    private TextButton lMore;
    private TextButton lExit;

    private static Sound soundClick;

    public Menu(Game g) {
        super(g);
        asset = new Asset();
        textStyle = new TextButton.TextButtonStyle();
        textStyleDisapear = new TextButton.TextButtonStyle();
        textStyle.font = FONT;
        textStyleDisapear.font = FONT;
        create();
    }

    @Override
    public void create() {
        asset.loadMenu();
        asset.manager.finishLoading();
        if(asset.manager.update()){
            soundClick = asset.manager.get("sound/click.ogg", Sound.class);
            texture = new Image(asset.manager.get("menu.png", Texture.class));
            textStyle.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("confirmButtonNewGame.png", Texture.class)));
            textStyleDisapear.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("menuButton.png", Texture.class)));
            iLoad = new Image(new Texture(Gdx.files.internal("loadingFull.png")));
            iLoad.setSize(280, 50);
            iLoad.setPosition(BaseScreen.VIEW_WIDTH /2 -iLoad.getWidth() /2, 30);
        }

        STYLE.font = FONT;
        lNewGame = new TextButton("Nowa gra", textStyleDisapear);
        lLoadGame = new TextButton ("Wczytaj gre", textStyleDisapear);
        lMore = new TextButton("Jak grac?", textStyleDisapear);
        lExit = new TextButton("Wyjscie", textStyleDisapear);

        lNewGame.setPosition(BaseScreen.VIEW_WIDTH/2 -lNewGame.getWidth()/2, 300);
        lLoadGame.setPosition(BaseScreen.VIEW_WIDTH/2 -lNewGame.getWidth()/2, 228);
        lMore.setPosition(BaseScreen.VIEW_WIDTH/2 -lNewGame.getWidth()/2, 156);
        lExit.setPosition(BaseScreen.VIEW_WIDTH/2 -lNewGame.getWidth()/2, 85);

        lNewGame.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getSoundClick().play();
                setToutch(Touchable.disabled);
                confirmDialog();
                return false;
            }
        });

        lLoadGame.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                iLoad.setVisible(true);
                getSoundClick().play();
                new LoadAllItemToGame().loadItems();
                ExperienceRequired.loadExperienceList();
                BaseDialogs.loadNpcTextList();
                BaseDialogs.loadIndexOptions();
                BaseDialogs.loadIndexListener();
                BaseTask.loadAllTasks();
                BaseEnemyAI.loadAI();
                stage.addAction(Actions.sequence(Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        stage.addActor(iLoad);
                    }
                }), Actions.delay(0.5f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        new Quest(stage);
                        setMap();
                    }
                })));
                return false;
            }
        });

        lMore.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getSoundClick().play();
                new HowPlay(game);
                return false;
            }
        });

        lExit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                getSoundClick().play();
                Gdx.app.exit();
                return false;
            }
        });

        stage.addActor(texture);

        addActors(lNewGame, lLoadGame, lMore, lExit);
    }

    @Override
    public void update(float v) {
    }

    private void confirmDialog() {
        asset.loadMenu();
        asset.manager.finishLoading();
        if(asset.manager.update()){
            Image background = new Image(asset.manager.get("confirmNewGame.png", Texture.class));
            TextButton bYes = new TextButton("Tak", textStyle);
            TextButton bNo = new TextButton("Nie", textStyle);
            Label label = new Label("Czy napewno chcesz\nrozapoczac nowa gre?", STYLE);
            label.setFontScale(0.7f);

            background.setSize(background.getWidth() + 24, 80);
            background.setPosition(BaseScreen.VIEW_WIDTH /2 -background.getWidth() /2, 280);
            label.setPosition(BaseScreen.VIEW_WIDTH /2 -label.getWidth()*0.7f /2, background.getY() +background.getHeight() /2 -5);
            bYes.setPosition(BaseScreen.VIEW_WIDTH /4 -bYes.getWidth() /2, 285);
            bNo.setPosition(BaseScreen.VIEW_WIDTH /4 *3 -bYes.getWidth() /2, 285);

            bYes.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    getSoundClick().play();
                    clearAllPreference();
                    new LoadAllItemToGame().loadItems();
                    ExperienceRequired.loadExperienceList();
                    BaseDialogs.loadNpcTextList();
                    BaseDialogs.loadIndexOptions();
                    BaseDialogs.loadIndexListener();
                    BaseTask.loadAllTasks();
                    BaseEnemyAI.loadAI();
                    stage.addAction(Actions.sequence(Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            stage.addActor(iLoad);
                        }
                    }), Actions.delay(0.5f), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            new Quest(stage);
                            setMap();
                        }
                    })));
                    return false;
                }
            });

            bNo.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    getSoundClick().play();
                    fontStage.clear();
                    setToutch(Touchable.enabled);
                    create();
                    return false;
                }
            });

            addActors(background, label, bYes, bNo);
        }
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

        prefStats.putInteger("POS_X", 100).flush();
        prefStats.putInteger("POS_Y", 100).flush();
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

    public static Sound getSoundClick(){
        return soundClick;
    }

    private void setToutch(Touchable toutch){
        lNewGame.setTouchable(toutch);
        lLoadGame.setTouchable(toutch);
        lMore.setTouchable(toutch);
        lExit.setTouchable(toutch);
    }
}
