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
    private static Label itemType;
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

    public static boolean active = false;
    private static boolean firstClick = true;

    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    static {
        style.font = font;
    }

    //private static Preferences pref;

    public FuncionalityShop(){
        stage = BaseScreen.getStage();
        //pref = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
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
                            imageAddListener(item, item.getPathImage());
                            return false;
                        }
                    });
                    Equipment.setSlotEmpty(slotNr, true);

                    //pref.putString("SLOT" + slotNr, item.getItemKey());
                    //pref.flush();

                    System.out.println(item.getItemKey());

                    stage.addActor(item.getImage());
                    return;
                }
                slotNr++;
            }
        }
    }

    private static void imageAddListener(final Item item, String pathImage) {
        if(active) {
            removeAll();
            firstClick = false;

            Transaction.setHeroMoneyVisibleLeft(true);
            Transaction.updateSellTouchable(true);
            Transaction.updateBuyButton(false);
            Transaction.setBackEnabled(false);

            TextButton.TextButtonStyle styleButton = new TextButton.TextButtonStyle();
            styleButton.font = font;
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
            lPrice.setPosition(BaseScreen.VIEW_WIDTH /2 -lPrice.getWidth()/2, itemPrice.getY() -40);

            bClose.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Transaction.updateSellTouchable(false);
                    Transaction.setBackEnabled(true);
                    removeAll();
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
            }),Actions.moveBy(0, -55, 1)));
            money.addAction(Actions.moveBy(0, -55, 1));
            lPrice.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(0.6f), Actions.fadeIn(0.3f)));

            addActors(backgroundUp, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong, itemWiedza,
                    itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, bClose, money, itemPrice, lPrice);
        }
    }

    private static void removeAll() {
        try{
            if(!firstClick) {
                checkIsNotNull(backgroundUp);
                checkIsNotNull(itemImage);
                checkIsNotNull(itemName);
                checkIsNotNull(itemType);
                checkIsNotNull(itemHp);
                checkIsNotNull(itemArmor);
                checkIsNotNull(itemStrong);
                checkIsNotNull(itemWiedza);
                checkIsNotNull(itemDefenseFiz);
                checkIsNotNull(itemDefenseMag);
                checkIsNotNull(itemPrice);
                checkIsNotNull(money);
                checkIsNotNull(barName);
                checkIsNotNull(barPrice);
                checkIsNotNull(itemBackground);
                checkIsNotNull(bClose);
                Transaction.setHeroMoneyVisibleLeft(false);
                Transaction.updateSellButton(true);
                Transaction.updateBuyButton(true);
                lPrice.addAction(Actions.fadeOut(0));
            }
        }catch (Exception e){
            BaseScreen.showException(e);
        }
    }

    private static void checkIsNotNull(Actor actor){
        if(actor != null)
            actor.remove();
    }

    private static void addActors(Actor... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }

    public static void setActive(boolean setActive){
        active = setActive;
    }
}
