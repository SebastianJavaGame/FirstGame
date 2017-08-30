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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Asset;
import com.mygdx.game.BaseShopDepartaments;
import com.mygdx.game.Equipment;
import com.mygdx.game.FuncionalityShop;
import com.mygdx.game.Hero;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.MyGdxGame;

/**
 * Created by Sebastian on 2017-07-30.
 */

public class Shop extends BaseScreen{
    private final Preferences PREF = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
    private Asset asset = new Asset();
    private Image background;
    private Image backgroundMenu;
    private ImageButton close;
    public static final int POS_Y_NEXT_BACKGROUND = 164;

    private static final BitmapFont font = MyGdxGame.createDistanceFont();
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
        asset.loadShop();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            background = new Image(asset.manager.get("shopBackground.png", Texture.class));
            backgroundMenu = new Image(asset.manager.get("shopMenuBackground.png", Texture.class));
            close = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonCancel.png", Texture.class))));

            lName = new Label(name, style);
            lLevel = new Label("Poziom " + level, style);

            lName.setFontScale(0.6f);
            lLevel.setFontScale(0.6f);

            image.setPosition(20, BaseScreen.VIEW_HEIGHT - image.getHeight() + 5);
            int lengthX = 135;
            lName.setPosition(lengthX - lName.getWidth()*0.6f / 2, BaseScreen.VIEW_HEIGHT - 28);
            lLevel.setPosition(lengthX - lLevel.getWidth()*0.6f / 2, BaseScreen.VIEW_HEIGHT - 46);

            close.setSize(55, 55);
            close.setPosition(BaseScreen.VIEW_WIDTH - close.getWidth() - 25, BaseScreen.VIEW_HEIGHT - close.getHeight() + 6);
            close.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    Hero.setActiveMove(false);
                    Menu.setMap();
                    return false;
                }
            });

            addActors(background, close, image, lName, lLevel);

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
    }

    private void createShopMenu() {
        backgroundMenu.setPosition(-10, POS_Y_NEXT_BACKGROUND);
        addActors(backgroundMenu);

        buttonMenu[0] = createButton(20, 365);
        buttonMenu[0].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(0, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[1] = createButton(20, 305);
        buttonMenu[1].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(1, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[2] = createButton(20, 245);
        buttonMenu[2].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(2, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[3] = createButton(20, 185);
        buttonMenu[3].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(3, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[4] = createButton(160, 365);
        buttonMenu[4].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(4, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[5] = createButton(160, 305);
        buttonMenu[5].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(5, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[6] = createButton(160, 245);
        buttonMenu[6].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(6, image, name, level, idShop);
                return false;
            }
        });

        buttonMenu[7] = createButton(160, 185);
        buttonMenu[7].addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Menu.getSoundClick().play();
                removeMenu();
                new Transaction(7, image, name, level, idShop);
                return false;
            }
        });
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
        backgroundMenu.remove();
        for(Button b: buttonMenu)
            b.remove();
    }
}