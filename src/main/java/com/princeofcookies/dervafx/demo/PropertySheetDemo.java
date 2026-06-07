package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaPropertySheet;
import com.princeofcookies.dervafx.DervaWindow;

final class PropertySheetDemo {
    private PropertySheetDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaPropertySheet", "PropertySheetDemo.java", 320, 1340, 400, 260, PropertySheetDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaPropertySheet sheet = DervaFX.propertySheet()
                .fillWidth()
                .fillHeight()
                .addPage("General", DervaFX.vbox()
                        .spacing(8)
                        .padding(6)
                        .fillWidth()
                        .add(DervaFX.label("General page"))
                        .add(DervaFX.textField().prompt("Name").fillWidth()))
                .addPage("Appearance", DervaFX.vbox()
                        .spacing(8)
                        .padding(6)
                        .fillWidth()
                        .add(DervaFX.label("Appearance page"))
                        .add(DervaFX.comboBox().item("Derma").item("Dark").value("Derma").fillWidth()))
                .addPage("Advanced", DervaFX.vbox()
                        .spacing(8)
                        .padding(6)
                        .fillWidth()
                        .add(DervaFX.checkBox("Enable advanced mode"))
                        .add(DervaFX.textArea().text("Tabbed content works like a simple property sheet.").fillWidth().fillHeight()));

        return DervaFX.window("DervaPropertySheet Demo")
                .position(320, 1340)
                .size(400, 260)
                .minSize(300, 220)
                .resizable(true, true)
                .add(sheet);
    }
}
