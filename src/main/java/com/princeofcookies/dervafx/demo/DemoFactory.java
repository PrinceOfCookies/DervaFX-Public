package com.princeofcookies.dervafx.demo;

import com.princeofcookies.dervafx.DervaRoot;
import com.princeofcookies.dervafx.DervaWindow;

import javafx.scene.Scene;

@FunctionalInterface
interface DemoFactory {
    DervaWindow create(DervaRoot root, Scene scene);
}
