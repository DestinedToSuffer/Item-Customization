package co.vangar.itemcustomization.commands;

import co.vangar.itemcustomization.ItemCustomization;
import co.vangar.itemcustomization.utils.utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;

public class ResetName implements CommandExecutor {

    private ItemCustomization plugin;

    public ResetName(ItemCustomization plugin){
        this.plugin = plugin;
        plugin.getCommand("resetname").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(utils.prefix() + "Only players may execute this command");
        } else {
            Player p = (Player) sender;
            if(p.hasPermission("resetname.use")){
                if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR){
                    if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != ""){
                        if(plugin.getConfig().getBoolean("ResetName.refund") && p.getGameMode() != GameMode.CREATIVE){
                            int newXp = p.getLevel() + plugin.getConfig().getInt("ResetName.expRefunded");
                            p.setLevel(newXp);
                        }
                        ItemStack itemInHand = new ItemStack(p.getInventory().getItemInMainHand());
                        ItemMeta itemMeta = itemInHand.getItemMeta();

                        itemMeta.setDisplayName(null);

                        itemInHand.setItemMeta(itemMeta);
                        p.getInventory().setItemInMainHand(itemInHand);

                        p.sendMessage(utils.prefix() + "You have reset the name!");
                    } else p.sendMessage(utils.error("This item has no name!"));
                } else p.sendMessage(utils.error("Must have an item in hand!"));
            } else p.sendMessage(utils.error("You do not have the permission to run this command!"));
        } return false;
    }
}