package fr.kappacite.skinsapi.events;

import fr.kappacite.skinsapi.SkinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        SkinsAPI.getInstance().getSkinsManager().removePlayerInitialeSkin(event.getPlayer());
    }

}
