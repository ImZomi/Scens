package me.imzomi.uhcscenarios.scenarios;

import me.imzomi.uhcscenarios.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.CraftItemEvent;

public class Bowless implements Listener, CommandExecutor {

    private Main plugin;
    public Bowless(Main plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void cancelBow(CraftItemEvent e){
        if (plugin.Bowless){
            if (e.getRecipe().getResult().getType() == Material.BOW || e.getRecipe().getResult().getType() == Material.CROSSBOW){
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void bowUse(EntityShootBowEvent e){
        if (plugin.Bowless) {
            if (e.getEntity().getType() == EntityType.PLAYER){
            e.setCancelled(true);
        }}}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Enganxe" + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " No puedes ejecutar comandos desde la consola");
        }
        if (sender.hasPermission("uhc.admin") && cmd.getName().equalsIgnoreCase("Bowless")) {
            if (!plugin.Bowless) {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_GRAY+ "["+ChatColor.GOLD+"Enganxe"+ChatColor.DARK_GRAY+"]"+ " &0➤ &fBowless has been &aenabled"));
                plugin.Bowless = Boolean.valueOf(true);
            } else {
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.DARK_GRAY+ "["+ChatColor.GOLD+"Enganxe"+ChatColor.DARK_GRAY+"]"+ " &0➤ &fBowless has been &cdisabled"));
                plugin.Bowless = Boolean.valueOf(false);
            }
        } else {
            p.sendMessage(ChatColor.RED + "No tienes permisos para utilizar este comando");
        }
        return false;
    }
}

