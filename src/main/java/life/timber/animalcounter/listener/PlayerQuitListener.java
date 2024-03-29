package life.timber.animalcounter.listener;

import life.timber.animalcounter.AnimalCounter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        AnimalCounter.getInstance().getDatabaseManager().savePlayer(event.getPlayer());
    }
}
