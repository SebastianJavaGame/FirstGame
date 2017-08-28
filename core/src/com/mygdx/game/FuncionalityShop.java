package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;
import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-31.
 */

public class FuncionalityShop {
    private static Stage stage;

    private static Image backgroundUp;
    private static Image itemImage;
    private static Label itemName;
    private static Label itemLevelRequire;
    private static Label itemHp;
    private static Label itemStrong;
    private static Label itemWiedza;
    private static Label itemArmor;
    private static Label itemDefenseFiz;
    private static Label itemDefenseMag;
    private static Label itemPrice;
    private static Label lPrice;
    private static TextButton bClose;
    private static Image money;
    private static Image itemBackground;
    private static Image barName;
    private static Image barPrice;

    private static boolean active = false;
    private static boolean firstClick = true;
    private static String actualItemNameShop;
    private static Image actualItemImageBag;
    private static int actualSlotNrBag;
    private static Label lAnimTransaction;
    private static Image imageAnimTransaction;

    private static int price;

    private static final BitmapFont font = MyGdxGame.createDistanceFont();
    public static final Label.LabelStyle style = new Label.LabelStyle();
    static {
        style.font = font;
    }

    public FuncionalityShop(){
        stage = BaseScreen.getStage();
    }

    public static void addItemToBag(final Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (slotNr == slot) {
                    item.getImage().setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    item.setStan(Item.Stan.BAG);
                    item.getImage().addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            imageAddListenerBag(item, item.getPathImage());
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

    public static void addItemToShop(final Item item, int slot) {
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
                            imageAddListenerShop(item, item.getPathImage());
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

    private static void imageAddListenerBag(final Item item, String pathImage) {
        if(active) {
            Asset asset = new Asset();
            asset.loadFunctionalityShop();
            asset.manager.finishLoading();
            if (asset.manager.update()) {
                removeAllShop(); //TODO removeAllBag
                firstClick = false;

                int row = (int) (item.getImage().getX() - 10) / 50;
                int column = (int) item.getImage().getY() / 50;
                if (column == 2)
                    column = 0;
                else if (column == 0)
                    column = 2;
                int slotNr = column * 6 + row;
                actualSlotNrBag = slotNr;
                actualItemImageBag = item.getImage();

                Screen.Transaction.setHeroMoneyVisibleLeft(true);
                Screen.Transaction.updateSellTouchable(true);
                Screen.Transaction.updateBuyButton(false);
                Screen.Transaction.setBackEnabled(false);

                if (lAnimTransaction != null && imageAnimTransaction != null) {
                    getlAnimTransaction().remove();
                    getImageAnimTransaction().remove();
                }

                TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
                styleButton.font = font;
                styleButton.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonBack.png", Texture.class)));
                bClose = new TextButton("Zamknij", styleButton);

                backgroundUp = new Image(asset.manager.get("statsBackground.png", Texture.class));
                itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));

                itemName = new Label("" + item.getItemName(), style);
                itemLevelRequire = new Label("Wymagany poziom: " + item.getLevelRequire(), style);
                itemHp = new Label("Punkty życia +" + item.getHp(), style);
                itemStrong = new Label("Siła +" + item.getStrong(), style);
                itemWiedza = new Label("Wiedza +" + item.getWiedza(), style);
                itemArmor = new Label("Pancerz +" + item.getArmor() + "%", style);
                itemDefenseFiz = new Label("ObronaFizyczna +" + item.getDefenseFiz(), style);
                itemDefenseMag = new Label("ObronaMagiczna +" + item.getDefenseMag(), style);
                itemPrice = new Label("" + item.getCashValue(), style);
                lPrice = new Label("Cena", style);
                price = (int)(item.getCashValue()*0.35f);

                money = new Image(asset.manager.get("uiMoney.png", Texture.class));
                itemBackground = new Image(asset.manager.get("slotInfoItem.png", Texture.class));
                barName = new Image(asset.manager.get("nameBar.png", Texture.class));
                barPrice = new Image(asset.manager.get("barX.png", Texture.class));

                float scale = 0.5f;

                backgroundUp.setBounds(0, 230, BaseMap.VIEW_WIDTH, 190);
                itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
                itemName.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth()*scale / 2, backgroundUp.getY() + 152);
                itemLevelRequire.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth()*scale / 2, backgroundUp.getY() + 121);
                itemHp.setPosition(20, backgroundUp.getY() + 90);
                itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 90);
                itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 60);
                itemWiedza.setPosition(20, backgroundUp.getY() + 60);
                itemDefenseFiz.setPosition(10, backgroundUp.getY() + 30);
                itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 + 10, backgroundUp.getY() + 30);
                itemPrice.setPosition(BaseScreen.VIEW_WIDTH / 2 - itemPrice.getWidth()*scale / 2 - 10, backgroundUp.getY() -itemPrice.getHeight()*scale/2 +6);
                money.setBounds(itemPrice.getX() + itemPrice.getWidth() *scale + 7, itemPrice.getY() +itemPrice.getHeight()*scale /2, 20, 20);
                barName.setBounds(itemName.getX() -10, itemName.getY() -2, itemName.getWidth()*scale +18, itemName.getHeight()*scale + 15);
                itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
                barPrice.setBounds(0, itemPrice.getY() - 4, BaseMap.VIEW_WIDTH + 15, 25);
                bClose.setPosition(BaseScreen.VIEW_WIDTH / 2 - bClose.getWidth() / 2, itemPrice.getY());
                lPrice.setPosition(BaseScreen.VIEW_WIDTH / 2 - lPrice.getWidth()*0.5f / 2, Screen.Shop.POS_Y_NEXT_BACKGROUND + 25);

                itemPrice.setColor(Color.GOLD);

                itemName.setFontScale(scale);
                itemHp.setFontScale(scale);
                itemArmor.setFontScale(scale);
                itemName.setFontScale(scale);
                itemStrong.setFontScale(scale);
                itemWiedza.setFontScale(scale);
                itemDefenseFiz.setFontScale(scale);
                itemDefenseMag.setFontScale(scale);
                itemPrice.setFontScale(scale);
                itemLevelRequire.setFontScale(scale);
                lPrice.setFontScale(0.5f);

                if (Hero.getLevel() < item.getLevelRequire())
                    itemLevelRequire.setColor(Color.RED);

                bClose.addListener(new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Screen.Transaction.updateSellTouchable(false);
                        Screen.Transaction.setBackEnabled(true);
                        removeAllShop();
                        return false;
                    }
                });

                bClose.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(1)));
                itemPrice.addAction(Actions.sequence(Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        itemPrice.setText("" + item.getCashValue());
                        itemPrice.setColor(Color.GOLD);
                    }
                }), Actions.moveBy(0, -55, 1)));
                money.addAction(Actions.moveBy(0, -55, 1));
                lPrice.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(0.6f), Actions.fadeIn(0.3f)));

                addActors(backgroundUp, itemBackground, itemImage, barName, itemName, itemLevelRequire, itemHp, itemStrong, itemWiedza,
                        itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, bClose, money, itemPrice, lPrice);
            }
        }
    }

    public static void imageAddListenerShop(final Item item, String pathImage) {
        Asset asset = new Asset();
        asset.loadFunctionalityShop();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            removeAllShop();
            firstClick = false;
            actualItemNameShop = item.getItemKey();

            Screen.Transaction.setHeroMoneyVisibleRight(true);
            Screen.Transaction.updateBuyTouchable(true);
            Screen.Transaction.updateSellButton(false);

            if (lAnimTransaction != null && imageAnimTransaction != null) {
                getlAnimTransaction().remove();
                getImageAnimTransaction().remove();
            }

            TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
            styleButton.font = Screen.Transaction.FONT;
            styleButton.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonBack.png", Texture.class)));
            bClose = new TextButton("Zamknij", styleButton);

            backgroundUp = new Image(asset.manager.get("statsBackground.png", Texture.class));
            itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));

            itemName = new Label("" + item.getItemName(), style);
            itemLevelRequire = new Label("Wymagany poziom: " +item.getLevelRequire(), style);
            itemHp = new Label("Punkty życia +" + item.getHp(), style);
            itemStrong = new Label("Siła +" + item.getStrong(), style);
            itemWiedza = new Label("Wiedza +" + item.getWiedza(), style);
            itemArmor = new Label("Pancerz +" + item.getArmor() + "%", style);
            itemDefenseFiz = new Label("ObronaFizyczna +" + item.getDefenseFiz(), style);
            itemDefenseMag = new Label("ObronaMagiczna +" + item.getDefenseMag(), style);
            itemPrice = new Label("" + item.getCashValue(), style);
            lPrice = new Label("Cena", style);
            price = item.getCashValue();

            money = new Image(asset.manager.get("uiMoney.png", Texture.class));
            itemBackground = new Image(asset.manager.get("slotInfoItem.png", Texture.class));
            barName = new Image(asset.manager.get("nameBar.png", Texture.class));
            barPrice = new Image(asset.manager.get("barX.png", Texture.class));

            float scale = 0.5f;

            backgroundUp.setBounds(0, -16, BaseMap.VIEW_WIDTH, 190);
            itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
            itemName.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth()*scale / 2, backgroundUp.getY() + 152);
            itemLevelRequire.setPosition(itemName.getX() +itemName.getWidth()*scale /2 -itemName.getWidth()*scale /3, backgroundUp.getY() + 122);
            itemHp.setPosition(20, backgroundUp.getY() + 90);
            itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 90);
            itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 + 20, backgroundUp.getY() + 64);
            itemWiedza.setPosition(20, backgroundUp.getY() + 64);
            itemDefenseFiz.setPosition(10, backgroundUp.getY() + 38);
            itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 + 10, backgroundUp.getY() + 38);
            itemPrice.setPosition(BaseScreen.VIEW_WIDTH / 2 - itemPrice.getWidth()*scale / 2 - 10, backgroundUp.getY() -itemPrice.getHeight()*scale/2 +2);
            money.setBounds(itemPrice.getX() + itemPrice.getWidth() *scale + 7, itemPrice.getY() +itemPrice.getHeight()*scale /2, 20, 20);
            barName.setBounds(itemName.getX() -10, itemName.getY() -2, itemName.getWidth()*scale +20, itemName.getHeight()*scale + 15);
            itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
            barPrice.setBounds(0, itemPrice.getY() - 4, BaseMap.VIEW_WIDTH + 15, 25);
            bClose.setPosition(BaseScreen.VIEW_WIDTH / 2 - bClose.getWidth() / 2, itemPrice.getY() +18);
            lPrice.setPosition(BaseScreen.VIEW_WIDTH / 2 - lPrice.getWidth()*0.5f / 2, Screen.Shop.POS_Y_NEXT_BACKGROUND + 26);

            itemPrice.setColor(Color.GOLD);

            itemName.setFontScale(scale);
            itemHp.setFontScale(scale);
            itemArmor.setFontScale(scale);
            itemName.setFontScale(scale);
            itemStrong.setFontScale(scale);
            itemWiedza.setFontScale(scale);
            itemDefenseFiz.setFontScale(scale);
            itemDefenseMag.setFontScale(scale);
            itemPrice.setFontScale(scale);
            itemLevelRequire.setFontScale(scale);
            lPrice.setFontScale(0.5f);

            if (Hero.getLevel() < item.getLevelRequire()) {
                itemLevelRequire.setColor(Color.RED);
            }

            bClose.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Screen.Transaction.updateBuyTouchable(false);
                    Screen.Transaction.setBackEnabled(true);
                    removeAllShop();
                    return false;
                }
            });

            itemPrice.addAction(Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    itemPrice.setText("" + item.getCashValue());
                    itemPrice.setColor(Color.GOLD);
                    itemPrice.setPosition(itemPrice.getX(), Screen.Shop.POS_Y_NEXT_BACKGROUND - 25);
                    money.setPosition(money.getX(), Screen.Shop.POS_Y_NEXT_BACKGROUND -15);
                }
            }), Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, 34, 0.8f)))));
            money.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(0, 34, 0.8f))));
            lPrice.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(0.6f), Actions.fadeIn(0.3f)));

            addActors(backgroundUp, itemBackground, itemImage, barName, itemName, itemLevelRequire, itemHp, itemStrong, itemWiedza,
                    itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, bClose, money, itemPrice, lPrice);
        }
    }

    public static void animationEndTransaction(String text, Color color){
        lAnimTransaction = new Label(text, style);
        imageAnimTransaction = new Image(new Texture(Gdx.files.internal("uiMoney.png")));

        lAnimTransaction.setColor(color);
        imageAnimTransaction.setSize(20, 20);
        lAnimTransaction.setPosition(BaseScreen.VIEW_WIDTH /2 -(lAnimTransaction.getWidth() +imageAnimTransaction.getWidth()) /2, 190);
        imageAnimTransaction.setPosition(lAnimTransaction.getX() +lAnimTransaction.getWidth() +4, 190);

        lAnimTransaction.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(1), Actions.delay(3), Actions.fadeOut(1)));
        imageAnimTransaction.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(1), Actions.delay(3), Actions.fadeOut(1)));
        addActors(lAnimTransaction, imageAnimTransaction);
    }

    public static void removeAllShop() {
        if(!firstClick) {
            backgroundUp.remove();
            itemImage.remove();
            itemName.remove();
            itemLevelRequire.remove();
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
            Screen.Transaction.setHeroMoneyVisibleRight(false);
            Screen.Transaction.setHeroMoneyVisibleLeft(false);
            Screen.Transaction.updateSellButton(true);
            Screen.Transaction.updateBuyButton(true);
            lPrice.remove();
        }
    }

    private static void addActors(Actor... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }

    public static void setActive(boolean setActive){
        active = setActive;
    }

    public static void setFirstClick(boolean setFirstClick){
        firstClick = setFirstClick;
    }

    public static String getActualItemNameShop(){
        return actualItemNameShop;
    }

    public static Image getActualItemImageBag(){
        return actualItemImageBag;
    }

    public static int getActualSlotNrBag(){
        return actualSlotNrBag;
    }

    public static int getPrice(){
        return price;
    }

    public static Label getlAnimTransaction(){
        return lAnimTransaction;
    }

    public static Image getImageAnimTransaction(){
        return imageAnimTransaction;
    }
}
