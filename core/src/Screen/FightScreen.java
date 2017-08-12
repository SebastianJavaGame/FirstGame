package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Asset;
import com.mygdx.game.BaseEnemyAI;
import com.mygdx.game.Enemy;
import com.mygdx.game.Equipment;
import com.mygdx.game.Hero;
import com.mygdx.game.MyException;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-07-09.
 */

public class FightScreen extends BaseScreen {
    private Asset asset = new Asset();
    private static final int ENERGY_MAX = 100;
    private static final float FONT_SCALE_HP = 2;
    private static final float FONT_SCALE_PROCENT = 1.7f;

    private static final int[] DURATION_ANIMATION = new int[]{1, 4, 7, 10, 13, 16};
    private static final int[] SUPER_ATTACK = new int[]{5, 0, 5, 0};

    private static Hero hero;
    private static Enemy enemy;

    private static Image heroImage;
    public static Image enemyImage;
    private static Image waponHero;
    private static Image waponEnemy;
    private static Image magicHero;
    private static Image magicEnemy;
    private static Image block;
    private static Image blood;

    private static float targetX;

    private static Image backgroundFight;
    private static ImageButton[] plusButton;
    private static ImageButton[] minusButton;
    private static ImageButton abortNonActive;
    private static ImageButton abortActive;
    private static TextButton startFight;
    private static TextButton buttonAbort;

    private static Image barHpHero;
    private static Image barHpEnemy;
    private static Image barEnergyHero;
    private static Image barEnergyEnemy;

    private static Label[] labelPointFight;
    private static int[] pointUserPref;
    private static int freePointFight;
    private static int pointFightEnemy;

    private static int energyMaxHero;
    private static int energyMaxEnemy;
    private static int hpMaxHero;
    private static int hpMaxEnemy;
    private static int energyHero;
    private static int energyEnemy;
    private static int hpHero;
    private static int hpEnemy;

    private static Label lHpHero;
    private static Label lHpEnemy;
    private static Label lEnergyHero;
    private static Label lEnergyEnemy;
    private static Label labelRoundNumber;
    private static Label[] labelCalculateHp;
    private static Label[] labelCalculateProcent;
    private static Label labelFreePoint;

    private static int enemyAiStats[];

    public static boolean flip;
    private static boolean abort;
    private static boolean abortFirstTap;
    private static boolean animationPlay;
    private static boolean stopAnimationRoundAttack = false;

    private static ArrayList<Integer> avergePercentsFight;
    private static ArrayList<Integer> avergeDmgFight;

    BitmapFont font = new BitmapFont();
    Label.LabelStyle style = new Label.LabelStyle();
    Label.LabelStyle styleBlood = new Label.LabelStyle();

    private Music musicBattle;
    private Sound hitBlock;
    private Sound hitPositive;
    private Sound die;

    public FightScreen(Game g, Hero hero, Enemy enemy, boolean flip) {
        super(g);
        this.hero = hero;
        this.enemy = enemy;
        plusButton = new ImageButton[4];
        minusButton = new ImageButton[4];
        labelPointFight = new Label[4];
        pointUserPref = new int[4];
        enemyAiStats = new int[4];
        style.font = font;
        styleBlood.font = font;
        styleBlood.fontColor = new Color(Color.FIREBRICK);
        this.flip = flip;
        abort = true;
        abortFirstTap = true;
        avergePercentsFight = new ArrayList<Integer>();
        avergeDmgFight = new ArrayList<Integer>();
        labelCalculateProcent = new Label[6];
        labelCalculateHp = new Label[6];
        create();
    }

