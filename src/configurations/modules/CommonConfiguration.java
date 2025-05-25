package configurations.modules;

import com.google.inject.AbstractModule;
import modelBuilders.IPersonBuilder;
import modelBuilders.PersonBuilder;
import services.IManageBirth;
import services.IPersonService;
import services.PersonService;
import utils.IMapToPerson;
import utils.MapperPerson;
import web.MyServlet;

public class CommonConfiguration extends AbstractModule {

    @Override
    public void configure() {
        // Configure Person Service
        bind(IPersonService.class).to(PersonService.class);
        bind(IManageBirth.class).to(PersonService.class);

        // Configure Person Builder
        bind(IPersonBuilder.class).to(PersonBuilder.class);

        // Configure Web & Web Utils
        bind(MyServlet.class);
        bind(IMapToPerson.class).to(MapperPerson.class);
    }
}
