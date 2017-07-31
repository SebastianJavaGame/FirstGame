package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-30.
 */

class Transaction {
    private static final Image BACKGROUND_TRANSACTION = new Image(new Texture(Gdx.files.internal("shopBackgroundTransaction.png")));
    private static final BitmapFont FONT = new BitmapFont();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_BUY = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_SELL = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_BACK = new TextButton.TextButtonStyle();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private final Preferences preferences = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
    private static boolean firstClick = true;
    private static Stage stage;

    static {
        style.font = FONT;
        STYLE_TRANSACTION_BUY.font = FONT;
        STYLE_TRANSACTION_BUY.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonTransaction.png"))));
        STYLE_TRANSACTION_SELL.font = FONT;
        STYLE_TRANSACTION_SELL.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonTransaction.png"))));
        STYLE_BACK.font = FONT;
        STYLE_BACK.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonBack.png"))));
    }

    private static Label lTextLeft;
    private static Label lMoneyLeft;
    private static Image iMoneyLeft;
    private static Label lTextRight;
    private static Label lMoneyRight;
    private static Label lPrice;
    private static Image iMoneyRight;

    private Image backgroundUp;
    private Image itemImage;
    private Label itemName;
    private Label itemType;
    private Label itemHp;
    private Label itemStrong;
    private Label itemWiedza;
    private Label itemArmor;
    private Label itemDefenseFiz;
    private Label itemDefenseMag;
    private Label itemPrice;
    private TextButton bClose;
    private Image money;
    private Image itemBackground;
    private Image barName;
    private Image barPrice;

    private Image image;
    private String name;
    private int level;
    private int idShop;

    private static TextButton bBack;
    private static TextButton bBuy;
    private static TextButton bSell;

    private boolean[] emptySlotShop;

    private static String actualItemName;
    private static int actualSlotNr;

