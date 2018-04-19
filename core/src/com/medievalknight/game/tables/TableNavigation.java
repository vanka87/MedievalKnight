package com.medievalknight.game.tables;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.medievalknight.game.ButtonClickListener;
import com.medievalknight.game.MenuManager;
import com.medievalknight.game.interfaces.IScreenHider;

public class TableNavigation extends TableBase {

    private TextButton[] navTxtBtn = new TextButton[2];
    private int curScreenIndex = 0;

    public TableNavigation() {
        table = new Table(skin);
        table.setBounds(0, 0, MenuManager.DEVICE_WIDTH, MenuManager.DEVICE_HEIGHT);

        for (int i = 0; i < navTxtBtn.length; i++) {
            navTxtBtn[i] = new TextButton(null, skin);
        }

        navTxtBtn[0].setText("PREV");
        navTxtBtn[1].setText("NEXT");

        navTxtBtn[0].setName(String.valueOf(-1));
        navTxtBtn[1].setName(String.valueOf(1));

        table.add(navTxtBtn[0]).expandX().left();
        table.add(navTxtBtn[1]).expandX().right();

        table.setY(0);

        for (int i = 0; i < navTxtBtn.length; i++) {
            navTxtBtn[i].addListener(new ButtonClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    TextButton curBtn = (TextButton) event.getListenerActor();
                    int screenNum = game.menu.screens.size() - 1;

                    curScreenIndex += Integer.parseInt(curBtn.getName());

                    //goto first screen
                    if (curScreenIndex > screenNum) curScreenIndex = 0;
                    //goto the last screen
                    if (curScreenIndex < 0) curScreenIndex = screenNum;

                    Gdx.input.setInputProcessor(null);

                    ((IScreenHider) game.getScreen()).hideScreen(game.menu.screens.get(curScreenIndex));
                }
            });
        }
    }
}
