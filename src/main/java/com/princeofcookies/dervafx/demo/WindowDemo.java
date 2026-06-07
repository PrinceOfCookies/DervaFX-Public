package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class WindowDemo {
    private WindowDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaWindow", "WindowDemo.java", 680, 24, 360, 230, WindowDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Drag me around. I should clamp to the app bounds."))
                .add(DervaFX.label("Resize from the right edge, bottom edge, or bottom-right corner."))
                .add(DervaFX.textArea()
                        .text("DervaWindow now promotes child content so it grows with the window body.")
                        .size(0, 120)
                        .fillWidth()
                        .fillHeight())
                .add(DervaFX.button("Bring To Front").onClick(() -> {
                }));

        return DervaFX.window("DervaWindow Demo")
                .position(680, 24)
                .size(360, 230)
                .minSize(280, 180)
                .resizable(true, true)
                .add(content);
    }
}
