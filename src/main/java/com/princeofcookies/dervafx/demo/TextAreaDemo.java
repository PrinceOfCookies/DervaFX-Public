package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class TextAreaDemo {
    private TextAreaDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaTextArea", "TextAreaDemo.java", 1000, 850, 340, 220, TextAreaDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        var area = DervaFX.textArea()
                .prompt("Multi-line text")
                .text("Text areas support prompt text, direct text assignment, and fill sizing.")
                .fillWidth()
                .fillHeight();

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("Multi-line input wrapper"))
                .add(area);

        return DervaFX.window("DervaTextArea Demo")
                .position(1000, 850)
                .size(340, 220)
                .minSize(260, 180)
                .resizable(true, true)
                .add(content);
    }
}
