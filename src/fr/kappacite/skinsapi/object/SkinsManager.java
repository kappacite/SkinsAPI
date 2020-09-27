package fr.kappacite.skinsapi.object;

import com.mojang.authlib.properties.Property;
import fr.kappacite.skinsapi.nms.Skins;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class SkinsManager {

    private HashMap<UUID, Property> initialeSkin = new HashMap<>();

    private Skins skins;

    public SkinsManager(Skins skins){
        this.skins = skins;
    }

    public boolean setPlayerSkin(Player player, Skin skin) {
        Validate.notNull(player, "Player cannot be null.");
        Validate.notNull(skin, "Skin cannot be null.");
        return this.skins.setSkin(player, skin);
    }

    public boolean setPlayerSkin(Player player, Skin skin, boolean updateSelf) {
        Validate.notNull(player, "Player cannot be null.");
        Validate.notNull(skin, "Skin cannot be null.");
        return this.skins.setSkin(player, skin, updateSelf);
    }

    public Skin getPlayerSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        return this.skins.getPlayerSkin(player);
    }

    public Property getPlayerSkinAsProperty(Player player){
        Validate.notNull(player, "Player cannot be null.");
        return this.skins.getPlayerSkinAsProperty(player);
    }

    public Property getPlayerInitialeSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        return this.initialeSkin.get(player.getUniqueId());
    }

    public boolean resetPlayerSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        Property initialeSkin = this.getPlayerInitialeSkin(player);
        return this.setPlayerSkin(player, new Skin(initialeSkin.getValue(), initialeSkin.getSignature()));
    }

    public boolean resetPlayerSkin(Player player, boolean updateSelf){
        Validate.notNull(player, "Player cannot be null.");
        Property initialeSkin = this.getPlayerInitialeSkin(player);
        return this.setPlayerSkin(player, new Skin(initialeSkin.getValue(), initialeSkin.getSignature()), updateSelf);
    }

    public void addPlayerInitialeSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        this.initialeSkin.put(player.getUniqueId(), getPlayerSkinAsProperty(player));
    }

    public void removePlayerInitialeSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        if(this.initialeSkin.containsKey(player.getUniqueId())) this.initialeSkin.remove(player.getUniqueId());
    }

}
