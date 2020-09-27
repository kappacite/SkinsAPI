package fr.kappacite.skinsapi.events;

import fr.kappacite.skinsapi.SkinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Disconnect implements Listener {

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event){
        SkinsAPI.getInstance().getSkinsManager().removePlayerInitialeSkin(event.getPlayer());
    }

}
