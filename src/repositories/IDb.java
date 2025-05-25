package repositories;

import models.IPerson;

/**
 * Generic database interface.
 */
public interface IDb {
    void updateBirth(IPerson person) throws Exception;
}
