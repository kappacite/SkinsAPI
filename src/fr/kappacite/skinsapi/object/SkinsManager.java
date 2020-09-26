package fr.kappacite.skinsapi.object;

import fr.kappacite.skinsapi.nms.Skins;
import org.bukkit.entity.Player;

public class SkinsManager {

    private Skins skins;

    public SkinsManager(Skins skins){
        this.skins = skins;
    }

    public boolean setSkins(Player player, Skin skin) {
        return this.skins.setSkin(player, skin);
    }

    public boolean setSkins(Player player, Skin skin, boolean updateSelf) {
        return this.skins.setSkin(player, skin, updateSelf);
    }
}
