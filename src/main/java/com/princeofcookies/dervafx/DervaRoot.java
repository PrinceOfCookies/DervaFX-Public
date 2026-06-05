package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DervaRoot extends DervaElement<DervaRoot> {
    private final AnchorPane root;

    public DervaRoot() {
        super(new AnchorPane());
        this.root = (AnchorPane) contentNode();
        root.getStyleClass().add("dervafx-root");
        root.setPickOnBounds(true);
    }

    public Pane getPane() {
        return root;
    }

    public DervaRoot add(DervaElement<?> child) {
        if (child != null) {
            root.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaRoot remove(DervaElement<?> child) {
        if (child != null) {
            root.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaRoot clear() {
        root.getChildren().clear();
        return this;
    }

    @Override
    public DervaRoot padding(Insets padding) {
        root.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }
}
