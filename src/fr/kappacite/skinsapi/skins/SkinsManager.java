package fr.kappacite.skinsapi.skins;

import com.mojang.authlib.properties.Property;
import fr.kappacite.skinsapi.SkinsAPI;
import fr.kappacite.skinsapi.exceptions.SkinAlreadyLoadException;
import fr.kappacite.skinsapi.exceptions.SkinNotLoadedException;
import fr.kappacite.skinsapi.nms.Skins;
import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class SkinsManager {

    private final HashMap<UUID, Property> initialSkin = new HashMap<>();
    protected final HashMap<File, Skin> loadedImageSkin = new HashMap<>();
    protected final HashMap<String, Skin> loadedMojangSkin = new HashMap<>();

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

    public Property getPlayerInitialSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        return this.initialSkin.get(player.getUniqueId());
    }

    public boolean resetPlayerSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        Property initialSkin = this.getPlayerInitialSkin(player);
        return this.setPlayerSkin(player, new Skin(initialSkin.getValue(), initialSkin.getSignature()));
    }

    public boolean resetPlayerSkin(Player player, boolean updateSelf){
        Validate.notNull(player, "Player cannot be null.");
        Property initialSkin = this.getPlayerInitialSkin(player);
        return this.setPlayerSkin(player, new Skin(initialSkin.getValue(), initialSkin.getSignature()), updateSelf);
    }

    public void loadSkin(File file) throws SkinAlreadyLoadException {
        Validate.notNull(file, "File cannot be null.");
        if(this.loadedImageSkin.containsKey(file)) throw new SkinAlreadyLoadException("Skin not loaded !");
        SkinsUtils.loadSkins(null, file, SkinType.IMAGE);
    }

    public void loadSkin(String player) throws SkinAlreadyLoadException {
        Validate.notNull(player, "Player cannot be null.");
        if(this.loadedMojangSkin.containsKey(player)) throw new SkinAlreadyLoadException("Skin not loaded !");
        SkinsUtils.loadSkins(player, null, SkinType.MOJANG);
    }

    public Skin getImageSkin(File image) throws SkinNotLoadedException {
        if(!this.loadedImageSkin.containsKey(image)) throw new SkinNotLoadedException("Skin not loaded !");
        return this.loadedImageSkin.get(image);
    }

    public Skin getMojangSkin(String player) throws SkinNotLoadedException {
        if(!this.loadedMojangSkin.containsKey(player)) throw new SkinNotLoadedException("Skin not loaded !");
        return this.loadedMojangSkin.get(player);
    }

    protected void addPlayerInitialSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        this.initialSkin.put(player.getUniqueId(), getPlayerSkinAsProperty(player));
    }

    protected void removePlayerInitialSkin(Player player){
        Validate.notNull(player, "Player cannot be null.");
        this.initialSkin.remove(player.getUniqueId());
    }

}
