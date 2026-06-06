package com.princeofcookies.dervafx;

import javafx.scene.control.CheckBox;

public class DervaCheckBox extends DervaElement<DervaCheckBox> {
    private final CheckBox checkBox;

    public DervaCheckBox(String text) {
        super(new CheckBox(text == null ? "" : text));
        this.checkBox = (CheckBox) contentNode();
        checkBox.getStyleClass().add("dervafx-check-box");
    }

    @Override
    public DervaCheckBox text(String value) {
        checkBox.setText(value == null ? "" : value);
        return this;
    }

    public boolean selected() {
        return checkBox.isSelected();
    }

    public DervaCheckBox selected(boolean value) {
        checkBox.setSelected(value);
        return this;
    }
}
