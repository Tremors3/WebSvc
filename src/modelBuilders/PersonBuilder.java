package modelBuilders;

import com.google.inject.Singleton;
import models.IPerson;
import models.Person;

/**
 * Builder for the Person model.
 */
@Singleton
public class PersonBuilder implements IPersonBuilder {

    protected PersonBuilder() {}

    @Override
    public IPerson getPerson() {
        return new Person();
    }
}
