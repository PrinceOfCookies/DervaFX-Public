package com.princeofcookies.dervafx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DervaWindow extends DervaElement<DervaWindow> {
    private final VBox window;
    private final HBox titleBar;
    private final Label titleLabel;
    private final Button closeButton;
    private final VBox content;
    private boolean draggable;
    private double dragOffsetX;
    private double dragOffsetY;

    public DervaWindow(String title) {
        super(new VBox());
        this.window = (VBox) contentNode();
        this.titleBar = new HBox();
        this.titleLabel = new Label(title == null ? "" : title);
        this.closeButton = new Button("x");
        this.content = new VBox(6);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        window.getStyleClass().add("dervafx-window");
        window.setSpacing(0);
        window.setFillWidth(true);
        window.setPrefSize(320, 220);

        titleBar.getStyleClass().add("dervafx-window-titlebar");
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setSpacing(8);
        titleBar.setPadding(new Insets(8, 10, 8, 10));

        titleLabel.getStyleClass().add("dervafx-window-title");

        closeButton.getStyleClass().add("dervafx-window-close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(event -> visible(false));

        titleBar.getChildren().addAll(titleLabel, spacer, closeButton);

        content.getStyleClass().add("dervafx-window-content");
        content.setPadding(new Insets(10));
        content.setFillWidth(true);

        window.getChildren().addAll(titleBar, content);

        installDragHandlers();
        draggable(true);
    }

    @Override
    public DervaWindow text(String value) {
        titleLabel.setText(value == null ? "" : value);
        return this;
    }

    public DervaWindow draggable(boolean value) {
        this.draggable = value;
        titleBar.setCursor(value ? Cursor.MOVE : Cursor.DEFAULT);
        return this;
    }

    public DervaWindow add(DervaElement<?> child) {
        if (child != null) {
            content.getChildren().add(child.getNode());
        }
        return this;
    }

    public DervaWindow remove(DervaElement<?> child) {
        if (child != null) {
            content.getChildren().remove(child.getNode());
        }
        return this;
    }

    public DervaWindow clear() {
        content.getChildren().clear();
        return this;
    }

    @Override
    public DervaWindow padding(Insets padding) {
        content.setPadding(padding == null ? Insets.EMPTY : padding);
        return this;
    }

    @Override
    public DervaWindow spacing(double value) {
        content.setSpacing(value);
        return this;
    }

    @Override
    public DervaWindow align(Pos alignment) {
        content.setAlignment(alignment == null ? Pos.TOP_LEFT : alignment);
        return this;
    }

    @Override
    public DervaWindow fillWidth() {
        content.setFillWidth(true);
        return super.fillWidth();
    }

    @Override
    public DervaWindow size(double width, double height) {
        super.size(width, height);
        window.setPrefSize(width, height);
        return this;
    }

    private void installDragHandlers() {
        titleBar.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (!draggable || isCloseButtonEvent(event)) {
                return;
            }
            dragOffsetX = event.getSceneX() - rootNode().getLayoutX();
            dragOffsetY = event.getSceneY() - rootNode().getLayoutY();
            event.consume();
        });

        titleBar.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (!draggable || isCloseButtonEvent(event)) {
                return;
            }
            rootNode().setLayoutX(event.getSceneX() - dragOffsetX);
            rootNode().setLayoutY(event.getSceneY() - dragOffsetY);
            event.consume();
        });

        titleBar.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (draggable && !isCloseButtonEvent(event)) {
                event.consume();
            }
        });
    }

    private boolean isCloseButtonEvent(MouseEvent event) {
        Node target = event.getPickResult() == null ? null : event.getPickResult().getIntersectedNode();
        while (target != null) {
            if (target == closeButton) {
                return true;
            }
            target = target.getParent();
        }
        return false;
    }
}
