package fr.kappacite.skinsapi.nms;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import fr.kappacite.skinsapi.skins.Skin;
import net.minecraft.server.v1_8_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class Skins_1_8_R2 extends Skins{

    @Override
    public Property getPlayerSkinAsProperty(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        GameProfile gameProfile = entityPlayer.getProfile();

        if(gameProfile.getProperties().containsKey("textures")) return (Property) gameProfile.getProperties().get("textures").toArray()[0];
        return new Property("textures", "", "");
    }

    @Override
    public Skin getPlayerSkin(Player player) {
        Property textures = this.getPlayerSkinAsProperty(player);
        return new Skin(textures.getValue(), textures.getSignature());
    }

    @Override
    public boolean setSkin(Player player, Skin skin){
        return setSkin(player, skin.getValue(), skin.getSignature());
    }

    @Override
    public boolean setSkin(Player player, Skin skin, boolean updateSelf){
        return setSkin(player, skin.getValue(), skin.getSignature(), updateSelf);
    }

    @Override
    protected boolean setSkin(Player player, String value, String signature) {
        return setSkin(player, value, signature, true);
    }

    @Override
    protected boolean setSkin(Player player, String value, String signature, boolean updateSelf) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().removeAll("textures");
        gameProfile.getProperties().put("textures", new Property("textures", value, signature));
        updateSkin(player, updateSelf);
        return true;
    }

    @Override
    protected void updateSkin(Player player, boolean updateSelf) {

        Location l = player.getLocation();
        CraftPlayer craftPlayer = (CraftPlayer) player;
        EntityPlayer entityPlayer = craftPlayer.getHandle();
        World world = entityPlayer.getWorld();
        PlayerConnection playerConnection = entityPlayer.playerConnection;

        PacketPlayOutPlayerInfo removePlayerPacket = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);
        PacketPlayOutPlayerInfo addPlayerPacket = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        PacketPlayOutEntityDestroy destroyEntityPacket = new PacketPlayOutEntityDestroy(player.getEntityId());
        PacketPlayOutNamedEntitySpawn entitySpawnPacket = new PacketPlayOutNamedEntitySpawn(entityPlayer);
        PacketPlayOutRespawn respawnEntityPacket = new PacketPlayOutRespawn (
                craftPlayer.getWorld().getEnvironment().getId(), world.getDifficulty(),
                world.getWorldData().getType(), entityPlayer.playerInteractManager.getGameMode());
        PacketPlayOutPosition positionPacket = new PacketPlayOutPosition(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), new HashSet<>());
        PacketPlayOutHeldItemSlot heldItemPacket = new PacketPlayOutHeldItemSlot(player.getInventory().getHeldItemSlot());

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayer.getUniqueId().equals(player.getUniqueId())){
                PlayerConnection connection = ((CraftPlayer) onlinePlayer).getHandle().playerConnection;
                connection.sendPacket(removePlayerPacket);
                connection.sendPacket(addPlayerPacket);
                connection.sendPacket(destroyEntityPacket);
                connection.sendPacket(entitySpawnPacket);
            }
        }

        if(!updateSelf) return;

        playerConnection.sendPacket(removePlayerPacket);
        playerConnection.sendPacket(addPlayerPacket);
        playerConnection.sendPacket(respawnEntityPacket);
        playerConnection.sendPacket(positionPacket);
        playerConnection.sendPacket(heldItemPacket);
        player.updateInventory();
        craftPlayer.updateScaledHealth();
        entityPlayer.triggerHealthUpdate();

        return;
    }
}
