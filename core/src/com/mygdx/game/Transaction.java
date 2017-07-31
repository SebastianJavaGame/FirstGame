package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-07-30.
 */

class Transaction {
    private static final Image BACKGROUND_TRANSACTION = new Image(new Texture(Gdx.files.internal("shopBackgroundTransaction.png")));
    private final Stage stage = BaseScreen.getStage();
    private static final BitmapFont FONT_ACTIVE= new BitmapFont();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_BUY = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_SELL = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_BACK = new TextButton.TextButtonStyle();

    static {
        STYLE_TRANSACTION_BUY.font = FONT_ACTIVE;
        STYLE_TRANSACTION_BUY.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonTransaction.png"))));
        STYLE_TRANSACTION_SELL.font = FONT_ACTIVE;
        STYLE_TRANSACTION_SELL.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonTransaction.png"))));
        STYLE_BACK.font = FONT_ACTIVE;
        STYLE_BACK.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonBack.png"))));
    }

    private Image image;
    private String name;
    private int level;
    private int idShop;

    private static TextButton bBack;
    private static TextButton bBuy;
    private static TextButton bSell;

    private boolean[] emptySlotShop;

    public Transaction(int itemGroup, final Image image, final String name, final int level, final int idShop) {
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        emptySlotShop = new boolean[10];


        Shop.setActive(true);

        BACKGROUND_TRANSACTION.setPosition(0, Shop.POS_Y_NEXT_BACKGROUND);

        bBack = new TextButton("Wroc do menu", STYLE_BACK);
        bBuy = new TextButton("Kup", STYLE_TRANSACTION_BUY);
        bSell = new TextButton("Sprzedaj", STYLE_TRANSACTION_BUY);

        updateSellTouchable(false);
        updateBuyToutchable(false);

        bBack.setPosition(174, 238);
        bBuy.setPosition(18, Shop.POS_Y_NEXT_BACKGROUND +10);
        bSell.setPosition(214, Shop.POS_Y_NEXT_BACKGROUND +10);

        bBack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.clear();
                BaseScreen.getGame().setScreen(new Shop(BaseScreen.getGame(), image, name, level, idShop));
                return false;
            }
        });

        bBuy.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Click BUY");
                return false;
            }
        });

        bSell.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Click SELL");
                return false;
            }
        });

        addActors(BACKGROUND_TRANSACTION, bBack, bBuy, bSell);

        String[] itemList = BaseShopDepartaments.getItemsFromDepartament(idShop, itemGroup);

        for (int i = 0; i < itemList.length; i++) {
            String value = itemList[i];
            System.out.println(value);
            if (!value.equals(""))
                try {
                    addItemToBag(LoadAllItemToGame.getItem(value), i);
                } catch (CloneNotSupportedException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }
            //else
                //Equipment.setSlotEmpty(i, false);
        }
    }

    public void addItemToBag(final Item item, int slot) {
        if(slot > 9)
            return;

        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                if (slotNr == slot) {
                    item.getImage().setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    //item.setStan(Item.Stan.BAG);
                    item.getImage().addListener(new InputListener(){
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            //imageAddListener(item, item.getPathImage());
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

    private void addActors(Actor... actor){
        for(Actor object: actor)
            stage.addActor(object);
    }

    public static void updateBuyButton(boolean enabledBuy){
        if(enabledBuy){
            bBuy.setText("Kup");
        }else {
            bBuy.setText("");
        }
    }

    public static void updateBuyToutchable(boolean touchable){
        if(touchable){
            bBuy.setTouchable(Touchable.enabled);
        }else {
            bBuy.setTouchable(Touchable.disabled);
        }
    }

    public static void updateSellButton(boolean enabledSell){
        if(enabledSell){
            bSell.setText("Sprzedaj");
        }else{
            bSell.setText("");
        }
    }

    public static void updateSellTouchable(boolean touchable){
        if(touchable){
            bSell.setTouchable(Touchable.enabled);
        }else{
            bSell.setTouchable(Touchable.disabled);
        }
    }

    public static void setBackEnabled(boolean enabled){
        if (enabled)
            bBack.setTouchable(Touchable.enabled);
        else
            bBack.setTouchable(Touchable.disabled);
    }
}
