package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-06-10.
 */

public class Equipment {
    public static final int BLOCK_SIZE = 50;
    public static final int AMOUNT_ATTRIBUTE = 4;

    private Hero hero;

    private Stage stage;
    private Stage card;

    private ImageButton buttonEq;
    private ImageButton buttonStats;
    private ImageButton buttonExit;
    private ImageButton[] buttonAdd;
    private ImageButton closeGame;

    private Image background;
    private Image bar, barLeft, barRight;
    private Image[] barEmptyStats;
    private Image separator;

    private BlockItem[] block;

    private BitmapFont font;
    private Label.LabelStyle styleWhite;
    private Label.LabelStyle styleGreen;
    private Label infoOne;
    private Label infoTwo;
    private Label attribute[];
    private Label attributeStats[];
    private Label infoCloseCard;
    private Label infoCloseGame;
    private Label freePoint;
    private Label pointToAdd;

    private int point;

    private Table table;

    public Equipment(Stage stage, Stage card, Hero hero){
        this.hero = hero;
        this.stage = stage;
        this.card = card;
        block = new BlockItem[26];
        attribute = new Label[AMOUNT_ATTRIBUTE];
        attributeStats = new Label[AMOUNT_ATTRIBUTE *2];
        barEmptyStats = new Image[AMOUNT_ATTRIBUTE *2];
        buttonAdd = new ImageButton[AMOUNT_ATTRIBUTE];
        point = 0;

        create();
    }

