package com.medievalknight.game.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.medievalknight.game.ButtonClickListener;
import com.medievalknight.game.MenuManager;
import com.medievalknight.game.weaponholders.WeaponItem;
import com.medievalknight.game.screens.ScreenShop;

import java.util.ArrayList;

public class TableInventory extends TableHolders {

    public TableInventory(String text) {
        table = new Table(skin);
        table.setBounds(0, 0, MenuManager.DEVICE_WIDTH, MenuManager.DEVICE_HEIGHT);

        title = new Label(text, skin);
        title.setAlignment(Align.center);

        description = new Label(null, skin);
        description.setAlignment(Align.left);

        for (int i = 0; i < confirmTxtBtn.length; i++) {
            confirmTxtBtn[i] = new TextButton("", skin);
        }

        confirmTxtBtn[0].setText("USE");
        confirmTxtBtn[1].setText("DEL");

        layoutScrollPane = new Table(skin);

        scrollPane = new ScrollPane(layoutScrollPane, skin);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setTransform(true);

        table.add(title).colspan(4);

        table.row();
        table.add().expandX();
        table.add(description).expandX();
        table.add(confirmTxtBtn[0]);
        table.add(confirmTxtBtn[1]);

        table.row();
        table.bottom().add(scrollPane).colspan(4);

        table.setY(0);
    }

    public void fillTable(final ArrayList<WeaponItem> weapons) {

        super.fillTable(weapons);

        game.gM.weaponInv.setCurWeapon(weapons.get(curIndex));

        for (int i = 0; i < cellImgBtn.size(); i++) {
            cellImgBtn.get(i).addListener(new ButtonClickListener() {

                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);

                    ImageButton curBtn = (ImageButton) event.getListenerActor();

                    for (int j = 0; j < cellImgBtn.size(); j++) {
                        if (j != Integer.parseInt(curBtn.getName())) {
                            cellImgBtn.get(j).setChecked(false);
                        } else {
                            cellImgBtn.get(j).setChecked(true);
                        }
                        if (cellImgBtn.get(j).isChecked()) {
                            curIndex = j;
                            game.gM.weaponInv.setCurWeapon(weapons.get(j));
                            showDescription(game.gM.weaponInv.getCurWeapon());
                        }
                    }
                }
            });
        }

        if (game.getScreen() instanceof ScreenShop) {
            confirmTxtBtn[0].setTouchable(Touchable.disabled);
            confirmTxtBtn[0].getColor().a = 0.5f;
        } else {
            confirmTxtBtn[0].setTouchable(Touchable.enabled);
            confirmTxtBtn[0].getColor().a = 1f;
        }

        confirmTxtBtn[0].addListener(new ButtonClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                if (game.gM.weaponKnight.addWeapon(game.gM.weaponInv.getCurWeapon())) {
                    game.gM.weaponInv.removeWeapon(curIndex);
                }

                game.menu.tableKnight.fillTable(game.gM.weaponKnight.getWeapons());
                game.menu.tableInventory.fillTable(game.gM.weaponInv.getWeapons());
            }
        });

        confirmTxtBtn[1].addListener(new ButtonClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.gM.weaponInv.removeWeapon(curIndex);
                game.menu.tableInventory.fillTable(game.gM.weaponInv.getWeapons());
            }
        });
    }
}