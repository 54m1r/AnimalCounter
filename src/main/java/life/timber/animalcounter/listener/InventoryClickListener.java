package life.timber.animalcounter.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory().getName().startsWith("§&§lTierzähler §6§l")) {
            event.setCancelled(true);
        }
    }
}
