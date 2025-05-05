package repositories;

import models.IPerson;

public interface IDb {
	void updateBirth(IPerson person) throws Exception;  // Using a Person model
}
