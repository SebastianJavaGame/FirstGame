package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseScreen;
import Screen.Map_01;

/**
 * Created by Sebastian on 2017-07-30.
 */

public class Shop extends BaseScreen{
    private final Preferences PREF = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
    private final Image BACKGROUND = new Image(new Texture(Gdx.files.internal("shopBackground.png")));
    private final Image BACKGROUND_MENU = new Image(new Texture(Gdx.files.internal("shopMenuBackground.png")));
    private final ImageButton CLOSE = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonCancel.png")))));
    public static final int POS_Y_NEXT_BACKGROUND = 164;

    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    static {
        style.font = font;
    }

    private Stage stage;

    private Image image;
    private String name;
    private int level;
    private int idShop;

    private Label lName;
    private Label lLevel;
    private Button[] buttonMenu;

    public Shop(Game g, Image image, String name, int level, int idShop){
        super(g);
        this.stage = BaseScreen.getStage();
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        buttonMenu = new Button[8];
        new BaseShopDepartaments();
        new FuncionalityShop();

        create();
    }

    @Override
    public void create() {
        lName = new Label(name, style);
        lLevel = new Label("Poziom " +level, style);

        image.setPosition(20, BaseScreen.VIEW_HEIGHT -image.getHeight() +5);
        int lengthX = 135;
        lName.setPosition(lengthX -lName.getWidth() /2, BaseScreen.VIEW_HEIGHT -20);
        lLevel.setPosition(lengthX -lLevel.getWidth() /2, BaseScreen.VIEW_HEIGHT -38);

        CLOSE.setSize(55, 55);
        CLOSE.setPosition(BaseScreen.VIEW_WIDTH -CLOSE.getWidth() -25, BaseScreen.VIEW_HEIGHT -CLOSE.getHeight() +6);
        CLOSE.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Hero.setActiveMove(false);
                BaseScreen.getGame().setScreen(new Map_01(BaseScreen.getGame()));
                return false;
            }
        });

        addActors(BACKGROUND, CLOSE, image, lName, lLevel);

        //load item with eq to inventory
        Equipment.slotEmpty = new boolean[18];
        for (int i = 0; i < 18; i++) {
            String value = PREF.getString("SLOT" + i, "");

            if (!value.equals(""))
                try {
                    FuncionalityShop.addItemToBag(LoadAllItemToGame.getItem(value), i);
                } catch (CloneNotSupportedException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }
            else
                Equipment.setSlotEmpty(i, false);
        }

        createShopMenu();
        /*
        int positionXFirstColumn = 50;
        int positionXSecondColumn = 200;
        Label lHelmet = new Label("Helmy", style);
        Label lArmor = new Label("Zbroje", style);
        Label lPants = new Label("Spodnie", style);
        Label lShoes = new Label("Buty", style);
        Label lWapon = new Label("Bronie", style);
        Label lItemBlock = new Label("Tarcze", style);
        Label lItemHand = new Label("Rekawice", style);
        Label lRing = new Label("Pierscienie", style);

        lHelmet.setPosition(positionXFirstColumn, POS_Y_NEXT_BACKGROUND +150);
        lArmor.setPosition(positionXFirstColumn, POS_Y_NEXT_BACKGROUND +120);
        lPants.setPosition(positionXFirstColumn, POS_Y_NEXT_BACKGROUND +80);
        lShoes.setPosition(positionXFirstColumn, POS_Y_NEXT_BACKGROUND +40);
        lWapon.setPosition(positionXSecondColumn, POS_Y_NEXT_BACKGROUND +150);
        lItemBlock.setPosition(positionXSecondColumn, POS_Y_NEXT_BACKGROUND +120);
        lItemHand.setPosition(positionXSecondColumn, POS_Y_NEXT_BACKGROUND +80);
        lRing.setPosition(positionXSecondColumn, POS_Y_NEXT_BACKGROUND +40);
        //addActors(BACKGROUND_MENU, lHelmet, lArmor, lPants, lShoes, lWapon, lItemBlock, lItemHand, lRing);
        */
    }

    private void createShopMenu() {
        BACKGROUND_MENU.setPosition(-10, POS_Y_NEXT_BACKGROUND);
        addActors(BACKGROUND_MENU);

        buttonMenu[0] = createButton(20, 365);
        buttonMenu[0].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                removeMenu();
                new Transaction(0, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[1] = createButton(20, 305);
        buttonMenu[1].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                removeMenu();
                new Transaction(1, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[2] = createButton(20, 245);
        buttonMenu[3] = createButton(20, 185);

        buttonMenu[4] = createButton(160, 365);
        buttonMenu[4].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                removeMenu();
                new Transaction(4, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[5] = createButton(160, 305);
        buttonMenu[6] = createButton(160, 245);
        buttonMenu[7] = createButton(160, 185);
    }

    @Override
    public void update(float dt) {
    }

    private Button createButton(int x, int y){
        Button button = new Button(new Button.ButtonStyle());
        button.setX(x);
        button.setY(y);
        button.setWidth(140);
        button.setHeight(55);
        stage.addActor(button);
        return button;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {//TODO to delete
        System.out.println(screenX);
        System.out.println(screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    private void addActors(Actor... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }

    private void removeMenu(){
        BACKGROUND_MENU.remove();
        for(Button b: buttonMenu)
            b.remove();
    }
}