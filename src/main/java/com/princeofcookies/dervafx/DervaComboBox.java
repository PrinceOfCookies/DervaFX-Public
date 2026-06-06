package com.princeofcookies.dervafx;

import java.util.Collection;

import javafx.scene.control.ComboBox;

@SuppressWarnings("unchecked")
public class DervaComboBox extends DervaElement<DervaComboBox> {
    private final ComboBox<String> comboBox;

    public DervaComboBox() {
        super(new ComboBox<String>());
        this.comboBox = (ComboBox<String>) contentNode();
        comboBox.getStyleClass().add("dervafx-combo-box");
        comboBox.setMaxWidth(Double.MAX_VALUE);
    }

    public DervaComboBox items(Collection<String> values) {
        comboBox.getItems().clear();
        if (values != null) {
            comboBox.getItems().addAll(values);
        }
        return this;
    }

    public DervaComboBox item(String value) {
        comboBox.getItems().add(value == null ? "" : value);
        return this;
    }

    public String value() {
        return comboBox.getValue();
    }

    public DervaComboBox value(String value) {
        comboBox.setValue(value);
        return this;
    }

    public DervaComboBox prompt(String value) {
        comboBox.setPromptText(value == null ? "" : value);
        return this;
    }

    public DervaComboBox editable(boolean value) {
        comboBox.setEditable(value);
        return this;
    }
}
