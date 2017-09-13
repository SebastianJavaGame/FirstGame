package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Sebastian on 2017-07-31.
 */

public class BaseShopDepartaments {
    private static Preferences[][] pref = new Preferences[6 ][8];
    private static String[] items = new String[10];

    public BaseShopDepartaments(){
        loadFirstShop();
        loadSecondShop();
        loadThirdShop();
        loadFourthShop();
        loadFivthShop();
        loadSixthShop();
    }

    /**
     * SHOP 1
     */
    private void loadFirstShop() {
        //Helmet
        pref[0][0] = Gdx.app.getPreferences("SHOP_0_HELMET");
        pref[0][0].clear();
        pref[0][0].putString("SLOT0", "helmet1").flush();
        pref[0][0].putString("SLOT1", "helmet2").flush();
        pref[0][0].putString("SLOT2", "helmet3").flush();
        pref[0][0].putString("SLOT3", "helmet4").flush();
        pref[0][0].putString("SLOT4", "helmet5").flush();

        //Armor
        pref[0][1] = Gdx.app.getPreferences("SHOP_0_ARMOR");
        pref[0][1].clear();
        pref[0][1].putString("SLOT0", "armor1").flush();
        pref[0][1].putString("SLOT1", "armor2").flush();
        pref[0][1].putString("SLOT2", "armor3").flush();
        pref[0][1].putString("SLOT3", "armor4").flush();
        pref[0][1].putString("SLOT4", "armor5").flush();
        //pref[0][1].putString("SLOT8", "armor1pay").flush();
        //pref[0][1].putString("SLOT9", "armor2pay").flush();

        //Pants
        pref[0][2] = Gdx.app.getPreferences("SHOP_0_PANTS");
        pref[0][2].clear();
        pref[0][2].putString("SLOT0", "pants1").flush();
        pref[0][2].putString("SLOT1", "pants2").flush();
        pref[0][2].putString("SLOT2", "pants3").flush();
        pref[0][2].putString("SLOT3", "pants4").flush();

        //Shoes
        pref[0][3] = Gdx.app.getPreferences("SHOP_0_SHOES");
        pref[0][3].clear();
        pref[0][3].putString("SLOT0", "shoes1").flush();
        pref[0][3].putString("SLOT1", "shoes2").flush();
        pref[0][3].putString("SLOT2", "shoes3").flush();

        //Wapons
        pref[0][4] = Gdx.app.getPreferences("SHOP_0_WAPON");
        pref[0][4].clear();
        pref[0][4].putString("SLOT0", "wapons1").flush();
        pref[0][4].putString("SLOT1", "wapons2").flush();
        pref[0][4].putString("SLOT2", "wapons3").flush();
        pref[0][4].putString("SLOT3", "wapons4").flush();
        pref[0][4].putString("SLOT4", "wapons5").flush();
        pref[0][4].putString("SLOT5", "wapons6").flush();
        pref[0][4].putString("SLOT6", "wapons7").flush();
        pref[0][4].putString("SLOT7", "wapons8").flush();

        //Item block
        pref[0][5] = Gdx.app.getPreferences("SHOP_0_ITEM_BLOCK");
        pref[0][5].clear();
        pref[0][5].putString("SLOT0", "tarcza1").flush();
        pref[0][5].putString("SLOT1", "tarcza2").flush();
        pref[0][5].putString("SLOT2", "tarcza3").flush();

        //Item arm
        pref[0][6] = Gdx.app.getPreferences("SHOP_0_ITEM_ARM");
        pref[0][6].clear();
        pref[0][6].putString("SLOT0", "rekawice1").flush();
        pref[0][6].putString("SLOT1", "rekawice2").flush();
        pref[0][6].putString("SLOT2", "rekawice3").flush();
        pref[0][6].putString("SLOT3", "rekawice4").flush();

        //Ring
        pref[0][7] = Gdx.app.getPreferences("SHOP_0_RING");
        pref[0][7].clear();
        pref[0][7].putString("SLOT0", "ring1").flush();
        pref[0][7].putString("SLOT1", "ring2").flush();
        pref[0][7].putString("SLOT2", "ring3").flush();
        pref[0][7].putString("SLOT3", "ring4").flush();
        pref[0][7].putString("SLOT4", "ring5").flush();
    }

