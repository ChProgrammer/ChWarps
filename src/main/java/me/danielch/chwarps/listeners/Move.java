package me.danielch.chwarps.listeners;

import me.danielch.chwarps.ChWarps;
import me.danielch.chwarps.utils.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (ChWarps.playersTpList.containsKey(player.getUniqueId()) && !ChWarps.playersTpList.get(player.getUniqueId())) {
            if (event.getTo().getX() != event.getFrom().getX() || event.getTo().getY() != event.getFrom().getY() || event.getTo().getZ() != event.getFrom().getZ()) {
                ChWarps.playersTpList.put(player.getUniqueId(), true);
                player.sendMessage(DataManager.getMessage("ERROR.MOVED_BEFORE_TP"));
            }
        }
    }

}
