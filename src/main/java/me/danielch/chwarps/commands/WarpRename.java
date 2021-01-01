package me.danielch.chwarps.commands;

import me.danielch.chwarps.utils.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarpRename implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("ChWarps.renameWarp"))) {
            sender.sendMessage(DataManager.getMessage("NOT_PERMISSION.COMMAND"));
            return true;
        }
        if (args == null || args.length < 2) {
            sender.sendMessage(DataManager.getMessage("CORRECT_USAGE.RENAMEWARP"));
            return true;
        }
        if (!DataManager.existWarp(args[0])) {
            sender.sendMessage(DataManager.getMessage("ERROR.WARP_NOT_EXIST").replace("%warp_name%", args[0]));
            return true;
        }

        if (DataManager.existWarp(args[1])) {
            sender.sendMessage(DataManager.getMessage("ERROR.WARP_ALREADY_EXIST").replace("%warp_name%", args[1]));
            return true;
        }

        DataManager.addWarp(args[1], DataManager.getPositionWarp(args[0]));
        DataManager.deleteWarp(args[0]);

        sender.sendMessage(DataManager.getMessage("SUCCESSFULLY.RENAME_WARP").replace("%old_warp_name%", args[0]).replace("%new_warp_name%", args[1]));

        return false;
    }
}
