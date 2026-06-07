package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class FactoryDemo {
    private FactoryDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaFX", "FactoryDemo.java", 660, 580, 360, 250, FactoryDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaLabel status = DervaFX.label("Use DervaFX as the factory surface.");

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(6)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Factory methods"))
                .add(DervaFX.label("root, panel, vbox, hbox, grid, window"))
                .add(DervaFX.label("label, button, textField, textArea, checkBox, comboBox"))
                .add(DervaFX.button("Read Active Theme").onClick(() ->
                        status.text("Active theme: " + DervaFX.theme().name())))
                .add(status);

        return DervaFX.window("DervaFX Factory Demo")
                .position(660, 580)
                .size(360, 250)
                .minSize(280, 180)
                .resizable(true, true)
                .add(content);
    }
}
