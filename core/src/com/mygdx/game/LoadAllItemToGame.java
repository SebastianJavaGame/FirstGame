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
        ITEMS.put("wapons1", new Item("wapons1", "Items/wapons/1.png", "Sagal", Item.ItemType.WAPON, 1,         10, 3, 1, 0, 0, 0, 100));
        ITEMS.put("wapons2", new Item("wapons2", "Items/wapons/2.png", "Grav", Item.ItemType.WAPON, 4,          20, 7, 2, 0, 2, 1, 600)); //15
        ITEMS.put("wapons3", new Item("wapons3", "Items/wapons/3.png", "Astev", Item.ItemType.WAPON, 6,         30, 12, 5, 0, 3, 2, 1100)); //25
        ITEMS.put("wapons4", new Item("wapons4", "Items/wapons/4.png", "Drecarparis", Item.ItemType.WAPON, 8,   40, 15, 8, 0.1f, 4, 3, 2000));
        ITEMS.put("wapons5", new Item("wapons5", "Items/wapons/5.png", "Tsanemi", Item.ItemType.WAPON, 10,      40, 19, 10, 0.1f, 5, 6, 3000)); //45
        ITEMS.put("wapons6", new Item("wapons6", "Items/wapons/6.png", "Nart", Item.ItemType.WAPON, 12,         40, 23, 12, 0.2f, 7, 7, 4500));
        ITEMS.put("wapons7", new Item("wapons7", "Items/wapons/7.png", "Skalfar", Item.ItemType.WAPON, 15,      60, 26, 15, 0.2f, 9, 7, 7000));  //65
        ITEMS.put("wapons8", new Item("wapons8", "Items/wapons/8.png", "Arebla", Item.ItemType.WAPON, 18,       70, 30, 16, 0.4f, 10, 8, 12500));
        ITEMS.put("wapons9", new Item("wapons9", "Items/wapons/9.png", "Degarlim", Item.ItemType.WAPON, 20,     90, 30, 20, 0.5f, 12, 9, 18000));   //85
        ITEMS.put("wapons10", new Item("wapons10", "Items/wapons/10.png", "Istev", Item.ItemType.WAPON, 22,     100, 30, 24, 0.9f, 12, 10, 25000)); //95
        ITEMS.put("wapons11", new Item("wapons11", "Items/wapons/11.png", "Smaga", Item.ItemType.WAPON, 24,     100, 34, 27, 1.1f, 12, 11, 35000));
        ITEMS.put("wapons12", new Item("wapons12", "Items/wapons/12.png", "Tehla", Item.ItemType.WAPON, 25,     100, 37, 30, 1.2f, 12, 14, 40000)); //115
        ITEMS.put("wapons13", new Item("wapons13", "Items/wapons/13.png", "Tirhall", Item.ItemType.WAPON, 26,   120, 40, 32, 1.2f, 14, 15, 45000));
        ITEMS.put("wapons14", new Item("wapons14", "Items/wapons/14.png", "Piroklest", Item.ItemType.WAPON, 28, 120, 45, 34, 1.4f, 15, 15, 55000));
        ITEMS.put("wapons15", new Item("wapons15", "Items/wapons/15.png", "Gaomorph", Item.ItemType.WAPON, 30,  150, 48, 37, 1.5f, 15, 16, 65500)); //145
        ITEMS.put("wapons16", new Item("wapons16", "Items/wapons/16.png", "Xanether", Item.ItemType.WAPON, 32,  160, 50, 41, 1.6f, 16, 17, 72000));
        ITEMS.put("wapons17", new Item("wapons17", "Items/wapons/17.png", "Devgrator", Item.ItemType.WAPON, 35, 180, 54, 44, 1.6f, 16, 18, 85000)); //165
        ITEMS.put("wapons18", new Item("wapons18", "Items/wapons/18.png", "Ishalm", Item.ItemType.WAPON, 37,    200, 56, 48, 1.7f, 17, 18, 95000));
        ITEMS.put("wapons19", new Item("wapons19", "Items/wapons/19.png", "Virrel", Item.ItemType.WAPON, 38,    200, 60, 50, 1.8f, 19, 19, 100000)); //185
        ITEMS.put("wapons20", new Item("wapons20", "Items/wapons/20.png", "Falgar", Item.ItemType.WAPON, 42,    210, 65, 53, 1.8f, 20, 19, 115000));
        ITEMS.put("wapons21", new Item("wapons21", "Items/wapons/21.png", "Skogen", Item.ItemType.WAPON, 44,    210, 68, 58, 1.9f, 20, 20, 135000));
        ITEMS.put("wapons22", new Item("wapons22", "Items/wapons/22.png", "Avgans", Item.ItemType.WAPON, 45,    230, 72, 64, 1.9f, 20, 20, 170000)); //215
        ITEMS.put("wapons23", new Item("wapons23", "Items/wapons/23.png", "Nevigan", Item.ItemType.WAPON, 48,   240, 75, 68, 1.9f, 22, 20, 200000));
        ITEMS.put("wapons24", new Item("wapons24", "Items/wapons/24.png", "Brunval", Item.ItemType.WAPON, 50,   270, 80, 70, 1.9f, 22, 20, 250000)); //235
        ITEMS.put("wapons25", new Item("wapons25", "Items/wapons/25.png", "Verlah", Item.ItemType.WAPON, 52,    280, 82, 75, 2.0f, 22, 21, 300000));
        ITEMS.put("wapons26", new Item("wapons26", "Items/wapons/26.png", "Gjalmer", Item.ItemType.WAPON, 55,   300, 85, 78, 2.0f, 23, 22, 365000)); //255
        ITEMS.put("wapons27", new Item("wapons27", "Items/wapons/27.png", "Engvelian", Item.ItemType.WAPON, 57, 310, 90, 80, 2.1f, 24, 22, 430000));
        ITEMS.put("wapons28", new Item("wapons28", "Items/wapons/28.png", "Berteur", Item.ItemType.WAPON, 60,   310, 94, 83, 2.1f, 24, 25, 480000)); //275
        ITEMS.put("wapons29", new Item("wapons29", "Items/wapons/29.png", "Selenoram", Item.ItemType.WAPON, 64, 320, 95, 86, 2.4f, 26, 25, 550000));
        ITEMS.put("wapons30", new Item("wapons30", "Items/wapons/30.png", "Dmarlang", Item.ItemType.WAPON, 67,  330, 100, 90, 2.4f, 26, 25, 620000)); //295
        ITEMS.put("wapons31", new Item("wapons31", "Items/wapons/31.png", "Vastablin", Item.ItemType.WAPON, 70, 330, 105, 94, 2.4f, 26, 25, 700000)); //305
        ITEMS.put("wapons32", new Item("wapons32", "Items/wapons/32.png", "Exaviam", Item.ItemType.WAPON, 72,   350, 107, 95, 2.5f, 28, 27, 800000));
        ITEMS.put("wapons33", new Item("wapons33", "Items/wapons/33.png", "Promaris", Item.ItemType.WAPON, 75,  370, 108, 97, 2.7f, 30, 29, 900000)); //325
        ITEMS.put("wapons34", new Item("wapons34", "Items/wapons/34.png", "Dertham", Item.ItemType.WAPON, 80,   400, 110, 100, 3.0f, 30, 30, 1000000)); //335

        /**
         * Wapons pay
         */
        //ITEMS.put("wapons1pay", new Item("wapons1pay", "Items/wapons/1pay.png", "Grom", Item.ItemType.WAPON, 5,           40, 19, 10, 0.3f, 5, 6, 0.30));
        //ITEMS.put("wapons2pay", new Item("wapons2pay", "Items/wapons/2pay.png", "Isgeraptor", Item.ItemType.WAPON, 12,    90, 30, 20, 0.7f, 12, 9, 0.45));
        //ITEMS.put("wapons3pay", new Item("wapons3pay", "Items/wapons/3pay.png", "Killamur", Item.ItemType.WAPON, 18,      100, 34, 27, 1.2f, 12, 11, 0.60));
        //ITEMS.put("wapons4pay", new Item("wapons4pay", "Items/wapons/4pay.png", "Panaris", Item.ItemType.WAPON, 25,       150, 48, 37, 1.6f, 15, 16, 0.80));
        //ITEMS.put("wapons5pay", new Item("wapons5pay", "Items/wapons/5pay.png", "Zgon", Item.ItemType.WAPON, 32,          200, 60, 50, 1.9f, 19, 19, 1.00));
        //ITEMS.put("wapons6pay", new Item("wapons6pay", "Items/wapons/6pay.png", "Htegen", Item.ItemType.WAPON, 36,        210, 65, 53, 2.0f, 20, 19, 1.20));
        //ITEMS.put("wapons7pay", new Item("wapons7pay", "Items/wapons/7pay.png", "Santrion", Item.ItemType.WAPON, 40,      230, 72, 64, 2.2f, 20, 20, 1.50));
        //ITEMS.put("wapons8pay", new Item("wapons8pay", "Items/wapons/8pay.png", "Ból", Item.ItemType.WAPON, 45,           280, 82, 75, 2.5f, 22, 22, 1.90));
        //ITEMS.put("wapons9pay", new Item("wapons9pay", "Items/wapons/9pay.png", "Strach", Item.ItemType.WAPON, 50,        310, 90, 80, 2.7f, 24, 24, 2.30));
        //ITEMS.put("wapons10pay", new Item("wapons10pay", "Items/wapons/10pay.png", "Errable", Item.ItemType.WAPON, 55,    320, 95, 86, 2.9f, 26, 28, 2.90));
        //ITEMS.put("wapons11pay", new Item("wapons11pay", "Items/wapons/11pay.png", "Cierń", Item.ItemType.WAPON, 60,      350, 105, 94, 3.0f, 29, 29, 3.50));
        //ITEMS.put("wapons12pay", new Item("wapons12pay", "Items/wapons/12pay.png", "Morran", Item.ItemType.WAPON, 65,     450, 115, 110, 3.5f, 35, 35, 4.90));

        /**
         * Armor
         */
        ITEMS.put("armor1", new Item("armor1", "Items/armor/1.png", "heldren", Item.ItemType.ARMOR, 1,          10, 0, 0, 0.2f, 2, 2, 100));         //7
        ITEMS.put("armor2", new Item("armor2", "Items/armor/2.png", "pusk", Item.ItemType.ARMOR, 5,             20, 1, 0, 0.5f, 5, 4, 800));         //17
        ITEMS.put("armor3", new Item("armor3", "Items/armor/3.png", "ajnes", Item.ItemType.ARMOR, 10,           60, 5, 3, 1.0f, 12, 9, 2800));       //45
        ITEMS.put("armor4", new Item("armor4", "Items/armor/4.png", "kaldras", Item.ItemType.ARMOR, 15,         100, 7, 5, 1.4f, 16, 13, 6700));     //65
        ITEMS.put("armor5", new Item("armor5", "Items/armor/5.png", "arabol", Item.ItemType.ARMOR, 20,          160, 9, 7, 2.0f, 18, 15, 17000));    //85
        ITEMS.put("armor6", new Item("armor6", "Items/armor/6.png", "vadium", Item.ItemType.ARMOR, 25,          220, 11, 10, 2.8f, 24, 20, 36500));  //115
        ITEMS.put("armor7", new Item("armor7", "Items/armor/7.png", "dabbe", Item.ItemType.ARMOR, 29,           300, 12, 11, 3.8f, 29, 25, 65000));  //145
        ITEMS.put("armor8", new Item("armor8", "Items/armor/8.png", "Azter", Item.ItemType.ARMOR, 32,           320, 13, 12, 4.0f, 32, 26, 65000));  //155
        ITEMS.put("armor9", new Item("armor9", "Items/armor/9.png", "voldron", Item.ItemType.ARMOR, 35,         340, 13, 11, 4.3f, 34, 30, 88000));  //165
        ITEMS.put("armor10", new Item("armor10", "Items/armor/10.png", "Kapon", Item.ItemType.ARMOR, 40,        380, 14, 12, 5.0f, 40, 36, 110000)); //190
        ITEMS.put("armor11", new Item("armor11", "Items/armor/11.png", "teahell", Item.ItemType.ARMOR, 43,      400, 15, 13, 5.2f, 42, 38, 110000)); //200
        ITEMS.put("armor12", new Item("armor12", "Items/armor/12.png", "arnsal", Item.ItemType.ARMOR, 46,       440, 14, 14, 5.7f, 46, 40, 160000));  //215
        ITEMS.put("armor13", new Item("armor13", "Items/armor/13.png", "ceretris", Item.ItemType.ARMOR, 50,     500, 14, 15, 6.4f, 48, 44, 230000));  //235
        ITEMS.put("armor14", new Item("armor14", "Items/armor/14.png", "manpur", Item.ItemType.ARMOR, 54,       540, 16, 17, 7.0f, 51, 47, 350000));  //255
        ITEMS.put("armor15", new Item("armor15", "Items/armor/15.png", "Orinal", Item.ItemType.ARMOR, 57,       550, 17, 18, 7.4f, 52, 49, 350000));  //265
        ITEMS.put("armor16", new Item("armor16", "Items/armor/16.png", "maendra", Item.ItemType.ARMOR, 60,      580, 17, 18, 7.6f, 53, 52, 450000));  //275
        ITEMS.put("armor17", new Item("armor17", "Items/armor/17.png", "Zagreb", Item.ItemType.ARMOR, 64,       620, 19, 18, 8.0f, 55, 55, 575000));  //290
        ITEMS.put("armor18", new Item("armor18", "Items/armor/18.png", "varlin", Item.ItemType.ARMOR, 68,       640, 19, 19, 8.2f, 57, 57, 575000));  //299
        ITEMS.put("armor19", new Item("armor19", "Items/armor/19.png", "gheiterog", Item.ItemType.ARMOR, 71,    650, 20, 20, 8.4f, 58, 57, 700000));  //305
        ITEMS.put("armor20", new Item("armor20", "Items/armor/20.png", "kaliertare", Item.ItemType.ARMOR, 75,   700, 22, 21, 9.0f, 62, 60, 850000));  //327
        ITEMS.put("armor21", new Item("armor21", "Items/armor/21.png", "isgur", Item.ItemType.ARMOR, 80,        800, 25, 25, 10.0f, 65, 65, 1000000));  //??

        /**
         * Armor pay
         */
        //ITEMS.put("armor1pay", new Item("armor1pay", "Items/armor/1pay.png", "Mager", Item.ItemType.ARMOR, 15,      220, 11, 10, 2.8f, 24, 20, 0.40));
        //ITEMS.put("armor2pay", new Item("armor2pay", "Items/armor/2pay.png", "vay", Item.ItemType.ARMOR, 21,        320, 12, 12, 4.0f, 32, 25, 0.60));
        //ITEMS.put("armor3pay", new Item("armor3pay", "Items/armor/3pay.png", "varthall", Item.ItemType.ARMOR, 25,   350, 13, 11, 4.3f, 36, 32, 0.80));
        //ITEMS.put("armor4pay", new Item("armor4pay", "Items/armor/4pay.png", "kalleres", Item.ItemType.ARMOR, 36,   450, 15, 15, 5.9f, 48, 43, 1.20));
        //ITEMS.put("armor5pay", new Item("armor5pay", "Items/armor/5pay.png", "Vorg", Item.ItemType.ARMOR, 45,       550, 16, 17, 7.0f, 51, 48, 1.60));
        //ITEMS.put("armor6pay", new Item("armor6pay", "Items/armor/6pay.png", "agonia", Item.ItemType.ARMOR, 50,     650, 20, 22, 8.8f, 55, 55, 2.25));
        //ITEMS.put("armor7pay", new Item("armor7pay", "Items/armor/7pay.png", "xergen", Item.ItemType.ARMOR, 65,     850, 30, 30, 11.5f, 65, 65, 3.90));

        /**
         * Helmet
         */
        ITEMS.put("helmet1", new Item("helmet1", "Items/helmet/1.png", "Opaska", Item.ItemType.HELMET, 1,             0, 0, 0, 0.1f, 2, 2, 100)); //5
        ITEMS.put("helmet2", new Item("helmet2", "Items/helmet/2.png", "Hato", Item.ItemType.HELMET, 3,              20, 1, 1, 0.3f, 4, 3, 600)); //15
        ITEMS.put("helmet3", new Item("helmet3", "Items/helmet/3.png", "Lena", Item.ItemType.HELMET, 6,             30, 4, 2, 0.4f, 7, 5, 1100)); //25
        ITEMS.put("helmet4", new Item("helmet4", "Items/helmet/4.png", "Hoodlin", Item.ItemType.HELMET, 10,             60, 6, 4, 0.7f, 13, 9, 3100)); //45
        ITEMS.put("helmet5", new Item("helmet5", "Items/helmet/5.png", "Magon", Item.ItemType.HELMET, 16,             110, 10, 10, 1.0f, 18, 11, 7500)); //70
        ITEMS.put("helmet6", new Item("helmet6", "Items/helmet/6.png", "Maramus", Item.ItemType.HELMET, 20,             130, 12, 14, 1.3f, 19, 14, 16500)); //85
        ITEMS.put("helmet7", new Item("helmet7", "Items/helmet/7.png", "rolax", Item.ItemType.HELMET, 22,            160, 12, 14, 1.7f, 21, 15, 24000));  //95
        ITEMS.put("helmet8", new Item("helmet8", "Items/helmet/8.png", "tesek", Item.ItemType.HELMET, 27,            230, 19, 16, 2.0f, 25, 22, 44000)); //125
        ITEMS.put("helmet9", new Item("helmet9", "Items/helmet/9.png", "mellasthegan", Item.ItemType.HELMET, 30,            290, 21, 18, 2.3f, 29, 25, 60000));  //145
        ITEMS.put("helmet10", new Item("helmet10", "Items/helmet/10.png", "redias", Item.ItemType.HELMET, 36,            360, 23, 21, 2.7f, 35, 33, 90000));  //175
        ITEMS.put("helmet11", new Item("helmet11", "Items/helmet/11.png", "keldarn", Item.ItemType.HELMET, 40,            400, 23, 22, 3.0f, 38, 37, 100000));  //190
        ITEMS.put("helmet12", new Item("helmet12", "Items/helmet/12.png", "geotaur", Item.ItemType.HELMET, 46,            480, 25, 24, 3.8f, 43, 43, 180000));  //220
        ITEMS.put("helmet13", new Item("helmet13", "Items/helmet/13.png", "irkhaml", Item.ItemType.HELMET, 51,            520, 25, 24, 4.3f, 47, 45, 260000));  //235
        ITEMS.put("helmet14", new Item("helmet14", "Items/helmet/14.png", "isvard", Item.ItemType.HELMET, 55,          550, 26, 25, 4.8f, 49, 48, 350000));  //255
        ITEMS.put("helmet15", new Item("helmet15", "Items/helmet/15.png", "marsarry", Item.ItemType.HELMET, 62,          600, 27, 27, 5.5f, 54, 53, 525000));  //280
        ITEMS.put("helmet16", new Item("helmet16", "Items/helmet/16.png", "irkemla", Item.ItemType.HELMET, 70,          650, 29, 29, 6.2f, 60, 56, 700000));  //305
        ITEMS.put("helmet17", new Item("helmet17", "Items/helmet/17.png", "gerthill", Item.ItemType.HELMET, 80,          700, 32, 32, 7.0f, 65, 65, 1000000)); //335

        /**
         * Helmet pay
         */
        //ITEMS.put("helmet1pay", new Item("helmet1pay", "Items/helmet/1pay.png", "Iskra", Item.ItemType.HELMET, 20,          300, 21, 18, 2.3f, 30, 25, 0.50));
        //ITEMS.put("helmet2pay", new Item("helmet2pay", "Items/helmet/2pay.png", "Kres", Item.ItemType.HELMET, 30,          480, 25, 24, 3.8f, 43, 43, 0.90));
        //ITEMS.put("helmet3pay", new Item("helmet3pay", "Items/helmet/3pay.png", "Bargon", Item.ItemType.HELMET, 44,          600, 25, 25, 5.0f, 50, 52, 1.90));
        //ITEMS.put("helmet4pay", new Item("helmet4pay", "Items/helmet/4pay.png", "Valdren", Item.ItemType.HELMET, 60,          700, 35, 35, 7.0f, 65, 65, 2.90));


        /**
         * Pants
         */
        ITEMS.put("pants1", new Item("pants1", "Items/pants/1.png", "Portki", Item.ItemType.PANTS, 1,             0, 1, 1, 0.0f, 1, 2, 100)); //5
        ITEMS.put("pants2", new Item("pants2", "Items/pants/2.png", "Bony", Item.ItemType.PANTS, 7,             60, 1, 3, 1.0f, 4, 6, 100));  //30
        ITEMS.put("pants3", new Item("pants3", "Items/pants/3.png", "lyskmery", Item.ItemType.PANTS, 15,           130, 11, 14, 1.4f, 14, 19, 100)); //65
        ITEMS.put("pants4", new Item("pants4", "Items/pants/4.png", "Olny", Item.ItemType.PANTS, 22,             170, 15, 18, 1.6f, 17, 22, 25000)); //95
        ITEMS.put("pants5", new Item("pants5", "Items/pants/5.png", "lystry", Item.ItemType.PANTS, 27,             200, 19, 23, 1.8f, 23, 26, 7000)); //130
        ITEMS.put("pants6", new Item("pants6", "Items/pants/6.png", "vengsy", Item.ItemType.PANTS, 31,             270, 25, 28, 2.0f, 23, 26, 62000));
        ITEMS.put("pants7", new Item("pants7", "Items/pants/7.png", "biltebendary", Item.ItemType.PANTS, 38,         300, 28, 30, 2.2f, 24, 27, 62000));
        ITEMS.put("pants8", new Item("pants8", "Items/pants/8.png", "hadrasy", Item.ItemType.PANTS, 45,             370, 35, 39, 2.8f, 34, 42, 100000));
        ITEMS.put("pants9", new Item("pants9", "Items/pants/9.png", "vaekeny", Item.ItemType.PANTS, 55,             430, 41, 45, 3.6f, 41, 50, 350000));
        ITEMS.put("pants10", new Item("pants10", "Items/pants/10.png", "tengvery", Item.ItemType.PANTS, 58,             480, 45, 49, 4.0f, 44, 52, 445000));
        ITEMS.put("pants11", new Item("pants11", "Items/pants/11.png", "beressery", Item.ItemType.PANTS, 65,             500, 50, 52, 4.5f, 47, 53, 600000));
        ITEMS.put("pants12", new Item("pants12", "Items/pants/12.png", "tharimmy", Item.ItemType.PANTS, 75,             550, 55, 56, 5.0f, 56, 58, 850000));
        ITEMS.put("pants13", new Item("pants13", "Items/pants/13.png", "Nony", Item.ItemType.PANTS, 80,             580, 56, 60, 5.0f, 58, 60, 1000000));  //335

        /**
         * Pants pay
         */
        //ITEMS.put("pants1pay", new Item("pants1pay", "Items/pants/1pay.png", "Magny", Item.ItemType.PANTS, 38,             450, 42, 45, 4.0f, 40, 50, 1.90));
        //ITEMS.put("pants2pay", new Item("pants2pay", "Items/pants/2pay.png", "frexy", Item.ItemType.PANTS, 64,             580, 56, 60, 5.0f, 58, 60, 2.90));

        /**
         * Rekawice
         */
        ITEMS.put("rekawice1", new Item("rekawice1", "Items/rekawice/1.png", "alandry", Item.ItemType.HAND_ITEM, 1,              10, 0, 1, 0.0f, 1, 2, 100));
        ITEMS.put("rekawice2", new Item("rekawice2", "Items/rekawice/2.png", "pezary", Item.ItemType.HAND_ITEM, 6,              50, 3, 5, 0.0f, 5, 7, 1000));       //25
        ITEMS.put("rekawice3", new Item("rekawice3", "Items/rekawice/3.png", "baerieny", Item.ItemType.HAND_ITEM, 10,             90, 5, 9, 0.1f, 9, 12, 3100));      //45
        ITEMS.put("rekawice4", new Item("rekawice4", "Items/rekawice/4.png", "zedry", Item.ItemType.HAND_ITEM, 16,             110, 9, 16, 0.2f, 15, 17, 8500));   //70
        ITEMS.put("rekawice5", new Item("rekawice5", "Items/rekawice/5.png", "biasegi", Item.ItemType.HAND_ITEM, 22,             190, 17, 20, 0.3f, 17, 19, 24000));  //95
        ITEMS.put("rekawice6", new Item("rekawice6", "Items/rekawice/6.png", "Barcel", Item.ItemType.HAND_ITEM, 28,             280, 24, 26, 0.5f, 24, 27, 50000));  //135
        ITEMS.put("rekawice7", new Item("rekawice7", "Items/rekawice/7.png", "faldry", Item.ItemType.HAND_ITEM, 34,             360, 28, 30, 0.7f, 28, 30, 82000));  //160
        ITEMS.put("rekawice8", new Item("rekawice8", "Items/rekawice/8.png", "aquerisy", Item.ItemType.HAND_ITEM, 40,             440, 33, 36, 1.0f, 33, 33, 105000));  //190
        ITEMS.put("rekawice9", new Item("rekawice9", "Items/rekawice/9.png", "Kemsy", Item.ItemType.HAND_ITEM, 45,             500, 38, 39, 1.4f, 35, 37, 160000));  //215
        ITEMS.put("rekawice10", new Item("rekawice10", "Items/rekawice/10.png", "szpony", Item.ItemType.HAND_ITEM, 49,             520, 40, 44, 1.5f, 36, 41, 160000));  //230
        ITEMS.put("rekawice11", new Item("rekawice11", "Items/rekawice/11.png", "Mogny", Item.ItemType.HAND_ITEM, 54,          560, 43, 47, 1.8f, 40, 44, 360000));  //250
        ITEMS.put("rekawice12", new Item("rekawice12", "Items/rekawice/12.png", "Grovy", Item.ItemType.HAND_ITEM, 61,          600, 45, 53, 2.0f, 42, 48, 360000));  //270
        ITEMS.put("rekawice13", new Item("rekawice13", "Items/rekawice/13.png", "udreki", Item.ItemType.HAND_ITEM, 65,          640, 49, 56, 2.4f, 46, 49, 600000));  //290
        ITEMS.put("rekawice14", new Item("rekawice14", "Items/rekawice/14.png", "veldreny", Item.ItemType.HAND_ITEM, 75,          700, 55, 60, 3.2f, 50, 55, 890000));  //325

        /**
         * Rekawice pay
         */
        //ITEMS.put("rekawice1pay", new Item("rekawice1pay", "Items/rekawice/1pay.png", "Grensy", Item.ItemType.HAND_ITEM, 24,          450, 33, 36, 1.0f, 33, 33, 0.90));  //325
        //ITEMS.put("rekawice2pay", new Item("rekawice2pay", "Items/rekawice/2pay.png", "Tharimy", Item.ItemType.HAND_ITEM, 42,          560, 43, 47, 1.8f, 40, 44, 1.90));  //325
        //ITEMS.put("rekawice3pay", new Item("rekawice3pay", "Items/rekawice/3pay.png", "Verrvy", Item.ItemType.HAND_ITEM, 58,          700, 55, 60, 3.5f, 50, 55, 2.90));  //325

        /**
         * Ring
         */
        ITEMS.put("ring1", new Item("ring1", "Items/ring/1.png", "karten", Item.ItemType.RING, 1,               0, 0, 2, 0.0f, 0, 3, 100));
        ITEMS.put("ring2", new Item("ring2", "Items/ring/2.png", "haragan", Item.ItemType.RING, 5,               20, 2, 7, 0.0f, 2, 7, 750));       //20
        ITEMS.put("ring3", new Item("ring3", "Items/ring/3.png", "tella", Item.ItemType.RING, 10,              70, 7, 11, 0.1f, 6, 13, 2900));     //45
        ITEMS.put("ring4", new Item("ring4", "Items/ring/4.png", "ajnes", Item.ItemType.RING, 14,              100, 10, 14, 0.1f, 9, 16, 6200));   //60
        ITEMS.put("ring5", new Item("ring5", "Items/ring/5.png", "belest", Item.ItemType.RING, 19,              160, 12, 18, 0.2f, 13, 19, 16000));  //80
        ITEMS.put("ring6", new Item("ring6", "Items/ring/6.png", "baraesz", Item.ItemType.RING, 25,              240, 17, 25, 0.2f, 18, 28, 38500));  //115
        ITEMS.put("ring7", new Item("ring7", "Items/ring/7.png", "hamaris", Item.ItemType.RING, 31,              330, 24, 31, 0.3f, 24, 34, 70000));  //150
        ITEMS.put("ring8", new Item("ring8", "Items/ring/8.png", "Bool", Item.ItemType.RING, 36,              400, 29, 35, 0.2f, 26, 38, 90000));  //170
        ITEMS.put("ring9", new Item("ring9", "Items/ring/9.png", "khelem", Item.ItemType.RING, 39,              420, 30, 37, 0.2f, 29, 40, 90000));  //180
        ITEMS.put("ring10", new Item("ring10", "Items/ring/10.png", "nirt", Item.ItemType.RING, 42,              490, 36, 43, 0.4f, 31, 44, 110000));  //195
        ITEMS.put("ring11", new Item("ring11", "Items/ring/11.png", "Blask", Item.ItemType.RING, 45,           570, 40, 45, 0.4f, 34, 47, 170000));  //215
        ITEMS.put("ring12", new Item("ring12", "Items/ring/12.png", "allakaj", Item.ItemType.RING, 48,           590, 43, 47, 0.4f, 35, 49, 170000));  //225
        ITEMS.put("ring13", new Item("ring13", "Items/ring/13.png", "kerepeks", Item.ItemType.RING, 53,           630, 44, 49, 0.5f, 38, 53, 310000));  //240
        ITEMS.put("ring14", new Item("ring14", "Items/ring/14.png", "narthill", Item.ItemType.RING, 56,           680, 48, 52, 0.6f, 42, 56, 375000));  //260
        ITEMS.put("ring15", new Item("ring15", "Items/ring/15.png", "sphaera", Item.ItemType.RING, 60,           730, 52, 55, 0.6f, 43, 58, 475000));  //275
        ITEMS.put("ring16", new Item("ring16", "Items/ring/16.png", "szhaol", Item.ItemType.RING, 63,           750, 54, 55, 0.8f, 45, 60, 520000)); //285
        ITEMS.put("ring17", new Item("ring17", "Items/ring/17.png", "merkehm", Item.ItemType.RING, 69,           800, 55, 59, 0.9f, 46, 63, 680000));  //305
        ITEMS.put("ring18", new Item("ring18", "Items/ring/18.png", "wachar", Item.ItemType.RING, 74,           860, 58, 62, 1.1f, 48, 67, 860000));  //325
        ITEMS.put("ring19", new Item("ring19", "Items/ring/19.png", "velezen", Item.ItemType.RING, 80,           900, 60, 65, 1.5f, 50, 70, 1000000));  //??

        /**
         * Ring pay
         */
        //ITEMS.put("ring1pay", new Item("ring1pay", "Items/ring/1pay.png", "skra", Item.ItemType.RING, 20,              340, 24, 32, 0.5f, 24, 34, 0.60));
        //ITEMS.put("ring2pay", new Item("ring2pay", "Items/ring/2pay.png", "Carpon", Item.ItemType.RING, 34,              500, 36, 44, 0.5f, 32, 45, 1.10));
        //ITEMS.put("ring3pay", new Item("ring3pay", "Items/ring/3pay.png", "kriaring", Item.ItemType.RING, 47,              730, 52, 55, 0.6f, 43, 58, 1.90));
        //ITEMS.put("ring4pay", new Item("ring4pay", "Items/ring/4pay.png", "eatarus", Item.ItemType.RING, 60,              860, 58, 62, 1.1f, 47, 66, 2.90));

        /**
         * Shoes
         */
        ITEMS.put("shoes1", new Item("shoes1", "Items/shoes/1.png", "Batamury", Item.ItemType.SHOES, 1,            20, 2, 0, 0.1f, 0, 0, 100));
        ITEMS.put("shoes2", new Item("shoes2", "Items/shoes/2.png", "sideny", Item.ItemType.SHOES, 7,            90, 6, 2, 0.6f, 5, 2, 1500)); //30
        ITEMS.put("shoes3", new Item("shoes3", "Items/shoes/3.png", "Iburasy", Item.ItemType.SHOES, 15,           140, 11, 5, 1.3f, 12, 5, 7200)); //65
        ITEMS.put("shoes4", new Item("shoes4", "Items/shoes/4.png", "tekki", Item.ItemType.SHOES, 23,           220, 17, 9, 2.0f, 20, 7, 30000)); //100
        ITEMS.put("shoes5", new Item("shoes4", "Items/shoes/5.png", "Wovki", Item.ItemType.SHOES, 27,            260, 17, 16, 2.1f, 20, 15, 45000)); //120
        ITEMS.put("shoes6", new Item("shoes6", "Items/shoes/6.png", "salany", Item.ItemType.SHOES, 30,           310, 28, 14, 2.7f, 28, 12, 66000)); //145
        ITEMS.put("shoes7", new Item("shoes7", "Items/shoes/7.png", "Viroklesy", Item.ItemType.SHOES, 36,           370, 34, 18, 3.2f, 34, 15, 92000)); //175
        ITEMS.put("shoes8", new Item("shoes8", "Items/shoes/8.png", "hetakamby", Item.ItemType.SHOES, 45,           450, 43, 25, 4.0f, 37, 20, 175000)); //215
        ITEMS.put("shoes9", new Item("shoes9", "Items/shoes/9.png", "sidveny", Item.ItemType.SHOES, 52,           500, 48, 31, 4.4f, 40, 28, 300000)); //245
        ITEMS.put("shoes10", new Item("shoes10", "Items/shoes/10.png", "iskremly", Item.ItemType.SHOES, 60,           540, 50, 35, 5.3f, 45, 34, 500000)); //275
        ITEMS.put("shoes11", new Item("shoes11", "Items/shoes/11.png", "Vartrogi", Item.ItemType.SHOES, 66,        580, 52, 37, 5.8f, 47, 37, 600000)); //290
        ITEMS.put("shoes12", new Item("shoes12", "Items/shoes/12.png", "itrimsy", Item.ItemType.SHOES, 72,        650, 60, 40, 6.5f, 55, 40, 820000)); //325

        /**
         * Shoes pay
         */
        //ITEMS.put("shoes1pay", new Item("shoes1pay", "Items/shoes/1pay.png", "Zigsy", Item.ItemType.SHOES, 30,           460, 44, 26, 4.0f, 38, 20, 0.90));
        //ITEMS.put("shoes2pay", new Item("shoes2pay", "Items/shoes/2pay.png", "Morgi", Item.ItemType.SHOES, 48,           550, 53, 36, 5.5f, 45, 35, 1.90));
        //ITEMS.put("shoes3pay", new Item("shoes3pay", "Items/shoes/3pay.png", "Holmy", Item.ItemType.SHOES, 62,           680, 60, 40, 7.0f, 55, 42, 2.90));

        /**
         * Tarcza
         */
        ITEMS.put("tarcza1", new Item("tarcza1", "Items/tarcza/1.png", "ragur", Item.ItemType.ITEM_BLOCK, 1,             0, 0, 0, 0.3f, 1, 1, 100));
        ITEMS.put("tarcza2", new Item("tarcza2", "Items/tarcza/2.png", "karas", Item.ItemType.ITEM_BLOCK, 7,             20, 5, 4, 0.7f, 6, 6, 100)); //30
        ITEMS.put("tarcza3", new Item("tarcza3", "Items/tarcza/3.png", "piroscape", Item.ItemType.ITEM_BLOCK, 15,            40, 9, 7, 1.6f, 15, 13, 100)); //65
        ITEMS.put("tarcza4", new Item("tarcza4", "Items/tarcza/4.png", "davtagar", Item.ItemType.ITEM_BLOCK, 23,            50, 15, 13, 2.6f, 22, 19, 100)); //100
        ITEMS.put("tarcza5", new Item("tarcza5", "Items/tarcza/5.png", "ildern", Item.ItemType.ITEM_BLOCK, 30,            90, 21, 18, 3.7f, 31, 30, 100)); //145
        ITEMS.put("tarcza6", new Item("tarcza6", "Items/tarcza/6.png", "arcapang", Item.ItemType.ITEM_BLOCK, 36,            90, 21, 20, 4.6f, 40, 40, 100)); //175
        ITEMS.put("tarcza7", new Item("tarcza7", "Items/tarcza/7.png", "krapkas", Item.ItemType.ITEM_BLOCK, 45,            150, 27, 23, 5.8f, 46, 48, 100));  //215
        ITEMS.put("tarcza8", new Item("tarcza8", "Items/tarcza/8.png", "reffas", Item.ItemType.ITEM_BLOCK, 52,            200, 32, 28, 7.0f, 48, 49, 100));  //245
        ITEMS.put("tarcza9", new Item("tarcza9", "Items/tarcza/9.png", "immurtal", Item.ItemType.ITEM_BLOCK, 60,            250, 33, 31, 8.0f, 53, 55, 100));  //275
        ITEMS.put("tarcza10", new Item("tarcza10", "Items/tarcza/10.png", "helmverd", Item.ItemType.ITEM_BLOCK, 66,         280, 33, 31, 8.8f, 55, 58, 100)); //290
        ITEMS.put("tarcza11", new Item("tarcza11", "Items/tarcza/11.png", "angwasev", Item.ItemType.ITEM_BLOCK, 72,         320, 35, 35, 10.0f, 60, 60, 100));

        /**
         * Tarcza pay
         */
        //ITEMS.put("tarcza1pay", new Item("tarcza1pay", "Items/tarcza/1pay.png", "anasileus", Item.ItemType.ITEM_BLOCK, 57,   350, 35, 35, 10.5f, 62, 62, 2.40));
    }

    public static Item getItem(String key) throws CloneNotSupportedException {
        return ITEMS.get(key).clone();
    }
}
