package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Sebastian on 2017-06-11.
 */

public class Item implements Cloneable, Disposable{
    public static final int BLOCK_SIZE = 50;

    public enum ItemType{
        HELMET, ARMOR, PANTS, SHOES, WAPON, ITEM_BLOCK, RING, HAND_ITEM
    }

    public enum Stan{
        BAG, HUMAN
    }

    private Asset asset = new Asset();
    private ItemType itemType;
    private Stan stan;

    private Texture texture;

    private String itemKey;
    private String itemName;
    private String pathImage;
    private Image image;
    private int levelRequire;
    private int hp = 0;
    private int strong = 0;
    private int wiedza = 0;
    private float armor = 0;
    private int defenseFiz = 0;
    private int defenseMag = 0;
    private int cashValue = 0;

    public Item(String  pathImage, ItemType itemType){
        this.pathImage = pathImage;

        asset.manager.load(pathImage, Texture.class);
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            this.texture = asset.manager.get(pathImage, Texture.class);
            this.image = new Image(texture);
        }

        this.itemType = itemType;
        image.setSize(BLOCK_SIZE, BLOCK_SIZE);
    }

    public Item(String itemKey, String pathImage, String itemName, ItemType itemType, int levelRequire, int hp, int strong, int wiedza, float armor, int defenseFiz, int defenseMag, int cashValue) {
        this.itemKey = itemKey;
        this.pathImage = pathImage;

        asset.manager.load(pathImage, Texture.class);
        asset.manager.finishLoading();
        if(asset.manager.update()) {
            this.texture = asset.manager.get(pathImage, Texture.class);
            this.image = new Image(texture);
        }

        this.itemName = itemName;
        this.itemType = itemType;
        image.setSize(BLOCK_SIZE, BLOCK_SIZE);
        this.levelRequire = levelRequire;
        this.hp = hp;
        this.strong = strong;
        this.wiedza = wiedza;
        this.armor = armor;
        this.defenseFiz = defenseFiz;
        this.defenseMag = defenseMag;
        this.cashValue = cashValue;
    }

    @Override
    public void dispose() {
        texture.dispose();
        asset.dispose();
    }

    @Override
    public Item clone() throws CloneNotSupportedException{
        Item item =  (Item) super.clone();
        item.image = new Image(asset.manager.get(pathImage, Texture.class));
        item.image.setSize(BLOCK_SIZE, BLOCK_SIZE);
        return item;
    }

    /**
     * Getters and Setters
     */
    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public int getLevelRequire(){
        return levelRequire;
    }

    public int getHp() {
        return hp;
    }

    public int getStrong() {
        return strong;
    }

    public int getWiedza() {
        return wiedza;
    }

    public int getDefenseFiz() {
        return defenseFiz;
    }

    public int getDefenseMag() {
        return defenseMag;
    }

    public float getArmor(){
        return armor;
    }

    public int getCashValue(){
        return cashValue;
    }

    public String getPathImage(){
        return pathImage;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemKey(){
        return itemKey;
    }

    public Image getImage(){
        return image;
    }

    public Stan getStan() {
        return stan;
    }

    public Texture getTexture() {
        return texture;
    }
}
