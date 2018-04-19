package com.medievalknight.game.weaponholders;

public class WeaponItem {
    private String type = null;
    private String name = null;
    private int attack = 0;
    private int defense = 0;
    private int price = 0;
    private String icon = null;
    private String description = null;

    public WeaponItem(String t, String n, int a, int d, int p, String i, String de) {
        this.type = t;
        this.name = n;
        this.attack = a;
        this.defense = d;
        this.price = p;
        this.icon = i;
        this.description = de;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getPrice() {
        return this.price;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getDescription() {
        return this.description;
    }
}
