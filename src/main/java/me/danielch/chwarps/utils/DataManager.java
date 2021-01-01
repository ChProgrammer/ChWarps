package me.danielch.chwarps.utils;

import me.danielch.chwarps.ChWarps;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataManager {

    private static final ChWarps main = ChWarps.getInstance();
    private static File messagesFile;
    public static FileConfiguration messages;
    private static File warpsFile;
    public static FileConfiguration warps;

    public static void init() {
        messagesFile = new File(main.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) main.saveResource("messages.yml", false);
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        messages.options().copyDefaults(true);

        warpsFile = new File(main.getDataFolder(), "warps.yml");
        if (!warpsFile.exists()) main.saveResource("warps.yml", false);
        warps = YamlConfiguration.loadConfiguration(warpsFile);
        warps.options().copyDefaults(true);
    }

    public static double getTpDelay() {
        return main.getConfig().getDouble("delayToTeleportInSeconds");
    }

    public static ArrayList<String> getWarpsList() {
        ArrayList<String> list = new ArrayList<>();
        ConfigurationSection configSection = warps.getConfigurationSection("warps");
        if (configSection != null) {
            for (String key : configSection.getKeys(false))
                if (!key.equals("")) {
                    list.add(key);
                }
        }
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    public static boolean existWarp(String name) {
        return warps.get("warps." + name) != null;
    }

    public static Location getPositionWarp(String name) {
        if (existWarp(name)) {
            return Util.convertStringToLocation(warps.getString("warps." + name + ".position"));
        }
        return null;
    }

    public static void setNewPositionWarp(String name, Location loc) {
        warps.set("warps." + name + ".position", Util.convertLocationToString(loc));
        reloadWarps();
    }

    public static void deleteWarp(String name) {
        warps.set("warps." + name, null);
        reloadWarps();
    }

    public static void addWarp(String name, Location loc) {
        warps.set("warps." + name + ".position", Util.convertLocationToString(loc));
        reloadWarps();
    }

    public static String getMessage(String locale) {
        String defaultMessage = messages.getString("CHAT_PREFIX") + "" + messages.getString(locale);
        return ChatColor.translateAlternateColorCodes('&', defaultMessage);
    }

    public static void reloadWarps() {
        try {
            warps.save(warpsFile);
            warps.load(warpsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void reloadMessages() {
        try {
            messages.load(messagesFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
