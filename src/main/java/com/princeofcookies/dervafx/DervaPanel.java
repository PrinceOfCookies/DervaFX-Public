package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class DervaPanel extends DervaElement<DervaPanel> {
    private final VBox panel;

    public DervaPanel() {
        super(new VBox());
        this.panel = (VBox) contentNode();
        panel.getStyleClass().add("dervafx-panel");
        panel.setSpacing(6);
        panel.setPadding(new Insets(8));
        panel.setFillWidth(true);
    }

    public DervaPanel add(DervaElement<?> child) {
        if (child != null) {
            panel.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaPanel remove(DervaElement<?> child) {
        if (child != null) {
            panel.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaPanel clear() {
        panel.getChildren().clear();
        return this;
    }

    @Override
    public DervaPanel padding(Insets padding) {
        panel.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    @Override
    public DervaPanel spacing(double value) {
        panel.setSpacing(value);
        return this;
    }

    @Override
    public DervaPanel align(Pos pos) {
        panel.setAlignment(pos == null ? Pos.TOP_LEFT : pos);
        return this;
    }

    @Override
    public DervaPanel fillWidth() {
        panel.setFillWidth(true);
        return super.fillWidth();
    }
}
