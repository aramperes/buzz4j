<img align="right" alt="buzz4j" width="150" src=".github/buzz4j.png">

# buzz4j

Java bindings for [HarfBuzz](https://harfbuzz.github.io).

[![MIT licensed](https://img.shields.io/badge/License-MIT-yellow.svg)](./LICENSE)
[![Build status](https://github.com/aramperes/buzz4j/actions/workflows/build.yaml/badge.svg)](https://github.com/aramperes/buzz4j/actions)
[![Latest Release](https://img.shields.io/github/v/tag/aramperes/buzz4j?label=release)](https://github.com/aramperes/buzz4j/releases/latest)

## Usage

This library only implements a tiny subset of HarfBuzz API. I focused on the use-case for my own projects:

```java
import dev.poire.buzz4j.HarfBuzz;

HarfBuzz.shapeString(fontPath, text);
```

Which gives you a list of glyph and their drawing position:

```
ShapeGlyph{
  int glyphId
  int advanceX
  int advanceY
  int offsetX
  int offsetY
}[]
```

## Compile

There is a submodule for `harfbuzz`. Start by fetching the submodule sources with `git submodule update`.

Then, `cd harfbuzz` and follow the [official instructions](https://harfbuzz.github.io/building.html) (meson
recommended):

```sh
meson build --wrap-mode=default
meson compile -C build
```

The `harfbuzz/build` directory should have, at minimum:

```
src/harfbuzz.lib
src/harfbuzz.dll
subprojects/freetype-2.13.0/freetype-6.dll
subprojects/libpng-1.6.39/png16-16.dll
```

Now, back in the root directory, run tests and build a `debug` library:

```sh
./gradlew build
```

This will build a JAR in `build/libs`, but it is then recommended to build
the shared library in `release` mode for distribution:

```sh
./gradlew assembleRelease
```

The build outputs for distribution are:

```
build/libs/buzz4j-1.0-SNAPSHOT.jar
buzz4jni/build/lib/main/release/buzz4j.dll
```

Keep in mind that you will need dependencies at runtime:

```
harfbuzz.dll
freetype-6.dll
png16-16.dll
```

## License

MIT License. See `LICENSE` for details.
