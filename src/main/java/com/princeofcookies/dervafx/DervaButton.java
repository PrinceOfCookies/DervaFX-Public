package com.princeofcookies.dervafx;

import javafx.scene.control.Button;

public class DervaButton extends DervaElement<DervaButton> {
    private final Button button;

    public DervaButton(String text) {
        super(new Button(text == null ? "" : text));
        this.button = (Button) contentNode();
        button.getStyleClass().add("dervafx-button");
    }

    public DervaButton onClick(Runnable action) {
        button.setOnAction(event -> {
            if (action != null) {
                action.run();
            }
        });
        return this;
    }
}
