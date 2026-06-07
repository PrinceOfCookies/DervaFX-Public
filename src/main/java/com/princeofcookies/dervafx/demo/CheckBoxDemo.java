package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaCheckBox;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class CheckBoxDemo {
    private CheckBoxDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaCheckBox", "CheckBoxDemo.java", 320, 1090, 320, 220, CheckBoxDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaCheckBox autosave = DervaFX.checkBox("Autosave").selected(true);
        DervaCheckBox notifications = DervaFX.checkBox("Notifications").selected(false);
        DervaLabel status = DervaFX.label("Read selection with the button below.");

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(autosave)
                .add(notifications)
                .add(DervaFX.button("Read State").onClick(() -> status.text(
                        "Autosave=" + autosave.selected() + ", Notifications=" + notifications.selected())))
                .add(status);

        return DervaFX.window("DervaCheckBox Demo")
                .position(320, 1090)
                .size(320, 220)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
