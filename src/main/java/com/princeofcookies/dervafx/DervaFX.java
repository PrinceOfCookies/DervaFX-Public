package com.princeofcookies.dervafx;

import javafx.scene.Scene;

public final class DervaFX {
    private DervaFX() {
    }

    public static DervaRoot root() {
        return new DervaRoot();
    }

    public static DervaPanel panel() {
        return new DervaPanel();
    }

    public static DervaVBox vbox() {
        return new DervaVBox();
    }

    public static DervaHBox hbox() {
        return new DervaHBox();
    }

    public static DervaWindow window(String title) {
        return new DervaWindow(title);
    }

    public static DervaLabel label(String text) {
        return new DervaLabel(text);
    }

    public static DervaButton button(String text) {
        return new DervaButton(text);
    }

    public static DervaTextField textField() {
        return new DervaTextField();
    }

    public static DervaTextArea textArea() {
        return new DervaTextArea();
    }

    public static DervaCheckBox checkBox(String text) {
        return new DervaCheckBox(text);
    }

    public static DervaComboBox comboBox() {
        return new DervaComboBox();
    }

    public static DervaTheme theme() {
        return DervaThemeManager.theme();
    }

    public static void setTheme(DervaTheme theme) {
        DervaThemeManager.setTheme(theme);
    }

    public static void applyTheme(Scene scene) {
        DervaThemeManager.applyTo(scene);
    }
}
