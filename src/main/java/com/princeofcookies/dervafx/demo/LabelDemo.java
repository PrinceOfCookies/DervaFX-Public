package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class LabelDemo {
    private LabelDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaLabel", "LabelDemo.java", 320, 600, 280, 200, LabelDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Standard label"))
                .add(DervaFX.label("Labels inherit shared DervaElement helpers."))
                .add(DervaFX.label("Use them for titles, body text, and status lines."));

        return DervaFX.window("DervaLabel Demo")
                .position(320, 600)
                .size(280, 200)
                .minSize(240, 160)
                .resizable(true, true)
                .add(content);
    }
}
