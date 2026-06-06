package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaGrid;
import com.princeofcookies.dervafx.DervaWindow;

final class GridDemo {
    private GridDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaGrid", "GridDemo.java", 1020, 320, 340, 300, GridDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaGrid grid = DervaFX.grid()
                .hgap(10)
                .vgap(10)
                .padding(4)
                .debugGrid(true)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Project"), 0, 0)
                .add(DervaFX.textField().prompt("Name").fillWidth(), 1, 0)
                .add(DervaFX.label("Theme"), 0, 1)
                .add(DervaFX.comboBox().item("Dark").item("Light").value("Dark").fillWidth(), 1, 1)
                .add(DervaFX.checkBox("Span row").selected(true), 0, 2, 2, 1)
                .add(DervaFX.button("Apply"), 0, 3)
                .add(DervaFX.button("Preview").fillWidth(), 1, 3);

        return DervaFX.window("DervaGrid Demo")
                .position(1020, 320)
                .size(340, 300)
                .minSize(280, 220)
                .resizable(true, true)
                .add(grid);
    }
}
