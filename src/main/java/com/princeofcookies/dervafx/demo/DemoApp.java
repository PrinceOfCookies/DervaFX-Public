package com.princeofcookies.dervafx.demo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaGrid;
import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public final class DemoApp extends Application {
    private static final double HUB_X = 24.0;
    private static final double HUB_Y = 24.0;

    @Override
    public void start(Stage stage) {
        AnchorPane host = new AnchorPane();

        DervaRoot root = DervaFX.root().anchorFill();
        host.getChildren().add(root.getNode());
        AnchorPane.setTopAnchor(root.getNode(), 0.0);
        AnchorPane.setRightAnchor(root.getNode(), 0.0);
        AnchorPane.setBottomAnchor(root.getNode(), 0.0);
        AnchorPane.setLeftAnchor(root.getNode(), 0.0);

        Scene scene = new Scene(host, 1480, 1620);
        DervaFX.applyTheme(scene);

        List<DemoSpec> specs = List.of(
                FactoryDemo.spec(),
                ElementDemo.spec(),
                RootDemo.spec(),
                WindowDemo.spec(),
                PanelDemo.spec(),
                VBoxDemo.spec(),
                HBoxDemo.spec(),
                GridDemo.spec(),
                LabelDemo.spec(),
                ButtonDemo.spec(),
                TextFieldDemo.spec(),
                TextAreaDemo.spec(),
                CheckBoxDemo.spec(),
                ComboBoxDemo.spec(),
                ThemeDemo.spec(),
                ThemeManagerDemo.spec());

        Map<DemoSpec, DervaWindow> windows = new LinkedHashMap<>();
        for (DemoSpec spec : specs) {
            DervaWindow window = spec.create(root, scene).visible(false);
            root.add(window);
            windows.put(spec, window);
        }

        Runnable hideAll = () -> windows.values().forEach(window -> window.visible(false));
        Runnable resetAll = () -> windows.forEach(DemoSpec::reset);
        Runnable openAll = () -> {
            windows.forEach((spec, window) -> {
                spec.reset(window);
                window.visible(true).toFront();
            });
        };

        DervaGrid menuGrid = DervaFX.grid()
                .hgap(10)
                .vgap(8)
                .padding(4)
                .fillWidth();

        int row = 0;
        for (DemoSpec spec : specs) {
            DervaWindow target = windows.get(spec);
            menuGrid.add(DervaFX.button("Open " + spec.title()).onClick(() -> target.visible(true).toFront()), 0, row);
            menuGrid.add(DervaFX.label(spec.fileName()), 1, row);
            row++;
        }

        DervaVBox hubContent = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Demo menu"))
                .add(DervaFX.label("Each DervaFX surface has its own demo source file."))
                .add(menuGrid)
                .add(DervaFX.button("Open Every Demo").onClick(openAll))
                .add(DervaFX.button("Hide Every Demo").onClick(hideAll))
                .add(DervaFX.button("Reset Demo Layout").onClick(resetAll))
                .add(DervaFX.panel()
                        .padding(new Insets(10))
                        .spacing(8)
                        .fillWidth()
                        .add(DervaFX.label("Theme note"))
                        .add(DervaFX.label("ThemeManagerDemo can swap the entire app theme live.")));

        DervaWindow hubWindow = DervaFX.window("DervaFX Demo Menu")
                .position(HUB_X, HUB_Y)
                .size(280, 920)
                .minSize(240, 320)
                .resizable(false, true)
                .add(hubContent);

        root.add(hubWindow);

        stage.setTitle("DervaFX Demo Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
