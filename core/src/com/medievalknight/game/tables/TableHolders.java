package com.medievalknight.game.tables;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.medievalknight.game.ButtonClickListener;
import com.medievalknight.game.weaponholders.WeaponItem;

import java.util.ArrayList;

public class TableHolders extends TableBase {
    protected Table layoutScrollPane;
    protected Label title, description;
    protected ScrollPane scrollPane;

    protected TextButton[] confirmTxtBtn = new TextButton[2];

    protected ArrayList<ImageButton> cellImgBtn = new ArrayList<ImageButton>();
    protected ArrayList<ImageButton.ImageButtonStyle> imgBtnStyle = new ArrayList<ImageButton.ImageButtonStyle>();

    protected int curIndex = 0;

    public void fillTable(final ArrayList<WeaponItem> weapons) {
        layoutScrollPane.clear();
        cellImgBtn.clear();
        imgBtnStyle.clear();
        removeListeners();

        Table[] layoutContent = new Table[weapons.size()];

        for (int i = 0; i < layoutContent.length; i++) {
            layoutContent[i] = new Table(skin);

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

            layoutContent[i].add(cellImgBtn.get(i));

            layoutContent[i].row();
            layoutContent[i].add(new Label(
                    (weapons.get(i) == null) ? "EMPTY" : weapons.get(i).getName(),
                    skin)
            );

            layoutContent[i].row();
            layoutContent[i].add(new Label(
                    (weapons.get(i) == null) ? null :
                            "A:+" + weapons.get(i).getAttack() + " D:+" + weapons.get(i).getDefense(),
                    skin)
            );

            layoutContent[i].row();
            layoutContent[i].add(new Label(
                    (weapons.get(i) == null) ? null :
                            weapons.get(i).getPrice() + "$",
                    skin));

            layoutScrollPane.add(layoutContent[i]);
        }

        cellImgBtn.get(curIndex).setChecked(true);
        showDescription(weapons.get(curIndex));
    }

    public void removeListeners() {
        for (int i = 0; i < confirmTxtBtn.length; i++) {
            for (EventListener eL : confirmTxtBtn[i].getListeners()) {
                if (eL instanceof ButtonClickListener) {
                    confirmTxtBtn[i].removeListener(eL);
                }
            }
        }
    }

    public void showDescription(WeaponItem wi) {
        if (wi != null) {
            description.setText("Description : " + wi.getDescription());
        } else {
            description.setText("Description : EMPTY CELL");
        }
    }
}
