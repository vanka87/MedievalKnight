package com.medievalknight.game.weaponholders;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class WeaponKnight {
    //kinght holder size
    public static final int NUM_CELL = 6;

    private int attack = 0;
    private int defense = 0;

    private ArrayList<WeaponItem> weapons = null;
    private Map<String, Integer> weaponsMap = null;//Map: Key=WeaponName, Object=weapons index

    private WeaponItem curWeapon;

    public WeaponKnight() {
        weapons = new ArrayList();
        weaponsMap = new TreeMap<String, Integer>();

        for (int i = 0; i < NUM_CELL; i++) {
            weapons.add(null);
        }

        //markup according to location on the screen
        weaponsMap.put("Helmet", 0);
        weaponsMap.put("Sword", 1);
        weaponsMap.put("Armor", 2);
        weaponsMap.put("Shield", 3);
        weaponsMap.put("Belt", 4);
        weaponsMap.put("Boots", 5);
    }

    public void calcSkills() {
        this.attack = 0;
        this.defense = 0;

        for (int i = 0; i < weapons.size(); i++) {
            if (weapons.get(i) != null) {
                this.attack += weapons.get(i).getAttack();
                this.defense += weapons.get(i).getDefense();
            }
        }
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public boolean addWeapon(WeaponItem wi) {
        if (wi != null) {
            String key = wi.getType();

            //checking if the cell is already filled
            if (weapons.get(weaponsMap.get(key)) == null) {
                weapons.set(weaponsMap.get(key), wi);

                return true;
            } else {
                return false;
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
