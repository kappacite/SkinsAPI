package fr.kappacite.skinsapi.nms;

import com.mojang.authlib.properties.Property;
import fr.kappacite.skinsapi.skins.Skin;
import org.bukkit.entity.Player;

public abstract class Skins {

    public abstract boolean setSkin(Player player, Skin skin);

    public abstract boolean setSkin(Player player, Skin skin, boolean updateSelf);

    public abstract Property getPlayerSkinAsProperty(Player player);

    public abstract Skin getPlayerSkin(Player player);

    protected abstract boolean setSkin(Player player, String value, String signature, boolean updateSelf);

    protected abstract boolean setSkin(Player player, String value, String signature);

    protected abstract void updateSkin(Player player, boolean updateSelf);




}
