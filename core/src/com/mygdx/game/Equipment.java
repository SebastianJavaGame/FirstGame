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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-06-10.
 */

public class Equipment {
    //Value
    static final float FONT_SIZE_NOT_AVILABLE_SLOT = 1;
    //
    public static final Preferences PREF_FIGHT = Gdx.app.getPreferences("FIGHT");
    public static final String[] KEY_PREF_FIGHT = new String[]{"ATTACK_PHYSICS", "DEFENSE_PHYSICS", "ATTACK_MAGIC", "DEFENSE_MAGIC"};

    public static final String PREF_NAME_EQ = "ITEMS";
    public static final String[] PREF_ITEM_HUMAN = new String[]{
            "HELMET", "ARMOR", "PANTS", "SHOES", "WAPON", "ITEM_BLOCK", "RING", "ITEM_HAND"};

    public static final String[] PATH_DEFAULT_IMAGE = new String[]{
            "helm.png", "zbroja.png", "spodnie.png", "buty.png", "bron.png", "tarcza.png", "pierscien.png", "rekawice.png"
    };

    public static final Item.ItemType[] ITEM_TYPES = new Item.ItemType[]{
            Item.ItemType.HELMET, Item.ItemType.ARMOR, Item.ItemType.PANTS, Item.ItemType.SHOES, Item.ItemType.WAPON, Item.ItemType.ITEM_BLOCK, Item.ItemType.RING, Item.ItemType.HAND_ITEM
    };

