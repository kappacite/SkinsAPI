package fr.kappacite.skinsapi.nms;

import fr.kappacite.skinsapi.object.Skin;
import org.bukkit.entity.Player;

public abstract class Skins {

    public abstract boolean setSkin(Player player, Skin skin);

    public abstract boolean setSkin(Player player, Skin skin, boolean updateSelf);

    protected abstract boolean setSkin(Player player, String value, String signature, boolean updateSelf);

    protected abstract boolean setSkin(Player player, String value, String signature);

    protected abstract void updateSkin(Player player, boolean updateSelf);


}