    private void create() {
        background = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        background.setSize(BaseScreen.VIEW_WIDTH, BaseScreen.VIEW_HEIGHT - 50);

        buttonEq = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("eq.png"))));
        buttonEq.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardEq();
                System.out.println("Eq");
                return false;
            }
        });
        buttonEq.setPosition(background.getX() - 7, 380);
        buttonEq.setSize(background.getWidth() /3, 50);
        buttonEq.setOrigin(buttonEq.getWidth() /2, buttonEq.getHeight() /2);

        barLeft = new Image(new Texture(Gdx.files.internal("bar.png")));
        barLeft.setRotation(90);
        barLeft.setPosition(buttonEq.getX() + buttonEq.getWidth() - 7, 380);
        barLeft.setSize(buttonEq.getHeight(), barLeft.getHeight());

        buttonStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stats.png"))));
        buttonStats.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardStats();
                System.out.println("Stats");
                return false;
            }
        });
        buttonStats.setPosition(barLeft.getX() + 5, 380);
        buttonStats.setSize(background.getWidth() /3, 50);

        barRight = new Image(new Texture(Gdx.files.internal("bar.png")));
        barRight.setRotation(90);
        barRight.setPosition(buttonStats.getX() + buttonStats.getWidth() + 8, 380);
        barRight.setSize(buttonStats.getHeight(), barRight.getHeight());

        buttonExit = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("exit.png"))));
        buttonExit.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardExit();
                System.out.println("Exit");
                return false;
            }
        });
        buttonExit.setPosition(buttonStats.getX() + buttonStats.getWidth() + 10, 380);
        buttonExit.setSize(background.getWidth() /3, 50);

        bar = new Image(new Texture(Gdx.files.internal("bar.png")));
        bar.setPosition(background.getX(), 375);
        bar.setSize(background.getWidth(), bar.getHeight());

        stage.addActor(background);
        stage.addActor(buttonEq);
        stage.addActor(buttonStats);
        stage.addActor(buttonExit);
        stage.addActor(bar);
        stage.addActor(barLeft);
        stage.addActor(barRight);
    }

    private void initCardEq() {
        card.clear();

        block[0] = new BlockItem(new Image(new Texture(Gdx.files.internal("helm.png"))));
        block[0].setBlock(background.getX() + background.getWidth() /2 - BLOCK_SIZE /2, 320, BLOCK_SIZE, BLOCK_SIZE);

        block[1] = new BlockItem(new Image(new Texture(Gdx.files.internal("zbroja.png"))));
        block[1].setBlock(background.getX() + background.getWidth() /2 - BLOCK_SIZE /2, 267, BLOCK_SIZE, BLOCK_SIZE);

        block[2] = new BlockItem(new Image(new Texture(Gdx.files.internal("spodnie.png"))));
        block[2].setBlock(background.getX() + background.getWidth() /2 - BLOCK_SIZE /2, 214, BLOCK_SIZE, BLOCK_SIZE);

        block[3] = new BlockItem(new Image(new Texture(Gdx.files.internal("buty.png"))));
        block[3].setBlock(background.getX() + background.getWidth() /2 - BLOCK_SIZE /2, 161, BLOCK_SIZE, BLOCK_SIZE);

        block[4] = new BlockItem(new Image(new Texture(Gdx.files.internal("bron.png"))));
        block[4].setBlock(block[1].getImage().getX() + BLOCK_SIZE +3, 267, BLOCK_SIZE, BLOCK_SIZE);

        block[5] = new BlockItem(new Image(new Texture(Gdx.files.internal("tarcza.png"))));
        block[5].setBlock(block[1].getImage().getX() - BLOCK_SIZE -3, 267, BLOCK_SIZE, BLOCK_SIZE);

        block[6] = new BlockItem(new Image(new Texture(Gdx.files.internal("pierscien.png"))));
        block[6].setBlock(block[2].getImage().getX() - BLOCK_SIZE -3, 214, BLOCK_SIZE, BLOCK_SIZE);

        block[7] = new BlockItem(new Image(new Texture(Gdx.files.internal("rekawice.png"))));
        block[7].setBlock(block[2].getImage().getX() + BLOCK_SIZE +3, 214, BLOCK_SIZE, BLOCK_SIZE);

        int blockNr = 7;
        for(int i = 2; i >= 0; i--){
            for(int j = 0; j < 6; j++){
                blockNr++;

                block[blockNr] = new BlockItem(new Image(new Texture(Gdx.files.internal("emptySlot.png"))));
                block[blockNr].setBlock(((background.getWidth() - BLOCK_SIZE *6 )/2) + background.getX() + j* BLOCK_SIZE, background.getY() + i* BLOCK_SIZE + 3, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        for(BlockItem item: block)
            card.addActor(item.getImage());
    }

    private void initCardStats() {
        card.clear();

        table = new Table();
        font = new BitmapFont();
        styleWhite = new Label.LabelStyle();
        styleGreen = new Label.LabelStyle();
        styleWhite.font = font;
        styleGreen.font = font;
        styleWhite.fontColor = new Color(Color.WHITE);
        styleGreen.fontColor = new Color(Color.GREEN);

        freePoint = new Label("Free points:", styleWhite);
        pointToAdd = new Label(String.valueOf(point), styleGreen);
        infoOne = new Label("Non EQ", styleWhite);
        infoTwo = new Label("With Eq", styleGreen);
        attribute[0] = new Label("Hp", styleWhite);
        attribute[1] = new Label("Strong", styleWhite);
        attribute[2] = new Label("Wiedza", styleWhite);
        attribute[3] = new Label("Defense", styleWhite);

        for(int i = 0; i < barEmptyStats.length; i++){
            barEmptyStats[i] = new Image(new Texture(Gdx.files.internal("barStats.png")));
            barEmptyStats[i].setSize(15, 10);
        }

        for(int i = 0; i < buttonAdd.length; i++){
            buttonAdd[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("add.png"))));
            buttonAdd[i].addListener(new InputListener(){
                public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                    System.out.println("Click button add point to skills");
                    if(point > 0){
                        //TODO add point to skill
                    }
                    return false;
                }
            });
            buttonAdd[i].setSize(15, 10);
        }

        attributeStats[0] = new Label(String.valueOf(hero.getMaxHp()), styleWhite);
        attributeStats[0].setPosition(115 - attributeStats[0].getWidth() /2, 297);

        attributeStats[1] = new Label(String.valueOf(hero.hpEq), styleGreen);
        attributeStats[1].setPosition(215 - attributeStats[1].getWidth() /2, 297);

        attributeStats[2] = new Label(String.valueOf(hero.strong), styleWhite);
        attributeStats[2].setPosition(115 - attributeStats[2].getWidth() /2, 257);

        attributeStats[3] = new Label(String.valueOf(hero.strongEq), styleGreen);
        attributeStats[3].setPosition(215 - attributeStats[3].getWidth() /2, 257);

        attributeStats[4] = new Label(String.valueOf(hero.wiedza), styleWhite);
        attributeStats[4].setPosition(115 - attributeStats[4].getWidth() /2, 217);

        attributeStats[5] = new Label(String.valueOf(hero.wiedzaEq), styleGreen);
        attributeStats[5].setPosition(215 - attributeStats[5].getWidth() /2, 217);

        attributeStats[6] = new Label(String.valueOf(hero.defense), styleWhite);
        attributeStats[6].setPosition(115 - attributeStats[6].getWidth() /2, 177);

        attributeStats[7] = new Label(String.valueOf(hero.defenseEq), styleGreen);
        attributeStats[7].setPosition(215 - attributeStats[7].getWidth() /2, 177);

        table.row();
        table.add();
        table.add(infoOne);
        table.add(infoTwo);
        table.row().padTop(5f);
        table.add(attribute[0]);
        table.add(barEmptyStats[0]);
        table.add(barEmptyStats[4]);
        table.add(buttonAdd[0]);
        table.row().padTop(10f);
        table.add(attribute[1]);
        table.add(barEmptyStats[1]);
        table.add(barEmptyStats[5]);
        table.add(buttonAdd[1]);
        table.row().padTop(10f);
        table.add(attribute[2]);
        table.add(barEmptyStats[2]);
        table.add(barEmptyStats[6]);
        table.add(buttonAdd[2]);
        table.row().padTop(10f);
        table.add(attribute[3]);
        table.add(barEmptyStats[3]);
        table.add(barEmptyStats[7]);
        table.add(buttonAdd[3]);
        table.row().padTop(20f);
        table.add();
        table.add(freePoint);
        table.add(pointToAdd).align(Align.left);
        table.add();

        table.setFillParent(true);
        table.pack();
        card.addActor(table);

        for(Label attribute: attributeStats)
            card.addActor(attribute);
    }

    private void initCardExit() {
        card.clear();

        font = new BitmapFont();
        styleWhite = new Label.LabelStyle();
        styleWhite.font = font;

        infoCloseCard = new Label("If you want close this card \nclick icon at right up corner", styleWhite);
        infoCloseCard.setPosition((background.getWidth() - infoCloseCard.getWidth()) /2, 300);
        infoCloseCard.setFontScale(1.1f);

        separator = new Image(new Texture(Gdx.files.internal("bar.png")));
        separator.setPosition(5, 250);
        separator.setSize(310, 3);

        infoCloseGame = new Label("Exit game", styleWhite);
        infoCloseGame.setPosition((background.getWidth() - infoCloseGame.getWidth()) /2, 180);
        infoCloseGame.setFontScale(1.1f);

        closeGame = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exit.png")))));
        closeGame.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                //TODO point saving game
                Gdx.app.exit();
                return false;
            }
        });
        closeGame.setPosition((background.getWidth() - closeGame.getWidth()) /2, 80);

        card.addActor(infoCloseCard);
        card.addActor(separator);
        card.addActor(infoCloseGame);
        card.addActor(closeGame);
    }

    public void setPoint(int point){
        this.point = point;
    }
}
