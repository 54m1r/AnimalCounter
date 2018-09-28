package life.timber.animalcounter.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import life.timber.animalcounter.AnimalCounter;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {

    private HashMap<Player, Document> documents = new HashMap<>();

    public void createPlayer(Player player) {

        FindIterable<Document> find = AnimalCounter.getInstance().getMongoManager().getMongoCollection().find(Filters.eq("uuid", player.getUniqueId().toString()));
        if (find.first() == null) {
            AnimalCounter.getInstance().getAllAnimalUUIDs().put(player, new ArrayList<String>());
            AnimalCounter.getInstance().getAllAnimalTypeStrings().put(player, new ArrayList<String>());

            Document document = new Document();
            document.append("name", player.getName())
                    .append("uuid", player.getUniqueId().toString())
                    .append("animalUUIDs", new ArrayList<String>())
                    .append("animalTypeStrings", new ArrayList<String>());

            AnimalCounter.getInstance().getMongoManager().getMongoCollection().insertOne(document);
            documents.put(player, document);
        } else {
            loadPlayer(player, find.first());
        }
    }

    private void loadPlayer(Player player, Document document) {

        documents.put(player, document);
        AnimalCounter.getInstance().getAllAnimalTypeStrings().put(player, (ArrayList<String>) document.get("animalTypeStrings"));
        AnimalCounter.getInstance().getAllAnimalUUIDs().put(player, (ArrayList<String>) document.get("animalUUIDs"));

    }

    public void savePlayer(Player player) {

        ArrayList<String> types = AnimalCounter.getInstance().getAllAnimalTypeStrings().get(player);
        ArrayList<String> uuids = AnimalCounter.getInstance().getAllAnimalUUIDs().get(player);

        Document document = documents.get(player);

        document.append("name", player.getName())
                .append("animalUUIDs", uuids)
                .append("animalTypeStrings", types);

        AnimalCounter.getInstance().getMongoManager().getMongoCollection().updateOne(Filters.eq("uuid", player.getUniqueId().toString()), new Document("$set", document));

        documents.remove(player);
        AnimalCounter.getInstance().getAllAnimalTypeStrings().remove(player);
        AnimalCounter.getInstance().getAllAnimalUUIDs().remove(player);
    }
}
