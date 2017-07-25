package Screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ExperienceRequired;
import com.mygdx.game.Hero;
import com.mygdx.game.ProgressCircle;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-07-11.
 */

public class FightLose extends BaseScreen {
    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private static final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();

    private Hero hero;
    final private Preferences prefStats;

    private ProgressCircle sprite;
    private PolygonSpriteBatch pbatch;

    private final TextButton confirm;

    private Label lExp;
    private Label lWordExp;

    private float dura = 0;

    private int expAdd;

    private float precentStart = 0;
    private float precentEnd = 0;

    private boolean stop = false;
    private boolean sleep = false;
    private boolean nextLevelPrecent = true;

    private int one;
    private int two;
    private int expActual;
    private int expMax;

    static {
        style.font = font;
        textStyle.font = font;
        textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("itemButton.png"))));
    }

    public FightLose(final Game game, final Hero hero, float dropProcent, int dmgAverrage, float target, final int moneyDrop, final int expAdd, final String dropItem) {
        super(game);
        this.hero = hero;
        pbatch = new PolygonSpriteBatch();
        this.expAdd = expAdd;
        prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        expActual = hero.getExp();
        this.expAdd = expAdd;
        expMax = ExperienceRequired.getMaxExperience(hero.getLevel());

        Image background = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        Image barGold = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image barStats = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image iconMoney = new Image(new Texture(Gdx.files.internal("uiMoney.png")));

        confirm = new TextButton("Pomin", textStyle);

        Label.LabelStyle styleRed = new Label.LabelStyle();
        styleRed.font = font;
        styleRed.fontColor = new Color(Color.RED);

        Label lExpText = new Label("Zdobyte doswiadczenie", style);
        lExp = new Label(String.valueOf(expAdd), style);
        lWordExp = new Label("exp", style);
        Label lMoney = new Label("Zloto: +" + moneyDrop, style);
        Label lDrop = new Label("Szansa na drop: " + dropProcent + "%", style);
        Label lStatsDmgAverrage = new Label("Srednie zadane obrazenia: " + dmgAverrage, style);
        Label lStatsCelnosc = new Label("Srednia target atakow: " + target + "%", style);

        float lenghtText = 90 / lExp.getWidth() * 0.75f;

        lExpText.setFontScale(1.8f);
        lExp.setFontScale(lenghtText);
        lWordExp.setFontScale(2f);
        lMoney.setFontScale(1.5f);

        lExpText.setPosition(BaseScreen.VIEW_WIDTH / 2 - lExpText.getWidth() / 1.14f, 450);
        lExp.setPosition(BaseScreen.VIEW_WIDTH / 2 - lExp.getWidth() * (lenghtText / 2), BaseScreen.VIEW_HEIGHT * 0.75f);
        lWordExp.setPosition(BaseScreen.VIEW_WIDTH / 2 - lExp.getWidth() / 2 - 5, BaseScreen.VIEW_HEIGHT * 0.69f);
        lMoney.setPosition(BaseScreen.VIEW_WIDTH / 2 - lMoney.getWidth() / 2 - iconMoney.getWidth() / 1.5f - 5, 245);
        lDrop.setPosition(BaseScreen.VIEW_WIDTH / 2 - lDrop.getWidth() / 2, 215);
        lStatsDmgAverrage.setPosition(BaseScreen.VIEW_WIDTH / 2 - lStatsDmgAverrage.getWidth() / 2, 84);
        lStatsCelnosc.setPosition(BaseScreen.VIEW_WIDTH / 2 - lStatsCelnosc.getWidth() / 2, 63);

        background.setPosition(0, 0);
        barGold.setBounds(lMoney.getX() - 6, lMoney.getY() - 10, lMoney.getWidth() * 1.5f + 15, iconMoney.getHeight());
        barStats.setBounds(lStatsCelnosc.getX() - 15, 48, lStatsCelnosc.getWidth() + 30, 68);
        iconMoney.setPosition(lMoney.getX() + lMoney.getWidth() * 1.5f + 6, 238);


        addActors(background, barGold, barStats, iconMoney, lExpText, lMoney, lDrop, lStatsDmgAverrage, lStatsCelnosc, confirm);

        confirm.setSize(BaseScreen.VIEW_WIDTH, 50);
        confirm.setPosition(BaseScreen.VIEW_WIDTH / 2 - confirm.getWidth() / 2, 0);
        confirm.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                precentStart = (float) expActual / expMax * 100;

                int finalExp = expActual + expAdd;
                System.out.println(finalExp);

                if (finalExp > 0)
                    hero.setExp(finalExp);
                else
                    hero.setExp(0);

                hero.setMoney(hero.getMoney() + moneyDrop);
                prefStats.putInteger("MONEY", hero.getMoney());
                prefStats.putInteger("EXP", hero.getExp());
                prefStats.flush();

                game.setScreen(new Map_01(game));
                return false;
            }
        });

        create();
    }

    @Override
    public void create() {
        Image emptyCircleProgressBar = new Image(new Texture(Gdx.files.internal("circleExp/circleProgresBarExp.png")));

        //Android
        if (Gdx.app.getType() != Application.ApplicationType.Desktop) {
            int width = Gdx.app.getGraphics().getWidth();
            int height = Gdx.app.getGraphics().getHeight();

            float screenX = 100 / ((float) width / BaseMap.VIEW_WIDTH) / 100;
            float screenY = 100 / ((float) height / BaseMap.VIEW_HEIGHT) / 100;

            int minValue;
            if (width < height)
                minValue = width;
            else
                minValue = height;

            TextureRegion texture;

            if (minValue > 0 && minValue <= 270)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp240.png")));
            else if (minValue > 270 && minValue <= 400)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp320.png")));
            else if (minValue > 400 && minValue <= 510)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp480.png")));
            else if (minValue > 510 && minValue <= 600)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp540.png")));
            else if (minValue > 600 && minValue <= 750)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp720.png")));//TODO optimalize choose 720 or 768 and save to two resolution screen
            else if (minValue > 750 && minValue <= 790)                       //TODO reschear all image and look at resolution x,y sometimes two images have the same resolution
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp768.png")));
            else if (minValue > 790 && minValue <= 950)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp800.png")));
            else if (minValue > 950 && minValue <= 1150)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp1080.png")));
            else if (minValue > 1150 && minValue <= 1320)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp1200.png")));
            else if (minValue > 1320 && minValue <= 1500)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp1440.png")));
            else if (minValue > 1500 && minValue <= 1560)
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp1536.png")));
            else
                texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp2560.png")));

            emptyCircleProgressBar.setSize(screenX * texture.getRegionWidth(), screenY * texture.getRegionHeight());
            emptyCircleProgressBar.setPosition(BaseScreen.VIEW_WIDTH / 2 - screenX * texture.getRegionWidth() / 2, BaseScreen.VIEW_HEIGHT * 0.583f);

            sprite = new ProgressCircle(texture, pbatch);
            sprite.setPosition(width / 2 - sprite.getWidth() / 2, height * 0.583f);
        }
        //Desktop and others
        else {
            int width = Gdx.app.getGraphics().getWidth();
            int height = Gdx.app.getGraphics().getHeight();

            float screenX = 100 / ((float) width / BaseMap.VIEW_WIDTH) / 100;
            float screenY = 100 / ((float) height / BaseMap.VIEW_HEIGHT) / 100;

            TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("circleExp/circleExp320.png")));
            sprite = new ProgressCircle(texture, pbatch);
            sprite.setPosition(screenX * width / 2 - sprite.getWidth() / 2, screenY * height * 0.583f);
            emptyCircleProgressBar.setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        }
        addActors(emptyCircleProgressBar, lExp, lWordExp, sprite);

        precentStart = ((float) expActual / expMax * 100);
        precentEnd = ((float) expActual + expAdd) / expMax * 100;

        if(expActual > 0)
            sprite.setPercentage(precentStart);
        else {
            stop = true;
            sprite.setPercentage(0.5f);
            confirm.setText("OK");
        }
    }

    @Override
    public void update(float dt) {
        if (!sleep) {
            sprite.addAction(Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    } finally {
                        sleep = true;
                    }
                }
            })));
        }
        if (!stop) {
            dura += dt;
            if (dura <= 20) {
                float percent = (precentStart - dura * 100 / 20) +5;

                if(percent <= 0.5) {
                    sprite.setPercentage(0.5f);
                    confirm.setText("OK");
                    stop = true;
                }
                else {
                    if(percent <= precentStart && percent >= precentEnd)
                        sprite.setPercentage(percent);
                    else if(percent < precentEnd) {
                        confirm.setText("OK");
                        stop = true;
                    }
                }
            } else {
                dura = 0;
            }
        }
    }

    private void addActors(Actor... actors) {
        for (Actor actor : actors)
            stage.addActor(actor);
    }
}
