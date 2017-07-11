package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import Screen.BaseScreen;

/**
 * Created by Sebastian on 2017-06-10.
 */

public class Bag {
    private Hero hero;

    private Stage stage;
    private Stage card;

    private ImageButton buttonEq;
    private ImageButton buttonStats;
    private ImageButton buttonQuest;
    private ImageButton buttonExit;
    private ImageButton closeGame;

    private Image background;
    private Image separator;

    private BitmapFont font;
    private Label.LabelStyle styleWhite;
    private Label infoCloseCard;
    private Label infoCloseGame;

    public Bag(Stage stage, Stage card, Hero hero){
        this.hero = hero;
        this.stage = stage;
        this.card = card;

        create();
    }

    private void create() {
        background = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        background.setSize(BaseScreen.VIEW_WIDTH, BaseScreen.VIEW_HEIGHT - 50);

        buttonEq = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("eq.png"))));
        buttonEq.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                try {
                    initCardEq();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                System.out.println("Eq");
                return false;
            }
        });
        buttonEq.setPosition(background.getX() + 30, 385);

        buttonStats = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("stats.png"))));
        buttonStats.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardStats();
                System.out.println("Stats");
                return false;
            }
        });
        buttonStats.setPosition(background.getX() + 103, 385);

        buttonQuest = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("quest.png"))));
        buttonQuest.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardQuest();
                System.out.println("Quest");
                return false;
            }
        });
        buttonQuest.setPosition(background.getX() + 176, 385);

        buttonExit = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("exit.png"))));
        buttonExit.addListener(new InputListener(){
            public boolean touchDown (InputEvent ev, float x, float y, int pointer, int button){
                initCardExit();
                System.out.println("Exit");
                return false;
            }
        });
        buttonExit.setPosition(background.getX() + 250, 385);

        stage.addActor(background);
        stage.addActor(buttonEq);
        stage.addActor(buttonStats);
        stage.addActor(buttonQuest);
        stage.addActor(buttonExit);
    }

    private void initCardEq() throws CloneNotSupportedException {
        card.clear();
        new Equipment(card, hero);
    }

    private void initCardStats() {
        card.clear();
        new StatsHero(card, hero);
    }

    private void initCardQuest() {
        //TODO init quest card
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

        closeGame = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("exitGame.png")))));
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
}
