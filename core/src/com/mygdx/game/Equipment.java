package com.mygdx.game;

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;
import Screen.BaseScreen;
import Screen.Menu;

/**
 * Created by Sebastian on 2017-06-10.
 */

public class Equipment{
    public static final String PREF_NAME_EQ = "ITEMS";
    public static final String PREF_NAME_FIGHT = "FIGHT";

    public final Preferences PREF_FIGHT = Gdx.app.getPreferences(PREF_NAME_FIGHT);
    public final Preferences PREF_ITEMS = Gdx.app.getPreferences(PREF_NAME_EQ);

    public static final String[] KEY_PREF_FIGHT = new String[]{"ATTACK_PHYSICS", "DEFENSE_PHYSICS", "ATTACK_MAGIC", "DEFENSE_MAGIC"};

    public static final String[] PREF_ITEM_HUMAN = new String[]{
            "HELMET", "ARMOR", "PANTS", "SHOES", "WAPON", "ITEM_BLOCK", "RING", "ITEM_HAND"};

    public static final String[] PATH_DEFAULT_IMAGE = new String[]{
            "helm.png", "zbroja.png", "spodnie.png", "buty.png", "bron.png", "tarcza.png", "pierscien.png", "rekawice.png"
    };

    public static final Item.ItemType[] ITEM_TYPES = new Item.ItemType[]{
            Item.ItemType.HELMET, Item.ItemType.ARMOR, Item.ItemType.PANTS, Item.ItemType.SHOES, Item.ItemType.WAPON, Item.ItemType.ITEM_BLOCK, Item.ItemType.RING, Item.ItemType.ITEM_HAND
    };

