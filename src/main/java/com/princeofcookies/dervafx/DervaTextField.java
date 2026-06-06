package com.princeofcookies.dervafx;

import javafx.scene.control.TextField;

public class DervaTextField extends DervaElement<DervaTextField> {
    private final TextField textField;

    public DervaTextField() {
        super(new TextField());
        this.textField = (TextField) contentNode();
        textField.getStyleClass().add("dervafx-text-field");
    }

    @Override
    public DervaTextField text(String value) {
        textField.setText(value == null ? "" : value);
        return this;
    }

    public String value() {
        return textField.getText();
    }

    public DervaTextField prompt(String value) {
        textField.setPromptText(value == null ? "" : value);
        return this;
    }

    public DervaTextField editable(boolean value) {
        textField.setEditable(value);
        return this;
    }
}
