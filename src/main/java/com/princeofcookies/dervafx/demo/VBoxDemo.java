package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class VBoxDemo {
    private VBoxDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaVBox", "VBoxDemo.java", 320, 300, 300, 280, VBoxDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaVBox box = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Row 1"))
                .add(DervaFX.button("Row 2"))
                .add(DervaFX.textField().prompt("Row 3").fillWidth())
                .add(DervaFX.textArea().prompt("Row 4").size(0, 110).fillWidth().fillHeight());

        return DervaFX.window("DervaVBox Demo")
                .position(320, 300)
                .size(300, 280)
                .minSize(240, 220)
                .resizable(true, true)
                .add(box);
    }
}
