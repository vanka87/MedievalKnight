package com.medievalknight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.medievalknight.game.weaponholders.WeaponInventory;
import com.medievalknight.game.weaponholders.WeaponKnight;
import com.medievalknight.game.weaponholders.WeaponShop;

public class GameManager {
    public WeaponKnight weaponKnight;
    public WeaponInventory weaponInv;
    public WeaponShop weaponShop;

    private JsonValue items;

    public GameManager() {
        items = new JsonReader().parse(Gdx.files.internal("ui/weapons.json"));

        weaponKnight = new WeaponKnight();
        weaponInv = new WeaponInventory();
        weaponShop = new WeaponShop();
    }

    public JsonValue getWeaponData() {
        return items;
    }
}
