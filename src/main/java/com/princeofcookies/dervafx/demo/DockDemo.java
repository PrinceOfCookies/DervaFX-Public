package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaDock;
import com.princeofcookies.dervafx.DervaWindow;

final class DockDemo {
    private DockDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaDock", "DockDemo.java", 1040, 560, 320, 260, DockDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaDock dock = DervaFX.dock()
                .dockPadding(6)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Top Bar").dockTop().dockMargin(0, 0, 0, 6))
                .add(DervaFX.button("Left Dock").dockLeft().dockMargin(0, 0, 6, 0).setSize(90, 0))
                .add(DervaFX.button("Right Dock").dockRight().dockMargin(6, 0, 0, 0).setSize(90, 0))
                .add(DervaFX.textArea()
                        .text("DockFill takes the remaining space, similar to Derma dock layout.")
                        .fillWidth()
                        .fillHeight()
                        .dockFill());

        return DervaFX.window("DervaDock Demo")
                .position(1040, 560)
                .size(320, 260)
                .minSize(260, 200)
                .resizable(true, true)
                .add(dock);
    }
}
