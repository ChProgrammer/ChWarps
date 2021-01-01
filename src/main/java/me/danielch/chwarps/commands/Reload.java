package me.danielch.chwarps.commands;

import me.danielch.chwarps.ChWarps;
import me.danielch.chwarps.utils.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("ChWarps.reload")) {
            ChWarps.reloadAllPlugin();
            sender.sendMessage(DataManager.getMessage("PLUGIN.RELOAD"));
        } else {
            sender.sendMessage(DataManager.getMessage("NOT_PERMISSION.COMMAND"));
        }

        return false;
    }
}
