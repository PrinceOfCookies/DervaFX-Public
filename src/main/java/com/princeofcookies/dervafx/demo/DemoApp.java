package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaPanel;
import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class BarebonesDemoApp extends Application {
    @Override
    public void start(Stage stage) {
        AnchorPane host = new AnchorPane();

        DervaRoot root = DervaFX.root().anchorFill();
        host.getChildren().add(root.getNode());
        AnchorPane.setTopAnchor(root.getNode(), 0.0);
        AnchorPane.setRightAnchor(root.getNode(), 0.0);
        AnchorPane.setBottomAnchor(root.getNode(), 0.0);
        AnchorPane.setLeftAnchor(root.getNode(), 0.0);

        DervaPanel panel = DervaFX.panel()
                .padding(new Insets(12))
                .spacing(10)
                .add(DervaFX.label("DervaFX demo"))
                .add(DervaFX.button("Test Button").onClick(() -> System.out.println("Clicked test button")));

        DervaVBox stack = DervaFX.vbox()
                .spacing(8)
                .add(DervaFX.label("Small chainable wrapper base"))
                .add(panel);

        DervaWindow window = DervaFX.window("Base Window")
                .position(24, 24)
                .size(360, 220)
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
