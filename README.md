# Nano

## License
This mod is licensed under the GNU General Public License v3.0 (or later).
See the LICENSE file for details.
---
## Using Nano

### Defining nano version
In `gradle.properties` put a `nano_version` key and set it to the version of nano you are using:
```properties
nano_version=0.0.1-alpha.15
```

### Defining repository
Your `build.gradle` you should have a `repositories` block that looks like this:
```groovy
repositories {
    maven {
        name = "JitPack"
        url = "https://jitpack.io"
    }
}
```
If you already have JitPack listed, you can skip this step.

---
## Depending on Nano

### Nano as a required dependency
This is best used when your mod cannot run without Nano, and is usually what you want.
In your `build.gradle` `dependencies` block you should have a `modImplementation`
```groovy
dependencies {
    modImplementation("com.github.C-Div:nano:${project.nano_version}")
}
```

### Nano as an optional dependency
This is best used when your mod can run without Nano, but has interactions with it when present.
In your `build.gradle` `dependencies` block you should have a `modCompileOnly`
```groovy
dependencies {
    modCompileOnly("com.github.C-Div:nano:${project.nano_version}")
    modLocalRuntime("com.github.C-Div:nano:${project.nano_version}") // Optional, this makes nano run automatically when testing
}
```
It's important to note that you will have to manually check if Nano is loaded via `FabricLoader.isModLoaded()`, below is an example with a standard `ModInitializer`
```java
package yourname.modname;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.api.ModInitializer;

public class ModCommon implements ModInitializer {
    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("nano")) {
            // Do something
        }
    }
}
```

### Nano as an API
This is best used when your mod's public API requires Nano.
In your `build.gradle` `dependencies` block you should have a `modApi`
```groovy
dependencies {
    modApi("com.github.C-Div:nano:${project.nano_version}")
}
```
---
## Using Nano's dependencies

### Apple skin
Official instructions can be found in their [repository](https://github.com/squeek502/AppleSkin/tree/1.21-fabric).
#### An additional step may be necessary if using `modLocalRuntime` with apple skin:
In `gradle.properties`
```properties
# This is the version that Nano uses
cloth_version=15.0.140
```
In `build.gradle`
```groovy
dependencies {
    modLocalRuntime("me.shedaniel.cloth:cloth-config-fabric:${project.cloth_version}")
}
```

### First Person Model
At the moment there does not seem to be any official instructions in their repository.

You can instead add it using Modrinth's Maven repository.
Official instructions from Modrinth exist for each individual mod version, for reference Nano uses version [2.7.2 for Minecraft 1.21.1](https://modrinth.com/mod/first-person-model/version/fSfRdYJ6)

### Pekhui
Official instructions can be found in their [repository](https://github.com/Virtuoel/Pehkui).