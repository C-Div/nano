# Nano

## Compatibility
- Fabric API 0.116.1 or above
- Fabric loader 0.14.20 or above
- Minecraft 1.21 or 1.21.1

## License
This mod is licensed under the GNU General Public License v3.0 (or later).
See the LICENSE file for details.

<details>
<summary>For Developers</summary>

---

## Using Nano
<details>
<summary>Importing Nano</summary>

### Defining Nano version
In `gradle.properties` put a `nano_version` key and set it to the version of Nano you are using:
```properties
nano_version=0.0.1-alpha.15
```

### Defining repository
Add the following to your `repositories` block in `build.gradle`:
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
Use this when your mod requires Nano to function. This is the recommended option for most projects.
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
    modLocalRuntime("com.github.C-Div:nano:${project.nano_version}") // Optional, this automatically loads Nano during development/testing
}
```
When using Nano as an optional dependency, always check whether it is loaded via `FabricLoader.isModLoaded()` before accessing its classes or functionality. Below is an example with a standard `ModInitializer`:
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

### Nano as part of your API
This is best used when your mod's public API requires Nano.
In your `build.gradle` `dependencies` block you should have a `modApi`
```groovy
dependencies {
    modApi("com.github.C-Div:nano:${project.nano_version}")
}
```
</details>
<details>
<summary>The API</summary>

### Notes
> API here is subject to change while Nano is in alpha. Any undocumented API should be considered incomplete, not implemented, or unstable, and should not be used.

### Why X is not a part of the API
#### Loot scaling registry
As far as I can tell, it is not possible to get the entity type from a Loot Table Registry Key.

### API Classes

#### `NanoIntegration`
Represents an integration that is loaded during `Nano` initialization; any mod that is looking to run during `Nano` setup should implement this.
To utilize `NanoIntegration` you must specify the entry point in your `fabric.mod.json` `entrypoints` with the `nano` key
```json
{

    "entrypoints": {
        "nano": [
            "yourname.modname.NanoIntegration"
        ]
    }
}
```
```java
package yourname.modname;

import cdiv.nano.api.NanoIntegration;

public class YourMod implements NanoIntegration {
    @Override 
    public void onNanoInitialize() {
        // Do something
    }
}
```
#### `NanoClientIntegration`
This interface is similar to `NanoIntegration` but for the client.
To utilize `NanoClientIntegration` you must specify the entry point in your `fabric.mod.json` `entrypoints` with the `nano-client` key
```json
{

    "entrypoints": {
        "nano-client": [
            "yourname.modname.client.NanoClientIntegration"
        ]
    }
}
```
```java
package yourname.modname;

import cdiv.nano.client.api.NanoClientIntegration;

public class YourModClient implements NanoClientIntegration {
    @Override 
    public void onNanoInitialize() {
        // Do something
    }
}
```
#### `Option`
Class that represents an API option. Supports change listeners via `OptionListener` and value prioritization using `priority`.
Note all options have their default values set with a priority of 0.
#### `LockingOption`/`PackageLockingOption`
Similar to Option, but comes with the added feature of locking, after an option is locked it cannot be set.
`PackageLockingOption` is different from `LockingOption` in that it can only be locked by Nano, this is usually
for options that can only be read once. To see when a `PackageLockingOption` locks check the class's Javadocs.

Note that there is a separate `PackageLockingOption` for both the client and common side.

### API Configuration
<details>
<summary>Common Configuration</summary>

#### `Food`
Configuration for food, being nutrition scaling, saturation scaling, and over eating.
> Note that even if enabled, the food scaling does **not** apply to modded food items, see API Registries for that.
#### `Item`
Configuration for items, being item scaling and item drop scaling.
#### `Loot`
Configuration for mob loot, being loot scaling.
#### `Pathfinding`
Configuration for mob pathfinding, being pathfinding scaling and pathfinding minimum scaling.
> Note that even if enabled, the pathfinding scaling does **not** apply to modded entities, see API Registries for that.
#### `ScaleModifiers`
Configuration for Nano scale modifiers.
#### `Sound`
Configuration for sound, being sound scaling.
#### `Stepping`
Configuration for mob stepping, being stepping damage and entities immune to stepping damage.
#### `Xp`
Configuration for mob xp, being xp scaling.
</details>
<details>
<summary>Client Configuration</summary>

#### `AppleSkin`
Configuration for the Apple Skin mod.
#### `FirstPersonModel`
Configuration for the First Person Model mod.
#### `Sound`
Configuration for sound, being growth sounds.
#### `Keybinding`
Configuration for keybinds.
</details>
</details>

### API Events
<details>
<summary>Common Events</summary>

#### `LootEvents`
Events related to mob loot scaling
</details>

### API Registries
<details>
<summary>Common Registries</summary>

> Registries listed here can be found under `Nano.registry.Registries`.

#### `PathfindingScaling`
This registry contains each `EntityType` that should be affected by `Pathfinding` configuration.
This is intended to be an opt-in system for mods.
#### `FoodScaling`
This registry contains each `Item` that should be affected by `Food` configuration.
This is intended to be an opt-in system for mods.
</details>

---
## Integrations with external mods used by Nano

### Apple Skin
Official instructions can be found in their [repository](https://github.com/squeek502/AppleSkin/tree/1.21-fabric).
#### An additional step may be necessary if using `modLocalRuntime` with Apple Skin:
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
At the time of writing, there are no official setup instructions in their [repository](https://github.com/tr7zw/FirstPersonModel/).

You can instead add it using Modrinth's Maven repository.
Official instructions from Modrinth exist for each individual mod version, for reference Nano uses version [2.7.2 for Minecraft 1.21.1](https://modrinth.com/mod/first-person-model/version/fSfRdYJ6)

### Pekhui
Official instructions can be found in their [repository](https://github.com/Virtuoel/Pehkui).
</details>