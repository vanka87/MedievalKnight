package com.medievalknight.game.weaponholders;

import com.badlogic.gdx.utils.JsonValue;
import com.medievalknight.game.KnightGame;

import java.util.ArrayList;

public class WeaponShop {
    private KnightGame game = KnightGame.getInstance();

    //weaponshop size
    public static final int WEAPON_NUM = 100;

    private ArrayList<WeaponItem> weapons = null;
    private WeaponItem curWeapon;

    //creation weaponshop with random non-unique elements
    public void createWeapons() {
        int numItems = 0;
        short rndType = 0;

        numItems = game.gM.getWeaponData().get("items").size;

        weapons = new ArrayList();

        for (int i = 0; i < WEAPON_NUM; i++) {
            rndType = (short) Math.round((numItems - 1) * Math.random());

            JsonValue curItem = game.gM.getWeaponData().get("items").get(rndType);

            weapons.add(new WeaponItem(
                            curItem.getString("type"),
                            curItem.getString("name"),
                            curItem.getInt("attack"),
                            curItem.getInt("defense"),
                            curItem.getInt("price"),
                            curItem.getString("icon"),
                            curItem.getString("description")
                    )
            );
        }
    }

    public void setCurWeapon(WeaponItem wi) {
        this.curWeapon = wi;
    }

    public WeaponItem getCurWeapon() {
        return this.curWeapon;
    }

    public ArrayList<WeaponItem> getWeapons() {
        return this.weapons;
    }
}



