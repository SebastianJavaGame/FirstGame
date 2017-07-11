package Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.mygdx.game.Enemy;
import com.mygdx.game.Hero;

/**
 * Created by Sebastian on 2017-07-09.
 */

public class FightScreen extends BaseScreen {
    private static final int ENERGY_MAX = 100;
    private static final float FONT_SCALE_HP = 2;
    private static final float FONT_SCALE_PROCENT = 1.7f;

    private static Hero hero;
    private static Enemy enemy;

    private static Image heroImage;
    private static Image enemyImage;

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
    private static int freePointFight;

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

    private static int enemyAiStats[];

    private static boolean flip;
    private static boolean abort;

    BitmapFont font = new BitmapFont();
    Label.LabelStyle style = new Label.LabelStyle();
    Label.LabelStyle styleBlood = new Label.LabelStyle();

    public FightScreen(Game g, Hero hero, Enemy enemy, boolean flip) {
        super(g);
        this.hero = hero;
        this.enemy = enemy;
        plusButton = new ImageButton[4];
        minusButton = new ImageButton[4];
        labelPointFight = new Label[4];
        enemyAiStats = new int[4];
        style.font = font;
        styleBlood.font = font;
        styleBlood.fontColor = new Color(Color.FIREBRICK);
        this.flip = flip;
        abort = true;
        create();
    }

