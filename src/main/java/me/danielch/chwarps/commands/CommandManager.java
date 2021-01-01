package me.danielch.chwarps.commands;

import me.danielch.chwarps.ChWarps;
import me.danielch.chwarps.utils.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private ChWarps main = ChWarps.getInstance();
    private final HashMap<String, CommandExecutor> commands = new HashMap<>();

    public void registerCommand(String command, CommandExecutor commandExecutor) {
        if (commandExecutor == null || command == null || command.isEmpty()) {
            throw new IllegalArgumentException("Invalid command paramters specified");
        }
        this.commands.put(command.toLowerCase(), commandExecutor);
    }

    public void unregisterCommand(String command) {
        this.commands.remove(command.toLowerCase());
    }

    public CommandExecutor getCommandExecutor(String command) {
        return this.commands.get(command.toLowerCase());
    }

    public boolean hasCommand(String command) {
        return this.commands.containsKey(command.toLowerCase());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0 && hasCommand(args[0].toLowerCase())) {
            return this.getCommandExecutor(args[0]).onCommand(sender, command, label, getNewArgs(args));
        } else {
            sender.sendMessage(ChWarps.info);
            sender.sendMessage(DataManager.getMessage("ERROR.MESSAGE"));
        }

        return false;
    }

    public String[] getNewArgs(String[] args) {
        List<String> newArgs = new ArrayList<>();
        for (int i = 1; i < (args.length); i++) {
            newArgs.add(args[i]);
        }
        return newArgs.toArray(new String[(args.length - 1)]);
    }
}
