# DervaFX

[![DervaFX Package Builder](https://github.com/PrinceOfCookies/DervaFX-Public/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/PrinceOfCookies/DervaFX-Public/actions/workflows/maven-publish.yml)


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
        .add(DervaFX.label("WOOOOOOOOOOOOOOO"))
        .add(DervaFX.button("Click me"))
);

root.add(window);
```