    @Override
    public void create() {
        hpHero = hero.getHp();
        hpEnemy = enemy.getHp();
        hpMaxHero = hero.getFullHp();
        hpMaxEnemy = enemy.getHp();
        freePointFight = 10;
        energyMaxEnemy = 100;
        energyMaxHero = 100;
        energyHero = energyMaxHero;
        energyEnemy = energyMaxEnemy;

        backgroundFight = new Image(new Texture(Gdx.files.internal("fight.png")));
        barHpHero = new Image(new Texture(Gdx.files.internal("barHpFight.png")));
        barHpEnemy = new Image(new Texture(Gdx.files.internal("barHpFight.png")));
        barEnergyHero = new Image(new Texture(Gdx.files.internal("barEnergyFight.png")));
        barEnergyEnemy = new Image(new Texture(Gdx.files.internal("barEnergyFight.png")));
        heroImage = new Image(new Texture(Gdx.files.internal("heroImage.png")));

        if(flip) {
            enemy.getTexture().flip(true, false);
            enemyImage = new Image(enemy.getTexture());
        }else
            enemyImage = new Image(enemy.getTexture());

        heroImage.setBounds(10, 180, BaseScreen.VIEW_WIDTH /2 - 20, 200);
        enemyImage.setBounds(BaseScreen.VIEW_WIDTH /2 + 10, 180, BaseScreen.VIEW_WIDTH /2 -20, 200);
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
        Label labelFreePoint = new Label("" + freePointFight, style);
        labelRoundNumber = new Label("1", style);
        Label labelName = new Label("Gondor", style);
        Label labelLvl = new Label("Level 35", style);

        lHpHero.setPosition(53 + barHpHero.getWidth() /2 -(lHpHero.getWidth() *fontSize) /2, 15);
        lHpEnemy.setPosition(267 + barHpEnemy.getWidth() /2 -(lHpEnemy.getWidth() *fontSize) /2, 461);
        lEnergyHero.setPosition(53 + barEnergyHero.getWidth() /2 -(lEnergyHero.getWidth() *fontSize) /2, 0);
        lEnergyEnemy.setPosition(267 + barEnergyEnemy.getWidth() /2 -(lEnergyEnemy.getWidth() *fontSize) /2, 446);
        labelName.setPosition((BaseScreen.VIEW_WIDTH - 190) /2 - labelName.getWidth() /2, 460);
        labelLvl.setPosition((BaseScreen.VIEW_WIDTH - 190) /2 - labelLvl.getWidth() /2 +10, 445);
        labelFreePoint.setPosition(131, 105);
        labelRoundNumber.setPosition(287, 105);

        labelName.setFontScale(1.2f);
        lHpHero.setFontScale(fontSize);
        lHpEnemy.setFontScale(fontSize);
        lEnergyHero.setFontScale(fontSize);
        lEnergyEnemy.setFontScale(fontSize);

        abortNonActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbord.png")))));
        abortNonActive.setBounds(255, 0, 45, 45);
        abortNonActive.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(abort) {
                    Label label = new Label("ABORT!", style);
                    label.setFontScale(3);
                    label.setPosition(85, 250);
                    stage.addActor(label);

                    Action action = Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new Map_01(game));
                        }
                    });
                    stage.addAction(Actions.sequence(Actions.delay(1), action));
                }
                return false;
            }
        });

        abortActive = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbordActive.png")))));
        abortActive.setBounds(255, 0, 45, 45);
        abortActive.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                final TextButton.TextButtonStyle textStyleAbort = new TextButton.TextButtonStyle();
                textStyleAbort.font = font;
                textStyleAbort.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttonAbort.png"))));
                buttonAbort = new TextButton("Abort!", textStyleAbort);
                buttonAbort.setSize(150, 50);
                buttonAbort.setPosition(BaseScreen.VIEW_WIDTH /2 - startFight.getWidth() /2, 160);
                buttonAbort.addListener(new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        Action action = Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                game.setScreen(new Map_01(game));
                            }
                        });
                        stage.addAction(Actions.sequence(Actions.delay(1), action));
                        return false;
                    }
                });
                stage.addActor(buttonAbort);
                return false;
            }
        });

        final TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        textStyle.font = font;
        textStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("itemButton.png"))));

        startFight = new TextButton("Fight!", textStyle);
        startFight.setSize(150, 50);
        startFight.setPosition(BaseScreen.VIEW_WIDTH /2 - startFight.getWidth() /2, 135);
        startFight.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start");
                startFight.remove();
                updateRound();
                abort = false;
                if(buttonAbort != null)
                    buttonAbort.remove();
                return false;
            }
        });

        for(int i = 0; i < 4; i++) {
            plusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("plus.png")))));
            minusButton[i] = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("minus.png")))));
            plusButton[i].setPosition(i* 78, 79);
            minusButton[i].setPosition(i* 78, 35);

            labelPointFight[i] = new Label("0", style);
            labelPointFight[i].setPosition(54 + i* 78, 69);
            labelPointFight[i].setFontScale(1.2f);

            addListener(i, true, labelFreePoint);
            addListener(i, false, labelFreePoint);
        }

        addActors(backgroundFight);
        for(int i = 0; i < 4; i++){
            stage.addActor(plusButton[i]);
            stage.addActor(minusButton[i]);
            stage.addActor(labelPointFight[i]);
        }

        addActors(labelFreePoint, labelRoundNumber, labelName, labelLvl, abortActive, abortNonActive, barHpHero, barHpEnemy, heroImage, barEnergyHero,
                barEnergyEnemy, enemyImage, enemy.getHead(), lHpHero, lHpEnemy, lEnergyHero, lEnergyEnemy, startFight);
    }

    @Override
    public void update(float dt) {
        //Update Scale bar hp and energy
        barHpHero.setSize((float)hpHero / hero.getFullHp() *120, barHpHero.getHeight());
        barEnergyHero.setSize(energyHero / energyMaxHero *120, barEnergyHero.getHeight());

        barHpEnemy.setSize((float)hpEnemy / hpMaxEnemy *-120, barHpEnemy.getHeight());
        barEnergyEnemy.setSize(energyEnemy / energyMaxEnemy *-120, barEnergyEnemy.getHeight());
    }

    private void updateRound() {
        enemyAiStats = updateEnemyAi();

        //Hero attack physics
        int physicsDmgHero = calculatePhysicsDmgHero();
        int physicsProcentHero = calculatePhysicsProcentHero();

        //Enemy attack physic
        int physicsDmgEnemy = calculatePhysicsDmgEnemy();
        int physicsProcentEnemy = calculatePhysicsProcentEnemy();

        //Hero attack magic
        int magicDmgHero = calculateMagicDmgHero();
        int magicProcentHero = calculateMagicPocentHero();

        //Enemy attack magic
        int magicDmgEnemy = calculateMagicDmgEnemy();
        int magicProcentEnemy = calculateMagicPocentEnemy();

        firstAttack(randomHit(physicsDmgEnemy, physicsProcentEnemy), physicsProcentEnemy);
        secondAttack(randomHit(physicsDmgEnemy, physicsProcentEnemy), physicsProcentEnemy);
        thirdAttack(randomHit(physicsDmgHero, physicsProcentHero), physicsProcentHero);
        fourthAttack(randomHit(physicsDmgHero, physicsProcentHero), physicsProcentHero);
        fiveAttack(randomHit(magicDmgEnemy, magicProcentEnemy), magicProcentEnemy);
        sixAttack(randomHit(magicDmgHero, magicProcentHero), magicProcentHero);
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

    private void sixAttack(final int dmg, int procent) {
        Label hpMagEnemyFirst = new Label("-" + dmg, styleBlood);
        Label procentMagEnemyFirst = new Label(procent + "%", style);
        hpMagEnemyFirst.setFontScale(FONT_SCALE_HP);
        procentMagEnemyFirst.setFontScale(FONT_SCALE_PROCENT);
        hpMagEnemyFirst.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - hpMagEnemyFirst.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        procentMagEnemyFirst.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - procentMagEnemyFirst.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(hpMagEnemyFirst,procentMagEnemyFirst);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpEnemy -= dmg;
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                checkKill();

                stage.addActor(startFight);
                labelRoundNumber.setText(String.valueOf(Integer.parseInt(labelRoundNumber.getText().toString()) +1));
                abortNonActive.remove();
            }
        });
        animate(hpMagEnemyFirst, procentMagEnemyFirst, 15, action);
    }

    private void fiveAttack(final int dmg, int procent) {
        Label hpMagHeroFirst = new Label("-" + dmg, styleBlood);
        Label procentMagHeroFirst = new Label(procent + "%", style);
        hpMagHeroFirst.setFontScale(FONT_SCALE_HP);
        procentMagHeroFirst.setFontScale(FONT_SCALE_PROCENT);
        hpMagHeroFirst.setPosition(heroImage.getX() + heroImage.getWidth() /2 - hpMagHeroFirst.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        procentMagHeroFirst.setPosition(heroImage.getX() + heroImage.getWidth() /2 - procentMagHeroFirst.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(hpMagHeroFirst, procentMagHeroFirst);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpHero -= dmg;
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                checkKill();
            }
        });
        animate(hpMagHeroFirst, procentMagHeroFirst, 12, action);
    }

    private void fourthAttack(final int dmg, int procent) {
        Label hpPhyEnemySecond = new Label("-" + dmg, styleBlood);
        Label procentPhyEnemySecond = new Label(procent + "%", style);
        hpPhyEnemySecond.setFontScale(FONT_SCALE_HP);
        procentPhyEnemySecond.setFontScale(FONT_SCALE_PROCENT);
        hpPhyEnemySecond.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - hpPhyEnemySecond.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        procentPhyEnemySecond.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - procentPhyEnemySecond.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(hpPhyEnemySecond, procentPhyEnemySecond);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpEnemy -= dmg;
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                checkKill();
            }
        });
        animate(hpPhyEnemySecond, procentPhyEnemySecond, 9, action);
    }

    private void thirdAttack(final int dmg, int procent) {
        Label hpPhyEnemyFirst = new Label("-" + dmg, styleBlood);
        Label procentPhyEnemyFirst = new Label(procent + "%", style);
        hpPhyEnemyFirst.setFontScale(FONT_SCALE_HP);
        procentPhyEnemyFirst.setFontScale(FONT_SCALE_PROCENT);
        hpPhyEnemyFirst.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - hpPhyEnemyFirst.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() -15);
        procentPhyEnemyFirst.setPosition(enemyImage.getX() + enemyImage.getWidth() /2 - procentPhyEnemyFirst.getWidth() /2 -10, enemyImage.getY() + enemyImage.getHeight() +10);
        addActors(hpPhyEnemyFirst, procentPhyEnemyFirst);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpEnemy -= dmg;
                lHpEnemy.setText(hpEnemy + " / " + hpMaxEnemy);
                checkKill();
            }
        });
        animate(hpPhyEnemyFirst, procentPhyEnemyFirst, 6, action);
    }

    private void secondAttack(final int dmg, int procent) {
        Label hpPhyHeroSecond = new Label("-" + dmg, styleBlood);
        Label procentPhyHeroSecond = new Label(procent + "%", style);
        hpPhyHeroSecond.setFontScale(FONT_SCALE_HP);
        procentPhyHeroSecond.setFontScale(FONT_SCALE_PROCENT);
        hpPhyHeroSecond.setPosition(heroImage.getX() + heroImage.getWidth() /2 - hpPhyHeroSecond.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        procentPhyHeroSecond.setPosition(heroImage.getX() + heroImage.getWidth() /2 - procentPhyHeroSecond.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(hpPhyHeroSecond, procentPhyHeroSecond);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpHero -= dmg;
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                checkKill();
            }
        });
        animate(hpPhyHeroSecond, procentPhyHeroSecond, 3, action);

    }

    private void firstAttack(final int dmg, int procent) {
        Label hpPhyHeroFirst = new Label("-" + dmg, styleBlood);
        Label procentPhyHeroFirst = new Label(procent + "%", style);
        hpPhyHeroFirst.setFontScale(FONT_SCALE_HP);
        procentPhyHeroFirst.setFontScale(FONT_SCALE_PROCENT);
        hpPhyHeroFirst.setPosition(heroImage.getX() + heroImage.getWidth() /2 - hpPhyHeroFirst.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() -15);
        procentPhyHeroFirst.setPosition(heroImage.getX() + heroImage.getWidth() /2 - procentPhyHeroFirst.getWidth() /2 -10, heroImage.getY() + heroImage.getHeight() +10);
        addActors(hpPhyHeroFirst, procentPhyHeroFirst);
        Action action = Actions.run(new Runnable() {
            @Override
            public void run() {
                hpHero -= dmg;
                lHpHero.setText(hpHero + " / " + hpMaxHero);
                checkKill();
            }
        });
        animate(hpPhyHeroFirst, procentPhyHeroFirst, 0, action);
    }

    private void checkKill(){
        if(hpHero < 1){
            hpHero = 0;
            //TODO wait
            game.setScreen(new HeroDead(game));
        }

        if(hpEnemy < 1){
            hpEnemy = 0;
            //TODO wait
            game.setScreen(new FightStats(game));
        }
    }

    private void animate(Label dmg, Label procent, int delay, Action action) {
        dmg.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay + 0.5f), Actions.fadeIn(0),  Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.8f), Actions.moveBy(0, 10, 1))));
        procent.addAction(Actions.sequence(Actions.fadeOut(0), Actions.delay(delay), Actions.fadeIn(0), Actions.delay(0.1f), Actions.moveBy(0, 20, 2), Actions.parallel(Actions.fadeOut(0.4f), Actions.moveBy(0, 10, 1), action)));
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

        return (int)dmg;
    }

    private int[] updateEnemyAi(){
        return new int[]{2, 3, 3, 2};
    }

    private float pointTable(int i, boolean heroBoolean) {
        if(heroBoolean)
            i = Integer.parseInt(labelPointFight[i].getText().toString());

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
                    System.out.println("PLUS");
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
                    System.out.println("MINUS");
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
