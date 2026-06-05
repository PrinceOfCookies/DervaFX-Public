package com.princeofcookies.dervafx;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

import javafx.scene.Scene;

public final class DervaThemeManager {
    private static final Map<Scene, String> appliedStylesheets = new WeakHashMap<>();
    private static DervaTheme currentTheme = DervaTheme.dark();

    private DervaThemeManager() {
    }

    public static synchronized DervaTheme theme() {
        return currentTheme;
    }

    public static synchronized void setTheme(DervaTheme theme) {
        currentTheme = theme == null ? DervaTheme.dark() : theme;
        refreshScenes();
    }

    public static synchronized void applyTo(Scene scene) {
        if (scene == null) {
            return;
        }
        String previous = appliedStylesheets.get(scene);
        if (previous != null) {
            scene.getStylesheets().remove(previous);
        }
        String stylesheet = requireStylesheet(currentTheme);
        scene.getStylesheets().add(stylesheet);
        appliedStylesheets.put(scene, stylesheet);
    }

    private static void refreshScenes() {
        appliedStylesheets.keySet().removeIf(scene -> scene == null);
        for (Map.Entry<Scene, String> entry : new ArrayList<>(appliedStylesheets.entrySet())) {
            Scene scene = entry.getKey();
            String previous = entry.getValue();
            if (previous != null) {
                scene.getStylesheets().remove(previous);
            }
            String stylesheet = requireStylesheet(currentTheme);
            scene.getStylesheets().add(stylesheet);
            appliedStylesheets.put(scene, stylesheet);
        }
    }

    private static String requireStylesheet(DervaTheme theme) {
        String resource = theme.stylesheetResource();
        var url = DervaThemeManager.class.getResource(resource);
        if (url == null) {
            throw new IllegalStateException("Missing stylesheet resource: " + resource);
        }
        return url.toExternalForm();
    }
}