    public Transaction(int itemGroup, final Image image, final String name, final int level, final int idShop) {
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        emptySlotShop = new boolean[10];
        stage = BaseScreen.getStage();

        FuncionalityShop.setActive(true);

        BACKGROUND_TRANSACTION.setPosition(0, Shop.POS_Y_NEXT_BACKGROUND);

        bBack = new TextButton("Wroc do menu", STYLE_BACK);
        bBuy = new TextButton("Kup", STYLE_TRANSACTION_BUY);
        bSell = new TextButton("Sprzedaj", STYLE_TRANSACTION_BUY);

        bBack.setPosition(174, 238);
        bBuy.setPosition(18, Shop.POS_Y_NEXT_BACKGROUND + 10);
        bSell.setPosition(214, Shop.POS_Y_NEXT_BACKGROUND + 10);

        addActors(BACKGROUND_TRANSACTION, bBack, bBuy, bSell);

        String[] itemList = BaseShopDepartaments.getItemsFromDepartament(idShop, itemGroup);
        showEq(itemList);

        bBack.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.clear();
                firstClick = true;
                BaseScreen.getGame().setScreen(new Shop(BaseScreen.getGame(), image, name, level, idShop));
                return false;
            }
        });

        bBuy.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Click BUY");
                for(int i = 0; i < 18; i++){
                    String value = preferences.getString("SLOT" +i, "");

                    if(value.equals("")){
                        try {
                            addItemToBag(LoadAllItemToGame.getItem(actualItemName), i);
                            preferences.putString("SLOT" +i, actualItemName);
                            preferences.flush();
                            removeAll();
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    if(i == 17){
                        Label label = new Label("Brak pustego miejsca w ekwipunku", style);
                        label.setFontScale(2);
                        label.setPosition(BaseScreen.VIEW_WIDTH /2 - label.getWidth() /2, BaseScreen.VIEW_HEIGHT /2);
                        label.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(0.6f), Actions.delay(1), Actions.fadeOut(0.4f)));
                        stage.addActor(label);
                    }
                }
                return false;
            }
        });

        bSell.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Click SELL");
                return false;
            }
        });

        showHeroMoney();
        setHeroMoneyVisibleLeft(false);
        setHeroMoneyVisibleRight(false);
        updateSellTouchable(false);
        updateBuyToutchable(false);
    }

    private void showEq(String[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            String value = itemList[i];
            System.out.println(value);
            if (!value.equals(""))
                try {
                    addItemToShop(LoadAllItemToGame.getItem(value), i);
                } catch (CloneNotSupportedException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }
        }
    }

    public void addItemToShop(final Item item, int slot) {
        if (slot > 10)
            return;

        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (slotNr == slot) {
                    item.getImage().setBounds(31 + (j * 20) + j * Item.BLOCK_SIZE, (i * 19) + i * Item.BLOCK_SIZE + 231, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    item.getImage().addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            imageAddListener(item, item.getPathImage());
                            return false;
                        }
                    });
                    Equipment.setSlotEmpty(slotNr, true);
                    stage.addActor(item.getImage());
                    return;
                }
                slotNr++;
            }
        }
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

                    stage.addActor(item.getImage());
                    return;
                }
                slotNr++;
            }
        }
    }

    public void imageAddListener(final Item item, String pathImage) {
        removeAll();
        firstClick = false;
        actualItemName = item.getItemKey();

        setHeroMoneyVisibleRight(true);
        Transaction.updateBuyToutchable(true);
        Transaction.updateSellButton(false);

        TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
        styleButton.font = FONT;
        styleButton.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonBack.png"))));
        bClose = new TextButton("Zamknij", styleButton);

        backgroundUp = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));

        itemName = new Label("" + item.getItemName(), style);
        itemType = new Label("" + item.getItemType().toString(), style);
        itemHp = new Label("Hp: +" + item.getHp(), style);
        itemStrong = new Label("Strong: +" + item.getStrong(), style);
        itemWiedza = new Label("Wiedza: +" + item.getWiedza(), style);
        itemArmor = new Label("Armor: +" + item.getArmor() + "%", style);
        itemDefenseFiz = new Label("Defense physics: +" + item.getDefenseFiz(), style);
        itemDefenseMag = new Label("Defense magic: +" + item.getDefenseMag(), style);
        itemPrice = new Label("" + item.getCashValue(), style);
        lPrice = new Label("Cena", style);

        money = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
        itemBackground = new Image(new Texture(Gdx.files.internal("slotInfoItem.png")));
        barName = new Image(new Texture(Gdx.files.internal("nameBar.png")));
        barPrice = new Image(new Texture(Gdx.files.internal("barX.png")));

        backgroundUp.setBounds(0, -16, BaseMap.VIEW_WIDTH, 190);
        itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
        itemName.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 160);
        itemType.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 132);
        itemHp.setPosition(20, backgroundUp.getY() + 100);
        itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 100);
        itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 70);
        itemWiedza.setPosition(20, backgroundUp.getY() + 70);
        itemDefenseFiz.setPosition(20, backgroundUp.getY() + 40);
        itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 40);
        itemPrice.setPosition(BaseScreen.VIEW_WIDTH / 2 - itemPrice.getWidth() / 2 - 10, backgroundUp.getY() + 10);
        money.setBounds(itemPrice.getX() + itemPrice.getWidth() + 7, itemPrice.getY(), 18, 17);
        barName.setBounds(itemName.getX() - 20, itemName.getY() - 16, itemName.getWidth() + 40, itemName.getHeight() + 30);
        itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
        barPrice.setBounds(0, itemPrice.getY() - 4, BaseMap.VIEW_WIDTH + 15, 25);
        bClose.setPosition(BaseScreen.VIEW_WIDTH / 2 - bClose.getWidth() / 2, itemPrice.getY() - 5);
        lPrice.setPosition(BaseScreen.VIEW_WIDTH /2 -lPrice.getWidth() /2, Shop.POS_Y_NEXT_BACKGROUND +35);

        bClose.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                updateBuyToutchable(false);
                removeAll();
                return false;
            }
        });

        itemPrice.addAction(Actions.sequence(Actions.run(new Runnable() {
            @Override
            public void run() {
                itemPrice.setText("" + item.getCashValue());
                itemPrice.setColor(Color.GOLD);
                itemPrice.setPosition(itemPrice.getX(), Shop.POS_Y_NEXT_BACKGROUND -15);
                money.setPosition(money.getX(), Shop.POS_Y_NEXT_BACKGROUND -15);
            }
        }), Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, 34, 0.8f)))));
        money.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, 34, 0.8f))));
        lPrice.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(0.6f), Actions.fadeIn(0.3f)));

        addActors(backgroundUp, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong, itemWiedza,
                itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, bClose, money, itemPrice, lPrice);

    }

    public void showHeroMoney() {
        lTextLeft = new Label("Moje zloto ", style);
        lMoneyLeft = new Label("" + Hero.getMoney(), style);
        iMoneyLeft = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
        lTextRight = new Label("Moje zloto ", style);
        lMoneyRight = new Label("" + Hero.getMoney(), style);
        iMoneyRight = new Image(new Texture(Gdx.files.internal("uiMoney.png")));

        lMoneyLeft.setColor(Color.GOLD);
        iMoneyLeft.setSize(20, 20);

        lMoneyRight.setColor(Color.GOLD);
        iMoneyRight.setSize(20, 20);

        lTextLeft.setPosition(bBuy.getX() + bBuy.getWidth() / 2 - lTextLeft.getWidth() / 2, bBuy.getY() + 25);
        lMoneyLeft.setPosition(bBuy.getX() + bBuy.getWidth() / 2 - lMoneyLeft.getWidth() / 2 - iMoneyLeft.getWidth() / 2, bBuy.getY() +8);
        iMoneyLeft.setPosition(lMoneyLeft.getX() + lMoneyLeft.getWidth() + 5, lMoneyLeft.getY() -3);

        lTextRight.setPosition(bSell.getX() + bSell.getWidth() / 2 - lTextRight.getWidth() / 2, bSell.getY() + 25);
        lMoneyRight.setPosition(bSell.getX() + bSell.getWidth() / 2 - lMoneyRight.getWidth() / 2 - iMoneyRight.getWidth() / 2, bSell.getY() +8);
        iMoneyRight.setPosition(lMoneyRight.getX() + lMoneyRight.getWidth() + 5, lMoneyRight.getY() -3);

        addActors(lTextLeft, lMoneyLeft, iMoneyLeft, lTextRight, lMoneyRight, iMoneyRight);
    }

    private void removeAll() {
        if(!firstClick) {
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
            setHeroMoneyVisibleRight(false);
            Transaction.updateSellButton(true);
            Transaction.updateBuyButton(true);
            lPrice.addAction(Actions.fadeOut(0));
        }
    }

    private static void addActors(Actor... actor) {
        for (Actor object : actor)
            stage.addActor(object);
    }

    public static void updateBuyButton(boolean enabledBuy) {
        if (enabledBuy) {
            bBuy.setVisible(true);
        } else {
            bBuy.setVisible(false);
        }
    }

    public static void updateBuyToutchable(boolean touchable) {
        if (touchable) {
            bBuy.setTouchable(Touchable.enabled);
        } else {
            bBuy.setTouchable(Touchable.disabled);
        }
    }

    public static void updateSellButton(boolean enabledSell) {
        if (enabledSell) {
            bSell.setVisible(true);
        } else {
            bSell.setVisible(false);
        }
    }

    public static void updateSellTouchable(boolean touchable) {
        if (touchable) {
            bSell.setTouchable(Touchable.enabled);
        } else {
            bSell.setTouchable(Touchable.disabled);
        }
    }

    public static void setBackEnabled(boolean enabled) {
        if (enabled)
            bBack.setTouchable(Touchable.enabled);
        else
            bBack.setTouchable(Touchable.disabled);
    }

    public static void setHeroMoneyVisibleLeft(boolean isVisible){
        lTextLeft.setVisible(isVisible);
        lMoneyLeft.setVisible(isVisible);
        iMoneyLeft.setVisible(isVisible);
    }

    public static void setHeroMoneyVisibleRight(boolean isVisible){
        lTextRight.setVisible(isVisible);
        lMoneyRight.setVisible(isVisible);
        iMoneyRight.setVisible(isVisible);
    }
}
