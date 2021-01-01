package me.danielch.chwarps.commands;

import me.danielch.chwarps.utils.DataManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpSet implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(DataManager.getMessage("ERROR.ONLY_PLAYER_COMMAND"));
            return true;
        }
        Player player = (Player) sender;
        if (!(player.hasPermission("ChWarps.setWarp"))) {
            sender.sendMessage(DataManager.getMessage("NOT_PERMISSION.COMMAND"));
            return true;
        }
        if (args == null || args.length < 1) {
            sender.sendMessage(DataManager.getMessage("CORRECT_USAGE.SETWARP"));
            return true;
        }

        Location location = player.getLocation();

        if (!DataManager.existWarp(args[0])) {
            DataManager.addWarp(args[0], location);
            player.sendMessage(DataManager.getMessage("SUCCESSFULLY.SET_WARP")
                    .replace("%warp_name%", args[0])
            );
        } else {
            DataManager.setNewPositionWarp(args[0], location);
            player.sendMessage(DataManager.getMessage("SUCCESSFULLY.REPLACE_WARP")
                    .replace("%warp_name%", args[0])
            );
        }

        return false;
    }
}
