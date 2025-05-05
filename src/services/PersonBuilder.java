package services;

import models.IPerson;
import models.Person;

public class PersonBuilder implements IPersonBuilder{

    private static PersonBuilder _instance = null;

    public static IPersonBuilder GetInstance() {
        if(_instance == null)
            _instance = new PersonBuilder();

        return _instance;
    }

    @Override
    public IPerson getPerson() {
        return new Person();
    }

}
