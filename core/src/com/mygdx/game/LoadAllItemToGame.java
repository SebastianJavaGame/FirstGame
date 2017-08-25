package com.mygdx.game;

import java.util.HashMap;

/**
 * Created by Sebastian on 2017-06-29.
 */

public class LoadAllItemToGame {
    public static final HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    public static final void loadItems(){
        /**
         * Wapons free
         */
        ITEMS.put("wapons1", new Item("wapons1", "Items/wapons/1.png", "Sagal", Item.ItemType.WAPON, 1,         10, 1, 1, 0, 0, 0, 100));
        ITEMS.put("wapons2", new Item("wapons2", "Items/wapons/2.png", "Grav", Item.ItemType.WAPON, 4,          10, 4, 1, 0, 1, 1, 600)); //8
        ITEMS.put("wapons3", new Item("wapons3", "Items/wapons/3.png", "Astev", Item.ItemType.WAPON, 6,         20, 6, 2, 0, 2, 1, 1100)); //13
        ITEMS.put("wapons4", new Item("wapons4", "Items/wapons/4.png", "Drecarparis", Item.ItemType.WAPON, 8,   20, 7, 4, 0.1f, 2, 2, 2000));//18
        ITEMS.put("wapons5", new Item("wapons5", "Items/wapons/5.png", "Tsanemi", Item.ItemType.WAPON, 10,      20, 10, 5, 0.1f, 3, 3, 3000)); //25
        ITEMS.put("wapons6", new Item("wapons6", "Items/wapons/6.png", "Nart", Item.ItemType.WAPON, 12,         20, 11, 6, 0.1f, 4, 4, 4500));//28
        ITEMS.put("wapons7", new Item("wapons7", "Items/wapons/7.png", "Skalfar", Item.ItemType.WAPON, 15,      30, 13, 8, 0.1f, 5, 4, 7000));  //34
        ITEMS.put("wapons8", new Item("wapons8", "Items/wapons/8.png", "Arebla", Item.ItemType.WAPON, 18,       40, 15, 8, 0.2f, 5, 4, 12500));//38
        ITEMS.put("wapons9", new Item("wapons9", "Items/wapons/9.png", "Degarlim", Item.ItemType.WAPON, 20,     50, 15, 10, 0.3f, 6, 5, 18000)); //44
        ITEMS.put("wapons10", new Item("wapons10", "Items/wapons/10.png", "Istev", Item.ItemType.WAPON, 22,     50, 15, 12, 0.5f, 6, 5, 25000)); //48
        ITEMS.put("wapons11", new Item("wapons11", "Items/wapons/11.png", "Smaga", Item.ItemType.WAPON, 24,     50, 17, 14, 0.6f, 6, 6, 35000));//54
        ITEMS.put("wapons12", new Item("wapons12", "Items/wapons/12.png", "Tehla", Item.ItemType.WAPON, 25,     50, 18, 15, 0.6f, 6, 7, 40000)); //57
        ITEMS.put("wapons13", new Item("wapons13", "Items/wapons/13.png", "Tirhall", Item.ItemType.WAPON, 26,   60, 20, 16, 0.6f, 7, 8, 45000));//63
        ITEMS.put("wapons14", new Item("wapons14", "Items/wapons/14.png", "Piroklest", Item.ItemType.WAPON, 28, 60, 23, 17, 0.7f, 8, 8, 55000));//69
        ITEMS.put("wapons15", new Item("wapons15", "Items/wapons/15.png", "Gaomorph", Item.ItemType.WAPON, 30,  80, 24, 18, 0.8f, 8, 8, 65500)); //74
        ITEMS.put("wapons16", new Item("wapons16", "Items/wapons/16.png", "Xanether", Item.ItemType.WAPON, 32,  80, 25, 21, 0.8f, 8, 9, 72000));//79
        ITEMS.put("wapons17", new Item("wapons17", "Items/wapons/17.png", "Devgrator", Item.ItemType.WAPON, 35, 90, 26, 22, 0.8f, 8, 9, 85000)); //82
        ITEMS.put("wapons18", new Item("wapons18", "Items/wapons/18.png", "Ishalm", Item.ItemType.WAPON, 37,    100, 26, 24, 0.9f, 9, 9, 95000));//87
        ITEMS.put("wapons19", new Item("wapons19", "Items/wapons/19.png", "Virrel", Item.ItemType.WAPON, 38,    100, 30, 25, 0.9f, 10, 10, 100000)); //94
        ITEMS.put("wapons20", new Item("wapons20", "Items/wapons/20.png", "Falgar", Item.ItemType.WAPON, 42,    110, 32, 27, 0.9f, 10, 10, 115000));//99
        ITEMS.put("wapons21", new Item("wapons21", "Items/wapons/21.png", "Skogen", Item.ItemType.WAPON, 44,    110, 34, 29, 1f, 10, 10, 135000));//104
        ITEMS.put("wapons22", new Item("wapons22", "Items/wapons/22.png", "Avgans", Item.ItemType.WAPON, 45,    120, 36, 32, 1f, 10, 10, 170000)); //110
        ITEMS.put("wapons23", new Item("wapons23", "Items/wapons/23.png", "Nevigan", Item.ItemType.WAPON, 48,   120, 38, 34, 1f, 11, 10, 200000));//115
        ITEMS.put("wapons24", new Item("wapons24", "Items/wapons/24.png", "Brunval", Item.ItemType.WAPON, 50,   140, 40, 35, 1f, 11, 10, 250000)); //120
        ITEMS.put("wapons25", new Item("wapons25", "Items/wapons/25.png", "Verlah", Item.ItemType.WAPON, 52,    140, 41, 38, 1f, 11, 11, 300000));//125
        ITEMS.put("wapons26", new Item("wapons26", "Items/wapons/26.png", "Gjalmer", Item.ItemType.WAPON, 55,   150, 43, 39, 1f, 12, 11, 365000)); //130
        ITEMS.put("wapons27", new Item("wapons27", "Items/wapons/27.png", "Engvelian", Item.ItemType.WAPON, 57, 160, 45, 40, 1.1f, 12, 11, 430000));//135
        ITEMS.put("wapons28", new Item("wapons28", "Items/wapons/28.png", "Berteur", Item.ItemType.WAPON, 60,   160, 47, 42, 1.1f, 12, 13, 480000)); //140
        ITEMS.put("wapons29", new Item("wapons29", "Items/wapons/29.png", "Selenoram", Item.ItemType.WAPON, 64, 160, 48, 43, 1.2f, 13, 13, 550000));//145
        ITEMS.put("wapons30", new Item("wapons30", "Items/wapons/30.png", "Dmarlang", Item.ItemType.WAPON, 67,  170, 50, 45, 1.2f, 13, 13, 620000)); //150
        ITEMS.put("wapons31", new Item("wapons31", "Items/wapons/31.png", "Vastablin", Item.ItemType.WAPON, 70, 180, 53, 48, 1.2f, 13, 13, 700000)); //155
        ITEMS.put("wapons32", new Item("wapons32", "Items/wapons/32.png", "Exaviam", Item.ItemType.WAPON, 72,   180, 54, 48, 1.3f, 14, 14, 800000));//160
        ITEMS.put("wapons33", new Item("wapons33", "Items/wapons/33.png", "Promaris", Item.ItemType.WAPON, 75,  190, 54, 49, 1.4f, 15, 29, 900000)); //165
        ITEMS.put("wapons34", new Item("wapons34", "Items/wapons/34.png", "Dertham", Item.ItemType.WAPON, 80,   200, 55, 50, 1.5f, 15, 15, 1000000)); //170
//TODO -50%
        /**
         * Wapons pay
         */
        //ITEMS.put("wapons1pay", new Item("wapons1pay", "Items/wapons/1pay.png", "Grom", Item.ItemType.WAPON, 5,           20, 10, 5, 0.2f, 3, 3, 0.30));
        //ITEMS.put("wapons2pay", new Item("wapons2pay", "Items/wapons/2pay.png", "Isgeraptor", Item.ItemType.WAPON, 12,    50, 15, 10, 0.4f, 6, 5, 0.45));
        //ITEMS.put("wapons3pay", new Item("wapons3pay", "Items/wapons/3pay.png", "Killamur", Item.ItemType.WAPON, 18,      50, 17, 14, 0.6f, 6, 6, 0.60));
        //ITEMS.put("wapons4pay", new Item("wapons4pay", "Items/wapons/4pay.png", "Panaris", Item.ItemType.WAPON, 25,       80, 24, 19, 0.8f, 8, 8, 0.80));
        //ITEMS.put("wapons5pay", new Item("wapons5pay", "Items/wapons/5pay.png", "Zgon", Item.ItemType.WAPON, 32,          100, 30, 25, 1f, 10, 10, 1.00));
        //ITEMS.put("wapons6pay", new Item("wapons6pay", "Items/wapons/6pay.png", "Htegen", Item.ItemType.WAPON, 36,        110, 36, 27, 1f, 10, 10, 1.20));
        //ITEMS.put("wapons7pay", new Item("wapons7pay", "Items/wapons/7pay.png", "Santrion", Item.ItemType.WAPON, 40,      120, 36, 32, 1.1f, 10, 10, 1.50));
        //ITEMS.put("wapons8pay", new Item("wapons8pay", "Items/wapons/8pay.png", "Ból", Item.ItemType.WAPON, 45,           140, 41, 38, 1,3f, 11, 11, 1.90));
        //ITEMS.put("wapons9pay", new Item("wapons9pay", "Items/wapons/9pay.png", "Strach", Item.ItemType.WAPON, 50,        160, 35, 40, 1,4f, 12, 12, 2.30));
        //ITEMS.put("wapons10pay", new Item("wapons10pay", "Items/wapons/10pay.png", "Errable", Item.ItemType.WAPON, 55,    160, 48, 43, 1,5f, 13, 14, 2.90));
        //ITEMS.put("wapons11pay", new Item("wapons11pay", "Items/wapons/11pay.png", "Cierń", Item.ItemType.WAPON, 60,      180, 53, 37, 1,5f, 15, 15, 3.50));
        //ITEMS.put("wapons12pay", new Item("wapons12pay", "Items/wapons/12pay.png", "Morran", Item.ItemType.WAPON, 65,     230, 58, 55, 1,8f, 18, 18, 4.90));

        /**
         * Armor
         */
        ITEMS.put("armor1", new Item("armor1", "Items/armor/1.png", "heldren", Item.ItemType.ARMOR, 1,          10, 0, 0, 0.1f, 0, 1, 100));         //3
        ITEMS.put("armor2", new Item("armor2", "Items/armor/2.png", "pusk", Item.ItemType.ARMOR, 5,             10, 1, 0, 0.3f, 3, 2, 800));         //10
        ITEMS.put("armor3", new Item("armor3", "Items/armor/3.png", "ajnes", Item.ItemType.ARMOR, 10,           30, 3, 2, 0.5f, 6, 5, 2800));       //24
        ITEMS.put("armor4", new Item("armor4", "Items/armor/4.png", "kaldras", Item.ItemType.ARMOR, 15,         50, 4, 3, 0.7f, 8, 7, 6700));     //34
        ITEMS.put("armor5", new Item("armor5", "Items/armor/5.png", "arabol", Item.ItemType.ARMOR, 20,          80, 5, 4, 1f, 9, 8, 17000));    //44
        ITEMS.put("armor6", new Item("armor6", "Items/armor/6.png", "vadium", Item.ItemType.ARMOR, 25,          110, 5, 5, 1.4f, 12, 10, 36500));  //57
        ITEMS.put("armor7", new Item("armor7", "Items/armor/7.png", "dabbe", Item.ItemType.ARMOR, 29,           150, 6, 6, 1.9f, 15, 13, 65000));  //73
        ITEMS.put("armor8", new Item("armor8", "Items/armor/8.png", "Azter", Item.ItemType.ARMOR, 32,           160, 7, 6, 2f, 16, 13, 65000));  //77
        ITEMS.put("armor9", new Item("armor9", "Items/armor/9.png", "voldron", Item.ItemType.ARMOR, 35,         170, 7, 6, 2.2f, 17, 15, 88000));  //83
        ITEMS.put("armor10", new Item("armor10", "Items/armor/10.png", "Kapon", Item.ItemType.ARMOR, 40,        190, 7, 6, 2.5f, 20, 18, 110000)); //94
        ITEMS.put("armor11", new Item("armor11", "Items/armor/11.png", "teahell", Item.ItemType.ARMOR, 43,      200, 8, 7, 2.6f, 21, 19, 110000)); //100
        ITEMS.put("armor12", new Item("armor12", "Items/armor/12.png", "arnsal", Item.ItemType.ARMOR, 46,       220, 7, 8, 2.9f, 23, 20, 160000));  //108
        ITEMS.put("armor13", new Item("armor13", "Items/armor/13.png", "ceretris", Item.ItemType.ARMOR, 50,     250, 7, 8, 3.2f, 24, 22, 230000));  //117
        ITEMS.put("armor14", new Item("armor14", "Items/armor/14.png", "manpur", Item.ItemType.ARMOR, 54,       270, 8, 9, 3.5f, 26, 24, 350000));  //128
        ITEMS.put("armor15", new Item("armor15", "Items/armor/15.png", "Orinal", Item.ItemType.ARMOR, 57,       280, 9, 9, 3.7f, 26, 25, 350000));  //133
        ITEMS.put("armor16", new Item("armor16", "Items/armor/16.png", "maendra", Item.ItemType.ARMOR, 60,      290, 9, 9, 3.7f, 27, 26, 450000));  //136
        ITEMS.put("armor17", new Item("armor17", "Items/armor/17.png", "Zagreb", Item.ItemType.ARMOR, 64,       310, 10, 9, 4.0f, 28, 26, 575000));  //143
        ITEMS.put("armor18", new Item("armor18", "Items/armor/18.png", "varlin", Item.ItemType.ARMOR, 68,       320, 9, 9, 4.1f, 28, 28, 575000));  //148
        ITEMS.put("armor19", new Item("armor19", "Items/armor/19.png", "gheiterog", Item.ItemType.ARMOR, 71,    330, 10, 10, 4.2f, 29, 29, 700000));  //154
        ITEMS.put("armor20", new Item("armor20", "Items/armor/20.png", "kaliertare", Item.ItemType.ARMOR, 75,   350, 11, 12, 4.5f, 31, 30, 850000));  //165
        ITEMS.put("armor21", new Item("armor21", "Items/armor/21.png", "isgur", Item.ItemType.ARMOR, 80,        380, 12, 12, 4.8f, 32, 32, 1000000));  //175

        /**
         * Armor pay
         */
        //ITEMS.put("armor1pay", new Item("armor1pay", "Items/armor/1pay.png", "Mager", Item.ItemType.ARMOR, 15,      110, 6, 5, 1.4f, 12, 10, 0.40));
        //ITEMS.put("armor2pay", new Item("armor2pay", "Items/armor/2pay.png", "vay", Item.ItemType.ARMOR, 21,        160, 6, 6, 2.0f, 16, 13, 0.60));
        //ITEMS.put("armor3pay", new Item("armor3pay", "Items/armor/3pay.png", "varthall", Item.ItemType.ARMOR, 25,   180, 7, 6, 2.2f, 17, 16, 0.80));
        //ITEMS.put("armor4pay", new Item("armor4pay", "Items/armor/4pay.png", "kalleres", Item.ItemType.ARMOR, 36,   230, 8, 8, 3f, 24, 22, 1.20));
        //ITEMS.put("armor5pay", new Item("armor5pay", "Items/armor/5pay.png", "Vorg", Item.ItemType.ARMOR, 45,       280, 8, 8, 3.5f, 26, 24, 1.60));
        //ITEMS.put("armor6pay", new Item("armor6pay", "Items/armor/6pay.png", "agonia", Item.ItemType.ARMOR, 50,     330, 10, 11, 4.4f, 28, 28, 2.25));
        //ITEMS.put("armor7pay", new Item("armor7pay", "Items/armor/7pay.png", "xergen", Item.ItemType.ARMOR, 65,     400, 15, 15, 5.8f, 33, 33, 3.90));

        /**
         * Helmet
         */
        ITEMS.put("helmet1", new Item("helmet1", "Items/helmet/1.png", "Opaska", Item.ItemType.HELMET, 1,             0, 0, 0, 0.1f, 1, 1, 100)); //3
        ITEMS.put("helmet2", new Item("helmet2", "Items/helmet/2.png", "Hato", Item.ItemType.HELMET, 3,              10, 1, 1, 0.2f, 2, 2, 600)); //9
        ITEMS.put("helmet3", new Item("helmet3", "Items/helmet/3.png", "Lena", Item.ItemType.HELMET, 6,             20, 2, 1, 0.2f, 4, 3, 1100)); //14
        ITEMS.put("helmet4", new Item("helmet4", "Items/helmet/4.png", "Hoodlin", Item.ItemType.HELMET, 10,             30, 3, 2, 0.4f, 7, 5, 3100)); //24
        ITEMS.put("helmet5", new Item("helmet5", "Items/helmet/5.png", "Magon", Item.ItemType.HELMET, 16,             60, 5, 5, 0.5f, 8, 6, 7500)); //34
        ITEMS.put("helmet6", new Item("helmet6", "Items/helmet/6.png", "Maramus", Item.ItemType.HELMET, 20,             70, 6, 7, 0.7f, 10, 7, 16500)); //43
        ITEMS.put("helmet7", new Item("helmet7", "Items/helmet/7.png", "rolax", Item.ItemType.HELMET, 22,            80, 6, 7, 0.9f, 11, 8, 24000));  //48
        ITEMS.put("helmet8", new Item("helmet8", "Items/helmet/8.png", "tesek", Item.ItemType.HELMET, 27,            120, 10, 8, 1f, 13, 11, 44000)); //63
        ITEMS.put("helmet9", new Item("helmet9", "Items/helmet/9.png", "mellasthegan", Item.ItemType.HELMET, 30,            150, 11, 9, 1.2f, 15, 13, 60000));  //74
        ITEMS.put("helmet10", new Item("helmet10", "Items/helmet/10.png", "redias", Item.ItemType.HELMET, 36,            180, 12, 11, 1.4f, 18, 17, 90000));  //89
        ITEMS.put("helmet11", new Item("helmet11", "Items/helmet/11.png", "keldarn", Item.ItemType.HELMET, 40,            200, 12, 11, 1.5f, 19, 18, 100000));  //94
        ITEMS.put("helmet12", new Item("helmet12", "Items/helmet/12.png", "geotaur", Item.ItemType.HELMET, 46,            240, 13, 12, 1.9f, 22, 22, 180000));  //111
        ITEMS.put("helmet13", new Item("helmet13", "Items/helmet/13.png", "irkhaml", Item.ItemType.HELMET, 51,            260, 13, 12, 2.2f, 24, 23, 260000));  //119
        ITEMS.put("helmet14", new Item("helmet14", "Items/helmet/14.png", "isvard", Item.ItemType.HELMET, 55,          280, 13, 13, 2.4f, 25, 24, 350000));  //126
        ITEMS.put("helmet15", new Item("helmet15", "Items/helmet/15.png", "marsarry", Item.ItemType.HELMET, 62,          300, 14, 14, 2.7f, 27, 27, 525000));  //138
        ITEMS.put("helmet16", new Item("helmet16", "Items/helmet/16.png", "irkemla", Item.ItemType.HELMET, 70,          330, 15, 15, 3.1f, 30, 28, 700000));  //151
        ITEMS.put("helmet17", new Item("helmet17", "Items/helmet/17.png", "gerthill", Item.ItemType.HELMET, 80,          350, 16, 16, 3.4f, 32, 32, 1000000)); //164

        /**
         * Helmet pay
         */
        //ITEMS.put("helmet1pay", new Item("helmet1pay", "Items/helmet/1pay.png", "Iskra", Item.ItemType.HELMET, 20,          150, 11, 9, 1.2f, 15, 13, 0.50));
        //ITEMS.put("helmet2pay", new Item("helmet2pay", "Items/helmet/2pay.png", "Kres", Item.ItemType.HELMET, 30,          240, 13, 12, 1.9f, 22, 22, 0.90));
        //ITEMS.put("helmet3pay", new Item("helmet3pay", "Items/helmet/3pay.png", "Bargon", Item.ItemType.HELMET, 44,          300, 13, 13, 2.5f, 25, 26, 1.90));
        //ITEMS.put("helmet4pay", new Item("helmet4pay", "Items/helmet/4pay.png", "Valdren", Item.ItemType.HELMET, 60,          350, 16, 16, 3.5f, 33, 33, 2.90));


        /**
         * Pants
         */
        ITEMS.put("pants1", new Item("pants1", "Items/pants/1.png", "Portki", Item.ItemType.PANTS, 1,             0, 1, 1, 0.0f, 1, 0, 100)); //3
        ITEMS.put("pants2", new Item("pants2", "Items/pants/2.png", "Bony", Item.ItemType.PANTS, 7,             30, 1, 2, 0.2f, 2, 3, 100));  //13
        ITEMS.put("pants3", new Item("pants3", "Items/pants/3.png", "lyskmery", Item.ItemType.PANTS, 15,           60, 5, 6, 0.5f, 5, 7, 100)); //34
        ITEMS.put("pants4", new Item("pants4", "Items/pants/4.png", "Olny", Item.ItemType.PANTS, 22,             90, 8, 9, 0.8f, 9, 11, 25000)); //54
        ITEMS.put("pants5", new Item("pants5", "Items/pants/5.png", "lystry", Item.ItemType.PANTS, 27,             100, 10, 12, 0.9f, 12, 13, 7000)); //66
        ITEMS.put("pants6", new Item("pants6", "Items/pants/6.png", "vengsy", Item.ItemType.PANTS, 31,             140, 13, 14, 1.0f, 12, 13, 62000));//76
        ITEMS.put("pants7", new Item("pants7", "Items/pants/7.png", "biltebendary", Item.ItemType.PANTS, 38,         150, 14, 15, 1.1f, 12, 14, 62000));//81
        ITEMS.put("pants8", new Item("pants8", "Items/pants/8.png", "hadrasy", Item.ItemType.PANTS, 45,             190, 17, 19, 1.9f, 17, 21, 100000));//112
        ITEMS.put("pants9", new Item("pants9", "Items/pants/9.png", "vaekeny", Item.ItemType.PANTS, 55,             220, 21, 23, 1.8f, 21, 25, 350000));//130
        ITEMS.put("pants10", new Item("pants10", "Items/pants/10.png", "tengvery", Item.ItemType.PANTS, 58,             240, 23, 25, 2.0f, 22, 26, 445000));//140
        ITEMS.put("pants11", new Item("pants11", "Items/pants/11.png", "beressery", Item.ItemType.PANTS, 65,             250, 25, 26, 2.3f, 24, 27, 600000));//150
        ITEMS.put("pants12", new Item("pants12", "Items/pants/12.png", "tharimmy", Item.ItemType.PANTS, 75,             280, 27, 28, 2.5f, 27, 29, 850000));//164
        ITEMS.put("pants13", new Item("pants13", "Items/pants/13.png", "Nony", Item.ItemType.PANTS, 80,             290, 28, 30, 2.5f, 29, 30, 1000000));  //171

        /**
         * Pants pay
         */
        //ITEMS.put("pants1pay", new Item("pants1pay", "Items/pants/1pay.png", "Magny", Item.ItemType.PANTS, 38,             230, 21, 23, 2.0f, 20, 25, 1.90));
        //ITEMS.put("pants2pay", new Item("pants2pay", "Items/pants/2pay.png", "frexy", Item.ItemType.PANTS, 64,             290, 27, 30, 2.5f, 29, 30, 2.90));

        /**
         * Rekawice
         */
        ITEMS.put("rekawice1", new Item("rekawice1", "Items/rekawice/1.png", "alandry", Item.ItemType.ITEM_HAND, 1,              10, 0, 1, 0.0f, 1, 0, 100));//3
        ITEMS.put("rekawice2", new Item("rekawice2", "Items/rekawice/2.png", "pezary", Item.ItemType.ITEM_HAND, 6,              30, 1, 3, 0.0f, 3, 4, 1000));       //14
        ITEMS.put("rekawice3", new Item("rekawice3", "Items/rekawice/3.png", "baerieny", Item.ItemType.ITEM_HAND, 10,             50, 3, 4, 0.1f, 5, 6, 3100));      //24
        ITEMS.put("rekawice4", new Item("rekawice4", "Items/rekawice/4.png", "zedry", Item.ItemType.ITEM_HAND, 16,             60, 5, 8, 0.1f, 8, 9, 8500));   //38
        ITEMS.put("rekawice5", new Item("rekawice5", "Items/rekawice/5.png", "biasegi", Item.ItemType.ITEM_HAND, 22,             100, 8, 10, 0.2f, 9, 10, 24000));  //50
        ITEMS.put("rekawice6", new Item("rekawice6", "Items/rekawice/6.png", "Barcel", Item.ItemType.ITEM_HAND, 28,             140, 12, 13, 0.3f, 12, 14, 50000));  //69
        ITEMS.put("rekawice7", new Item("rekawice7", "Items/rekawice/7.png", "faldry", Item.ItemType.ITEM_HAND, 34,             180, 14, 15, 0.4f, 14, 15, 82000));  //81
        ITEMS.put("rekawice8", new Item("rekawice8", "Items/rekawice/8.png", "aquerisy", Item.ItemType.ITEM_HAND, 40,             220, 17, 18, 0.5f, 17, 17, 105000));  //97
        ITEMS.put("rekawice9", new Item("rekawice9", "Items/rekawice/9.png", "Kemsy", Item.ItemType.ITEM_HAND, 45,             250, 19, 19, 0.7f, 18, 19, 160000));  //108
        ITEMS.put("rekawice10", new Item("rekawice10", "Items/rekawice/10.png", "szpony", Item.ItemType.ITEM_HAND, 49,             260, 20, 22, 0.8f, 18, 21, 160000));  //116
        ITEMS.put("rekawice11", new Item("rekawice11", "Items/rekawice/11.png", "Mogny", Item.ItemType.ITEM_HAND, 54,          270, 22, 24, 0.9f, 20, 22, 360000));  //125
        ITEMS.put("rekawice12", new Item("rekawice12", "Items/rekawice/12.png", "Grovy", Item.ItemType.ITEM_HAND, 61,          300, 23, 27, 1f, 21, 24, 360000));  //136
        ITEMS.put("rekawice13", new Item("rekawice13", "Items/rekawice/13.png", "udreki", Item.ItemType.ITEM_HAND, 65,          320, 25, 27, 1.2f, 23, 25, 600000));  //145
        ITEMS.put("rekawice14", new Item("rekawice14", "Items/rekawice/14.png", "veldreny", Item.ItemType.ITEM_HAND, 75,          350, 27, 30, 1.6f, 25, 27, 890000));  //161

        /**
         * Rekawice pay
         */
        //ITEMS.put("rekawice1pay", new Item("rekawice1pay", "Items/rekawice/1pay.png", "Grensy", Item.ItemType.ITEM_HAND, 24,          230, 17, 18, 0.5f, 17, 17, 0.90));  //325
        //ITEMS.put("rekawice2pay", new Item("rekawice2pay", "Items/rekawice/2pay.png", "Tharimy", Item.ItemType.ITEM_HAND, 42,          280, 22, 28, 0.9f, 20, 22, 1.90));  //325
        //ITEMS.put("rekawice3pay", new Item("rekawice3pay", "Items/rekawice/3pay.png", "Verrvy", Item.ItemType.ITEM_HAND, 58,          350, 27, 30, 1.8f, 25, 27, 2.90));  //325

        /**
         * Ring
         */
        ITEMS.put("ring1", new Item("ring1", "Items/ring/1.png", "karten", Item.ItemType.RING, 1,               0, 0, 1, 0.0f, 0, 2, 100));//3
        ITEMS.put("ring2", new Item("ring2", "Items/ring/2.png", "haragan", Item.ItemType.RING, 5,               10, 1, 4, 0.0f, 1, 4, 750));       //11
        ITEMS.put("ring3", new Item("ring3", "Items/ring/3.png", "tella", Item.ItemType.RING, 10,              40, 4, 6, 0.1f, 3, 7, 2900));     //25
        ITEMS.put("ring4", new Item("ring4", "Items/ring/4.png", "ajnes", Item.ItemType.RING, 14,              50, 5, 7, 0.1f, 5, 8, 6200));   //32
        ITEMS.put("ring5", new Item("ring5", "Items/ring/5.png", "belest", Item.ItemType.RING, 19,              80, 6, 9, 0.1f, 7, 9, 16000));  //41
        ITEMS.put("ring6", new Item("ring6", "Items/ring/6.png", "baraesz", Item.ItemType.RING, 25,              120, 9, 13, 0.1f, 9, 14, 38500));  //59
        ITEMS.put("ring7", new Item("ring7", "Items/ring/7.png", "hamaris", Item.ItemType.RING, 31,              170, 12, 16, 0.1f, 12, 17, 70000));  //75
        ITEMS.put("ring8", new Item("ring8", "Items/ring/8.png", "Bool", Item.ItemType.RING, 36,              200, 14, 18, 0.1f, 13, 19, 90000));  //85
        ITEMS.put("ring9", new Item("ring9", "Items/ring/9.png", "khelem", Item.ItemType.RING, 39,              210, 15, 18, 0.1f, 14, 20, 90000));  //89
        ITEMS.put("ring10", new Item("ring10", "Items/ring/10.png", "nirt", Item.ItemType.RING, 42,              250, 18, 22, 0.2f, 16, 22, 110000));  //105
        ITEMS.put("ring11", new Item("ring11", "Items/ring/11.png", "Blask", Item.ItemType.RING, 45,           280, 20, 23, 0.2f, 17, 23, 170000));  //113
        ITEMS.put("ring12", new Item("ring12", "Items/ring/12.png", "allakaj", Item.ItemType.RING, 48,           290, 22, 24, 0.2f, 18, 25, 170000));  //120
        ITEMS.put("ring13", new Item("ring13", "Items/ring/13.png", "kerepeks", Item.ItemType.RING, 53,           320, 22, 25, 0.3f, 19, 27, 310000));  //128
        ITEMS.put("ring14", new Item("ring14", "Items/ring/14.png", "narthill", Item.ItemType.RING, 56,           340, 24, 26, 0.3f, 21, 28, 375000));  //136
        ITEMS.put("ring15", new Item("ring15", "Items/ring/15.png", "sphaera", Item.ItemType.RING, 60,           370, 26, 28, 0.3f, 22, 29, 475000));  //145
        ITEMS.put("ring16", new Item("ring16", "Items/ring/16.png", "szhaol", Item.ItemType.RING, 63,           380, 27, 28, 0.4f, 23, 30, 520000)); //150
        ITEMS.put("ring17", new Item("ring17", "Items/ring/17.png", "merkehm", Item.ItemType.RING, 69,           400, 28, 30, 0.5f, 23, 32, 680000));  //158
        ITEMS.put("ring18", new Item("ring18", "Items/ring/18.png", "wachar", Item.ItemType.RING, 74,           430, 29, 31, 0.6f, 24, 34, 860000));  //167
        ITEMS.put("ring19", new Item("ring19", "Items/ring/19.png", "velezen", Item.ItemType.RING, 80,           450, 30, 33, 0.8f, 25, 35, 1000000));  //176

        /**
         * Ring pay
         */
        //ITEMS.put("ring1pay", new Item("ring1pay", "Items/ring/1pay.png", "skra", Item.ItemType.RING, 20,              170, 12, 16, 0.3f, 12, 17, 0.60));
        //ITEMS.put("ring2pay", new Item("ring2pay", "Items/ring/2pay.png", "Carpon", Item.ItemType.RING, 34,              250, 18, 22, 0.3f, 16, 23, 1.10));
        //ITEMS.put("ring3pay", new Item("ring3pay", "Items/ring/3pay.png", "kriaring", Item.ItemType.RING, 47,              370, 26, 28, 0.3f, 22, 29, 1.90));
        //ITEMS.put("ring4pay", new Item("ring4pay", "Items/ring/4pay.png", "eatarus", Item.ItemType.RING, 60,              430, 29, 31, 0.6f, 24, 33, 2.90));

        /**
         * Shoes
         */
        ITEMS.put("shoes1", new Item("shoes1", "Items/shoes/1.png", "Batamury", Item.ItemType.SHOES, 1,            10, 1, 0, 0.1f, 0, 0, 100));//3
        ITEMS.put("shoes2", new Item("shoes2", "Items/shoes/2.png", "sideny", Item.ItemType.SHOES, 7,            50, 3, 1, 0.3f, 3, 1, 1500)); //16
        ITEMS.put("shoes3", new Item("shoes3", "Items/shoes/3.png", "Iburasy", Item.ItemType.SHOES, 15,           70, 6, 3, 0.7f, 6, 3, 7200)); //31
        ITEMS.put("shoes4", new Item("shoes4", "Items/shoes/4.png", "tekki", Item.ItemType.SHOES, 23,           110, 9, 5, 1.0f, 10, 4, 30000)); //48
        ITEMS.put("shoes5", new Item("shoes4", "Items/shoes/5.png", "Wovki", Item.ItemType.SHOES, 27,            130, 9, 8, 1.1f, 10, 8, 45000)); //58
        ITEMS.put("shoes6", new Item("shoes6", "Items/shoes/6.png", "salany", Item.ItemType.SHOES, 30,           160, 14, 7, 1.6f, 14, 6, 66000)); //76
        ITEMS.put("shoes7", new Item("shoes7", "Items/shoes/7.png", "Viroklesy", Item.ItemType.SHOES, 36,           180, 14, 9, 1.6f, 17, 8, 92000)); //85
        ITEMS.put("shoes8", new Item("shoes8", "Items/shoes/8.png", "hetakamby", Item.ItemType.SHOES, 45,           230, 21, 13, 2.0f, 18, 10, 175000)); //108
        ITEMS.put("shoes9", new Item("shoes9", "Items/shoes/9.png", "sidveny", Item.ItemType.SHOES, 52,           250, 24, 16, 2.2f, 20, 14, 300000)); //124
        ITEMS.put("shoes10", new Item("shoes10", "Items/shoes/10.png", "iskremly", Item.ItemType.SHOES, 60,           270, 25, 18, 2.7f, 23, 17, 500000)); //139
        ITEMS.put("shoes11", new Item("shoes11", "Items/shoes/11.png", "Vartrogi", Item.ItemType.SHOES, 66,        290, 26, 19, 2.9f, 24, 19, 600000)); //148
        ITEMS.put("shoes12", new Item("shoes12", "Items/shoes/12.png", "itrimsy", Item.ItemType.SHOES, 72,        330, 30, 20, 3.3f, 28, 20, 820000)); //166

        /**
         * Shoes pay
         */
        //ITEMS.put("shoes1pay", new Item("shoes1pay", "Items/shoes/1pay.png", "Zigsy", Item.ItemType.SHOES, 30,           230, 22, 13, 2.0f, 19, 10, 0.90));
        //ITEMS.put("shoes2pay", new Item("shoes2pay", "Items/shoes/2pay.png", "Morgi", Item.ItemType.SHOES, 48,           280, 27, 18, 2.8f, 23, 18, 1.90));
        //ITEMS.put("shoes3pay", new Item("shoes3pay", "Items/shoes/3pay.png", "Holmy", Item.ItemType.SHOES, 62,           340, 30, 20, 3.5f, 28, 21, 2.90));

        /**
         * Tarcza
         */
        ITEMS.put("tarcza1", new Item("tarcza1", "Items/tarcza/1.png", "ragur", Item.ItemType.ITEM_BLOCK, 1,             0, 0, 0, 0.1f, 1, 1, 100));//3
        ITEMS.put("tarcza2", new Item("tarcza2", "Items/tarcza/2.png", "karas", Item.ItemType.ITEM_BLOCK, 7,             10, 3, 2, 0.4f, 3, 3, 100)); //16
        ITEMS.put("tarcza3", new Item("tarcza3", "Items/tarcza/3.png", "piroscape", Item.ItemType.ITEM_BLOCK, 15,            20, 5, 4, 0.8f, 8, 7, 100)); //34
        ITEMS.put("tarcza4", new Item("tarcza4", "Items/tarcza/4.png", "davtagar", Item.ItemType.ITEM_BLOCK, 23,            30, 8, 7, 1.3f, 11, 10, 100)); //52
        ITEMS.put("tarcza5", new Item("tarcza5", "Items/tarcza/5.png", "ildern", Item.ItemType.ITEM_BLOCK, 30,            50, 11, 9, 1.9f, 16, 15, 100)); //75
        ITEMS.put("tarcza6", new Item("tarcza6", "Items/tarcza/6.png", "arcapang", Item.ItemType.ITEM_BLOCK, 36,            80, 11, 10, 2.3f, 20, 20, 100)); //92
        ITEMS.put("tarcza7", new Item("tarcza7", "Items/tarcza/7.png", "krapkas", Item.ItemType.ITEM_BLOCK, 45,            80, 14, 12, 2.9f, 23, 24, 100));  //109
        ITEMS.put("tarcza8", new Item("tarcza8", "Items/tarcza/8.png", "reffas", Item.ItemType.ITEM_BLOCK, 52,            100, 16, 14, 3.5f, 24, 25, 100));  //125
        ITEMS.put("tarcza9", new Item("tarcza9", "Items/tarcza/9.png", "immurtal", Item.ItemType.ITEM_BLOCK, 60,            130, 17, 16, 4.0f, 26, 28, 100));  //141
        ITEMS.put("tarcza10", new Item("tarcza10", "Items/tarcza/10.png", "helmverd", Item.ItemType.ITEM_BLOCK, 66,         140, 17, 16, 4.4f, 28, 29, 100)); //149
        ITEMS.put("tarcza11", new Item("tarcza11", "Items/tarcza/11.png", "angwasev", Item.ItemType.ITEM_BLOCK, 72,         160, 18, 18, 5.0f, 30, 30, 100));//163

        /**
         * Tarcza pay
         */
        //ITEMS.put("tarcza1pay", new Item("tarcza1pay", "Items/tarcza/1pay.png", "anasileus", Item.ItemType.ITEM_BLOCK, 57,   180, 18, 18, 5.3f, 31, 31, 2.40));
    }

    public static Item getItem(String key) throws CloneNotSupportedException {
        return ITEMS.get(key).clone();
    }
}
