package com.princeofcookies.dervafx;

import javafx.scene.Node;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;

public class DervaPropertySheet extends DervaElement<DervaPropertySheet> {
    private final TabPane tabPane;

    public DervaPropertySheet() {
        super(new TabPane());
        this.tabPane = (TabPane) contentNode();
        tabPane.getStyleClass().add("dervafx-property-sheet");
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public DervaPropertySheet addPage(String title, DervaElement<?> content) {
        Tab tab = new Tab(title == null ? "" : title);
        tab.setClosable(false);
        Node node = content == null ? null : content.getNode();
        if (node instanceof Region region) {
            region.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            region.setMinSize(0, 0);
        }
        tab.setContent(node);
        tabPane.getTabs().add(tab);
        if (tabPane.getTabs().size() == 1) {
            tabPane.getSelectionModel().select(0);
        }
        return this;
    }

    public DervaPropertySheet clear() {
        tabPane.getTabs().clear();
        return this;
    }

    public DervaPropertySheet selectedPage(int index) {
        if (index >= 0 && index < tabPane.getTabs().size()) {
            tabPane.getSelectionModel().select(index);
        }
        return this;
    }
}
