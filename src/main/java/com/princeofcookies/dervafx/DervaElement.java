package com.princeofcookies.dervafx;

import java.util.Objects;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class DervaElement<T extends DervaElement<T>> {
    private final StackPane root;
    private final Node contentNode;
    private DervaDockMode dockMode = DervaDockMode.NONE;
    private Insets dockMargin = Insets.EMPTY;

    protected DervaElement(Node contentNode) {
        this.contentNode = Objects.requireNonNull(contentNode, "contentNode");
        this.root = new StackPane(contentNode);
        root.getStyleClass().add("dervafx-element");
    }

    public Node getNode() {
        return root;
    }

    protected final StackPane rootNode() {
        return root;
    }

    protected final Node contentNode() {
        return contentNode;
    }

    public T position(double x, double y) {
        root.setLayoutX(x);
        root.setLayoutY(y);
        return self();
    }

    public T setPos(double x, double y) {
        return position(x, y);
    }

    public T size(double width, double height) {
        root.setPrefSize(width, height);
        if (contentNode instanceof Region region) {
            region.setPrefSize(width, height);
        }
        return self();
    }

    public T setSize(double width, double height) {
        return size(width, height);
    }

    public T minSize(double width, double height) {
        root.setMinSize(width, height);
        if (contentNode instanceof Region region) {
            region.setMinSize(width, height);
        }
        return self();
    }

    public T maxSize(double width, double height) {
        root.setMaxSize(width, height);
        if (contentNode instanceof Region region) {
            region.setMaxSize(width, height);
        }
        return self();
    }

    public T padding(Insets insets) {
        if (contentNode instanceof Region region) {
            region.setPadding(insets == null ? Insets.EMPTY : insets);
        } else {
            root.setPadding(insets == null ? Insets.EMPTY : insets);
        }
        return self();
    }

    public T padding(double value) {
        return padding(new Insets(value));
    }

    public T spacing(double value) {
        if (contentNode instanceof VBox box) {
            box.setSpacing(value);
        } else if (contentNode instanceof HBox box) {
            box.setSpacing(value);
        }
        return self();
    }

    public T align(Pos pos) {
        Pos alignment = pos == null ? Pos.TOP_LEFT : pos;
        if (contentNode instanceof VBox box) {
            box.setAlignment(alignment);
        } else if (contentNode instanceof HBox box) {
            box.setAlignment(alignment);
        } else {
            StackPane.setAlignment(contentNode, alignment);
        }
        return self();
    }

    public T fillWidth() {
        root.setMaxWidth(Double.MAX_VALUE);
        if (contentNode instanceof Region region) {
            region.setMaxWidth(Double.MAX_VALUE);
        }
        if (contentNode instanceof VBox box) {
            box.setFillWidth(true);
        }
        HBox.setHgrow(root, Priority.ALWAYS);
        return self();
    }

    public T fillHeight() {
        root.setMaxHeight(Double.MAX_VALUE);
        if (contentNode instanceof Region region) {
            region.setMaxHeight(Double.MAX_VALUE);
        }
        VBox.setVgrow(root, Priority.ALWAYS);
        return self();
    }

    public T text(String value) {
        if (contentNode instanceof Labeled labeled) {
            labeled.setText(value == null ? "" : value);
        }
        return self();
    }

    public T visible(boolean value) {
        root.setVisible(value);
        root.setManaged(value);
        return self();
    }

    public T setVisible(boolean value) {
        return visible(value);
    }

    public T enabled(boolean value) {
        root.setDisable(!value);
        return self();
    }

    public T setEnabled(boolean value) {
        return enabled(value);
    }

    public T dock(DervaDockMode mode) {
        this.dockMode = mode == null ? DervaDockMode.NONE : mode;
        return self();
    }

    public T dockNone() {
        return dock(DervaDockMode.NONE);
    }

    public T dockTop() {
        return dock(DervaDockMode.TOP);
    }

    public T dockBottom() {
        return dock(DervaDockMode.BOTTOM);
    }

    public T dockLeft() {
        return dock(DervaDockMode.LEFT);
    }

    public T dockRight() {
        return dock(DervaDockMode.RIGHT);
    }

    public T dockFill() {
        return dock(DervaDockMode.FILL);
    }

    public T dockMargin(double left, double top, double right, double bottom) {
        this.dockMargin = new Insets(top, right, bottom, left);
        return self();
    }

    final DervaDockMode dockMode() {
        return dockMode;
    }

    final Insets dockMargin() {
        return dockMargin;
    }

    public T cssClass(String className) {
        if (className == null) {
            return self();
        }
        String trimmed = className.trim();
        if (!trimmed.isEmpty() && !contentNode.getStyleClass().contains(trimmed)) {
            contentNode.getStyleClass().add(trimmed);
        }
        return self();
    }

    public T style(String inlineStyle) {
        if (inlineStyle != null && !inlineStyle.isBlank()) {
            contentNode.setStyle(inlineStyle);
        }
        return self();
    }

    public T anchorFill() {
        return fillWidth().fillHeight();
    }

    @SuppressWarnings("unchecked")
    protected final T self() {
        return (T) this;
    }
}
