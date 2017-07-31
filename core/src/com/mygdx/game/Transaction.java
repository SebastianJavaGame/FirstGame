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

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-30.
 */

class Transaction {
    private static final Image BACKGROUND_TRANSACTION = new Image(new Texture(Gdx.files.internal("shopBackgroundTransaction.png")));
    public static final BitmapFont FONT = new BitmapFont();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_BUY = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_SELL = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_BACK = new TextButton.TextButtonStyle();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private final Preferences preferences = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
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
    private static Image iMoneyRight;

    private Image image;
    private String name;
    private int level;
    private int idShop;

    private static TextButton bBack;
    private static TextButton bBuy;
    private static TextButton bSell;

    public Transaction(int itemGroup, final Image image, final String name, final int level, final int idShop) {
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
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
                FuncionalityShop.setFirstClick(true);
                FuncionalityShop.setActive(false);
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
                            FuncionalityShop.addItemToBag(LoadAllItemToGame.getItem(FuncionalityShop.getActualItemName()), i);
                            preferences.putString("SLOT" +i, FuncionalityShop.getActualItemName());
                            preferences.flush();
                            FuncionalityShop.removeAllShop(); //TODO removeAllBag
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    if(i == 17){//TODO TEST!
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
                int slotNr = FuncionalityShop.getActualSlotNr();
                Equipment.slotEmpty[slotNr] = false;
                //item.getImage().remove(); //TODO napraw this line
                preferences.putString("SLOT" + slotNr, "");
                preferences.flush();
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
                    FuncionalityShop.addItemToShop(LoadAllItemToGame.getItem(value), i);
                } catch (CloneNotSupportedException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }
        }
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
