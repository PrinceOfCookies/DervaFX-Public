# DervaFX

[![DervaFX Package Builder](https://github.com/PrinceOfCookies/DervaFX-Public/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/PrinceOfCookies/DervaFX-Public/actions/workflows/maven-publish.yml)

Documentation lives in the [My Webiste](https://PrinceOfCookies.com/DervaFX).

Current scope:

- Small chainable wrapper base
- Basic containers and controls
- Basic text, toggle, and select inputs
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

## Demo

![DervaFX demo](assets/Demo.png)

## Current surface

```java
DervaRoot root = DervaFX.root();

DervaWindow window = DervaFX.window("Base Window")
    .size(320, 220)
    .position(24, 24);

window.add(
    DervaFX.vbox()
        .spacing(8)
        .add(DervaFX.label("DervaFX demo"))
        .add(DervaFX.textField().prompt("Project name"))
        .add(DervaFX.checkBox("Remember layout"))
        .add(DervaFX.button("Click me"))
);

root.add(window);
```
