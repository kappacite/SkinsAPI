package fr.kappacite.skinsapi.skins;

import fr.kappacite.skinsapi.SkinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SkinStorage implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event){
        SkinsAPI.getInstance().getSkinsManager().addPlayerInitialSkin(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event){
        SkinsAPI.getInstance().getSkinsManager().removePlayerInitialSkin(event.getPlayer());
    }


}
