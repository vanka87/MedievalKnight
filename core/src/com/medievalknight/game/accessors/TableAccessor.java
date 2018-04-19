package com.medievalknight.game.accessors;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

import aurelienribon.tweenengine.TweenAccessor;

public class TableAccessor implements TweenAccessor<Table> {
    public static final int TWEEN_X = 0;
    public static final int TWEEN_Y = 1;
    public static final int TWEEN_ALPHA = 2;

    @Override
    public int getValues(Table target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case TWEEN_Y:
                returnValues[0] = target.getY();
                return 1;

            case TWEEN_X:
                returnValues[0] = target.getX();
                return 1;

            case TWEEN_ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;

            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Table target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case TWEEN_Y:
                target.setY(newValues[0]);
                break;

            case TWEEN_X:
                target.setX(newValues[0]);
                break;

            case TWEEN_ALPHA:
                target.setColor(target.getColor().r, target.getColor().g, target.getColor().b, newValues[0]);
                break;

            default:
                assert false;
        }
    }
}