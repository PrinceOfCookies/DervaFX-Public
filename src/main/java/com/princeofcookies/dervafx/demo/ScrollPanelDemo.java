package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaScrollPanel;
import com.princeofcookies.dervafx.DervaWindow;

final class ScrollPanelDemo {
    private ScrollPanelDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaScrollPanel", "ScrollPanelDemo.java", 760, 1340, 380, 260, ScrollPanelDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaScrollPanel scroll = DervaFX.scrollPanel()
                .spacing(8)
                .padding(6)
                .fillWidth()
                .fillHeight();

        for (int i = 1; i <= 16; i++) {
            scroll.add(DervaFX.panel()
                    .padding(6)
                    .spacing(4)
                    .fillWidth()
                    .add(DervaFX.label("Entry " + i))
                    .add(DervaFX.textField().prompt("Value " + i).fillWidth()));
        }

        return DervaFX.window("DervaScrollPanel Demo")
                .position(760, 1340)
                .size(380, 260)
                .minSize(300, 220)
                .resizable(true, true)
                .add(scroll);
    }
}
