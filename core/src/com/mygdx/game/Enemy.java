package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Enemy extends Character {
    private Image head;
    private Image wapon;
    private String name;
    private int level;
    private int hp;
    private int strong;
    private int wiedza;
    private int armor;
    private int defensePhysics;
    private int defenseMagic;
    private ArrayList<String> dropItemName;
    private float randomDrop;

    private Vector2 expToKill;
    private Vector2 moneyToKill;

    public Enemy(Texture texture, Image head, Image wapon, String name, int level, int hp, int strong, int wiedza, int armor, int defensePhysics, int defenseMagic,
                 float randomDrop, int expToKillMin, int expToKillMax, int moneyToKillMin, int moneyToKillMax) {
        super(texture);
        this.head = head;
        this.wapon = wapon;
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.strong = strong;
        this.wiedza = wiedza;
        this.armor = armor;
        this.defensePhysics = defensePhysics;
        this.defenseMagic = defenseMagic;
        this.randomDrop = randomDrop;
        this.expToKill = new Vector2(expToKillMin, expToKillMax);
        this.moneyToKill = new Vector2(moneyToKillMin, moneyToKillMax);
        dropItemName = new ArrayList();

        setSize(this.getWidth() *0.45f, this.getHeight() *0.45f);
    }

    public void setDropItemName(String ... itemName){
        for(String item: itemName)
            dropItemName.add(item);
    }

    @Override
    public void collisionDo() {
        System.out.println("ENEMY");
    }

    /**
     * Setter and Getter
     */

    public String getName(){
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp(){
        return hp;
    }

    public int getStrong() {
        return strong;
    }

    public int getWiedza() {
        return wiedza;
    }

    public int getArmor() {
        return armor;
    }

    public int getDefensePhysics() {
        return defensePhysics;
    }

    public int getDefenseMagic() {
        return defenseMagic;
    }

    public float getRandomDrop(){
        return randomDrop;
    }

    public Image getHead() {
        return head;
    }

    public Image getWapon(){
        return wapon;
    }
}
