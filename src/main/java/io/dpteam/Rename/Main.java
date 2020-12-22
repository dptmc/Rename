package io.dpteam.Rename;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
   public Main() {
      super();
   }

   public void onEnable() {
      Bukkit.getServer().getLogger().info("[DPT.MC] Rename Enabled.");
   }

   public void onDisable() {
      Bukkit.getServer().getLogger().info("[DPT.MC] Rename Disabled.");
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      Player p;
      ItemStack itemStack;
      String string;
      String colouredString;
      String spacedString;
      if (cmd.getName().equals("rename")) {
         if (sender instanceof Player) {
            p = (Player)sender;
            if (p.getItemInHand() != null && !p.getItemInHand().getType().equals(Material.AIR)) {
               if (args.length == 1) {
                  itemStack = p.getItemInHand();
                  string = args[0];
                  colouredString = string.replaceAll("_", " ");
                  spacedString = ChatColor.translateAlternateColorCodes('&', colouredString);
                  ItemMeta itemStackMeta = itemStack.getItemMeta();
                  itemStackMeta.setDisplayName(spacedString);
                  itemStack.setItemMeta(itemStackMeta);
                  p.sendMessage(ChatColor.GOLD + "Succesfully renamed the item in hand to: " + ChatColor.RESET + spacedString + ChatColor.GOLD + "!");
               } else {
                  p.sendMessage(ChatColor.GOLD + "Wrong usage. Example: " + ChatColor.GRAY + "/rename &cCoffee" + ChatColor.GOLD + ".");
               }
            } else {
               p.sendMessage(ChatColor.GOLD + "You must hold an item in your hand.");
            }

            return true;
         }

         sender.sendMessage("Must be a player to use this command.");
      } else if (cmd.getName().equals("relore")) {
         if (sender instanceof Player) {
            p = (Player)sender;
            if (p.getItemInHand() != null && !p.getItemInHand().getType().equals(Material.AIR)) {
               if (args.length == 1) {
                  itemStack = p.getItemInHand();
                  string = args[0];
                  colouredString = ChatColor.translateAlternateColorCodes('&', string);
                  spacedString = colouredString.replaceAll("_", " ");
                  String[] loreNotConverted = spacedString.split(";");
                  List lore = new ArrayList();
                  String[] var15 = loreNotConverted;
                  int var14 = loreNotConverted.length;

                  for(int var13 = 0; var13 < var14; ++var13) {
                     String s = var15[var13];
                     lore.add(s);
                  }

                  ItemMeta itemStackMeta = itemStack.getItemMeta();
                  itemStackMeta.setLore(lore);
                  itemStack.setItemMeta(itemStackMeta);
                  p.sendMessage(ChatColor.GOLD + "Succesfully set the lore of the item in hand.");
               } else {
                  p.sendMessage(ChatColor.GOLD + "Wrong usage. Example: " + ChatColor.GRAY + "/relore &cThis_is_a_lore.;&fIsn't_it_nice?" + ChatColor.GOLD + ".");
               }
            } else {
               p.sendMessage(ChatColor.GOLD + "You must hold an item in your hand.");
            }

            return true;
         }

         sender.sendMessage("Must be a player to use this command.");
      }

      return false;
   }
}
