package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Bag;
import com.mygdx.game.Enemy;
import com.mygdx.game.Hero;
import com.mygdx.game.Hero3D;
import com.mygdx.game.Npc;
import com.mygdx.game.RenderCollisionLine_Test;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-06-04.
 */

public abstract class BaseMap extends BaseScreen {
    public final static int ICON_ITEM_SIZE = 25;

    protected int mapWidth;
    protected int mapHeight;
    private float realWidth;
    private float realHeight;

    public Rectangle leftRec;
    public Rectangle upRec;
    public Rectangle rightRec;
    public Rectangle bottomRec;

    protected static Hero hero;
    protected static Hero3D hero3D;
    protected static Image bgTexture;
    protected static BaseMap actualMap;

    protected InputMultiplexer im;

    public static Stage stageUi;
    private static Stage stageStats;
    private static Stage stageCard;
    private String bgSrc;

    private boolean windowStatsOpen;
    private boolean stopGame;

    private Image uiBackground;
    private Image uiBarEmptyHp, uiBarEmptyExp;
    private Image uiBarHp;
    private Image uiBarExp;
    private ImageButton uiStats;

    private Label.LabelStyle labelStyle;
    private Label moneyLabel;
    private Label hpLabel;
    private Label expLabel;
    private Label levelLabel;

    private RenderCollisionLine_Test testRender;

    protected ArrayList<Polygon> objectPolygon;
    protected ArrayList<Vector2[]> verticalPolygon;
    protected ArrayList<Enemy> enemyList;
    protected ArrayList<Npc> npcList;

    public BaseMap(Game game, int mapWidth, int mapHeight, String bgSrc) {
            super(game);
            this.mapWidth = mapWidth;
            this.mapHeight = mapHeight;
            this.bgSrc = bgSrc;

            this.leftRec = new Rectangle(-1, -1, 2, mapHeight);
            this.upRec = new Rectangle(-1, mapHeight - 1, mapWidth + 1, 2);
            this.rightRec = new Rectangle(mapWidth - 1, -1, 2, mapHeight);
            this.bottomRec = new Rectangle(-1, -1, mapWidth, 2);

            realWidth = (float) Gdx.app.getGraphics().getWidth() / VIEW_WIDTH;
            realHeight = (float) Gdx.app.getGraphics().getHeight() / VIEW_HEIGHT;

            windowStatsOpen = false;
            stopGame = false;

            create();
            initializeUiStage();
    }

    protected abstract void generateMap();
    protected abstract ArrayList<Enemy> getEnemies();

    @Override
    public void create() {
        hero3D = new Hero3D();
        hero3D.create();
        bgTexture = new Image(new Texture(Gdx.files.internal(bgSrc)));

        generateMap();

        hero = new Hero(new Texture(Gdx.files.internal("hero.png")), objectPolygon, verticalPolygon, camera, hero3D, enemyList, npcList, stage, game);
        hero.setPosition(410, 320);
        hero.setSize(10, 10);
        hero.setOrigin(hero.getWidth() /2, hero.getHeight() /2);
        stage.addActor(hero);

        testRender = new RenderCollisionLine_Test(camera, hero);
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
        for(Enemy e: enemyList)
            e.drawBox();

        for(Vector2 v: Hero.temporaryListVector)
            RenderCollisionLine_Test.drawPointSquare(v);

        //if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
          //  System.out.println("Hero: " + (hero.getX() + hero.getWidth() /2) + " " + (hero.getY() + hero.getHeight() /2));

        if(hero.isMoveStop())
            hero.objectCollision();

        if(hero.isAroundMove())
            hero.objectPointCollision();

        if(hero.isChangeTrack())
            hero.changeTrack();

        if(hero.isAnimation())
            hero.finishWalk();

        if(hero.isEnemyCollision())
            hero.collisionEnemy();

        //if(hero.isNpcCollision())
        //    hero.collisionNpc();

    }

    public void clearEnemyList(){
        enemyList.clear();
        //TODO if enemy not desapeare with map try change this method to static and enemyList with this class too
    }

