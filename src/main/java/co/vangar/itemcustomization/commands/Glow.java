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

public class Glow implements CommandExecutor {

    private ItemCustomization plugin;

    public Glow(ItemCustomization plugin){
        this.plugin = plugin;
        plugin.getCommand("glow").setExecutor(this);
    }

    public void shiny(Player p){
        ItemStack itemInHand = new ItemStack(p.getInventory().getItemInMainHand());
        ItemMeta itemMeta = itemInHand.getItemMeta();

        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemInHand.setItemMeta(itemMeta);

        p.getInventory().setItemInMainHand(itemInHand);
        p.sendMessage(utils.prefix() + "Your item is now shiny!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(utils.prefix() + "Only players may execute this command");
        } else {
            Player p = (Player) sender;
            if(p.hasPermission("glow.use")){
                if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() != Material.AIR){
                    if(plugin.getConfig().getBoolean("Glow.requiresExp")){
                        int reqExp = plugin.getConfig().getInt("Glow.expRequired");
                        if(p.getLevel() >= reqExp || p.getGameMode() == GameMode.CREATIVE){
                            shiny(p);
                            if(p.getGameMode() != GameMode.CREATIVE) p.setLevel(p.getLevel() - reqExp);
                        } else p.sendMessage(utils.error("Must have " + reqExp + " levels to make your item glow!"));
                    } else shiny(p);
                } else p.sendMessage(utils.error("Must have an item in hand!"));
            } else p.sendMessage(utils.error("You do not have the permission to run this command!"));
        } return false;
    }
}