    @Override
    public void create() {
        Preferences preferences = Gdx.app.getPreferences(Equipment.PREF_NAME_FIGHT);
        hpHero = hero.getHp();
        hpEnemy = enemy.getHp();
        //hpHero = 0;
        hpEnemy = 10;
        hpMaxHero = hero.getFullHp();
        hpMaxEnemy = enemy.getHp();
        freePointFight = preferences.getInteger("FIGHT_POINT", 10);
        pointFightEnemy = 10;
        energyMaxEnemy = 100;
        energyMaxHero = 100;
        energyHero = energyMaxHero;
        energyEnemy = energyMaxEnemy;
        animationPlay = false;
        abort = true;

        pointUserPref[3] = preferences.getInteger("ATTACK_PHYSICS", 0);
        pointUserPref[2] = preferences.getInteger("DEFENSE_PHYSICS", 0);
        pointUserPref[1] = preferences.getInteger("ATTACK_MAGIC", 0);
        pointUserPref[0] = preferences.getInteger("DEFENSE_MAGIC", 0);

        asset.loadFightScreen();
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            musicBattle = asset.manager.get("sound/battle.ogg", Music.class);
            hitBlock = asset.manager.get("sound/blockAttack.ogg", Sound.class);
            hitPositive = asset.manager.get("sound/hitPositive.ogg", Sound.class);
            die = asset.manager.get("sound/herodie.ogg", Sound.class);
            backgroundFight = new Image(asset.manager.get("fight.png", Texture.class));
            barHpHero = new Image(asset.manager.get("barHpFight.png", Texture.class));
            barHpEnemy = new Image(asset.manager.get("barHpFight.png", Texture.class));
            barEnergyHero = new Image(asset.manager.get("barEnergyFight.png", Texture.class));
            barEnergyEnemy = new Image(asset.manager.get("barEnergyFight.png", Texture.class));
            heroImage = new Image(asset.manager.get("heroImage.png", Texture.class));

            if (flip) {
                enemy.getTexture().flip(true, false);
                enemyImage = new Image(enemy.getTexture());
            } else
                enemyImage = new Image(enemy.getTexture());

            try {
                waponHero = flipY();
                waponHero.setPosition(130, 280);
                waponHero.setSize(80, 80);
            } catch (CloneNotSupportedException e) {
            }

            heroImage.setBounds(25, 175, BaseScreen.VIEW_WIDTH / 2 - 50, 190);
            enemyImage.setSize(enemyImage.getWidth(), enemyImage.getHeight());
            targetX = (BaseScreen.VIEW_WIDTH / 2 - enemyImage.getWidth()) / 2 + BaseScreen.VIEW_WIDTH / 2;
            enemyImage.setPosition(targetX, 175);
            System.out.println(targetX + "target");
            waponEnemy = enemy.getWapon();
            waponEnemy.setPosition(95, 280);
            waponEnemy.setSize(80, 80);
            waponEnemy.setOrigin(waponEnemy.getWidth(), 0);

            magicHero = new Image(asset.manager.get("magicHero.png", Texture.class));
            magicHero.setBounds(targetX, enemyImage.getY() + enemyImage.getHeight() / 2, enemyImage.getWidth() - 30, enemyImage.getHeight() - 120);

            magicEnemy = new Image(asset.manager.get("magicEnemy.png", Texture.class));
            magicEnemy.setBounds(heroImage.getX() + 40, heroImage.getY() + 80, heroImage.getWidth() - 30, heroImage.getHeight() - 120);

            block = new Image(asset.manager.get("blockAttack.png", Texture.class));
            blood = new Image(asset.manager.get("blood.png", Texture.class));

            enemy.getHead().setPosition(270, 435);
            barHpHero.setBounds(53, 19, 120, 10);
            barHpEnemy.setBounds(267, 466, -120, 10);
            barEnergyHero.setBounds(53, 4, 120, 10);
            barEnergyEnemy.setBounds(267, 451, -120, 10);

            final float fontSize = 0.85f;

            lHpHero = new Label(hpHero + " / " + hpMaxHero, style);
            lHpEnemy = new Label(hpMaxEnemy + " / " + hpMaxEnemy, style);
            lEnergyHero = new Label(energyMaxHero + " / " + ENERGY_MAX, style);
            lEnergyEnemy = new Label(energyMaxEnemy + " / " + ENERGY_MAX, style);
            labelFreePoint = new Label("" + freePointFight, style);
            labelRoundNumber = new Label("1", style);
            Label labelName = new Label("Gondor", style);
            Label labelLvl = new Label("Level 35", style);

            lHpHero.setPosition(53 + barHpHero.getWidth() / 2 - (lHpHero.getWidth() * fontSize) / 2, 15);
            lHpEnemy.setPosition(267 + barHpEnemy.getWidth() / 2 - (lHpEnemy.getWidth() * fontSize) / 2, 461);
            lEnergyHero.setPosition(53 + barEnergyHero.getWidth() / 2 - (lEnergyHero.getWidth() * fontSize) / 2, 0);
            lEnergyEnemy.setPosition(267 + barEnergyEnemy.getWidth() / 2 - (lEnergyEnemy.getWidth() * fontSize) / 2, 446);
            labelName.setPosition((BaseScreen.VIEW_WIDTH - 190) / 2 - labelName.getWidth() / 2, 460);
            labelLvl.setPosition((BaseScreen.VIEW_WIDTH - 190) / 2 - labelLvl.getWidth() / 2 + 10, 445);
            labelFreePoint.setPosition(131, 105);
            labelRoundNumber.setPosition(287, 105);

            labelName.setFontScale(1.2f);
            lHpHero.setFontScale(fontSize);
            lHpEnemy.setFontScale(fontSize);
            lEnergyHero.setFontScale(fontSize);
            lEnergyEnemy.setFontScale(fontSize);

            abortNonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonAbord.png", Texture.class))));
            abortNonActive.setBounds(255, 0, 45, 45);
            abortNonActive.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.setIsFirstSpawnHeroPosition(true);
                    Menu.getSoundClick().play();
                    musicBattle.stop();

                    if (abort && !animationPlay) {
                        Label label = new Label("ABORT!", style);
                        label.setFontScale(3);
                        label.setPosition(85, 250);
                        stage.addActor(label);

                        Action action = Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                if (flip) {
                                    enemy.getTexture().flip(true, false);
                                    enemyImage = new Image(enemy.getTexture());
                                } else
                                    enemyImage = new Image(enemy.getTexture());

                                Menu.setMap();
                            }
                        });
                        stage.addAction(Actions.sequence(Actions.delay(1), action));
                    }
                    return false;
                }
            });

            abortActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonAbordActive.png", Texture.class))));
            abortActive.setBounds(255, 0, 45, 45);
            abortActive.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    if (abortFirstTap && !animationPlay) {
                        if (hpHero > hpMaxHero * 0.2f)
                            abortFirstTap = false;

                        final TextButton.TextButtonStyle textStyleAbort = new TextButton.TextButtonStyle();
                        textStyleAbort.font = font;
                        textStyleAbort.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("buttonAbord.png", Texture.class)));
                        buttonAbort = new TextButton("Abort!", textStyleAbort);
                        buttonAbort.setSize(150, 50);
                        buttonAbort.setPosition(BaseScreen.VIEW_WIDTH / 2 - startFight.getWidth() / 2, 180);
                        buttonAbort.addListener(new InputListener() {
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                Menu.getSoundClick().play();
                                Menu.setIsFirstSpawnHeroPosition(true);
                                musicBattle.stop();

                                if (hpHero > hpMaxHero * 0.2f) {
                                    Action action = Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            startFight.setPosition(0, -50);

                                            Label label = new Label("-20% HP", styleBlood);
                                            label.setPosition(buttonAbort.getX() + buttonAbort.getWidth() / 2 - label.getWidth() / 2 - 25, buttonAbort.getY() + 50);
                                            stage.addActor(label);
                                            label.setFontScale(2);

                                            Action action0 = Actions.run(new Runnable() {
                                                @Override
                                                public void run() {
                                                    buttonAbort.addAction(Actions.sequence(Actions.moveBy(0, -60, 1), Actions.fadeOut(2), Actions.moveTo(175, -3), Actions.fadeIn(1)));
                                                    buttonAbort.clearListeners();
                                                    abortActive.remove();
                                                    hpHero -= hpMaxHero * 0.2f;
                                                    lHpHero.setText(hpHero + " / " + hpMaxHero);
                                                }
                                            });

                                            Action action2 = Actions.run(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Label label = new Label("ABORT!", style);
                                                    label.setFontScale(3);
                                                    label.setPosition(85, 250);
                                                    stage.addActor(label);
                                                }
                                            });

                                            Action action3 = Actions.run(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (flip) {
                                                        enemy.getTexture().flip(true, false);
                                                        enemyImage = new Image(enemy.getTexture());
                                                    } else
                                                        enemyImage = new Image(enemy.getTexture());

                                                    hero.setHp(hpHero);

                                                    Menu.setMap();
                                                }
                                            });
                                            label.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(0, -60, 3), action0), Actions.parallel(Actions.fadeOut(1),
                                                    Actions.delay(3)), action2, Actions.delay(1.5f), action3));
                                        }
                                    });
                                    stage.addAction(Actions.sequence(action));
                                } else {
                                    buttonAbort.remove();
                                    new InfoScreen("Masz zbyt malo punktow zycia\nucieczka spowodowalaby smierc.\nPodnies sie i walcz!", 3, BaseScreen.getStage());
                                }
                                return false;
                            }
                        });
                        stage.addActor(buttonAbort);
                    }
                    return false;
                }
            });

            final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
            textStyle.font = font;
            textStyle.up = new TextureRegionDrawable(new TextureRegion(asset.manager.get("itemButton.png", Texture.class)));

            startFight = new TextButton("Fight!", textStyle);
            startFight.setSize(150, 50);
            startFight.setPosition(BaseScreen.VIEW_WIDTH / 2 - startFight.getWidth() / 2, 135);
            startFight.addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    startFight.remove();
                    try {
                        updateRound();
                    } catch (CloneNotSupportedException e) {
                    }
                    abort = false;
                    if (buttonAbort != null)
                        buttonAbort.remove();
                    return false;
                }
            });

            for (int i = 0; i < 4; i++) {
                plusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("plus.png", Texture.class))));
                minusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(asset.manager.get("minus.png", Texture.class))));
                plusButton[i].setPosition(i * 78, 79);
                minusButton[i].setPosition(i * 78, 35);

                labelPointFight[i] = new Label("" + pointUserPref[i], style);
                labelPointFight[i].setPosition(54 + i * 78, 69);

                addListener(i, true, labelFreePoint);
                addListener(i, false, labelFreePoint);
            }

            addActors(backgroundFight);
            for (int i = 0; i < 4; i++) {
                stage.addActor(plusButton[i]);
                stage.addActor(minusButton[i]);
                stage.addActor(labelPointFight[i]);
            }

            addActors(labelFreePoint, labelRoundNumber, labelName, labelLvl, abortActive, abortNonActive, barHpHero, barHpEnemy, heroImage, barEnergyHero,
                    barEnergyEnemy, enemyImage, enemy.getHead(), lHpHero, lHpEnemy, lEnergyHero, lEnergyEnemy, startFight);
        }
        musicBattle.setLooping(true);
        musicBattle.setVolume(0.6f);
        musicBattle.play();
    }

    @Override
    public void update(float dt) {
        //Update Scale bar hp and energy
        barHpHero.setSize((float)hpHero / hero.getFullHp() *120, barHpHero.getHeight());
        barEnergyHero.setSize((float)energyHero / energyMaxHero *120, barEnergyHero.getHeight());

        barHpEnemy.setSize((float)hpEnemy / hpMaxEnemy *-120, barHpEnemy.getHeight());
        barEnergyEnemy.setSize((float)energyEnemy / energyMaxEnemy *-120, barEnergyEnemy.getHeight());


    }

    private void updateRound() throws CloneNotSupportedException {
        enemyAiStats = updateEnemyAi();
        animationPlay = true;

        //Hero attack physics
        int physicsDmgHero = calculatePhysicsDmgHero();
        int physicsProcentHero = calculatePhysicsProcentHero();
        int physicsDmgHeroSecond = calculatePhysicsDmgHero();

        //Enemy attack physic
        int physicsDmgEnemy = calculatePhysicsDmgEnemy();
        int physicsProcentEnemy = calculatePhysicsProcentEnemy();
        int physicsDmgEnemySecond = calculatePhysicsDmgEnemy();

        //Hero attack magic
        int magicDmgHero = calculateMagicDmgHero();
        int magicProcentHero = calculateMagicPocentHero();

        //Enemy attack magic
        int magicDmgEnemy = calculateMagicDmgEnemy();
        int magicProcentEnemy = calculateMagicPocentEnemy();

        waponHero.addAction(Actions.fadeOut(0));
        stage.addActor(waponHero);
        waponEnemy.addAction(Actions.fadeOut(0));
        stage.addActor(waponEnemy);

        magicHero.addAction(Actions.fadeOut(0));
        stage.addActor(magicHero);
        magicEnemy.addAction(Actions.fadeOut(0));
        stage.addActor(magicEnemy);

        block.addAction(Actions.fadeOut(0));
        stage.addActor(block);

        blood.addAction(Actions.fadeOut(0));
        stage.addActor(blood);

        int[] randomHit = randomQueueHit();

        for(int i = 0; i < 6; i++){
            int duration = DURATION_ANIMATION[i];
            if(!stopAnimationRoundAttack) {
                System.out.println(stopAnimationRoundAttack);
                switch (randomHit[i]) {
                    case 1:
                        firstAttack(randomHit(physicsDmgEnemy, physicsProcentEnemy), physicsProcentEnemy, duration);
                        break;
                    case 2:
                        secondAttack(randomHit(physicsDmgEnemySecond, physicsProcentEnemy), physicsProcentEnemy, duration);
                        break;
                    case 3:
                        thirdAttack(randomHit(physicsDmgHero, physicsProcentHero), physicsProcentHero, duration);
                        break;
                    case 4:
                        fourthAttack(randomHit(physicsDmgHeroSecond, physicsProcentHero), physicsProcentHero, duration);
                        break;
                    case 5:
                        fiveAttack(randomHit(magicDmgEnemy, magicProcentEnemy), magicProcentEnemy, duration);
                        break;
                    case 6:
                        sixAttack(randomHit(magicDmgHero, magicProcentHero), magicProcentHero, duration);
                        break;
                    default:
                        break;
                }
                if (i == 5) {
                    Action action = Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            stage.addActor(startFight);
                            animationPlay = false;
                            abortFirstTap = true;
                            labelRoundNumber.setText(String.valueOf(Integer.parseInt(labelRoundNumber.getText().toString()) + 1));
                            abortNonActive.remove();
                        }
                    });
                    stage.addAction(Actions.sequence(Actions.delay(18), action));
                }
            }
        }
    }

    private int[] randomQueueHit() {
        boolean[] empty = new boolean[6];
        int[] number = new int[6];
        for(int i = 0; i < 6; i++){
            int random = MathUtils.random(1, 6);
            if(empty[random -1] != true){
                number[i] = random;
                empty[random -1] = true;
            }else{
                int check = random;
                boolean end = false;

                do{
                    if(check +1 > 6)
                        check = 0;
                    else
                        check++;
                    if(check == 0) {
                        if (empty[5] != true) {
                            empty[5] = true;
                            number[i] = 6;
                            end = true;
                        }
                    }
                    else {
                        if (empty[check - 1] != true) {
                            empty[check - 1] = true;
                            number[i] = check;
                            end = true;
                        }
                    }

                }while (!end);
            }
        }
        for (int num: number)
            System.out.println(num);
        return number;
    }

    private int randomHit(final int dmg, final int procent){
        int fortune = MathUtils.random(1, 100);
        System.out.println("dmg: " + dmg);
        System.out.println(fortune + " " + procent + "%" + (fortune < procent));
        if(fortune > procent)
            return 0;
        else
            return dmg;
    }

    private void sixAttack(final int dmg, final int procent, int duration) {
        labelCalculateHp[5] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[5] = new Label(procent + "%", style);
        labelCalculateHp[5].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[5].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[5].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateHp[5].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        labelCalculateProcent[5].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateProcent[5].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(labelCalculateHp[5],labelCalculateProcent[5]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                avergePercentsFight.add(procent);

                if(dmg == 0) {
                    energyHero -= 15;
                    animateBlock(false);
                    checkEnergy();
                }else {
                    avergeDmgFight.add(dmg);
                    hpEnemy -= dmg;
                    energyHero -= 20;
                    checkEnergy();
                    checkKill();
                    animateBlood(false);
                }
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                lEnergyHero.setText(energyHero + " / " + energyMaxHero);
            }
        });
        animateMagic(labelCalculateHp[5], labelCalculateProcent[5], duration, action, true);
    }

    private void fiveAttack(final int dmg, final int procent, int duration) {
        labelCalculateHp[4] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[4] = new Label(procent + "%", style);
        labelCalculateHp[4].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[4].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[4].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateHp[4].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        labelCalculateProcent[4].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateProcent[4].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(labelCalculateHp[4], labelCalculateProcent[4]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                if(dmg == 0) {
                    energyEnemy -= 15;
                    animateBlock(true);
                    checkEnergy();
                }else {
                    hpHero -= dmg;
                    energyEnemy -= 20;
                    checkEnergy();
                    checkKill();
                    animateBlood(true);
                }
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                lEnergyEnemy.setText(energyEnemy + " / " + energyMaxEnemy);

            }
        });
        animateMagic(labelCalculateHp[4], labelCalculateProcent[4], duration, action, false);
    }

    private void fourthAttack(final int dmg, final int procent, int duration) {
        labelCalculateHp[3] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[3] = new Label(procent + "%", style);
        labelCalculateHp[3].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[3].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[3].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateHp[3].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        labelCalculateProcent[3].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateProcent[3].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(labelCalculateHp[3], labelCalculateProcent[3]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                avergePercentsFight.add(procent);

                if(dmg == 0) {
                    energyHero -= 10;
                    animateBlock(false);
                    checkEnergy();
                }else {
                    avergeDmgFight.add(dmg);
                    hpEnemy -= dmg;
                    energyHero -= 15;
                    checkEnergy();
                    checkKill();
                    animateBlood(false);
                }
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                lEnergyHero.setText(energyHero + " / " + energyMaxHero);
            }
        });
        animatePhysics(labelCalculateHp[3], labelCalculateProcent[3], duration, action, true);
    }

    private void thirdAttack(final int dmg, final int procent, int duration) throws CloneNotSupportedException {
        labelCalculateHp[2] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[2] = new Label(procent + "%", style);
        labelCalculateHp[2].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[2].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[2].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateHp[2].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        labelCalculateProcent[2].setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - labelCalculateProcent[2].getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(labelCalculateHp[2], labelCalculateProcent[2]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                avergePercentsFight.add(procent);

                if(dmg == 0) {
                    energyHero -= 10;
                    animateBlock(false);
                    checkEnergy();
                }else {
                    avergeDmgFight.add(dmg);
                    hpEnemy -= dmg;
                    energyHero -= 15;
                    checkEnergy();
                    checkKill();
                    animateBlood(false);
                }
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                lEnergyHero.setText(energyHero + " / " + energyMaxHero);
            }
        });
        animatePhysics(labelCalculateHp[2], labelCalculateProcent[2], duration, action, true);
    }

    private void secondAttack(final int dmg, final int procent, int duration) {
        labelCalculateHp[1] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[1] = new Label(procent + "%", style);
        labelCalculateHp[1].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[1].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[1].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateHp[1].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        labelCalculateProcent[1].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateProcent[1].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(labelCalculateHp[1], labelCalculateProcent[1]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                if(dmg == 0) {
                    energyEnemy -= 10;
                    animateBlock(true);
                    checkEnergy();
                }else {
                    hpHero -= dmg;
                    energyEnemy -= 15;
                    checkEnergy();
                    checkKill();
                    animateBlood(true);
                }
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                lEnergyEnemy.setText(energyEnemy + " / " + energyMaxEnemy);
            }
        });
        animatePhysics(labelCalculateHp[1], labelCalculateProcent[1], duration, action, false);

    }

    private void firstAttack(final int dmg, final int procent, int duration) {
        labelCalculateHp[0] = new Label("-" + dmg, styleBlood);
        labelCalculateProcent[0] = new Label(procent + "%", style);
        labelCalculateHp[0].setFontScale(FONT_SCALE_HP);
        labelCalculateProcent[0].setFontScale(FONT_SCALE_PROCENT);
        labelCalculateHp[0].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateHp[0].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        labelCalculateProcent[0].setPosition(heroImage.getX() + heroImage.getWidth() /2 - labelCalculateProcent[0].getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(labelCalculateHp[0],  labelCalculateProcent[0]);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                if(dmg == 0) {
                    energyEnemy -= 10;
                    animateBlock(true);
                    checkEnergy();
                }else {
                    hpHero -= dmg;
                    energyEnemy -= 15;
                    checkEnergy();
                    checkKill();
                    animateBlood(true);
                }
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                lEnergyEnemy.setText(energyEnemy + " / " + energyMaxEnemy);
            }
        });
        animatePhysics(labelCalculateHp[0], labelCalculateProcent[0], duration, action, false);
    }

    private void animateBlock(boolean isHero) {
        if(isHero)
            block.setPosition(heroImage.getX() -12, heroImage.getY() +heroImage.getHeight() -20);
        else
            block.setPosition(enemyImage.getX() +enemyImage.getWidth()/2 +30, enemyImage.getY() + enemyImage.getHeight() -20);

        block.addAction(Actions.sequence(Actions.fadeIn(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                hitBlock.play();
            }
        }), Actions.moveBy(0, 15, 1), Actions.parallel(Actions.moveBy(0, 15, 0.5f), Actions.fadeOut(0.5f))));
    }

    private void animateBlood(boolean isHero) {
        if(isHero)
            blood.setPosition(heroImage.getX() -12, heroImage.getY() +heroImage.getHeight() -20);
        else
            blood.setPosition(enemyImage.getX() +enemyImage.getWidth()/2 +30, enemyImage.getY() + enemyImage.getHeight() -20);

        blood.addAction(Actions.sequence(Actions.fadeIn(0.5f), Actions.run(new Runnable() {
            @Override
            public void run() {
                hitPositive.play();
            }
        }) ,Actions.moveBy(0, 15, 1), Actions.parallel(Actions.moveBy(0, 15, 0.5f), Actions.fadeOut(0.5f))));
    }

    private static Image flipY() throws CloneNotSupportedException {
        if(Equipment.getTextureWapon() != null) {
            final TextureRegion texture = new TextureRegion(Equipment.getTextureWapon());
            texture.flip(true, false);
            Image image = new Image(texture);

            return image;
        }else{
            final TextureRegion texture = new TextureRegion(new Texture(Gdx.files.internal("heroArm.png")));
            texture.flip(true, false);
            Image image = new Image(texture);

            return image;
        }
    }

    private void checkKill(){
        if (hpHero < 1) {
            hpHero = 0;

            stage.addAction(Actions.sequence(Actions.delay(2.5f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    endFightRemoveEffect();
                    die.play();
                }
            }),Actions.delay(3), Actions.run(new Runnable() {
                @Override
                public void run() {
                    if (flip) {
                        enemy.getTexture().flip(true, false);
                        enemyImage = new Image(enemy.getTexture());
                    } else
                        enemyImage = new Image(enemy.getTexture());

                    float expMinus = Hero.getMaxExp();
                    float temporary = MathUtils.random(12, 17);

                    expMinus *= (temporary /100);

                    musicBattle.stop();
                    game.setScreen(new FightLose(game, hero, (int)calculateAverageWithArrey(avergeDmgFight), calculateAverageWithArrey(avergePercentsFight), -1000, -(int)expMinus));//TODO set minus gold
                }
            })));
        }
        else if(hpEnemy < 1){
            hpEnemy = 0;

            stage.addAction(Actions.sequence(Actions.delay(2.5f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    endFightRemoveEffect();
                    die.play();
                }
            }),Actions.delay(3), Actions.run(new Runnable() {
                @Override
                public void run() {
                    float expDrop = enemy.getExpDrop();
                    float moneyDrop = enemy.getMoneyDrop();
                    float temporary = MathUtils.random(-12, 12);

                    temporary = (100 +temporary) /100;
                    expDrop *= temporary;

                    temporary = MathUtils.random(-15, 15);
                    temporary = (100 +temporary) /100;
                    moneyDrop *= temporary;

                    int sizeListDropItem = enemy.getDropItem().size();
                    String dropItemName = "";

                    if(sizeListDropItem > 0) {
                        float chanceOnDrop = enemy.getRandomDrop() *10;
                        temporary = MathUtils.random(1, 1000);

                        if((int)temporary <= (int)chanceOnDrop) {
                            temporary = MathUtils.random(0, sizeListDropItem - 1);
                            dropItemName = enemy.getDropItem().get((int) temporary);
                        }
                    }

                    musicBattle.stop();
                    game.setScreen(new FightWin(game, hero, enemy, (int)calculateAverageWithArrey(avergeDmgFight), calculateAverageWithArrey(avergePercentsFight), (int)moneyDrop, (int)expDrop, dropItemName));
                }
            })));
        }
    }

    private float calculateAverageWithArrey(ArrayList<Integer> numbers){
        float value = 0;
        for(int dmg: numbers)
            value += dmg;
        value /= numbers.size();
        return value;
    }

    private void checkEnergy(){
        if(hpHero < 1 || hpEnemy < 1)
            return;

        if(energyHero < 1){
            int actualPoint0 = Integer.parseInt(labelPointFight[0].getText().toString());
            int actualPoint1 = Integer.parseInt(labelPointFight[1].getText().toString());
            int actualPoint2 = Integer.parseInt(labelPointFight[2].getText().toString());
            int actualPoint3 = Integer.parseInt(labelPointFight[3].getText().toString());

            int sume = freePointFight +actualPoint0 +actualPoint1 +actualPoint2 +actualPoint3;
            System.out.println(sume);

            if(sume > 10)
                try {
                    throw new MyException();
                } catch (MyException e) {
                    BaseScreen.showException(e);
                    e.printStackTrace();
                }

            if(sume > 5) {
                animateEnergyMinusPoint(true);
                energyHero +=100;

                if (freePointFight > 0) {
                    freePointFight--;
                    labelFreePoint.setText("" + freePointFight);
                } else if (Integer.parseInt(labelPointFight[0].getText().toString()) > 0) {
                    actualPoint0--;
                    labelPointFight[0].setText(String.valueOf(actualPoint0));
                } else if (Integer.parseInt(labelPointFight[1].getText().toString()) > 0) {
                    actualPoint1--;
                    labelPointFight[1].setText(String.valueOf(actualPoint1));
                } else if (Integer.parseInt(labelPointFight[2].getText().toString()) > 0) {
                    actualPoint2--;
                    labelPointFight[2].setText(String.valueOf(actualPoint2));
                } else if (Integer.parseInt(labelPointFight[3].getText().toString()) > 0) {
                    actualPoint3--;
                    labelPointFight[3].setText(String.valueOf(actualPoint3));
                }
            }else
                energyHero = 0;
        }
        if(energyEnemy < 1){
            if(pointFightEnemy > 5) {
                animateEnergyMinusPoint(false);
                energyEnemy += 100;
                pointFightEnemy--;
            }else
                energyEnemy = 100;
        }
    }

    private void endFightRemoveEffect(){
        waponHero.remove();
        waponEnemy.remove();
        magicHero.remove();
        magicEnemy.remove();
        block.remove();
        blood.remove();
        for(Label l: labelCalculateHp)
                l.remove();

        for(Label l: labelCalculateProcent)
                l.remove();
        startFight.remove();
    }

    private Label animateEnergyMinusPoint(boolean isHero){
        Label label = new Label("-1 punkt akcji", style);
        label.setColor(Color.ROYAL);
        label.addAction(Actions.fadeOut(0));
        label.setFontScale(2);

        if(isHero){
            label.setPosition(50, 115);
            label.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(0, 20, 2), Actions.fadeIn(0.6f)),
                    Actions.parallel(Actions.moveBy(0, 20, 2), Actions.sequence(Actions.delay(1), Actions.fadeOut(1)))));
        }else{
            label.setPosition(BaseScreen.VIEW_WIDTH -label.getWidth()*2 -60, 430);
            label.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(0, -20, 2), Actions.fadeIn(0.6f)),
                    Actions.parallel(Actions.moveBy(0, -20, 2), Actions.sequence(Actions.delay(1), Actions.fadeOut(1)))));
        }

        stage.addActor(label);
        return label;
    }

    private void animatePhysics(Label dmg, Label procent, int delay, Action action, boolean isHero) {
        if(isHero) {
            dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay +0.6f), Actions.fadeIn(0), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
            procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), action, Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1))));
            waponHero.addAction(Actions.sequence(Actions.delay(delay -0.2f), Actions.rotateBy(20), Actions.parallel(Actions.fadeIn(0.15f), Actions.rotateBy(-18, 0.15f)), Actions.parallel(Actions.delay(0.3f), Actions.rotateBy(-36, 0.3f)), Actions.rotateBy(-18, 0.15f), Actions.parallel(Actions.fadeOut(0.15f), Actions.rotateBy(-9, 0.15f)), Actions.rotateBy(61)));
        }else{
            if(enemy.getAttackType()){
                dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay + 0.6f), Actions.fadeIn(0), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
                procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), action, Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1))));
                waponEnemy.addAction(Actions.sequence(Actions.delay(delay - 0.2f), Actions.moveTo(heroImage.getX() +heroImage.getWidth() +20, enemyImage.getY() + enemyImage.getHeight() /2 -40), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveTo(heroImage.getX() +heroImage.getWidth() /2, heroImage.getY() +heroImage.getHeight() /2, 0.7f)), Actions.parallel(Actions.sequence(Actions.delay(0.5f), Actions.fadeOut(0.4f)), Actions.moveBy(70, 0, 0.7f))));

            }else {
                dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay + 0.6f), Actions.fadeIn(0), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
                procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), action, Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1))));
                waponEnemy.addAction(Actions.sequence(Actions.delay(delay - 0.2f), Actions.rotateBy(-20), Actions.parallel(Actions.fadeIn(0.15f), Actions.rotateBy(18, 0.15f)), Actions.parallel(Actions.delay(0.3f), Actions.rotateBy(36, 0.3f)), Actions.rotateBy(18, 0.15f), Actions.parallel(Actions.fadeOut(0.15f), Actions.rotateBy(9, 0.15f)), Actions.rotateBy(-61)));
            }
        }
    }

    private void animateMagic(Label dmg, Label procent, int delay, Action action, boolean isHero) {
        final float SPEED_MAGIC = 0.5f;
        if(isHero) {
            dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay +0.6f), Actions.fadeIn(0), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
            procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), action, Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1))));
            magicHero.addAction(Actions.sequence(Actions.delay(delay -0.5f), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(50, -10, SPEED_MAGIC +0.1f), Actions.scaleTo(1, 1, SPEED_MAGIC)), Actions.parallel(Actions.moveTo(targetX, enemyImage.getY() + enemyImage.getHeight() /2, SPEED_MAGIC -0.4f),Actions.fadeOut(0.4f))));
            magicHero.setBounds(heroImage.getX() + 50, heroImage.getY() +80, heroImage.getWidth() -30, heroImage.getHeight() -120);
        }else{
            dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay +0.6f), Actions.fadeIn(0), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
            procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), action, Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1))));
            magicEnemy.addAction(Actions.sequence(Actions.delay(delay -0.5f), Actions.parallel(Actions.fadeIn(0.5f), Actions.moveBy(-50, -10, SPEED_MAGIC +0.1f), Actions.scaleTo(1, 1, SPEED_MAGIC)), Actions.parallel(Actions.moveTo(BaseScreen.VIEW_WIDTH /4 -magicEnemy.getWidth() /2, heroImage.getY() + heroImage.getHeight() /2, SPEED_MAGIC -0.4f), Actions.fadeOut(0.4f))));
            magicEnemy.setBounds(targetX, enemyImage.getY() + enemyImage.getHeight() /2, heroImage.getWidth() -30, heroImage.getHeight() -120);
        }
    }

    private int calculateMagicPocentEnemy() {
        float enemyCellAttack = pointTable(enemyAiStats[2], false) *(float)enemy.getDefenseMagic();
        float heroCellDefense = pointTable(3, true) *(float)hero.getDefenseMagEq();
        float procent = enemyCellAttack /(enemyCellAttack + heroCellDefense) *100;

        System.out.println("Procent enemy to hero - Magic");
        System.out.println("enemy point: " + pointTable(enemyAiStats[2], false) *100 + "%");
        System.out.println("hero point: " + pointTable(3, true) *100 + "%");
        System.out.println("enemy attack: " + enemy.getDefenseMagic());
        System.out.println("hero defense: " + hero.getDefenseMagEq());
        System.out.println("enemy procent: " + enemyCellAttack);
        System.out.println("hero procent: " + heroCellDefense);
        System.out.println("F: " + procent + " I: " + (int)procent);
        System.out.println("-----------------------------------------------");

        return (int)procent;
    }

    private int calculateMagicDmgEnemy() {
        float dmg = enemy.getWiedza() *((100 +(float)enemy.getLevel()*2) /100);

        System.out.println("Dmg enemy to hero - Magic");
        System.out.println("enemy wiedza: " + enemy.getWiedza());
        System.out.println("enemy level hit: " + (100 +(float)enemy.getLevel()*2) + "%");
        System.out.println("Dmg none armor: " + dmg);

        dmg -= dmg *((float)hero.getArmor() /100);

        System.out.println("Armor: " + hero.getArmor() + "%");
        System.out.println("Final DMG: F: " + dmg + " I: " + (int)dmg);
        System.out.println("-----------------------------------------------");

        return (int)dmg;
    }

    private int calculateMagicPocentHero() {
        float heroCellAttack = pointTable(2, true) *(float)hero.getDefenseMagEq();
        float enemyCellDefense = pointTable(enemyAiStats[3], false) *(float)enemy.getDefenseMagic();
        float procent = heroCellAttack /(heroCellAttack + enemyCellDefense) *100;

        System.out.println("Procent hero to enemy - Magic");
        System.out.println("hero point: " + pointTable(2, true) *100 + "%");
        System.out.println("enemy point: " + pointTable(enemyAiStats[3], false) *100 + "%");
        System.out.println("hero attack: " + hero.getDefenseMagEq());
        System.out.println("enemy defense: " + enemy.getDefenseMagic());
        System.out.println("hero procent: " + heroCellAttack);
        System.out.println("enemy procent: " + enemyCellDefense);
        System.out.println("F: " + procent + " I: " + (int)procent);
        System.out.println("-----------------------------------------------");

        return (int)procent;
    }

    private int calculateMagicDmgHero() {
        float dmg = hero.getWiedzaEq() *((100 +(float)hero.getLevel()*2) /100);

        System.out.println("Dmg hero to enemy - Magic");
        System.out.println("hero wiedza: " + hero.getWiedzaEq());
        System.out.println("hero level hit: " + (100 +(float)hero.getLevel()*2) + "%");
        System.out.println("Dmg none armor: " + dmg);

        dmg -= dmg *((float)enemy.getArmor() /100);

        System.out.println("Armor: " + enemy.getArmor() + "%");
        System.out.println("Final DMG: F: " + dmg + " I: " + (int)dmg);
        System.out.println("-----------------------------------------------");

        float temporary = MathUtils.random(-12, 12);
        temporary = (100 +temporary) /100;
        dmg *= temporary;

        return (int)dmg;
    }

    private int calculatePhysicsProcentEnemy() {
        float enemyCellAttack = pointTable(enemyAiStats[0], false) *(float)enemy.getDefensePhysics();
        float heroCellDefense = pointTable(1, true) *(float)hero.getDefenseFizEq();
        float procent = enemyCellAttack /(enemyCellAttack + heroCellDefense) *100;

        System.out.println("Procent enemy to hero");
        System.out.println("enemy point: " + pointTable(enemyAiStats[0], false) *100 + "%");
        System.out.println("hero point: " + pointTable(1, true) *100 + "%");
        System.out.println("enemy attack: " + enemy.getDefensePhysics());
        System.out.println("hero defense: " + hero.getDefenseFizEq());
        System.out.println("enemy procent: " + enemyCellAttack);
        System.out.println("hero procent: " + heroCellDefense);
        System.out.println("F: " + procent + " I: " + (int)procent);
        System.out.println("-----------------------------------------------");

        return (int)procent;
    }

    private int calculatePhysicsDmgEnemy() {
        float dmg = enemy.getStrong() *((100 +(float)enemy.getLevel()) /100);

        System.out.println("Dmg enemy to hero");
        System.out.println("enemy strong: " + enemy.getStrong());
        System.out.println("enemy level hit: " + (100 +(float)enemy.getLevel()) + "%");
        System.out.println("Dmg none armor: " + dmg);

        dmg -= dmg *((float)hero.getArmor() /100);

        System.out.println("Armor: " + hero.getArmorEq() + "%");
        System.out.println("Final DMG: F: " + dmg + " I: " + (int)dmg);
        System.out.println("-----------------------------------------------");

        return (int)dmg;
    }

    private int calculatePhysicsProcentHero() {
        float heroCellAttack = pointTable(0, true) *(float)hero.getDefenseFizEq();
        float enemyCellDefense = pointTable(enemyAiStats[1], false) *(float)enemy.getDefensePhysics();
        float procent = heroCellAttack /(heroCellAttack + enemyCellDefense) *100;

        System.out.println("Procent hero to enemy");
        System.out.println("hero point: " + pointTable(0, true) *100 + "%");
        System.out.println("enemy point: " + pointTable(enemyAiStats[1], false) *100 + "%");
        System.out.println("hero attack: " + hero.getDefenseFizEq());
        System.out.println("enemy defense: " + enemy.getDefensePhysics());
        System.out.println("hero procent: " + heroCellAttack);
        System.out.println("enemy procent: " + enemyCellDefense);
        System.out.println("F: " + procent + " I: " + (int)procent);
        System.out.println("-----------------------------------------------");

        return (int)procent;
    }

    private int calculatePhysicsDmgHero() {
        float dmg = hero.getStrongEq() *((100 +(float)hero.getLevel()) /100);

        System.out.println("Dmg hero to enemy");
        System.out.println("hero strong: " + hero.getStrongEq());
        System.out.println("hero level hit: " + (100 +(float)hero.getLevel()) + "%");
        System.out.println("Dmg none armor: " + dmg);

        dmg -= dmg *((float)enemy.getArmor() /100);

        System.out.println("Armor: " + enemy.getArmor() + "%");
        System.out.println("Final DMG: F: " + dmg + " I: " + (int)dmg);
        System.out.println("-----------------------------------------------");

        float temporary = MathUtils.random(-12, 12);
        temporary = (100 +temporary) /100;
        dmg *= temporary;

        return (int)dmg;
    }

    private int[] updateEnemyAi(){
        float heroPercentHp = (float)hpHero /hpMaxHero *100;
        float enemyPercentHp = (float)hpEnemy / hpMaxEnemy *100;

        if((enemyPercentHp -heroPercentHp) >= 50 && pointFightEnemy == 10) {
            System.out.println("SUPER ATTACK");
            return SUPER_ATTACK;
        }

        int random;
        if(pointFightEnemy > 8) {
            random = MathUtils.random(0, 3);
            System.out.println("random" + random);
        }else{
            random = MathUtils.random(0, 2);
            System.out.println("random" + random);
        }

        if((enemyPercentHp -heroPercentHp) >= 20) {
            System.out.println("ATTACK");
            return BaseEnemyAI.getEnemyAIPoint(2, pointFightEnemy, random);
        }
        else if((enemyPercentHp - heroPercentHp) < 20 && (enemyPercentHp -heroPercentHp) > -20) {
            System.out.println("NORMAL");
            return BaseEnemyAI.getEnemyAIPoint(1, pointFightEnemy, random);
        }
        else if ((enemyPercentHp -heroPercentHp) < -20) {
            System.out.println("Defense");
            return BaseEnemyAI.getEnemyAIPoint(0, pointFightEnemy, random);
        }
        else
            System.out.println("ERROR NOT CHANGE SETTING POINT FIGHT");

        return new int[]{2, 3, 3, 2};
    }

    private float pointTable(int i, boolean heroBoolean) {
        if(heroBoolean)
            i = Integer.parseInt(labelPointFight[i].getText().toString());
        //TODO set % values count point
        switch (i){
            case 1:
                return 0.7f;
            case 2:
                return 1.2f;
            case 3:
                return 1.6f;
            case 4:
                return 2;
            case 5:
                return 2.3f;
            default:
                return 0.1f;
        }
    }

    private void addActors(Actor... actors){
        for(Actor actor: actors)
            stage.addActor(actor);
    }

    private void addListener(final int iterator, boolean plus, final Label label){
        if(plus) {
            plusButton[iterator].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Menu.getSoundClick().play();
                    int actualPoint = Integer.parseInt(labelPointFight[iterator].getText().toString());
                    if(actualPoint < 5 && freePointFight > 0) {
                        actualPoint++;
                        freePointFight--;
                        label.setText("" + freePointFight);
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
                    if (actualPoint > 0 && freePointFight >= 0) {
                        actualPoint--;
                        freePointFight++;
                        label.setText("" + freePointFight);
                        labelPointFight[iterator].setText(String.valueOf(actualPoint));
                    }
                    return false;
                }
            });
        }
    }
}