    public static final int BLOCK_POSITION[][] = new int[][]{{BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 324}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 271},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 218}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 165},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 - Item.BLOCK_SIZE - 3, 271}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 + Item.BLOCK_SIZE + 3, 271},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 + Item.BLOCK_SIZE + 3, 218}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 - Item.BLOCK_SIZE - 3, 218}};

    private static final BitmapFont font = MyGdxGame.createDistanceFont();
    private Asset asset = new Asset();

    private TextButton takeOn;
    private TextButton takeOff;
    private TextButton drop;
    private TextButton cancel;

    private Image hpIcon;
    private Image hpIconDown;
    private Image silaIcon;
    private Image silaIconDown;
    private Image pancerzIcon;
    private Image pancerzIconDown;
    private Image wiedzaIcon;
    private Image wiedzaIconDown;
    private Image zrecznoscIcon;
    private Image zrecznoscIconDown;
    private Image magiaIcon;
    private Image magiaIconDown;
    private Image backgroundUp;
    private Image backgroundDown;
    private Image itemImage;
    private Label itemName;
    private Label itemLevelRequire;
    private Label itemHp;
    private Label itemStrong;
    private Label itemWiedza;
    private Label itemDefenseFiz;
    private Label itemDefenseMag;
    private Label itemArmor;
    private Image money;
    private Label itemPrice;
    private Label infoStorage;
    private Image itemBackground;
    private Image barName;
    private Image barPrice;
    private Image itemImageDown;
    private Label itemNameDown;
    private Label itemLevelRequireDown;
    private Label itemHpDown;
    private Label itemStrongDown;
    private Label itemWiedzaDown;
    private Label itemArmorDown;
    private Label itemDefenseFizDown;
    private Label itemDefenseMagDown;
    private Image moneyDown;
    private Label itemPriceDown;
    private Label infoStorageDown;
    private Image itemBackgroundDown;
    private Image barNameDown;
    private Image barPriceDown;

    private static Hero hero;
    private static Stage card;

    private static Image[] emptyBlock;
    private static Item[] block;
    private static boolean[] blockEmpty;
    public static boolean[] slotEmpty;

    private static Label labelFreePoint;
    private static Label[] labelPointFight;
    private static ImageButton[] plusButton;
    private static ImageButton[] minusButton;
    private static int freePointFight;

    private static boolean blockClick = false;

    private Sound soundEquipment;

    public Equipment(Stage card, Hero hero) throws CloneNotSupportedException {
        this.hero = hero;
        this.card = card;
        block = new Item[8];
        slotEmpty = new boolean[18];
        blockEmpty = new boolean[8];
        emptyBlock = new Image[8];
        freePointFight = PREF_FIGHT.getInteger("FIGHT_POINT", 10);
        for (boolean slot : slotEmpty)
            slot = false;
        create();
    }

    private void create() throws CloneNotSupportedException {
        asset.loadEquipment();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            soundEquipment = asset.manager.get("sound/soundEquipment.ogg", Sound.class);
            ImageButton userPref = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonUserPref.png", Texture.class))));
            userPref.setSize(60, 60);
            userPref.setPosition(250, 180);
            userPref.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    freePointFight = PREF_FIGHT.getInteger("FIGHT_POINT", 10);
                    Menu.getSoundClick().play();
                    setBlockClick(true);
                    final Image background = new Image(asset.manager.get("userPrefBackground.png", Texture.class));
                    labelPointFight = new Label[4];
                    plusButton = new ImageButton[4];
                    minusButton = new ImageButton[4];

                    BitmapFont font = MyGdxGame.createDistanceFont();
                    Label.LabelStyle style = new Label.LabelStyle();
                    style.font = font;

                    labelFreePoint = new Label("Free points: " + PREF_FIGHT.getInteger("FIGHT_POINT", 10), style);
                    labelFreePoint.setFontScale(0.8f);
                    labelFreePoint.setPosition(5, 412);

                    final TextButton.TextButtonStyle styleSave = new TextButton.TextButtonStyle();
                    styleSave.font = font;
                    styleSave.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonAbort.png", Texture.class)));

                    final TextButton buttonSave = new TextButton("Zapisz", styleSave);
                    final TextButton buttonCancel = new TextButton("Anuluj", styleSave);

                    buttonSave.setBounds(30, 5, 110, 40);
                    buttonCancel.setBounds(buttonSave.getWidth() + 70, 5, 110, 40);

                    buttonSave.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            Menu.getSoundClick().play();
                            PREF_FIGHT.putInteger("ATTACK_PHYSICS", Integer.parseInt(labelPointFight[0].getText().toString())).flush();
                            PREF_FIGHT.putInteger("DEFENSE_PHYSICS", Integer.parseInt(labelPointFight[1].getText().toString())).flush();
                            PREF_FIGHT.putInteger("ATTACK_MAGIC", Integer.parseInt(labelPointFight[2].getText().toString())).flush();
                            PREF_FIGHT.putInteger("DEFENSE_MAGIC", Integer.parseInt(labelPointFight[3].getText().toString())).flush();
                            PREF_FIGHT.putInteger("FIGHT_POINT", freePointFight).flush();

                            setBlockClick(false);
                            background.remove();
                            buttonSave.remove();
                            buttonCancel.remove();
                            labelFreePoint.remove();
                            for (int i = 0; i < 4; i++) {
                                minusButton[i].remove();
                                plusButton[i].remove();
                                labelPointFight[i].remove();
                            }
                            return false;
                        }
                    });

                    buttonCancel.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            setBlockClick(false);
                            Menu.getSoundClick().play();
                            background.remove();
                            buttonSave.remove();
                            buttonCancel.remove();
                            labelFreePoint.remove();
                            for (int i = 0; i < 4; i++) {
                                minusButton[i].remove();
                                plusButton[i].remove();
                                labelPointFight[i].remove();
                            }
                            return false;
                        }
                    });

                    card.addActor(background);
                    card.addActor(labelFreePoint);
                    card.addActor(buttonSave);
                    card.addActor(buttonCancel);

                    for (int i = 0; i < 4; i++) {
                        plusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("plusPref.png", Texture.class))));
                        minusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("minusPref.png", Texture.class))));
                        plusButton[i].setPosition(200, (i + 1) * 95);
                        minusButton[i].setPosition(70, (i + 1) * 95);

                        labelPointFight[i] = new Label(String.valueOf(PREF_FIGHT.getInteger(KEY_PREF_FIGHT[i])), style);
                        labelPointFight[i].setPosition(BaseMap.VIEW_WIDTH / 2 - labelPointFight[i].getWidth() / 2 - 3, 87 + i * 95);
                        labelPointFight[i].setFontScale(1.5f);

                        addListener(i, true, labelFreePoint);
                        addListener(i, false, labelFreePoint);

                        card.addActor(plusButton[i]);
                        card.addActor(minusButton[i]);
                        card.addActor(labelPointFight[i]);
                    }
                    return false;
                }
            });
            card.addActor(userPref);

            //load item or load default image
            for (int i = 0; i < 8; i++) {
                emptyBlock[i] = new Image(asset.manager.get("slot.png", Texture.class));
                String value = PREF_ITEMS.getString(PREF_ITEM_HUMAN[i], "");

                if (!value.equals("")) {
                    blockEmpty[i] = false;
                    block[i] = LoadAllItemToGame.getItem(value);
                    block[i].setStan(Item.Stan.HUMAN);
                    imageAddListener(block[i], block[i].getImage(), block[i].getPathImage());
                    updatePositionFitIn(block[i]);
                    updateStats();
                    emptyBlock[i].setBounds(block[i].getImage().getX() - 2, block[i].getImage().getY() - 2, Item.BLOCK_SIZE + 4, Item.BLOCK_SIZE + 4);
                    card.addActor(emptyBlock[i]);
                    card.addActor(block[i].getImage());
                } else {
                    blockEmpty[i] = false;
                    Item item = new Item(PATH_DEFAULT_IMAGE[i], ITEM_TYPES[i]);
                    emptyBlock[i].setBounds(BLOCK_POSITION[i][0] - 2, BLOCK_POSITION[i][1] - 2, Item.BLOCK_SIZE + 4, Item.BLOCK_SIZE + 4);
                    card.addActor(emptyBlock[i]);
                    updatePositionFitIn(item);
                }
            }

            //load item to bag from preferences
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    Image image = new Image(asset.manager.get("emptySlot.png", Texture.class));
                    image.setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    card.addActor(image);
                }
            }
            for (int i = 0; i < 18; i++) {
                String value = PREF_ITEMS.getString("SLOT" + i, "");

                if (!value.equals(""))
                    addItemToBag(LoadAllItemToGame.getItem(value), i);
                else
                    slotEmpty[i] = false;
            }
        }
    }

    public void addItemToBag(Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if(slotNr == slot){
                    item.getImage().setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE +10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    item.setStan(Item.Stan.BAG);
                    imageAddListener(item, item.getImage(), item.getPathImage());
                    slotEmpty[slotNr] = true;

                    PREF_ITEMS.putString("SLOT" + slotNr, item.getItemKey());//TODO delete those two line and test are it work
                    PREF_ITEMS.flush();

                    card.addActor(item.getImage());
                    return;
                }
                slotNr++;
            }
        }
        if(slotNr == 18){
            //TODO show message by fight about bag is full
        }
    }

    private void updatePositionFitIn(Item item) {
        switch (item.getItemType()) {
            case HELMET:
                item.getImage().setPosition(BLOCK_POSITION[0][0], BLOCK_POSITION[0][1]);
                clearDefaultImage(item, 0);
                break;
            case ARMOR:
                item.getImage().setPosition(BLOCK_POSITION[1][0], BLOCK_POSITION[1][1]);
                clearDefaultImage(item, 1);
                break;
            case PANTS:
                item.getImage().setPosition(BLOCK_POSITION[2][0], BLOCK_POSITION[2][1]);
                clearDefaultImage(item, 2);
                break;
            case SHOES:
                item.getImage().setPosition(BLOCK_POSITION[3][0], BLOCK_POSITION[3][1]);
                clearDefaultImage(item, 3);
                break;
            case WAPON:
                item.getImage().setPosition(BLOCK_POSITION[4][0], BLOCK_POSITION[4][1]);
                clearDefaultImage(item, 4);
                break;
            case ITEM_BLOCK:
                item.getImage().setPosition(BLOCK_POSITION[5][0], BLOCK_POSITION[5][1]);
                clearDefaultImage(item, 5);
                break;
            case RING:
                item.getImage().setPosition(BLOCK_POSITION[6][0], BLOCK_POSITION[6][1]);
                clearDefaultImage(item, 6);
                break;
            case ITEM_HAND:
                item.getImage().setPosition(BLOCK_POSITION[7][0], BLOCK_POSITION[7][1]);
                clearDefaultImage(item, 7);
                break;
        }
        item.setStan(Item.Stan.HUMAN);
    }

    private void clearDefaultImage(Item item, int i){
        if(PREF_ITEMS.getString(item.getItemType().toString()).equals("")){
            card.addActor(item.getImage());
            blockEmpty[i] = true;
        }
        if(blockEmpty[i] && !PREF_ITEMS.getString(item.getItemType().toString()).equals("")){
            block[i].getImage().remove();
            block[i] = item;
            card.addActor(block[i].getImage());
            blockEmpty[i] = false;
        }else
            block[i] = item;
    }

    private void updatePositionFitOut(Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (!slotEmpty[slotNr]) {
                    if (slotNr != slot)
                        PREF_ITEMS.putString("SLOT" + slot, "");

                    item.getImage().setPosition(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10);
                    item.setStan(Item.Stan.BAG);
                    slotEmpty[slotNr] = true;

                    PREF_ITEMS.putString("SLOT" + slotNr, item.getItemKey());
                    PREF_ITEMS.flush();

                    Image image = item.getImage();
                    item.getImage().remove();
                    card.addActor(image);
                    return;
                }
                slotNr++;
            }
        }
    }

    private void updatePositionFitOut(Item item) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (!slotEmpty[slotNr]) {
                    item.getImage().setPosition(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10);
                    item.setStan(Item.Stan.BAG);
                    slotEmpty[slotNr] = true;

                    PREF_ITEMS.putString("SLOT" + slotNr, item.getItemKey());
                    PREF_ITEMS.flush();

                    Image image = item.getImage();
                    item.getImage().remove();
                    card.addActor(image);
                    return;
                }
                slotNr++;
            }
        }
    }

    private void imageAddListener(final Item item, final Image image, final String pathImage) {
        final float SIZE_ICONS = 0.7f;
        image.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                if (!blockClick) {
                    blockClick = true;

                    final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
                    textStyle.font = font;
                    textStyle.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("itemButton.png", Texture.class)));

                    takeOn = new TextButton("Załóż", textStyle);
                    takeOff = new TextButton("Zdejmij", textStyle);
                    drop = new TextButton("Wyrzuć", textStyle);
                    cancel = new TextButton("Anuluj", textStyle);
                    backgroundUp = new Image(asset.manager.get("statsBackground.png", Texture.class));
                    backgroundUp.setBounds(0, 240, BaseMap.VIEW_WIDTH, 190);

                    Label.LabelStyle style = new Label.LabelStyle();
                    style.font = font;

                    Label.LabelStyle styleGreen = new Label.LabelStyle();
                    styleGreen.font = font;
                    styleGreen.fontColor = new Color(Color.LIGHT_GRAY);

                    itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));
                    itemName = new Label("" + item.getItemName(), style);
                    itemLevelRequire = new Label("Wymagany poziom: " + item.getLevelRequire(), style);
                    itemHp = new Label("Punkty Życia +" + item.getHp(), style);
                    itemStrong = new Label("Siła +" + item.getStrong(), style);
                    itemWiedza = new Label("Wiedza +" + item.getWiedza(), style);
                    itemArmor = new Label("Pancerz +" + item.getArmor() + "%", style);
                    itemDefenseFiz = new Label("Zręczność +" + item.getDefenseFiz(), style);
                    itemDefenseMag = new Label("Magia +" + item.getDefenseMag(), style);
                    money = new Image(asset.manager.get("uiMoney.png", Texture.class));
                    itemPrice = new Label("Cena: " + (int)(item.getCashValue()*0.35f), style);
                    infoStorage = new Label("PLECAK", styleGreen);
                    itemBackground = new Image(asset.manager.get("slotInfoItem.png", Texture.class));
                    barName = new Image(asset.manager.get("nameBar.png", Texture.class));
                    barPrice = new Image(asset.manager.get("barX.png", Texture.class));
                    hpIcon = new Image(asset.manager.get("hp.jpg", Texture.class));
                    silaIcon = new Image(asset.manager.get("sila.jpg", Texture.class));
                    wiedzaIcon = new Image(asset.manager.get("wiedza.jpg", Texture.class));
                    pancerzIcon = new Image(asset.manager.get("pancerz.jpg", Texture.class));
                    zrecznoscIcon = new Image(asset.manager.get("zrecznosc.jpg", Texture.class));
                    magiaIcon = new Image(asset.manager.get("magia.jpg", Texture.class));

                    float scale = 0.5f;

                    if(item.getLevelRequire() > hero.getLevel())
                        itemLevelRequire.setColor(Color.RED);

                    itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
                    itemName.setPosition((BaseMap.VIEW_WIDTH + 75) / 2 - itemName.getWidth()*scale/ 2, backgroundUp.getY() + 150);
                    itemHp.setPosition(30, backgroundUp.getY() + 85);
                    itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundUp.getY() + 85);
                    itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundUp.getY() + 55);
                    itemWiedza.setPosition(30, backgroundUp.getY() + 55);
                    itemDefenseFiz.setPosition(30, backgroundUp.getY() + 25);
                    itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundUp.getY() + 25);
                    itemPrice.setPosition(BaseScreen.VIEW_WIDTH /2 -itemPrice.getWidth()*scale /2 +15, backgroundUp.getY() -itemPrice.getHeight()*scale /2 +2);
                    money.setBounds(itemPrice.getX() +itemPrice.getWidth()*scale +10, itemPrice.getY() +itemPrice.getHeight()*scale /2, 20, 20);
                    infoStorage.setPosition(5, itemPrice.getY());
                    barName.setBounds(itemName.getX() -10, itemName.getY() +2, itemName.getWidth()*scale +18, itemName.getHeight()*scale +15);
                    itemLevelRequire.setPosition(barName.getX() +(itemName.getWidth()*scale/2 -itemLevelRequire.getWidth()*scale / 2), backgroundUp.getY() + 120);
                    itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
                    barPrice.setBounds(0, backgroundUp.getY(), BaseMap.VIEW_WIDTH +15, 25);
                    hpIcon.setBounds(itemHp.getX() -hpIcon.getWidth() *SIZE_ICONS -1, itemHp.getY() +hpIcon.getHeight() *(1.1f -SIZE_ICONS), hpIcon.getWidth() *SIZE_ICONS, hpIcon.getHeight() *SIZE_ICONS);
                    silaIcon.setBounds(itemStrong.getX() -silaIcon.getWidth() *SIZE_ICONS -1, itemStrong.getY() +silaIcon.getHeight() *(1.1f -SIZE_ICONS), silaIcon.getWidth() *SIZE_ICONS, silaIcon.getHeight() *SIZE_ICONS);
                    wiedzaIcon.setBounds(itemWiedza.getX() -wiedzaIcon.getWidth() *SIZE_ICONS -1, itemWiedza.getY() +wiedzaIcon.getHeight() *(1.1f -SIZE_ICONS), wiedzaIcon.getWidth() *SIZE_ICONS, wiedzaIcon.getHeight() *SIZE_ICONS);
                    pancerzIcon.setBounds(itemArmor.getX() -pancerzIcon.getWidth() *SIZE_ICONS -1, itemArmor.getY() +pancerzIcon.getHeight() *(1.1f -SIZE_ICONS), pancerzIcon.getWidth() *SIZE_ICONS, pancerzIcon.getHeight() *SIZE_ICONS);
                    zrecznoscIcon.setBounds(itemDefenseFiz.getX() -zrecznoscIcon.getWidth() *SIZE_ICONS -1, itemDefenseFiz.getY() +zrecznoscIcon.getHeight() *(1.1f -SIZE_ICONS), zrecznoscIcon.getWidth() *SIZE_ICONS, zrecznoscIcon.getHeight() *SIZE_ICONS);
                    magiaIcon.setBounds(itemDefenseMag.getX() -magiaIcon.getWidth() *SIZE_ICONS -1, itemDefenseMag.getY() +magiaIcon.getHeight() *(1.1f -SIZE_ICONS), magiaIcon.getWidth() *SIZE_ICONS, magiaIcon.getHeight() *SIZE_ICONS);

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
                    infoStorage.setFontScale(scale);
                    itemLevelRequire.setFontScale(scale);

                    switch (item.getStan()) {
                        case BAG:
                            System.out.println("BAG");
                            try {
                                if (!PREF_ITEMS.getString(item.getItemType().toString()).equals("")) {
                                    backgroundDown = new Image(asset.manager.get("statsBackground.png", Texture.class));
                                    backgroundDown.setBounds(0, 0, BaseMap.VIEW_WIDTH, 190);

                                    final Item itemUp = LoadAllItemToGame.getItem(PREF_ITEMS.getString(item.getItemType().toString()));
                                    itemUp.setStan(Item.Stan.BAG);
                                    itemImageDown = new Image(new Texture(Gdx.files.internal(itemUp.getPathImage())));
                                    itemNameDown = new Label("" + itemUp.getItemName(), style);
                                    itemLevelRequireDown = new Label("Wymagany poziom: " + itemUp.getLevelRequire(), style);
                                    itemHpDown = new Label("Punkty życia +" + itemUp.getHp(), style);
                                    itemStrongDown = new Label("Siła +" + itemUp.getStrong(), style);
                                    itemWiedzaDown = new Label("Wiedza +" + itemUp.getWiedza(), style);
                                    itemArmorDown = new Label("Pancerz +" + itemUp.getArmor() + "%", style);
                                    itemDefenseFizDown = new Label("Zręczność +" + itemUp.getDefenseFiz(), style);
                                    itemDefenseMagDown = new Label("Magia +" + itemUp.getDefenseMag(), style);
                                    moneyDown = new Image(asset.manager.get("uiMoney.png", Texture.class));
                                    itemPriceDown = new Label("Cena: " + (int)(itemUp.getCashValue()*0.35f), style);
                                    infoStorageDown = new Label("ZAŁOŻONE", styleGreen);
                                    itemBackgroundDown = new Image(asset.manager.get("slotInfoItem.png", Texture.class));
                                    barNameDown = new Image(asset.manager.get("nameBar.png", Texture.class));
                                    barPriceDown = new Image(asset.manager.get("barX.png", Texture.class));
                                    hpIconDown = new Image(asset.manager.get("hp.jpg", Texture.class));
                                    silaIconDown = new Image(asset.manager.get("sila.jpg", Texture.class));
                                    wiedzaIconDown = new Image(asset.manager.get("wiedza.jpg", Texture.class));
                                    pancerzIconDown = new Image(asset.manager.get("pancerz.jpg", Texture.class));
                                    zrecznoscIconDown = new Image(asset.manager.get("zrecznosc.jpg", Texture.class));
                                    magiaIconDown = new Image(asset.manager.get("magia.jpg", Texture.class));

                                    if(itemUp.getLevelRequire() > hero.getLevel())
                                        itemLevelRequire.setColor(Color.ROYAL);

                                    //Item INFO
                                    itemImageDown.setBounds(20, backgroundDown.getY() + 120, 60, 60);
                                    itemNameDown.setPosition((BaseMap.VIEW_WIDTH + 75) / 2 - itemNameDown.getWidth()*(scale)/ 2, backgroundDown.getY() + 150);
                                    itemHpDown.setPosition(30, backgroundDown.getY() + 85);
                                    itemArmorDown.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundDown.getY() + 85);
                                    itemStrongDown.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundDown.getY() + 55);
                                    itemWiedzaDown.setPosition(30, backgroundDown.getY() + 55);
                                    itemDefenseFizDown.setPosition(30, backgroundDown.getY() + 25);
                                    itemDefenseMagDown.setPosition(BaseMap.VIEW_WIDTH / 2 +35, backgroundDown.getY() + 25);
                                    itemPriceDown.setPosition(BaseScreen.VIEW_WIDTH /2 -itemPriceDown.getWidth()*scale /2 +15, backgroundDown.getY() -itemPriceDown.getHeight()*scale /2 +2);
                                    moneyDown.setBounds(itemPriceDown.getX() +itemPriceDown.getWidth()*scale +10, itemPriceDown.getY() +itemPriceDown.getHeight()*scale /2, 20, 20);
                                    infoStorageDown.setPosition(5, itemPriceDown.getY());
                                    barNameDown.setBounds(itemNameDown.getX() -10, itemNameDown.getY() +2, itemNameDown.getWidth()*scale +18, itemNameDown.getHeight()*scale +15);
                                    itemLevelRequireDown.setPosition(barNameDown.getX() +(itemNameDown.getWidth()*scale/2 -itemLevelRequireDown.getWidth()*scale / 2), backgroundDown.getY() + 120);
                                    itemBackgroundDown.setBounds(15, backgroundDown.getY() + 115, 70, 70);
                                    barPriceDown.setBounds(0, backgroundDown.getY(), BaseMap.VIEW_WIDTH +15, 25);
                                    hpIconDown.setBounds(itemHpDown.getX() -hpIconDown.getWidth() *SIZE_ICONS -1, itemHpDown.getY() +hpIconDown.getHeight() *(1.1f -SIZE_ICONS), hpIconDown.getWidth() *SIZE_ICONS, hpIconDown.getHeight() *SIZE_ICONS);
                                    silaIconDown.setBounds(itemStrongDown.getX() -silaIconDown.getWidth() *SIZE_ICONS -1, itemStrongDown.getY() +silaIconDown.getHeight() *(1.1f -SIZE_ICONS), silaIconDown.getWidth() *SIZE_ICONS, silaIconDown.getHeight() *SIZE_ICONS);
                                    wiedzaIconDown.setBounds(itemWiedzaDown.getX() -wiedzaIconDown.getWidth() *SIZE_ICONS -1, itemWiedzaDown.getY() +wiedzaIconDown.getHeight() *(1.1f -SIZE_ICONS), wiedzaIconDown.getWidth() *SIZE_ICONS, wiedzaIconDown.getHeight() *SIZE_ICONS);
                                    pancerzIconDown.setBounds(itemArmorDown.getX() -pancerzIconDown.getWidth() *SIZE_ICONS -1, itemArmorDown.getY() +pancerzIconDown.getHeight() *(1.1f -SIZE_ICONS), pancerzIconDown.getWidth() *SIZE_ICONS, pancerzIconDown.getHeight() *SIZE_ICONS);
                                    zrecznoscIconDown.setBounds(itemDefenseFizDown.getX() -zrecznoscIconDown.getWidth() *SIZE_ICONS -1, itemDefenseFizDown.getY() +zrecznoscIconDown.getHeight() *(1.1f -SIZE_ICONS), zrecznoscIconDown.getWidth() *SIZE_ICONS, zrecznoscIconDown.getHeight() *SIZE_ICONS);
                                    magiaIconDown.setBounds(itemDefenseMagDown.getX() -magiaIconDown.getWidth() *SIZE_ICONS -1, itemDefenseMagDown.getY() +magiaIconDown.getHeight() *(1.1f -SIZE_ICONS), magiaIconDown.getWidth() *SIZE_ICONS, magiaIconDown.getHeight() *SIZE_ICONS);

                                    itemPriceDown.setColor(Color.GOLD);

                                    itemNameDown.setFontScale(scale);
                                    itemHpDown.setFontScale(scale);
                                    itemArmorDown.setFontScale(scale);
                                    itemNameDown.setFontScale(scale);
                                    itemStrongDown.setFontScale(scale);
                                    itemWiedzaDown.setFontScale(scale);
                                    itemDefenseFizDown.setFontScale(scale);
                                    itemDefenseMagDown.setFontScale(scale);
                                    itemPriceDown.setFontScale(scale);
                                    infoStorageDown.setFontScale(scale);
                                    itemLevelRequireDown.setFontScale(scale);

                                    takeOn.setBounds(0, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    takeOn.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            if(item.getLevelRequire() <= hero.getLevel()) {
                                                int row = (int) (item.getImage().getX() - 10) / 50;
                                                int column = (int) item.getImage().getY() / 50;
                                                if (column == 2)
                                                    column = 0;
                                                else if (column == 0)
                                                    column = 2;
                                                int slotNr = column * 6 + row;

                                                slotEmpty[slotNr] = false;

                                                for (int i = 0; i < 8; i++) {
                                                    if (item.getItemType().toString().equals(PREF_ITEM_HUMAN[i])) {
                                                        updatePositionFitOut(block[i], slotNr);
                                                    }
                                                }
                                                updatePositionFitIn(item);
                                                PREF_ITEMS.putString(item.getItemType().toString(), item.getItemKey());
                                                PREF_ITEMS.flush();
                                                updateStats();

                                                removeAllDown();
                                                removeAll();
                                                soundEquipment.play(0.5f);
                                            }else{
                                                removeAll();
                                                removeAllDown();
                                                new Screen.InfoScreen("Masz zbyt maly poziom\naby zalozyc ten przedmiot", 2.5f, card);
                                            }
                                            return false;
                                        }
                                    });

                                    drop.setBounds(BaseMap.VIEW_WIDTH / 3, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    drop.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            int row = (int) (item.getImage().getX() - 10) / 50;
                                            int column = (int) item.getImage().getY() / 50;
                                            if (column == 2)
                                                column = 0;
                                            else if (column == 0)
                                                column = 2;
                                            int slotNr = column * 6 + row;

                                            slotEmpty[slotNr] = false;
                                            item.getImage().remove();
                                            PREF_ITEMS.putString("SLOT" + slotNr, "");
                                            PREF_ITEMS.flush();

                                            removeAllDown();
                                            removeAll();
                                            soundEquipment.play(0.5f);
                                            return false;
                                        }
                                    });

                                    cancel.setBounds((BaseMap.VIEW_WIDTH / 3) * 2, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    cancel.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            Menu.getSoundClick().play();
                                            removeAllDown();
                                            removeAll();
                                            return false;
                                        }
                                    });
                                    //Add actor on stage
                                    addAllActorToStage(backgroundUp, backgroundDown, itemBackground, itemImage, barName, itemName, itemLevelRequire, itemHp, itemStrong,
                                            itemWiedza, itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, infoStorage, money, itemPrice, itemBackgroundDown, itemImageDown, barNameDown,
                                            itemNameDown, itemLevelRequireDown, itemHpDown, itemStrongDown, itemWiedzaDown, itemArmorDown,
                                            itemDefenseFizDown, itemDefenseMagDown, barPriceDown, moneyDown, itemPriceDown, infoStorageDown, takeOn,  drop, cancel,
                                            hpIcon, silaIcon, wiedzaIcon, pancerzIcon, magiaIcon, zrecznoscIcon, hpIconDown, silaIconDown, wiedzaIconDown, pancerzIconDown, zrecznoscIconDown, magiaIconDown);
                                } else {
                                    takeOn.setBounds(0, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    takeOn.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            if(item.getLevelRequire() <= hero.getLevel()) {
                                                PREF_ITEMS.putString(item.getItemType().toString(), item.getItemKey());
                                                PREF_ITEMS.flush();

                                                int row = (int) (item.getImage().getX() - 10) / 50;
                                                int column = (int) item.getImage().getY() / 50;
                                                if (column == 2)
                                                    column = 0;
                                                else if (column == 0)
                                                    column = 2;
                                                int slotNr = column * 6 + row;

                                                System.out.println(PREF_ITEMS.getString("WAPON"));

                                                updatePositionFitIn(item);
                                                slotEmpty[slotNr] = false;
                                                PREF_ITEMS.putString("SLOT" + slotNr, "");
                                                PREF_ITEMS.flush();
                                                updateStats();

                                                System.out.println(PREF_ITEMS.getString("WAPON"));
                                                System.out.println(blockEmpty[4]);

                                                removeAll();
                                                soundEquipment.play(0.5f);
                                            }else{
                                                removeAll();
                                                new Screen.InfoScreen("Masz zbyt maly poziom\naby zalozyc ten przedmiot", 2.5f, card);
                                            }
                                            return false;
                                        }
                                    });

                                    drop.setBounds(BaseMap.VIEW_WIDTH / 3, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    drop.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            int row = (int) (item.getImage().getX() - 10) / 50;
                                            int column = (int) item.getImage().getY() / 50;
                                            if (column == 2)
                                                column = 0;
                                            else if (column == 0)
                                                column = 2;
                                            int slotNr = column * 6 + row;

                                            slotEmpty[slotNr] = false;
                                            item.getImage().remove();
                                            PREF_ITEMS.putString("SLOT" + slotNr, "");
                                            PREF_ITEMS.flush();
                                            updateStats();
                                            removeAll();
                                            soundEquipment.play(0.5f);
                                            return false;
                                        }
                                    });

                                    cancel.setBounds((BaseMap.VIEW_WIDTH / 3) * 2, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    cancel.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            Menu.getSoundClick().play();
                                            removeAll();
                                            return false;
                                        }
                                    });
                                    //Add Actor on stage
                                    addAllActorToStage(backgroundUp, itemBackground, itemImage, barName, itemName, itemLevelRequire, itemHp, itemStrong, itemWiedza,
                                            itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, money, itemPrice, infoStorage, takeOn,  drop, cancel,
                                            hpIcon, silaIcon, wiedzaIcon, pancerzIcon, magiaIcon, zrecznoscIcon);
                                }
                                break;
                            } catch (CloneNotSupportedException e) {
                            }
                        case HUMAN:
                            System.out.println("HUMAN");
                            takeOff.setBounds(0, 190, BaseMap.VIEW_WIDTH / 2, 50);
                            takeOff.addListener(new InputListener() {
                                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                    int i = 0;
                                    for (boolean slot : slotEmpty)
                                        if (slot == true)
                                            i++;

                                    if (i == 18) {
                                        BitmapFont font = MyGdxGame.createDistanceFont();
                                        Label.LabelStyle style = new Label.LabelStyle();
                                        style.font = font;
                                        style.fontColor = new Color(Color.RED);
                                        new Screen.InfoScreen("Plecak jest pelny", 1.6f, card);

                                        removeAll();
                                        return false;
                                    }else {
                                        updatePositionFitOut(item);
                                        addDefaultImage(item.getItemType().toString());
                                        PREF_ITEMS.putString(item.getItemType().toString(), "");
                                        PREF_ITEMS.flush();
                                        updateStats();
                                        removeAll();
                                        soundEquipment.play(0.5f);
                                        return false;
                                    }
                                }
                            });

                            cancel.setBounds(BaseMap.VIEW_WIDTH / 2, 190, BaseMap.VIEW_WIDTH / 2, 50);
                            cancel.addListener(new InputListener() {
                                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                    Menu.getSoundClick().play();
                                    removeAll();
                                    return false;
                                }
                            });
                            //Add Actor on stage
                            addAllActorToStage(backgroundUp, itemBackground, itemImage, barName, itemName, itemLevelRequire, itemHp, itemStrong, itemWiedza,
                                    itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, money, itemPrice, infoStorage, takeOff, cancel,
                                    hpIcon, silaIcon, wiedzaIcon, pancerzIcon, magiaIcon, zrecznoscIcon);
                            //
                            break;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
    }

    private void removeAll(){
        blockClick = false;
        itemImage.remove();
        itemName.remove();
        itemLevelRequire.remove();
        itemHp.remove();
        itemStrong.remove();
        itemWiedza.remove();
        itemArmor.remove();
        itemDefenseFiz.remove();
        itemDefenseMag.remove();
        itemPrice.remove();
        barName.remove();
        itemBackground.remove();
        barPrice.remove();
        infoStorage.remove();
        money.remove();
        cancel.remove();
        drop.remove();
        takeOn.remove();
        takeOff.remove();
        backgroundUp.remove();
        hpIcon.remove();
        silaIcon.remove();
        wiedzaIcon.remove();
        pancerzIcon.remove();
        zrecznoscIcon.remove();
        magiaIcon.remove();
    }

    private void removeAllDown(){
        itemImageDown.remove();
        itemNameDown.remove();
        itemLevelRequireDown.remove();
        itemHpDown.remove();
        itemStrongDown.remove();
        itemWiedzaDown.remove();
        itemArmorDown.remove();
        itemDefenseFizDown.remove();
        itemDefenseMagDown.remove();
        itemPriceDown.remove();
        barNameDown.remove();
        itemBackgroundDown.remove();
        barPriceDown.remove();
        infoStorageDown.remove();
        moneyDown.remove();
        backgroundDown.remove();
        hpIconDown.remove();
        wiedzaIconDown.remove();
        silaIconDown.remove();
        pancerzIconDown.remove();
        zrecznoscIconDown.remove();
        magiaIconDown.remove();
    }

    private static void addListener(final int iterator, boolean plus, final Label label){
        if(plus) {
            plusButton[iterator].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    int actualPoint = Integer.parseInt(labelPointFight[iterator].getText().toString());
                    if(actualPoint < 5 && freePointFight >= 0) {
                        actualPoint++;
                        freePointFight--;
                        label.setText("Free points: " + freePointFight);
                        label.setFontScale(1);
                        labelPointFight[iterator].setText(String.valueOf(actualPoint));
                    }
                    return false;
                }
            });
        }else{
            minusButton[iterator].addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    int actualPoint = Integer.parseInt(labelPointFight[iterator].getText().toString());
                    if (actualPoint > 0 && freePointFight < 10) {
                        actualPoint--;
                        freePointFight++;
                        label.setText("Free points: " + freePointFight);
                        label.setFontScale(1);
                        labelPointFight[iterator].setText(String.valueOf(actualPoint));
                    }
                    return false;
                }
            });
        }
    }

    private static void addDefaultImage(String name) {
        for(int i = 0; i < 8; i++){
            if(PREF_ITEM_HUMAN[i].equals(name)){
                blockEmpty[i] = true;
                Item item = new Item(PATH_DEFAULT_IMAGE[i], ITEM_TYPES[i]);
                item.getImage().setBounds(BLOCK_POSITION[i][0], BLOCK_POSITION[i][1], Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                block[i] = item;
                card.addActor(block[i].getImage());
            }
        }
    }

    private static void updateStats(){
        try {
            new UpdateHeroStats(hero);
        } catch (CloneNotSupportedException e) {}
    }

    private static void addAllActorToStage(Actor ... actor){
        for(Actor object: actor)
            card.addActor(object);
    }

    /**
     * Getter and Setter
     */
    public static void setBlockClick(boolean click){
        blockClick = click;
    }

    public static Texture getTextureWapon() throws CloneNotSupportedException {
        Preferences pref = Gdx.app.getPreferences(PREF_NAME_EQ);
        if(pref.getString("WAPON", "").equals(""))
            return null;

        return LoadAllItemToGame.getItem(pref.getString("WAPON", "")).getTexture();
    }

    public static boolean getBlockClick(){
        return blockClick;
    }

    public static void setSlotEmpty(int index, boolean isEmpty){
        slotEmpty[index] = isEmpty;
    }
}
