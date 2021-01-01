package me.danielch.chwarps.commands;

import me.danielch.chwarps.utils.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpList implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (DataManager.getWarpsList() != null) {
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-------------[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "ChWarps" + ChatColor.GOLD + "" + ChatColor.BOLD + "]-------------");
            for (String warpName : DataManager.getWarpsList()) {
                if (sender.hasPermission("ChWarps.teleport." + warpName)) {
                    sender.sendMessage(DataManager.getMessage("WARPS_LIST_FORMAT_MESSAGE")
                            .replace("%warp_name%", warpName)
                    );
                }
            }
            sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "-------------[" + ChatColor.YELLOW + "" + ChatColor.BOLD + "ChWarps" + ChatColor.GOLD + "" + ChatColor.BOLD + "]-------------");
        } else {
            sender.sendMessage(DataManager.getMessage("ERROR.NO_WARP_FOUND"));
        }


        return false;
    }
}
