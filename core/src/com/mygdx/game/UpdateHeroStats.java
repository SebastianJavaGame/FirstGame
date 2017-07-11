package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Sebastian on 2017-07-03.
 */

public class UpdateHeroStats {
    private Preferences pref;

    public UpdateHeroStats(Hero hero) throws CloneNotSupportedException {
        pref = Gdx.app.getPreferences(Equipment.PREF_NAME_EQ);

        int atributeHp = 0;
        int atributeStrong = 0;
        int atributeWiedza = 0;
        int atributeArmor = 0;
        int atributeDefenseFiz = 0;
        int atributeDefenseMag = 0;

        for (int i = 0; i < 8; i++) {
            String value = pref.getString(Equipment.PREF_ITEM_HUMAN[i], "");
            if (!value.equals("")) {
                Item item = LoadAllItemToGame.getItem(value);

                atributeHp += item.getHp();
                atributeStrong += item.getStrong();
                atributeWiedza += item.getWiedza();
                atributeArmor += item.getArmor();
                atributeDefenseFiz += item.getDefenseFiz();
                atributeDefenseMag += item.getDefenseMag();
            }
        }

        hero.setHpNonEq(hero.getMaxHp());
        hero.setHpEq(hero.getHpNonEq() + atributeHp);
        hero.setFullHp(hero.getHpEq());
        hero.setStrongEq(hero.getStrong() + atributeStrong);
        hero.setWiedzaEq(hero.getWiedza() + atributeWiedza);
        hero.setArmorEq(hero.getArmor() + atributeArmor);
        hero.setDefenseFizEq(hero.getDefenseFiz() + atributeDefenseFiz);
        hero.setDefenseMagEq(hero.getDefenseMag() + atributeDefenseMag);
    }
}
