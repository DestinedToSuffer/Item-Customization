package co.vangar.itemcustomization;

import co.vangar.itemcustomization.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemCustomization extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        new SetLore(this);
        new SetName(this);
        new Glow(this);
        new ResetLore(this);
        new ResetName(this);
        new ResetGlow(this);
    }
}