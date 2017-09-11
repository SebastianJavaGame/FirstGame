package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Sebastian on 2017-08-07.
 */

public class Asset implements Disposable {
    public final AssetManager manager = new AssetManager();

    public void loadMenu(){
        manager.load("menu.jpg", Texture.class);
        manager.load("confirmButtonNewGame.png", Texture.class);
        manager.load("menuButton.png", Texture.class);
        manager.load("confirmNewGame.png", Texture.class);
        manager.load("loadingFull.png", Texture.class);
        manager.load("sound/click.ogg", Sound.class);
    }

    public void loadCompassClass(){
        manager.load("compass.png", Texture.class);
        manager.load("compassToward.png", Texture.class);
        manager.load("map.png", Texture.class);
        manager.load("map01View.jpg", Texture.class);
        manager.load("list.png", Texture.class);
        manager.load("cross.png", Texture.class);
        manager.load("mapMap.png", Texture.class);
        manager.load("deleteLine.png", Texture.class);
        manager.load("deleteBoss.png", Texture.class);
    }

    public void loadBag(){
        manager.load("statsBackground.png", Texture.class);
        manager.load("eq.png", Texture.class);
        manager.load("stats.png", Texture.class);
        manager.load("quest.png", Texture.class);
        manager.load("pay.png", Texture.class);
        manager.load("exit.png", Texture.class);
        manager.load("bar.png", Texture.class);
        manager.load("exitGame.png", Texture.class);

        manager.load("sound/card.ogg", Sound.class);
    }

    public void loadEnemy(){
        manager.load("shadow.png", Texture.class);
        manager.load("buttonAttack.png", Texture.class);
        manager.load("buttonInfo.png", Texture.class);
        manager.load("buttonCancel.png", Texture.class);
        manager.load("infoEnemy.png", Texture.class);
        manager.load("hp.jpg", Texture.class);
        manager.load("sila.jpg", Texture.class);
        manager.load("wiedza.jpg", Texture.class);
        manager.load("pancerz.jpg", Texture.class);
        manager.load("zrecznosc.jpg", Texture.class);
        manager.load("magia.jpg", Texture.class);
        manager.load("buttonBorder.png", Texture.class);

        manager.load("sound/collisionEnemy.ogg", Sound.class);
        manager.load("sound/card.ogg", Sound.class);
    }

