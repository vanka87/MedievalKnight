package com.medievalknight.game.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.medievalknight.game.ButtonClickListener;
import com.medievalknight.game.MenuManager;
import com.medievalknight.game.weaponholders.WeaponItem;
import com.medievalknight.game.weaponholders.WeaponKnight;
import com.medievalknight.game.screens.ScreenKnight;

import java.util.ArrayList;

public class TableKnight extends TableHolders {
    protected Label title;

    protected ArrayList<ImageButton> cellImgBtn = new ArrayList<ImageButton>();
    protected ArrayList<ImageButton.ImageButtonStyle> imgBtnStyle = new ArrayList<ImageButton.ImageButtonStyle>();

    public TableKnight(String text) {
        table = new Table(skin);
        table.setBounds(0, 0, MenuManager.DEVICE_WIDTH, MenuManager.DEVICE_HEIGHT);

        title = new Label(text, skin);
        title.setAlignment(Align.center);

        description = new Label(null, skin);
        description.setAlignment(Align.left);

        for (int i = 0; i < confirmTxtBtn.length; i++) {
            confirmTxtBtn[i] = new TextButton("", skin);
        }

        confirmTxtBtn[0].setText("DROP");

        for (int i = 0; i < WeaponKnight.NUM_CELL; i++) {
            cellImgBtn.add(new ImageButton(skin));
        }

        table.setY(0);
    }

    private void addContent() {
        table.add(title).colspan(4);

        table.row();
        table.add();
        table.center().add(cellImgBtn.get(0));
        table.add(description);

        table.row();
        table.add(cellImgBtn.get(1)).right().padLeft(MenuManager.DEVICE_WIDTH * 0.05f);
        table.add(cellImgBtn.get(2));
        table.add(cellImgBtn.get(3)).left().expandX();
        table.add(confirmTxtBtn[0]);

        table.row();
        table.add();
        table.add(cellImgBtn.get(4));

        table.row();
        table.add();
        table.add(cellImgBtn.get(5));

        table.row();
        table.add().expandY();
    }

    public void fillTable(final ArrayList<WeaponItem> weapons) {
        table.clear();
        cellImgBtn.clear();
        imgBtnStyle.clear();
        removeListeners();

        for (int i = 0; i < weapons.size(); i++) {
            imgBtnStyle.add(new ImageButton.ImageButtonStyle(
                    skin.getDrawable("btn_cell_up"),
                    skin.getDrawable("btn_cell_down"),
                    skin.getDrawable("btn_cell_down"),
                    skin.getDrawable((weapons.get(i) == null) ? "btn_cell_up" : weapons.get(i).getIcon()),
                    skin.getDrawable((weapons.get(i) == null) ? "btn_cell_down" : weapons.get(i).getIcon()),
                    skin.getDrawable((weapons.get(i) == null) ? "btn_cell_down" : weapons.get(i).getIcon())
            ));

            cellImgBtn.add(new ImageButton(imgBtnStyle.get(i)));
            cellImgBtn.get(i).setName(String.valueOf(i));
        }

        game.gM.weaponKnight.calcSkills();

        description.setText("MY MEDIEVAL KNIGHT SKILLS" +
                "\nATTACK : + " + game.gM.weaponKnight.getAttack() +
                "\nDEFENSE : + " + game.gM.weaponKnight.getDefense()
        );

        addContent();

        cellImgBtn.get(curIndex).setChecked(true);
        game.gM.weaponKnight.setCurWeapon(weapons.get(curIndex));

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
                            game.gM.weaponKnight.setCurWeapon(weapons.get(j));
                        }
                    }
                }
            });
        }

        if (game.getScreen() instanceof ScreenKnight) {
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

                if (game.gM.weaponInv.addWeapon(game.gM.weaponKnight.getCurWeapon())) {
                    game.gM.weaponKnight.removeWeapon(curIndex);
                }

                game.menu.tableInventory.fillTable(game.gM.weaponInv.getWeapons());
                game.menu.tableKnight.fillTable(game.gM.weaponKnight.getWeapons());

            }
        });
    }
}