    /**
     * SHOP 2
     */
    private void loadSecondShop(){
        //Helmet
        pref[1][0] = Gdx.app.getPreferences("SHOP_1_HELMET");
        pref[1][0].clear();
        pref[1][0].putString("SLOT0", "helmet4").flush();
        pref[1][0].putString("SLOT1", "helmet5").flush();
        pref[1][0].putString("SLOT2", "helmet6").flush();
        pref[1][0].putString("SLOT3", "helmet7").flush();
        pref[1][0].putString("SLOT4", "helmet8").flush();

        //Armor
        pref[1][1] = Gdx.app.getPreferences("SHOP_1_ARMOR");
        pref[1][1].clear();
        pref[1][1].putString("SLOT0", "armor4").flush();
        pref[1][1].putString("SLOT1", "armor5").flush();
        pref[1][1].putString("SLOT2", "armor6").flush();
        pref[1][1].putString("SLOT3", "armor7").flush();
        pref[1][1].putString("SLOT4", "armor8").flush();

        //Pants
        pref[1][2] = Gdx.app.getPreferences("SHOP_1_PANTS");
        pref[1][2].clear();
        pref[1][2].putString("SLOT0", "pants3").flush();
        pref[1][2].putString("SLOT1", "pants4").flush();
        pref[1][2].putString("SLOT2", "pants5").flush();
        pref[1][2].putString("SLOT3", "pants6").flush();

        //Shoes
        pref[1][3] = Gdx.app.getPreferences("SHOP_1_SHOES");
        pref[1][3].clear();
        pref[1][3].putString("SLOT0", "shoes3").flush();
        pref[1][3].putString("SLOT1", "shoes4").flush();
        pref[1][3].putString("SLOT2", "shoes5").flush();
        pref[1][3].putString("SLOT3", "shoes6").flush();

        //Wapons
        pref[1][4] = Gdx.app.getPreferences("SHOP_1_WAPON");
        pref[1][4].clear();
        pref[1][4].putString("SLOT0", "wapons7").flush();
        pref[1][4].putString("SLOT1", "wapons8").flush();
        pref[1][4].putString("SLOT2", "wapons9").flush();
        pref[1][4].putString("SLOT3", "wapons10").flush();
        pref[1][4].putString("SLOT4", "wapons11").flush();
        pref[1][4].putString("SLOT5", "wapons12").flush();
        pref[1][4].putString("SLOT6", "wapons13").flush();
        pref[1][4].putString("SLOT7", "wapons14").flush();

        //Item block
        pref[1][5] = Gdx.app.getPreferences("SHOP_1_ITEM_BLOCK");
        pref[1][5].clear();
        pref[1][5].putString("SLOT0", "tarcza3").flush();
        pref[1][5].putString("SLOT1", "tarcza4").flush();
        pref[1][5].putString("SLOT2", "tarcza5").flush();

        //Item arm
        pref[1][6] = Gdx.app.getPreferences("SHOP_1_ITEM_ARM");
        pref[1][6].clear();
        pref[1][6].putString("SLOT0", "rekawice4").flush();
        pref[1][6].putString("SLOT1", "rekawice5").flush();
        pref[1][6].putString("SLOT2", "rekawice6").flush();
        pref[1][6].putString("SLOT3", "rekawice7").flush();

        //Ring
        pref[1][7] = Gdx.app.getPreferences("SHOP_1_RING");
        pref[1][7].clear();
        pref[1][7].putString("SLOT0", "ring4").flush();
        pref[1][7].putString("SLOT1", "ring5").flush();
        pref[1][7].putString("SLOT2", "ring6").flush();
        pref[1][7].putString("SLOT3", "ring7").flush();
        pref[1][7].putString("SLOT4", "ring8").flush();
    }

