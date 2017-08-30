package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.Asset;
import com.mygdx.game.Bag;
import com.mygdx.game.Character;
import com.mygdx.game.Hero;
import com.mygdx.game.Hero3D;
import com.mygdx.game.ImplementObjectMap;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.RenderCollisionLine_Test;
import com.mygdx.game.StatsHero;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-06-04.
 */

public abstract class BaseMap extends BaseScreen implements ImplementObjectMap{
    public final static int ICON_ITEM_SIZE = 25;
    protected Asset asset = new Asset();

    private float realWidth;
    private float realHeight;

    protected static Hero hero;
    protected static Hero3D hero3D;
    protected Image bgTexture;
    protected static BaseMap actualMap;
    private Preferences preferences;

    private float hpRefresh;

    protected static ArrayList<Rectangle> entriaceToMapRectangle;
    protected static ArrayList<Integer> indexToLoadNextMap;
    protected static ArrayList<Vector2> entriencesPosition;

    protected static int deadPosX;
    protected static int deadPosY;

    protected InputMultiplexer im;

    public static Stage stageUi;
    private static Stage stageStats;
    private static Stage stageCard;

    private boolean windowStatsOpen;
    private boolean stopGame;

    private Image uiBackground;
    private Image uiBarEmptyHp, uiBarEmptyExp;
    private Image uiBarHp;
    private Image uiBarExp;
    private Image backgtoundLvl;
    private ImageButton uiStats;

    private Label.LabelStyle labelStyle;
    private Label moneyLabel;
    private Label hpLabel;
    private Label expLabel;
    private Label levelLabel;

    private Sound card;

    private RenderCollisionLine_Test testRender;

    protected ArrayList<Polygon> objectPolygon;
    protected ArrayList<Vector2[]> verticalPolygon;
    protected ArrayList<Vector2> optimiseMap;
    protected ArrayList<boolean[]> optimiseToward;
    protected static ArrayList<Character> charactersList;

    protected static boolean bossInstance;

    static {
        hero3D = new Hero3D();
        hero3D.create();

        entriaceToMapRectangle = new ArrayList<Rectangle>();
        indexToLoadNextMap = new ArrayList<Integer>();
        entriencesPosition = new ArrayList<Vector2>();
    }

    public BaseMap(Game game, Image imageBg) {
            super(game);
            this.bgTexture = imageBg;

            preferences = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);

            realWidth = (float) Gdx.app.getGraphics().getWidth() / VIEW_WIDTH;
            realHeight = (float) Gdx.app.getGraphics().getHeight() / VIEW_HEIGHT;

            windowStatsOpen = false;
            stopGame = false;

            hpRefresh = 0;

