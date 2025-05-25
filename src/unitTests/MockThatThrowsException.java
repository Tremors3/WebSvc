package unitTests;

import models.IPerson;
import repositories.IDb;

/**
 * Database mock for testing, that always throw an exception.
 */
public class MockThatThrowsException implements IDb {

    @Override
    public void updateBirth(IPerson person) throws Exception {
        throw new Exception(
            "User with " + person.get_id() + " does not exist!"
        );
    }
}
