package Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Asset;
import com.mygdx.game.BaseShopDepartaments;
import com.mygdx.game.Equipment;
import com.mygdx.game.FuncionalityShop;
import com.mygdx.game.Hero;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-07-30.
 */

public class Transaction {
    private Asset asset = new Asset();
    private Image backgroundTransaction;
    public static final BitmapFont FONT = MyGdxGame.createDistanceFont();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_BUY = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_TRANSACTION_SELL = new TextButton.TextButtonStyle();
    private static final TextButton.TextButtonStyle STYLE_BACK = new TextButton.TextButtonStyle();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private final Preferences preferences = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
    private final Preferences preferencesStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
    private static Stage stage;

    static {
        style.font = FONT;
        STYLE_TRANSACTION_BUY.font = FONT;
        STYLE_TRANSACTION_SELL.font = FONT;
        STYLE_BACK.font = FONT;
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

    private Sound soundShop;

    public Transaction(int itemGroup, final Image image, final String name, final int level, final int idShop) {
        this.image = image;
        this.name = name;
        this.level = level;
        this.idShop = idShop;
        stage = BaseScreen.getStage();

        asset.loadTransaction();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            soundShop = asset.manager.get("sound/shop.ogg", Sound.class);
            STYLE_BACK.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonBack.png", Texture.class)));
            STYLE_TRANSACTION_SELL.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonTransaction.png", Texture.class)));
            STYLE_TRANSACTION_BUY.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonTransaction.png", Texture.class)));
            backgroundTransaction = new Image(asset.manager.get("shopBackgroundTransaction.png", Texture.class));

            FuncionalityShop.setActive(true);

            backgroundTransaction.setPosition(0, Screen.Shop.POS_Y_NEXT_BACKGROUND);

            bBack = new TextButton("Menu", STYLE_BACK);
            bBuy = new TextButton("Kup", STYLE_TRANSACTION_BUY);
            bSell = new TextButton("Sprzedaj", STYLE_TRANSACTION_BUY);

            bBack.setPosition(174, 238);
            bBuy.setPosition(18, Screen.Shop.POS_Y_NEXT_BACKGROUND + 10);
            bSell.setPosition(214, Screen.Shop.POS_Y_NEXT_BACKGROUND + 10);

            addActors(backgroundTransaction, bBack, bBuy, bSell);

            String[] itemList = BaseShopDepartaments.getItemsFromDepartament(idShop, itemGroup);
            showEq(itemList);
            showHeroMoney();

            bBack.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    stage.clear();
                    if (FuncionalityShop.getImageAnimTransaction() != null && FuncionalityShop.getlAnimTransaction() != null) {
                        FuncionalityShop.getlAnimTransaction().remove();
                        FuncionalityShop.getImageAnimTransaction().remove();
                    }
                    Menu.getSoundClick().play();
                    FuncionalityShop.setFirstClick(true);
                    FuncionalityShop.setActive(false);
                    BaseScreen.getGame().setScreen(new Screen.Shop(BaseScreen.getGame(), image, name, level, idShop));
                    return false;
                }
            });

            bBuy.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    updateBuyTouchable(false);
                    Transaction.setBackEnabled(true);
                    for (int i = 0; i < 18; i++) {
                        String value = preferences.getString("SLOT" + i, "");

                        if (value.equals("")) {
                            try {
                                int money = preferencesStats.getInteger("MONEY");
                                System.out.println("hero money= " + money);
                                System.out.println("Item price= " + FuncionalityShop.getPrice());
                                if (money >= FuncionalityShop.getPrice()) {
                                    soundShop.play();
                                    FuncionalityShop.animationEndTransaction("-" + FuncionalityShop.getPrice(), Color.RED);
                                    FuncionalityShop.addItemToBag(LoadAllItemToGame.getItem(FuncionalityShop.getActualItemNameShop()), i);

                                    preferences.putString("SLOT" + i, FuncionalityShop.getActualItemNameShop());
                                    preferences.flush();
                                    Hero.setMoney(Hero.getMoney() - FuncionalityShop.getPrice());
                                    preferencesStats.putInteger("MONEY", Hero.getMoney()).flush();

                                    lMoneyLeft.setText("" + Hero.getMoney());
                                    lMoneyRight.setText("" + Hero.getMoney());
                                } else {
                                    if(FuncionalityShop.getlAnimTransaction() != null)
                                        FuncionalityShop.getlAnimTransaction().remove();
                                    if(FuncionalityShop.getImageAnimTransaction() != null)
                                        FuncionalityShop.getImageAnimTransaction().remove();
                                    FuncionalityShop.animationEndTransaction("Brak zlota!", Color.RED);
                                }
                                FuncionalityShop.removeAllShop();
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        if (i == 17) {//TODO TEST!
                            new InfoScreen("Brak pustego miejsca w ekwipunku", 2, stage);
                            /*Label label = new Label("Brak pustego miejsca w ekwipunku", style);
                            label.setFontScale(1);
                            label.setPosition(BaseScreen.VIEW_WIDTH / 2 - label.getWidth() / 2, BaseScreen.VIEW_HEIGHT / 2);
                            label.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(0.6f), Actions.delay(1), Actions.fadeOut(0.4f)));
                            stage.addActor(label);
                            */
                        }
                    }
                    return false;
                }
            });

            bSell.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    soundShop.play();
                    updateSellTouchable(false);
                    Transaction.setBackEnabled(true);
                    FuncionalityShop.animationEndTransaction("+" + FuncionalityShop.getPrice(), Color.GREEN);
                    FuncionalityShop.removeAllShop();

                    int slotNr = FuncionalityShop.getActualSlotNrBag();
                    Equipment.slotEmpty[slotNr] = false;
                    FuncionalityShop.getActualItemImageBag().remove();

                    preferences.putString("SLOT" + slotNr, "");
                    preferences.flush();
                    Hero.setMoney(Hero.getMoney() + FuncionalityShop.getPrice());
                    preferencesStats.putInteger("MONEY", Hero.getMoney()).flush();

                    lMoneyLeft.setText("" + Hero.getMoney());
                    lMoneyRight.setText("" + Hero.getMoney());
                    return false;
                }
            });

            setHeroMoneyVisibleLeft(false);
            setHeroMoneyVisibleRight(false);
            updateSellTouchable(false);
            updateBuyTouchable(false);
        }
    }

    private void showEq(String[] itemList) {
        for (int i = 0; i < itemList.length; i++) {
            String value = itemList[i];
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

        lTextLeft.setFontScale(0.5f);
        lTextRight.setFontScale(0.5f);
        lMoneyLeft.setFontScale(0.5f);
        lMoneyRight.setFontScale(0.5f);

        lMoneyLeft.setColor(Color.GOLD);
        iMoneyLeft.setSize(20, 20);

        lMoneyRight.setColor(Color.GOLD);
        iMoneyRight.setSize(20, 20);

        lTextLeft.setPosition(bBuy.getX() + bBuy.getWidth() / 2 - lTextLeft.getWidth()*0.5f / 2, bBuy.getY() + 14);
        lMoneyLeft.setPosition(bBuy.getX() + bBuy.getWidth() / 2 - lMoneyLeft.getWidth()*0.5f / 2 - iMoneyLeft.getWidth() / 2, bBuy.getY() -4);
        iMoneyLeft.setPosition(lMoneyLeft.getX() + lMoneyLeft.getWidth()*0.5f + 5, lMoneyLeft.getY() +8);

        lTextRight.setPosition(bSell.getX() + bSell.getWidth() / 2 - lTextRight.getWidth()*0.5f /2 -16, bSell.getY() + 20);
        lMoneyRight.setPosition(bSell.getX() + bSell.getWidth() / 2 - lMoneyRight.getWidth()*0.5f -iMoneyRight.getWidth() /2, bSell.getY());
        iMoneyRight.setPosition(lMoneyRight.getX() + lMoneyRight.getWidth()*0.5f + 5, lMoneyRight.getY() +10);

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

    public static void updateBuyTouchable(boolean touchable) {
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
