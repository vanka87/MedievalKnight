package com.medievalknight.game.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import com.medievalknight.game.ButtonClickListener;
import com.medievalknight.game.MenuManager;
import com.medievalknight.game.weaponholders.WeaponItem;

import java.util.ArrayList;

public class TableShop extends TableHolders {

    public TableShop(String text) {
        table = new Table(skin);
        table.setBounds(0, 0, MenuManager.DEVICE_WIDTH, MenuManager.DEVICE_HEIGHT);

        title = new Label(text, skin);
        title.setAlignment(Align.center);

        description = new Label(null, skin);
        description.setAlignment(Align.right);

        for (int i = 0; i < confirmTxtBtn.length; i++) {
            confirmTxtBtn[i] = new TextButton(null, skin);
        }

        confirmTxtBtn[0].setText("BUY");
        confirmTxtBtn[0].setName(String.valueOf(1));

        layoutScrollPane = new Table(skin);

        scrollPane = new ScrollPane(layoutScrollPane, skin);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setTransform(true);

        table.add(title).colspan(2);

        table.row();
        table.top().add(scrollPane).colspan(2);

        table.row();
        table.add(description).expandX();

        table.add(confirmTxtBtn[0]);

        table.setY(0);
    }

    public void fillTable(final ArrayList<WeaponItem> weapons) {

        super.fillTable(weapons);

        game.gM.weaponShop.setCurWeapon(weapons.get(curIndex));

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
                            game.gM.weaponShop.setCurWeapon(weapons.get(curIndex));
                            showDescription(game.gM.weaponShop.getCurWeapon());
                        }
                    }
                }
            });
        }

        confirmTxtBtn[0].addListener(new ButtonClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.gM.weaponInv.addWeapon(game.gM.weaponShop.getCurWeapon());
                game.menu.tableInventory.fillTable(game.gM.weaponInv.getWeapons());
            }
        });
    }
}