    public void loadEquipment(){
        manager.load("buttonUserPref.png", Texture.class);
        manager.load("userPrefBackground.png", Texture.class);
        manager.load("buttonAbort.png", Texture.class);
        manager.load("plusPref.png", Texture.class);
        manager.load("minusPref.png", Texture.class);
        manager.load("slot.png", Texture.class);
        manager.load("emptySlot.png", Texture.class);
        manager.load("itemButton.png", Texture.class);
        manager.load("statsBackground.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("slotInfoItem.png", Texture.class);
        manager.load("nameBar.png", Texture.class);
        manager.load("barX.png", Texture.class);
        manager.load("hp.jpg", Texture.class);
        manager.load("sila.jpg", Texture.class);
        manager.load("wiedza.jpg", Texture.class);
        manager.load("pancerz.jpg", Texture.class);
        manager.load("zrecznosc.jpg", Texture.class);
        manager.load("magia.jpg", Texture.class);

        manager.load("sound/soundEquipment.ogg", Sound.class);
    }

    public void loadFieldDialogue(){
        manager.load("dialogueShortBar.png", Texture.class);
        manager.load("dialogueLongBar.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("uiExp.png", Texture.class);

        manager.load("sound/textDialog.ogg", Sound.class);
    }

    public void loadFunctionalityShop(){
        manager.load("buttonBack.png", Texture.class);
        manager.load("statsBackground.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("slotInfoItem.png", Texture.class);
        manager.load("nameBar.png", Texture.class);
        manager.load("barX.png", Texture.class);
        manager.load("hp.jpg", Texture.class);
        manager.load("sila.jpg", Texture.class);
        manager.load("wiedza.jpg", Texture.class);
        manager.load("pancerz.jpg", Texture.class);
        manager.load("zrecznosc.jpg", Texture.class);
        manager.load("magia.jpg", Texture.class);
    }

    public void loadHero(){
        manager.load("heroArm.png", Texture.class);

        manager.load("sound/nextMap.ogg", Sound.class);
        manager.load("sound/step.ogg", Sound.class);
        manager.load("sound/lvlUp.ogg", Sound.class);
    }

    public void loadShop(){
        manager.load("shopBackground.png", Texture.class);
        manager.load("shopMenuBackground.png", Texture.class);
        manager.load("buttonCancel.png", Texture.class);
        manager.load("buttonExit.png", Texture.class);
    }

    public void loadStatsHero(){
        manager.load("barX.png", Texture.class);
        manager.load("barExp.png", Texture.class);
        manager.load("barEmpty.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("barStats.png", Texture.class);
        manager.load("add.png", Texture.class);
    }

    public void loadTask(){
        manager.load("taskBackground.jpg", Texture.class);
        manager.load("taskProgressBackground.png", Texture.class);
        manager.load("taskProgress.png", Texture.class);
        manager.load("buttonTaskCancel.png", Texture.class);
        manager.load("enemy/map2/p3.png", Texture.class);
        manager.load("enemy/map2/p5.png", Texture.class);
        manager.load("enemy/map3/p2.png", Texture.class);
        manager.load("enemy/map3/p4.png", Texture.class);
        manager.load("enemy/map4/p3.png", Texture.class);
        manager.load("enemy/map4/p5.png", Texture.class);
        manager.load("enemy/map4/p9.png", Texture.class);
        manager.load("enemy/map5/p5.png", Texture.class);
        manager.load("enemy/map5/p7.png", Texture.class);
        manager.load("enemy/map5/p10.png", Texture.class);
        manager.load("enemy/map6/p3.png", Texture.class);
        manager.load("enemy/map6/p8.png", Texture.class);
    }

    public void loadTransaction(){
        manager.load("buttonBack.png", Texture.class);
        manager.load("buttonTransaction.png", Texture.class);
        manager.load("shopBackgroundTransaction.png", Texture.class);

        manager.load("sound/shop.ogg", Sound.class);
    }

    public void loadBaseMap(){
        manager.load("hero.png", Texture.class);
        manager.load("uiBackground.png", Texture.class);
        manager.load("uiHp.png", Texture.class);
        manager.load("uiExp.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("barEmpty.png", Texture.class);
        manager.load("barHp.png", Texture.class);
        manager.load("barExp.png", Texture.class);
        manager.load("slotLvl.png", Texture.class);

        manager.load("sound/card.ogg", Sound.class);
    }

    public void loadFightScreen(){
        manager.load("fight.png", Texture.class);
        manager.load("barHpFight.png", Texture.class);
        manager.load("barEnergyFight.png", Texture.class);
        manager.load("heroImage.png", Texture.class);
        manager.load("magicHero.png", Texture.class);
        manager.load("magicEnemy.png", Texture.class);
        manager.load("blood.png", Texture.class);
        manager.load("blockAttack.png", Texture.class);
        manager.load("buttonAbord.png", Texture.class);
        manager.load("buttonAbordActive.png", Texture.class);
        manager.load("itemButton.png", Texture.class);
        manager.load("plus.png", Texture.class);
        manager.load("minus.png", Texture.class);

        manager.load("sound/battle.ogg", Music.class);
        manager.load("sound/blockAttack.ogg", Sound.class);
        manager.load("sound/hitPositive.ogg", Sound.class);
        manager.load("sound/herodie.ogg", Sound.class);
    }

    public void loadQuest(){
        manager.load("sound/taskComplite.ogg", Sound.class);
    }

    public void dispose() {
        manager.dispose();
    }
}