    /**
     * SHOP 3
     */
    private void loadThirdShop(){
        //Helmet
        pref[2][0] = Gdx.app.getPreferences("SHOP_2_HELMET");
        pref[2][0].clear();
        pref[2][0].putString("SLOT0", "helmet8").flush();
        pref[2][0].putString("SLOT1", "helmet9").flush();
        pref[2][0].putString("SLOT2", "helmet10").flush();
        pref[2][0].putString("SLOT3", "helmet11").flush();

        //Armor
        pref[2][1] = Gdx.app.getPreferences("SHOP_2_ARMOR");
        pref[2][1].clear();
        pref[2][1].putString("SLOT0", "armor8").flush();
        pref[2][1].putString("SLOT1", "armor9").flush();
        pref[2][1].putString("SLOT2", "armor10").flush();
        pref[2][1].putString("SLOT3", "armor11").flush();
        //pref[2][1].putString("SLOT4", "helmet12").flush();
        //pref[2][1].putString("SLOT8", "helmet2pay").flush();

        //Pants
        pref[2][2] = Gdx.app.getPreferences("SHOP_2_PANTS");
        pref[2][2].clear();
        pref[2][2].putString("SLOT0", "pants5").flush();
        pref[2][2].putString("SLOT1", "pants6").flush();
        pref[2][2].putString("SLOT2", "pants7").flush();
        pref[2][2].putString("SLOT3", "pants8").flush();
        pref[2][2].putString("SLOT4", "pants9").flush();

        //Shoes
        pref[2][3] = Gdx.app.getPreferences("SHOP_2_SHOES");
        pref[2][3].clear();
        pref[2][3].putString("SLOT0", "shoes5").flush();
        pref[2][3].putString("SLOT1", "shoes6").flush();
        pref[2][3].putString("SLOT2", "shoes7").flush();
        pref[2][3].putString("SLOT3", "shoes8").flush();
        pref[2][3].putString("SLOT4", "shoes9").flush();

        //Wapons
        pref[2][4] = Gdx.app.getPreferences("SHOP_2_WAPON");
        pref[2][4].clear();
        pref[2][4].putString("SLOT0", "wapons14").flush();
        pref[2][4].putString("SLOT1", "wapons15").flush();
        pref[2][4].putString("SLOT2", "wapons16").flush();
        pref[2][4].putString("SLOT3", "wapons17").flush();
        pref[2][4].putString("SLOT4", "wapons18").flush();
        pref[2][4].putString("SLOT5", "wapons19").flush();
        pref[2][4].putString("SLOT6", "wapons20").flush();

        //Item block
        pref[2][5] = Gdx.app.getPreferences("SHOP_2_ITEM_BLOCK");
        pref[2][5].clear();
        pref[2][5].putString("SLOT0", "tarcza4").flush();
        pref[2][5].putString("SLOT1", "tarcza5").flush();
        pref[2][5].putString("SLOT2", "tarcza6").flush();

        //Item arm
        pref[2][6] = Gdx.app.getPreferences("SHOP_2_ITEM_ARM");
        pref[2][6].clear();
        pref[2][6].putString("SLOT0", "rekawice6").flush();
        pref[2][6].putString("SLOT1", "rekawice7").flush();
        pref[2][6].putString("SLOT2", "rekawice8").flush();
        pref[2][6].putString("SLOT3", "rekawice9").flush();

        //Ring
        pref[2][7] = Gdx.app.getPreferences("SHOP_2_RING");
        pref[2][7].clear();
        pref[2][7].putString("SLOT0", "ring8").flush();
        pref[2][7].putString("SLOT1", "ring9").flush();
        pref[2][7].putString("SLOT2", "ring10").flush();
        pref[2][7].putString("SLOT3", "ring11").flush();
    }

