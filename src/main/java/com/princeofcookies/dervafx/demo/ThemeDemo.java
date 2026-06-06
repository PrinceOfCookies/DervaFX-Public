package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaTheme;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class ThemeDemo {
    private ThemeDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaTheme", "ThemeDemo.java", 320, 1110, 360, 250, ThemeDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaTheme dark = DervaTheme.dark();
        DervaTheme sand = DervaTheme.of("sand").stylesheetResource("/com/princeofcookies/dervafx/dervafx-sand.css");
        DervaLabel status = DervaFX.label("Theme objects are plain descriptors.");

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("DervaTheme builds theme descriptors."))
                .add(DervaFX.button("Inspect Dark Theme").onClick(() ->
                        status.text(dark.name() + " -> " + dark.stylesheetResource())))
                .add(DervaFX.button("Inspect Sand Theme").onClick(() ->
                        status.text(sand.name() + " -> " + sand.stylesheetResource())))
                .add(status);

        return DervaFX.window("DervaTheme Demo")
                .position(320, 1110)
                .size(360, 250)
                .minSize(280, 180)
                .resizable(true, true)
                .add(content);
    }
}
