package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaPanel;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.geometry.Insets;

final class PanelDemo {
    private PanelDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaPanel", "PanelDemo.java", 1060, 24, 300, 230, PanelDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaPanel panel = DervaFX.panel()
                .padding(new Insets(12))
                .spacing(10)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Panel content"))
                .add(DervaFX.textField().prompt("Panel field").fillWidth())
                .add(DervaFX.checkBox("Panel toggle").selected(true))
                .add(DervaFX.button("Panel action"));

        return DervaFX.window("DervaPanel Demo")
                .position(1060, 24)
                .size(300, 230)
                .minSize(240, 180)
                .resizable(true, true)
                .add(panel);
    }
}
