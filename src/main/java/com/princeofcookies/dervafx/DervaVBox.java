package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class DervaVBox extends DervaElement<DervaVBox> {
    private final VBox box;

    public DervaVBox() {
        super(new VBox());
        this.box = (VBox) contentNode();
        box.getStyleClass().add("dervafx-vbox");
        box.setSpacing(6);
        box.setFillWidth(true);
    }

    public DervaVBox add(DervaElement<?> child) {
        if (child != null) {
            box.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaVBox remove(DervaElement<?> child) {
        if (child != null) {
            box.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaVBox clear() {
        box.getChildren().clear();
        return this;
    }

    public DervaVBox spacing(double value) {
        box.setSpacing(value);
        return this;
    }

    public DervaVBox padding(Insets padding) {
        box.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    public DervaVBox padding(double value) {
        box.setPadding(new Insets(value));
        return this;
    }

    public DervaVBox alignment(Pos alignment) {
        box.setAlignment(alignment == null ? Pos.TOP_LEFT : alignment);
        return this;
    }

    @Override
    public DervaVBox fillWidth() {
        box.setFillWidth(true);
        return super.fillWidth();
    }

    @Override
    public DervaVBox fillHeight() {
        return super.fillHeight();
    }
}
