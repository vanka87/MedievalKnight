package com.medievalknight.game.weaponholders;

import com.medievalknight.game.weaponholders.WeaponItem;

import java.util.ArrayList;

public class WeaponInventory {
    //inventory size
    private static final int NUM_CELL = 10;

    private ArrayList<WeaponItem> weapons = null;
    private WeaponItem curWeapon;

    public WeaponInventory() {
        weapons = new ArrayList();

        for (int i = 0; i < NUM_CELL; i++) {
            weapons.add(null);
        }
    }

    public boolean addWeapon(WeaponItem wi) {
        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i) == null) {
                weapons.set(i, wi);
                return true;
            }
        }

        return false;
    }

    public void removeWeapon(int index) {
        weapons.set(index, null);
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
