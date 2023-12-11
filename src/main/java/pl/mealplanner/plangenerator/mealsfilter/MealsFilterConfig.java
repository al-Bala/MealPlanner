package pl.mealplanner.plangenerator.mealsfilter;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MealsFilterConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    @Bean
    MongoTemplate mongoTemplate(){
        MongoClient mongoClient = MongoClients.create(uri);
        return new MongoTemplate(mongoClient, databaseName);
    }

    @Bean
    MealsFilterRepository mealFilterRepository(){
        return new MealsFilterRepositoryImpl(mongoTemplate(), collection);
    }

    @Bean
    MealsFilterFacade mealsFilterFacade(){
        MealsFilterService service = new MealsFilterService(mealFilterRepository());
        return new MealsFilterFacade(service);
    }
    public MealsFilterFacade createForTest(){
        return new MealsFilterFacade(new MealsFilterService(mealFilterRepository()));
    }
}
