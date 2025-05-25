package unitTests;

import models.IPerson;
import repositories.IDb;

public class MockThatThrowsException implements IDb {

    @Override
    public void updateBirth(IPerson person) throws Exception {
        throw new Exception(
            "User with " + person.get_id() + " does not exist!"
        );
    }
}
