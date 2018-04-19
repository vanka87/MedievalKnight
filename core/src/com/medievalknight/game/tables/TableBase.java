package com.medievalknight.game.tables;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.medievalknight.game.KnightGame;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Elastic;

public class TableBase {
    protected KnightGame game = KnightGame.getInstance();

    public Table table;
    protected Skin skin;

    public TableBase() {
        skin = game.menu.getSkin();
    }

    public void showTable(int direction, float startValue, float endValue, float duration) {
        game.renderer.addGameTween(new TweenManager());
        final int indexOfTween = game.renderer.getGameTweens().size() - 1;

        Tween.set(table, direction).target(startValue).start(game.renderer.getGameTweens().get(indexOfTween));
        Tween.to(table, direction, duration).delay(0).target(endValue).ease(Elastic.OUT.a(1f).p(1f)).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                Gdx.input.setInputProcessor(game.gameStage);
            }
        }).start(game.renderer.getGameTweens().get(indexOfTween));
    }

    public void hideTable(int direction, float startValue, float endValue, float duration, final Screen nextScreen) {
        game.renderer.addGameTween(new TweenManager());
        final int indexOfTween = game.renderer.getGameTweens().size() - 1;

        Tween.set(table, direction).target(startValue).start(game.renderer.getGameTweens().get(indexOfTween));
        Tween.to(table, direction, duration).target(endValue).ease(Elastic.IN.a(1f).p(1f)).setCallback(new TweenCallback() {

            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(nextScreen);
            }

        }).start(game.renderer.getGameTweens().get(indexOfTween));
    }
}
