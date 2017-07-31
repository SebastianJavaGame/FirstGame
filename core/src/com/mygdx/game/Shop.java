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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;
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

    private static boolean active = false;

    public Shop(Game g, Image image, String name, int level, int idShop){
        super(g);
        this.stage = BaseScreen.getStage();
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        buttonMenu = new Button[8];
        new BaseShopDepartaments();

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
                    addItemToBag(LoadAllItemToGame.getItem(value), i);
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

    public void addItemToBag(final Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (slotNr == slot) {
                    item.getImage().setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    item.setStan(Item.Stan.BAG);
                    item.getImage().addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            imageAddListener(item, item.getPathImage());
                            return false;
                        }
                    });
                    Equipment.setSlotEmpty(slotNr, true);

                    PREF.putString("SLOT" + slotNr, item.getItemKey());
                    PREF.flush();

                    System.out.println(item.getItemKey());

                    stage.addActor(item.getImage());
                    return;
                }
                slotNr++;
            }
        }
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

    private void imageAddListener(final Item item, String pathImage) {
        if(active) {
            Transaction.updateSellTouchable(true);
            Transaction.updateBuyButton(false);
            Transaction.setBackEnabled(false);

            TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
            styleButton.font = font;
            styleButton.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonBack.png"))));
            final TextButton bClose = new TextButton("Zamknij", styleButton);

            final Image backgroundUp = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
            final Image itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));

            final Label itemName = new Label("" + item.getItemName(), style);
            final Label itemType = new Label("" + item.getItemType().toString(), style);
            final Label itemHp = new Label("Hp: +" + item.getHp(), style);
            final Label itemStrong = new Label("Strong: +" + item.getStrong(), style);
            final Label itemWiedza = new Label("Wiedza: +" + item.getWiedza(), style);
            final Label itemArmor = new Label("Armor: +" + item.getArmor() + "%", style);
            final Label itemDefenseFiz = new Label("Defense physics: +" + item.getDefenseFiz(), style);
            final Label itemDefenseMag = new Label("Defense magic: +" + item.getDefenseMag(), style);
            final Label itemPrice = new Label("" + item.getCashValue(), style);

            final Image money = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
            final Image itemBackground = new Image(new Texture(Gdx.files.internal("slotInfoItem.png")));
            final Image barName = new Image(new Texture(Gdx.files.internal("nameBar.png")));
            final Image barPrice = new Image(new Texture(Gdx.files.internal("barX.png")));

            backgroundUp.setBounds(0, 230, BaseMap.VIEW_WIDTH, 190);
            itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
            itemName.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 160);
            itemType.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 132);
            itemHp.setPosition(20, backgroundUp.getY() + 100);
            itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 100);
            itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 70);
            itemWiedza.setPosition(20, backgroundUp.getY() + 70);
            itemDefenseFiz.setPosition(20, backgroundUp.getY() + 40);
            itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 40);
            itemPrice.setPosition(BaseScreen.VIEW_WIDTH /2 -itemPrice.getWidth()/2 -10, backgroundUp.getY() + 10);
            money.setBounds(itemPrice.getX() +itemPrice.getWidth() +7, itemPrice.getY(), 18, 17);
            barName.setBounds(itemName.getX() - 20, itemName.getY() - 16, itemName.getWidth() + 40, itemName.getHeight() + 30);
            itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
            barPrice.setBounds(0, itemPrice.getY() - 4, BaseMap.VIEW_WIDTH + 15, 25);
            bClose.setPosition(BaseScreen.VIEW_WIDTH /2 - bClose.getWidth() /2, itemPrice.getY() -7);

            bClose.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Transaction.updateSellButton(true);
                    Transaction.updateBuyButton(true);
                    Transaction.updateSellTouchable(false);
                    Transaction.setBackEnabled(true);
                    backgroundUp.remove();
                    itemImage.remove();
                    itemName.remove();
                    itemType.remove();
                    itemHp.remove();
                    itemArmor.remove();
                    itemStrong.remove();
                    itemWiedza.remove();
                    itemDefenseFiz.remove();
                    itemDefenseMag.remove();
                    itemPrice.remove();
                    money.remove();
                    barName.remove();
                    barPrice.remove();
                    itemBackground.remove();
                    bClose.remove();
                    return false;
                }
            });

            bClose.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(1)));
            itemPrice.addAction(Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    itemPrice.setText("" + item.getCashValue());
                    //itemPrice.setPosition(BaseScreen.VIEW_WIDTH /2 -itemPrice.getWidth()/2 -10, backgroundUp.getY() + 10);
                    //money.setPosition(itemPrice.getX() +itemPrice.getWidth() +7, itemPrice.getY());
                }
            }),Actions.moveBy(0, -50, 1.2f)));
            money.addAction(Actions.moveBy(0, -50, 1.2f));

            addActors(backgroundUp, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong, itemWiedza,
                    itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, bClose, money, itemPrice);
        }
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

    public static void setActive(boolean setActive){
        active = setActive;
    }
}