package fr.kappacite.skinsapi.events;

import fr.kappacite.skinsapi.SkinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        SkinsAPI.getInstance().getSkinsManager().addPlayerInitialeSkin(event.getPlayer());
    }

}
