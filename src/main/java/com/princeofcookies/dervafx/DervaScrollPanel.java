package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DervaScrollPanel extends DervaElement<DervaScrollPanel> {
    private final ScrollPane scrollPane;
    private final VBox content;

    public DervaScrollPanel() {
        super(new ScrollPane());
        this.scrollPane = (ScrollPane) contentNode();
        this.content = new VBox(6);

        scrollPane.getStyleClass().add("dervafx-scroll-panel");
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setContent(content);

        content.getStyleClass().add("dervafx-scroll-panel-content");
        content.setFillWidth(true);
        content.setPadding(new Insets(6));
    }

    public DervaScrollPanel add(DervaElement<?> child) {
        if (child != null) {
            if (child.getNode() instanceof javafx.scene.layout.Region region) {
                region.setMaxWidth(Double.MAX_VALUE);
                region.setMinHeight(0);
            }
            VBox.setVgrow(child.getNode(), Priority.NEVER);
            content.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaScrollPanel remove(DervaElement<?> child) {
        if (child != null) {
            content.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaScrollPanel clear() {
        content.getChildren().clear();
        return this;
    }

    @Override
    public DervaScrollPanel padding(Insets padding) {
        content.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    @Override
    public DervaScrollPanel padding(double value) {
        content.setPadding(new Insets(value));
        return this;
    }

    @Override
    public DervaScrollPanel spacing(double value) {
        content.setSpacing(value);
        return this;
    }

    @Override
    public DervaScrollPanel align(Pos pos) {
        content.setAlignment(pos == null ? Pos.TOP_LEFT : pos);
        return this;
    }
}
