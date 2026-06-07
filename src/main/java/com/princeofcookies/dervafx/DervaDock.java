package com.princeofcookies.dervafx;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class DervaDock extends DervaElement<DervaDock> {
    private final DockPane dockPane;
    private final List<DervaElement<?>> children;

    public DervaDock() {
        super(new DockPane());
        this.dockPane = (DockPane) contentNode();
        this.children = dockPane.children;
        dockPane.getStyleClass().add("dervafx-dock");
    }

    public DervaDock add(DervaElement<?> child) {
        if (child != null) {
            children.add(child);
            dockPane.getChildren().add(child.getNode());
            dockPane.requestLayout();
        }
        return this;
    }

    public DervaDock remove(DervaElement<?> child) {
        if (child != null) {
            children.remove(child);
            dockPane.getChildren().remove(child.getNode());
            dockPane.requestLayout();
        }
        return this;
    }

    public DervaDock clear() {
        children.clear();
        dockPane.getChildren().clear();
        dockPane.requestLayout();
        return this;
    }

    @Override
    public DervaDock padding(Insets padding) {
        dockPane.setPadding(padding == null ? Insets.EMPTY : padding);
        dockPane.requestLayout();
        return this;
    }

    @Override
    public DervaDock padding(double value) {
        return padding(new Insets(value));
    }

    public DervaDock dockPadding(double value) {
        return padding(value);
    }

    public DervaDock dockPadding(double left, double top, double right, double bottom) {
        return padding(new Insets(top, right, bottom, left));
    }

    private static final class DockPane extends Pane {
        private final List<DervaElement<?>> children = new ArrayList<>();
        private Insets padding = Insets.EMPTY;

        @Override
        protected void layoutChildren() {
            Insets padding = getPadding();
            double left = padding.getLeft();
            double top = padding.getTop();
            double right = getWidth() - padding.getRight();
            double bottom = getHeight() - padding.getBottom();

            for (DervaElement<?> child : children) {
                Node node = child.getNode();
                if (!node.isManaged()) {
                    continue;
                }

                Insets margin = child.dockMargin();
                double areaX = left + margin.getLeft();
                double areaY = top + margin.getTop();
                double areaWidth = Math.max(0.0, right - left - margin.getLeft() - margin.getRight());
                double areaHeight = Math.max(0.0, bottom - top - margin.getTop() - margin.getBottom());

                double prefWidth = computePrefWidth(node, areaWidth);
                double prefHeight = computePrefHeight(node, areaHeight);

                switch (child.dockMode()) {
                    case TOP -> {
                        placeNode(node, areaX, areaY, areaWidth, prefHeight);
                        top += prefHeight + margin.getTop() + margin.getBottom();
                    }
                    case BOTTOM -> {
                        placeNode(node, areaX, bottom - margin.getBottom() - prefHeight, areaWidth, prefHeight);
                        bottom -= prefHeight + margin.getTop() + margin.getBottom();
                    }
                    case LEFT -> {
                        placeNode(node, areaX, areaY, prefWidth, areaHeight);
                        left += prefWidth + margin.getLeft() + margin.getRight();
                    }
                    case RIGHT -> {
                        placeNode(node, right - margin.getRight() - prefWidth, areaY, prefWidth, areaHeight);
                        right -= prefWidth + margin.getLeft() + margin.getRight();
                    }
                    case FILL -> placeNode(node, areaX, areaY, areaWidth, areaHeight);
                    case NONE -> node.relocate(areaX, areaY);
                }
            }
        }

        @Override
        protected double computePrefWidth(double height) {
            double max = 0.0;
            for (DervaElement<?> child : children) {
                max = Math.max(max, computePrefWidth(child.getNode(), -1));
            }
            return max + padding.getLeft() + padding.getRight();
        }

        @Override
        protected double computePrefHeight(double width) {
            double total = 0.0;
            for (DervaElement<?> child : children) {
                total += computePrefHeight(child.getNode(), -1);
            }
            return total + padding.getTop() + padding.getBottom();
        }

        private void placeNode(Node node, double x, double y, double width, double height) {
            if (node instanceof Region region) {
                region.resizeRelocate(x, y, width, height);
            } else {
                layoutInArea(node, x, y, width, height, 0, Insets.EMPTY, true, true, HPos.LEFT, VPos.TOP);
            }
        }

        private static double computePrefWidth(Node node, double height) {
            if (node instanceof Region region) {
                return region.prefWidth(height);
            }
            return node.getLayoutBounds().getWidth();
        }

        private static double computePrefHeight(Node node, double width) {
            if (node instanceof Region region) {
                return region.prefHeight(width);
            }
            return node.getLayoutBounds().getHeight();
        }
    }
}
