package fr.kappacite.skinsapi.events;

import fr.kappacite.skinsapi.SkinsAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Connect implements Listener {

    public void onConnect(PlayerConnectEvent event){
        SkinsAPI.getInstance().getSkinsManager().addPlayerInitialeSkin(event.getPlayer());
    }

}
