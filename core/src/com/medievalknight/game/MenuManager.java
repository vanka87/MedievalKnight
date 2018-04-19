package com.medievalknight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.medievalknight.game.accessors.TableAccessor;
import com.medievalknight.game.screens.ScreenInventory;
import com.medievalknight.game.screens.ScreenKnight;
import com.medievalknight.game.screens.ScreenShop;
import com.medievalknight.game.tables.TableInventory;
import com.medievalknight.game.tables.TableKnight;
import com.medievalknight.game.tables.TableNavigation;
import com.medievalknight.game.tables.TableShop;

import java.util.Map;
import java.util.TreeMap;

import aurelienribon.tweenengine.Tween;

public class MenuManager {
    public static float DEVICE_WIDTH = Gdx.graphics.getWidth();
    public static float DEVICE_HEIGHT = Gdx.graphics.getHeight();

    private TextureAtlas atlas;
    private Skin skin;

    public Map<Integer, Screen> screens = new TreeMap<Integer, Screen>();
    public TableNavigation tableNav;
    public TableKnight tableKnight;
    public TableInventory tableInventory;
    public TableShop tableShop;

    public MenuManager() {
        screens.put(0, new ScreenKnight());
        screens.put(1, new ScreenShop());
        screens.put(2, new ScreenInventory());

        Tween.registerAccessor(Actor.class, new TableAccessor());
    }

    public void createTables() {
        atlas = new TextureAtlas(Gdx.files.internal("ui/sprites.pack"));
        skin = new Skin(Gdx.files.internal("ui/ui.json"), atlas);

        tableNav = new TableNavigation();
        tableKnight = new TableKnight("MEDIEVAL KNIGHT");
        tableInventory = new TableInventory("INVENTORY");
        tableShop = new TableShop("WEAPON SHOP");
    }

    public Skin getSkin() {
        return this.skin;
    }
}
