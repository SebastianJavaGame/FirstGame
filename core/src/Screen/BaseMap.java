package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Equipment;
import com.mygdx.game.Hero;
import com.mygdx.game.Npc;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-06-04.
 */

public abstract class BaseMap extends BaseScreen {
    public final static int SPEED_MOVE = 25;
    public final static int ICON_ITEM_SIZE = 25;

    protected int mapWidth;
    protected int mapHeight;
    private float realWidth;
    private float realHeight;

    public Rectangle leftRec;
    public Rectangle upRec;
    public Rectangle rightRec;
    public Rectangle bottomRec;

    protected Hero hero;
    protected ArrayList<Npc> npcList;
    protected Image bgTexture;

    private Stage stageUi;
    private Stage stageStats;
    private Stage stageCard;
    private String bgSrc;

    private boolean windowStatsOpen;
    private boolean stopGame;

    private Image uiBackground;
    private Image uiExp;
    private Image uiHp;
    private Image uiMoney;
    private Image uiBarEmptyHp, uiBarEmptyExp;
    private Image uiBarHp;
    private Image uiBarExp;

    private ImageButton uiStats;

    private BitmapFont font;
    private Label.LabelStyle labelStyle;
    private Label levelNameLabel;
    private Label moneyLabel;
    private Label hpLabel;
    private Label expLabel;
    private Label levelLabel;

    public abstract void generateMap();
    public abstract void collisionEndMap();

    public BaseMap(Game game, int mapWidth, int mapHeight, String bgSrc) {
        super(game);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.bgSrc = bgSrc;

        this.leftRec = new Rectangle(-1, -1, 2, mapHeight);
        this.upRec = new Rectangle(-1, mapHeight -1, mapWidth +1, 2);
        this.rightRec = new Rectangle(mapWidth -1, -1, 2, mapHeight);
        this.bottomRec = new Rectangle(-1, -1, mapWidth, 2);

        realWidth = (float)Gdx.app.getGraphics().getWidth() / VIEW_WIDTH;
        realHeight = (float)Gdx.app.getGraphics().getHeight() / VIEW_HEIGHT;

        windowStatsOpen = false;
        stopGame = false;

        create();
    }

    @Override
    public void create() {
        npcList = new ArrayList<Npc>();
        hero = new Hero(this, new Texture(Gdx.files.internal("badlogic.jpg")), 100);
        bgTexture = new Image(new Texture(Gdx.files.internal(bgSrc)));

        generateMap();
        initializeUiStage();
    }

    @Override
    public void update(float dt) {
        collisionDetected();
        cameraUpdate();
        hero.act(dt);

        uiUpdate();
        stageUi.act(dt);
        stageUi.draw();

        if(windowStatsOpen) {
            stageStats.act(dt);
            stageStats.draw();
            stageCard.act(dt);
            stageCard.draw();
        }
    }

