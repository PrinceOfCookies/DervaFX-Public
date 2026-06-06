package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class ComboBoxDemo {
    private ComboBoxDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaComboBox", "ComboBoxDemo.java", 1020, 830, 320, 240, ComboBoxDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        var combo = DervaFX.comboBox()
                .item("Dark")
                .item("Light")
                .item("Studio")
                .value("Dark")
                .fillWidth();
        DervaLabel status = DervaFX.label("Current theme: Dark");

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Combo box selection"))
                .add(combo)
                .add(DervaFX.button("Read Selection").onClick(() -> status.text("Current theme: " + combo.value())))
                .add(status);

        return DervaFX.window("DervaComboBox Demo")
                .position(1020, 830)
                .size(320, 240)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
