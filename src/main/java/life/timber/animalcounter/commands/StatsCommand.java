package life.timber.animalcounter.commands;

import life.timber.animalcounter.AnimalSpecies;
import life.timber.animalcounter.AnimalCounter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class StatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (sender.hasPermission("timber.tiertaehler.command.stats")) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    if (Bukkit.getPlayer(args[0]).isOnline()) {
                        player = Bukkit.getPlayer(args[0]);
                    } else {
                        sender.sendMessage(AnimalCounter.getInstance().getPrefix() + "Der Spieler ist nicht online.");
                        return false;
                    }
                } else {
                    sender.sendMessage(AnimalCounter.getInstance().getPrefix() + "Der Spieler konnte nicht gefunden werden.");
                    return false;
                }
            } else {
                player = (Player) sender;
            }

            Inventory inventory = Bukkit.createInventory(null, 9 * 5, "§&§lTierzähler §6§l" + player.getName());

            for (AnimalSpecies animalSpecies : AnimalSpecies.values()) {
                int i = 0;
                for (String entityType : AnimalCounter.getInstance().getAllAnimalTypeStrings().get(player)) {
                    if (animalSpecies.getEntityType().toString().equals(entityType)) {
                        i++;
                    }
                }

                ItemStack itemStack = new ItemStack(Material.APPLE);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(animalSpecies.toString() + " " + i);
                itemStack.setItemMeta(itemMeta);
                inventory.addItem(itemStack);
            }
            player.openInventory(inventory);
        } else {
            sender.sendMessage(AnimalCounter.getInstance().getPrefix() + "Du hast nicht die benötigten Rechte");
        }
        return false;
    }
}
