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
        ITEMS.put("wapons2", new Item("wapons2", "Items/wapons/2.png", "Grav", Item.ItemType.WAPON, 4,          10, 1, 1, 0, 1, 1, 600)); //7   1
        ITEMS.put("wapons3", new Item("wapons3", "Items/wapons/3.png", "Astev", Item.ItemType.WAPON, 6,         20, 4, 2, 0, 2, 1, 1100)); //12 2
        ITEMS.put("wapons4", new Item("wapons4", "Items/wapons/4.png", "Drecarparis", Item.ItemType.WAPON, 8,   20, 5, 3, 0.1f, 2, 2, 2000));//15   3
        ITEMS.put("wapons5", new Item("wapons5", "Items/wapons/5.png", "Tsanemi", Item.ItemType.WAPON, 10,      20, 7, 4, 0.1f, 3, 3, 3000)); //21 4
        ITEMS.put("wapons6", new Item("wapons6", "Items/wapons/6.png", "Nart", Item.ItemType.WAPON, 12,         20, 9, 5, 0.1f, 3, 3, 4500));//23  5
        ITEMS.put("wapons7", new Item("wapons7", "Items/wapons/7.png", "Skalfar", Item.ItemType.WAPON, 15,      30, 11, 6, 0.1f, 4, 3, 7500));  //28    6
        ITEMS.put("wapons8", new Item("wapons8", "Items/wapons/8.png", "Arebla", Item.ItemType.WAPON, 18,       30, 13, 7, 0.1f, 4, 3, 12500));//31 7
        ITEMS.put("wapons9", new Item("wapons9", "Items/wapons/9.png", "Degarlim", Item.ItemType.WAPON, 20,     40, 14, 8, 0.2f, 5, 3, 18000)); //38   8
        ITEMS.put("wapons10", new Item("wapons10", "Items/wapons/10.png", "Istev", Item.ItemType.WAPON, 22,     40, 14, 10, 0.3f, 5, 3, 25000)); //41   9
        ITEMS.put("wapons11", new Item("wapons11", "Items/wapons/11.png", "Smaga", Item.ItemType.WAPON, 24,     50, 15, 10, 0.4f, 5, 4, 35000));//44    10
        ITEMS.put("wapons12", new Item("wapons12", "Items/wapons/12.png", "Tehla", Item.ItemType.WAPON, 25,     50, 16, 10, 0.4f, 6, 4, 40000)); //46   11
        ITEMS.put("wapons13", new Item("wapons13", "Items/wapons/13.png", "Tirhall", Item.ItemType.WAPON, 26,   60, 19, 11, 0.5f, 6, 4, 45000));//52    12
        ITEMS.put("wapons14", new Item("wapons14", "Items/wapons/14.png", "Piroklest", Item.ItemType.WAPON, 28,  60, 21, 11, 0.6f, 7,5, 55000));//56    13
        ITEMS.put("wapons15", new Item("wapons15", "Items/wapons/15.png", "Gaomorph", Item.ItemType.WAPON, 30,  70, 23, 11, 0.7f, 7,5, 65500)); //60   14
        ITEMS.put("wapons16", new Item("wapons16", "Items/wapons/16.png", "Xanether", Item.ItemType.WAPON, 32,  70, 23, 13, 0.7f, 8,6, 72000));//64    15
        ITEMS.put("wapons17", new Item("wapons17", "Items/wapons/17.png", "Devgrator", Item.ItemType.WAPON, 35, 90, 23, 13, 0.7f, 8,6, 85000)); //66    16
        ITEMS.put("wapons18", new Item("wapons18", "Items/wapons/18.png", "Ishalm", Item.ItemType.WAPON, 37,    90, 24, 14, 0.8f, 9,6, 95000));//70   17
        ITEMS.put("wapons19", new Item("wapons19", "Items/wapons/19.png", "Virrel", Item.ItemType.WAPON, 38,    100, 25, 16, 0.9f, 10,6, 100000)); //76   18
        ITEMS.put("wapons20", new Item("wapons20", "Items/wapons/20.png", "Falgar", Item.ItemType.WAPON, 42,    100, 25, 18, 1f, 10,7, 115000));//80    19
        ITEMS.put("wapons21", new Item("wapons21", "Items/wapons/21.png", "Skogen", Item.ItemType.WAPON, 44,    110, 26, 18, 1f, 11,8, 135000));//84  20
        ITEMS.put("wapons22", new Item("wapons22", "Items/wapons/22.png", "Avgans", Item.ItemType.WAPON, 45,    110, 28, 19, 1.1f, 12,8, 170000)); //89 21
        ITEMS.put("wapons23", new Item("wapons23", "Items/wapons/23.png", "Nevigan", Item.ItemType.WAPON, 48,   120, 28, 19, 1.2f, 13,9, 200000));//93  22
        ITEMS.put("wapons24", new Item("wapons24", "Items/wapons/24.png", "Brunval", Item.ItemType.WAPON, 50,   120, 29, 20, 1.2f, 14,10, 250000)); //97    23
        ITEMS.put("wapons25", new Item("wapons25", "Items/wapons/25.png", "Verlah", Item.ItemType.WAPON, 52,    130, 31, 20, 1.3f, 14,10, 300000));//101 24
        ITEMS.put("wapons26", new Item("wapons26", "Items/wapons/26.png", "Gjalmer", Item.ItemType.WAPON, 55,   130, 32, 20, 1.4f, 16,10, 365000)); //105    25
        ITEMS.put("wapons27", new Item("wapons27", "Items/wapons/27.png", "Engvelian", Item.ItemType.WAPON, 57, 150, 32, 21, 1.4f, 16,11, 430000));//109   26
        ITEMS.put("wapons28", new Item("wapons28", "Items/wapons/28.png", "Berteur", Item.ItemType.WAPON, 60,   150, 33, 22, 1.5f, 16,12, 480000)); //113  27
        ITEMS.put("wapons29", new Item("wapons29", "Items/wapons/29.png", "Selenoram", Item.ItemType.WAPON, 64, 160, 35, 22, 1.6f, 17,12, 550000));//118   28
        ITEMS.put("wapons30", new Item("wapons30", "Items/wapons/30.png", "Dmarlang", Item.ItemType.WAPON, 67,  160, 36, 23, 1.7f, 17,13, 620000)); //121  29
        ITEMS.put("wapons31", new Item("wapons31", "Items/wapons/31.png", "Vastablin", Item.ItemType.WAPON, 70, 170, 37, 25, 1.7f, 17,13, 700000)); //125  30
        ITEMS.put("wapons32", new Item("wapons32", "Items/wapons/32.png", "Exaviam", Item.ItemType.WAPON, 72,   170, 38, 26, 1.8f, 17,14, 800000));//129   31
        ITEMS.put("wapons33", new Item("wapons33", "Items/wapons/33.png", "Promaris", Item.ItemType.WAPON, 75,  180, 39, 26, 1.9f, 18,14, 900000)); //133  32
        ITEMS.put("wapons34", new Item("wapons34", "Items/wapons/34.png", "Dertham", Item.ItemType.WAPON, 80,   200, 40, 26, 2f, 18,15, 1000000)); //137 33
        /**
         * Wapons pay
         */
        ITEMS.put("wapons1pay", new Item("wapons1pay", "Items/wapons/1pay.png", "Grom", Item.ItemType.WAPON, 5,           20, 9, 5, 0.1f, 3, 3, 30));
        ITEMS.put("wapons2pay", new Item("wapons2pay", "Items/wapons/2pay.png", "Isgeraptor", Item.ItemType.WAPON, 12,    30, 13, 7, 0.1f, 4, 3, 45));
        //ITEMS.put("wapons3pay", new Item("wapons3pay", "Items/wapons/3pay.png", "Killamur", Item.ItemType.WAPON, 18,      50, 16, 10, 0.4f, 6, 4, 0.60));
        //ITEMS.put("wapons4pay", new Item("wapons4pay", "Items/wapons/4pay.png", "Panaris", Item.ItemType.WAPON, 25,       80, 24, 19, 0.8f, 8, 8, 0.80));
        //ITEMS.put("wapons5pay", new Item("wapons5pay", "Items/wapons/5pay.png", "Zgon", Item.ItemType.WAPON, 32,          90, 24, 14, 0.8f, 9,6, 1.00));
        //ITEMS.put("wapons6pay", new Item("wapons6pay", "Items/wapons/6pay.png", "Htegen", Item.ItemType.WAPON, 36,        100, 25, 18, 1f, 10,7, 1.20));
        //ITEMS.put("wapons7pay", new Item("wapons7pay", "Items/wapons/7pay.png", "Santrion", Item.ItemType.WAPON, 40,      110, 28, 19, 1.1f, 12,8, 1.50));
        //ITEMS.put("wapons8pay", new Item("wapons8pay", "Items/wapons/8pay.png", "Ból", Item.ItemType.WAPON, 45,           120, 29, 20, 1.2f, 14,10, 1.90));
        //ITEMS.put("wapons9pay", new Item("wapons9pay", "Items/wapons/9pay.png", "Strach", Item.ItemType.WAPON, 50,        130, 32, 20, 1.4f, 16,10, 2.30));
        //ITEMS.put("wapons10pay", new Item("wapons10pay", "Items/wapons/10pay.png", "Errable", Item.ItemType.WAPON, 55,    150, 33, 22, 1.5f, 16,12, 2.90));
        //ITEMS.put("wapons11pay", new Item("wapons11pay", "Items/wapons/11pay.png", "Cierń", Item.ItemType.WAPON, 60,      170, 37, 25, 1.7f, 17,13, 3.50));
        //ITEMS.put("wapons12pay", new Item("wapons12pay", "Items/wapons/12pay.png", "Morran", Item.ItemType.WAPON, 65,     200, 40, 26, 2f, 18,15, 4.90));

        /**
         * Armor
         */
        ITEMS.put("armor1", new Item("armor1", "Items/armor/1.png", "Heldren", Item.ItemType.ARMOR, 1,          10, 0, 0, 0.1f, 0, 1, 100));         //3
        ITEMS.put("armor2", new Item("armor2", "Items/armor/2.png", "Pusk", Item.ItemType.ARMOR, 5,             10, 1, 0, 0.3f, 2, 1, 800));         //9  1
        ITEMS.put("armor3", new Item("armor3", "Items/armor/3.png", "Ajnes", Item.ItemType.ARMOR, 10,           20, 3, 2, 0.5f, 5, 4, 3100));       //21 3
        ITEMS.put("armor4", new Item("armor4", "Items/armor/4.png", "Kaldras", Item.ItemType.ARMOR, 15,         50, 3, 2, 0.7f, 7, 6, 8000));     //29  4
        ITEMS.put("armor5", new Item("armor5", "Items/armor/5.png", "Arabol", Item.ItemType.ARMOR, 20,          80, 3, 2, 1f, 8, 7, 17000));    //38    6
        ITEMS.put("armor6", new Item("armor6", "Items/armor/6.png", "Vadium", Item.ItemType.ARMOR, 25,          100, 3, 3, 1.4f, 10, 9, 36500));  //49     8
        ITEMS.put("armor7", new Item("armor7", "Items/armor/7.png", "Dabbe", Item.ItemType.ARMOR, 29,           130, 4, 4, 1.9f, 13, 11, 65000));  //63     10
        ITEMS.put("armor8", new Item("armor8", "Items/armor/8.png", "Azter", Item.ItemType.ARMOR, 32,           140, 4, 4, 1.9f, 14, 12, 75000));  //66   16
        ITEMS.put("armor9", new Item("armor9", "Items/armor/9.png", "Voldron", Item.ItemType.ARMOR, 35,         150, 4, 4, 2f, 15, 13, 88000));  //70  13
        ITEMS.put("armor10", new Item("armor10", "Items/armor/10.png", "Kapon", Item.ItemType.ARMOR, 40,        170, 5, 5, 2.2f, 17, 15, 110000)); //80     14
        ITEMS.put("armor11", new Item("armor11", "Items/armor/11.png", "Teahell", Item.ItemType.ARMOR, 43,      180, 6, 5, 2.2f, 18, 16, 125000)); //84     16
        ITEMS.put("armor12", new Item("armor12", "Items/armor/12.png", "Arnsal", Item.ItemType.ARMOR, 46,       190, 6, 5, 2.3f, 20, 18, 160000));  //90        18
        ITEMS.put("armor13", new Item("armor13", "Items/armor/13.png", "Ceretris", Item.ItemType.ARMOR, 50,     190, 6, 6, 2.4f, 23, 21, 230000));  //98    19
        ITEMS.put("armor14", new Item("armor14", "Items/armor/14.png", "Manpur", Item.ItemType.ARMOR, 54,       210, 6, 7, 2.7f, 24, 23, 340000));  //107   21
        ITEMS.put("armor15", new Item("armor15", "Items/armor/15.png", "Orinal", Item.ItemType.ARMOR, 57,       210, 7, 7, 2.8f, 25, 23, 390000));  //110       22
        ITEMS.put("armor16", new Item("armor16", "Items/armor/16.png", "Maendra", Item.ItemType.ARMOR, 60,      220, 7, 7, 2.8f, 26, 23, 450000));  //112      24
        ITEMS.put("armor17", new Item("armor17", "Items/armor/17.png", "Zagreb", Item.ItemType.ARMOR, 64,       230, 8, 7, 3f, 26, 24, 520000));  //117      26
        ITEMS.put("armor18", new Item("armor18", "Items/armor/18.png", "Varlin", Item.ItemType.ARMOR, 68,       240, 8, 7, 3.1f, 26, 25, 610000));  //120       27
        ITEMS.put("armor19", new Item("armor19", "Items/armor/19.png", "Gheiterog", Item.ItemType.ARMOR, 71,    250, 8, 8, 3.2f, 28, 25, 700000));  //125     29
        ITEMS.put("armor20", new Item("armor20", "Items/armor/20.png", "Kaliertare", Item.ItemType.ARMOR, 75,   270, 9, 8, 3.4f, 30, 27, 850000));  //134     31
        ITEMS.put("armor21", new Item("armor21", "Items/armor/21.png", "Isgur", Item.ItemType.ARMOR, 80,        300, 9, 9, 3.5f, 33, 28, 1000000));  //143    32

        /**
         * Armor pay
         */
        //ITEMS.put("armor1pay", new Item("armor1pay", "Items/armor/1pay.png", "Mager", Item.ItemType.ARMOR, 15,      100, 3, 3, 1.4f, 10, 9, 0.40));
        //ITEMS.put("armor2pay", new Item("armor2pay", "Items/armor/2pay.png", "Vay", Item.ItemType.ARMOR, 21,        130, 4, 4, 1.9f, 13, 11, 0.60));
        //ITEMS.put("armor3pay", new Item("armor3pay", "Items/armor/3pay.png", "Varthall", Item.ItemType.ARMOR, 25,   150, 4, 4, 2f, 15, 13, 0.80));
        //ITEMS.put("armor4pay", new Item("armor4pay", "Items/armor/4pay.png", "Kalleres", Item.ItemType.ARMOR, 36,   190, 6, 5, 2.3f, 20, 18, 1.20));
        //ITEMS.put("armor5pay", new Item("armor5pay", "Items/armor/5pay.png", "Vorg", Item.ItemType.ARMOR, 45,       200, 6, 6, 2.5f, 23, 21, 1.60));
        //ITEMS.put("armor6pay", new Item("armor6pay", "Items/armor/6pay.png", "Agonia", Item.ItemType.ARMOR, 50,     250, 8, 7, 3f, 26, 25, 2.25));
        //ITEMS.put("armor7pay", new Item("armor7pay", "Items/armor/7pay.png", "Xergen", Item.ItemType.ARMOR, 65,     300, 10, 10, 3.5f, 33, 28, 3.90));

        /**
         * Helmet
         */
        ITEMS.put("helmet1", new Item("helmet1", "Items/helmet/1.png", "Opaska", Item.ItemType.HELMET, 1,             0, 0, 0, 0.1f, 1, 1, 100)); //3
        ITEMS.put("helmet2", new Item("helmet2", "Items/helmet/2.png", "Hato", Item.ItemType.HELMET, 3,              10, 1, 1, 0.2f, 1, 1, 600)); //7   2
        ITEMS.put("helmet3", new Item("helmet3", "Items/helmet/3.png", "Lena", Item.ItemType.HELMET, 6,             20, 1, 1, 0.2f, 2, 2, 1100)); //10  4
        ITEMS.put("helmet4", new Item("helmet4", "Items/helmet/4.png", "Hoodlin", Item.ItemType.HELMET, 10,             30, 2, 2, 0.3f, 5, 3, 3100)); //18  6
        ITEMS.put("helmet5", new Item("helmet5", "Items/helmet/5.png", "Magon", Item.ItemType.HELMET, 16,             50, 4, 4, 0.4f, 6, 4, 8000)); //26    8
        ITEMS.put("helmet6", new Item("helmet6", "Items/helmet/6.png", "Maramus", Item.ItemType.HELMET, 20,             60, 5, 5, 0.6f, 7, 5, 16500)); //33    10
        ITEMS.put("helmet7", new Item("helmet7", "Items/helmet/7.png", "Rolax", Item.ItemType.HELMET, 22,            70, 6, 5, 0.6f, 8, 5, 24000));  //36  12
        ITEMS.put("helmet8", new Item("helmet8", "Items/helmet/8.png", "Tesek", Item.ItemType.HELMET, 27,            90, 8, 7, 0.8f, 11, 8, 44000)); //49  14
        ITEMS.put("helmet9", new Item("helmet9", "Items/helmet/9.png", "Mellasthegan", Item.ItemType.HELMET, 30,            110, 9, 8, 1f, 12, 10, 60000));  //58 16
        ITEMS.put("helmet10", new Item("helmet10", "Items/helmet/10.png", "Redias", Item.ItemType.HELMET, 36,            140, 11, 10, 1.2f, 14, 12, 90000));  //71  18
        ITEMS.put("helmet11", new Item("helmet11", "Items/helmet/11.png", "Keldarn", Item.ItemType.HELMET, 40,            140, 11, 10, 1.3f, 15, 13, 120000));  //74    20
        ITEMS.put("helmet12", new Item("helmet12", "Items/helmet/12.png", "Geotaur", Item.ItemType.HELMET, 46,            160, 12, 12, 1.6f, 17, 18, 180000));  //89    22
        ITEMS.put("helmet13", new Item("helmet13", "Items/helmet/13.png", "Irkhaml", Item.ItemType.HELMET, 51,            170, 13, 14, 1.6f, 18, 19, 260000));  //95    24
        ITEMS.put("helmet14", new Item("helmet14", "Items/helmet/14.png", "Isvard", Item.ItemType.HELMET, 55,          170, 14, 14, 1.7f, 20, 20, 350000));  //100  26
        ITEMS.put("helmet15", new Item("helmet15", "Items/helmet/15.png", "Marsarry", Item.ItemType.HELMET, 62,          180, 15, 14, 1.9f, 23, 23, 525000));  //110    28
        ITEMS.put("helmet16", new Item("helmet16", "Items/helmet/16.png", "Irkemla", Item.ItemType.HELMET, 70,          190, 15, 14, 2.2f, 27, 26, 700000));  //121 30
        ITEMS.put("helmet17", new Item("helmet17", "Items/helmet/17.png", "Gerthill", Item.ItemType.HELMET, 80,          200, 15, 15, 2.5f, 30, 30, 1000000)); //132    32

        /**
         * Helmet pay
         */
        ITEMS.put("helmet1pay", new Item("helmet1pay", "Items/helmet/1pay.png", "Iskra", Item.ItemType.HELMET, 20,          110, 9, 8, 1f, 12, 10, 60000));
        //ITEMS.put("helmet2pay", new Item("helmet2pay", "Items/helmet/2pay.png", "Kres", Item.ItemType.HELMET, 30,          150, 12, 11, 1.5f, 17, 18, 0.90));
        //ITEMS.put("helmet3pay", new Item("helmet3pay", "Items/helmet/3pay.png", "Bargon", Item.ItemType.HELMET, 44,          180, 15, 14, 1.9f, 22, 23, 1.90));
        //ITEMS.put("helmet4pay", new Item("helmet4pay", "Items/helmet/4pay.png", "Valdren", Item.ItemType.HELMET, 60,          200, 15, 15, 2.6f, 30, 30, 2.90));


        /**
         * Pants
         */
        ITEMS.put("pants1", new Item("pants1", "Items/pants/1.png", "Portki", Item.ItemType.PANTS, 1,             0, 1, 1, 0.0f, 1, 0, 100)); //3
        ITEMS.put("pants2", new Item("pants2", "Items/pants/2.png", "Bony", Item.ItemType.PANTS, 7,             30, 1, 2, 0.1f, 1, 2, 1600));  //10  3
        ITEMS.put("pants3", new Item("pants3", "Items/pants/3.png", "Lyskmery", Item.ItemType.PANTS, 15,           60, 5, 5, 0.4f, 4, 5, 7300)); //29    5
        ITEMS.put("pants4", new Item("pants4", "Items/pants/4.png", "Olny", Item.ItemType.PANTS, 22,             80, 7, 7, 0.7f, 8, 9, 25000)); //46   8
        ITEMS.put("pants5", new Item("pants5", "Items/pants/5.png", "Lystry", Item.ItemType.PANTS, 27,             80, 8, 8, 0.9f, 9, 11, 46000)); //53      10
        ITEMS.put("pants6", new Item("pants6", "Items/pants/6.png", "Vengsy", Item.ItemType.PANTS, 31,             100, 10, 8, 1.1f, 11, 13, 62000));//63  13
        ITEMS.put("pants7", new Item("pants7", "Items/pants/7.png", "Biltebendary", Item.ItemType.PANTS, 38,         120, 12, 9, 1.4f, 13, 15, 100000));//75    16
        ITEMS.put("pants8", new Item("pants8", "Items/pants/8.png", "Hadrasy", Item.ItemType.PANTS, 45,             150, 15, 12, 1.8f, 16, 18, 160000));//94    18
        ITEMS.put("pants9", new Item("pants9", "Items/pants/9.png", "Vaekeny", Item.ItemType.PANTS, 55,              170, 16, 14, 2f, 19, 21, 350000));//109   21
        ITEMS.put("pants10", new Item("pants10", "Items/pants/10.png", "Tengvery", Item.ItemType.PANTS, 58,             170, 16, 14, 2f, 19, 21, 445000));//117   23
        ITEMS.put("pants11", new Item("pants11", "Items/pants/11.png", "Beressery", Item.ItemType.PANTS, 65,             180, 17, 15, 2.1f, 21, 22, 600000));//124  26
        ITEMS.put("pants12", new Item("pants12", "Items/pants/12.png", "Tharimmy", Item.ItemType.PANTS, 75,             190, 17, 16, 2.4f, 25, 24, 850000));//135   29
        ITEMS.put("pants13", new Item("pants13", "Items/pants/13.png", "Nony", Item.ItemType.PANTS, 80,             200, 17, 17, 2.5f, 26, 26, 1000000));  //140    31

        /**
         * Pants pay
         */
        //ITEMS.put("pants1pay", new Item("pants1pay", "Items/pants/1pay.png", "Magny", Item.ItemType.PANTS, 38,             170, 16, 15, 2f, 20, 21, 1.90));
        //ITEMS.put("pants2pay", new Item("pants2pay", "Items/pants/2pay.png", "Frexy", Item.ItemType.PANTS, 64,             200, 18, 17, 2.5f, 26, 26, 2.90));

        /**
         * Rekawice
         */
        ITEMS.put("rekawice1", new Item("rekawice1", "Items/rekawice/1.png", "Alandry", Item.ItemType.ITEM_HAND, 1,              10, 0, 1, 0.0f, 1, 0, 100));//3
        ITEMS.put("rekawice2", new Item("rekawice2", "Items/rekawice/2.png", "Pezary", Item.ItemType.ITEM_HAND, 6,              30, 1, 2, 0.0f, 3, 3, 1200));       //12    2
        ITEMS.put("rekawice3", new Item("rekawice3", "Items/rekawice/3.png", "Baerieny", Item.ItemType.ITEM_HAND, 10,             40, 2, 3, 0.1f, 4, 5, 3100));      //19   5
        ITEMS.put("rekawice4", new Item("rekawice4", "Items/rekawice/4.png", "Zedry", Item.ItemType.ITEM_HAND, 16,             50, 4, 6, 0.1f, 7, 7, 8500));   //31 7
        ITEMS.put("rekawice5", new Item("rekawice5", "Items/rekawice/5.png", "Biasegi", Item.ItemType.ITEM_HAND, 22,             80, 6, 9, 0.2f, 8, 9, 24000));  //40    10
        ITEMS.put("rekawice6", new Item("rekawice6", "Items/rekawice/6.png", "Barcel", Item.ItemType.ITEM_HAND, 28,             140, 12, 13, 0.3f, 12, 14, 50000));  //57   12
        ITEMS.put("rekawice7", new Item("rekawice7", "Items/rekawice/7.png", "Faldry", Item.ItemType.ITEM_HAND, 34,             160, 12, 16, 0.5f, 14, 15, 82000));  //67   14
        ITEMS.put("rekawice8", new Item("rekawice8", "Items/rekawice/8.png", "Aquerisy", Item.ItemType.ITEM_HAND, 40,             180, 17, 19, 0.5f, 15, 17, 105000));  //80  17
        ITEMS.put("rekawice9", new Item("rekawice9", "Items/rekawice/9.png", "Kemsy", Item.ItemType.ITEM_HAND, 45,             190, 20, 21, 0.6f, 17, 18, 160000));  //89   19
        ITEMS.put("rekawice10", new Item("rekawice10", "Items/rekawice/10.png", "Szpony", Item.ItemType.ITEM_HAND, 49,             200, 22, 22, 0.6f, 18, 18, 190000));  //94   22
        ITEMS.put("rekawice11", new Item("rekawice11", "Items/rekawice/11.png", "Mogny", Item.ItemType.ITEM_HAND, 54,          210, 23, 24, 0.7f, 19, 19, 300000));  //101  24
        ITEMS.put("rekawice12", new Item("rekawice12", "Items/rekawice/12.png", "Grovy", Item.ItemType.ITEM_HAND, 61,           230, 24, 25, 1f, 20, 20, 370000));  //110    27
        ITEMS.put("rekawice13", new Item("rekawice13", "Items/rekawice/13.png", "Udreki", Item.ItemType.ITEM_HAND, 65,          240, 25, 26, 1.1f, 21, 21, 600000));  //116 29
        ITEMS.put("rekawice14", new Item("rekawice14", "Items/rekawice/14.png", "Veldreny", Item.ItemType.ITEM_HAND, 75,          270, 30, 30, 1.3f, 22, 22, 890000));  //130   31

        /**
         * Rekawice pay
         */
        //ITEMS.put("rekawice1pay", new Item("rekawice1pay", "Items/rekawice/1pay.png", "Grensy", Item.ItemType.ITEM_HAND, 24,          180, 17, 19, 0.5f, 15, 17, 0.90));  //325
        //ITEMS.put("rekawice2pay", new Item("rekawice2pay", "Items/rekawice/2pay.png", "Tharimy", Item.ItemType.ITEM_HAND, 42,          210, 23, 24, 0.7f, 19, 19, 1.90));  //325
        //ITEMS.put("rekawice3pay", new Item("rekawice3pay", "Items/rekawice/3pay.png", "Verrvy", Item.ItemType.ITEM_HAND, 58,          270, 30, 30, 1.4f, 22, 22, 2.90));  //325

        /**
         * Ring
         */
        ITEMS.put("ring1", new Item("ring1", "Items/ring/1.png", "Karten", Item.ItemType.RING, 1,               0, 0, 1, 0.0f, 0, 2, 100));//3
        ITEMS.put("ring2", new Item("ring2", "Items/ring/2.png", "Haragan", Item.ItemType.RING, 5,               10, 1, 4, 0.0f, 1, 3, 750));       //10    1
        ITEMS.put("ring3", new Item("ring3", "Items/ring/3.png", "Tella", Item.ItemType.RING, 10,              30, 4, 5, 0.1f, 3, 5, 2900));     //21   4
        ITEMS.put("ring4", new Item("ring4", "Items/ring/4.png", "Ajnes", Item.ItemType.RING, 14,              50, 4, 6, 0.1f, 4, 6, 6200));   //27 5
        ITEMS.put("ring5", new Item("ring5", "Items/ring/5.png", "Belest", Item.ItemType.RING, 19,              70, 5, 7, 0.1f, 6, 7, 16000));  //36    7
        ITEMS.put("ring6", new Item("ring6", "Items/ring/6.png", "Baraesz", Item.ItemType.RING, 25,              100, 9, 10, 0.1f, 8, 11, 38500));  //50    9
        ITEMS.put("ring7", new Item("ring7", "Items/ring/7.png", "Hamaris", Item.ItemType.RING, 31,              150, 12, 13, 0.1f, 10, 13, 70000));  //64  11
        ITEMS.put("ring8", new Item("ring8", "Items/ring/8.png", "Bool", Item.ItemType.RING, 36,              170, 14, 14, 0.1f, 13, 14, 90000));  //73 12
        ITEMS.put("ring9", new Item("ring9", "Items/ring/9.png", "Khelem", Item.ItemType.RING, 39,             190, 15, 15, 0.1f, 15, 15, 105000));  //80   14
        ITEMS.put("ring10", new Item("ring10", "Items/ring/10.png", "Nirt", Item.ItemType.RING, 42,               200, 18, 16, 0.2f, 16, 17, 110000));  //89 16
        ITEMS.put("ring11", new Item("ring11", "Items/ring/11.png", "Blask", Item.ItemType.RING, 45,           220, 18, 18, 0.2f, 17, 18, 170000));  //95   18
        ITEMS.put("ring12", new Item("ring12", "Items/ring/12.png", "Allakaj", Item.ItemType.RING, 48,           220, 19, 19, 0.2f, 18, 20, 250000));  //100    20
        ITEMS.put("ring13", new Item("ring13", "Items/ring/13.png", "Kerepeks", Item.ItemType.RING, 53,           230, 19, 20, 0.3f, 20, 22, 310000));  //107   21
        ITEMS.put("ring14", new Item("ring14", "Items/ring/14.png", "Narthill", Item.ItemType.RING, 56,           250, 21, 21, 0.3f, 21, 22, 375000));  //113   23
        ITEMS.put("ring15", new Item("ring15", "Items/ring/15.png", "Sphaera", Item.ItemType.RING, 60,           260, 21, 22, 0.4f, 23, 24, 475000));  //120    25
        ITEMS.put("ring16", new Item("ring16", "Items/ring/16.png", "Szhaol", Item.ItemType.RING, 63,           270, 23, 22, 0.4f, 23, 24, 520000)); //123  27
        ITEMS.put("ring17", new Item("ring17", "Items/ring/17.png", "Merkehm", Item.ItemType.RING, 69,           280, 23, 24, 0.5f, 23, 27, 680000));  //130    28
        ITEMS.put("ring18", new Item("ring18", "Items/ring/18.png", "Wachar", Item.ItemType.RING, 74,           290, 23, 25, 0.6f, 24, 30, 860000));  //137 30
        ITEMS.put("ring19", new Item("ring19", "Items/ring/19.png", "Velezen", Item.ItemType.RING, 80,           300, 23, 27, 0.7f, 24, 33, 1000000));  //144   32

        /**
         * Ring pay
         */
        //ITEMS.put("ring1pay", new Item("ring1pay", "Items/ring/1pay.png", "Skra", Item.ItemType.RING, 20,              150, 12, 13, 0.2f, 10, 13, 0.60));
        //ITEMS.put("ring2pay", new Item("ring2pay", "Items/ring/2pay.png", "Carpon", Item.ItemType.RING, 34,              220, 18, 18, 0.2f, 15, 18, 1.10));
        //ITEMS.put("ring3pay", new Item("ring3pay", "Items/ring/3pay.png", "Kriaring", Item.ItemType.RING, 47,              260, 21, 22, 0.5f, 23, 24, 1.90));
        //ITEMS.put("ring4pay", new Item("ring4pay", "Items/ring/4pay.png", "Eatarus", Item.ItemType.RING, 60,              300, 23, 27, 0.7f, 24, 33, 2.90));

        /**
         * Shoes
         */
        ITEMS.put("shoes1", new Item("shoes1", "Items/shoes/1.png", "Batamury", Item.ItemType.SHOES, 1,            10, 1, 0, 0.1f, 0, 0, 100));//3
        ITEMS.put("shoes2", new Item("shoes2", "Items/shoes/2.png", "Sideny", Item.ItemType.SHOES, 7,            50, 3, 1, 0.2f, 1, 1, 1500)); //13  3
        ITEMS.put("shoes3", new Item("shoes3", "Items/shoes/3.png", "Iburasy", Item.ItemType.SHOES, 15,           60, 4, 2, 0.6f, 5, 2, 7200)); //26    7
        ITEMS.put("shoes4", new Item("shoes4", "Items/shoes/4.png", "Tekki", Item.ItemType.SHOES, 23,           90, 7, 4, 1.0f, 8, 3, 30000)); //40   8
        ITEMS.put("shoes5", new Item("shoes4", "Items/shoes/5.png", "Wovki", Item.ItemType.SHOES, 27,            100, 8, 5, 1.1f, 9, 5, 45000)); //47  11
        ITEMS.put("shoes6", new Item("shoes6", "Items/shoes/6.png", "Salany", Item.ItemType.SHOES, 30,           130, 11, 7, 1.5f, 11, 6, 66000)); //62  14
        ITEMS.put("shoes7", new Item("shoes7", "Items/shoes/7.png", "Viroklesy", Item.ItemType.SHOES, 36,           150, 13, 7, 1.6f, 12, 6, 92000)); //68  17
        ITEMS.put("shoes8", new Item("shoes8", "Items/shoes/8.png", "Hetakamby", Item.ItemType.SHOES, 45,           180, 16, 10, 2f, 16, 9, 175000)); //88   20
        ITEMS.put("shoes9", new Item("shoes9", "Items/shoes/9.png", "Sidveny", Item.ItemType.SHOES, 52,           200, 16, 11, 2.3f, 18, 14, 300000)); //101    23
        ITEMS.put("shoes10", new Item("shoes10", "Items/shoes/10.png", "Iskremly", Item.ItemType.SHOES, 60,           200, 19, 13, 2.6f, 21, 15, 500000)); //114    25
        ITEMS.put("shoes11", new Item("shoes11", "Items/shoes/11.png", "Vartrogi", Item.ItemType.SHOES, 66,        200, 19, 14, 2.8f, 22, 17, 600000)); //120   28
        ITEMS.put("shoes12", new Item("shoes12", "Items/shoes/12.png", "Itrimsy", Item.ItemType.SHOES, 72,        230, 20, 15, 3f, 25, 22, 820000)); //135    31

        /**
         * Shoes pay
         */
        //ITEMS.put("shoes1pay", new Item("shoes1pay", "Items/shoes/1pay.png", "Zigsy", Item.ItemType.SHOES, 30,           180, 16, 10, 2f, 16, 9, 0.90));
        //ITEMS.put("shoes2pay", new Item("shoes2pay", "Items/shoes/2pay.png", "Morgi", Item.ItemType.SHOES, 48,            200, 20, 14, 2.6f, 21, 15, 1.90));
        //ITEMS.put("shoes3pay", new Item("shoes3pay", "Items/shoes/3pay.png", "Holmy", Item.ItemType.SHOES, 62,           250, 20, 16, 3f, 25, 22, 2.90));

        /**
         * Tarcza
         */
        ITEMS.put("tarcza1", new Item("tarcza1", "Items/tarcza/1.png", "Ragur", Item.ItemType.ITEM_BLOCK, 1,             0, 0, 0, 0.1f, 1, 1, 100));//3
        ITEMS.put("tarcza2", new Item("tarcza2", "Items/tarcza/2.png", "Karas", Item.ItemType.ITEM_BLOCK, 7,             10, 3, 1, 0.4f, 2, 2, 1650)); //13  3
        ITEMS.put("tarcza3", new Item("tarcza3", "Items/tarcza/3.png", "Piroscape", Item.ItemType.ITEM_BLOCK, 15,            20, 4, 3, 0.8f, 7, 4, 7500)); //28  6
        ITEMS.put("tarcza4", new Item("tarcza4", "Items/tarcza/4.png", "Davtagar", Item.ItemType.ITEM_BLOCK, 23,            30, 5, 4, 1.2f, 10, 9, 30000)); //43 9
        ITEMS.put("tarcza5", new Item("tarcza5", "Items/tarcza/5.png", "Ildern", Item.ItemType.ITEM_BLOCK, 30,            40, 7, 6, 1.8f, 15, 13, 60000)); //63  12
        ITEMS.put("tarcza6", new Item("tarcza6", "Items/tarcza/6.png", "Arcapang", Item.ItemType.ITEM_BLOCK, 36,             60, 9, 8, 2f, 19, 16, 90000)); //77   15
        ITEMS.put("tarcza7", new Item("tarcza7", "Items/tarcza/7.png", "Krapkas", Item.ItemType.ITEM_BLOCK, 45,            80, 10, 9, 2.5f, 21, 18, 165000));  //90   19
        ITEMS.put("tarcza8", new Item("tarcza8", "Items/tarcza/8.png", "Reffas", Item.ItemType.ITEM_BLOCK, 52,            80, 10, 10, 3f, 24, 22, 290000));  //103  22
        ITEMS.put("tarcza9", new Item("tarcza9", "Items/tarcza/9.png", "Immurtal", Item.ItemType.ITEM_BLOCK, 60,            100, 10, 10, 3.4f, 28, 26, 475000));  //116    25
        ITEMS.put("tarcza10", new Item("tarcza10", "Items/tarcza/10.png", "Helmverd", Item.ItemType.ITEM_BLOCK, 66,         110, 10, 10, 3.5f, 29, 28, 570000)); //121 28
        ITEMS.put("tarcza11", new Item("tarcza11", "Items/tarcza/11.png", "Angwasev", Item.ItemType.ITEM_BLOCK, 72,         150, 10, 10, 4f, 31, 31, 900000));//133  31

        /**
         * Tarcza pay
         */
        ITEMS.put("tarcza1pay", new Item("tarcza1pay", "Items/tarcza/1pay.png", "Annasil", Item.ItemType.ITEM_BLOCK, 57,   150, 10, 10, 4f, 31, 31, 240));
    }

    public static Item getItem(String key) throws CloneNotSupportedException {
        return ITEMS.get(key).clone();
    }
}
