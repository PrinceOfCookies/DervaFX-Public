package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaLabel;
import com.princeofcookies.dervafx.DervaTheme;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class ThemeManagerDemo {
    private ThemeManagerDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaThemeManager", "ThemeManagerDemo.java", 700, 1110, 380, 260, ThemeManagerDemo::createWindow);
    }

    private static DervaWindow createWindow(com.princeofcookies.dervafx.DervaRoot root, javafx.scene.Scene scene) {
        DervaTheme dark = DervaTheme.dark();
        DervaTheme sand = DervaTheme.of("sand").stylesheetResource("/com/princeofcookies/dervafx/dervafx-sand.css");
        DervaLabel status = DervaFX.label("Current theme: " + DervaFX.theme().name());

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("DervaThemeManager tracks and reapplies the active scene theme."))
                .add(DervaFX.button("Apply Dark").onClick(() -> {
                    DervaFX.setTheme(dark);
                    status.text("Current theme: " + DervaFX.theme().name());
                }))
                .add(DervaFX.button("Apply Sand").onClick(() -> {
                    DervaFX.setTheme(sand);
                    status.text("Current theme: " + DervaFX.theme().name());
                }))
                .add(DervaFX.button("Reapply Scene Theme").onClick(() -> {
                    DervaFX.applyTheme(scene);
                    status.text("Current theme: " + DervaFX.theme().name());
                }))
                .add(status);

        return DervaFX.window("DervaThemeManager Demo")
                .position(700, 1110)
                .size(380, 260)
                .minSize(300, 200)
                .resizable(true, true)
                .add(content);
    }
}
