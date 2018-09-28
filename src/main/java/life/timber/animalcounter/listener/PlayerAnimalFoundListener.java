package life.timber.animalcounter.listener;

import life.timber.animalcounter.AnimalCounter;
import life.timber.animalcounter.events.PlayerAnimalFoundEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerAnimalFoundListener implements Listener {

    @EventHandler
    public void onFound(PlayerAnimalFoundEvent event) {
        final Player player = event.getPlayer();
        final Entity entity = event.getEntity();

        player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 10);
        player.sendMessage(AnimalCounter.getInstance().getPrefix() + "Du hast ein/eine §6" + entity.toString() + " §7gefunden.");
        AnimalCounter.getInstance().getAllAnimalUUIDs().get(player).add(entity.getUniqueId().toString());
        AnimalCounter.getInstance().getAllAnimalTypeStrings().get(player).add(entity.getType().toString());
    }

}
