package pl.mealplanner.plangenerator.mealsfilter;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MealsFilterDatabaseConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Bean(destroyMethod = "close")
    public MongoClient getMongoClient() {
            return MongoClients.create(uri);
    }
    @Bean
    public MongoDatabase getMongoDatabase() {
        return getMongoClient().getDatabase(databaseName);
    }
    @Bean
    public MongoCollection<Document> getMongoCollection() {
        return getMongoDatabase().getCollection("recipes");
    }
}
