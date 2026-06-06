package com.princeofcookies.dervafx;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class DervaWindow extends DervaElement<DervaWindow> {
    private static final double RESIZE_MARGIN = 10.0;
    private static final double DEFAULT_MIN_WIDTH = 240.0;
    private static final double DEFAULT_MIN_HEIGHT = 160.0;

    private final VBox window;
    private final HBox titleBar;
    private final Label titleLabel;
    private final Button closeButton;
    private final VBox content;
    private boolean draggable;
    private boolean resizableHorizontal;
    private boolean resizableVertical;
    private double dragOffsetX;
    private double dragOffsetY;
    private boolean resizing;
    private boolean resizingEast;
    private boolean resizingSouth;
    private double resizeStartSceneX;
    private double resizeStartSceneY;
    private double resizeStartWidth;
    private double resizeStartHeight;

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
        content.setMinHeight(0);
        VBox.setVgrow(content, Priority.ALWAYS);

        Rectangle contentClip = new Rectangle();
        content.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            contentClip.setWidth(newBounds.getWidth());
            contentClip.setHeight(newBounds.getHeight());
        });
        content.setClip(contentClip);

        window.getChildren().addAll(titleBar, content);

        rootNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> rootNode().toFront());
        installDragHandlers();
        installResizeHandlers();
        minSize(DEFAULT_MIN_WIDTH, DEFAULT_MIN_HEIGHT);
        draggable(true);
        resizable(false);
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

    public DervaWindow resizable(boolean value) {
        return resizable(value, value);
    }

    public DervaWindow resizable(boolean horizontal, boolean vertical) {
        this.resizableHorizontal = horizontal;
        this.resizableVertical = vertical;
        if (!horizontal && !vertical) {
            resizing = false;
            resizingEast = false;
            resizingSouth = false;
            rootNode().setCursor(Cursor.DEFAULT);
        }
        return this;
    }

    public DervaWindow resizableHorizontal(boolean value) {
        return resizable(value, resizableVertical);
    }

    public DervaWindow resizableVertical(boolean value) {
        return resizable(resizableHorizontal, value);
    }

    public DervaWindow add(DervaElement<?> child) {
        if (child != null) {
            promoteWindowChild(child.getNode());
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

    public DervaWindow toFront() {
        rootNode().toFront();
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

    @Override
    public DervaWindow minSize(double width, double height) {
        super.minSize(width, height);
        window.setMinSize(width, height);
        return this;
    }

    @Override
    public DervaWindow maxSize(double width, double height) {
        super.maxSize(width, height);
        window.setMaxSize(width, height);
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
            rootNode().setLayoutX(clampHorizontalPosition(event.getSceneX() - dragOffsetX));
            rootNode().setLayoutY(clampVerticalPosition(event.getSceneY() - dragOffsetY));
            event.consume();
        });

        titleBar.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (draggable && !isCloseButtonEvent(event)) {
                event.consume();
            }
        });
    }

    private void installResizeHandlers() {
        rootNode().addEventHandler(MouseEvent.MOUSE_MOVED, event -> {
            if (resizing) {
                return;
            }
            updateResizeCursor(event);
        });

        rootNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            if (!resizing) {
                rootNode().setCursor(Cursor.DEFAULT);
            }
        });

        rootNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            if (isCloseButtonEvent(event)) {
                return;
            }
            ResizeEdges edges = detectResizeEdges(event);
            if (!edges.active()) {
                return;
            }
            resizing = true;
            resizingEast = edges.east();
            resizingSouth = edges.south();
            resizeStartSceneX = event.getSceneX();
            resizeStartSceneY = event.getSceneY();
            resizeStartWidth = Math.max(window.getWidth(), window.getPrefWidth());
            resizeStartHeight = Math.max(window.getHeight(), window.getPrefHeight());
            rootNode().setCursor(edges.cursor());
            event.consume();
        });

        rootNode().addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            if (!resizing) {
                return;
            }

            double nextWidth = resizeStartWidth;
            double nextHeight = resizeStartHeight;
            if (resizingEast) {
                nextWidth = clampWidth(resizeStartWidth + (event.getSceneX() - resizeStartSceneX));
            }
            if (resizingSouth) {
                nextHeight = clampHeight(resizeStartHeight + (event.getSceneY() - resizeStartSceneY));
            }
            size(nextWidth, nextHeight);
            event.consume();
        });

        rootNode().addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            if (!resizing) {
                return;
            }
            resizing = false;
            resizingEast = false;
            resizingSouth = false;
            updateResizeCursor(event);
            event.consume();
        });
    }

    private void updateResizeCursor(MouseEvent event) {
        ResizeEdges edges = detectResizeEdges(event);
        rootNode().setCursor(edges.cursor());
    }

    private ResizeEdges detectResizeEdges(MouseEvent event) {
        if ((!resizableHorizontal && !resizableVertical) || isCloseButtonEvent(event)) {
            return ResizeEdges.none();
        }

        Bounds bounds = rootNode().getLayoutBounds();
        double x = event.getX();
        double y = event.getY();
        boolean east = resizableHorizontal && x >= bounds.getWidth() - RESIZE_MARGIN;
        boolean south = resizableVertical && y >= bounds.getHeight() - RESIZE_MARGIN;
        return new ResizeEdges(east, south);
    }

    private double clampHorizontalPosition(double x) {
        Parent parent = rootNode().getParent();
        if (parent == null) {
            return x;
        }
        double maxX = Math.max(0.0, parent.getLayoutBounds().getWidth() - rootNode().getLayoutBounds().getWidth());
        return clamp(x, 0.0, maxX);
    }

    private double clampVerticalPosition(double y) {
        Parent parent = rootNode().getParent();
        if (parent == null) {
            return y;
        }
        double maxY = Math.max(0.0, parent.getLayoutBounds().getHeight() - rootNode().getLayoutBounds().getHeight());
        return clamp(y, 0.0, maxY);
    }

    private double clampWidth(double width) {
        double minWidth = resolveMinWidth();
        double maxWidth = resolveMaxWidth();
        return clamp(width, minWidth, maxWidth);
    }

    private double clampHeight(double height) {
        double minHeight = resolveMinHeight();
        double maxHeight = resolveMaxHeight();
        return clamp(height, minHeight, maxHeight);
    }

    private double resolveMinWidth() {
        return window.getMinWidth() > 0 ? window.getMinWidth() : DEFAULT_MIN_WIDTH;
    }

    private double resolveMinHeight() {
        return window.getMinHeight() > 0 ? window.getMinHeight() : DEFAULT_MIN_HEIGHT;
    }

    private double resolveMaxWidth() {
        Parent parent = rootNode().getParent();
        double bound = parent == null
                ? Double.MAX_VALUE
                : Math.max(resolveMinWidth(), parent.getLayoutBounds().getWidth() - rootNode().getLayoutX());
        double configured = window.getMaxWidth();
        if (configured > 0 && configured < Double.MAX_VALUE) {
            return Math.max(resolveMinWidth(), Math.min(bound, configured));
        }
        return bound;
    }

    private double resolveMaxHeight() {
        Parent parent = rootNode().getParent();
        double bound = parent == null
                ? Double.MAX_VALUE
                : Math.max(resolveMinHeight(), parent.getLayoutBounds().getHeight() - rootNode().getLayoutY());
        double configured = window.getMaxHeight();
        if (configured > 0 && configured < Double.MAX_VALUE) {
            return Math.max(resolveMinHeight(), Math.min(bound, configured));
        }
        return bound;
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private void promoteWindowChild(Node node) {
        VBox.setVgrow(node, Priority.ALWAYS);
        if (node instanceof Region region) {
            region.setMaxWidth(Double.MAX_VALUE);
            region.setMaxHeight(Double.MAX_VALUE);
            region.setMinHeight(0);
        }
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

    private record ResizeEdges(boolean east, boolean south) {
        private static ResizeEdges none() {
            return new ResizeEdges(false, false);
        }

        private boolean active() {
            return east || south;
        }

        private Cursor cursor() {
            if (east && south) {
                return Cursor.SE_RESIZE;
            }
            if (east) {
                return Cursor.E_RESIZE;
            }
            if (south) {
                return Cursor.S_RESIZE;
            }
            return Cursor.DEFAULT;
        }
    }
}