    /**
     * 4
     */
    private void loadFourthShop(){
        //Helmet
        pref[3][0] = Gdx.app.getPreferences("SHOP_3_HELMET");
        pref[3][0].clear();
        pref[3][0].putString("SLOT0", "helmet10").flush();
        pref[3][0].putString("SLOT1", "helmet11").flush();
        pref[3][0].putString("SLOT2", "helmet12").flush();
        pref[3][0].putString("SLOT3", "helmet13").flush();
        pref[3][0].putString("SLOT4", "helmet14").flush();
        pref[3][0].putString("SLOT5", "helmet15").flush();

        //Armor
        pref[3][1] = Gdx.app.getPreferences("SHOP_3_ARMOR");
        pref[3][1].clear();
        pref[3][1].putString("SLOT0", "armor10").flush();
        pref[3][1].putString("SLOT1", "armor11").flush();
        pref[3][1].putString("SLOT2", "armor12").flush();
        pref[3][1].putString("SLOT3", "armor13").flush();
        pref[3][1].putString("SLOT4", "armor14").flush();
        pref[3][1].putString("SLOT5", "armor15").flush();
        //pref[3][1].putString("SLOT8", "armor5pay").flush();
        //pref[3][1].putString("SLOT9", "armor6pay").flush();

        //Pants
        pref[3][2] = Gdx.app.getPreferences("SHOP_3_PANTS");
        pref[3][2].clear();
        pref[3][2].putString("SLOT0", "pants7").flush();
        pref[3][2].putString("SLOT1", "pants8").flush();
        pref[3][2].putString("SLOT2", "pants9").flush();
        pref[3][2].putString("SLOT3", "pants10").flush();

        //Shoes
        pref[3][3] = Gdx.app.getPreferences("SHOP_3_SHOES");
        pref[3][3].clear();
        pref[3][3].putString("SLOT0", "shoes7").flush();
        pref[3][3].putString("SLOT1", "shoes8").flush();
        pref[3][3].putString("SLOT2", "shoes9").flush();
        pref[3][3].putString("SLOT3", "shoes10").flush();

        //Wapons
        pref[3][4] = Gdx.app.getPreferences("SHOP_3_WAPON");
        pref[3][4].clear();
        pref[3][4].putString("SLOT0", "wapons21").flush();
        pref[3][4].putString("SLOT1", "wapons22").flush();
        pref[3][4].putString("SLOT2", "wapons23").flush();
        pref[3][4].putString("SLOT3", "wapons24").flush();
        pref[3][4].putString("SLOT4", "wapons25").flush();
        pref[3][4].putString("SLOT5", "wapons26").flush();
        pref[3][4].putString("SLOT6", "wapons27").flush();

        //Item block
        pref[3][5] = Gdx.app.getPreferences("SHOP_3_ITEM_BLOCK");
        pref[3][5].clear();
        pref[3][5].putString("SLOT0", "tarcza7").flush();
        pref[3][5].putString("SLOT1", "tarcza8").flush();
        pref[3][5].putString("SLOT2", "tarcza9").flush();

        //Item arm
        pref[3][6] = Gdx.app.getPreferences("SHOP_3_ITEM_ARM");
        pref[3][6].clear();
        pref[3][6].putString("SLOT0", "rekawice9").flush();
        pref[3][6].putString("SLOT1", "rekawice10").flush();
        pref[3][6].putString("SLOT2", "rekawice11").flush();
        pref[3][6].putString("SLOT3", "rekawice12").flush();

        //Ring
        pref[3][7] = Gdx.app.getPreferences("SHOP_3_RING");
        pref[3][7].clear();
        pref[3][7].putString("SLOT0", "ring10").flush();
        pref[3][7].putString("SLOT1", "ring11").flush();
        pref[3][7].putString("SLOT2", "ring12").flush();
        pref[3][7].putString("SLOT3", "ring13").flush();
        pref[3][7].putString("SLOT4", "ring14").flush();
        pref[3][7].putString("SLOT5", "ring15").flush();
    }