    public static final int BLOCK_POSITION[][] = new int[][]{{BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 324}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 271},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 218}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2, 165},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 - Item.BLOCK_SIZE - 3, 271}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 + Item.BLOCK_SIZE + 3, 271},
            {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 + Item.BLOCK_SIZE + 3, 218}, {BaseMap.VIEW_WIDTH / 2 - Item.BLOCK_SIZE / 2 - Item.BLOCK_SIZE - 3, 218}};

    private static final Preferences pref = Gdx.app.getPreferences(PREF_NAME_EQ);

    private static Hero hero;
    private static Stage card;

    private static Image[] emptyBlock;
    private static Item[] block;
    private static boolean[] blockEmpty;
    private static boolean[] slotEmpty;

    private static Label labelFreePoint;
    private static Label[] labelPointFight;
    private static ImageButton[] plusButton;
    private static ImageButton[] minusButton;
    private static int freePointFight;

    private static boolean blockClick = false;

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

    private static void create() throws CloneNotSupportedException {
        ImageButton userPref = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonUserPref.png")))));
        userPref.setPosition(250, 180);
        userPref.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setBlockClick(true);
                final Image background = new Image(new Texture(Gdx.files.internal("userPrefBackground.png")));
                labelPointFight = new Label[4];
                plusButton = new ImageButton[4];
                minusButton = new ImageButton[4];

                BitmapFont font = new BitmapFont();
                Label.LabelStyle style = new Label.LabelStyle();
                style.font = font;

                labelFreePoint = new Label("Free points: " + PREF_FIGHT.getInteger("FIGHT_POINT", 10), style);
                labelFreePoint.setPosition(5, 425);
                labelFreePoint.setFontScale(1.4f);

                final TextButton.TextButtonStyle styleSave = new TextButton.TextButtonStyle();
                styleSave.font = font;
                styleSave.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbort.png"))));

                final TextButton buttonSave = new TextButton("Save", styleSave);
                final TextButton buttonCancel = new TextButton("Cancel", styleSave);

                buttonSave.setBounds(30, 5, 110, 40);
                buttonCancel.setBounds(buttonSave.getWidth() +70, 5, 110, 40);

                buttonSave.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        PREF_FIGHT.putInteger("ATTACK_PHYSICS", Integer.parseInt(labelPointFight[0].getText().toString()));
                        PREF_FIGHT.putInteger("DEFENSE_PHYSICS", Integer.parseInt(labelPointFight[1].getText().toString()));
                        PREF_FIGHT.putInteger("ATTACK_MAGIC", Integer.parseInt(labelPointFight[2].getText().toString()));
                        PREF_FIGHT.putInteger("DEFENSE_MAGIC", Integer.parseInt(labelPointFight[3].getText().toString()));
                        PREF_FIGHT.putInteger("FIGHT_POINT", freePointFight);
                        PREF_FIGHT.flush();

                        setBlockClick(false);
                        background.remove();
                        buttonSave.remove();
                        buttonCancel.remove();
                        labelFreePoint.remove();
                        for(int i = 0; i < 4; i++){
                            minusButton[i].remove();
                            plusButton[i].remove();
                            labelPointFight[i].remove();
                        }
                        return false;
                    }
                });

                buttonCancel.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        setBlockClick(false);
                        background.remove();
                        buttonSave.remove();
                        buttonCancel.remove();
                        labelFreePoint.remove();
                        for(int i = 0; i < 4; i++){
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

                for(int i = 0; i < 4; i++) {
                    plusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("plusPref.png")))));
                    minusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("minusPref.png")))));
                    plusButton[i].setPosition(200, (i+1) *95);
                    minusButton[i].setPosition(70, (i+1) *95);

                    labelPointFight[i] = new Label(String.valueOf(PREF_FIGHT.getInteger(KEY_PREF_FIGHT[i])), style);
                    labelPointFight[i].setPosition(BaseMap.VIEW_WIDTH /2 - labelPointFight[i].getWidth() /2 -3, 98 +i *95);
                    labelPointFight[i].setFontScale(2);

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
            emptyBlock[i] = new Image(new Texture(Gdx.files.internal("slot.png")));
            String value = pref.getString(PREF_ITEM_HUMAN[i], "");

            if (!value.equals("")) {
                blockEmpty[i] = false;
                block[i] = LoadAllItemToGame.getItem(value);
                block[i].setStan(Item.Stan.HUMAN);
                imageAddListener(block[i], block[i].getImage(), block[i].getPathImage());
                updatePositionFitIn(block[i]);
                updateStats();
                emptyBlock[i].setBounds(block[i].getImage().getX() -2, block[i].getImage().getY() -2, Item.BLOCK_SIZE +4, Item.BLOCK_SIZE +4);
                card.addActor(emptyBlock[i]);
                card.addActor(block[i].getImage());
            }else{
                blockEmpty[i] = false;
                Item item = new Item(PATH_DEFAULT_IMAGE[i], ITEM_TYPES[i]);
                emptyBlock[i].setBounds(BLOCK_POSITION[i][0] -2, BLOCK_POSITION[i][1] -2, Item.BLOCK_SIZE +4, Item.BLOCK_SIZE +4);
                card.addActor(emptyBlock[i]);
                updatePositionFitIn(item);
            }
        }

        //load item to bag from preferences
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                Image image = new Image(new Texture(Gdx.files.internal("emptySlot.png")));
                image.setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                card.addActor(image);
            }
        }
        for (int i = 0; i < 18; i++) {
            String value = pref.getString("SLOT" + i, "");

            if (!value.equals(""))
                addItemToBag(LoadAllItemToGame.getItem(value), i);
            else
                slotEmpty[i] = false;
        }
    }

    public static void addItemToBag(Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if(slotNr == slot){
                    item.getImage().setBounds(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE +10, Item.BLOCK_SIZE, Item.BLOCK_SIZE);
                    item.setStan(Item.Stan.BAG);
                    imageAddListener(item, item.getImage(), item.getPathImage());
                    slotEmpty[slotNr] = true;

                    pref.putString("SLOT" + slotNr, item.getItemKey());
                    pref.flush();

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

    private static void updatePositionFitIn(Item item) {
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
            case HAND_ITEM:
                item.getImage().setPosition(BLOCK_POSITION[7][0], BLOCK_POSITION[7][1]);
                clearDefaultImage(item, 7);
                break;
        }
        item.setStan(Item.Stan.HUMAN);
    }

    private static void clearDefaultImage(Item item, int i){
        if(pref.getString(item.getItemType().toString()).equals("")){
            card.addActor(item.getImage());
            blockEmpty[i] = true;
        }
        if(blockEmpty[i] && !pref.getString(item.getItemType().toString()).equals("")){
            block[i].getImage().remove();
            block[i] = item;
            card.addActor(block[i].getImage());
            blockEmpty[i] = false;
        }else
            block[i] = item;
    }

    private static void updatePositionFitOut(Item item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (!slotEmpty[slotNr]) {
                    if (slotNr != slot)
                        pref.putString("SLOT" + slot, "");

                    item.getImage().setPosition(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10);
                    item.setStan(Item.Stan.BAG);
                    slotEmpty[slotNr] = true;

                    pref.putString("SLOT" + slotNr, item.getItemKey());
                    pref.flush();

                    Image image = item.getImage();
                    item.getImage().remove();
                    card.addActor(image);
                    return;
                }
                slotNr++;
            }
        }
    }

    private static void updatePositionFitOut(Item item) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (!slotEmpty[slotNr]) {
                    item.getImage().setPosition(10 + j * Item.BLOCK_SIZE, i * Item.BLOCK_SIZE + 10);
                    item.setStan(Item.Stan.BAG);
                    slotEmpty[slotNr] = true;

                    pref.putString("SLOT" + slotNr, item.getItemKey());
                    pref.flush();

                    Image image = item.getImage();
                    item.getImage().remove();
                    card.addActor(image);
                    return;
                }
                slotNr++;
            }
        }
    }

    private static void imageAddListener(final Item item, final Image image, final String pathImage) {
        image.addListener(new InputListener() {
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                if (!blockClick) {
                    blockClick = true;

                    BitmapFont font = new BitmapFont();
                    final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
                    textStyle.font = font;
                    textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("itemButton.png"))));

                    final TextButton takeOn = new TextButton("Take on", textStyle);
                    final TextButton takeOff = new TextButton("Take off", textStyle);
                    final TextButton drop = new TextButton("Drop", textStyle);
                    final TextButton cancel = new TextButton("Cancel", textStyle);
                    final Image backgroundUp = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
                    backgroundUp.setBounds(0, 240, BaseMap.VIEW_WIDTH, 190);

                    Label.LabelStyle style = new Label.LabelStyle();
                    style.font = font;

                    Label.LabelStyle styleGreen = new Label.LabelStyle();
                    styleGreen.font = font;
                    styleGreen.fontColor = new Color(Color.LIGHT_GRAY);

                    final Image itemImage = new Image(new Texture(Gdx.files.internal(pathImage)));
                    final Label itemName = new Label("" + item.getItemName(), style);
                    final Label itemType = new Label("" + item.getItemType().toString(), style);
                    final Label itemHp = new Label("Hp: +" + item.getHp(), style);
                    final Label itemStrong = new Label("Strong: +" + item.getStrong(), style);
                    final Label itemWiedza = new Label("Wiedza: +" + item.getWiedza(), style);
                    final Label itemArmor = new Label("Armor: +" + item.getArmor() + "%", style);
                    final Label itemDefenseFiz = new Label("Defense physics: +" + item.getDefenseFiz(), style);
                    final Label itemDefenseMag = new Label("Defense magic: +" + item.getDefenseMag(), style);
                    final Image money = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
                    final Label itemPrice = new Label(" " + item.getCashValue(), style);
                    final Label infoStorage = new Label("BAG", styleGreen);
                    final Image itemBackground = new Image(new Texture(Gdx.files.internal("slotInfoItem.png")));
                    final Image barName = new Image(new Texture(Gdx.files.internal("nameBar.png")));
                    final Image barPrice = new Image(new Texture(Gdx.files.internal("barX.png")));

                    itemImage.setBounds(20, backgroundUp.getY() + 120, 60, 60);
                    itemName.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 160);
                    itemType.setPosition((BaseMap.VIEW_WIDTH + 80) / 2 - itemName.getWidth() / 2, backgroundUp.getY() + 132);
                    itemHp.setPosition(20, backgroundUp.getY() + 100);
                    itemArmor.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundUp.getY() + 100);
                    itemStrong.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundUp.getY() + 70);
                    itemWiedza.setPosition(20, backgroundUp.getY() + 70);
                    itemDefenseFiz.setPosition(20, backgroundUp.getY() + 40);
                    itemDefenseMag.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundUp.getY() + 40);
                    money.setBounds(BaseMap.VIEW_WIDTH / 2 - itemPrice.getWidth() /2 -10, backgroundUp.getY() + 10, 18, 17);
                    itemPrice.setPosition(money.getX() +20, money.getY());
                    infoStorage.setPosition(5, itemPrice.getY());
                    barName.setBounds(itemName.getX() -20, itemName.getY() -16, itemName.getWidth() +40, itemName.getHeight() +30);
                    itemBackground.setBounds(15, backgroundUp.getY() + 115, 70, 70);
                    barPrice.setBounds(0, itemPrice.getY() -4, BaseMap.VIEW_WIDTH +15, 25);

                    switch (item.getStan()) {
                        case BAG:
                            System.out.println("BAG");
                            try {
                                if (!pref.getString(item.getItemType().toString()).equals("")) {
                                    final Image backgroundDown = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
                                    backgroundDown.setBounds(0, 0, BaseMap.VIEW_WIDTH, 190);

                                    Item itemUp = LoadAllItemToGame.getItem(pref.getString(item.getItemType().toString()));
                                    itemUp.setStan(Item.Stan.BAG);
                                    final Image itemImageDown = new Image(new Texture(Gdx.files.internal(itemUp.getPathImage())));
                                    final Label itemNameDown = new Label("" + itemUp.getItemName(), style);
                                    final Label itemTypeDown = new Label("" + itemUp.getItemType().toString(), style);
                                    final Label itemHpDown = new Label("Hp: +" + itemUp.getHp(), style);
                                    final Label itemStrongDown = new Label("Strong: +" + itemUp.getStrong(), style);
                                    final Label itemWiedzaDown = new Label("Wiedza: +" + itemUp.getWiedza(), style);
                                    final Label itemArmorDown = new Label("Armor: +" + itemUp.getArmor() + "%", style);
                                    final Label itemDefenseFizDown = new Label("Defense physics: +" + itemUp.getDefenseFiz(), style);
                                    final Label itemDefenseMagDown = new Label("Defense magic: +" + itemUp.getDefenseMag(), style);
                                    final Image moneyDown = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
                                    final Label itemPriceDown = new Label(" " + itemUp.getCashValue(), style);
                                    final Label infoStorageDown = new Label("HUMAN", styleGreen);
                                    final Image itemBackgroundDown = new Image(new Texture(Gdx.files.internal("slotInfoItem.png")));
                                    final Image barNameDown = new Image(new Texture(Gdx.files.internal("nameBar.png")));
                                    final Image barPriceDown = new Image(new Texture(Gdx.files.internal("barX.png")));

                                    //Item INFO
                                    itemImageDown.setBounds(20, backgroundDown.getY() + 120, 60, 60);
                                    itemNameDown.setPosition((BaseMap.VIEW_WIDTH + 70) / 2 - itemName.getWidth() / 2, backgroundDown.getY() + 160);
                                    itemTypeDown.setPosition((BaseMap.VIEW_WIDTH + 70) / 2 - itemName.getWidth() / 2, backgroundDown.getY() + 132);
                                    itemHpDown.setPosition(20, backgroundDown.getY() + 100);
                                    itemArmorDown.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundDown.getY() + 100);
                                    itemStrongDown.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundDown.getY() + 70);
                                    itemWiedzaDown.setPosition(20, backgroundDown.getY() + 70);
                                    itemDefenseFizDown.setPosition(20, backgroundDown.getY() + 40);
                                    itemDefenseMagDown.setPosition(BaseMap.VIEW_WIDTH / 2 +20, backgroundDown.getY() + 40);
                                    moneyDown.setBounds(BaseMap.VIEW_WIDTH / 2 - itemPriceDown.getWidth() /2 -10, backgroundDown.getY() + 10, 18, 17);
                                    itemPriceDown.setPosition(moneyDown.getX() +20, moneyDown.getY());
                                    infoStorageDown.setPosition(5, itemPriceDown.getY());
                                    barNameDown.setBounds(itemNameDown.getX() -20, itemNameDown.getY() -16, itemNameDown.getWidth() +40, itemNameDown.getHeight() +30);
                                    itemBackgroundDown.setBounds(15, backgroundDown.getY() + 115, 70, 70);
                                    barPriceDown.setBounds(0, itemPriceDown.getY() -4, BaseMap.VIEW_WIDTH +15, 25);

                                    takeOn.setBounds(0, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    takeOn.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
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
                                            pref.putString(item.getItemType().toString(), item.getItemKey());
                                            pref.flush();
                                            updateStats();

                                            blockClick = false;
                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            itemImageDown.remove();
                                            itemNameDown.remove();
                                            itemTypeDown.remove();
                                            itemHpDown.remove();
                                            itemStrongDown.remove();
                                            itemWiedzaDown.remove();
                                            itemArmorDown.remove();
                                            itemDefenseFizDown.remove();
                                            itemDefenseMagDown.remove();
                                            itemPriceDown.remove();
                                            backgroundUp.remove();
                                            backgroundDown.remove();
                                            barName.remove();
                                            barNameDown.remove();
                                            itemBackground.remove();
                                            itemBackgroundDown.remove();
                                            barPrice.remove();
                                            barPriceDown.remove();
                                            infoStorage.remove();
                                            infoStorageDown.remove();
                                            money.remove();
                                            moneyDown.remove();
                                            return false;
                                        }
                                    });

                                    drop.setBounds(BaseMap.VIEW_WIDTH / 3, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    drop.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            blockClick = false;
                                            int row = (int) (item.getImage().getX() - 10) / 50;
                                            int column = (int) item.getImage().getY() / 50;
                                            if (column == 2)
                                                column = 0;
                                            else if (column == 0)
                                                column = 2;
                                            int slotNr = column * 6 + row;

                                            slotEmpty[slotNr] = false;
                                            item.getImage().remove();
                                            pref.putString("SLOT" + slotNr, "");
                                            pref.flush();

                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            itemImageDown.remove();
                                            itemNameDown.remove();
                                            itemTypeDown.remove();
                                            itemHpDown.remove();
                                            itemStrongDown.remove();
                                            itemWiedzaDown.remove();
                                            itemArmorDown.remove();
                                            itemDefenseFizDown.remove();
                                            itemDefenseMagDown.remove();
                                            itemPriceDown.remove();
                                            backgroundUp.remove();
                                            backgroundDown.remove();
                                            barName.remove();
                                            barNameDown.remove();
                                            itemBackground.remove();
                                            itemBackgroundDown.remove();
                                            barPrice.remove();
                                            barPriceDown.remove();
                                            infoStorage.remove();
                                            infoStorageDown.remove();
                                            money.remove();
                                            moneyDown.remove();
                                            return false;
                                        }
                                    });

                                    cancel.setBounds((BaseMap.VIEW_WIDTH / 3) * 2, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    cancel.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            blockClick = false;
                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            itemImageDown.remove();
                                            itemNameDown.remove();
                                            itemTypeDown.remove();
                                            itemHpDown.remove();
                                            itemStrongDown.remove();
                                            itemWiedzaDown.remove();
                                            itemArmorDown.remove();
                                            itemDefenseFizDown.remove();
                                            itemDefenseMagDown.remove();
                                            itemPriceDown.remove();
                                            backgroundUp.remove();
                                            backgroundDown.remove();
                                            barName.remove();
                                            barNameDown.remove();
                                            itemBackground.remove();
                                            itemBackgroundDown.remove();
                                            barPrice.remove();
                                            barPriceDown.remove();
                                            infoStorage.remove();
                                            infoStorageDown.remove();
                                            money.remove();
                                            moneyDown.remove();
                                            return false;
                                        }
                                    });
                                    //Add actor on stage
                                    addAllActorToStage(backgroundUp, backgroundDown, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong,
                                            itemWiedza, itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, infoStorage, money, itemPrice, itemBackgroundDown, itemImageDown, barNameDown,
                                            itemNameDown, itemTypeDown, itemHpDown, itemStrongDown, itemWiedzaDown, itemArmorDown,
                                            itemDefenseFizDown, itemDefenseMagDown, barPriceDown, moneyDown, itemPriceDown, infoStorageDown, takeOn,  drop, cancel);
                                } else {
                                    takeOn.setBounds(0, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    takeOn.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            pref.putString(item.getItemType().toString(), item.getItemKey());

                                            int row = (int) (item.getImage().getX() - 10) / 50;
                                            int column = (int) item.getImage().getY() / 50;
                                            if (column == 2)
                                                column = 0;
                                            else if (column == 0)
                                                column = 2;
                                            int slotNr = column * 6 + row;

                                            System.out.println(pref.getString("WAPON"));

                                            updatePositionFitIn(item);
                                            slotEmpty[slotNr] = false;
                                            pref.putString("SLOT" + slotNr, "");
                                            pref.flush();
                                            updateStats();

                                            System.out.println(pref.getString("WAPON"));
                                            System.out.println(blockEmpty[4]);

                                            blockClick = false;
                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            backgroundUp.remove();
                                            barName.remove();
                                            itemBackground.remove();
                                            barPrice.remove();
                                            infoStorage.remove();
                                            money.remove();
                                            return false;
                                        }
                                    });

                                    drop.setBounds(BaseMap.VIEW_WIDTH / 3, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    drop.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            blockClick = false;
                                            int row = (int) (item.getImage().getX() - 10) / 50;
                                            int column = (int) item.getImage().getY() / 50;
                                            if (column == 2)
                                                column = 0;
                                            else if (column == 0)
                                                column = 2;
                                            int slotNr = column * 6 + row;

                                            slotEmpty[slotNr] = false;
                                            item.getImage().remove();
                                            pref.putString("SLOT" + slotNr, "");
                                            pref.flush();
                                            updateStats();

                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            backgroundUp.remove();
                                            barName.remove();
                                            itemBackground.remove();
                                            barPrice.remove();
                                            infoStorage.remove();
                                            money.remove();
                                            return false;
                                        }
                                    });

                                    cancel.setBounds((BaseMap.VIEW_WIDTH / 3) * 2, 190, BaseMap.VIEW_WIDTH / 3, 50);
                                    cancel.addListener(new InputListener() {
                                        public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                            blockClick = false;
                                            takeOn.remove();
                                            drop.remove();
                                            cancel.remove();
                                            itemImage.remove();
                                            itemName.remove();
                                            itemType.remove();
                                            itemHp.remove();
                                            itemStrong.remove();
                                            itemWiedza.remove();
                                            itemArmor.remove();
                                            itemDefenseFiz.remove();
                                            itemDefenseMag.remove();
                                            itemPrice.remove();
                                            backgroundUp.remove();
                                            barName.remove();
                                            itemBackground.remove();
                                            barPrice.remove();
                                            infoStorage.remove();
                                            money.remove();
                                            return false;
                                        }
                                    });
                                    //Add Actor on stage
                                    addAllActorToStage(backgroundUp, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong, itemWiedza,
                                            itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, money, itemPrice, infoStorage, takeOn,  drop, cancel);
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
                                        BitmapFont font = new BitmapFont();
                                        Label.LabelStyle style = new Label.LabelStyle();
                                        style.font = font;
                                        style.fontColor = new Color(Color.RED);
                                        Label label = new Label("Bag is full. Please drop item", style);
                                        label.setFontScale(FONT_SIZE_NOT_AVILABLE_SLOT);

                                        label.setPosition(BaseMap.VIEW_WIDTH / 2 - label.getWidth() / 2, BaseMap.VIEW_HEIGHT / 2);
                                        label.addAction(Actions.sequence(
                                                Actions.delay(2),
                                                Actions.fadeOut(1),
                                                Actions.removeActor()
                                        ));
                                        card.addActor(label);

                                        blockClick = false;
                                        takeOff.remove();
                                        cancel.remove();
                                        itemImage.remove();
                                        itemName.remove();
                                        itemType.remove();
                                        itemHp.remove();
                                        itemStrong.remove();
                                        itemWiedza.remove();
                                        itemArmor.remove();
                                        itemDefenseFiz.remove();
                                        itemDefenseMag.remove();
                                        itemPrice.remove();
                                        backgroundUp.remove();
                                        barName.remove();
                                        itemBackground.remove();
                                        barPrice.remove();
                                        infoStorage.remove();
                                        money.remove();
                                        return false;
                                    }else {
                                        updatePositionFitOut(item);
                                        addDefaultImage(item.getItemType().toString());
                                        pref.putString(item.getItemType().toString(), "");
                                        pref.flush();
                                        updateStats();

                                        blockClick = false;
                                        takeOff.remove();
                                        cancel.remove();
                                        itemImage.remove();
                                        itemName.remove();
                                        itemType.remove();
                                        itemHp.remove();
                                        itemStrong.remove();
                                        itemWiedza.remove();
                                        itemArmor.remove();
                                        itemDefenseFiz.remove();
                                        itemDefenseMag.remove();
                                        itemPrice.remove();
                                        backgroundUp.remove();
                                        barName.remove();
                                        itemBackground.remove();
                                        barPrice.remove();
                                        infoStorage.remove();
                                        money.remove();
                                        return false;
                                    }
                                }
                            });

                            cancel.setBounds(BaseMap.VIEW_WIDTH / 2, 190, BaseMap.VIEW_WIDTH / 2, 50);
                            cancel.addListener(new InputListener() {
                                public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                                    blockClick = false;
                                    takeOff.remove();
                                    cancel.remove();
                                    itemImage.remove();
                                    itemName.remove();
                                    itemType.remove();
                                    itemHp.remove();
                                    itemStrong.remove();
                                    itemWiedza.remove();
                                    itemArmor.remove();
                                    itemDefenseFiz.remove();
                                    itemDefenseMag.remove();
                                    itemPrice.remove();
                                    backgroundUp.remove();
                                    barName.remove();
                                    itemBackground.remove();
                                    barPrice.remove();
                                    infoStorage.remove();
                                    money.remove();
                                    return false;
                                }
                            });
                            //Add Actor on stage
                            addAllActorToStage(backgroundUp, itemBackground, itemImage, barName, itemName, itemType, itemHp, itemStrong, itemWiedza,
                                    itemArmor, itemDefenseFiz, itemDefenseMag, barPrice, money, itemPrice, infoStorage, takeOff, cancel);
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

    private static void addListener(final int iterator, boolean plus, final Label label){
        if(plus) {
            plusButton[iterator].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("PLUS");
                    int actualPoint = Integer.parseInt(labelPointFight[iterator].getText().toString());
                    if(actualPoint < 5 && freePointFight > 0) {
                        actualPoint++;
                        freePointFight--;
                        label.setText("Free points: " + freePointFight);
                        label.setFontScale(1.4f);
                        labelPointFight[iterator].setText(String.valueOf(actualPoint));
                    }
                    return false;
                }
            });
        }else{
            minusButton[iterator].addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("MINUS");
                    int actualPoint = Integer.parseInt(labelPointFight[iterator].getText().toString());
                    if (actualPoint > 0 && freePointFight >= 0) {
                        actualPoint--;
                        freePointFight++;
                        label.setText("Free points: " + freePointFight);
                        label.setFontScale(1.4f);
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
        if(pref.getString("WAPON", "").equals(""))
            return null;

        return LoadAllItemToGame.getItem(pref.getString("WAPON", "")).getTexture();
    }

    public static boolean getBlockClick(){
        return blockClick;
    }
}
