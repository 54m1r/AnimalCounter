package life.timber.animalcounter.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class PlayerAnimalFoundEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private Entity animal;
    private ArrayList<String> alreadyFoundAnimalUUIDs;
    private ArrayList<String> alreadyFoundAnimalTypes;
    private boolean isCancelled;

    public PlayerAnimalFoundEvent(Player player, Entity animal, ArrayList<String> alreadyFoundAnimalUUIDs, ArrayList<String> alreadyFoundAnimalTypes) {
        this.player = player;
        this.animal = animal;
        this.alreadyFoundAnimalUUIDs = alreadyFoundAnimalUUIDs;
        this.alreadyFoundAnimalTypes = alreadyFoundAnimalTypes;

        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.isCancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public String getEventName() {
        return super.getEventName();
    }

    public ArrayList<String> getAlreadyFoundAnimalTypes() {
        return alreadyFoundAnimalTypes;
    }

    public ArrayList<String> getAlreadyFoundAnimalUUIDs() {
        return alreadyFoundAnimalUUIDs;
    }

    public Entity getEntity() {
        return animal;
    }

    public Player getPlayer() {
        return player;
    }
}
