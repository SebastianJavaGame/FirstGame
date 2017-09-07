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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Bag;
import com.mygdx.game.Compass;
import com.mygdx.game.Enemy;
import com.mygdx.game.Equipment;
import com.mygdx.game.ExperienceRequired;
import com.mygdx.game.Hero;
import com.mygdx.game.LoadAllItemToGame;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.ProgressCircle;
import com.mygdx.game.Quest;
import com.mygdx.game.StatsHero;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-07-11.
 */

public class FightWin extends BaseScreen {
    private static final BitmapFont font = MyGdxGame.createDistanceFont();
    private static final Label.LabelStyle style = new Label.LabelStyle();
    private static final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();

    private Enemy enemy;
    final private Preferences prefItem;
    final private Preferences prefStats;

    private ProgressCircle sprite;
    private PolygonSpriteBatch pbatch;

    private final TextButton confirm;

    private Image itemImage = null;
    private Image iconHideItemDrop;
    private Label lEmpty;
    private Label lFull;
    private Label lDropChar;
    private Label lExp;
    private Label lWordExp;

    private float dura = 0;
    private float duration;

    private int expAdd;

    private int circleComplete = 115;

    private float precentStart = 0;
    private float precentEnd = 0;
    private float dropProcent;

    private boolean stop = false;
    private boolean sleep = false;
    private boolean nextLevelPrecent = true;
    private boolean precentActual = false;
    private boolean upperTwo = false;

    private int one;
    private int two;
    private int oneBase;
    private int twoBase;
    private int expActual;
    private int expMax;
    private int iteration = 0;
    private int allExp;

    static {
        style.font = font;
        textStyle.font = font;
        textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbort.png"))));
    }

    public FightWin(final Game game, final Hero hero, final Enemy enemy, int dmgAverrage, float target, final int moneyDrop, final int expAdd, final String dropItem) {
        super(game);
        pbatch = new PolygonSpriteBatch();
        this.expAdd = expAdd;
        prefItem = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);
        prefStats = Gdx.app.getPreferences(StatsHero.PREF_NAME_STATS);
        one = hero.getLevel() - 1;
        oneBase = hero.getLevel() - 1;
        two = hero.getLevel();
        twoBase = hero.getLevel();
        expActual = hero.getExp();
        expMax = ExperienceRequired.getMaxExperience(two);
        allExp = expAdd;
        dropProcent = enemy.getRandomDrop();
        this.enemy = enemy;

