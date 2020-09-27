# SkinsAPI
SkinsAPI is an API for spigot developers who wants to manipulate player skins.
It allows you to change a player skin easily with 2 lines.

# Installation
Download ``SkinsAPI.jar`` and add it as a dependance to your project.

# Usage
First, you need to set the plugin & to enable SkinsAPI.
In your main class, in the onEnable:
```java
SkinsAPI.INSTANCE.setPlugin(this);
SkinsAPI.INSTANCE.enable();
```

Don't forget the disable the API when your plugin is disable.

To set a player skin,
```java
SkinsAPI.INSTANCE.getSkinsManager().setSkin(player, value, signature);
```
And that's it!

# Compatibility
SkinsAPI supports 1.8 to 1.15.2 with full NMS compatibility.

# Support
If you have some problem with it like NMS or other, you can open an issue.

# In future
 - Get skin from Mojang
 
# License
[MIT](https://choosealicense.com/licenses/mit/)


