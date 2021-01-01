package me.danielch.chwarps;

import me.danielch.chwarps.commands.CommandManager;
import me.danielch.chwarps.commands.Help;
import me.danielch.chwarps.commands.Reload;
import me.danielch.chwarps.commands.WarpDelete;
import me.danielch.chwarps.commands.WarpList;
import me.danielch.chwarps.commands.WarpRename;
import me.danielch.chwarps.commands.WarpSet;
import me.danielch.chwarps.commands.WarpTp;
import me.danielch.chwarps.listeners.Move;
import me.danielch.chwarps.utils.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class ChWarps extends JavaPlugin {

    public static HashMap<UUID, Boolean> playersTpList;
    public static ConsoleCommandSender consoleSender;
    public static FileConfiguration config;
    public static String prefix;
    public static String info;

    @Override
    public void onEnable() {
        this.initVariables();
        this.initCommands();
        this.initListeners();
        this.sendCustomMessage();
    }

    @Override
    public void onDisable() {
        this.sendCustomMessage();
    }

    public void initVariables() {
        consoleSender = this.getServer().getConsoleSender();
        config = this.getConfig();
        playersTpList = new HashMap<>();
        DataManager.init();
        prefix = ChatColor.GOLD + "" + ChatColor.BOLD + "[" + ChatColor.YELLOW + "ChWarps" + ChatColor.GOLD + "" + ChatColor.BOLD + "] " + ChatColor.WHITE;
        info = prefix + ChatColor.DARK_GREEN + "Version: " + getDescription().getVersion() + "\n" + prefix + ChatColor.DARK_GREEN + "Created by: DanielCh - Discord: DanielCh13#7256";
    }

    public void initCommands() {
        final CommandManager commandManager = new CommandManager();
        commandManager.registerCommand("help", new Help());
        commandManager.registerCommand("set", new WarpSet());
        commandManager.registerCommand("rename", new WarpRename());
        commandManager.registerCommand("delete", new WarpDelete());
        commandManager.registerCommand("reload", new Reload());
        commandManager.registerCommand("list", new WarpList());
        commandManager.registerCommand("tp", new WarpTp());

        this.getCommand("chwarps").setExecutor(commandManager);
    }

    public void initListeners() {
        this.getServer().getPluginManager().registerEvents(new Move(), this);
    }

    public void sendCustomMessage() {
        consoleSender.sendMessage("");
        consoleSender.sendMessage(ChatColor.GREEN + "   ____   _      __        __                             ");
        consoleSender.sendMessage(ChatColor.GREEN + "  / ___| | |__   \\ \\      / /   __ _   _ __   _ __    ___ ");
        consoleSender.sendMessage(ChatColor.GREEN + " | |     | '_ \\   \\ \\ /\\ / /   / _` | | '__| | '_ \\  / __|");
        consoleSender.sendMessage(ChatColor.GREEN + " | |___  | | | |   \\ V  V /   | (_| | | |    | |_) | \\__ \\");
        consoleSender.sendMessage(ChatColor.GREEN + "  \\____| |_| |_|    \\_/\\_/     \\__,_| |_|    | .__/  |___/");
        consoleSender.sendMessage(ChatColor.GREEN + "                                             |_|          ");
        consoleSender.sendMessage("");
        consoleSender.sendMessage(info);
        consoleSender.sendMessage("");
    }

    public static void reloadAllPlugin() {
        ChWarps.getInstance().reloadConfig();
        DataManager.reloadWarps();
        DataManager.reloadMessages();
    }

    public static ChWarps getInstance() {
        return getPlugin(ChWarps.class);
    }
}
