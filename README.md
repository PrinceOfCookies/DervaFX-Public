# DervaFX

DervaFX is a GMod Derma type wrapper of JavaFX

Current scope:

- Small chainable wrapper base
- Basic containers and controls
- One simple theme manager
- One minimal demo app

## Build

Requires Java 21 or newer.

```bash
mvn clean package
```

## Run

```bash
mvn javafx:run
```

## Current surface

```java
DervaRoot root = DervaFX.root();

DervaWindow window = DervaFX.window("Base Window")
    .size(320, 220)
    .position(24, 24);

window.add(
    DervaFX.vbox()
        .spacing(8)
        .add(DervaFX.label("Barebones starter"))
        .add(DervaFX.button("Click me"))
);

root.add(window);
```
