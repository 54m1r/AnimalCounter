package life.timber.animalcounter;

import life.timber.animalcounter.commands.StatsCommand;
import life.timber.animalcounter.database.DatabaseManager;
import life.timber.animalcounter.database.MongoManager;
import life.timber.animalcounter.listener.PlayerJoinListener;
import life.timber.animalcounter.listener.PlayerQuitListener;
import life.timber.animalcounter.listener.PlayerAnimalFoundListener;
import life.timber.animalcounter.listener.PlayerInteractAtEntityListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class AnimalCounter extends JavaPlugin {

    private static AnimalCounter instance;

    private MongoManager mongoManager;
    private DatabaseManager databaseManager;

    private final String prefix = "§lPREFIX §r";

    private HashMap<Player, ArrayList<String>> allAnimalTypeStrings = new HashMap<>();
    private HashMap<Player, ArrayList<String>> allAnimalUUIDs = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;

        this.mongoManager = new MongoManager("localhost", 27017);
        this.mongoManager.connect();

        this.databaseManager = new DatabaseManager();

        register();
    }

    @Override
    public void onDisable() {
        this.mongoManager.disconnect();
    }

    private void register() {
        this.getCommand("stats").setExecutor(new StatsCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerAnimalFoundListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    public static AnimalCounter getInstance() {
        return instance;
    }

    public MongoManager getMongoManager() {
        return mongoManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public String getPrefix() {
        return prefix;
    }

    public HashMap<Player, ArrayList<String>> getAllAnimalUUIDs() {
        return allAnimalUUIDs;
    }

    public HashMap<Player, ArrayList<String>> getAllAnimalTypeStrings() {
        return allAnimalTypeStrings;
    }
}