    /**
     * 5
     */
    private void loadFivthShop(){
        //Helmet
        pref[4][0] = Gdx.app.getPreferences("SHOP_4_HELMET");
        pref[4][0].clear();
        pref[4][0].putString("SLOT0", "helmet14").flush();
        pref[4][0].putString("SLOT1", "helmet15").flush();
        pref[4][0].putString("SLOT2", "helmet16").flush();
        pref[4][0].putString("SLOT3", "helmet17").flush();
        pref[4][0].putString("SLOT4", "helmet18").flush();

        //Armor
        pref[4][1] = Gdx.app.getPreferences("SHOP_4_ARMOR");
        pref[4][1].clear();
        pref[4][1].putString("SLOT0", "armor14").flush();
        pref[4][1].putString("SLOT1", "armor15").flush();
        pref[4][1].putString("SLOT2", "armor16").flush();
        pref[4][1].putString("SLOT3", "armor17").flush();
        pref[4][1].putString("SLOT4", "armor18").flush();
        //pref[4][1].putString("SLOT8", "armor6pay").flush();
        //pref[4][1].putString("SLOT9", "armor7pay").flush();

        //Pants
        pref[4][2] = Gdx.app.getPreferences("SHOP_4_PANTS");
        pref[4][2].clear();
        pref[4][2].putString("SLOT0", "pants10").flush();
        pref[4][2].putString("SLOT1", "pants11").flush();
        pref[4][2].putString("SLOT2", "pants12").flush();
        pref[4][2].putString("SLOT3", "pants13").flush();

        //Shoes
        pref[4][3] = Gdx.app.getPreferences("SHOP_4_SHOES");
        pref[4][3].clear();
        pref[4][3].putString("SLOT0", "shoes10").flush();
        pref[4][3].putString("SLOT1", "shoes11").flush();
        pref[4][3].putString("SLOT2", "shoes12").flush();
        pref[4][3].putString("SLOT3", "shoes13").flush();

        //Wapons
        pref[4][4] = Gdx.app.getPreferences("SHOP_4_WAPON");
        pref[4][4].clear();
        pref[4][4].putString("SLOT0", "wapons25").flush();
        pref[4][4].putString("SLOT1", "wapons26").flush();
        pref[4][4].putString("SLOT2", "wapons27").flush();
        pref[4][4].putString("SLOT3", "wapons28").flush();
        pref[4][4].putString("SLOT4", "wapons29").flush();
        pref[4][4].putString("SLOT5", "wapons30").flush();

        //Item block
        pref[4][5] = Gdx.app.getPreferences("SHOP_4_ITEM_BLOCK");
        pref[4][5].clear();
        pref[4][5].putString("SLOT0", "tarcza8").flush();
        pref[4][5].putString("SLOT1", "tarcza9").flush();
        pref[4][5].putString("SLOT2", "tarcza10").flush();
        pref[4][5].putString("SLOT3", "tarcza11").flush();

        //Item arm
        pref[4][6] = Gdx.app.getPreferences("SHOP_4_ITEM_ARM");
        pref[4][6].clear();
        pref[4][6].putString("SLOT0", "rekawice12").flush();
        pref[4][6].putString("SLOT1", "rekawice13").flush();
        pref[4][6].putString("SLOT2", "rekawice14").flush();
        pref[4][6].putString("SLOT3", "rekawice15").flush();

        //Ring
        pref[4][7] = Gdx.app.getPreferences("SHOP_4_RING");
        pref[4][7].clear();
        pref[4][7].putString("SLOT1", "ring14").flush();
        pref[4][7].putString("SLOT2", "ring15").flush();
        pref[4][7].putString("SLOT3", "ring16").flush();
        pref[4][7].putString("SLOT4", "ring17").flush();
        pref[4][7].putString("SLOT5", "ring18").flush();
    }

