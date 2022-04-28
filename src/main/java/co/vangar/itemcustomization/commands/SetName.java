package co.vangar.itemcustomization.commands;

import co.vangar.itemcustomization.ItemCustomization;
import co.vangar.itemcustomization.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class SetName implements CommandExecutor {

    private ItemCustomization plugin;

    public SetName(ItemCustomization plugin){
        this.plugin = plugin;
        plugin.getCommand("setname").setExecutor(this);
    }


    public void changeName(String [] args, Player p){
        String name = utils.chat(String.join(" ", args));

        ItemStack itemInHand = new ItemStack(p.getInventory().getItemInMainHand());
        ItemMeta itemMeta = itemInHand.getItemMeta();

        itemMeta.setDisplayName(name);
        itemInHand.setItemMeta(itemMeta);

        p.getInventory().setItemInMainHand(itemInHand);
        p.sendMessage(utils.prefix() + "You have successfully set the name!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(utils.prefix() + "Only players may execute this command");
        } else {
            Player p = (Player) sender;
            if(p.hasPermission("setname.use")){
                if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR){
                    if(args.length > 0){
                        if(plugin.getConfig().getBoolean("SetName.requiresExp")){
                            int reqExp = plugin.getConfig().getInt("SetName.expRequired");
                            if(p.getLevel() >= reqExp || p.getGameMode() == GameMode.CREATIVE){
                                changeName(args, p);
                                if(p.getGameMode() != GameMode.CREATIVE) p.setLevel(p.getLevel() - reqExp);
                            } else p.sendMessage(utils.error("Must have " + reqExp + " levels to set lore!"));
                        } else changeName(args, p);
                    } else p.sendMessage(utils.error("You must have lore for the item!"));
                } else p.sendMessage(utils.error("Must have an item in hand!"));
            } else p.sendMessage(utils.error("You do not have the permission to run this command!"));
        } return false;
    }
}