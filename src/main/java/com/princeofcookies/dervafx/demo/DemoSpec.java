package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.scene.Scene;

record DemoSpec(
        String title,
        String fileName,
        double x,
        double y,
        double width,
        double height,
        DemoFactory factory) {

    DervaWindow create(DervaRoot root, Scene scene) {
        return factory.create(root, scene);
    }

    void reset(DervaWindow window) {
        window.position(x, y).size(width, height);
    }
}
