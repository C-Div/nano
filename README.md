# Nano

## License
This mod is licensed under the GNU General Public License v3.0 (or later).
See the LICENSE file for details.

## Using the API
In `gradle.properties` put a `nano_version` key and set it to the version of nano you are using:
```properties
nano_version=0.0.1-alpha.7
```
Your `build.gradle` you should have a repositories block that looks like this:
```groovy
repositories {
    maven {
        name = "JitPack"
        url = "https://jitpack.io"
    }
}
```
And finally in your `build.gradle` dependencies block you should have a modImplementation
```groovy
dependencies {
	modImplementation "com.github.C-Div:nano:${project.nano_version}"
}
```