package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaGrid;
import com.princeofcookies.dervafx.DervaPanel;
import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class DemoApp extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane host = new AnchorPane();

        DervaRoot root = DervaFX.root().anchorFill();
        host.getChildren().add(root.getNode());
        AnchorPane.setTopAnchor(root.getNode(), 0.0);
        AnchorPane.setRightAnchor(root.getNode(), 0.0);
        AnchorPane.setBottomAnchor(root.getNode(), 0.0);
        AnchorPane.setLeftAnchor(root.getNode(), 0.0);

        DervaGrid settingsGrid = DervaFX.grid()
                .hgap(10)
                .vgap(10)
                .debugGrid(true)
                .add(DervaFX.label("Project"), 0, 0)
                .add(DervaFX.textField().prompt("Project name").fillWidth(), 1, 0)
                .add(DervaFX.label("Theme"), 0, 1)
                .add(DervaFX.comboBox()
                        .prompt("Theme")
                        .item("Dark")
                        .item("Light")
                        .value("Dark")
                        .fillWidth(), 1, 1)
                .add(DervaFX.label("Profile"), 0, 2)
                .add(DervaFX.textField().prompt("Default workspace").fillWidth(), 1, 2)
                .add(DervaFX.checkBox("Remember layout").selected(true), 0, 3, 2, 1)
                .add(DervaFX.label("Notes"), 0, 4)
                .add(DervaFX.textArea().prompt("Notes").size(0, 92).fillWidth(), 1, 4)
                .add(DervaFX.button("Apply"), 0, 5)
                .add(DervaFX.button("Preview").fillWidth(), 1, 5);

        DervaPanel panel = DervaFX.panel()
                .padding(new Insets(12))
                .spacing(10)
                .add(DervaFX.label("DervaFX grid demo"))
                .add(settingsGrid)
                .add(DervaFX.button("Test Button").onClick(() -> System.out.println("Clicked test button")));

        DervaVBox stack = DervaFX.vbox()
                .spacing(8)
                .add(DervaFX.label("Small chainable wrapper base with basic inputs and grid layout"))
                .add(panel);

        DervaWindow window = DervaFX.window("Base Window")
                .position(24, 24)
                .size(520, 360)
                .add(stack);

        root.add(window);

        Scene scene = new Scene(host, 900, 600);
        DervaFX.applyTheme(scene);

        stage.setTitle("DervaFX Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
