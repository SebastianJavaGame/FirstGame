package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Sebastian on 2017-08-07.
 */

public class Asset implements Disposable {
    public final AssetManager manager = new AssetManager();

    public void loadMenu(){
        manager.load("menu.png", Texture.class);
        manager.load("confirmButtonNewGame.png", Texture.class);
        manager.load("menuButton.png", Texture.class);
        manager.load("confirmNewGame.png", Texture.class);
    }

    public void loadBag(){
        manager.load("statsBackground.png", Texture.class);
        manager.load("eq.png", Texture.class);
        manager.load("stats.png", Texture.class);
        manager.load("quest.png", Texture.class);
        manager.load("exit.png", Texture.class);
        manager.load("bar.png", Texture.class);
        manager.load("exitGame.png", Texture.class);
    }

    public void loadEnemy(){
        manager.load("shadow.png", Texture.class);
        manager.load("buttonAttack.png", Texture.class);
        manager.load("buttonInfo.png", Texture.class);
        manager.load("buttonCancel.png", Texture.class);
        manager.load("infoEnemy.png", Texture.class);
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
    }

    public void loadFieldDialogue(){
        manager.load("dialogueShortBar.png", Texture.class);
        manager.load("dialogueLongBar.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("uiExp.png", Texture.class);
    }

    public void loadFunctionalityShop(){
        manager.load("buttonBack.png", Texture.class);
        manager.load("statsBackground.png", Texture.class);
        manager.load("uiMoney.png", Texture.class);
        manager.load("slotInfoItem.png", Texture.class);
        manager.load("nameBar.png", Texture.class);
        manager.load("barX.png", Texture.class);
    }

    public void loadHero(){
        manager.load("heroArm.png", Texture.class);
    }

    public void loadShop(){
        manager.load("shopBackground.png", Texture.class);
        manager.load("shopMenuBackground.png", Texture.class);
        manager.load("buttonCancel.png", Texture.class);
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
        manager.load("taskBackground.png", Texture.class);
        manager.load("taskProgressBackground.png", Texture.class);
        manager.load("taskProgress.png", Texture.class);
        manager.load("buttonTaskCancel.png", Texture.class);
    }

    public void loadTransaction(){
        manager.load("buttonBack.png", Texture.class);
        manager.load("buttonTransaction.png", Texture.class);
        manager.load("shopBackgroundTransaction.png", Texture.class);
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
    }

    public void loadMap_01(){
        //TODO load all map with enemy image
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