            asset.loadBaseMap();
            asset.manager.finishLoading();
            if(asset.manager.update()) {
                 create();
                 initializeUiStage();
             }
    }

    protected abstract void generateMap();
    protected abstract ArrayList<Character> getCharacter();
    protected abstract void saveOrginalPosition();

    @Override
    public void create() {
        entriaceToMapRectangle.clear();
        indexToLoadNextMap.clear();
        entriencesPosition.clear();
        generateMap();

        new Bag(BaseScreen.getStage());
        hero = new Hero(asset.manager.get("hero.png", Texture.class), objectPolygon, verticalPolygon, optimiseMap, optimiseToward, camera, hero3D, charactersList);
        hero.setPosition(preferences.getInteger("POS_X"), preferences.getInteger("POS_Y"));
        hero.setSize(8, 8);
        hero.setOrigin(hero.getWidth() /2, hero.getHeight() /2);
        stage.addActor(hero);

        testRender = new RenderCollisionLine_Test(camera, hero);

        saveOrginalPosition();
    }

    @Override
    public void update(float dt) {
        cameraUpdate();
        hero.act(dt);
        uiUpdate();
        stageUi.act(dt);
        stageUi.draw();

        hero3D.render();

        if(windowStatsOpen) {
            stageStats.act(dt);
            stageStats.draw();
            stageCard.act(dt);
            stageCard.draw();
        }

        testRender.draw();
        //for(Character c: charactersList)
         //   RenderCollisionLine_Test.drawPublic(c.getCollision());
        //for(Rectangle r: entriaceToMapRectangle)
           // RenderCollisionLine_Test.drawPublic(r);

        //for(Vector2 v: Hero.temporaryListVector)
            //RenderCollisionLine_Test.drawPointSquare(v);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
            System.out.println("Hero: " + (hero.getX() + hero.getWidth() /2) + " " + (hero.getY() + hero.getHeight() /2));

        if(hero.isMoveStop())
            hero.objectCollision();

        if(hero.isAroundMove())
            hero.objectPointCollision();

        if(hero.isChangeTrack())
            hero.changeTrack();

        if(hero.isAnimation()) {
            hero.finishWalk();

            //TODO check are heroBox is in collision with entrience rectangle list
        }

        if(hero.isCharacterCollisionLook())
            hero.collisionCharacter();

        setPosX((int)hero.getX());
        setPosY((int)hero.getY());
    }

    @Override
    public void pause()   {
        preferences.putInteger("FREE_POINT", preferences.getInteger("FREE_POINT")).flush();
        preferences.putInteger("EXP", hero.getExp()).flush();
        preferences.putInteger("MONEY", hero.getMoneyNoStatic()).flush();
        preferences.putInteger("HP", hero.getHp()).flush();
        preferences.putFloat("ARMOR", hero.getArmor()).flush();
        preferences.putInteger("STRONG", hero.getStrong()).flush();
        preferences.putInteger("WIEDZA", hero.getWiedza()).flush();
        preferences.putInteger("DEFENSE_FIZ", hero.getDefenseFiz()).flush();
        preferences.putInteger("DEFENSE_MAG", hero.getDefenseMag()).flush();

        if(!bossInstance) {
            preferences.putInteger("POS_X", (int) hero.getX()).flush();
            preferences.putInteger("POS_Y", (int) hero.getY()).flush();
        }
    }

    public void clearCharacterList(){
        charactersList.clear();
        //TODO if enemy not desapeare with map try change this method to static and enemyList with this class too
    }

    private void initializeUiStage(){
        stageUi = new Stage(new FillViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageStats = new Stage(new FillViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageCard = new Stage(new FillViewport(VIEW_WIDTH, VIEW_HEIGHT));

        im = new InputMultiplexer(this, stageUi, stageStats, stageCard, stage);
        Gdx.input.setInputProcessor(im);

        card = asset.manager.get("sound/card.ogg", Sound.class);

        BitmapFont font = MyGdxGame.createDistanceFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        uiBackground = addImageToStageUi("uiBackground.png", 0, 430);
        uiBackground.setSize(VIEW_WIDTH, uiBackground.getHeight() + 3);
        addImageToStageUi("uiHp.png", 60, 455, ICON_ITEM_SIZE -2, ICON_ITEM_SIZE -2);
        addImageToStageUi("uiExp.png", 60, 430, ICON_ITEM_SIZE, ICON_ITEM_SIZE);
        addImageToStageUi("uiMoney.png", 194, 455, ICON_ITEM_SIZE -2, ICON_ITEM_SIZE -2);
        backgtoundLvl = addImageToStageUi("slotLvl.png", 7, 430, 53, 50);

        uiBarEmptyHp = addImageToStageUi("barEmpty.png", 85, 462, 102, 14);
        uiBarEmptyExp = addImageToStageUi("barEmpty.png", 85, 438, 180, 14);
        uiBarHp = addImageToStageUi("barHp.png", 86, 464, 100, 10);
        uiBarExp = addImageToStageUi("barExp.png", 86, 440, 100, 10);
        moneyLabel = addLabelToStageUi(220, 470, 0.4f);
        hpLabel = addLabelToStageUi(5, 0, 0.4f);
        expLabel = addLabelToStageUi(5, 0 , 0.4f);
        Label levelNameLabel = addLabelToStageUi(9, 463, 0.5f);
        levelNameLabel.setText("Poziom");
        levelLabel = addLabelToStageUi(0, 446, 0.6f);

        uiStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("uiStats.png")))));
        uiStats.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                if(windowStatsOpen){
                    stopGame = false;
                    windowStatsOpen = false;
                    stageStats.clear();
                    stageCard.clear();
                    card.play();
                }else{
                    windowStatsOpen = true;
                    stopGame = true;
                    new Bag(stageStats, stageCard, hero);
                    card.play();
                }
                System.out.println(windowStatsOpen);
                return false;
            }

        });
        uiStats.setPosition(267, 430);
        uiStats.setSize(50, 50);

        stageUi.addActor(uiStats);
    }

    private Label addLabelToStageUi(float posX, float posY, float fontScale){
        Label label = new Label("", labelStyle);
        label.setPosition(posX, posY);
        label.setFontScale(fontScale);
        stageUi.addActor(label);
        return label;
    }

    private Image addImageToStageUi(String path, float posX, float posY){
        Image image = new Image(asset.manager.get(path, Texture.class));
        image.setPosition(posX, posY);
        stageUi.addActor(image);
        return image;
    }

    private Image addImageToStageUi(String path, float posX, float posY, float sizeX, float sizeY){
        Image image = new Image(asset.manager.get(path, Texture.class));
        image.setPosition(posX, posY);
        image.setSize(sizeX, sizeY);
        stageUi.addActor(image);
        return image;
    }

    private void uiUpdate() {
        hpRefresh += Gdx.graphics.getDeltaTime();
        if(hpRefresh > 2){
            hero.setHp((int)(hero.getHp() +(hero.getFullHp() *0.02f)));

            if(hero.getHp() > hero.getFullHp())
                hero.setHp(hero.getFullHp());

            hpRefresh = 0;
        }

        if(hero.getHp() > hero.getFullHp())
            hero.setHp(hero.getFullHp());

        levelLabel.setText(String.valueOf(hero.getLevel()));
        if(hero.getLevel() < 10)
            levelLabel.setPosition(backgtoundLvl.getX() +backgtoundLvl.getWidth() /2 -5, levelLabel.getY());
        else
            levelLabel.setPosition(backgtoundLvl.getX() +backgtoundLvl.getWidth() /2 -10, levelLabel.getY());
        hpLabel.setText(hero.getHp() + " / " + hero.getFullHp());
        expLabel.setText(hero.getExp() + " / " + hero.getMaxExp());
        moneyLabel.setText(hero.getMoneyString());

        hpLabel.setPosition(uiBarEmptyHp.getX() + uiBarEmptyHp.getWidth() /2 - ((String.valueOf(hero.getHp()).length() + 3
                + String.valueOf(hero.getMaxHp()).length()) *3 +1), 463 + uiBarEmptyHp.getHeight() /2);
        expLabel.setPosition(uiBarEmptyExp.getX() + uiBarEmptyExp.getWidth() /2 - ((String.valueOf(hero.getExp()).length() + 3
                + String.valueOf(hero.getMaxExp()).length()) *3 +1) -1, 438 + uiBarEmptyExp.getHeight() /2);

        uiBarHp.setSize((float) hero.getHp() / hero.getFullHp() * 100, uiBarHp.getHeight());
        uiBarExp.setSize((float) hero.getExp() / hero.getMaxExp() * 100, uiBarExp.getHeight());
    }

    private void cameraUpdate() {
        camera.position.set(hero.getX() + hero.getOriginX(), hero.getY() + hero.getOriginY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, VIEW_WIDTH / 2, bgTexture.getWidth() - VIEW_WIDTH / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, VIEW_HEIGHT / 2, bgTexture.getHeight() - VIEW_HEIGHT / 2 + (VIEW_HEIGHT - 430));
        camera.update();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //System.out.println("x: " + screenX);
       // System.out.println("y: " + screenY);
    if(!stopGame && !Hero.getActiveMove()) {
            screenX /= realWidth;
            screenY /= realHeight;
            if (screenY > uiBackground.getHeight()) {
                float x = ((screenX + camera.position.x) - BaseMap.VIEW_WIDTH /2 - hero.getWidth() /2);
                float y = (((BaseMap.VIEW_HEIGHT + camera.position.y) - BaseMap.VIEW_HEIGHT /2) - screenY - hero.getHeight() /2);
                hero.move(x, y);
            }
        }
        return false;
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        stageUi.getViewport().update(width, height);

        if(windowStatsOpen) {
            stageStats.getViewport().update(width, height);
            stageCard.getViewport().update(width, height);
        }
    }

    public void dispose(){
        stageUi.dispose();
        stageStats.dispose();
        stageCard.dispose();
        card.dispose();
        asset.dispose();
        Hero3D.dispose();
        super.dispose();
    }

    public static BaseMap getActualMap(){
        return actualMap;
    }

    public static ArrayList<Character> setCharacterList(ArrayList<Character> characters){
        return charactersList = characters;
    }

    public static ArrayList<Rectangle> getEntriaceToMapRectangle(){
        return entriaceToMapRectangle;
    }

    public static ArrayList<Integer> getIndexToLoadNextMap(){
        return indexToLoadNextMap;
    }

    public static ArrayList<Vector2> getEntriencesPosition(){
        return entriencesPosition;
    }

    public static boolean isBossInstance(){
        return bossInstance;
    }

    public static Vector2 getDeadPosition(){
        return new Vector2(deadPosX, deadPosY);
    }
}