package org.betamc.bmcdice;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Random;


public class BMCDice extends JavaPlugin {
    public void onEnable() {
        getServer().getLogger().info("BMCDice loaded.");
    }

    public void onDisable() {

    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("dice") || label.equalsIgnoreCase("bmcdice") || label.equalsIgnoreCase("roll")) {
            int maxRoll = 100;
            int minRoll = 1;
            if (args.length > 0) {
                if (args.length > 1) {
                    try {
                        minRoll = Integer.parseInt(args[0]);
                        maxRoll = Integer.parseInt(args[1]);
                    }
                    catch (NumberFormatException e) {
                        sender.sendMessage("§cError: Arguments must be valid numbers");
                        return false;
                    }
                }
                else {
                    try {
                        maxRoll = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage("§cError: Arguments must be valid numbers");
                        return false;
                    }
                }
            }

            if (minRoll == maxRoll) {
                sender.sendMessage("§cError: Minimum and maximum roll cannot be the same number");
                return false;
            }
            else if (minRoll < 0 || maxRoll < 0) {
                sender.sendMessage("§cError: Roll numbers cannot be negative");
                return false;
            }
            else if (maxRoll < minRoll) {
                sender.sendMessage("§cError: Minimum roll can't be larger than maximum roll");
                return false;
            }

            Random rand = new Random();
            int diceRoll = rand.nextInt(maxRoll - minRoll + 1) + minRoll;

            Bukkit.broadcastMessage("§a" + ((Player)sender).getName() + " §frolled§a " + diceRoll + " §ffrom§a " + minRoll + " §fto§a " + maxRoll);
        }

        return true;
    }
}
