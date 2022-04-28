package co.vangar.itemcustomization.commands;

import co.vangar.itemcustomization.ItemCustomization;
import co.vangar.itemcustomization.utils.utils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class ResetGlow implements CommandExecutor {

    private ItemCustomization plugin;

    public ResetGlow(ItemCustomization plugin){
        this.plugin = plugin;
        plugin.getCommand("resetglow").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(utils.prefix() + "Only players may execute this command");
        } else {
            Player p = (Player) sender;
            if(p.hasPermission("resetglow.use")){
                if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR){
                    if(p.getInventory().getItemInMainHand().getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)){
                        if(plugin.getConfig().getBoolean("ResetGlow.refund") && p.getGameMode() != GameMode.CREATIVE){
                            int newXp = p.getLevel() + plugin.getConfig().getInt("ResetGlow.expRefunded");
                            p.setLevel(newXp);
                        }
                        ItemStack itemInHand = new ItemStack(p.getInventory().getItemInMainHand());
                        ItemMeta itemMeta = itemInHand.getItemMeta();

                        itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                        itemMeta.removeEnchant(Enchantment.LUCK);

                        itemInHand.setItemMeta(itemMeta);
                        p.getInventory().setItemInMainHand(itemInHand);

                        p.sendMessage(utils.prefix() + "You have reset the glow effect!");
                    } else p.sendMessage(utils.error("This item does not have the glow effect!"));
                } else p.sendMessage(utils.error("Must have an item in hand!"));
            } else p.sendMessage(utils.error("You do not have the permission to run this command!"));
        } return false;
    }
}