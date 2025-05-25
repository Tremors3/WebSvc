package configurations.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import environment.*;

public class LoadConfiguration extends AbstractModule {

    @Override
    public void configure() {
        // Configure Environment Readers
        bind(DotenvEnvironmentReader.class).in(Singleton.class);
        bind(SystemEnvironmentReader.class).in(Singleton.class);

        bind(IEnvironmentReader.class)
            .to(DotenvEnvironmentReader.class)
            .in(Singleton.class);
        bind(IEnvironment.class).to(TheEnvironment.class).in(Singleton.class);
    }
}