    private void loadSixthShop(){
        //Helmet
        pref[5][0] = Gdx.app.getPreferences("SHOP_5_HELMET");
        pref[5][0].clear();
        pref[5][0].putString("SLOT0", "helmet17").flush();
        pref[5][0].putString("SLOT1", "helmet18").flush();
        pref[5][0].putString("SLOT2", "helmet19").flush();
        pref[5][0].putString("SLOT2", "helmet20").flush();
        pref[5][0].putString("SLOT2", "helmet21").flush();

        //Armor
        pref[5][1] = Gdx.app.getPreferences("SHOP_5_ARMOR");
        pref[5][1].clear();
        pref[5][1].putString("SLOT0", "armor17").flush();
        pref[5][1].putString("SLOT1", "armor18").flush();
        pref[5][1].putString("SLOT2", "armor19").flush();
        pref[5][1].putString("SLOT3", "armor20").flush();
        pref[5][1].putString("SLOT4", "armor21").flush();
        //pref[5][1].putString("SLOT8", "armor7pay").flush();

        //Pants
        pref[5][2] = Gdx.app.getPreferences("SHOP_5_PANTS");
        pref[5][2].clear();
        pref[5][2].putString("SLOT0", "pants12").flush();
        pref[5][2].putString("SLOT1", "pants13").flush();
        pref[5][2].putString("SLOT2", "pants14").flush();
        pref[5][2].putString("SLOT3", "pants15").flush();

        //Shoes
        pref[5][3] = Gdx.app.getPreferences("SHOP_5_SHOES");
        pref[5][3].clear();
        pref[5][3].putString("SLOT0", "shoes12").flush();
        pref[5][3].putString("SLOT1", "shoes13").flush();
        pref[5][3].putString("SLOT2", "shoes14").flush();
        pref[5][3].putString("SLOT3", "shoes15").flush();

        //Wapons
        pref[5][4] = Gdx.app.getPreferences("SHOP_5_WAPON");
        pref[5][4].clear();
        pref[5][4].putString("SLOT0", "wapons28").flush();
        pref[5][4].putString("SLOT1", "wapons29").flush();
        pref[5][4].putString("SLOT2", "wapons30").flush();
        pref[5][4].putString("SLOT3", "wapons31").flush();
        pref[5][4].putString("SLOT4", "wapons32").flush();
        pref[5][4].putString("SLOT5", "wapons33").flush();
        pref[5][4].putString("SLOT6", "wapons34").flush();

        //Item block
        pref[5][5] = Gdx.app.getPreferences("SHOP_5_ITEM_BLOCK");
        pref[5][5].clear();
        pref[5][5].putString("SLOT0", "tarcza9").flush();
        pref[5][5].putString("SLOT1", "tarcza10").flush();
        pref[5][5].putString("SLOT2", "tarcza11").flush();
        pref[5][5].putString("SLOT2", "tarcza12").flush();

        //Item arm
        pref[5][6] = Gdx.app.getPreferences("SHOP_5_ITEM_ARM");
        pref[5][6].clear();
        pref[5][6].putString("SLOT0", "rekawice14").flush();
        pref[5][6].putString("SLOT1", "rekawice15").flush();
        pref[5][6].putString("SLOT2", "rekawice16").flush();
        pref[5][6].putString("SLOT3", "rekawice17").flush();

        //Ring
        pref[5][7] = Gdx.app.getPreferences("SHOP_5_RING");
        pref[5][7].clear();
        pref[5][7].putString("SLOT0", "ring17").flush();
        pref[5][7].putString("SLOT1", "ring18").flush();
        pref[5][7].putString("SLOT2", "ring19").flush();
        pref[5][7].putString("SLOT3", "ring20").flush();
        pref[5][7].putString("SLOT4", "ring21").flush();
    }

    public static String[] getItemsFromDepartament(int indexShop, int indexDepartament){
        String itemName;

        for(int i = 0; i < items.length; i++){
            itemName = pref[indexShop][indexDepartament].getString("SLOT" +i, "");
            items[i] = itemName;
        }

        return items;
    }
}