package me.danielch.chwarps.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Util {

    public static String convertLocationToString(Location loc){
        return loc.getWorld().getName()+","+loc.getBlockX()+","+loc.getBlockY()+","+loc.getBlockZ()+","+loc.getYaw()+","+loc.getPitch();
    }

    public static Location convertStringToLocation(String loc){
        String[] locations = loc.split(",");
        int x = Integer.parseInt(locations [1]), y = Integer.parseInt(locations [2]), z = Integer.parseInt(locations [3]);
        float yaw = Float.parseFloat(locations [4]), pitch = Float.parseFloat(locations [5]);
        return new Location(Bukkit.getWorld(locations [0]), x, y, z, yaw, pitch);
    }

}