    private void initializeUiStage(){
        stageUi = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageStats = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageCard = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));

        im = new InputMultiplexer(this, stageUi, stageStats, stageCard, stage);
        Gdx.input.setInputProcessor(im);

        BitmapFont font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        uiBackground = addImageToStageUi("uiBackground.png", 0, 430);
        uiBackground.setSize(VIEW_WIDTH, uiBackground.getHeight() + 3);
        addImageToStageUi("uiHp.png", 60, 455, ICON_ITEM_SIZE -2, ICON_ITEM_SIZE -2);
        addImageToStageUi("uiExp.png", 60, 430, ICON_ITEM_SIZE, ICON_ITEM_SIZE);
        addImageToStageUi("uiMoney.png", 194, 455, ICON_ITEM_SIZE -2, ICON_ITEM_SIZE -2);

        uiBarEmptyHp = addImageToStageUi("barEmpty.png", 80, 460, 118, 20);
        uiBarEmptyExp = addImageToStageUi("barEmpty.png", 80, 435, 118, 20);
        uiBarHp = addImageToStageUi("barHp.png", 86, 465, 100, 10);
        uiBarExp = addImageToStageUi("barExp.png", 86, 440, 100, 10);
        moneyLabel = addLabelToStageUi(220, 470, 0.9f);
        hpLabel = addLabelToStageUi(5, 0, 0.85f);
        expLabel = addLabelToStageUi(5, 0 , 0.85f);
        Label levelNameLabel = addLabelToStageUi(11, 463, 1);
        levelNameLabel.setText("LEVEL");
        levelLabel = addLabelToStageUi(23, 446, 1.25f);

        uiStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("uiStats.png")))));
        uiStats.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                if(windowStatsOpen){
                    stopGame = false;
                    windowStatsOpen = false;
                    stageStats.clear();
                    stageCard.clear();
                }else{
                    windowStatsOpen = true;
                    stopGame = true;
                    new Bag(stageStats, stageCard, hero);
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
        Image image = new Image(new Texture(Gdx.files.internal(path)));
        image.setPosition(posX, posY);
        stageUi.addActor(image);
        return image;
    }

    private Image addImageToStageUi(String path, float posX, float posY, float sizeX, float sizeY){
        Image image = new Image(new Texture(Gdx.files.internal(path)));
        image.setPosition(posX, posY);
        image.setSize(sizeX, sizeY);
        stageUi.addActor(image);
        return image;
    }

    private void uiUpdate() {
        levelLabel.setText(String.valueOf(hero.getLevel()));
        hpLabel.setText(hero.getHp() + " / " + hero.getFullHp());
        expLabel.setText(hero.getExp() + " / " + hero.getMaxExp());
        moneyLabel.setText(hero.getMoneyString());

        hpLabel.setPosition(uiBarEmptyHp.getX() + uiBarEmptyHp.getWidth() /2 - ((String.valueOf(hero.getHp()).length() + 3
                + String.valueOf(hero.getMaxHp()).length()) *3 +1), 460 + uiBarEmptyHp.getHeight() /2);
        expLabel.setPosition(uiBarEmptyExp.getX() + uiBarEmptyExp.getWidth() /2 - ((String.valueOf(hero.getExp()).length() + 3
                + String.valueOf(hero.getMaxExp()).length()) *3 +1), 435 + uiBarEmptyExp.getHeight() /2);

        uiBarHp.setSize((float) hero.getHp() / hero.getFullHp() * 100, uiBarHp.getHeight());
        uiBarExp.setSize((float) hero.getExp() / hero.getMaxExp() * 100, uiBarExp.getHeight());
    }

    private void cameraUpdate() {
        camera.position.set(hero.getX() + hero.getOriginX(), hero.getY() + hero.getOriginY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, VIEW_WIDTH / 2, Map_01.MAP_WIDTH - VIEW_WIDTH / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, VIEW_HEIGHT / 2, Map_01.MAP_HEIGHT - VIEW_HEIGHT / 2 + (VIEW_HEIGHT - 430));
        camera.update();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    if(!stopGame && !Hero.getActiveMove()) {
            screenX /= realWidth;
            screenY /= realHeight;
            if (screenY > uiBackground.getHeight()) {
                float x = ((screenX + camera.position.x) - BaseMap.VIEW_WIDTH /2 - hero.getWidth() /2);
                float y = (((BaseMap.VIEW_HEIGHT + camera.position.y) - BaseMap.VIEW_HEIGHT /2) - screenY - hero.getHeight() /2);
                hero.move(x, y);
                //hero3D.moveRotate(screenX, screenY);
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
        hero3D.dispose();
    }

    public static BaseMap getActualMap(){
        return actualMap;
    }
}