package com.gmail.uprial.custombazookas;

import com.gmail.uprial.custombazookas.firework.FireworkBooster;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.uprial.custombazookas.common.CustomLogger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import java.util.Locale;

class CustomBazookasCommandExecutor implements CommandExecutor {
    public static final String COMMAND_NS = "custombazookas";

    private final CustomBazookas plugin;

    CustomBazookasCommandExecutor(CustomBazookas plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(COMMAND_NS)) {
            final CustomLogger customLogger = new CustomLogger(plugin.getLogger(), sender);
            if ((args.length >= 1) && (args[0].equalsIgnoreCase("reload"))) {
                if (sender.hasPermission(COMMAND_NS + ".reload")) {
                    if (plugin.reloadCustomBazookasConfig(customLogger)) {
                        customLogger.info("CustomBazookas config reloaded.");
                    }
                    return true;
                }
            }
            else if((args.length >= 1) && (args[0].equalsIgnoreCase("give"))) {
                if (sender.hasPermission(COMMAND_NS + ".give")) {
                    if(args.length < 5) {
                        customLogger.error(String.format("Usage: %s give @player @key @power @amount", COMMAND_NS));
                        return false;
                    }

                    final Player player = plugin.getPlayerByName(args[1]);
                    if(player == null) {
                        customLogger.error(String.format("Player '%s' does not exist.", args[1]));
                        return false;
                    }

                    final Material material;
                    try {
                        material = Material.valueOf(args[2].toUpperCase(Locale.getDefault()));
                    } catch (IllegalArgumentException ignored) {
                        customLogger.error(String.format("Material '%s' does not exist.", args[2]));
                        return false;
                    }

                    final int power;
                    try {
                        power = Integer.valueOf(args[3]);
                    } catch (NumberFormatException ignored) {
                        customLogger.error(String.format("Power '%s' is not an integer", args[3]));
                        return false;
                    }
                    if((power < 1) || (power > FireworkBooster.MAX_BOOSTERS)) {
                        customLogger.error(String.format("Power should be an integer between 1 and %d.", FireworkBooster.MAX_BOOSTERS));
                        return false;
                    }

                    final int amount;
                    try {
                        amount = Integer.valueOf(args[4]);
                    } catch (NumberFormatException ignored) {
                        customLogger.error(String.format("Amount '%s' should be an integer", args[4]));
                        return false;
                    }
                    if((amount < 1) || (amount > 64)) {
                        customLogger.error("Power should be an integer between 1 and 64.");
                        return false;
                    }

                    final Recipe recipe = plugin.getRecipe(material, power);
                    if(recipe == null) {
                        customLogger.error(String.format("Recipe '%s-%d' does not exist.", material, power));
                        return false;
                    }

                    final ItemStack itemStack = recipe.getResult();
                    itemStack.setAmount(amount);
                    player.getInventory().addItem(itemStack);

                    customLogger.info(String.format("Player '%s' got %d * '%s-%d'", player.getName(), amount, material, power));

                    return true;
                }
            } else if ((args.length == 0) || (args[0].equalsIgnoreCase("help"))) {
                String helpString = "==== CustomBazookas help ====\n";

                if (sender.hasPermission(COMMAND_NS + ".reload")) {
                    helpString += '/' + COMMAND_NS + " reload - reload config from disk\n";
                }
                if (sender.hasPermission(COMMAND_NS + ".give")) {
                    helpString += '/' + COMMAND_NS + " give @player @key @power @amount - subj\n";
                }

                customLogger.info(helpString);
                return true;
            }
        }
        return false;
    }
}
