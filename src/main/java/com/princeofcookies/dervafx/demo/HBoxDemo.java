package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaHBox;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class HBoxDemo {
    private HBoxDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaHBox", "HBoxDemo.java", 640, 300, 360, 220, HBoxDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaHBox box = DervaFX.hbox()
                .spacing(10)
                .fillWidth()
                .add(DervaFX.button("Left"))
                .add(DervaFX.textField().prompt("Center").fillWidth())
                .add(DervaFX.button("Right"));

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("HBox lays children out horizontally."))
                .add(box);

        return DervaFX.window("DervaHBox Demo")
                .position(640, 300)
                .size(360, 220)
                .minSize(280, 180)
                .resizable(true, false)
                .add(content);
    }
}
