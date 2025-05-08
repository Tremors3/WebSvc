package configurations;

import com.google.inject.AbstractModule;
import configurations.modules.CommonConfiguration;
import configurations.modules.LoadConfiguration;
import repositories.IDb;
import repositories.InMemoryDb;

public class LocalConfiguration extends AbstractModule {
    
    @Override
    public void configure() {
        install(new LoadConfiguration());
        install(new CommonConfiguration());
        
        // Configure Database
        bind(IDb.class).to(InMemoryDb.class);
    }
    
}
