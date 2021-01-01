package me.danielch.chwarps.commands;

import me.danielch.chwarps.ChWarps;
import me.danielch.chwarps.utils.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(DataManager.getMessage("ERROR.ONLY_PLAYER_COMMAND"));
            return true;
        }
        Player player = (Player) sender;

        if (args == null || args.length < 1) {
            player.sendMessage(DataManager.getMessage("CORRECT_USAGE.TP"));
            return true;
        }

        if (!DataManager.existWarp(args[0])) {
            player.sendMessage(DataManager.getMessage("ERROR.WARP_NOT_EXIST").replace("%warp_name%", args[0]));
            return true;
        }

        if (!(player.hasPermission("ChWarps.teleport." + args[0]))) {
            player.sendMessage(DataManager.getMessage("NOT_PERMISSION.TELEPORT"));
            return true;
        }

        double delay = DataManager.getTpDelay();

        ChWarps.playersTpList.put(player.getUniqueId(), false);
        sender.sendMessage(DataManager.getMessage("TP_MESSAGE").replace("%tp_delay%", "" + delay));

        Bukkit.getScheduler().scheduleSyncDelayedTask(ChWarps.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (!ChWarps.playersTpList.get(player.getUniqueId())) {
                    player.teleport(DataManager.getPositionWarp(args[0]));
                    sender.sendMessage(DataManager.getMessage("SUCCESSFULLY.TP").replace("%warp_name%", args[0]));
                    ChWarps.playersTpList.remove(player.getUniqueId());
                }
            }
        }, (long) (20 * delay));

        return false;
    }
}
