package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class DervaGrid extends DervaElement<DervaGrid> {
    private final GridPane grid;
    private boolean debugGrid;

    public DervaGrid() {
        super(new GridPane());
        this.grid = (GridPane) contentNode();
        grid.getStyleClass().add("dervafx-grid");
        grid.setHgap(8);
        grid.setVgap(8);
    }

    public DervaGrid add(DervaElement<?> child, int column, int row) {
        if (child != null) {
            grid.add(child.getNode(), column, row);
            applyDebugStyle(child);
        }
        return this;
    }

    public DervaGrid add(DervaElement<?> child, int column, int row, int columnSpan, int rowSpan) {
        if (child != null) {
            grid.add(child.getNode(), column, row, columnSpan, rowSpan);
            applyDebugStyle(child);
        }
        return this;
    }

    public DervaGrid remove(DervaElement<?> child) {
        if (child != null) {
            grid.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaGrid clear() {
        grid.getChildren().clear();
        return this;
    }

    public DervaGrid hgap(double value) {
        grid.setHgap(value);
        return this;
    }

    public DervaGrid vgap(double value) {
        grid.setVgap(value);
        return this;
    }

    public DervaGrid debugGrid(boolean value) {
        this.debugGrid = value;
        grid.setGridLinesVisible(value);
        if (value) {
            if (!grid.getStyleClass().contains("dervafx-grid-debug")) {
                grid.getStyleClass().add("dervafx-grid-debug");
            }
        } else {
            grid.getStyleClass().remove("dervafx-grid-debug");
        }
        grid.getChildren().forEach(node -> {
            if (value) {
                if (!node.getStyleClass().contains("dervafx-grid-cell-debug")) {
                    node.getStyleClass().add("dervafx-grid-cell-debug");
                }
            } else {
                node.getStyleClass().remove("dervafx-grid-cell-debug");
            }
        });
        return this;
    }

    @Override
    public DervaGrid spacing(double value) {
        return hgap(value).vgap(value);
    }

    @Override
    public DervaGrid padding(Insets padding) {
        grid.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    @Override
    public DervaGrid padding(double value) {
        grid.setPadding(new Insets(value));
        return this;
    }

    public DervaGrid alignment(Pos alignment) {
        grid.setAlignment(alignment == null ? Pos.TOP_LEFT : alignment);
        return this;
    }

    private void applyDebugStyle(DervaElement<?> child) {
        if (!debugGrid || child == null) {
            return;
        }
        if (!child.getNode().getStyleClass().contains("dervafx-grid-cell-debug")) {
            child.getNode().getStyleClass().add("dervafx-grid-cell-debug");
        }
    }
}
