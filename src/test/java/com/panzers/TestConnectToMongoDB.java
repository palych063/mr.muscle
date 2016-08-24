package com.panzers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.panzers.util.ConfigReader;
import org.apache.commons.configuration2.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TestConnectToMongoDB extends Assert {

    @Test
    public void testConnect() throws FileNotFoundException {
        ConfigReader configReader = new ConfigReader("mongoDB_test.properties");
        Configuration c = configReader.getConfig();
        MongoClient mongoClient = new MongoClient(c.getString("host") , c.getInt("port"));
        MongoDatabase db = mongoClient.getDatabase(c.getString("database"));
        assertNotNull(db);
        mongoClient.close();
    }
}
