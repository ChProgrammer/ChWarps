package me.danielch.chwarps.commands;

import me.danielch.chwarps.utils.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-------------[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "ChWarps" + ChatColor.GOLD + "" + ChatColor.BOLD + "]-------------");
        sender.sendMessage(DataManager.getMessage("COMMAND.HELP_MESSAGE"));
        if (sender.hasPermission("ChWarps.setWarp"))
            sender.sendMessage(DataManager.getMessage("COMMAND.SET_WARP_MESSAGE"));
        if (sender.hasPermission("ChWarps.renameWarp"))
            sender.sendMessage(DataManager.getMessage("COMMAND.RENAME_WARP_MESSAGE"));
        if (sender.hasPermission("ChWarps.deleteWarp"))
            sender.sendMessage(DataManager.getMessage("COMMAND.DELETE_WARP_MESSAGE"));
        if (sender.hasPermission("ChWarps.reload"))
            sender.sendMessage(DataManager.getMessage("COMMAND.RELOAD_PLUGIN_MESSAGE"));
        sender.sendMessage(DataManager.getMessage("COMMAND.LIST_WARPS_MESSAGE"));
        sender.sendMessage(DataManager.getMessage("COMMAND.TP_WARP_MESSAGE"));
        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-------------[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "ChWarps" + ChatColor.GOLD + "" + ChatColor.BOLD + "]-------------");

        return false;
    }
}