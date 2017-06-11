package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import Screen.BaseMap;

/**
 * Created by Sebastian on 2017-05-31.
 */

public class Hero extends Character {
    private BaseMap baseMap;
    private Vector2 velocity;

    private float speed;

    private int level;
    private int money;
    private int hp;
    private int maxHp;
    private int exp;
    private int maxExp;

    public int strong = 10;
    public int wiedza = 10;
    public int defense = 10;

    public int hpEq = 100 + 25;
    public int strongEq = strong +5;
    public int wiedzaEq = wiedza +5;
    public int defenseEq = defense +5;

    public Hero(BaseMap baseMap, Texture texture, float speed) {
        super(texture);
        this.baseMap = baseMap;
        velocity = new Vector2();
        this.speed = speed;

        create();
    }

    private void create(){
        setMaxExp(100);
        setMoney(184436320);
        setMaxHp(100);
        setHp(100);
        setExp(100);
        setLevel(25);
    }

    /**
     * Getters and Setters
     */

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setMaxHp(int maxHp){
        this.maxHp = maxHp;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public void setMaxExp(int maxExp){
        this.maxExp = maxExp;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setMoney(int money){
       this.money = money;
    }

    public int getLevel() {
        return level;
    }

    public String getMoney() {
        if(money >= 1000000)
            return " " + money / 1000000 + " KK";
        else if(money >= 1000)
            return " " + money / 1000 + " K";
        else
            return " " + money;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getExp() {
        return exp;
    }

    public int getMaxExp() {
        return maxExp;
    }
}
