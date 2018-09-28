package life.timber.animalcounter.listener;

import life.timber.animalcounter.AnimalCounter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        AnimalCounter.getInstance().getDatabaseManager().createPlayer(event.getPlayer());
        event.getPlayer().sendMessage(AnimalCounter.getInstance().getPrefix() + " Du kannst Tiere finden. Klicke dazu einfach auf eines und du bekommst eine kleine Belohnung.");
    }

}
