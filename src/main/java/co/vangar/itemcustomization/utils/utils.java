package co.vangar.itemcustomization.utils;

import org.bukkit.ChatColor;

public class utils {

    public static String chat (String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String prefix(){
        return ChatColor.DARK_PURPLE + "[IC] " + ChatColor.LIGHT_PURPLE;
    }

    public static String error(String errorMsg){
        return ChatColor.DARK_RED + "[IC] " + errorMsg;
    }

}