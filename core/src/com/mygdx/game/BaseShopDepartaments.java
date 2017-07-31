package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Sebastian on 2017-07-31.
 */

public class BaseShopDepartaments {
    private static Preferences[][] pref = new Preferences[1][8];
    private static String[] items = new String[10];

    public BaseShopDepartaments(){
        loadFirstShopHelmet();
        loadFirstShopArmor();
        loadFirstShopPants();
        loadFirstShopShoes();
        loadFirstShopWapon();
        loadFirstShopItemBlock();
        loadFirstShopItemArm();
        loadFirstShopRing();
    }

    /**
     * SHOP NR.0
     */
    private void loadFirstShopHelmet(){
        pref[0][0] = Gdx.app.getPreferences("SHOP_0_HELMET");
        pref[0][0].flush();
    }

    private void loadFirstShopArmor(){
        pref[0][1] = Gdx.app.getPreferences("SHOP_0_ARMOR");
        pref[0][1].putString("SLOT0", "gold_armor");
        pref[0][1].putString("SLOT1", "gold_armor");
        pref[0][1].flush();
    }

    private void loadFirstShopPants(){
        pref[0][2] = Gdx.app.getPreferences("SHOP_0_PANTS");
    }

    private void loadFirstShopShoes(){
        pref[0][3] = Gdx.app.getPreferences("SHOP_0_SHOES");
    }

    private void loadFirstShopWapon(){
        pref[0][4] = Gdx.app.getPreferences("SHOP_0_WAPON");
        pref[0][4].putString("SLOT0", "silver_sword");
        pref[0][4].putString("SLOT1", "fire_sword");
        pref[0][4].flush();
    }

    private void loadFirstShopItemBlock(){
        pref[0][5] = Gdx.app.getPreferences("SHOP_0_ITEM_BLOCK");
    }

    private void loadFirstShopItemArm(){
        pref[0][6] = Gdx.app.getPreferences("SHOP_0_ITEM_ARM");
    }

    private void loadFirstShopRing(){
        pref[0][7] = Gdx.app.getPreferences("SHOP_0_RING");
    }


    /**
     * SHOP NR.1
     */

    public static String[] getItemsFromDepartament(int indexShop, int indexDepartament){
        String itemName;

        for(int i = 0; i < items.length; i++){
            itemName = pref[indexShop][indexDepartament].getString("SLOT" +i, "");
            items[i] = itemName;
        }

        for(String s:items)
            System.out.println(s);

        return items;
    }
}