    private void initializeUiStage(){
        stageUi = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageStats = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));
        stageCard = new Stage(new FitViewport(VIEW_WIDTH, VIEW_HEIGHT));

        InputMultiplexer im = new InputMultiplexer(this, stageUi, stageStats, stageCard);
        Gdx.input.setInputProcessor(im);

        font = new BitmapFont();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        uiBackground = new Image(new Texture(Gdx.files.internal("uiBackground.png")));
        uiBackground.setPosition(0, 430);
        uiBackground.setSize(VIEW_WIDTH, uiBackground.getHeight());

        uiHp = new Image(new Texture(Gdx.files.internal("uiHp.png")));
        uiHp.setPosition(55, 455);
        uiHp.setSize(ICON_ITEM_SIZE, ICON_ITEM_SIZE);

        uiExp = new Image(new Texture(Gdx.files.internal("uiExp.png")));
        uiExp.setPosition(55, 430);
        uiExp.setSize(ICON_ITEM_SIZE, ICON_ITEM_SIZE);

        uiMoney = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
        uiMoney.setPosition(190, 455);
        uiMoney.setSize(ICON_ITEM_SIZE, ICON_ITEM_SIZE);

        uiStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("uiStats.png")))));
        uiStats.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                if(windowStatsOpen){
                    stopGame = false;
                    windowStatsOpen = false;
                }else{
                    windowStatsOpen = true;
                    stopGame = true;
                    new Equipment(stageStats, stageCard, hero);
                }
                System.out.println(windowStatsOpen);
                return false;
            }

        });
        uiStats.setPosition(260, 430);
        uiStats.setSize(50, 50);

        uiBarEmptyHp = new Image(new Texture(Gdx.files.internal("barEmpty.png")));
        uiBarEmptyHp.setPosition(75, 460);
        uiBarEmptyHp.setSize(118, 20);

        uiBarEmptyExp = new Image(new Texture(Gdx.files.internal("barEmpty.png")));
        uiBarEmptyExp.setPosition(75, 435);
        uiBarEmptyExp.setSize(118, 20);

        uiBarHp = new Image(new Texture(Gdx.files.internal("barHp.png")));
        uiBarHp.setPosition(81, 465);
        uiBarHp.setSize(100, 10);

        uiBarExp = new Image(new Texture(Gdx.files.internal("barExp.png")));
        uiBarExp.setPosition(81, 440);
        uiBarExp.setSize(100, 10);

        moneyLabel = new Label("", labelStyle);
        moneyLabel.setPosition(215, 470);
        moneyLabel.setFontScale(0.9f);

        hpLabel = new Label("", labelStyle);
        hpLabel.setFontScale(0.85f);

        expLabel = new Label("", labelStyle);
        expLabel.setFontScale(0.85f);

        levelNameLabel = new Label("Poziom", labelStyle);
        levelNameLabel.setPosition(5, 460);
        levelNameLabel.setFontScale(1);

        levelLabel = new Label("", labelStyle);
        levelLabel.setPosition(20, 450);
        levelLabel.setFontScale(1.25f);

        stageUi.addActor(uiBackground);
        stageUi.addActor(uiHp);
        stageUi.addActor(uiExp);
        stageUi.addActor(uiMoney);
        stageUi.addActor(uiStats);
        stageUi.addActor(uiBarEmptyHp);
        stageUi.addActor(uiBarEmptyExp);
        stageUi.addActor(uiBarHp);
        stageUi.addActor(uiBarExp);
        stageUi.addActor(moneyLabel);
        stageUi.addActor(hpLabel);
        stageUi.addActor(expLabel);
        stageUi.addActor(levelLabel);
        stageUi.addActor(levelNameLabel);
    }

    private void uiUpdate() {
        levelLabel.setText(String.valueOf(hero.getLevel()));
        hpLabel.setText(hero.getHp() + " / " + hero.getMaxHp());
        expLabel.setText(hero.getExp() + " / " + hero.getMaxExp());
        moneyLabel.setText(hero.getMoney());

        hpLabel.setPosition(uiBarEmptyHp.getX() + uiBarEmptyHp.getWidth() /2 - ((String.valueOf(hero.getHp()).length() + 3
                + String.valueOf(hero.getMaxHp()).length()) *3 +1), 460 + uiBarEmptyHp.getHeight() /2);
        expLabel.setPosition(uiBarEmptyExp.getX() + uiBarEmptyExp.getWidth() /2 - ((String.valueOf(hero.getExp()).length() + 3
                + String.valueOf(hero.getMaxExp()).length()) *3 +1), 435 + uiBarEmptyExp.getHeight() /2);

        updateImageBarHp();
        updateImageBarExp();
    }

    private void cameraUpdate() {
        camera.position.set(hero.getX() + hero.getOriginX(), hero.getY() + hero.getOriginY(), 0);
        camera.position.x = MathUtils.clamp(camera.position.x, VIEW_WIDTH / 2, Map_01.MAP_WIDTH - VIEW_WIDTH / 2);
        camera.position.y = MathUtils.clamp(camera.position.y, VIEW_HEIGHT / 2, Map_01.MAP_HEIGHT - VIEW_HEIGHT / 2 + (VIEW_HEIGHT - 430));
        camera.update();
    }

    private void collisionDetected(){
        hero.collisionUpdate();
        npcList.get(0).collisionUpdate();

        if(hero.collision.overlaps(npcList.get(0).collision)){
            npcList.get(0).collisionDo();
        }

        if(hero.collision.overlaps(leftRec)
                || hero.collision.overlaps(upRec)
                || hero.collision.overlaps(rightRec)
                || hero.collision.overlaps(bottomRec)){
            collisionEndMap();
        }
        //TODO past code in for-each to npcList (0, 1 ... n-1, n)
    }

    private void setPositionHero(float screenX, float screenY){
        float x = ((screenX + camera.position.x) - VIEW_WIDTH /2 - hero.getTexture().getRegionWidth() /2);
        float y = (((VIEW_HEIGHT + camera.position.y) - VIEW_HEIGHT /2) - screenY - hero.getTexture().getRegionHeight() /2);
        float duration = timeSpeed(x, y);
        hero.clearActions();
        hero.addAction(Actions.moveTo(x, y, duration));
    }

    private float timeSpeed(float positionX, float positionY){
        float distanceX = positionX - hero.getX();
        float distanceY = positionY - hero.getY();
        double duration;

        if(distanceX < 0)
            distanceX = hero.getX() - positionX;
        if (distanceY < 0)
            distanceY = hero.getY() - positionY;

        if(distanceX < distanceY){
            duration = distanceY / SPEED_MOVE + (distanceX * 0.40) / SPEED_MOVE;
        }else{
            duration = distanceX / SPEED_MOVE + (distanceY * 0.40) / SPEED_MOVE;
        }

        return (float) duration;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(!stopGame) {
            screenX /= realWidth;
            screenY /= realHeight;
            if (screenY > uiBackground.getHeight())
                setPositionHero(screenX, screenY);
        }
        return false;
    }

    public void updateImageBarHp(){
        uiBarHp.setSize((float) hero.getHp() / hero.getMaxHp() * 100, uiBarHp.getHeight());
    }

    public void updateImageBarExp(){
        uiBarExp.setSize((float) hero.getExp() / hero.getMaxExp() * 100, uiBarExp.getHeight());
    }

    public void resize(int width, int height) {
        mapStage.getViewport().update(width, height, true);
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
    }
}