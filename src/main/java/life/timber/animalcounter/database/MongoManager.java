package life.timber.animalcounter.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collections;

public class MongoManager {

    private int port;
    private String hostname;
    private String user;
    private String password;

    private boolean usePassword = false;

    private final String databseName = "minecraft";
    private final String collectionName = "players";

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;

    public MongoManager(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public MongoManager(String hostname, int port, String user, String password) {
        this.hostname = hostname;
        this.port = port;
        this.user = user;
        this.password = password;

        this.usePassword = true;
    }

    public void connect() {
        if (usePassword) {
            MongoCredential credential = MongoCredential.createCredential(user, databseName, password.toCharArray());
            this.mongoClient = new MongoClient(new ServerAddress(this.hostname, this.port), Collections.singletonList(credential));
            this.mongoDatabase = this.mongoClient.getDatabase(databseName);
        } else {
            this.mongoClient = new MongoClient(this.hostname, this.port);
            this.mongoDatabase = this.mongoClient.getDatabase(this.databseName);
        }

        this.mongoCollection = this.mongoDatabase.getCollection(this.collectionName);
    }


    public void disconnect() {
        this.mongoClient.close();
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }
}
