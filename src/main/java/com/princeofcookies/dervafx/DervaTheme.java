package com.princeofcookies.dervafx;

import java.util.Objects;

public final class DervaTheme {
    private final String name;
    private String stylesheetResource;

    private DervaTheme(String name) {
        this.name = Objects.requireNonNull(name, "name");
        this.stylesheetResource = "/com/princeofcookies/dervafx/dervafx-dark.css";
    }

    public static DervaTheme of(String name) {
        return new DervaTheme(name == null ? "custom" : name);
    }

    public static DervaTheme dark() {
        return of("dark");
    }

    public String name() {
        return name;
    }

    public String stylesheetResource() {
        return stylesheetResource;
    }

    public DervaTheme stylesheetResource(String stylesheetResource) {
        this.stylesheetResource = Objects.requireNonNull(stylesheetResource, "stylesheetResource");
        return this;
    }
}
