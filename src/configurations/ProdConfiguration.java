package configurations;

import com.google.inject.AbstractModule;
import configurations.modules.CommonConfiguration;
import configurations.modules.LoadConfiguration;
import repositories.IDb;
import repositories.MongoDB;

public class ProdConfiguration extends AbstractModule {

    @Override
    public void configure() {
        install(new LoadConfiguration());
        install(new CommonConfiguration());

        // Configure Database
        bind(IDb.class).to(MongoDB.class);
    }
}
