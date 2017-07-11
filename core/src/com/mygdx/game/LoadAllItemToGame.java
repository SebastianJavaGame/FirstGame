package com.mygdx.game;

import java.util.HashMap;

/**
 * Created by Sebastian on 2017-06-29.
 */

class LoadAllItemToGame {
    public static final HashMap<String, Item> ITEMS = new HashMap<String, Item>();

    public static final void loadItems(){
        ITEMS.put("silver_sword", new Item("silver_sword", "Items/Silver_sword.png", "Miecz srebrnego błysku", Item.ItemType.WAPON, 50, 5, 0, 6, 0, 0, 1000));
        ITEMS.put("gold_armor", new Item("gold_armor", "Items/Gold_armor.png", "Złoty pancerz nieśmiertelności", Item.ItemType.ARMOR, 80, 0, 0, 0, 20, 20, 1500));
        ITEMS.put("fire_sword", new Item("fire_sword", "Items/Fire_sword.png", "Piekielny miecz ognia", Item.ItemType.WAPON, 60, 10, 2, 10, 0, 0, 2000));
    }

    public static Item getItem(String key) throws CloneNotSupportedException {
        return ITEMS.get(key).clone();
    }
}
