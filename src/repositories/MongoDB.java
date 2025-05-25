package repositories;

import models.IPerson;

/**
 * MongoDB IDb implementation, for production use.
 */
public class MongoDB implements IDb {

    @Override
    public void updateBirth(IPerson person) throws Exception {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
