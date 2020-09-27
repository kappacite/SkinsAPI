package fr.kappacite.skinsapi.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDisconnectEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;

    public PlayerDisconnectEvent(Player player) {
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }

    public Player getPlayer() {
        return player;
    }
}
