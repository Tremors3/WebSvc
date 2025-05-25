package unitTests;

import com.google.inject.AbstractModule;
import configurations.modules.CommonConfiguration;
import configurations.modules.LoadConfiguration;
import repositories.IDb;

public class TestConfiguration extends AbstractModule {

    @Override
    public void configure() {
        install(new LoadConfiguration());
        install(new CommonConfiguration());

        // Configuring Mock Database
        bind(IDb.class).to(MockThatThrowsException.class);
    }
}
