import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static MongoClient mongoClient;
    private static MongoDBConnection mongoDBConnection;

    private MongoDBConnection() throws Exception {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        } catch (MongoException ex) {
            throw new Exception("Mongo socket is busy");
        }
    }

    public static MongoDBConnection createConnection() throws Exception {
        if (mongoDBConnection == null) {
            mongoDBConnection = new MongoDBConnection();
            return mongoDBConnection;
        }
        return mongoDBConnection;
    }

    public MongoDatabase getInstance() {
        return mongoClient.getDatabase("TheGym");
    }

}
