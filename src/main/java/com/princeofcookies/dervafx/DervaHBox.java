package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class DervaHBox extends DervaElement<DervaHBox> {
    private final HBox box;

    public DervaHBox() {
        super(new HBox());
        this.box = (HBox) contentNode();
        box.getStyleClass().add("dervafx-hbox");
        box.setSpacing(6);
        box.setFillHeight(true);
    }

    public DervaHBox add(DervaElement<?> child) {
        if (child != null) {
            box.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaHBox remove(DervaElement<?> child) {
        if (child != null) {
            box.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaHBox clear() {
        box.getChildren().clear();
        return this;
    }

    public DervaHBox spacing(double value) {
        box.setSpacing(value);
        return this;
    }

    public DervaHBox padding(Insets padding) {
        box.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    public DervaHBox padding(double value) {
        box.setPadding(new Insets(value));
        return this;
    }

    public DervaHBox alignment(Pos alignment) {
        box.setAlignment(alignment == null ? Pos.CENTER_LEFT : alignment);
        return this;
    }

    @Override
    public DervaHBox fillWidth() {
        return super.fillWidth();
    }

    @Override
    public DervaHBox fillHeight() {
        box.setFillHeight(true);
        return super.fillHeight();
    }
}