        final Enemy oryginalEnemy = new Enemy(enemy.getTexturePath(), enemy.getHeadPath(), enemy.getWaponPath(), enemy.getAttackType(), enemy.getName(), enemy.getLevel(),
                enemy.getHp(), enemy.getStrong(), enemy.getWiedza(), enemy.getArmor(), enemy.getDefensePhysics(), enemy.getDefenseMagic(), enemy.getRandomDrop(),
                enemy.getExpDrop(), enemy.getMoneyDrop(), enemy.getSpawnSecond());
        final ArrayList<String>  itemList = enemy.getDropItem();
        final Vector2 orginalPosition = new Vector2(enemy.getX(), enemy.getY());
        final Rectangle temporaryRectangleCollision = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());

        Image background = new Image(new Texture(Gdx.files.internal("statsBackground.png")));
        Image barGold = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image barStats = new Image(new Texture(Gdx.files.internal("barX.png")));
        Image iconMoney = new Image(new Texture(Gdx.files.internal("uiMoney.png")));
        Image emptySlotItem = new Image(new Texture(Gdx.files.internal("slot.png")));
        iconHideItemDrop = new Image(new Texture(Gdx.files.internal("circleExp/lottery.png")));

        confirm = new TextButton("Pomin", textStyle);

        if (!dropItem.equals("")) {
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
        lExp = new Label("+" + expAdd, style);
        lWordExp = new Label("exp", style);
        Label lMoney = new Label("Zloto: +" + moneyDrop, style);
        Label lDrop = new Label("Szansa na drop: " + dropProcent + "%", style);
        lDropChar = new Label("?", style);
        Label lStatsDmgAverrage = new Label("Srednie zadane obrazenia: " + dmgAverrage, style);
        Label lStatsCelnosc = new Label("Srednia celnosc atakow: " + String.format("%, .1f", target) + "%", style);
        lFull = new Label(" Plecak\njest pelny", styleRed);

        float lenghtText = 90 / lExp.getWidth() * 0.75f;
        lMoney.setColor(Color.GOLD);

        lExpText.setFontScale(0.9f);
        lExp.setFontScale(lenghtText);
        lWordExp.setFontScale(1);
        lMoney.setFontScale(0.8f);
        lDrop.setFontScale(0.6f);
        lDropChar.setFontScale(1);
        lStatsCelnosc.setFontScale(0.5f);
        lStatsDmgAverrage.setFontScale(0.5f);
        lFull.setFontScale(0.4f);

        lExpText.setPosition(BaseScreen.VIEW_WIDTH / 2 - lExpText.getWidth()*0.9f /2, 440);
        lExp.setPosition(BaseScreen.VIEW_WIDTH / 2 - lExp.getWidth() *lenghtText / 2, BaseScreen.VIEW_HEIGHT *0.74f);
        lWordExp.setPosition(BaseScreen.VIEW_WIDTH / 2 - lWordExp.getWidth() /2, BaseScreen.VIEW_HEIGHT *0.67f);
        lMoney.setPosition(BaseScreen.VIEW_WIDTH / 2 - lMoney.getWidth()*0.8f /2, 238);
        lDrop.setPosition(BaseScreen.VIEW_WIDTH / 2 - lDrop.getWidth()*0.6f /2, 207);
        lStatsDmgAverrage.setPosition(BaseScreen.VIEW_WIDTH / 2 - lStatsDmgAverrage.getWidth()*0.5f /2, 69);
        lStatsCelnosc.setPosition(BaseScreen.VIEW_WIDTH / 2 - lStatsCelnosc.getWidth()*0.5f /2, 48);

        background.setPosition(0, 0);
        barGold.setBounds(lMoney.getX() -5, lMoney.getY()+4, lMoney.getWidth()*0.8f +10, iconMoney.getHeight());
        barStats.setBounds(lStatsCelnosc.getX() - 15, 48, lStatsCelnosc.getWidth()*0.5f + 30, 70);
        iconMoney.setPosition(lMoney.getX() + lMoney.getWidth()*0.8f +10, 240);
        emptySlotItem.setSize(iconHideItemDrop.getWidth() + 10, iconHideItemDrop.getHeight() + 10);
        emptySlotItem.setPosition(BaseScreen.VIEW_WIDTH /2 - emptySlotItem.getWidth() /2, 110);
        iconHideItemDrop.setSize(85, 85);
        iconHideItemDrop.setPosition(BaseScreen.VIEW_WIDTH / 2 - iconHideItemDrop.getWidth() / 2, 123);
        iconHideItemDrop.setOrigin(iconHideItemDrop.getWidth() / 2, iconHideItemDrop.getHeight() / 2);

        lDropChar.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - lDropChar.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - lDropChar.getHeight() / 2);

        for (int i = 0; i < 18; i++) {
            String value = prefItem.getString("SLOT" + i, "");

            if (!value.equals("")) {
                iteration++;

                if (iteration == 18) {
                    lFull.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - lFull.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - lFull.getHeight() / 2);

                    addActors(background, barGold, barStats, iconMoney, emptySlotItem, iconHideItemDrop, lExpText,
                            lMoney, lDrop, lDropChar, lFull, lStatsDmgAverrage, lStatsCelnosc, confirm);
                    break;
                }
            }
        }
        if (iteration != 18) {
            if (itemImage != null) {
                itemImage.setSize(80, 80);
                itemImage.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - itemImage.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - itemImage.getHeight() / 2);

                addActors(background, barGold, barStats, iconMoney, emptySlotItem, iconHideItemDrop, lExpText,
                        lMoney, lDrop, lDropChar, itemImage, lStatsDmgAverrage, lStatsCelnosc, confirm);
                BaseMap.addRedLight();
                Bag.addRedLight(0);
            } else {

                lEmpty = new Label("Pusto", styleRed);
                lEmpty.setPosition(emptySlotItem.getX() + emptySlotItem.getWidth() / 2 - lEmpty.getWidth() / 2, emptySlotItem.getY() + emptySlotItem.getHeight() / 2 - lEmpty.getHeight() / 2);

                addActors(background, barGold, barStats, iconMoney, emptySlotItem, iconHideItemDrop, lExpText,
                        lMoney, lDrop, lDropChar, lEmpty, lStatsDmgAverrage, lStatsCelnosc, confirm);
            }
        }

        confirm.setSize(BaseScreen.VIEW_WIDTH, 50);
        confirm.setPosition(BaseScreen.VIEW_WIDTH / 2 - confirm.getWidth() / 2, 0);
        confirm.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, final int pointer, int button) {
                Quest.checkKillTargetWithTask(enemy.getName().toLowerCase());
                Menu.setIsFirstSpawnHeroPosition(true);
                Menu.getSoundClick().play();

                if(oryginalEnemy.getSpawnSecond() > 0) {
                    oryginalEnemy.setRectangle(5, 5, -10, -10);
                    BaseMap.getActualMap().getCharacter().add(oryginalEnemy);

                    Timer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            if (!hero.calculateCollisionTwoRectangle(new Rectangle(getPosX(), getPosY(), hero.getHeroBox().getWidth(), hero.getHeroBox().getHeight()), temporaryRectangleCollision)) {
                                oryginalEnemy.setPosition(orginalPosition.x, orginalPosition.y);
                                oryginalEnemy.collisionUpdate();
                                oryginalEnemy.setDropItemName(itemList);
                                this.cancel();
                            }
                        }
                    }, oryginalEnemy.getSpawnSecond(), 2, 9999);
                }

                expMax = ExperienceRequired.getMaxExperience(twoBase);
                float resultPrecent;
                float resultActualExp = (float) (Hero.getExp() +allExp) / expMax * 100;
                precentStart = (float) expActual / expMax * 100;
                int heroLevel = hero.getLevel();

                for (int i = 0; i < 100; i++) {
                    oneBase++;
                    twoBase++;
                    if (resultActualExp < 100) {
                        if (heroLevel == hero.getLevel())
                            hero.setExp(hero.getExp() + allExp);
                        else
                            hero.setExp(allExp);

                        hero.setMoney(hero.getMoney() + moneyDrop);
                        prefStats.putInteger("MONEY", hero.getMoney()).flush();
                        prefStats.putInteger("LEVEL", hero.getLevel()).flush();
                        prefStats.putInteger("EXP", hero.getExp()).flush();

                        if (!dropItem.equals("")) {
                            for (int j = 0; j < 18; j++) {
                                String value = prefItem.getString("SLOT" + j, "");

                                if (value.equals("")) {
                                    addItemToBag(dropItem, j);
                                    break;
                                }
                            }
                        }
                        if(!BaseScreen.getException())
                            Menu.setMap();
                        break;
                    } else {
                        resultPrecent = 100 - precentStart;
                        allExp -= ExperienceRequired.getMaxExperience(oneBase) * (resultPrecent / 100);
                        expMax = ExperienceRequired.getMaxExperience(twoBase);
                        resultActualExp = (float) allExp / expMax * 100;
                        precentStart = 0;
                        Hero.levelUp();
                    }
                }
                return false;
            }
        });

        create();
    }

    @Override
    public void create(){
        BaseMap.getActualMap().getCharacter().remove(enemy);

        Image emptyCircleProgressBar = new Image(new Texture(Gdx.files.internal("circleExp/circleProgresBarExp.png")));

        Preferences preferences = Gdx.app.getPreferences(Compass.LIST);
        if(enemy.getName().equals("Maven"))
            preferences.putBoolean("BOSS_1", true).flush();
        if(enemy.getName().equals("Quaregis"))
            preferences.putBoolean("BOSS_2", true).flush();
        if(enemy.getName().equals("Vedvarr"))
            preferences.putBoolean("BOSS_3", true).flush();
        if(enemy.getName().equals("Nathagan"))
            preferences.putBoolean("BOSS_4", true).flush();
        if(enemy.getName().equals("Valmorg"))
            preferences.putBoolean("BOSS_5", true).flush();

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

            TextureRegion texture = loadImage(minValue);

            emptyCircleProgressBar.setSize(screenX * texture.getRegionWidth(), screenY * texture.getRegionHeight());
            emptyCircleProgressBar.setPosition(BaseScreen.VIEW_WIDTH / 2 - screenX * texture.getRegionWidth() / 2, BaseScreen.VIEW_HEIGHT * 0.583f);

            sprite = new ProgressCircle(texture, pbatch);
            sprite.setPosition(width / 2 - sprite.getWidth() / 2, height * 0.583f);
        } else {  //Desktop and others
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

        precentStart = (float) expActual / expMax * 100;
        sprite.setPercentage(precentStart);

        precentEnd = ((float) expActual + expAdd) / expMax * 100;
        duration = 3;

        lFull.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration - 2), Actions.fadeIn(2)));

        if (itemImage != null) {
            itemImage.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration), Actions.fadeIn(3)));
            iconHideItemDrop.addAction(Actions.parallel(Actions.forever(Actions.rotateTo(720, duration)), Actions.sequence(Actions.delay(duration - 2), Actions.fadeOut(2))));
            lDropChar.addAction(Actions.sequence(Actions.delay(duration - 2), Actions.fadeOut(3)));
        } else {
            if (iteration != 18)
                lEmpty.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(duration), Actions.fadeIn(3)));
            iconHideItemDrop.addAction(Actions.parallel(Actions.forever(Actions.rotateTo(720, duration)), Actions.sequence(Actions.delay(duration - 2), Actions.fadeOut(2))));
            lDropChar.addAction(Actions.sequence(Actions.delay(duration - 2), Actions.fadeOut(3)));
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
                float percent = precentStart + dura * 100 / 20;

                if (percent > 2 && percent < 101)
                    upperTwo = true;
                else
                    upperTwo = false;

                if (percent >= circleComplete && nextLevelPrecent && !upperTwo) {
                    Label.LabelStyle styleGreen = new Label.LabelStyle();
                    styleGreen.font = font;
                    styleGreen.fontColor = new Color(Color.GREEN);

                    final Label label = new Label("AWANSUJESZ!", styleGreen);
                    Hero.playSoundLvlUp();
                    label.setPosition(BaseScreen.VIEW_WIDTH /2 -label.getWidth() /2, BaseScreen.VIEW_HEIGHT *0.80f);

                    label.addAction(Actions.sequence(Actions.fadeOut(0), Actions.parallel(Actions.fadeIn(1)),
                            Actions.parallel(Actions.delay(1), Actions.moveBy(0, 20, 1)), Actions.parallel(Actions.moveBy(0, 30, 1.5f), Actions.fadeOut(1.5f))));
                    stage.addActor(label);

                    one++;
                    two++;

                    float resultPrecent = 100 - precentStart;
                    expAdd -= ExperienceRequired.getMaxExperience(one) * (resultPrecent / 100);
                    expMax = ExperienceRequired.getMaxExperience(two);
                    float resultActualExp = (float) expAdd / expMax * 100;

                    precentStart = 0;
                    dura = 0;
                    precentEnd = resultActualExp;
                    circleComplete = -1;

                    nextLevelPrecent = false;
                    precentActual = true;
                }
                if (percent >= 3 && percent <= 5)
                    nextLevelPrecent = true;

                percent = precentStart + dura * 100 / 20;
                if (!precentActual)
                    percent -= 15;

                if (precentEnd < 1) {
                    sprite.setPercentage(percent);
                    precentEnd = 1;
                } else {
                    if (percent <= precentEnd && percent >= precentStart) {
                        sprite.setPercentage(percent);
                    } else if (percent > precentEnd) {
                        confirm.setText("OK");
                        stop = true;
                    }
                }
            } else {
                dura = 0;
            }
        }
    }

    private void addItemToBag(String item, int slot) {
        try {
            int slotNr = 0;
            for (int i = 2; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (slotNr == slot) {
                        //TODO add in menu screen seting and set true/false this bit code, becouse you show testers how look error
                    Equipment.slotEmpty = new boolean[18];
                    for (int k = 0; k < 18; k++) {
                        if (!prefItem.getString("SLOT" + k, "").equals(""))
                            Equipment.slotEmpty[k] = true;
                        else
                            Equipment.slotEmpty[k] = false;
                    }
                    //TODO #end
                        Equipment.slotEmpty[slotNr] = true;
                        prefItem.putString("SLOT" + slotNr, item);
                        prefItem.flush();
                        return;
                    }
                    slotNr++;
                }
            }
        }catch (Exception e) {
            BaseScreen.showException(e);
        }
    }

    private TextureRegion loadImage(int minValue){
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

        return texture;
    }

    private void addActors(Actor... actors) {
        for (Actor actor : actors)
            stage.addActor(actor);
    }
}
