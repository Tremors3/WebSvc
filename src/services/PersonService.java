package services;

import com.google.inject.Inject;
import models.IPerson;
import repositories.IDb;

/**
 * Person service class, containing Person related business-logic.
 */
public class PersonService implements IPersonService {

    @Inject
    private IDb _myDb;

    @Override
    public void updateBirth(IPerson person) throws Exception {
        if (person.get_age() < 18)
            throw new Exception("Person cannot be less than 18 years old: \'" + person.get_age() + "\'");
        
        if (person.get_age() > 100)
            throw new Exception("Person cannot be more than 100 years old: \' " + person.get_age() + "\'");
        
        this._myDb.updateBirth(person);
    }
}
