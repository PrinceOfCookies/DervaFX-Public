package com.princeofcookies.dervafx;

import javafx.scene.control.Label;

public class DervaLabel extends DervaElement<DervaLabel> {
    public DervaLabel(String text) {
        super(new Label(text == null ? "" : text));
        contentNode().getStyleClass().add("dervafx-label");
    }
}
