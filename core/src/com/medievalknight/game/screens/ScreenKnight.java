package com.medievalknight.game.screens;

import com.badlogic.gdx.Screen;
import com.medievalknight.game.KnightGame;
import com.medievalknight.game.MenuManager;
import com.medievalknight.game.accessors.TableAccessor;
import com.medievalknight.game.interfaces.IScreenHider;

public class ScreenKnight implements Screen, IScreenHider {
    private KnightGame game = KnightGame.getInstance();

    @Override
    public void show() {
        game.gameStage.addActor(game.menu.tableNav.table);
        game.gameStage.addActor(game.menu.tableKnight.table);

        game.menu.tableKnight.fillTable(game.gM.weaponKnight.getWeapons());

        game.menu.tableNav.showTable(TableAccessor.TWEEN_Y, MenuManager.DEVICE_HEIGHT, 0, 1);
        game.menu.tableKnight.showTable(TableAccessor.TWEEN_Y, MenuManager.DEVICE_HEIGHT, 0, 1);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void hideScreen(Screen nextScreen) {
        game.menu.tableKnight.hideTable(TableAccessor.TWEEN_Y, 0, -MenuManager.DEVICE_HEIGHT, 1, null);
        game.menu.tableNav.hideTable(TableAccessor.TWEEN_Y, 0, MenuManager.DEVICE_HEIGHT, 1, nextScreen);
    }
}
