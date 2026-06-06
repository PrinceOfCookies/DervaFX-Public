package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaFX;
import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaVBox;
import com.princeofcookies.dervafx.DervaWindow;

final class RootDemo {
    private RootDemo() {
    }

    static DemoSpec spec() {
        return new DemoSpec("DervaRoot", "RootDemo.java", 320, 24, 340, 250, RootDemo::createWindow);
    }

    private static DervaWindow createWindow(DervaRoot root, javafx.scene.Scene scene) {
        DervaWindow[] helper = new DervaWindow[1];

        DervaVBox content = DervaFX.vbox()
                .spacing(10)
                .padding(4)
                .fillWidth()
                .fillHeight()
                .add(DervaFX.label("DervaRoot is the shared overlay host for DervaFX content."))
                .add(DervaFX.button("Spawn Helper Window").onClick(() -> {
                    if (helper[0] == null) {
                        helper[0] = DervaFX.window("Root Child")
                                .position(690, 24)
                                .size(220, 140)
                                .add(DervaFX.vbox()
                                        .spacing(8)
                                        .add(DervaFX.label("Added through DervaRoot.add(...)"))
                                        .add(DervaFX.button("Bring Parent Forward")));
                    }
                    if (helper[0].getNode().getParent() == null) {
                        root.add(helper[0]);
                    }
                    helper[0].visible(true).toFront();
                }))
                .add(DervaFX.button("Remove Helper Window").onClick(() -> {
                    if (helper[0] != null && helper[0].getNode().getParent() != null) {
                        root.remove(helper[0]);
                    }
                }))
                .add(DervaFX.label("This demo exists because the root attached it to the scene graph."));

        return DervaFX.window("DervaRoot Demo")
                .position(320, 24)
                .size(340, 250)
                .minSize(280, 200)
                .resizable(true, true)
                .add(content);
    }
}
