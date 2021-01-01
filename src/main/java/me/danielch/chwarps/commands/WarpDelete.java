package me.danielch.chwarps.commands;

import me.danielch.chwarps.utils.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpDelete implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("ChWarps.deleteWarp")) {
            sender.sendMessage(DataManager.getMessage("NOT_PERMISSION.COMMAND"));
            return true;
        }

        if (!DataManager.existWarp(args[0])) {
            sender.sendMessage(DataManager.getMessage("ERROR.WARP_NOT_EXIST").replace("%warp_name%", args[0]));
            return true;
        }

        DataManager.deleteWarp(args[0]);

        sender.sendMessage(DataManager.getMessage("SUCCESSFULLY.DELETE_WARP").replace("%warp_name%", args[0]));

        return false;
    }
}
