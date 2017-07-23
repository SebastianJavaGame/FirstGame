package Screen;

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
import com.mygdx.game.Equipment;
import com.mygdx.game.ExperienceRequired;
import com.mygdx.game.Hero;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.ProgressCircle;
import com.mygdx.game.StatsHero;

/**
 * Created by Sebastian on 2017-07-11.
 */

public class FightWin extends BaseScreen {
    private static final BitmapFont font = new BitmapFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private static final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();

    private Hero hero;
    final private Preferences prefItem;
    final private Preferences prefStats;

    private ProgressCircle sprite;
    private PolygonSpriteBatch pbatch;

    private Image itemImage = null;
    private Image iconHideItemDrop;
    private Label lEmpty;
    private Label lFull;
    private Label lDropChar;

    private float dura = 0;
    private float duration;

    private int expAdd;

    private int circleComplete = 115;

    private float precentStart = 0;
    private float precentEnd = 0;

    private boolean stop = false;
    private boolean sleep = false;
    private boolean nextLevelPrecent = true;
    private boolean precentActual = false;
    private boolean upperTwo = false;

    private int one;
    private int two;
    private int expActual;
    private int expMax;

    static {
        style.font = font;
        textStyle.font = font;
        textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbort.png"))));
    }

    public FightWin(final Game game, final Hero hero, float dropProcent, int dmgAverrage, float target, final int moneyDrop, final int expAdd, final String dropItem){
        super(game);
        this.hero = hero;
        pbatch = new PolygonSpriteBatch();
        this.expAdd = expAdd;
        prefItem = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
        prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        one = hero.getLevel() -1;
        two = hero.getLevel();
        expActual = hero.getExp();
        expMax = ExperienceRequired.getMaxExperience(two);

        Image background = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        Image barGold = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image barStats = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image emptyCircleProgressBar = new Image(new Texture(Gdx.files.internal("circleProgresBarExp.png")));
        Image iconMoney = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
        Image emptySlotItem = new Image(new Texture(Gdx.files.internal("slot.png")));
        iconHideItemDrop = new Image(new Texture(Gdx.files.internal("lottery.png")));

        TextButton confirm = new TextButton("OK", textStyle);

        if(!dropItem.equals("")){
            try {
                itemImage = LoadAllItemToGame.getItem(dropItem).getImage();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        Label.LabelStyle styleRed = new Label.LabelStyle();
        styleRed.font = font;
        styleRed.fontColor = new Color(Color.RED);

        Label lExpText = new Label("Zdobyte doswiadczenie", style);
        Label lExp = new Label("+" + expAdd, style);
        Label lWordExp = new Label("exp", style);
        Label lMoney = new Label("Zloto: +" + moneyDrop, style);
        Label lDrop = new Label("Szansa na drop: " + dropProcent + "%", style);
        lDropChar = new Label("?", style);
        Label lStatsDmgAverrage = new Label("Srednie zadane obrazenia: " + dmgAverrage, style);
        Label lStatsCelnosc = new Label("Srednia target atakow: " + target + "%", style);

        float lenghtText = 90 /lExp.getWidth() *0.75f;

        lExpText.setFontScale(1.8f);
        lExp.setFontScale(lenghtText);
        lWordExp.setFontScale(2f);
        lMoney.setFontScale(1.5f);

        lExpText.setPosition(BaseScreen.VIEW_WIDTH /2 - lExpText.getWidth() /1.14f, 450);
        lExp.setPosition(BaseScreen.VIEW_WIDTH /2 - lExp.getWidth() *(lenghtText /2), 360);
        lWordExp.setPosition(BaseScreen.VIEW_WIDTH /2 - lExp.getWidth() /2 -5, 330);
        lMoney.setPosition(BaseScreen.VIEW_WIDTH /2 - lMoney.getWidth() /2 - iconMoney.getWidth() /1.5f -5, 245);
        lDrop.setPosition(BaseScreen.VIEW_WIDTH /2 - lDrop.getWidth() /2, 215);
        lStatsDmgAverrage.setPosition(BaseScreen.VIEW_WIDTH /2 - lStatsDmgAverrage.getWidth() /2, 84);
        lStatsCelnosc.setPosition(BaseScreen.VIEW_WIDTH /2 - lStatsCelnosc.getWidth() /2, 63);

        background.setPosition(0, 0);
        barGold.setBounds(lMoney.getX() -6, lMoney.getY() -10, lMoney.getWidth() *1.5f +15, iconMoney.getHeight());
        barStats.setBounds(lStatsCelnosc.getX() -15, 48, lStatsCelnosc.getWidth() +30, 68);
        emptyCircleProgressBar.setPosition(BaseScreen.VIEW_WIDTH /2 - emptyCircleProgressBar.getWidth() /2, 280);
        iconMoney.setPosition(lMoney.getX() + lMoney.getWidth() *1.5f +6, 238);
        emptySlotItem.setSize(iconHideItemDrop.getWidth() +10, iconHideItemDrop.getHeight() +10);
        emptySlotItem.setPosition(BaseScreen.VIEW_WIDTH /2 - emptySlotItem.getWidth() /2, 110);
        iconHideItemDrop.setSize(85, 85);
        iconHideItemDrop.setPosition(BaseScreen.VIEW_WIDTH /2 - iconHideItemDrop.getWidth() /2, 123);
        iconHideItemDrop.setOrigin(iconHideItemDrop.getWidth() /2, iconHideItemDrop.getHeight() /2);

        lDropChar.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() /2 -lDropChar.getWidth() /2, emptySlotItem.getY() +emptySlotItem.getHeight() /2 -lDropChar.getHeight() /2);

        confirm.setSize(BaseScreen.VIEW_WIDTH, 50);
        confirm.setPosition(BaseScreen.VIEW_WIDTH /2 - confirm.getWidth() /2, 0);
        confirm.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                hero.setMoney(hero.getMoney() +moneyDrop);
                prefStats.putInteger("MONEY", hero.getMoney());
                prefStats.putInteger("LEVEL", hero.getLevel());
                prefStats.putInteger("EXP", hero.getExp());
                prefStats.flush();

                if(!dropItem.equals("")) {
                    for (int i = 0; i < 18; i++) {
                        String value = prefItem.getString("SLOT" + i, "");

                        if (value.equals("")) {
                            addItemToBag(dropItem, i);
                            break;
                        }
                    }
                }

                game.setScreen(new Map_01(game));
                return false;
            }
        });

        int iteration = 0;
        for (int i = 0; i < 18; i++) {
            String value = prefItem.getString("SLOT" + i, "");

            if (!value.equals("")) {
                iteration++;

                if(iteration == 18) {
                    lFull = new Label("Plecak jest\n   pelny", styleRed);
                    lFull.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - lFull.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - lFull.getHeight() / 2);

                    addActors(background, barGold, barStats, emptyCircleProgressBar, iconMoney, emptySlotItem, iconHideItemDrop, lExpText, lExp,
                            lWordExp, lMoney, lDrop, lDropChar, lFull, lStatsDmgAverrage, lStatsCelnosc, confirm);
                    break;
                }
            }
        }
        if (iteration != 18) {
            if (itemImage != null) {
                itemImage.setSize(80, 80);
                itemImage.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - itemImage.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - itemImage.getHeight() / 2);

                addActors(background, barGold, barStats, emptyCircleProgressBar, iconMoney, emptySlotItem, iconHideItemDrop, lExpText, lExp,
                        lWordExp, lMoney, lDrop, lDropChar, itemImage, lStatsDmgAverrage, lStatsCelnosc, confirm);
            } else {

                lEmpty = new Label("Pusto", styleRed);
                lEmpty.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - lEmpty.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - lEmpty.getHeight() / 2);

                addActors(background, barGold, barStats, emptyCircleProgressBar, iconMoney, emptySlotItem, iconHideItemDrop, lExpText, lExp,
                        lWordExp, lMoney, lDrop, lDropChar, lEmpty, lStatsDmgAverrage, lStatsCelnosc, confirm);
            }
        }

        create();
    }

    @Override
    public void create() {
        sprite = new ProgressCircle(new TextureRegion(new Texture(Gdx.files.internal("circleExp.png"))), pbatch);
        sprite.setSize(sprite.getWidth() + 6, sprite.getHeight() +10);
        sprite.setPosition(BaseScreen.VIEW_WIDTH /2 - sprite.getWidth() /2 +3, 280);
        stage.addActor(sprite);

        precentStart = (float)expActual /expMax *100;
        sprite.setPercentage(precentStart);

        precentEnd = ((float)expActual +expAdd) /expMax *100;
        duration = 7;

        System.out.println(duration);
        System.out.println(precentEnd);
        System.out.println(precentStart);

        lFull.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration -2), Actions.fadeIn(2)));

        if(itemImage != null) {
            itemImage.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration), Actions.fadeIn(3)));
            iconHideItemDrop.addAction(Actions.parallel(Actions.forever(Actions.rotateTo(720, duration)), Actions.sequence(Actions.delay(duration -2), Actions.fadeOut(2))));
            lDropChar.addAction(Actions.sequence(Actions.delay(duration -2), Actions.fadeOut(3)));
        }
        else{
            lEmpty.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration), Actions.fadeIn(3)));
            iconHideItemDrop.addAction(Actions.parallel(Actions.forever(Actions.rotateTo(720, duration)), Actions.sequence(Actions.delay(duration -2), Actions.fadeOut(2))));
            lDropChar.addAction(Actions.sequence(Actions.delay(duration -2), Actions.fadeOut(3)));
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
        if(!stop) {
            dura += dt;
            if (dura <= 20) {
                float percent = precentStart + dura * 100 / 20;

                if(percent > 2 && percent < 101)
                    upperTwo = true;
                else
                    upperTwo = false;

                if(percent >= circleComplete && nextLevelPrecent && !upperTwo) {
                    System.out.println("LEVEL UP!!!"); //TODO communicate about level up
                    hero.setLevel(hero.getLevel() +1);
                    hero.setMaxExp(ExperienceRequired.getMaxExperience(hero.getLevel()));

                    one++; two++;
                    float resultPrecent = 100 -precentStart;
                    expAdd -= ExperienceRequired.getMaxExperience(one) * (resultPrecent /100);
                    expMax = ExperienceRequired.getMaxExperience(two);
                    float resultActualExp = (float)expAdd /expMax *100;

                    hero.setExp(expAdd);

                    precentStart = 0;
                    dura = 0;
                    precentEnd = resultActualExp;
                    circleComplete = -1;

                    nextLevelPrecent = false;
                    precentActual = true;
                }
                if(percent >= 3 && percent <= 5)
                    nextLevelPrecent = true;

                percent = precentStart + dura * 100 / 20;
                if(!precentActual)
                    percent -= 15;

                if(precentEnd < 1) {
                    sprite.setPercentage(percent);
                    precentEnd = 1;
                }else {
                    if (percent <= precentEnd && percent >= precentStart) {
                        sprite.setPercentage(percent);
                    } else if (percent > precentEnd)
                        stop = true;
                }
            } else {
                dura = 0;
            }
        }
    }

    private void addItemToBag(String item, int slot) {
        int slotNr = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if(slotNr == slot){
                    Equipment.slotEmpty[slotNr] = true;
                    prefItem.putString("SLOT" + slotNr, item);
                    prefItem.flush();
                    return;
                }
                slotNr++;
            }
        }
    }

    private void addActors(Actor... actors){
        for(Actor actor: actors)
            stage.addActor(actor);
    }
}