package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class TextFieldDemo {
    private TextFieldDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaTextField", "TextFieldDemo.java", 660, 850, 320, 210, TextFieldDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        var field = DervaFX.textField().prompt("Type here").fillWidth();
        DervaLabel status = DervaFX.label("Current value: ");

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Single-line input wrapper"))
                .add(field)
                .add(DervaFX.button("Read Value").onClick(() -> status.text("Current value: " + field.value())))
                .add(status);

        return DervaFX.window("DervaTextField Demo")
                .position(660, 850)
                .size(320, 210)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
