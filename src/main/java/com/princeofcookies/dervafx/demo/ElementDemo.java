package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class ElementDemo {
    private ElementDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaElement", "ElementDemo.java", 320, 580, 320, 240, ElementDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaVBox content = DervaFX.vbox()
                .spacing(8)
                .padding(6)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Shared helper surface"))
                .add(DervaFX.label("position, size, minSize, maxSize"))
                .add(DervaFX.label("padding, spacing, align"))
                .add(DervaFX.label("fillWidth, fillHeight, visible, enabled"))
                .add(DervaFX.label("cssClass, style, anchorFill"))
                .add(DervaFX.button("This button is also a DervaElement"));

        return DervaFX.window("DervaElement Demo")
                .position(320, 580)
                .size(320, 240)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
