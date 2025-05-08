package services;

import com.google.inject.Inject;
import models.IPerson;
import repositories.IDb;

public class PersonService implements IPersonService {

    private IDb _myDb = null;

    @Inject
    public PersonService(IDb db) {
        this._myDb = db;
    }

    @Override
    public void updateBirth(IPerson person) throws Exception {
        this._myDb.updateBirth(person);
    }
}
