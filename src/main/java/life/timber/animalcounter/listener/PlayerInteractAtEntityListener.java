package life.timber.animalcounter.listener;

import life.timber.animalcounter.AnimalCounter;
import life.timber.animalcounter.AnimalSpecies;
import life.timber.animalcounter.events.PlayerAnimalFoundEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {

        if (event.getPlayer() != null && event.getRightClicked() != null) {

            if (!AnimalCounter.getInstance().getAllAnimalUUIDs().get(event.getPlayer()).contains(event.getRightClicked().getUniqueId().toString())) {
                for (AnimalSpecies animalSpecies : AnimalSpecies.values()) {
                    if (event.getRightClicked().getType() == animalSpecies.getEntityType()) {
                        PlayerAnimalFoundEvent playerAnimalFoundEvent = new PlayerAnimalFoundEvent(event.getPlayer(), event.getRightClicked(), AnimalCounter.getInstance().getAllAnimalUUIDs().get(event.getPlayer()), AnimalCounter.getInstance().getAllAnimalTypeStrings().get(event.getPlayer()));
                        Bukkit.getPluginManager().callEvent(playerAnimalFoundEvent);
                    }
                }
            } else {
                event.getPlayer().sendMessage(AnimalCounter.getInstance().getPrefix() + "Dieses Tier hast du schon gefunden.");
            }
        }

    }
}
