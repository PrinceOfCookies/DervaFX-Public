package com.princeofcookies.dervafx;

import javafx.scene.control.TextArea;

public class DervaTextArea extends DervaElement<DervaTextArea> {
    private final TextArea textArea;

    public DervaTextArea() {
        super(new TextArea());
        this.textArea = (TextArea) contentNode();
        textArea.getStyleClass().add("dervafx-text-area");
        textArea.setWrapText(true);
    }

    @Override
    public DervaTextArea text(String value) {
        textArea.setText(value == null ? "" : value);
        return this;
    }

    public String value() {
        return textArea.getText();
    }

    public DervaTextArea prompt(String value) {
        textArea.setPromptText(value == null ? "" : value);
        return this;
    }

    public DervaTextArea editable(boolean value) {
        textArea.setEditable(value);
        return this;
    }

    public DervaTextArea wrap(boolean value) {
        textArea.setWrapText(value);
        return this;
    }
}
