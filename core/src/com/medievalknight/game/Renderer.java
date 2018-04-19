package com.medievalknight.game;

import java.util.ArrayList;

import aurelienribon.tweenengine.TweenManager;

public class Renderer {
    private final static float STEP = 1 / 50f;

    private ArrayList<TweenManager> gameTweens = new ArrayList<TweenManager>();

    public void addGameTween(TweenManager tween) {
        gameTweens.add(tween);
    }

    public ArrayList<TweenManager> getGameTweens() {
        return gameTweens;
    }

    public void update() {
        for (int i = 0; i < gameTweens.size(); i++) {
            if (gameTweens.get(i) != null) {
                gameTweens.get(i).update(STEP);
            }
        }
    }
}
