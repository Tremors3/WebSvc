package usecases;

import models.IPerson;
import repositories.IDb;

public class PersonService implements IPersonService {

    private IDb _myDb = null;

    public PersonService(IDb db) {
        this._myDb = db;
    }

    @Override
    public void updateBirth(IPerson person) throws Exception {
        this._myDb.updateBirth(person);
    }
}
