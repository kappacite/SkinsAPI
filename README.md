# SkinsAPI
SkinsAPI is an API for spigot developers who wants to manipulate player skins.
It allows you to change a player skin easily with 2 lines.

# Installation
Download ``SkinsAPI.jar``(https://github.com/kappacite/SkinsAPI/blob/master/SkinsAPI.jar) and add it as a dependance to your project.

# Features

 * Get & set a skin from an image.
 * Get & set a skin from a mojang account.

# Usage
First, you need to set the plugin & enable SkinsAPI.
In your main class, in the onEnable:
```java
SkinsAPI.getInstance().setPlugin(this);
SkinsAPI.getInstance().enable();
```

Don't forget to disable the API when your plugin is reloaded/stopped.

To set a player skin,
```java
SkinsAPI.getInstance().getSkinsManager().setSkin(player, skin);
```
And that's it!

More details on https://github.com/kappacite/SkinsAPI/wiki.

# Build
If you are using Intellij IDEA, don't forget to set the dependency as " Compile " in Modules > Dependencies and to create an artifacts with dependencies.

# Compatibility
SkinsAPI supports 1.8 to 1.15.2 with full NMS compatibility.

# Support
If you have some problem with it like NMS or other, you can open an issue.
 
# License
[GPL](https://www.gnu.org/licenses/licenses.fr.html)


