package pl.mealplanner.plangenerator.packingchooser;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PackingChooserConfig {
    public PackingChooserFacade createForTest(){
        return new PackingChooserFacade(new PackingChooserService());
    }
}
