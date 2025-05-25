package services;

import models.IPerson;

/**
 * Interface that define methods to manage Person's birth date.
 */
public interface IManageBirth {
    void updateBirth(IPerson person) throws Exception;
}
