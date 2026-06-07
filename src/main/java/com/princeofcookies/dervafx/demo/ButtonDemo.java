package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaHBox;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

import java.util.concurrent.atomic.AtomicInteger;

final class ButtonDemo {
    private ButtonDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaButton", "ButtonDemo.java", 320, 850, 320, 210, ButtonDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        AtomicInteger clicks = new AtomicInteger();
        DervaLabel status = DervaFX.label("Clicks: 0");

        DervaHBox row = DervaFX.hbox()
                .spacing(8)
                .fillWidth()
                .add(DervaFX.button("Increment").onClick(() -> status.text("Clicks: " + clicks.incrementAndGet())))
                .add(DervaFX.button("Reset").onClick(() -> {
                    clicks.set(0);
                    status.text("Clicks: 0");
                }));

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Buttons are thin wrappers around JavaFX Button."))
                .add(row)
                .add(status);

        return DervaFX.window("DervaButton Demo")
                .position(320, 850)
                .size(320, 210)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
