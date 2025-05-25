package services;

import com.google.inject.Inject;
import models.IPerson;
import repositories.IDb;

public class PersonService implements IPersonService {

    @Inject
    private IDb _myDb;

    @Override
    public void updateBirth(IPerson person) throws Exception {
        this._myDb.updateBirth(person);
    }
}
